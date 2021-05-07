package com.gifaplicationp.gifaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gifaplicationp.gifaplication.interfaces.GifInterface;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GifsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifs);
    }

    public void findGif(View view){
        GifApi gifApi = new GifApi();
        ImageView imageView = findViewById(R.id.imageView);
        gifApi.getRequest(this);
        String urlGif = "https://giphy.com/gifs/snk-attack-on-titan-eren-jaeger-nWlYxbSRSzI6Q";
        Glide.with(getBaseContext()).load(urlGif).into(imageView);
    }
}