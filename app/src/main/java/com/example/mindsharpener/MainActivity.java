package com.example.mindsharpener;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView, textView4, textView5, textView6, textView7, textView8;
    private RadioGroup radioGroup;
    private Button checkButton;
    private int currentLevel;
    private int currentPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        radioGroup = findViewById(R.id.radioGroup);
        checkButton = findViewById(R.id.button);

        checkButton.setOnClickListener(view -> {
            checkAnswer();
            displayQuestion();
        });
    }

    private void checkAnswer() {

        String userAnswerStr = ((TextView) findViewById(R.id.textInputEditText2)).getText().toString();


        int userAnswer = 0;
        try {
            userAnswer = Integer.parseInt(userAnswerStr);
        } catch (NumberFormatException e) {

        }


        int firstNumber = Integer.parseInt(textView4.getText().toString());
        int secondNumber = Integer.parseInt(textView6.getText().toString());
        int operator = Integer.parseInt(textView5.getText().toString());


        int correctAnswer = calculateAnswer(firstNumber, secondNumber, operator);


        if (userAnswer == correctAnswer) {
            currentPoints++;
        } else {
            currentPoints--;
        }


        textView8.setText("POINTS: " + currentPoints);
    }

    private int calculateAnswer(int firstNumber, int secondNumber, int operator) {
        switch (operator) {
            case 0:
                return firstNumber + secondNumber;
            case 1:
                return firstNumber - secondNumber;
            case 2:
                return firstNumber * secondNumber;
            case 3:
                return firstNumber / secondNumber;
            default:
                return 0;
        }
    }

    @SuppressLint("NonConstantResourceId")
    private void displayQuestion() {

        Random random = new Random();


        RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        if (selectedRadioButton != null) {
            String levelStr = selectedRadioButton.getText().toString().replaceAll("[^0-9]", "");
            currentLevel = Integer.parseInt(levelStr);
        } else {

        }


        int firstNumber = random.nextInt((int) Math.pow(10, currentLevel));
        int secondNumber = random.nextInt((int) Math.pow(10, currentLevel));


        int operator = random.nextInt(4);


        textView4.setText(String.valueOf(firstNumber));
        textView6.setText(String.valueOf(secondNumber));
        textView5.setText(String.valueOf(operator));


        StringBuilder questionText = new StringBuilder();
        questionText.append("Calculate: ").append(firstNumber);

        switch (operator) {
            case 0:
                questionText.append(" + ");
                break;
            case 1:
                questionText.append(" - ");
                break;
            case 2:
                questionText.append(" * ");
                break;
            case 3:
                questionText.append(" / ");
                break;
            default:
                // Handle unknown operator
                break;
        }

        questionText.append(secondNumber);


        textView.setText(questionText.toString());
        }
    }



