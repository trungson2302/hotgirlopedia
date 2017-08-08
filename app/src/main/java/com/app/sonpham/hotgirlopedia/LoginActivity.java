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
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText mEditTextId, mEditTextPw;
    private Button mButtonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditTextId =(EditText)findViewById(R.id.editText);
        mEditTextPw =(EditText)findViewById(R.id.editText2);
        mButtonLogin =(Button)findViewById(R.id.button3);

        /**
         * login
         */
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEditTextId.getText().toString().equalsIgnoreCase("admin") &&
                        mEditTextPw.getText().toString().equalsIgnoreCase("admin")){
                    Intent intent=new Intent(LoginActivity.this,LoadActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Wrong ID or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    /**
//     * check if network is available
//     * @return true if available
//     */
//    private boolean isNetworkAvailable() {
//        ConnectivityManager connectivityManager
//                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
//    }
//    private void checkNetWorkAvailable(){
//        if(isNetworkAvailable()==false){
//            AlertDialog dialog=new AlertDialog.Builder(LoginActivity.this).create();
//            dialog.setTitle("NetWork Not Available !");
//            dialog.setMessage("Try Again ?");
//            dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    checkNetWorkAvailable();
//                }
//            });
//            dialog.setCanceledOnTouchOutside(false);
//            dialog.show();
//        }else {
//            // do stuff
//        }
//    }
}
