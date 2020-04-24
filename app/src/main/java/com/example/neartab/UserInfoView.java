package com.example.neartab;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class UserInfoView extends Fragment {
    private ViewPager viewPager;

    public ImageView imageView;
    Button ps_write;
    private SensorManager sensorManager;
    private Sensor stepDetectorSensor;
    TextView tvStepDetector;
    private int mStepDetector;

    BroadcastReceiver receiver;
    boolean flag=true;
    Toast toast;
    String serviceData;
    ImageButton btn_setup;

    @Override
    public View onCreateView(final LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userinfoview , container , false);


//        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
//        stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
//        if (stepDetectorSensor == null) {
//            Toast.makeText(getActivity() , "No Step Detect Sensor" , Toast.LENGTH_SHORT).show();
//        }

        TabLayout tabs = view.findViewById(R.id.tabs_userinfo);

        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_location_on2_black_24dp));
        tabs.setTabGravity(tabs.GRAVITY_FILL);
        final ViewPager viewPager = view.findViewById(R.id.userview);

        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        ImageView imageView = (ImageView) view.findViewById(R.id.userimage);

        imageView.setBackground(new ShapeDrawable(new OvalShape()));
        if (Build.VERSION.SDK_INT > 21) {
            imageView.setClipToOutline(true);

        }
        ps_write = (Button) view.findViewById(R.id.ps_write);

        btn_setup=(ImageButton)view.findViewById(R.id.btn_setup);


        receiver=new MyMainLocalReceiver();

        tvStepDetector = (TextView) view.findViewById(R.id.tvStepDetector);


        return view;
    }
    public SensorEventListener mySensorListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            tvStepDetector.setText(Float.toString(event.values[0]));
            Log.i("TAG",Float.toString(event.values[0]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor , int accuracy) {

        }
    };


    class MyMainLocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context , Intent intent) {
            int a =intent.getIntExtra("serviceData", -1);
            serviceData = String.valueOf(a);
            tvStepDetector.setText(serviceData);
            Toast.makeText(getActivity(),"Walking",Toast.LENGTH_SHORT).show();
        }






//여기서부터 서비스와 액티비티 연결
    }
//    private ServiceConnection mConnection=new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName className , IBinder service) {
//            MyStepService.MyStepServiceBinder binder=(MyStepService.MyStepServiceBinder)service;
//            mService= (com.nslb.twipee.User.MyStepService) binder.getService();
//            mService.registerCallback(mCallback);
//        }
//        @Override
//        public void onServiceDisconnected(ComponentName componentName) {
//            mService=null;
//
//        }
//    };
//    private MyStepService.ICallback mCallback=new MyStepService.ICallback(){
//        public void recvData(){
//
//        }
//    };
//    public void startServiceMethod(View v){
//        Intent Service=new Intent(getActivity(),MyStepService.class);
//        bindService(Service,mConnection,Context.BIND_AUTO_CREATE);
//    }
//    mService.myStepServiceFunc();
//기존에 있었던 것,
//    @Override
//    public void onResume() {
//        super.onResume();
//        sensorManager.registerListener(this, stepDetectorSensor, SensorManager.SENSOR_DELAY_UI);
//    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        sensorManager.unregisterListener(this);
//    }
//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        if(event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
//            if(event.values[0] == 1.0f) {
//                mStepDetector++;
//                tvStepDetector.setText(String.valueOf(mStepDetector));
//            }
//        }
//    }
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//    }


}