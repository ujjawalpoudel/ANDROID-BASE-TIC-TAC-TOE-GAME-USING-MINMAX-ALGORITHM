package com.example.ujjawalpoudel.ttt_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by Ujjawal Poudel on 11/27/2017.
 */

public class SinglePlayerSecondClass extends Activity
{
    public final static String MESSAGE_KEY_FIRST_CLASS ="SinglePlayerFirstClassMessage";
    public final static String MESSAGE_KEY_SECOND_CLASS1 ="SinglePlayerSecondClassMessage1";
    public final static String MESSAGE_KEY_SECOND_CLASS2 ="SinglePlayerSecondClassMessage2";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_player_second_class);
    }
    public void onRadioButtonClicked(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
        Intent intent=getIntent();
        String message="";
        String message1=intent.getStringExtra(MESSAGE_KEY_FIRST_CLASS);
        RadioButton checked_human_id = ((RadioButton) findViewById(R.id.radio_O));
        RadioButton checked_computer_id = ((RadioButton) findViewById(R.id.radio_X));
        intent = new Intent(this, SinglePlayerThirdClass.class);
        switch(view.getId())
        {
            case R.id.radio_O:
                if (checked)
                    checked_computer_id.setChecked(false);
                    message="O";
                    intent.putExtra(MESSAGE_KEY_SECOND_CLASS2,message);//new message
                    break;
            case R.id.radio_X:
                if (checked)
                    checked_human_id.setChecked(false);
                    message="X";
                    intent.putExtra(MESSAGE_KEY_SECOND_CLASS2,message);//new message
                     break;
        }
        intent.putExtra(MESSAGE_KEY_SECOND_CLASS1,message1);//previous message
        startActivity(intent);
    }
}
