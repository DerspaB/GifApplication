package com.gifaplicationp.gifaplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gifaplicationp.gifaplication.data.GifApi;
import com.gifaplicationp.gifaplication.R;

public class GifsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifs);

    }

    public void findGif(View view){
        GifApi gifApi = new GifApi();
        ImageView imageView = findViewById(R.id.imageView);
        TextView tvResultado = findViewById(R.id.tvResultado);
        gifApi.getRequest(this, tvResultado);
    }
}