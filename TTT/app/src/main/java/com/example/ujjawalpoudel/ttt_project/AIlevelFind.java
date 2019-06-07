package com.example.ujjawalpoudel.ttt_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by Ujjawal Poudel on 11/28/2017.
 */

public class AIlevelFind extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ai_level_find);
    }
    public void DifficultLevelClick(View view)
    {
        String button_text;
        button_text=((Button)view).getText().toString();
        if (button_text.equals("DUMMY AI"))
        {
            Intent intent = new Intent(this,ElementaryLevelFirstPage.class);
            startActivity(intent);
        }
        else if (button_text.equals("SMART AI"))
        {
            Intent intent = new Intent(this,SinglePlayerFirstClass.class);
            startActivity(intent);
        }
    }
}
