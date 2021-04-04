package com.hansung.android.myandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MonthViewActivity extends AppCompatActivity {
    int year,month, days,dayofweek,endofday;
    static dayAdapter dayadpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        ArrayList<dayItem> dayitems = new ArrayList<dayItem>();
        Calendar cal = Calendar.getInstance();
        year=intent.getIntExtra("year",-1); //인텐트값 받기
        month=intent.getIntExtra("month", -1);

        //초기 현재 날자값 받아오기 set 초기화
        if(year == -1 && month == -1) {
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
            cal.set(year,month,1);
        }
        //1월달이면 month 의 값이 0이고 만약 month -1이면 yaer의 값 1감소하고 month를 다시12월부터 시작 set 초기화
        else if(month == -1){
            year = year -1;
            month = 11;
            cal.set(year,month,1);
        }
        //12월달이면 month 의 값이 11이고 만약 month 12이면 yaer의 값 1증가하고 month를 다시1월부터 시작 set 초기화
        else if(month == 12){
            year = year+1;
            month = 0;
            cal.set(year,month,1);
        }
        //초기값이 아니면 이전 or 다음 버튼을 눌렀을때 year값과 month값을 다시 설정
        else{
            cal.set(year,month,1);
        }

        //상단 버튼, 년도, 월 출력
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

        //요일 표시 그리드뷰
        String[] day = {"일","월","화","수","목","금","토"};
        ArrayAdapter<String> dayadapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,day);
        GridView daygrid = (GridView) findViewById(R.id.dayofweekgridview);
        daygrid.setAdapter(dayadapt);

        //날짜 표시 그리드뷰
        dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        endofday = cal.getActualMaximum(Calendar.DATE);

        for(int i =1; i<dayofweek;i++){
            dayitems.add(new dayItem(" "));
        }
        for (int i = 1; i < endofday + 1; i++) {
            dayitems.add(new dayItem(String.valueOf(i)));
        }

        dayadpter = new dayAdapter(this,R.layout.item,dayitems);
        GridView daygridview =(GridView) findViewById(R.id.daygridview);
        daygridview.setAdapter(dayadpter);

        //날짜 클릭시 토스트 메세지 출력
        daygridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View vClicked,
                                    int position, long id) {
                String dayvalue = ((dayItem)dayadpter.getItem(position)).dayvalue;
                Toast.makeText(MonthViewActivity.this, year + "년" + (month+1) +"월" + dayvalue+"일",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }


}