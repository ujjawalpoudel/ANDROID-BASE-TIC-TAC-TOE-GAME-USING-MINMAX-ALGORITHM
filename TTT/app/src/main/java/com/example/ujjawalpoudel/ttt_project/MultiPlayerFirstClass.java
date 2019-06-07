package com.example.ujjawalpoudel.ttt_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ujjawal Poudel on 11/27/2017.
 */

public class MultiPlayerFirstClass extends Activity
{
    public final static String MESSAGE_KEY_FIRST_MULTI_CLASS1="MultiPlayerFirstClassMessage1";
    public final static String MESSAGE_KEY_FIRST_MULTI_CLASS2="MultiPlayerFirstClassMessage2";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_player_first_layout);
    }
    public void StartMultiClick(View view)
    {
        String StartButtonChecked=((Button)view).getText().toString();
        if (StartButtonChecked.equals("START"))
        {
            EditText player1=(EditText)findViewById(R.id.Player1Multi);
            EditText player2=(EditText)findViewById(R.id.Player2Multi);
            String message1=player1.getText().toString();
            String message2=player2.getText().toString();
            if (message1.matches("")||message2.matches(""))
            {
                Toast.makeText(getApplicationContext(),"Enter Name !!!",Toast.LENGTH_SHORT).show();
            }
            else
                {
            Intent intent = new Intent(this,MultiPlayerSecondClass.class);
            intent.putExtra(MESSAGE_KEY_FIRST_MULTI_CLASS1,message1);//player 1
            intent.putExtra(MESSAGE_KEY_FIRST_MULTI_CLASS2,message2);//player 2
            startActivity(intent);
                }

        }
    }
}
