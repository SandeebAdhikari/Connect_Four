package com.example.connectfour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartClick(View view)
    {
        Intent intent = new Intent(this, GameSettingsActivity.class);
        startActivity(intent);
    }

    public void onButtonClick(View view ){
        Uri webpage = Uri.parse("http://www.ccm.edu");
        Intent  intent = new Intent (Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }
}