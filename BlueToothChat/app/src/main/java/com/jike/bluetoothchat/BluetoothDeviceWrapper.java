package com.jike.bluetoothchat;

import android.bluetooth.BluetoothDevice;

/**
 * Created by Administrator on 6/29/2016.
 */
public class BluetoothDeviceWrapper {
    private final BluetoothDevice device;

    public BluetoothDeviceWrapper(BluetoothDevice device) {
        this.device = device;
    }

    public BluetoothDevice getDevice() {
        return device;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            assert o instanceof BluetoothDeviceWrapper;
            return ((BluetoothDeviceWrapper) o).getDevice().getAddress().equals(getDevice().getAddress());//兩個對像的MAC地址若相等
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s\n%s", getDevice().getName(), getDevice().getAddress());
    }
}
