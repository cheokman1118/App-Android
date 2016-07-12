package com.jike.bluetoothchat;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by Administrator on 6/30/2016.
 */
public class BlueToothConnection {
    private static final String NAME = "BlueToothChat";
    private static final UUID MY_UUID = UUID.fromString("aae8841b-5ecd-4650-aed7-4714042d268a");
    private final Activity context;
    private boolean listenning = true;
    private AcceptThread acceptThread = null;
    private ManageConnetionThread manageConnetionThread;
    private OnReadNewLineListener onReadNewLineListeren;

    public BlueToothConnection(Activity context) {
        this.context = context;
    }

    public void startServerSocket() {
        if (acceptThread == null) {
            acceptThread = new AcceptThread();
            acceptThread.start();
        }
    }

    public void stopServiceSocket() {
        if (acceptThread != null) {
            acceptThread.cancel();
            acceptThread = null;
        }
    }

    public void manageConnetion(BluetoothSocket socket) {
        manageConnetionThread = new ManageConnetionThread(socket);
        manageConnetionThread.start();
    }

    public OnReadNewLineListener getOnReadNewLineListener() {
        return onReadNewLineListeren;
    }

    public void setOnReadNewLineListener(OnReadNewLineListener onReadNewLineListener) {
        this.onReadNewLineListeren = onReadNewLineListener;
    }

    public void connect(BluetoothDevice device) {
        stopServiceSocket();
        new ConnectThread(device).start();
    }

    class ConnectThread extends Thread {
        private final BluetoothDevice device;
        private BluetoothSocket socket = null;

        public ConnectThread(BluetoothDevice device) {
            this.device = device;
            try {
                socket = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            super.run();
            if (socket == null) {
                return;
            }
            try {
                socket.connect();
                manageConnetion(socket);
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"成功連結",Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"Cannot Create Connection",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public void sendLine(String line) {
        if (manageConnetionThread != null) {
            manageConnetionThread.sendLine(line);
        }


    }

    class ManageConnetionThread extends Thread {
        private BluetoothSocket socket;
        private InputStream in;
        private OutputStream out;

        public ManageConnetionThread(BluetoothSocket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            try {
                out = socket.getOutputStream();
                in = socket.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (getOnReadNewLineListener() != null) {
                        getOnReadNewLineListener().onRead(line,socket.getRemoteDevice());
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            cancel();
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "Cancel Connection", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void cancel() {
            try {
                socket.close();
                manageConnetionThread = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendLine(String line) {
            line += "\n";
            try {
                out.write(line.getBytes("UTF-8"));
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class AcceptThread extends Thread {
        private BluetoothAdapter bluetoothAdapter;
        private BluetoothServerSocket serviceSocket = null;

        public AcceptThread() {
            listenning = true;
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            try {
                serviceSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord(BlueToothConnection.NAME, BlueToothConnection.MY_UUID);
                System.out.println("success to listen");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            super.run();
            if (serviceSocket == null) {
                return;
            }
            BluetoothSocket socket = null;
            while (listenning) {
                try {
                    socket = serviceSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                    if (listenning) {
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Cannot Connect", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                if (socket != null) {
                    cancel();
                    manageConnetion(socket);
                }
            }
        }

        public void cancel() {
            if (serviceSocket == null) {
                return;
            }
            listenning = false;
            try {
                serviceSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    interface OnReadNewLineListener {
        void onRead(String line,BluetoothDevice remotedevice);
    }
}
