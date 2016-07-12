package com.jike.baidumaplocate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;


public class MainActivity extends AppCompatActivity {
    public static final int PERMISSION_REQUEST_CODE = 1;
    private boolean isFirstLocate = true;
    private MapView mapView;
    private BaiduMap baiduMap;
    private LocationClient locationClient;
    private Button btnRefresh;
    private double longtitude, latitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        LocationClientOption lco = new LocationClientOption();
        lco.setOpenGps(true);
        lco.setLocationNotify(true);
        lco.setScanSpan(1000);
        lco.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        locationClient = new LocationClient(getApplicationContext());
        locationClient.setLocOption(lco);
        locationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                refreshLocation(bdLocation);
            }
        });
        mapView = (MapView) findViewById(R.id.map_view);
        baiduMap = mapView.getMap();
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationClient.requestLocation();
            }
        });
    }

    private boolean refreshLocation(BDLocation bdLocation) {
        if (bdLocation == null){
            Toast.makeText(this,"Fail To get Last Location",Toast.LENGTH_LONG).show();
            return false;
        }
        longtitude = bdLocation.getLongitude();
        latitude = bdLocation.getLatitude();

        baiduMap.setMyLocationEnabled(true);
        MyLocationData locatData = new MyLocationData.Builder().longitude(longtitude).latitude(latitude).build();
        baiduMap.setMyLocationData(locatData);
        TextView tv = new TextView(this);
        tv.setText("You're Here");
        LatLng pt = new  LatLng(latitude,longtitude);
        InfoWindow infoWindow = new InfoWindow(tv,pt,-50);
        baiduMap.animateMapStatus(
                MapStatusUpdateFactory.newMapStatus(
                        new MapStatus.Builder().target(new LatLng(latitude,longtitude)).zoom(bdLocation.getRadius()).build()));
        Toast.makeText(this,"location Updated. \n"+bdLocation.getLocType(), Toast.LENGTH_LONG).show();
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        locationClient.start();
        locationClient.requestLocation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        locationClient.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
