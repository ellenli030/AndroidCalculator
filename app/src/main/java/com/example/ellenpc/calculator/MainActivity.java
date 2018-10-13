package com.example.ellenpc.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private enum Operator {none, add, sub, mul, div, eq}
    private double data01=0, data02 = 0;
    private Operator opp = Operator.none;
    private boolean hasDot = false;
    private boolean requiresCleaning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickNumericalButton(View view) {
        int pressID = view.getId();

        TextView curText = (TextView)findViewById(R.id.resultEdit);

        if (opp == Operator.eq) {
            opp = Operator.none;
            curText.setText("");
        }

        if (requiresCleaning) {
            requiresCleaning = false;
            curText.setText("");
        }

        switch (pressID) {
            case R.id.button0:
                curText.setText(curText.getText() + "0");
                break;
            case R.id.button01:
                curText.setText(curText.getText() + "1");
                break;
            case R.id.button02:
                curText.setText(curText.getText() + "2");
                break;
            case R.id.button03:
                curText.setText(curText.getText() + "3");
                break;
            case R.id.button04:
                curText.setText(curText.getText() + "4");
                break;
            case R.id.button05:
                curText.setText(curText.getText() + "5");
                break;
            case R.id.button06:
                curText.setText(curText.getText() + "6");
                break;
            case R.id.button07:
                curText.setText(curText.getText() + "7");
                break;
            case R.id.button08:
                curText.setText(curText.getText() + "8");
                break;
            case R.id.button09:
                curText.setText(curText.getText() + "9");
                break;
            case R.id.buttonPer:
                if (!hasDot) {
                    curText.setText(curText.getText() + ".");
                    hasDot = true;
                } else {
                }
                break;
            default:
                curText.setText("ERROR");
                Log.d("Error","Error: Unknown Button pressed!");
                break;
        }
    }

    public void onClickFunctionButton(View view) {
        int pressID = view.getId();

        TextView curText = (TextView)findViewById(R.id.resultEdit);

        if (pressID == R.id.buttonClear) {
            opp = Operator.none;
            curText.setText("");
            data01 = 0;
            data02 = 0;
            requiresCleaning = false;
            return;
        }

        String dataText = curText.getText().toString();
        double numberVal = dataText.length() > 0 ? Double.parseDouble(dataText) : 0;

        if (opp == Operator.none) {
            data01 = numberVal;
            requiresCleaning = true;
            switch (pressID) {
                case R.id.buttonEQ:
                    opp = Operator.eq;
                    data01 = 0;
                    break;

                case R.id.buttonAdd:
                    opp = Operator.add;
                    break;

                case R.id.buttonMin:
                    opp = Operator.sub;
                    break;

                case R.id.buttonDiv:
                    opp = Operator.div;
                    break;

                case R.id.buttonMul:
                    opp = Operator.mul;
                    break;

                case R.id.buttonClear:
                    opp = Operator.none;
                    break;
            }
        } else {
            double result = 0;
            data02 = numberVal;

            switch (opp) {
                case eq:
                    break;

                case none:
                    break;

                case add:
                    result = data01 + data02;
                    break;

                case sub:
                    result = data01 - data02;
                    break;

                case div:
                    result = data01 / data02;
                    break;

                case mul:
                    result = data01 * data02;
                    break;
            }
            data01 = result;
            opp = Operator.none;
            if ( (result - (int)result) != 0) {
                curText.setText(String.valueOf(result));
            } else {
                curText.setText(String.valueOf((int)result));
            }
        }
    }

}
