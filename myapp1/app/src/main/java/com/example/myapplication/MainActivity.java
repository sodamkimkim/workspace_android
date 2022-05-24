package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private TextView zero;
    private TextView one;
    private TextView two;
    private TextView three;
    private TextView four;
    private TextView seven;
    private TextView five;
    private TextView six;
    private TextView eight;
    private TextView nine;
    private TextView ca;
    private TextView plus;
    private TextView result;
    private TextView multiply;
    private TextView substaract;
    private TextView divide;
    private TextView equal;

    ArrayList<String> arrListFormularValues;
    String newvalue = "0";

    String formula = "";
    boolean operatorOnOff = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        initData();
        addEventListener();
    }


    private void initData() {
//        r = 주소값
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        ca = findViewById(R.id.ca);
        plus = findViewById(R.id.plus);
        multiply = findViewById(R.id.multiply);
        substaract = findViewById(R.id.substract);
        divide = findViewById(R.id.divide);
        equal = findViewById(R.id.equal);
        result = findViewById(R.id.result);
        arrListFormularValues = new ArrayList<>();
    }

    private void setResultTextView(String number) {
        if (newvalue.equals("0")) {
            result.setText(number);
            newvalue = "";
        }
        newvalue += number;
        result.setText(newvalue);
        operatorOnOff = false;
    }

    private void setOperatorToFormula(String operator) {

        if (!operatorOnOff) {
            formula += operator;
        }

        operatorOnOff = false;

    }

