package com.example.ujjawalpoudel.ttt_project;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Created by Ujjawal Poudel on 11/27/2017.
 */

public class MultiPlayerThirdClass extends Activity
{
    public final static String MESSAGE_KEY_FIRST_MULTI_CLASS3="MultiPlayerFirstClassMessage3";
    public final static String MESSAGE_KEY_FIRST_MULTI_CLASS4="MultiPlayerFirstClassMessage4";
    public final static String MESSAGE_KEY_FIRST_MULTI_CLASS5="MultiPlayerFirstClassMessage5";
    int activePlayer = 0;
    String first="";
    int winnerStatus=0;
    String second="";
    String winnerMsg="";
    public  int activeplayer1=0;//player1
    public  int activeplayer2=0;//player2
    boolean gameIsActive = true;// 2 unplayed;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8}, //hor
            {0,3,6}, {1,4,7}, {2,5,8}, // ver
            {0,4,8}, {2,4,6}
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_player_third_layout);
        Intent intent=getIntent();
        Button resetCheckedButton=(Button)findViewById(R.id.Reset);
        resetCheckedButton.setVisibility(View.INVISIBLE);
       // String winnerMsg="";
        String message1=intent.getStringExtra(MESSAGE_KEY_FIRST_MULTI_CLASS3);//player 1
        String message2=intent.getStringExtra(MESSAGE_KEY_FIRST_MULTI_CLASS4);//player 2
        String message3=intent.getStringExtra(MESSAGE_KEY_FIRST_MULTI_CLASS5);//who select first move
        if (message3.equals("X"))
        {
            String first = message2;//If second player is selected then
            TextView CurrentState = (TextView)  findViewById(R.id.thing);
            CurrentState.setText(first+",your first move");
            activePlayer=1;
        }
        else if (message3.equals("O"))
        {
            String first =message1;//If first player is selected then
            TextView CurrentState = (TextView)  findViewById(R.id.thing);
            CurrentState.setText(first+",your first move");
            activePlayer=0;
        }
        TextView InfoDisplay=(TextView)findViewById(R.id.resultDisplay);
        InfoDisplay.setText(message1+"   "+activeplayer2+"     "+activeplayer1+"   "+message2);
    }
    public  void cellClick(View view)
    {
        TextView textView=(TextView)view;
        Intent intent=getIntent();
        String winnerMsg="";
        String message1=intent.getStringExtra(MESSAGE_KEY_FIRST_MULTI_CLASS3);//player 1
        String message2=intent.getStringExtra(MESSAGE_KEY_FIRST_MULTI_CLASS4);//player 2
        String message3=intent.getStringExtra(MESSAGE_KEY_FIRST_MULTI_CLASS5);//who select first move
        int tappedCounter = Integer.parseInt(textView.getTag().toString());
        if (gameState[tappedCounter] == 2  && gameIsActive)
        {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                textView.setText("O");
                activePlayer = 1;
            } else {
                textView.setText("X");
                activePlayer = 0;
            }
            //checking for the winner
            for (int[] winningPosition : winningPositions)
            {
                if (gameState[winningPosition[0]] ==gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] ==gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] !=2 )
                {
                    //Someone has won
                    gameIsActive = false;
                    if (activePlayer==1)
                    {
                        winnerMsg=message1+" wins !";
                        activeplayer2++;
                        winnerStatus=1;
                        Button resetCheckedButton=(Button)findViewById(R.id.Reset);
                        resetCheckedButton.setVisibility(View.VISIBLE);
                    }
                    else if (activePlayer==0)
                    {
                        winnerMsg = message2 + " wins !";
                        winnerStatus=2;
                        Button resetCheckedButton=(Button)findViewById(R.id.Reset);
                        resetCheckedButton.setVisibility(View.VISIBLE);
                        activeplayer1++;
                    }

                } else {
                    boolean gameIsOver = true;
                    for (int counterState : gameState )
                    {
                        if (counterState == 2)
                        {
                            gameIsOver = false;
                        }
                    }
                    if (gameIsOver)
                    {
                        winnerMsg="It's a draw !!!";
                        Button resetCheckedButton=(Button)findViewById(R.id.Reset);
                        resetCheckedButton.setVisibility(View.VISIBLE);
                    }
                }
                TextView textDisplay=(TextView)findViewById(R.id.thing);
                textDisplay.setText(winnerMsg);
                TextView InfoDisplay=(TextView)findViewById(R.id.resultDisplay);
                InfoDisplay.setText(message1+"   "+activeplayer2+"     "+activeplayer1+"   "+message2);
            }
        }
    }
    public void createBlankSpace()
    {
        gameIsActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++)
        {
            gameState[i] = 2;
        }
    }
    public void resetClick(View view)
    {
        TextView textView1=(TextView)findViewById(R.id.cell11);
        textView1.setText("");
        TextView textView2=(TextView)findViewById(R.id.cell12);
        textView2.setText("");
        TextView textView3=(TextView)findViewById(R.id.cell13);
        textView3.setText("");
        TextView textView4=(TextView)findViewById(R.id.cell21);
        textView4.setText("");
        TextView textView5=(TextView)findViewById(R.id.cell22);
        textView5.setText("");
        TextView textView6=(TextView)findViewById(R.id.cell23);
        textView6.setText("");
        TextView textView7=(TextView)findViewById(R.id.cell31);
        textView7.setText("");
        TextView textView8=(TextView)findViewById(R.id.cell32);
        textView8.setText("");
        TextView textView9=(TextView)findViewById(R.id.cell33);
        textView9.setText("");
        TextView textView=(TextView)findViewById(R.id.thing);
        Intent intent=getIntent();
        String message1=intent.getStringExtra(MESSAGE_KEY_FIRST_MULTI_CLASS3);//player 1
        String message2=intent.getStringExtra(MESSAGE_KEY_FIRST_MULTI_CLASS4);//player 2
        if (winnerStatus==1)
            textView.setText(message2+",your first move");
        else if (winnerStatus==2)
            textView.setText(message1+",your first move");
        gameIsActive = true;
        for (int i = 0; i < gameState.length; i++)
        {
            gameState[i] = 2;
        }
        Button resetCheckedButton=(Button)findViewById(R.id.Reset);
        resetCheckedButton.setVisibility(View.INVISIBLE);
       /* Intent intent = getIntent();
        finish();
        startActivity(intent);*/
    }
}