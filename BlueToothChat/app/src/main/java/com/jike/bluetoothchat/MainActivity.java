package com.jike.bluetoothchat;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, BlueToothConnection.OnReadNewLineListener {
    private static final int REQUEST_ENABLE_BLUETOOTH = 1000;
    private BluetoothAdapter bluetoothAdapter;
    private ListView lvDevices;
    private DeviceListAdapter deviceListAdapter;
    private boolean scanning = false;
    private View viewProgess;
    private BlueToothConnection connection;
    private EditText etInput;
    private TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connection = new BlueToothConnection(this);
        connection.setOnReadNewLineListener(this);
        viewProgess = findViewById(R.id.viewProgess);
        lvDevices = (ListView) findViewById(R.id.lvDevices);
        etInput = (EditText) findViewById(R.id.etChatInput);
        tvOutput = (TextView) findViewById(R.id.tvOuptut);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        deviceListAdapter = new DeviceListAdapter(this, android.R.layout.simple_list_item_1);
        lvDevices.setAdapter(deviceListAdapter);
        lvDevices.setOnItemClickListener(this);
        if (bluetoothAdapter == null) {
            Toast.makeText(MainActivity.this, "您的設備不支持藍牙", Toast.LENGTH_LONG).show();
            finish();
        } else {
            if (!bluetoothAdapter.isEnabled()) {
                requestEnableBlueTooth();
            } else {
                loadBoundedDevice();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ENABLE_BLUETOOTH:
                switch (resultCode) {
                    case RESULT_OK:
                        loadBoundedDevice();
                        break;
                    default:
                        new AlertDialog.Builder(this).setTitle("Warning").setMessage("你拒絕啟用藍牙").setPositiveButton("再次啟用", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestEnableBlueTooth();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).setCancelable(false).show();
                        break;
                }
                break;
        }
    }

    void requestEnableBlueTooth() {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(i, REQUEST_ENABLE_BLUETOOTH);
    }

    void loadBoundedDevice() {
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : bondedDevices) {
            deviceListAdapter.add(new BluetoothDeviceWrapper(device));
        }
    }

    public void btnDiscoverableClick(View view) {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        i.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 120);//120s
        startActivity(i);
    }

    public void btnStartScan(View view) {
        checktoScanDevice();
    }

    private void checktoScanDevice() {
        if (!scanning) {
            registerReceiver(deviceFoundReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
            bluetoothAdapter.startDiscovery();
            showProgess();
            scanning = true;
        }
    }

    public void btnStopScan(View view) {
        checktoStopScanDevice();
    }

    private void checktoStopScanDevice() {
        if (scanning) {
            unregisterReceiver(deviceFoundReceiver);
            bluetoothAdapter.startDiscovery();
            hideProgess();
            scanning = false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        checktoStopScanDevice();
        connection.stopServiceSocket();
    }

    @Override
    protected void onResume() {
        super.onResume();
        connection.startServerSocket();
    }

    void showProgess() {
        viewProgess.setVisibility(View.VISIBLE);
    }

    void hideProgess() {
        viewProgess.setVisibility(View.GONE);
    }

    private BroadcastReceiver deviceFoundReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            deviceListAdapter.add(new BluetoothDeviceWrapper(device));
        }
    };

    public void btnSendLine(View view) {
        if (!TextUtils.isEmpty(etInput.getText())){
            connection.sendLine(etInput.getText().toString());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        connection.connect(deviceListAdapter.getItem(position).getDevice());
    }

    @Override
    public void onRead(final String line, final BluetoothDevice remotedevice) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvOutput.append(remotedevice.getName()+":"+line+"\n");
            }
        });
    }
}
