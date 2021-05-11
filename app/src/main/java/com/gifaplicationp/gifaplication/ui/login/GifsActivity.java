package com.gifaplicationp.gifaplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gifaplicationp.gifaplication.data.GifApi;
import com.gifaplicationp.gifaplication.R;

public class GifsActivity extends AppCompatActivity {
    public EditText busqueda;
    private TextView tvUserWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifs);
        busqueda = findViewById(R.id.etBusqueda);
        tvUserWelcome = findViewById(R.id.tvUserWelcome);
        setUsername();
    }

    public void findGif(View view){
        String parametro = busqueda.getText().toString();
        GifApi gifApi = new GifApi();
        gifApi.setParametro(parametro);
        ImageView imageView = findViewById(R.id.imageView);
        gifApi.getRequest(this, imageView);
    }

    private void setUsername() {
        Bundle catchData = getIntent().getExtras();
        String username = catchData.getString("username");
        String welcome = getString(R.string.user_view) + " " + username;
        tvUserWelcome.setText(welcome);
    }
}