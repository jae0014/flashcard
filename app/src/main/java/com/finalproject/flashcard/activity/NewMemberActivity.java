package com.finalproject.flashcard.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.finalproject.flashcard.R;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class NewMemberActivity extends AppCompatActivity {

    private boolean checkValidPass;
    private boolean checkValidID;
    private boolean checkValidEmail;



    private EditText mNickName;
    private EditText mName;
    private EditText mUserId;
    private EditText mPass;
    private EditText mPassCheck;
    private EditText mBirthDate;
    private EditText mEmail;
    private EditText mAddress;
    private EditText mIntroduce;

    private Button mDoubleCheck;
    private Button mConfirm;

    public boolean validatoin()
    {
        if(checkValidEmail && checkValidID && checkValidPass && !mIntroduce.getText().toString().isEmpty())
            return true;
        else
            return false;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_member_form);

        mNickName = (EditText)findViewById(R.id.nick_name);
        mName = (EditText)findViewById(R.id.user_name);
        mUserId = (EditText)findViewById(R.id.user_id);
        mPass = (EditText)findViewById(R.id.pass1);
        mPassCheck = (EditText)findViewById(R.id.pass2);
        mBirthDate = (EditText)findViewById(R.id.birthday);
        mEmail = (EditText)findViewById(R.id.email);
        mAddress = (EditText)findViewById(R.id.address);
        mIntroduce = (EditText)findViewById(R.id.intro);

        mDoubleCheck = (Button) findViewById(R.id.user_id_button);
        mConfirm =  (Button) findViewById(R.id.new_member);



        mPassCheck.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                String str1 = mPass.getText().toString();
                String str2 = mPassCheck.getText().toString();

                if(!hasFocus && (str1.isEmpty() || str2.isEmpty()))
                {
                    Toast.makeText(getApplicationContext(),"비밀번호를 입력해주세요.",Toast.LENGTH_LONG).show();
                }
                else if(!hasFocus &&(!str1.equals(str2)))
                {
                    Toast.makeText(getApplicationContext(),"비번이 일치 하지 않아요",Toast.LENGTH_LONG).show();
                }
                else
                    checkValidPass = true;

            }
        });

        mEmail.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(!hasFocus && !mEmail.getText().toString().isEmpty())
                {
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2:1222/oracle",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    if(response.toString().equals("NO_RESULT_FOUND") )
                                    {
                                        Toast.makeText(getApplicationContext(),"사용 가능한 이메일 입니다.",Toast.LENGTH_LONG).show();
                                        checkValidEmail = true;
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
                            params.put("sql","SELECT * FROM MEMBER WHERE EMAIL ='" +mEmail.getText().toString() +"'");
                            return params;
                        }
                    };

                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);

                }
            }
        });

        mDoubleCheck.setOnClickListener(new View.OnClickListener() {
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
                                    Toast.makeText(getApplicationContext(),"사용 가능한 아이디입니다",Toast.LENGTH_LONG).show();
                                    checkValidID = true;
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
                        params.put("sql","SELECT * FROM MEMBER WHERE MID ='" +mUserId.getText().toString() +"'");
                        return params;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validatoin()) {
                    //new Client().requestJSON("http://10.0.2.2:1222/testURL","user","pass" , textView, con;
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2:1222/oracleCreate",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    if (response.toString().equals("NO_RESULT_FOUND")) {
                                        Toast.makeText(getApplicationContext(), "실패하였습니다.", Toast.LENGTH_LONG).show();
                                    }
                                    if(response.toString().equals("INSERTED"))
                                    {
                                        Toast.makeText(getApplicationContext(), "성공하였습니다.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                        }

                    }) {
                        @Override
                        protected Map<String, String> getParams() {

                            String date = mBirthDate.getText().toString();

                            Map<String, String> params = new HashMap<String, String>();
                            params.put("sql", "INSERT INTO MEMBER VALUES("
                                    + "'"   + mUserId.getText().toString()+"',"
                                    + "'"   + mPass.getText().toString()+"',"
                                    + "'"   + mName.getText().toString()+"',"
                                    + "TO_DATE('"+ date+"','YYYYMMDD'),"
                                    + "'"   + mEmail.getText().toString()+"',"
                                    + "'"   + mAddress.getText().toString()+"',"
                                    +"SYSDATE,SYSDATE,'Y',"
                                    + "'"   + mIntroduce.getText().toString()+"',"
                                    + "'"   + mNickName.getText().toString()+"'"
                                    + ")");
                            return params;
                        }
                    };

                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);
                }
            }
        });
    }
}
