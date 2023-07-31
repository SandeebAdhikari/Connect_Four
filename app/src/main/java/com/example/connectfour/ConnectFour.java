package com.example.connectfour;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ConnectFour
{
    private Context context;

    public boolean player = false;
    private static int GRID_SIZE;
    private static int CONNECT_LENGTH;
    private final int[][] connectGrid;

    int lastValueRow = 0;
    int lastValueCol = 0;

    public ConnectFour(int row, int con, Context cont)
    {
        GRID_SIZE = row;
        CONNECT_LENGTH = con;
        connectGrid = new int[row][row];
        context = cont;
    }

    public void newGame() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                connectGrid[row][col] = 0;
            }
        }
    }

    public int checkValue(int row, int col) {
        return connectGrid[row][col];
    }

    public void setValue(int col)
    {
        int row = 0;
        boolean done = false;
        while (row < GRID_SIZE && !done)
        {
            if (connectGrid[0][col] != 0)
            {
                Log.d("Full location at:", row + "," + col);
                Toast.makeText(context, "This location is occupied try again", Toast.LENGTH_LONG).show();
            }
            else if (connectGrid[row][col] != 0)
            {
                if (player)
                    connectGrid[row - 1][col] = 2;
                else if (!player)
                    connectGrid[row - 1][col] = 1;
                lastValueRow = row;
                lastValueCol = col;
                done = true;
            }
            else if (row == GRID_SIZE - 1)
            {
                if (player)
                    connectGrid[row][col] = 2;
                else if (!player)
                    connectGrid[row][col] = 1;
                lastValueRow = row;
                lastValueCol = col;
                done = true;
            }
            row++;
        }
    }
    public void setValue(int row, int col)
    {
        if (checkValue(row, col) == 0)
        {
            if (player)
                connectGrid[row][col] = 2;
            else if (!player)
                connectGrid[row][col] = 1;
            lastValueRow = row;
            lastValueCol = col;
        }
        else
        {
            Toast.makeText(context, "This location is occupied try again", Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkWinner()
    {
        int playerNum = 0;
        int totalConnect = 1;
        for (int row = -1; row <= 1; row++)
        {
            for (int col = -1; col <= 1; col++)
            {
                if (player) playerNum = 2;
                else        playerNum = 1;
                if (isOnGrid(row + lastValueRow,col + lastValueCol))
                {
                    if (row != 0 || col != 0)
                    {
                        if (checkValue(lastValueRow+row,lastValueCol+col) == playerNum)
                        {
                            totalConnect++;
                            for (int m = 2; m < CONNECT_LENGTH; m++)
                            {
                                if (isOnGrid((row * m) + lastValueRow,(col * m) + lastValueCol))
                                {
                                    if (checkValue((row * m) + lastValueRow,(col * m) + lastValueCol) == playerNum)
                                    {
                                        totalConnect++;
                                    }
                                }
                            }
                            for (int m = -1; m > -CONNECT_LENGTH; m--)
                            {
                                if (isOnGrid((row * m) + lastValueRow,(col * m) + lastValueCol))
                                {
                                    if (checkValue((row * m) + lastValueRow,(col * m) + lastValueCol) == playerNum)
                                    {
                                        totalConnect++;
                                    }
                                }
                            }
                            if (totalConnect >= CONNECT_LENGTH)
                            {
                                return true;
                            }
                            totalConnect = 0;
                        }
                    }
                }
            }
        }
        //000
        //020
        //112
        return false;
    }
    private boolean isOnGrid(int row, int col)
    {
        if (row >= 0 && row < GRID_SIZE
         && col >= 0 && col < GRID_SIZE)
            return true;
        return false;
    }


}
