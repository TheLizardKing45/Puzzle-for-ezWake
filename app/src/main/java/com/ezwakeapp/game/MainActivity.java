package com.ezwakeapp.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import static com.ezwakeapp.game.R.id.button1;

public class MainActivity extends AppCompatActivity implements OnClickListener  {

    private Button zeroBtn, oneBtn;
    Random random = new Random();
    boolean win = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zeroBtn = (Button)findViewById(R.id.button1);
        oneBtn = (Button)findViewById(R.id.button2);

        zeroBtn.setOnClickListener(this);
        oneBtn.setOnClickListener(this);

        createOperations();
    }

    private void createOperations() {
        //generate operator
        int op1 = random.nextInt(2);
        char op = 0;
        switch(op1) {
            case 0:
                op = '+';
                break;
            case 1:
                op = '-';
                break;
        }

        //generate 2 numbers for the equation
        int no1 = random.nextInt(50) + 50;
        int no2 = 0;
        if (no1 > 75) {
            no2 = random.nextInt(50) + 1;
        } else{
            no2 = random.nextInt(100) + 1;
        }

        //solve equation to display answer
        int answer = 0;
        switch(op1) {
            case 0:
                answer = no1 + no2;
                break;
            case 1:
                answer = no1 - no2;
        }


        //choose between displaying false and true answer
        int ansChoose = random.nextInt(2);
        switch(ansChoose) {
            case 0:
                //display True equation
                String equation = String.valueOf(no1) + " " + op + " " + String.valueOf(no2) + " " + "=" + String.valueOf(answer);
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(equation);
                rightBox = 0;
                break;
            case 1:
                //display False equation
                int rand = random.nextInt(9);
                int notAnswer = answer + rand;
                equation = String.valueOf(no1) + " " + op + " " + String.valueOf(no2) + " " + "=" + String.valueOf(notAnswer);
                textView = (TextView) findViewById(R.id.textView);
                textView.setText(equation);
                rightBox = 1;
                break;
        }
    }

    @Override
    public void onClick(View view) {
        boolean correct = false;

        switch(view.getId()) {
            case button1:
                if (rightBox == 0) {
                    correct = true;
                }
                break;
            case R.id.button2:
                if (rightBox == 1) {
                    correct = true;
                }
                break;
        }
        if (correct) {
            updateScore();
            checkForWin();
            if (win == false) {
                createOperations();
            }
        } else {

            createOperations();
        }
    }

    private void updateScore() {
        points++;
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText(String.valueOf(points));
    }

    int points = 0;
    int rightBox = 0;

    public void checkForWin() {
        if (points >= 5) {
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText("You Win!");
            oneBtn.setEnabled(false);
            zeroBtn.setEnabled(false);
            win = true;
        }

    }
}



