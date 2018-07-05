package com.zhouwenguang.hz.bluetoothble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter mBluetoothAdapter;
    String uuid1 = "a90924c1-47bd-43ae-a5f4-c3a732830f33";
    String uuid2 = "024105f1-11e0-4482-9306-ad8923e8b4e8";
    String uuid3 = "2c3d5aba-6aa5-4f11-b19d-37c584e00d0c";
    private ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1);
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "不支持低功耗蓝牙", Toast.LENGTH_SHORT).show();
            finish();
        }
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 0x0001);
        }
        if (mBluetoothAdapter.isEnabled()) {
//            UUID UUID1=UUID.fromString(uuid1);
//            UUID UUID2=UUID.fromString(uuid2);
//            UUID UUID3=UUID.fromString(uuid3);
//            UUID[] uuids=new UUID[3];
            mBluetoothAdapter.startLeScan(leScanCallback);

        }



    }

    BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            arrayAdapter.add(device.getName() + "\n" + device.getAddress());
        }
    };
}
