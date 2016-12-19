package com.example.migue_portatil.bluetoohprueba;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public int REQUEST_ENABLE_BT = 1;
    public ArrayAdapter<String> mArrayAdapter;
    public ListView miLista;
    public BluetoothAdapter mBluetoothAdapter;
    boolean conectado = false;
    public String dmac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miLista = (ListView) findViewById(R.id.list);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        miLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String info = ((TextView) view).getText().toString();
                String direccionMac = info.substring(info.length() - 17);


            }
        });



        //SI No tiene bluetooh el dispositivo
        if (mBluetoothAdapter == null) {
            Toast.makeText(this,"Tu dispositivo no tiene Bluetooh",Toast.LENGTH_LONG).show();
        }else {

            //SI El Bluetooh no esta disponible
            if (!mBluetoothAdapter.isEnabled()) {
                //Lanzar Intent para activarlo
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }else {


                Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
// If there are paired devices
                if (pairedDevices.size() > 0) {
                    // Loop through paired devices
                    for (BluetoothDevice device : pairedDevices) {
                        // Add the name and address to an array adapter to show in a ListView
                        String nombre = device.getName();
                        String dmac = device.getAddress();
                        mArrayAdapter.add(nombre + "\n" + dmac);
                    }

                    miLista.setAdapter(mArrayAdapter);
                }
            }
        }
    }

    public void clickBoton(View v){
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
// If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                String nombre = device.getName();
                String dmac = device.getAddress();
                mArrayAdapter.add(nombre + "\n" + dmac);
            }

            miLista.setAdapter(mArrayAdapter);
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       // super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_ENABLE_BT && resultCode== Activity.RESULT_OK){
            Toast.makeText(this,"El Bluetooh fue activado",Toast.LENGTH_LONG).show();
        }
    }
}
