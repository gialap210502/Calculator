package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private EditText displayEditText;
    private String currentInput = "";
    private String operator = "";
    private double operand1 = 0.0;
    private double result = 0.0;
    private boolean hasResult = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayEditText = findViewById(R.id.displayEditText);
        // Initialize click listeners for digit buttons (0-9)
        for (int i = 0; i <= 9; i++) {
            final int digit = i; // Declare a final variable to capture the current value of 'i'
            int buttonId = getResources().getIdentifier("button_" + digit, "id", getPackageName());
            Button button = findViewById(buttonId);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleDigitClick(digit);
                }
            });
        }
        // Initialize click listeners for operator buttons (+, -, *, /)
        Button buttonAdd = findViewById(R.id.button_add);
        Button buttonSubtract = findViewById(R.id.button_Subtract);
        Button buttonMultiply = findViewById(R.id.button_Multiply);
        Button buttonDivide = findViewById(R.id.button_divide);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperatorClick("+");
            }
        });
        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperatorClick("-");
            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperatorClick("*");
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperatorClick("/");
            }
        });
        // Initialize click listener for the equals button (=)
        Button buttonEquals = findViewById(R.id.button_equals);
        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateResult();
            }
        });

        // Initialize click listener for the clear button (C)
        Button buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDisplay();
            }
        });
    }
    private void handleDigitClick(int digit) {
        if (hasResult) {
            currentInput = "";
            hasResult = false;
        }
        currentInput += digit;
        updateDisplay();
    }

    private void handleOperatorClick(String newOperator) {
        if (!currentInput.isEmpty()) {
            operator = newOperator;
            operand1 = Double.parseDouble(currentInput);
            currentInput = "";
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double operand2 = Double.parseDouble(currentInput);

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        displayEditText.setText("Error: Division by zero");
                        return;
                    }
                    break;
            }

            currentInput = Double.toString(result);
            operator = "";
            operand1 = result;
            hasResult = true;
            updateDisplay();
        }
    }
    private void clearDisplay() {
        currentInput = "";
        operator = "";
        operand1 = 0.0;
        result = 0.0;
        hasResult = false;
        updateDisplay();
    }

    private void updateDisplay() {
        displayEditText.setText(currentInput);
    }

}