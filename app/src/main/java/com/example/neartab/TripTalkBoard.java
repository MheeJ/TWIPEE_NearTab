package com.example.neartab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;
import java.util.List;

public class TripTalkBoard extends AppCompatActivity implements View.OnClickListener {
    public static int BoardKind;



    private ListView listView;
    TextView tv_board_write, tv_title;
    Button btn_board_comment;
    ImageView backArrow;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_trip_talk_board);

        listView=(ListView)findViewById(R.id.listboard);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent , View view , int position , long id) {
                Intent it=new Intent(TripTalkBoard.this,MainActivity.class);
                startActivity(it);

            }
        });
        initView();


//        final ViewPager viewPager=view.findViewById(R.id.viewboard);

//        viewPager.setAdapter(postAapter);
//        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
//        viewPager.addOnPageChangeListener(new  TabLayout.TabLayoutOnPageChangeListener(tabs));
    }

    private void initView() {
        setBoardDTOS(BoardKind);



        initDatabase();

        //뒤로가기
        backArrow.setOnClickListener(this);

        //Title 설정 : 게시판



    }

    private void initDatabase() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            default:
                break;
        }
    }


    private  void setBoardDTOS(int BoardKind1){
        BoardKind = BoardKind1;

    }
}