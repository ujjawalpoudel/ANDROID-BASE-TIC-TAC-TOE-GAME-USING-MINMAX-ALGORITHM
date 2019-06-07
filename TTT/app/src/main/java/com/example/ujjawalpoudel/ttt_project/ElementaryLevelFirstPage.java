package com.example.ujjawalpoudel.ttt_project;

import android.app.Activity;
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

public class ElementaryLevelFirstPage extends Activity
{
    public final static String MESSAGE_KEY_FIRST_CLASS_ELEMENTRY_LEVEL ="SinglePlayerFirstClassMessageElementryLevel";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_player_first_class);
    }
    public void StartFirstClass(View view)
    {
        String StartText;
        StartText=((Button)view).getText().toString();
        if (StartText.equals("START"))
        {
            EditText EnterNameOfPlayer;
            EnterNameOfPlayer=(EditText)findViewById(R.id.SinglePlayerName);
            String message=EnterNameOfPlayer.getText().toString();
            if (message.matches(""))
                Toast.makeText(getApplicationContext(),"Enter Name !!!",Toast.LENGTH_SHORT).show();
            else
            {
                Intent intent = new Intent(this, ElementaryLevelSecondPage.class);
                intent.putExtra(MESSAGE_KEY_FIRST_CLASS_ELEMENTRY_LEVEL, message);
                startActivity(intent);
            }
        }
    }
}
