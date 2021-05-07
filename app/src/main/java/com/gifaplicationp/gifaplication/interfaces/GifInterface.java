package com.gifaplicationp.gifaplication.interfaces;

import com.gifaplicationp.gifaplication.GifApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GifInterface {

    @GET("?api_key=PBB08fVPiIErnJe1zPPEZG4t84lOlk3V&q={q}")
    public Call<GifApi> find(@Path("q") String q);
}
