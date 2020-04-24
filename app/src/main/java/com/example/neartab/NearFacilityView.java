package com.example.neartab;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;

import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;


public class NearFacilityView extends Fragment implements View.OnClickListener {
    private NearAdapter adapter;
    private ListView listView;
    private Button mBtnNearSearch;
    private double latitude;
    private double longitude;
    ToggleButton TripOnOff_Btn;
    MyReceiver myReceiver;
    private String MyAddress_Longitude, MyAddress_Latitude, ServiceData_LongitudeList, ServiceData_LatitudeList;
    List<String> MyLocation_LongitudeList;
    List<String>  MyLocation_LatitudeList;

    public NearFacilityView() {}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearfacilityview, container, false);
        mBtnNearSearch = (Button)view.findViewById(R.id.btn_near);
        mBtnNearSearch.setOnClickListener(this);
        TripOnOff_Btn = (ToggleButton)view.findViewById(R.id.trip_onoff);
        TripOnOff_Btn.setOnClickListener(this);
        adapter=new NearAdapter();
        listView=(ListView)view.findViewById(R.id.listview_near);
        setData();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent4 = new Intent(getActivity(), Near_Location.class);
                intent4.putExtra("My_longitude_double",longitude);
                intent4.putExtra("My_latitude_double",latitude);
                switch (i)
                {
                    case 0:
                        intent4.putExtra("location","화장실");
                        startActivity(intent4);
                        break;
                    case 1:
                        intent4.putExtra("location","주차장");
                        startActivity(intent4);
                        break;
                    case 2:
                        intent4.putExtra("location","흡연실");
                        startActivity(intent4);
                        break;
                    case 3:
                        intent4.putExtra("location","약국");
                        startActivity(intent4);
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }

    private void setData(){
        TypedArray Resldenear=getResources().obtainTypedArray(R.array.resId_near);
        String[] position=getResources().getStringArray(R.array.position);
        TypedArray Resldposition=getResources().obtainTypedArray(R.array.resld_position);
        for (int i=0; i<((TypedArray) Resldenear).length();i++){
            NearDTO dto=new NearDTO();
            dto.setResId_near(Resldenear.getResourceId(i,0));
            dto.setPosition(position[i]);
            dto.setResld_position(Resldposition.getResourceId(i,2));
            adapter.addItem(dto);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_near:
                Intent intent=new Intent(getActivity(), Near_Search.class);
                intent.putExtra("My_longitude",MyAddress_Longitude);
                intent.putExtra("My_latitude",MyAddress_Latitude);
                startActivity(intent);
                break;

            case R.id.trip_onoff:
                if (TripOnOff_Btn.isChecked()) {
                    //아래 세개 문장은 꼭 필요
                    prepareBackgroundService();
                    Toast.makeText(getApplicationContext(),"Service 시작",Toast.LENGTH_LONG).show();
                    getActivity().startService(new Intent(getActivity(),GPSBackgroundService.class));
                } else {
                    //아래 네개 문장은 꼭 필요
                    ServiceData_LongitudeList = "";
                    ServiceData_LatitudeList = "";
                    MyAddress_Latitude = null;
                    MyAddress_Longitude = null;
                    longitude = 0;
                    latitude = 0;
                    Toast.makeText(getApplicationContext(),"Service 끝",Toast.LENGTH_LONG).show();
                    getActivity().stopService(new Intent(getActivity(),GPSBackgroundService.class));
                    break;
                }
            default:
                break;
        }
    }

    //서비스 시작하기전 준비
    private void prepareBackgroundService(){
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(GPSBackgroundService.MY_ACTION);
        getActivity().registerReceiver(myReceiver, intentFilter);
    }

    //서비스에서 데이터 가져오는 부분
    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // TODO Auto-generated method stub
            //사용자가 이동한 전체 위치
            ServiceData_LongitudeList = arg1.getStringExtra("ServiceData_longitudeList");
            ServiceData_LatitudeList = arg1.getStringExtra("ServiceData_latitudeList");
            longitude = arg1.getDoubleExtra("double_longitude",0);
            latitude = arg1.getDoubleExtra("double_latitude",0);
            getMyLocation();
        }
    }

    //사용자의 현재 위치 MyAddress_Longitude, MyAddress_Latitude 에 저장해놓는 구문임.
    public void getMyLocation(){
        MyLocation_LongitudeList = Arrays.asList(ServiceData_LongitudeList.split("#"));
        if(! MyLocation_LongitudeList.isEmpty()){
            MyAddress_Longitude =  MyLocation_LongitudeList.get( MyLocation_LongitudeList.size()-1);
        }
        MyLocation_LatitudeList = Arrays.asList(ServiceData_LatitudeList.split("#"));
        if(! MyLocation_LatitudeList.isEmpty()){
            MyAddress_Latitude =  MyLocation_LatitudeList.get(MyLocation_LatitudeList.size()-1);
        }
    }

}
