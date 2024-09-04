package com.example.ph_calculator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private String currentInput = "";
    private String operator = "";
    private double firstValue = Double.NaN;
    private double secondValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        setButtonListeners();
    }

    private void setButtonListeners() {
        int[] numberButtonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9, R.id.btnDot
        };

        int[] operatorButtonIds = {
                R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide, R.id.btnEqual
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(this::onNumberButtonClick);
        }

        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(this::onOperatorButtonClick);
        }

        findViewById(R.id.btnClear).setOnClickListener(v -> clear());
    }

    private void onNumberButtonClick(View v) {
        Button button = (Button) v;
        currentInput += button.getText().toString();
        tvDisplay.setText(currentInput);
    }

    private void onOperatorButtonClick(View v) {
        Button button = (Button) v;
        String clickedOperator = button.getText().toString();

        if (!Double.isNaN(firstValue)) {
            secondValue = Double.parseDouble(currentInput);
            calculate();
            operator = clickedOperator;
            tvDisplay.setText(String.valueOf(firstValue));
        } else {
            firstValue = Double.parseDouble(currentInput);
            operator = clickedOperator;
        }

        currentInput = "";
    }

    private void calculate() {
        switch (operator) {
            case "+":
                firstValue = firstValue + secondValue;
                break;
            case "-":
                firstValue = firstValue - secondValue;
                break;
            case "*":
                firstValue = firstValue * secondValue;
                break;
            case "/":
                firstValue = firstValue / secondValue;
                break;
        }
    }

    private void clear() {
        currentInput = "";
        operator = "";
        firstValue = Double.NaN;
        secondValue = Double.NaN;
        tvDisplay.setText("0");
    }
}

