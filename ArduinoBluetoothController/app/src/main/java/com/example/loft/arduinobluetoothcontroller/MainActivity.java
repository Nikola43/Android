package com.example.loft.arduinobluetoothcontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;
import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends Activity  {
    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice>dispositivosSincronizados;
    ListView listView;
    String nombreDispositivo;
    String macDispositivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        listView = (ListView)findViewById(R.id.listView);
    }

    public void activarBluetooh(View v){
        if (!bluetoothAdapter.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Bluetooth activado",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth ya está activado", Toast.LENGTH_LONG).show();
        }
    }

    public void desactivarBluetooth(View v){
        bluetoothAdapter.disable();
        Toast.makeText(getApplicationContext(), "Turned off" ,Toast.LENGTH_LONG).show();
    }

    public  void activarVisibilidad(View v){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }


    public void mostrarDispositivosSincronizados(View v){
        dispositivosSincronizados = bluetoothAdapter.getBondedDevices();

        ArrayList list = new ArrayList();


        for(BluetoothDevice bluetoothDevice : dispositivosSincronizados)
        {
            nombreDispositivo = bluetoothDevice.getName();
            macDispositivo = bluetoothDevice.getAddress();

            list.add(nombreDispositivo);
        }
        Toast.makeText(getApplicationContext(), "Dispositivos sincronizados",Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new  ArrayAdapter(this,android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
    }
}