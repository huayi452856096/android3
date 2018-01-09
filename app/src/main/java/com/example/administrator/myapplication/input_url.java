package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;//注意view的大小写
import android.view.View.OnClickListener;
import android.net.Uri;
import android.widget.EditText;

public class input_url extends AppCompatActivity {

    private Button browse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Browse();
    }
    void Browse(){
        browse = (Button)findViewById(R.id.btn1) ;
        browse.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText text = (EditText) findViewById(R.id.editUri);
                String address = text.getText().toString();
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(address);
                intent.setData(content_url);
                startActivity(intent);
            }
        });

    }
}

