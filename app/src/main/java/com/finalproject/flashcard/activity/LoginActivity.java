package com.finalproject.flashcard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.finalproject.flashcard.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private Button mLoginButton;

    private Button mNewMemberButton;
    private EditText mUserIDEdit;
    private EditText mUserPassEdit;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mNewMemberButton=(Button)findViewById(R.id.new_user);
        mLoginButton = (Button)findViewById(R.id.login);
        mUserIDEdit = (EditText)findViewById(R.id.login_id);
        mUserPassEdit = (EditText)findViewById(R.id.login_pass);


        // 새 맴버 폼
        mNewMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newForm =new Intent(getApplicationContext(),NewMemberActivity.class);
                startActivity(newForm);


            }
        });
        //로그인 시도
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //new Client().requestJSON("http://10.0.2.2:1222/testURL","user","pass" , textView, con;
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                
                // Request a string response from the provided URL.

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2:1222/oracle",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                               if(response.toString().equals("NO_RESULT_FOUND") )
                               {
                                   Toast.makeText(getApplicationContext(),"로그인 실패",Toast.LENGTH_LONG);
                               }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error",error.toString());
                    }

                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("sql","SELECT * FROM MEMBER WHERE MID ='" +mUserIDEdit.getText().toString()  +"' AND PWD = '"+ mUserIDEdit.getText()+"'");
                        return params;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }
}
