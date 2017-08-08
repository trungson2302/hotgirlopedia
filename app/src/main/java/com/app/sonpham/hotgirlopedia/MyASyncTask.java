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
    private OkHttpClient mOkHttpClient = new OkHttpClient();
    private AsyncResponse mAsyncResponse =null;

    public interface AsyncResponse {
        void processFinish(ArrayList<HotGirl> output);
    }

    public MyASyncTask(Context context, String url, AsyncResponse asyncResponse) {
        mContext=context;
        mURL=url;
        this.mAsyncResponse =asyncResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mData=new ArrayList<HotGirl>();
        ((LoadActivity)mContext).progressBar.setMax(100);
    }

    /**
     * parse JSON and put into mData
     * @param params
     * @return
     */
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
        ((LoadActivity)mContext).progressBar.setProgress(values[0]);
        ((LoadActivity)mContext).textView.setText(values[0]+"/"+100);
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        if(aVoid.equalsIgnoreCase("FILES DOWNLOADED")){
            ((LoadActivity)mContext).progressBar.setProgress(100);
            ((LoadActivity)mContext).textView.setText(aVoid);
            ((LoadActivity)mContext).button.setEnabled(true);
            mAsyncResponse.processFinish(mData);
        }
    }

    /**
     * run OkHttpClient
     * @param url
     * @return
     * @throws IOException
     */
    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = mOkHttpClient.newCall(request).execute();
        return response.body().string();
    }

}
