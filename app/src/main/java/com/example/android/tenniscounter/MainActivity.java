package com.example.android.tenniscounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startNewGame(View view) {
        /* Get player names */
        EditText player1NameEditText = (EditText) findViewById(R.id.player_1_name);
        EditText player2NameEditText = (EditText) findViewById(R.id.player_2_name);

        String player1Name = player1NameEditText.getText().toString();
        if (player1Name.isEmpty()) {
            Toast.makeText(this, getString(R.string.player1_enter_name_toast), Toast.LENGTH_SHORT).show();
            return;
        }
        String player2Name = player2NameEditText.getText().toString();
        if (player2Name.isEmpty()) {
            Toast.makeText(this, getString(R.string.player2_enter_name_toast), Toast.LENGTH_SHORT).show();
            return;
        }

        /* Get serving player */
        int servingPlayer;
        RadioGroup serveRadioGroup = (RadioGroup) findViewById(R.id.serve_radiogroup);
        switch (serveRadioGroup.getCheckedRadioButtonId()) {
            case R.id.player_1_serve:
                servingPlayer = 1;
                break;
            case R.id.player_2_serve:
                servingPlayer = 2;
                break;
            default:
                Toast.makeText(this, getString(R.string.select_serving_player_toast), Toast.LENGTH_SHORT).show();
                return;
        }

        Bundle args = new Bundle();
        args.putString(ScoreboardActivity.ARG_PLAYER_1_NAME, player1Name);
        args.putString(ScoreboardActivity.ARG_PLAYER_2_NAME, player2Name);
        args.putInt(ScoreboardActivity.ARG_SERVE_START, servingPlayer);
        Intent i = new Intent(MainActivity.this, ScoreboardActivity.class);
        i.putExtras(args);
        startActivity(i);
    }
}