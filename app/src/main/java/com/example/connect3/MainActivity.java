package com.example.connect3;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 = Yellow, 1 = Red
    int activePlayer=0;
    // 2 = untapped 0 or 1 means tapped
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    // app 8 possible winning positions are recorded 3 horizontals, 3 verticals, 2 diagonals
    int [][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    //variable to prevent game activity after the game is won
    boolean gameActive=true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tapped = Integer.parseInt(counter.getTag().toString());
        if (gameState[tapped] == 2 && gameActive==true) {
            counter.setScaleX(0f);
            counter.setScaleY(0f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().scaleX(1f).scaleY(1f).setDuration(300);
            gameState[tapped] = activePlayer;
        }

        for (int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                    && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 2) {
                //somebody has won
                //game is not active anymore
                gameActive = false;
                String winner;
                if (activePlayer == 0)
                    winner = "Yellow";
                else
                    winner = "Red";
                TextView winnerMessage = (TextView) findViewById(R.id.result);
                winnerMessage.setText(winner + " has won!!");
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.winningMessage);
                linearLayout.setVisibility(View.VISIBLE);
            }
            else{
                boolean draw=true;
                for(int i=0;i<gameState.length;i++) if(gameState[i]==2) draw=false;
                if(draw)
                {
                    TextView winnerMessage = (TextView) findViewById(R.id.result);
                    winnerMessage.setText("Draw!!");
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.winningMessage);
                    linearLayout.setVisibility(View.VISIBLE);
                }

            }
        }
    }

        public void playAgain(View view){
            //making the result invisible
            gameActive=true;
            LinearLayout linearLayout= (LinearLayout) findViewById(R.id.winningMessage);
            linearLayout.setVisibility(View.INVISIBLE);
            //resetting the logic
            int activePlayer=0;
            for(int i=0;i<gameState.length;i++) gameState[i]=2;

            //making the board empty
            GridLayout gridLayout= (GridLayout) findViewById(R.id.board);
            for(int i=0;i<gridLayout.getChildCount();i++)
                ((ImageView) gridLayout.getChildAt(i)). setImageResource(0);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}


