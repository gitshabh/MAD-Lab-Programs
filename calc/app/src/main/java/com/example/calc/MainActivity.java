package com.example.calc;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    EditText txtValue;
    String operatorValue;
    Boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtValue = (EditText) findViewById(R.id.calculator_txt_valuetext);
        txtValue.setSingleLine(false);
    }


    public void numberClick(View view) {

        if (isNewOperation) {
            isNewOperation = false;
            txtValue.setText("");
        }

        String value = txtValue.getText().toString();
        int item = view.getId();

        if (item == R.id.calculator_btn_zero)
            value += "0";

        else if (item == R.id.calculator_btn_One) {
            value += "1";
        } else if (item == R.id.calculator_btn_Two) {
            value += "2";
        } else if (item == R.id.calculator_btn_Three) {
            value += "3";
        } else if (item == R.id.calculator_btn_four) {
            value += "4";
        } else if (item == R.id.calculator_btn_five) {
            value += "5";
        } else if (item == R.id.calculator_btn_six) {
            value += "6";
        } else if (item == R.id.calculator_btn_seven) {
            value += "7";
        } else if (item == R.id.calculator_btn_eight) {
            value += "8";
        } else if (item == R.id.calculator_btn_nine) {
            value += "9";
        } else if (item == R.id.calculator_btn_dot) {
            value += ".";
        }

        txtValue.setText(value);
    }


    public void operatorClick(View view) {
        String value = txtValue.getText().toString();
        int item = view.getId();
        if (item == R.id.calculator_btn_add) {
            operatorValue = "+";
            value += "+";
        } else if (item == R.id.calculator_btn_subtract) {
            operatorValue = "-";
            value += "-";
        } else if (item == R.id.calculator_btn_multiple) {
            operatorValue = "*";
            value += "*";
        } else if (item == R.id.calculator_btn_divide) {
            operatorValue = "/";
            value += "/";
        }

        txtValue.setText(value);
    }

    public void clearButton(View view) {
        txtValue.setText("");
    }

    public boolean checkP(char operatorA, char operatorB) {
        if (operatorB == '(' || operatorB == ')') {
            return false;
        }
        if ((operatorA == '+' || operatorA == '-') && (operatorB == '*' || operatorB == '/')) {
            return false;
        } else {
            return true;
        }
    }

    public float applyOp(char operatorX, float a, float b) {
        switch (operatorX) {
            case '+':
                return b + a;
            case '-':
                return b - a;
            case '*':
                return b * a;
            case '/':
                if (a != 0)
                    return b / a;
        }
        return 0;
    }

    public void equalsClick(View view) {
        try {
            String value = txtValue.getText().toString();
            char[] myChar = value.toCharArray();

            Stack<Float> myNumStack = new
                    Stack<Float>();

            Stack<Character> operatorStack = new
                    Stack<Character>();


            for (int i = 0; i < myChar.length; i++) {

                if (myChar[i] >= '0' &&
                        myChar[i] <= '9') {
                    StringBuffer stringBuffer = new
                            StringBuffer();


                    while (i < myChar.length && myChar[i] >= '0' && myChar[i] <= '9') {
                        stringBuffer.append(myChar[i++]);
                    }
                    myNumStack.push(Float.parseFloat(stringBuffer.toString()));


                    i--;
                } else if (myChar[i] == '/' || myChar[i] == '*' || myChar[i] == '+' || myChar[i] == '-') {

                    while (!operatorStack.empty() && checkP(myChar[i], operatorStack.peek())) {
                        myNumStack.push(applyOp(operatorStack.pop(),
                                myNumStack.pop(),
                                myNumStack.pop()));
                    }


                    operatorStack.push(myChar[i]);
                }
            }


            while (!operatorStack.empty()) {
                myNumStack.push(applyOp(operatorStack.pop(), myNumStack.pop(), myNumStack.pop()));
            }


            String finalValue = String.format("%.2f", myNumStack.pop());

            txtValue.setText(value + "\n" + finalValue);
        }
        catch(Exception e){
            Toast.makeText(this, "Re-Enter Correct Expression", Toast.LENGTH_SHORT).show();
        }

        isNewOperation = true;
    }
}