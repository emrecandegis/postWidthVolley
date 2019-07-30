package com.example.requesttest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private ArrayList<Param> parameters;
    private String url;
    private RequestQueue reqQue;
    private final Context listenerActivity;

    public HttpRequest(Context context) {
        parameters  = new ArrayList();
        reqQue      = Volley.newRequestQueue(context);
        listenerActivity = context;
    }

    public HttpRequest addParam(String key, Object value) {

        switch (value.getClass().getSimpleName()) {
            case "Integer":
                parameters.add(new Param(key, (int) value));
                break;
            case "Double":
                parameters.add(new Param(key, (Double) value));
                break;
            case "String":
                parameters.add(new Param(key, (String) value));
                break;
            default:
                return this;
        }
        return this;
    }

    public HttpRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public void sendRequest() {
        StringRequest request = new StringRequest(Request.Method.POST,
                url,
                (Response.Listener<String>) listenerActivity,
                (Response.ErrorListener) listenerActivity) {
                    @Override
                    protected Map<String, String> getParams() {
                        return HttpRequest.this.getParams();
                }
        };
        reqQue.add(request);
    }

    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        for (int i = 0; i < parameters.size(); i++)
            params.put(parameters.get(i).key, parameters.get(i).value);
        return params;
    }

    //region Parameter Class
    private class Param {
        public String key;
        public String value;

        Param(String key, String value) {
            this.key = key.trim();
            this.value = value;
        }

        Param(String key, int value) {
            this.key = key.trim();
            this.value = Integer.toString(value);
        }

        Param(String key, double value) {
            this.key = key.trim();
            this.value = Double.toString(value);
        }
    }
    //endregion
}

