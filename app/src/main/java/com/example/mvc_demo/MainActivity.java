package com.example.mvc_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText=findViewById(R.id.et);
        final TextView textView=findViewById(R.id.tv);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time=editText.getText().toString();
                if (time.equals("")){
                    Toast.makeText(MainActivity.this,"输入为空",Toast.LENGTH_SHORT).show();
                }else {
                    Data.getData(time,textView, new Data.OkhttpCallBack() {
                        @Override
                        public void onSuccess(final String response) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(response);
                                    Toast.makeText(MainActivity.this,"查询成功",Toast.LENGTH_SHORT).show();
//                                    Log.i("asd",response);
                                }
                            });

                        }

                        @Override
                        public void onFail(String error) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"查询失败",Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    });

                }
            }
        });
    }
}