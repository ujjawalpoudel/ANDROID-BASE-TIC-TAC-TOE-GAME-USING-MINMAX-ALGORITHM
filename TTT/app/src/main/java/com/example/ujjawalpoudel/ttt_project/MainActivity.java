package com.example.ujjawalpoudel.ttt_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }
    public void optionSelect(View view)
    {
            String button_text;
            button_text=((Button)view).getText().toString();
            if (button_text.equals("SINGLE PLAYER"))
            {
                Intent intent = new Intent(this,AIlevelFind.class);
                startActivity(intent);
            }
            else if (button_text.equals("MULTIPLAYER"))
            {
                Intent intent = new Intent(this,MultiPlayerFirstClass.class);
                startActivity(intent);
            }
            else if (button_text.equals("EXIT"))
            {
                System.exit(1);
            }

        }
    }

