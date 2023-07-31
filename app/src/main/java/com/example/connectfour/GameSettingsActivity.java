package com.example.connectfour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class GameSettingsActivity extends AppCompatActivity
{
    EditText gridSizeText;
    EditText connectText;
    CheckBox gravityBox;
    Spinner presetsSpinner;

    public static final String Extra_Size = "com.example.connectfour.size";
    public static final String Extra_Connect = "com.example.connectfour.connect";
    public static final String Extra_Gravity = "com.example.connectfour.gravity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getString(R.string.occupiedStr);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);

        gridSizeText = findViewById(R.id.gridSizeText);
        connectText = findViewById(R.id.connectText);
        gravityBox = findViewById(R.id.checkBox);
        presetsSpinner = findViewById(R.id.spinner);


        presetsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String preset = (String) parent.getItemAtPosition(position);

                switch (preset)
                {
                    case "Connect Four":
                    {
                        presets(6,4,true);
                    }break;
                    case "Connect Four Big":
                    {
                        presets(10,6,true);
                    }break;
                    case "Tic Tac Toe":
                    {
                        presets(3,3,false);
                    }break;
                    default:
                    {

                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }


    public void onStartButtonClick(View view)
    {
        int size , connect;
        boolean gravity;
        try
        {
            Log.d("onStartButtonClick"," Made it to try");

            size = Integer.parseInt(gridSizeText.getText().toString());
            connect = Integer.parseInt(connectText.getText().toString());
            gravity = gravityBox.isChecked();

            Intent intent = new Intent(this,GameActivity.class);
            intent.putExtra(Extra_Size, size);
            intent.putExtra(Extra_Connect, connect);
            intent.putExtra(Extra_Gravity, gravity);

            Log.d("onStartButtonClick"," Made it to end of try");

            startActivity(intent);
        }
        catch (NumberFormatException e)
        {
            Log.d("onStartButtonClick"," Made it to catch");
            Toast.makeText(GameSettingsActivity.this, "Please enter values for all settings.", Toast.LENGTH_LONG).show();
        }
    }

    public void presets (int size, int connect, boolean gravity)
    {
        gridSizeText.setText(Integer.toString(size));
        connectText.setText(Integer.toString(connect));
        gravityBox.setChecked(gravity);
    }
}