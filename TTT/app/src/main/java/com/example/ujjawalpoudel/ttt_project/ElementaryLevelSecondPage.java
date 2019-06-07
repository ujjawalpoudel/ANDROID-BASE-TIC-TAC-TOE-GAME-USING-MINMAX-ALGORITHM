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

public class ElementaryLevelSecondPage extends Activity
{
    public final static String MESSAGE_KEY_FIRST_CLASS_ELEMENTRY_LEVEL ="SinglePlayerFirstClassMessageElementryLevel";
    public final static String MESSAGE_KEY_FIRST_CLASS_ELEMENTRY_LEVEL_ONE ="SinglePlayerFirstClassMessageElementryLevelOne";
    public final static String MESSAGE_KEY_FIRST_CLASS_ELEMENTRY_LEVEL_TWO ="SinglePlayerFirstClassMessageElementryLevelTwo";
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
        String message1=intent.getStringExtra(MESSAGE_KEY_FIRST_CLASS_ELEMENTRY_LEVEL);
        RadioButton checked_human_id = ((RadioButton) findViewById(R.id.radio_O));
        RadioButton checked_computer_id = ((RadioButton) findViewById(R.id.radio_X));
       intent = new Intent(this, ElementartLevelMainPage.class);
        switch(view.getId())
        {
            case R.id.radio_O:
                if (checked)
                    checked_computer_id.setChecked(false);
                message="O";
                break;
            case R.id.radio_X:
                if (checked)
                    checked_human_id.setChecked(false);
                message="X";
                break;
        }
        intent.putExtra(MESSAGE_KEY_FIRST_CLASS_ELEMENTRY_LEVEL_ONE,message1);//previous message
        intent.putExtra(MESSAGE_KEY_FIRST_CLASS_ELEMENTRY_LEVEL_TWO,message);//new message
        startActivity(intent);
    }
}
