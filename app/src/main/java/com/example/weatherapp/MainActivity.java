package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.Retrofit.ApiClient;
import com.example.weatherapp.Retrofit.ApiInterface;
import com.example.weatherapp.Retrofit.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ImageView search;
    TextView tempText,deseText,humText;
    EditText textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        search = findViewById(R.id.searchID);
        tempText = findViewById(R.id.tempText);
        deseText = findViewById(R.id.deseText);
        humText = findViewById(R.id.humText);
        textField = findViewById(R.id.textField);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Here will call Api........

              getWeatherData(textField.getText().toString().trim());


            }
        });
    }
    private void getWeatherData(String name){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Example> call = apiInterface.getWeatherData(name);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                tempText.setText("Temp : "+" "+response.body().getMain().getTemp()+" C");
                deseText.setText("Feels Like :"+" "+response.body().getMain().getFeels_like());
                humText.setText("Humidity :"+" "+response.body().getMain().getHumidity());


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }
}
