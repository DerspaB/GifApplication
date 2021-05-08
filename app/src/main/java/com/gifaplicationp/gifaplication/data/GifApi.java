package com.gifaplicationp.gifaplication.data;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public class GifApi {
    private String urlRequest = "https://api.giphy.com/v1/gifs/random?api_key=PBB08fVPiIErnJe1zPPEZG4t84lOlk3V&tag=";
    private String parametro;
    private JSONObject respuesta;

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public GifApi() {}

    public GifApi(String parametro) {
        this.parametro = parametro;
    }

    public void getRequest(Activity activity, ImageView imageView){
        RequestQueue queue = Volley.newRequestQueue(activity);
        urlRequest = urlRequest+this.parametro;
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, this.urlRequest,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject  response) {
                try {
                    JSONObject data = response.getJSONObject("data");
                    JSONObject images = data.getJSONObject("images");
                    JSONObject medium = images.getJSONObject("downsized_medium");
                    String url = medium.getString("url");
                    Glide.with(activity).load(url).into(imageView);
                } catch (JSONException e) {
                    e.printStackTrace();
                }





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                respGet.setText("Data: Response failed");
                Toast.makeText(activity, "Data: Response failed", Toast.LENGTH_LONG).show();
            }

        }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headerParams = new HashMap<>();
//                headerParams.put("api_key", "PBB08fVPiIErnJe1zPPEZG4t84lOlk3V");
//                headerParams.put("q", "naruto");
//                Log.i(null, "dentro de parametros: " + headerParams);
//                return headerParams;
//            }
        };

        queue.add(stringRequest);
    }


}
