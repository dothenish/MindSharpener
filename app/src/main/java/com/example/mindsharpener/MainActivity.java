package com.example.mindsharpener;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int level = 0;
    private int score = 0;
    private int firstNumber = 0;
    private int secondNumber = 0;
    private int operator = 0;
    private int userAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Resource identifier
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        EditText answerText = findViewById(R.id.answerText);
        Button checkButton = findViewById(R.id.checkButton);

        //questions
        RadioButton defaultRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        level = getLevelFromRadioButton(defaultRadioButton);
        generateQuestion();

        //new questions
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            level = getLevelFromRadioButton(findViewById(checkedId));
            generateQuestion();
        });

        //check input field
        checkButton.setOnClickListener(v -> {
            // Check if the textEdit is empty
            if (answerText.getText().toString().isEmpty()) {
                Toast.makeText(
                        getApplicationContext(),
                        "Please answer the question given in the answer field.",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                userAnswer = Integer.parseInt(answerText.getText().toString());
                checkAnswer();
                generateQuestion();
            }
        });

    }

    //new question when radio buton is selected
    private void generateQuestion() {
        TextView firstNumberTextView = findViewById(R.id.firstNumberQ);
        TextView operatorTextView = findViewById(R.id.operatorQ);
        TextView secondNumberTextView = findViewById(R.id.secondNumberQ);

        Random random = new Random();
        firstNumber = random.nextInt(level);
        secondNumber = random.nextInt(level);
        operator = random.nextInt(4);

        //display
        firstNumberTextView.setText(String.valueOf(firstNumber));
        operatorTextView.setText(getOperatorSymbol(operator));
        secondNumberTextView.setText(String.valueOf(secondNumber));
    }

    //compare user answer with the real correct answer
    private void checkAnswer() {
        TextView scoreText = findViewById(R.id.scoreText);

        // Calculations
        int correctAnswer;
        switch (operator) {
            case 0:
                correctAnswer = firstNumber + secondNumber;
                break;
            case 1:
                correctAnswer = firstNumber - secondNumber;
                break;
            case 2:
                correctAnswer = firstNumber * secondNumber;
                break;
            case 3:
                correctAnswer = firstNumber / secondNumber;
                break;
            default:
                correctAnswer = 0;
        }

        //add score
        if (userAnswer == correctAnswer) {
            score++;
        } else {
            //deduct score
            score--;
        }

        // Display score
        scoreText.setText(String.valueOf(score));
    }

    //show operator symbol
    private String getOperatorSymbol(int operator) {
        // Map the number into an operator symbol
        switch (operator) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "*";
            case 3:
                return "/";
            default:
                return "";
        }
    }


    //get the level for radio button
    private int getLevelFromRadioButton(RadioButton radioButton) {
        switch (radioButton.getText().toString()) {
            case "i3":
                return 10;
            case "i5":
                return 100;
            case "i7":
                return 1000;
            default:
                return 0;
        }
    }
}
