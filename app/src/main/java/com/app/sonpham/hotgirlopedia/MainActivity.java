package com.app.sonpham.hotgirlopedia;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String MY_URL="http://192.168.1.107/sexybae/VNSB.json";
     Button btnGetIn;
     ProgressBar prgProgress;
     TextView tvProgress;
    ArrayList<HotGirl> mData;
    private Intent intenMain2Activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetIn=(Button)findViewById(R.id.btnGetIn);
        prgProgress=(ProgressBar)findViewById(R.id.progressBar);
        tvProgress=(TextView)findViewById(R.id.textView2);

        checkNetWorkAvailable();
        btnGetIn.setEnabled(false);
        btnGetIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intenMain2Activity =new Intent(MainActivity.this,Main2Activity.class);
                intenMain2Activity.putParcelableArrayListExtra("data",mData);
                startActivity(intenMain2Activity);
                finish();
            }
        });
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private void checkNetWorkAvailable(){
        if(isNetworkAvailable()==false){
            AlertDialog dialog=new AlertDialog.Builder(MainActivity.this).create();
            dialog.setTitle("NetWork Not Available !");
            dialog.setMessage("Try Again ?");
            dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    checkNetWorkAvailable();
                }
            });
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }else {
            MyASyncTask myASyncTask=new MyASyncTask(MainActivity.this, MY_URL, new MyASyncTask.AsyncResponse() {
                @Override
                public void processFinish(ArrayList<HotGirl> output) {
                    mData=output;
                }
            });
            myASyncTask.execute();
        }
    }
}
