package com.gifaplicationp.gifaplication.data;

import android.content.Context;
import android.util.Log;
import android.widget.TableRow;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final String ROOT_URL = "http://192.168.0.7:3700/api";
    private final String REGISTER_URL = ROOT_URL + "/register-user";
    private final String AUTHENTICATE_URL = ROOT_URL + "/authenticate-user";
    private String userId;
    private String username;
    private String password;
    private String registerError;
    private String authenticateError;
    private Context context;

    public UserService(String username, String password, Context context) {
        this.username = username;
        this.password = password;
        this.context = context;
        this.registerError = null;
        this.authenticateError = null;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRegisterError() {
        return registerError;
    }

    public String getAuthenticateError() {
        return authenticateError;
    }


    private JSONObject getBody() {
        Map<String,String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        JSONObject jsonObject = new JSONObject(params);
        Log.i("INFO", "getBody: " + jsonObject.toString());
        return jsonObject;
    }

    public void loginAuthenticate() {
        JsonObjectRequest stringRequest =
                new JsonObjectRequest(Request.Method.POST, AUTHENTICATE_URL, getBody(),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject user = response.getJSONObject("user");
                                    userId = user.getString("_id");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Log.i("INFO", " - response: " + response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String responseBody = null;
                        try {
                            responseBody = new String(error.networkResponse.data, "utf-8");
                            JSONObject data = new JSONObject(responseBody);
                            authenticateError = data.getString("message");
                            Log.i("ERROR", " - " + authenticateError);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
