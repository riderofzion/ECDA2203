package com.quentinrouet.okhttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpClient client = new OkHttpClient();
        Request requestLuke = new Request.Builder()
                .url("https://swapi.dev/api/people/1")
                .build();
        client.newCall(requestLuke).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.code() == 200){
                    String responseLuke = response.body().string();
                    Log.i(TAG, "onResponse: "+ responseLuke);
                    try {
                        JSONObject jsonLuke = new JSONObject(responseLuke);
                        People luke = new People(
                                jsonLuke.getString("name"),
                                jsonLuke.getString("height"),
                                jsonLuke.getString("mass"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}