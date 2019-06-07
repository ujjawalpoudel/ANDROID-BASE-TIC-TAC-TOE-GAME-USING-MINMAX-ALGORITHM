package com.example.ujjawalpoudel.ttt_project;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ElementartLevelMainPage extends Activity {
    //Declare global variables for the game space
    private GameBoard board = null;
    private int moveCount = 0, xloc = 0, yloc = 0;
    private String mark = "O", aiMark = "X";
    private boolean isOver = false;
    private AI ai = null;
    public final static String MESSAGE_KEY_FIRST_CLASS_ELEMENTRY_LEVEL_ONE ="SinglePlayerFirstClassMessageElementryLevelOne";
    //previous message
    public final static String MESSAGE_KEY_FIRST_CLASS_ELEMENTRY_LEVEL_TWO ="SinglePlayerFirstClassMessageElementryLevelTwo";
    //new message of X or O
    public int AIwinCOunt = 0;
    public int USERwinCount = 0;

    //Create the initial game space
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Look at the right XML layout and remove the action bar
        setContentView(R.layout.elementart_level_main_page);
        //Set up a new board and AI and assign the initial variables
        board = new GameBoard();
        ai = new AI(aiMark);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MESSAGE_KEY_FIRST_CLASS_ELEMENTRY_LEVEL_TWO);//Check "X" or "O"
        String message1 = intent.getStringExtra(MESSAGE_KEY_FIRST_CLASS_ELEMENTRY_LEVEL_ONE);//previous message
        TextView textView1 = (TextView) findViewById(R.id.resultDisplay);
        textView1.setText("Computer  " + AIwinCOunt + "        " + USERwinCount + "  " + message1);
        if (message.equals("X"))
            getAIMoveFirst(board);
        else {
            TextView textViewDisplayTurns = (TextView) findViewById(R.id.thing);
            textViewDisplayTurns.setText(message1 + ",your first turns ");
        }
    }

    //Action when reset is clicked which clears the screen and the virtual game board
    public void resetClick(View v) {
        clear();
        Intent intent = getIntent();
        String message = intent.getStringExtra(MESSAGE_KEY_FIRST_CLASS_ELEMENTRY_LEVEL_TWO);
        TextView textView = (TextView) findViewById(R.id.thing);
        textView.setText("");
        if (message.equals("X"))
            getAIMoveFirst(board);
    }

    //Action for when a cell is clicked. Determines which cell has been clicked and passed that
    //	information on the the virtual game board.
    public void cellClick(View v) {
        //Get the id of the clicked object and assign it to a Textview variable
        TextView cell = (TextView) findViewById(v.getId());
        TextView textViewDisplayTurns = (TextView) findViewById(R.id.thing);
        textViewDisplayTurns.setText("");
        //Check the content and make sure the cell is empty and that the game isn't over
        String content = (String) cell.getText();
        if (content == "" && !isOver) {
            //Find the X Y location values of the particular cell that was clicked
            switch (cell.getId()) {
                case R.id.cell11:
                    xloc = 0;
                    yloc = 0;
                    break;
                case R.id.cell12:
                    xloc = 0;
                    yloc = 1;
                    break;
                case R.id.cell13:
                    xloc = 0;
                    yloc = 2;
                    break;
                case R.id.cell21:
                    xloc = 1;
                    yloc = 0;
                    break;
                case R.id.cell22:
                    xloc = 1;
                    yloc = 1;
                    break;
                case R.id.cell23:
                    xloc = 1;
                    yloc = 2;
                    break;
                case R.id.cell31:
                    xloc = 2;
                    yloc = 0;
                    break;
                case R.id.cell32:
                    xloc = 2;
                    yloc = 1;
                    break;
                case R.id.cell33:
                    xloc = 2;
                    yloc = 2;
                    break;
            }
            //Place the player's mark on the specific X Y location on both the virtual and displayed board
            board.placeMark(xloc, yloc, mark);
            cell.setText(mark);
            //Increment move Count because a move was just made
            moveCount++;
            //Check to see if the game is over
            isOver = checkEnd(mark);
            //if the game game is over get the AI's move
            if (!isOver)
                getAIMoveFirst(board);
        }
    }

    //Checks to see if the game has ended provided with the last player to make a move
    private boolean checkEnd(String player) {
        //Checks the virtual board for a winner if there's a winner announce it with the provided player
        if (board.isWinner()) {
            announce(true, player);
            return true;
        }
        //Check to see if we've reached our move total meaning it's a draw
        else if (moveCount >= 9) {
            announce(false, player);
            return true;
        }
        //If neither win or draw then the game is still on
        return false;
    }

    //Announce the winner, given a boolean for whether it was a win or a draw
    // and given the last player to make a mark
    private void announce(boolean endState, String player) {
        //Check for if it's a win or a draw. if it's a win amend player with wins!
        //	if it's a lose replace player with it's a draw! I did this just because why
        //  declare another String when I can just reuse the one I have?
        String PlayerName = player;
        Intent intent = getIntent();
        String message = intent.getStringExtra(MESSAGE_KEY_FIRST_CLASS_ELEMENTRY_LEVEL_ONE);
        String textMessageDisplay = "";
        TextView textView = (TextView) findViewById(R.id.thing);
        if (endState == true && PlayerName == "X") {
            textMessageDisplay = "Computer wins!";
            AIwinCOunt++;
        } else if (endState == true && PlayerName == "O") {
            textMessageDisplay = message + " wins!";
            USERwinCount++;
        } else
            textMessageDisplay = "It's a draw!";
        textView.setText(textMessageDisplay);
        TextView textView1 = (TextView) findViewById(R.id.resultDisplay);
        textView1.setText("Computer  " + AIwinCOunt + "        " + USERwinCount + "  " + message);
    }

    //Clears the game Board
    private void clear() {
        //Get the id list of all the Textview cells
        int[] idList = {R.id.cell11, R.id.cell12, R.id.cell13, R.id.cell21,
                R.id.cell22, R.id.cell23, R.id.cell31, R.id.cell32, R.id.cell33};
        TextView cell;
        //For each cell clear the text with an empty string
        for (int item : idList) {
            cell = (TextView) findViewById(item);
            cell.setText("");
        }
        //Reset the game state and clear the virtual board
        isOver = false;
        moveCount = 0;
        board.clear();
    }

    private void getAIMoveFirst(GameBoard board) {
        //Send the board to the AI for it to determine and return the move in an array {x,y}
        Random r = new Random();
        int a = r.nextInt(3);
        int b = r.nextInt(3);
        int[] move = {a, b};
        TextView cell = null;
        switch (move[0]) {
            case 0:
                switch (move[1]) {
                    case 0:
                        cell = (TextView) findViewById(R.id.cell11);
                        break;
                    case 1:
                        cell = (TextView) findViewById(R.id.cell12);
                        break;
                    case 2:
                        cell = (TextView) findViewById(R.id.cell13);
                        break;
                }
                break;
            case 1:
                switch (move[1]) {
                    case 0:
                        cell = (TextView) findViewById(R.id.cell21);
                        break;
                    case 1:
                        cell = (TextView) findViewById(R.id.cell22);
                        break;
                    case 2:
                        cell = (TextView) findViewById(R.id.cell23);
                        break;
                }
                break;
            case 2:
                switch (move[1]) {
                    case 0:
                        cell = (TextView) findViewById(R.id.cell31);
                        break;
                    case 1:
                        cell = (TextView) findViewById(R.id.cell32);
                        break;
                    case 2:
                        cell = (TextView) findViewById(R.id.cell33);
                        break;
                }
                break;
        }
        //Make sure there's nothing already in the cell
        //	then place the mark with the ai's Mark, increment move count
        //	and check to see if the game's over
        while (!(cell != null && cell.getText() == ""))
        {
            // Random r = new Random();
            a = r.nextInt(3);
            b = r.nextInt(3);
            move[0]=a;
            move[1]=b;
            // TextView cell = null;
            switch (move[0]) {
                case 0:
                    switch (move[1]) {
                        case 0:
                            cell = (TextView) findViewById(R.id.cell11);
                            break;
                        case 1:
                            cell = (TextView) findViewById(R.id.cell12);
                            break;
                        case 2:
                            cell = (TextView) findViewById(R.id.cell13);
                            break;
                    }
                    break;
                case 1:
                    switch (move[1]) {
                        case 0:
                            cell = (TextView) findViewById(R.id.cell21);
                            break;
                        case 1:
                            cell = (TextView) findViewById(R.id.cell22);
                            break;
                        case 2:
                            cell = (TextView) findViewById(R.id.cell23);
                            break;
                    }
                    break;
                case 2:
                    switch (move[1]) {
                        case 0:
                            cell = (TextView) findViewById(R.id.cell31);
                            break;
                        case 1:
                            cell = (TextView) findViewById(R.id.cell32);
                            break;
                        case 2:
                            cell = (TextView) findViewById(R.id.cell33);
                            break;
                    }
                    break;
            }

        }
        if (cell != null && cell.getText() == "") {
            board.placeMark(move[0], move[1], aiMark);
            cell.setText(aiMark);
            moveCount++;
            isOver = checkEnd(aiMark);
        }
    }
}