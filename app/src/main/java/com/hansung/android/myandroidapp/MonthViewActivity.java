package com.hansung.android.myandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
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

        if(year == -1 || month == -1) {
            year = Calendar.getInstance().get(Calendar.YEAR);
            month = Calendar.getInstance().get(Calendar.MONTH);
        }

        TextView yearmonthTv =findViewById(R.id.month);
        yearmonthTv.setText(year+"년"+month+ "월");

        Button privBtn = findViewById(R.id.btn);
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
    }


}