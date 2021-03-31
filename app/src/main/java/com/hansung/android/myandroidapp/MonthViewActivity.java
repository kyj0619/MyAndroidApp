package com.hansung.android.myandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Calendar;

public class MonthViewActivity extends AppCompatActivity {
    int year;
    int month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        year=intent.getIntExtra("year",-1); //인텐트값 받기
        month=intent.getIntExtra("month", -1);


        //       Calendar cal = Calendar.getInstance();
        //       cal.set(2021,2,1);
        //       int dayofweek = cal.get(Calendar.DAY_OF_WEEK);


        //초기 현재 날자값 받아오기
        if(year == -1 && month == -1) {
            year = Calendar.getInstance().get(Calendar.YEAR);
            month = Calendar.getInstance().get(Calendar.MONTH);
        }
        //1월달이면 month 의 값이 0이고 만약 month -1이면 yaer의 값 1감소하고 month를 다시12월부터 시작
        else if(month == -1){
            year = year -1;
            month = 11;
        }
        //12월달이면 month 의 값이 11이고 만약 month 12이면 yaer의 값 1증가하고 month를 다시1월부터 시작
        else if(month == 12){
            year = year+1;
            month = 0;
        }

        TextView yearmonthTv =findViewById(R.id.month);
        yearmonthTv.setText(year+"년"+(month+1)+ "월");

        Button privBtn = findViewById(R.id.pre);
        privBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MonthViewActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month-1);
                startActivity(intent);
                finish();
            }
        });

        Button nextBtn = findViewById(R.id.next);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MonthViewActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month+1);
                startActivity(intent);
                finish();
            }
        });

        String[] day = {"일","월","화","수","목","금","토"};
        ArrayAdapter<String> dayadapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,day);
        GridView daygrid = (GridView) findViewById(R.id.dayofweekgridview);
        daygrid.setAdapter(dayadapt);
    }


}