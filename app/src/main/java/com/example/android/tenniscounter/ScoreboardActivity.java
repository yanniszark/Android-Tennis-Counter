package com.example.android.tenniscounter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ScoreboardActivity extends AppCompatActivity {

    public static final String ARG_PLAYER_1_NAME = "PLAYER_1_NAME";
    public static final String ARG_PLAYER_2_NAME = "PLAYER_2_NAME";
    public static final String ARG_SERVE_START = "SERVE_START";

    private static final int ADVANTAGE = 41;

    /* Variables to track in-game, game and set scores */
    int scorePlayer1 = 0, scorePlayer2 = 0;
    int gamesPlayer1 = 0, gamesPlayer2 = 0;
    int setsPlayer1 = 0, setsPlayer2 = 0;

    /* Variable to track serving player */
    int currServe;

    /* Variable to keep track of the name of the players */
    String player1Name;
    String player2Name;

    /* Initializing some TextViews as globals to limit use of findViewbyId */
    TextView scoreView1, scoreView2;
    TextView gameView1, gameView2;
    TextView setView1, setView2;
    TextView serveView;
    RelativeLayout mainActivityRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* If there are no arguments, go to main menu */
        Bundle args = getIntent().getExtras();
        if (args == null)
            startActivity(new Intent(ScoreboardActivity.this, MainActivity.class));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        scoreView1 = (TextView) findViewById(R.id.player_1_score);
        scoreView2 = (TextView) findViewById(R.id.player_2_score);

        gameView1 = (TextView) findViewById(R.id.game_count_1);
        gameView2 = (TextView) findViewById(R.id.game_count_2);

        setView1 = (TextView) findViewById(R.id.set_count_1);
        setView2 = (TextView) findViewById(R.id.set_count_2);

        serveView = (TextView) findViewById(R.id.serving_player);

        mainActivityRelativeLayout = (RelativeLayout) findViewById(R.id.activity_main);

        /* Setup bundle args */
        TextView player1NameView = (TextView) findViewById(R.id.player_1_name);
        player1Name = args.getString(ARG_PLAYER_1_NAME);
        player1NameView.setText(player1Name);
        TextView player2NameView = (TextView) findViewById(R.id.player_2_name);
        player2Name = args.getString(ARG_PLAYER_2_NAME);
        player2NameView.setText(player2Name);
        currServe = args.getInt(ARG_SERVE_START);

        /* Display current serving player */
        displayServe();
    }


    /**
     * Displays score of player whose tag is given by playerNumber
     */
    protected void displayScore(int playerNumber) {
        if (playerNumber == 1) {
            if (scorePlayer1 == ADVANTAGE)
                scoreView1.setText(getString(R.string.advantage).toUpperCase());
            else
                scoreView1.setText(String.valueOf(scorePlayer1));
        } else {
            if (scorePlayer2 == ADVANTAGE)
                scoreView2.setText(getString(R.string.advantage).toUpperCase());
            else
                scoreView2.setText(String.valueOf(scorePlayer2));
        }
    }

    /**
     * Displays the game score of the player given in the playerNumber parameter.
     *
     * @param playerNumber
     */
    protected void displayGames(int playerNumber) {
        if (playerNumber == 1) {
            gameView1.setText(String.valueOf(gamesPlayer1));
        } else {
            gameView2.setText(String.valueOf(gamesPlayer2));
        }
    }

    /**
     * Displays the set score of the player given in the playerNumber parameter.
     *
     * @param playerNumber
     */
    protected void displaySets(int playerNumber) {
        if (playerNumber == 1) {
            setView1.setText(String.valueOf(setsPlayer1));
        } else {
            setView2.setText(String.valueOf(setsPlayer2));
        }
    }

    /**
     * Displays serving player
     */
    protected void displayServe() {
        switch (currServe) {
            case 1:
                serveView.setText(player1Name);
                break;
            case 2:
                serveView.setText(player2Name);
                break;
        }
    }

    /**
     * Returns the number of the non-serving player
     */
    protected int reverseServe() {
        if (currServe == 1)
            return (2);
        else
            return (1);
    }


    /**
     * Method to display score = 15 to the corresponding player, who is found by the
     * view tag.
     *
     * @param v
     */
    public void fifteenScore(View v) {
        if (v.getTag() == null)
            return;
        int playerNumber = Integer.parseInt((String) v.getTag());
        if (playerNumber == 1) {
            if (scorePlayer1 == 15)
                return;
            scorePlayer1 = 15;
            displayScore(1);

        } else {
            if (scorePlayer2 == 15)
                return;
            scorePlayer2 = 15;
            displayScore(2);
        }
        displayServe();
    }

    /**
     * Method to display score = 30 to the corresponding player, who is found by the
     * view tag.
     *
     * @param v
     */
    public void thirtyScore(View v) {
        if (v.getTag() == null)
            return;
        int playerNumber = Integer.parseInt((String) v.getTag());
        if (playerNumber == 1) {
            if (scorePlayer1 == 30)
                return;
            scorePlayer1 = 30;
            displayScore(1);

        } else {
            if (scorePlayer2 == 30)
                return;
            scorePlayer2 = 30;
            displayScore(2);
        }
        displayServe();
    }

    /**
     * Method to display score = 40 to the corresponding player, who is found by the
     * view tag.
     *
     * @param v
     */
    public void fortyScore(View v) {
        if (v.getTag() == null)
            return;
        int playerNumber = Integer.parseInt((String) v.getTag());
        if (playerNumber == 1) {
            if (scorePlayer1 == 40)
                return;
            scorePlayer1 = 40;
            displayScore(1);

        } else {
            if (scorePlayer2 == 40)
                return;
            scorePlayer2 = 40;
            displayScore(2);
        }
        displayServe();
    }

    /**
     * Method to display score = ADV to the corresponding player, who is found by the
     * view tag.
     *
     * @param v
     */
    public void advantageScore(View v) {
        if (v.getTag() == null)
            return;
        int playerNumber = Integer.parseInt((String) v.getTag());
        if (playerNumber == 1) {
            if (scorePlayer1 == ADVANTAGE || scorePlayer2 != 40)
                return;
            scorePlayer1 = ADVANTAGE;
        } else {
            if (scorePlayer2 == ADVANTAGE || scorePlayer1 != 40)
                return;
            scorePlayer2 = ADVANTAGE;
        }
        displayScore(playerNumber);
    }

    /**
     * Method to increase game score of the corresponding player (found by view tag) by 1.
     * Checks if game score >= 6 and score difference >= 2 and updates set score if necessary.
     *
     * @param v
     */
    public void gameScore(View v) {
        if (v.getTag() == null)
            return;
        int playerNumber = Integer.parseInt((String) v.getTag());
        resetScore();
        currServe = reverseServe();
        displayServe();
        if (playerNumber == 1) {
            gamesPlayer1 += 1;
            if (gamesPlayer1 >= 6 && gamesPlayer1 - gamesPlayer2 >= 2) {
                resetGames();
                setScore(v);
            } else
                displayGames(playerNumber);
        } else {
            gamesPlayer2 += 1;
            if (gamesPlayer2 >= 6 && gamesPlayer2 - gamesPlayer1 >= 2) {
                resetGames();
                setScore(v);
            } else
                displayGames(playerNumber);
        }
    }

    /**
     * Method to increase set score of the corresponding player (found by view tag) by 1.
     * It also checks if the game has ended and declares the winner if necessary.
     *
     * @param v
     */
    public void setScore(View v) {
        if (v.getTag() == null)
            return;
        int playerNumber = Integer.parseInt((String) v.getTag());
        resetGames();
        if (playerNumber == 1) {
            setsPlayer1 += 1;
            if ((setsPlayer1 > setsPlayer2) && (setsPlayer1 >= 3)) {
                declareWinner(1);
            } else
                displaySets(playerNumber);
        } else {
            setsPlayer2 += 1;
            if ((setsPlayer2 > setsPlayer1) && (setsPlayer2 >= 3)) {
                declareWinner(2);
            } else
                displaySets(playerNumber);
        }
    }

    public void reset(View v) {
        resetSets();
    }

    /**
     * Resets in-game score count.
     */
    protected void resetScore() {
        scorePlayer1 = scorePlayer2 = 0;
        displayScore(1);
        displayScore(2);
    }

    /**
     * Resets game count.
     */
    protected void resetGames() {
        gamesPlayer1 = gamesPlayer2 = 0;
        displayGames(1);
        displayGames(2);
        resetScore();
    }

    /**
     * Resets set count.
     */
    protected void resetSets() {
        setsPlayer1 = setsPlayer2 = 0;
        displaySets(1);
        displaySets(2);
        resetGames();
    }

    /**
     * Method to display the popup message in order to declare winner.
     *
     * @param playerNumber
     */
    protected void declareWinner(int playerNumber) {
        /* Inflate a new popup window */
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View winnerView = inflater.inflate(R.layout.winner_popup, null);
        final PopupWindow mPopupWindow = new PopupWindow(
                winnerView,
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        if (Build.VERSION.SDK_INT >= 21) {
            mPopupWindow.setElevation(6.0f);
        }
        /* Cast a transparent black layer on the foreground*/
        if (Build.VERSION.SDK_INT >= 23)
            mainActivityRelativeLayout.setForeground(new ColorDrawable(ContextCompat.getColor(getApplicationContext(), R.color.transparentBlack)));
        Button resetButton = (Button) winnerView.findViewById(R.id.dismiss);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetSets();
                mPopupWindow.dismiss();
                if (Build.VERSION.SDK_INT >= 23)
                    mainActivityRelativeLayout.setForeground(null);
            }
        });
        TextView winnerText = (TextView) winnerView.findViewById(R.id.winner_textview);
        if (playerNumber == 1)
            winnerText.setText(player1Name + " " + getString(R.string.player_wins));
        else
            winnerText.setText(player2Name + " " + getString(R.string.player_wins));
        mPopupWindow.showAtLocation(mainActivityRelativeLayout, Gravity.CENTER, 0, 0);
    }

    public void returnToMainMenu(View view) {
        startActivity(new Intent(ScoreboardActivity.this, MainActivity.class));
    }
}




