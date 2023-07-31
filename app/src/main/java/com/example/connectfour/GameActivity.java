package com.example.connectfour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import static com.example.connectfour.GameSettingsActivity.Extra_Size;
import static com.example.connectfour.GameSettingsActivity.Extra_Connect;
import static com.example.connectfour.GameSettingsActivity.Extra_Gravity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity
{
    GridLayout gameGrid;
    TextView playerTextView;

    private int size;
    private int connect;
    private boolean gravity;

    private int player1Color;
    private int player2Color;
    private int offColor;

    ConnectFour connectFour;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        playerTextView = findViewById(R.id.playerTextView);
        gameGrid = findViewById(R.id.gameGrid);
        Intent intent = getIntent();
        size = intent.getIntExtra(Extra_Size, 1);
        connect = intent.getIntExtra(Extra_Connect, 1);
        gravity = intent.getBooleanExtra(Extra_Gravity, true);

        gameGrid.setRowCount(size);
        gameGrid.setColumnCount(size);

        player1Color = ContextCompat.getColor(this,R.color.red);
        player2Color = ContextCompat.getColor(this,R.color.blue);
        offColor = ContextCompat.getColor(this,R.color.white);

        connectFour = new ConnectFour(size,connect,GameActivity.this);
        makeButtons();
        setColors();

    }
    private void makeButtons()
    {
        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int index = gameGrid.indexOfChild(view);
                int row = index / size;
                int col = index % size;
                if (gravity)
                {
                    connectFour.setValue(col);
                }
                else
                {
                    connectFour.setValue(row,col);
                }
                setColors();
                if (connectFour.checkWinner())
                {
                    int playerNum;
                    if (connectFour.player) playerNum = 2;
                    else                    playerNum = 1;
                    playerTextView.setText("Player " + playerNum + " Wins");
                }
                connectFour.player = !connectFour.player;


            }
        };
        for (int i = 0; i < gameGrid.getRowCount() * gameGrid.getColumnCount(); i++)
        {
            Button button = new Button(this);
            button.setOnClickListener(clickListener);
            int buttonSize = gameGrid.getWidth()/size;
            gameGrid.addView(button, i);
        }


    }
    private void setColors()
    {
        if (connectFour.player)
            playerTextView.setText("Player 1");
        if (!connectFour.player)
            playerTextView.setText("Player 2");
        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                //Log.d("D", "setColors: "+row+","+col);
                int index = row * size + col;
                if (connectFour.checkValue(row,col)==0)
                    gameGrid.getChildAt(index).setBackgroundColor(offColor);
                else if (connectFour.checkValue(row,col) == 1)
                    gameGrid.getChildAt(index).setBackgroundColor(player1Color);
                else
                    gameGrid.getChildAt(index).setBackgroundColor(player2Color);
            }
        }
    }

}