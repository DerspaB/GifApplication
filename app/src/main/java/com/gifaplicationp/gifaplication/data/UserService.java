package com.gifaplicationp.gifaplication.data;

import android.content.Context;
import android.util.Log;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.concurrent.ListenableFuture;
import androidx.concurrent.callback.CallbackToFutureAdapter;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gifaplicationp.gifaplication.R;
import com.gifaplicationp.gifaplication.ui.login.LoggedInUserView;
import com.gifaplicationp.gifaplication.ui.login.LoginResult;
import com.gifaplicationp.gifaplication.ui.register.RegisterResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final String ROOT_URL = "http://192.168.0.7:3700/api";
    private final String REGISTER_URL = ROOT_URL + "/register-user";
    private final String AUTHENTICATE_URL = ROOT_URL + "/authenticate-user";
    private String username;
    private String password;
    private String singupError;
    private String authenticateError;
    private Context context;

    public UserService(String username, String password, Context context) {
        this.username = username;
        this.password = password;
        this.context = context;
        this.singupError = null;
        this.authenticateError = null;
    }

    private JSONObject getBody() {
        Map<String,String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        JSONObject jsonObject = new JSONObject(params);
        Log.i("INFO", "getBody: " + jsonObject.toString());
        return jsonObject;
    }

    public void loginAuthenticate(MutableLiveData<LoginResult> loginResult) {
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.start();
        JsonObjectRequest stringRequest =
                new JsonObjectRequest(Request.Method.POST, AUTHENTICATE_URL, getBody(),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                loginResult.setValue(new LoginResult(new LoggedInUserView(username)));
                                Log.i("INFO", " - response: " + response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String responseBody = null;
                        try {
                            loginResult.setValue(new LoginResult(R.string.login_failed));
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
        requestQueue.add(stringRequest);
    }

    public void singup(MutableLiveData<RegisterResult> registerResult) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.start();
        JsonObjectRequest stringRequest =
                new JsonObjectRequest(Request.Method.POST, REGISTER_URL, getBody(),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                registerResult.setValue(new RegisterResult(true, username));
                                Log.i("INFO", " - response: " + response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String responseBody = null;
                        try {
                            registerResult.setValue(new RegisterResult(R.string.signup_failed));
                            responseBody = new String(error.networkResponse.data, "utf-8");
                            JSONObject data = new JSONObject(responseBody);
                            singupError = data.getString("message");
                            Log.i("ERROR", " - " + singupError);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        requestQueue.add(stringRequest);
    }
}
