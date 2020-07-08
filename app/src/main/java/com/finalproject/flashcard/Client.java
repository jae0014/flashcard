package com.finalproject.flashcard;


import android.content.Context;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;

public class Client {

    public Client(){}

    public void requestJSON (String urlString, String flashcard,  String userID, TextView textView, Context context)
    {    RequestQueue queue = Volley.newRequestQueue(context);



    }


}