//    private String removeStartEndOperFormula(String formula) {
//
//        String[] arr = formula.split("");
//
//        if ((arr[0].equals("+")) |
//                (arr[0].equals("-")) |
//                (arr[0].equals("*")) |
//                (arr[0].equals("/"))) {
//            arr[0] = "";
//        }
//
//        if (arr[(arr.length) - 1].equals("+") |
//                arr[(arr.length) - 1].equals("-") |
//                arr[(arr.length) - 1].equals("*") |
//                arr[(arr.length) - 1].equals("/")) {
//            arr[(arr.length) - 1] = "";
//        }
//
//        formula = String.join("", arr);
//
//        System.out.println(formula);
//        return formula;
//    }

    private ArrayList<String> removeStartEndOperArrList(ArrayList<String> arrListFormularValues) {

        int arrListSize = arrListFormularValues.size();
        if (arrListFormularValues.get(arrListSize - 1).equals("+") |
                arrListFormularValues.get(arrListSize - 1).equals("-") |
                arrListFormularValues.get(arrListSize - 1).equals("*") |
                arrListFormularValues.get(arrListSize - 1).equals("/")) {
            arrListFormularValues.remove(arrListSize - 1);
            System.out.println("끝연산자 리무브됨? : " + arrListFormularValues.toString());
        }
        return arrListFormularValues;
    }

    private int findIndex(ArrayList<String> arrListFormularValues) {
        // 우선순위 높은 연산자 인덱스 찾아서
        // 그 앞과 뒤 숫자와 연산해주고
        // 그 결과를 다시 연산자 인덱스 위치에 넣어주기
        // 반환은 arraylist를 반환
        int idx1;
        int idx2;
        int opIndex = 0;
        if ((arrListFormularValues.indexOf("*") != -1) || (arrListFormularValues.indexOf("/") != -1)) {
            idx1 = arrListFormularValues.indexOf("*");
            idx2 = arrListFormularValues.indexOf("/");
            if (idx1 == -1 && idx2 != -1) {
                opIndex = idx2;
                System.out.println("여기는 findFirstPriorityOperator 1 : " + opIndex);
            } else if (idx2 == -1 && idx1 != -1) {
                opIndex = idx1;
                System.out.println("여기는 findFirstPriorityOperator 2 : " + opIndex);

            } else {
                opIndex = (idx1 < idx2) ? idx1 : idx2;
                System.out.println("여기는 findFirstPriorityOperator 3 : " + opIndex);
            }
        } else if ((arrListFormularValues.indexOf("+") != -1) || (arrListFormularValues.indexOf("-") != -1)) {
            idx1 = arrListFormularValues.indexOf("+");
            idx2 = arrListFormularValues.indexOf("-");

            if (idx1 == -1 && idx2 != -1) {
                opIndex = idx2;
                System.out.println("여기는 findFirstPriorityOperator 4 : " + opIndex);
            } else if (idx2 == -1 && idx1 != -1) {
                opIndex = idx1;
                System.out.println("여기는 findFirstPriorityOperator 5 : " + opIndex);
            } else {
                opIndex = (idx1 < idx2) ? idx1 : idx2;
                System.out.println("여기는 findFirstPriorityOperator 6 : " + opIndex);
            }
        }

        return opIndex;
    }



    private ArrayList<String> subCalaulate(int opIndex) {
        double leftNumber = Double.parseDouble(arrListFormularValues.get(opIndex - 1));
        double rightNumber = Double.parseDouble(arrListFormularValues.get(opIndex + 1));
        double result = 0.0;

        String operator = arrListFormularValues.get(opIndex);

        switch (operator) {
            case "*":
                result = leftNumber * rightNumber;
                break;
            case "/":
                result = leftNumber / rightNumber;
                break;
            case "+":
                result = leftNumber + rightNumber;
                break;
            case "-":
                result = leftNumber - rightNumber;
                break;

        }


        arrListFormularValues.set(opIndex - 1, String.valueOf(result));
        arrListFormularValues.remove(opIndex);
        arrListFormularValues.remove(opIndex);


        System.out.println("subcalculate : " + arrListFormularValues.toString());

        return arrListFormularValues;
    }

    private ArrayList<String> operateByPriority(ArrayList<String> arrListFormularValues) {
        while (arrListFormularValues.size() > 1) {
            arrListFormularValues = subCalaulate(findIndex(arrListFormularValues));
            System.out.println("중간결과??" + arrListFormularValues.toString());
        }
        System.out.println("최종결과??" + arrListFormularValues.toString());
        return arrListFormularValues;
    }

    private void addEventListener() {

        one.setOnClickListener(view -> {
            formula += "1";
            setResultTextView("1");
            System.out.println(formula);
        });


        two.setOnClickListener(view -> {
            formula += "2";
            setResultTextView("2");
            System.out.println(formula);
        });

        three.setOnClickListener(view -> {
            formula += "3";
            setResultTextView("3");
            System.out.println(formula);

        });

        four.setOnClickListener(view -> {
            formula += "4";
            setResultTextView("4");
            System.out.println(formula);

        });

        five.setOnClickListener(view -> {
            formula += "5";
            setResultTextView("5");
            System.out.println(formula);

        });

        six.setOnClickListener(view -> {
            formula += "6";
            setResultTextView("6");
            System.out.println(formula);

        });

        seven.setOnClickListener(view -> {
            formula += "7";
            setResultTextView("7");
            System.out.println(formula);

        });

        eight.setOnClickListener(view -> {
            formula += "8";
            setResultTextView("8");
            System.out.println(formula);


        });

        nine.setOnClickListener(view -> {
            formula += "9";
            setResultTextView("9");
            System.out.println(formula);

        });

        zero.setOnClickListener(view -> {
            formula += "0";
            setResultTextView("0");
            System.out.println(formula);

        });


        ca.setOnClickListener(view -> {
            arrListFormularValues.clear();
            newvalue = "0";
            formula = "";
            result.setText("0");

        });


        plus.setOnClickListener(view -> {
            String strTemp = String.valueOf(result.getText()); // result에 있는 현재 숫자들
            arrListFormularValues.add(strTemp);
            setOperatorToFormula("+");
            operatorOnOff = true;
            System.out.println(formula);
            arrListFormularValues.add("+");
            newvalue = "0";

            System.out.println("arrListFormularValues" + arrListFormularValues);

        });

        multiply.setOnClickListener(view -> {
            String strTemp = String.valueOf(result.getText()); // result에 있는 현재 숫자들
            arrListFormularValues.add(strTemp);
            setOperatorToFormula("*");
            operatorOnOff = true;
            System.out.println(formula);
            arrListFormularValues.add("*");
            newvalue = "0";


            System.out.println("arrListFormularValues" + arrListFormularValues);
        });

        substaract.setOnClickListener(view -> {
            String strTemp = String.valueOf(result.getText()); // result에 있는 현재 숫자들
            arrListFormularValues.add(strTemp);
            setOperatorToFormula("-");
            operatorOnOff = true;
            System.out.println(formula);
            arrListFormularValues.add("-");
            newvalue = "0";

            System.out.println("arrListFormularValues" + arrListFormularValues);
        });

        divide.setOnClickListener(view -> {
            String strTemp = String.valueOf(result.getText()); // result에 있는 현재 숫자들
            arrListFormularValues.add(strTemp);
            setOperatorToFormula("/");
            operatorOnOff = true;
            System.out.println(formula);
            arrListFormularValues.add("/");
            newvalue = "0";

            System.out.println("arrListFormularValues" + arrListFormularValues);
        });

        equal.setOnClickListener(view -> {
            System.out.println(formula);

            String strTemp = String.valueOf(result.getText()); // result에 있는 현재 숫자들
            arrListFormularValues.add(strTemp);
            int arrListLastIndex = arrListFormularValues.size() - 1;
            System.out.println("어레이리스트 마지막 : " + arrListFormularValues.get(arrListLastIndex));
            arrListFormularValues = removeStartEndOperArrList(arrListFormularValues);
            System.out.println("끝에연산자 지워졌나요? 여긴equal : " + arrListFormularValues);
            arrListFormularValues = operateByPriority(arrListFormularValues);
            result.setText(arrListFormularValues.get(0));
            arrListFormularValues.clear();
        });


    }

}