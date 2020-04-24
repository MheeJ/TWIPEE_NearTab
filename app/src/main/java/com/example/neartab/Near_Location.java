package com.example.neartab;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;

public class Near_Location extends AppCompatActivity {
    public double latitude; //현위치위도
    public double longitude; //현위치경도
    private ListView listView;
    public TextView n_address;
    private TextView Address_info;
    public double latitudeX; //검색위도
    public double longitudeY;//검색경도
    public String name;//검색이름
    public String address;//현위치
    private ArrayList<String> mArrayMarkerID;
    private static int mMarkerID;
    public TMapView tmapview;
    private String location;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.near_location);

        mArrayMarkerID = new ArrayList<>();
        mMarkerID = 0;
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.map_view);
        n_address = (TextView) findViewById(R.id.n_address);
        Address_info = (TextView)findViewById(R.id.address_info);
        tmapview = new TMapView(this);
        tmapview.setSKTMapApiKey("f14d574a-63eb-409b-8a59-8f895318bcdb");
        tmapview.setZoomLevel(15);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setTrackingMode(true);
        tmapview.setSightVisible(true);
        linearLayout.addView(tmapview);

        getCurrentPosition();

        tmapview.setCenterPoint(longitude, latitude); //->현재위치 = centerpoint
        tmapview.setLocationPoint(longitude, latitude);

        makegeocoder();

        if(address != null) {
            findAllPoi(address + location); //-> 화장실 검색
        }
    }

    public void getCurrentPosition() {
        Intent intent = getIntent();
        longitude = intent.getExtras().getDouble("My_longitude_double");
        latitude = intent.getExtras().getDouble("My_latitude_double");
        location = intent.getExtras().getString("location");
    }

    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {}
        public void onProviderEnabled(String provider) {}
        public void onProviderDisabled(String provider) {}
    };

    public void findAllPoi(String strData) {
        TMapData tmapdata = new TMapData();
        tmapdata.findAllPOI(strData, new TMapData.FindAllPOIListenerCallback() {
            @Override
            public void onFindAllPOI(ArrayList<TMapPOIItem> poiItem) {
                for (int i = 0; i < poiItem.size(); i++) {
                    TMapPOIItem item = poiItem.get(i);
                    name = item.getPOIName();
                    latitudeX = item.getPOIPoint().getLatitude();
                    longitudeY = item.getPOIPoint().getLongitude();
                    showmarker(poiItem);
                }
            }
        });
    }

    public void showmarker(ArrayList<TMapPOIItem> poiItem) {
        Bitmap bitmap = null;
        bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.i_location);
        for (int i = 0; i < poiItem.size(); i++) {
            TMapMarkerItem markerItem1 = new TMapMarkerItem();
            markerItem1.setIcon(bitmap);
            markerItem1.setTMapPoint(poiItem.get(i).getPOIPoint());
            tmapview.addMarkerItem("markerItem" + i, markerItem1);
            String strID = String.format("pmarker%d", mMarkerID++);
            mArrayMarkerID.add(strID);
        }
    }
    //
    public void makegeocoder(){
        final Geocoder geocoder = new Geocoder(this);
        List<Address> list = null;
        try
        {
            list = geocoder.getFromLocation(latitude, longitude, 5);
        } catch(IOException e)
        {
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        if(list !=null)
        {
            if (list.size() == 0) {
                n_address.setText("해당되는 주소 정보는 없습니다");
                Address_info.setText("");
            } else {
                n_address.setText(list.get(0).toString());
                String cut[] = list.get(0).toString().split(" ");
                for (int i = 0; i < cut.length; i++) {
                    System.out.println("cut[" + i + "] : " + cut[i]);
                }
                n_address.setText(cut[2] + " ");
                address = n_address.getText().toString();
                Address_info.setText("주변"+location+"위치");
            }
        }
    }
}

