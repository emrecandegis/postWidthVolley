package com.example.requesttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class MainActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  paramString = "test";
                double  paramDouble = 123.5d;
                int     paramInt = 111;

                new HttpRequest(MainActivity.this).
                        addParam("paramString", paramString ).
                        addParam("paramDouble", paramDouble).
                        addParam("paramInt"   , paramInt ).
                        setUrl("http://httpbin.org/post").
                        sendRequest();
            }
        });
    }
    @Override
    public void onResponse(String response) {
        Log.d("response", response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
    }
}
