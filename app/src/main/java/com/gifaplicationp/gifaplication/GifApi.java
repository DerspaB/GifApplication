package com.gifaplicationp.gifaplication;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public class GifApi {
    private String url = "https://api.giphy.com/v1/gifs/search?api_key=PBB08fVPiIErnJe1zPPEZG4t84lOlk3V&q=naruto";
    private String parametro = "";
    private static  final String apiKey = "PBB08fVPiIErnJe1zPPEZG4t84lOlk3V";

    public GifApi() {}

    public GifApi(String parametro) {
        this.parametro = parametro;
    }

    public void getRequest(Activity activity){
        RequestQueue queue = Volley.newRequestQueue(activity);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, this.url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject  response) {
//                respGet.setText("Data: "+response);
                Toast.makeText(activity, "Data: "+response, Toast.LENGTH_LONG).show();


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
