package com.example.lottogame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private Button addBtn;
    private Button initBtn;
    private Button runBtn;
    private NumberPicker numberPicker;
    private ArrayList<TextView> numberTextViewList = new ArrayList<>();
    private Set<Integer> pickerNumberSet = new HashSet<>();
    private boolean didRun = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        addEventListener();
        getRandomNumber();
        Log.d(TAG, "getRandomNumber() : " + getRandomNumber());

    }

    private void initData() {
        addBtn = findViewById(R.id.addButton);
        initBtn = findViewById(R.id.initButton);
        runBtn = findViewById(R.id.runButton);
        numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(45);
        numberTextViewList.add(findViewById(R.id.textView1));
        numberTextViewList.add(findViewById(R.id.textView2));
        numberTextViewList.add(findViewById(R.id.textView3));
        numberTextViewList.add(findViewById(R.id.textView4));
        numberTextViewList.add(findViewById(R.id.textView5));
        numberTextViewList.add(findViewById(R.id.textView6));
    }

    private void addEventListener() {
        runBtn.setOnClickListener(view -> {
            // 랜덤으로 list넣은거 1개든 5개든 담겨있을텐데
            List<Integer> list = getRandomNumber();
            // set 개열에,, 사용자가 선택한 번호 1개든 5개든 담겨있을 텐데..
            // 하나를 합쳐줄 거다.
            list.addAll(pickerNumberSet);
            // 정렬
            Collections.sort(list);
            Log.d(TAG, "결과: " + list.toString());
            didRun = true;
            //xml 출력!!
            for (int i = 0; i < list.size(); i++) {
                numberTextViewList.get(i).setText(String.valueOf(list.get(i)));
                numberTextViewList.get(i).setVisibility(View.VISIBLE);
                // 도전과제!!!!(1 ~10) (11~20)(21~30)(31~40)(41~45)색상 다르게
                numberTextViewList.get(i).setBackground(setTextViewBackground(list.get(i)));
            }
        });
        addBtn.setOnClickListener(view -> {
            if (didRun) {
                Toast.makeText(this, "초기화", Toast.LENGTH_SHORT).show();
            }
            // 1. NumberPicker 현재 값을 불러온다.
            int selectedNumber = numberPicker.getValue();
            Log.d(TAG, selectedNumber + " ");
            // 번호는 5개까지 선택 가능
            if (pickerNumberSet.size() >= 5) {
                Toast.makeText(this, "번호는 5개까지 선택이 가능합니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            // 똑같은 번호 선택시 예외 처리
            if (pickerNumberSet.contains(selectedNumber)) {
                Toast.makeText(this, "이미 선택한 번호야", Toast.LENGTH_SHORT).show();
                return;
            }
            //사용자가 1개 선택했더라면,, 0번째와 1번째에 값이 set된다.
            TextView textView = numberTextViewList.get(pickerNumberSet.size());
            textView.setVisibility(View.VISIBLE);
            textView.setText(String.valueOf(selectedNumber));
            pickerNumberSet.add(selectedNumber);


        });
        initBtn.setOnClickListener(view -> {
            didRun = false;
            //사용자가 선택한 숫자 set계열에 담았는데 .. clear써서 비우고,, textView도 지우면 된다.
            pickerNumberSet.clear();
            for (TextView tv : numberTextViewList) {
                tv.setVisibility(View.GONE);
            }
        });
    }

    private List<Integer> getRandomNumber() {
        ArrayList<Integer> list = new ArrayList<>();
        // 1 ~45 미리 만들어 두자
        for (int i = 1; i < 46; i++) {
            // 자료구조에 그냥 담기
            // 사용자가 선택한 번호는 처음부터 생성안되게 만들거임.
            if (pickerNumberSet.contains(i)) {
                continue;
            }
            list.add(i); // 1~45까지 값이 담긴다.
        }
        Collections.shuffle(list);//set은 순서가 없어서 list쓴다

        return list.subList(0, 6 - pickerNumberSet.size()); // 선택한 갯수 빼서 전달해줌

    }

    private Drawable setTextViewBackground(int number) {
        Drawable drawable;
        if (number > 10 && number <= 19) {
            drawable = ContextCompat.getDrawable(this, R.drawable.round_background5);
        } else if (number >= 20 && number <= 29) {
            // 자바 오브젝트에서 드로어블 리소스에 접근해서 가져ㅛ욤
            drawable = ContextCompat.getDrawable(this, R.drawable.round_background2);
        } else if (number >= 30 && number <= 39) {
            drawable = ContextCompat.getDrawable(this, R.drawable.round_background3);
        } else if (number >= 40) {
            drawable = ContextCompat.getDrawable(this, R.drawable.round_background4);
        } else {
            drawable = ContextCompat.getDrawable(this, R.drawable.round_background1);
        }
        return drawable;
    }
}