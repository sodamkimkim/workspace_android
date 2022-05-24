package com.example.mybmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BmiResultActivity extends AppCompatActivity {
private TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result);
        txtView = findViewById(R.id.txtView1);
        if(getIntent() != null){
            int height = getIntent().getIntExtra("height",0);
            int weight = getIntent().getIntExtra("weight",0);

            //체중/(신장(m)*신장(m))
            double bmiValue = weight/(Math.pow(height/100.0,2));//나누기 계산할 때는 무조건 소숫점으로 적기., 그래야 정확한 결과값나온다.
            Log.d("TAG",bmiValue+" ");
            String resultText = "";
            if(bmiValue <18.5){
                resultText = "저체중 입니다.";

            }else if(bmiValue >=18.5 && bmiValue <= 24.9){
                resultText = "정상 체중 입니다.";
            }else if(bmiValue >=25 && bmiValue<=29.9){
                resultText = "경도 비만입니다.";
            }else if(bmiValue >=30 && bmiValue <=34.9){
                resultText=" 중도 비만입니다.";
            }else{
                resultText = "고도 비만입니다.";
            }
txtView.setText(resultText);
        }
    }
}