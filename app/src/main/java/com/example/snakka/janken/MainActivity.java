package com.example.snakka.janken;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.gu).setOnClickListener(this);
        findViewById(R.id.choki).setOnClickListener(this);
        findViewById(R.id.pa).setOnClickListener(this);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }


    @Override
    public void onClick(View view){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("MY_HAND", view.getId());
        startActivity(intent);
    }

}