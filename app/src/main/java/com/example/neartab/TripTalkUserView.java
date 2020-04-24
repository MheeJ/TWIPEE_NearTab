package com.example.neartab;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TripTalkUserView extends AppCompatActivity {

    ImageView Seoul_black, Gangwon_blue, Incheon_brown, Gyeonggi_deepblue, Chungnam_deepgreen, Chungbuk_deepred, Daejeon_gold, Gyeongbuk_gray, Daegu_green, Ulsan_orange,
            Busan_pink, Gyeongnam_purple, Jeonbuk_red, Gwangju_skyblue, Jeonnam_white, Jeju_yellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userview);

        //서울
        TextView Seoul_people = (TextView) findViewById(R.id.Seoul_people);
        Seoul_black = (ImageView) findViewById(R.id.Seoul);


        //강원도
        TextView Gangwon_people = (TextView) findViewById(R.id.Gangwon_people);
        Gangwon_blue = (ImageView) findViewById(R.id.Gangwon);
        setCircle(10, getResources().getDrawable(R.drawable.c_deepblue,null), Gangwon_blue, Gangwon_people);

        //인천
        TextView Incheon_people = (TextView) findViewById(R.id.Incheon_people);
        Incheon_brown = (ImageView) findViewById(R.id.Incheon);
        setCircle(40, getResources().getDrawable(R.drawable.c_deepblue,null), Incheon_brown, Incheon_people);

        //경기도
        TextView Gyeonggi_people = (TextView) findViewById(R.id.Gyeonggi_people);
        Gyeonggi_deepblue = (ImageView) findViewById(R.id.Gyeonggi);
        setCircle(150, getResources().getDrawable(R.drawable.c_deepblue,null), Gyeonggi_deepblue, Gyeonggi_people);


        //충남
        TextView Chungnam_people = (TextView) findViewById(R.id.Chungnam_people);
        Chungnam_deepgreen = (ImageView) findViewById(R.id.Chungnam);
        setCircle(10,getResources().getDrawable(R.drawable.c_deepblue,null), Chungnam_deepgreen, Chungnam_people);

        //충북
        TextView Chungbuk_people = (TextView) findViewById(R.id.Chungbuk_people);
        Chungbuk_deepred = (ImageView) findViewById(R.id.Chungbuk);
        setCircle(60, getResources().getDrawable(R.drawable.c_deepblue,null), Chungbuk_deepred, Chungbuk_people);

        //대전
        TextView Daejeon_people = (TextView) findViewById(R.id.Daejeon_people);
        Daejeon_gold = (ImageView) findViewById(R.id.Daejeon);
        setCircle(230,  getResources().getDrawable(R.drawable.c_deepblue,null), Daejeon_gold, Daejeon_people);


        //경북
        TextView Gyeongbuk_people = (TextView) findViewById(R.id.Gyeongbuk_people);
        Gyeongbuk_gray = (ImageView) findViewById(R.id.Gyeongbuk);
        setCircle(20, getResources().getDrawable(R.drawable.c_deepblue,null), Gyeongbuk_gray, Gyeongbuk_people);

        //대구

        TextView Daegu_people = (TextView) findViewById(R.id.Daegu_people);
        Daegu_green = (ImageView) findViewById(R.id.Daegu);
        setCircle(200,getResources().getDrawable(R.drawable.c_deepblue,null), Daegu_green, Daegu_people);

        //울산
        TextView Ulsan_people = (TextView) findViewById(R.id.Ulsan_people);
        Ulsan_orange = (ImageView) findViewById(R.id.Ulsan);
        setCircle(40, getResources().getDrawable(R.drawable.c_deepblue,null), Ulsan_orange, Ulsan_people);

        //부산
        TextView Busan_people = (TextView) findViewById(R.id.Busan_people);
        Busan_pink = (ImageView) findViewById(R.id.Busan);
        setCircle(270, getResources().getDrawable(R.drawable.c_deepblue,null), Busan_pink, Busan_people);

        //경남
        TextView Gyeongnam_people = (TextView) findViewById(R.id.Gyeongnam_people);
        Gyeongnam_purple = (ImageView) findViewById(R.id.Gyeongnam);
        setCircle(40,getResources().getDrawable(R.drawable.c_deepblue,null), Gyeongnam_purple, Gyeongnam_people);

        //전북

        TextView Jeonbuk_people = (TextView) findViewById(R.id.Jeonbuk_people);
        Jeonbuk_red = (ImageView) findViewById(R.id.Jeonbuk);
        setCircle(10,getResources().getDrawable(R.drawable.c_deepblue,null), Jeonbuk_red, Jeonbuk_people);


        //광주
        TextView Gwangju_people = (TextView) findViewById(R.id.Gwangju_people);
        Gwangju_skyblue = (ImageView) findViewById(R.id.Gwangju);
        setCircle(80,getResources().getDrawable(R.drawable.c_deepblue,null), Gwangju_skyblue, Gwangju_people);

        //전남
        TextView Jeonnam_people = (TextView) findViewById(R.id.Jeonnam_people);
        Jeonnam_white = (ImageView) findViewById(R.id.Jeonnam);
        setCircle(170, getResources().getDrawable(R.drawable.c_deepblue,null),  Jeonnam_white, Jeonnam_people);

        //제주
        TextView Jeju_people = (TextView) findViewById(R.id.Jeju_people);
        Jeju_yellow = (ImageView) findViewById(R.id.Jeju);
        setCircle(270,getResources().getDrawable(R.drawable.c_deepblue,null), Jeju_yellow, Jeju_people);
    }

    private void setCircle(int num, Drawable drawable, ImageView iv, TextView tv)
    {
        iv.setImageDrawable(drawable);
        tv.setText(String.valueOf(num));

        if (0 <= num && num < 50) {
            iv.setImageResource(R.drawable.c_deepblue);
        } else if (50 <= num && num < 100) {
            iv.setImageResource(R.drawable.c_deepblue);
        } else if (150 <= num && num < 200) {
            iv.setImageResource(R.drawable.c_deepblue);
        } else if (200 <= num) {
            iv.setImageResource(R.drawable.c_deepblue);
        }
    }
}

