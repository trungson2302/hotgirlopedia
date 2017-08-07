package com.app.sonpham.hotgirlopedia;

import android.content.Context;
import android.os.AsyncTask;

import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Son Pham on 7/24/2017.
 */

public class MyASyncTask extends AsyncTask<Void,Integer,String> {
    private Context mContext;
    private ArrayList<HotGirl> mData;
    private String mURL;
    private String mStringData="";
    private String mStatus="FAILED TO DOWNLOAD FILES";
    private OkHttpClient client = new OkHttpClient();
    private AsyncResponse asyncResponse=null;
    private FirebaseDatabase firebaseDatabase;
    public interface AsyncResponse {
        void processFinish(ArrayList<HotGirl> output);
    }

    public MyASyncTask(Context context, String url, AsyncResponse asyncResponse) {
        mContext=context;
        mURL=url;
        this.asyncResponse=asyncResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mData=new ArrayList<HotGirl>();
        ((MainActivity)mContext).prgProgress.setMax(100);
        firebaseDatabase=FirebaseDatabase.getInstance();
    }

    @Override
    protected String doInBackground(Void... params) {
        try{
            mStringData=run(mURL);
            JSONObject jsonObject=new JSONObject(mStringData);
            JSONArray jsonArray=jsonObject.getJSONArray("hotgirls");
            int j=jsonArray.length();
        for (int i=0;i<j;i++){

            publishProgress((i*100)/j);
            JSONObject object = jsonArray.getJSONObject(i);
            HotGirl girl=new HotGirl();
            girl.setmID(object.getInt("id"));
            girl.setmFB(object.getString("urlfb"));
            girl.setmName(object.getString("name"));
            girl.setmInsta(object.getString("urlinsta"));
            girl.setmPhoto1(object.getString("photo1"));
            girl.setmPhoto2(object.getString("photo2"));
            girl.setmPhoto3(object.getString("photo3"));
            girl.setmPhoto4(object.getString("photo4"));
            girl.setmPhoto5(object.getString("photo5"));
            mData.add(girl);
            if(i==j-1)mStatus="FILES DOWNLOADED"; else mStatus="RUNNING";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mStatus;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        ((MainActivity)mContext).prgProgress.setProgress(values[0]);
        ((MainActivity)mContext).tvProgress.setText(values[0]+"/"+100);
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        if(aVoid.equalsIgnoreCase("FILES DOWNLOADED")){
            ((MainActivity)mContext).prgProgress.setProgress(100);
            ((MainActivity)mContext).tvProgress.setText(aVoid);
            ((MainActivity)mContext).btnGetIn.setEnabled(true);
            asyncResponse.processFinish(mData);
        }
    }
    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
