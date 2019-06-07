package com.example.ujjawalpoudel.ttt_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Ujjawal Poudel on 11/27/2017.
 */

public class MultiPlayerSecondClass extends Activity
{
    public final static String MESSAGE_KEY_FIRST_MULTI_CLASS1="MultiPlayerFirstClassMessage1";
    public final static String MESSAGE_KEY_FIRST_MULTI_CLASS2="MultiPlayerFirstClassMessage2";
    public final static String MESSAGE_KEY_FIRST_MULTI_CLASS3="MultiPlayerFirstClassMessage3";
    public final static String MESSAGE_KEY_FIRST_MULTI_CLASS4="MultiPlayerFirstClassMessage4";
    public final static String MESSAGE_KEY_FIRST_MULTI_CLASS5="MultiPlayerFirstClassMessage5";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_player_second_layout);
        Intent intent=getIntent();
        String message1=intent.getStringExtra(MESSAGE_KEY_FIRST_MULTI_CLASS1);//player 1
        String message2=intent.getStringExtra(MESSAGE_KEY_FIRST_MULTI_CLASS2);//player 2
        RadioButton player1text=(RadioButton)findViewById(R.id.radio_O);//left side player 1
        RadioButton player2text=(RadioButton)findViewById(R.id.radio_X);//right side player 2
        player1text.setText(message1);
        player2text.setText(message2);
    }
    public void SelectFirstTurn(View view)
    {
        Intent intent=getIntent();
        RadioButton checked_human_id = ((RadioButton) findViewById(R.id.radio_O));
        RadioButton checked_computer_id = ((RadioButton) findViewById(R.id.radio_X));
        boolean checked = ((RadioButton) view).isChecked();
        String message="";
        String message1=intent.getStringExtra(MESSAGE_KEY_FIRST_MULTI_CLASS1);
        String message2=intent.getStringExtra(MESSAGE_KEY_FIRST_MULTI_CLASS2);
        TextView textView=(TextView)view;
        intent = new Intent(this, MultiPlayerThirdClass.class);
        switch(view.getId())
        {
            case R.id.radio_O:
                message="O";//player 1
                checked_computer_id.setChecked(false);
                intent.putExtra(MESSAGE_KEY_FIRST_MULTI_CLASS5,message);//new message
                break;
            case R.id.radio_X:
                message="X";//player 2
                checked_human_id.setChecked(false);
                intent.putExtra(MESSAGE_KEY_FIRST_MULTI_CLASS5,message);//new message
                break;
        }
        intent.putExtra(MESSAGE_KEY_FIRST_MULTI_CLASS3,message1);
        intent.putExtra(MESSAGE_KEY_FIRST_MULTI_CLASS4,message2);

        startActivity(intent);
    }
}

