package com.example.mvc_demo;


import android.widget.TextView;
import android.annotation.SuppressLint;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class Data {



    public static void getData(final String time, final TextView textview,final OkhttpCallBack okhttpCallBack){
        try {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    Request request = new Request.Builder()
                            .url("https://news-at.zhihu.com/api/3/news/before/"+time)
                            .get()
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            okhttpCallBack.onFail(e.getMessage()+"asdfghjkl");
                            Log.i("asd",e.getMessage());
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            okhttpCallBack.onSuccess(Objects.requireNonNull(response.body()).string());
                        }
                    });
                }
            });
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public interface OkhttpCallBack{
        void onSuccess(String response);
        void onFail(String error);
    }
}
