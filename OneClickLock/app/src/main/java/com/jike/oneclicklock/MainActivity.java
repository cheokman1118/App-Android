package com.jike.oneclicklock;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int DEVICE_POLICY_MANAGER_REQUEST_CODE = 21;
    private Button btnGet,btnUn,btnLock;
    private DevicePolicyManager devicePolicyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);

        btnGet = (Button) findViewById(R.id.btnGetPerm);
        btnUn = (Button) findViewById(R.id.btnUnPerm);
        btnLock = (Button) findViewById(R.id.btnlock);
        btnGet.setOnClickListener(this);
        btnUn.setOnClickListener(this);
        btnLock.setOnClickListener(this);

        if (devicePolicyManager.isAdminActive(new ComponentName(this,DeviceManagerBR.class))){
            lockPage();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGetPerm:
                Intent i = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                i.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,new ComponentName(this,DeviceManagerBR.class));
                startActivityForResult(i, DEVICE_POLICY_MANAGER_REQUEST_CODE);
                break;
            case R.id.btnUnPerm:
                devicePolicyManager.removeActiveAdmin(new ComponentName(this,DeviceManagerBR.class));
                adminPage();
                break;

            case R.id.btnlock:
                devicePolicyManager.lockNow();
                break;
        }
    }
    public void adminPage(){
        btnLock.setVisibility(View.GONE);
        btnUn.setVisibility(View.GONE);
        btnGet.setVisibility(View.VISIBLE);
    }
    public void lockPage(){
        btnGet.setVisibility(View.GONE);
        btnUn.setVisibility(View.VISIBLE);
        btnLock.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case RESULT_OK:
                lockPage();
                break;
            case RESULT_CANCELED:
                break;
        }
    }
}
