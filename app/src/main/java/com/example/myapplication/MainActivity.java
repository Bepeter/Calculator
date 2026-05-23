package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView input;
//    Button button_c, button_cent, button_root, ac, point;
//    Button divide, multiply, plus, minus, equals;
//    Button button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9;
//
    String viewText;
    double firstNum;
    double secondNum;

    String operator = "";
    boolean newNum = false;

    double pointChange = 1;

    boolean isSwitch = false;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        input = findViewById(R.id.input);

        findViewById(R.id.button_c).setOnClickListener(thing -> onClearClick());
        findViewById(R.id.button_1).setOnClickListener(thing -> onNumberClick(1));
        findViewById(R.id.button_2).setOnClickListener(thing -> onNumberClick(2));
        findViewById(R.id.button_3).setOnClickListener(thing -> onNumberClick(3));
        findViewById(R.id.button_4).setOnClickListener(thing -> onNumberClick(4));
        findViewById(R.id.button_5).setOnClickListener(thing -> onNumberClick(5));
        findViewById(R.id.button_6).setOnClickListener(thing -> onNumberClick(6));
        findViewById(R.id.button_7).setOnClickListener(thing -> onNumberClick(7));
        findViewById(R.id.button_8).setOnClickListener(thing -> onNumberClick(8));
        findViewById(R.id.button_9).setOnClickListener(thing -> onNumberClick(9));
        findViewById(R.id.button_0).setOnClickListener(thing -> onNumberClick(0));
        findViewById(R.id.multiply).setOnClickListener(thing -> onOperatorClick("multi"));
        findViewById(R.id.plus).setOnClickListener(thing -> onOperatorClick("plus"));
        findViewById(R.id.minus).setOnClickListener(thing -> onOperatorClick("minus"));
        findViewById(R.id.divide).setOnClickListener(thing -> onOperatorClick("divide"));
        findViewById(R.id.equals).setOnClickListener(thing -> onEqualsClick());
        findViewById(R.id.ac).setOnClickListener(thing -> onAClearClick());
        findViewById(R.id.button_root).setOnClickListener(thing -> onOtherClick("root"));
        findViewById(R.id.point).setOnClickListener(thing -> onPointClick());
        findViewById(R.id.button_cent).setOnClickListener(thing -> onOtherClick("cent"));

    }

    void onOtherClick(String op) {
        double localNum = firstNum;
        if (newNum) {
            localNum = secondNum;
        }
        if(op == "root") {
            localNum = Math.sqrt(localNum);
            viewText = localNum + "";
        }
        if(op == "cent") {
            localNum = localNum / 100;
            viewText = localNum + "";
        }
        updateDisplay();
        viewText = "0";
        newNum = false;
        secondNum = 0;
        isSwitch = false;
        pointChange =1;
        firstNum = 0;
    }

    void onNumberClick(int num) {


        int quickMod = 10;

        if(newNum && secondNum == 0) {
            viewText = "";
        }
        if (isSwitch) {
            pointChange = pointChange * 0.1;
            quickMod = 1;
        }


        if(newNum){
            secondNum =( secondNum * quickMod) + (num * pointChange) ;

            viewText = "" + secondNum;
        }
        else {
            firstNum = (firstNum * quickMod) + (num * pointChange) ;

            viewText = "" + firstNum;
        }


        updateDisplay();


    }

    void onAClearClick() {

        if(newNum) {
            secondNum = 0;
            viewText = "" + secondNum;
        }
        else {
            firstNum = 0;
            viewText = "" + firstNum;
        }

        updateDisplay();
    }

    void onOperatorClick(String op) {
        viewText = "";
        operator = op;
//        if(!Objects.equals(operator, "")) {
//            return;
////            onEqualsClick();
//        }
        newNum = true;
        isSwitch = false;
        pointChange = 1;


    }

    void onEqualsClick() {
        if (Objects.equals(operator, "multi")) {
            firstNum = firstNum * secondNum;
            viewText = "" + firstNum;
        }
        else if (Objects.equals(operator, "divide")) {
            firstNum = firstNum / secondNum;
            viewText = "" + firstNum;
        }
        else if (Objects.equals(operator, "plus")) {
            firstNum = firstNum + secondNum;
            viewText = "" + firstNum;
        }
        else if (Objects.equals(operator, "minus")) {
            firstNum = firstNum - secondNum;
            viewText = "" + firstNum;
        }
        else {
           return;
        }
        updateDisplay();
        viewText = "0";
        newNum = false;
        secondNum = 0;
        isSwitch = false;
        pointChange =1;
        firstNum = 0;

    }

    void onPointClick() {
        isSwitch = true;
    }
    void onClearClick() {
        firstNum = 0;
        secondNum = 0;
        pointChange = 1;
        isSwitch = false;
        viewText = "" + firstNum;
        newNum = false;
        updateDisplay();
    }
    void updateDisplay() {

        input.setText(viewText);
    }
}