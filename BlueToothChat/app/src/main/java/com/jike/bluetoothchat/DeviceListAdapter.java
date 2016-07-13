package com.jike.bluetoothchat;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.view.InputDevice.getDevice;

/**
 * Created by Administrator on 6/29/2016.
 */
public class DeviceListAdapter extends BaseAdapter {
    private final Context context;
    private final int cellResourceId;
    private List<BluetoothDeviceWrapper> item = new ArrayList<>();

    public DeviceListAdapter(Context context, int cellResourceId) {
        this.context = context;
        this.cellResourceId = cellResourceId;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public BluetoothDeviceWrapper getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(cellResourceId, null);
        }
        assert convertView instanceof TextView;
        ((TextView) convertView).setText(getItem(position).toString());
        return convertView;
    }

    public void add(BluetoothDeviceWrapper bluetoothDeviceWrapper) {
        if (!item.contains(bluetoothDeviceWrapper)) {
            item.add(bluetoothDeviceWrapper);
            notifyDataSetChanged();
        }
    }
}
