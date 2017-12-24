package segev.gimfit;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Blueooth_conction extends AppCompatActivity {
    private static final String TAG ="MainActivity" ;
    BluetoothAdapter nBluetoothAdapter;

    protected void onDestroy(){
        Log.d(TAG,"onDestroy:called.");
        super.onDestroy();
        unregisterReceiver(nBroadcastReceiver1);
    }
    private final BroadcastReceiver nBroadcastReceiver1 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(nBluetoothAdapter.ACTION_STATE_CHANGED)) {

                final int state =intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,nBluetoothAdapter.ERROR);

                switch (state){
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG,"0nreceive:STATE_OFF");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d(TAG,"nBroadcastReceiver1:STATE_TURNING_OFF");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG,"nBroadcastReceiver1:STATE_ON");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d(TAG,"nBroadcastReceiver1:STATE_TURNING_ON");
                        break;
                }
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blueooth_conction);
        Button btonOfBT =(Button) findViewById(R.id.btonOfBT);
        nBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        btonOfBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick:enabling/disabling bluetooth. ");
                enableDisableBT();
            }
        });
    }
    public void enableDisableBT() {
        if (nBluetoothAdapter==null){
            Log.d(TAG,"enableDisable:DOES NOT HAVE BT ");
        }
        if(!nBluetoothAdapter.isEnabled()){
            Log.d(TAG,"enableDisable:enable BT ");
            Intent enableBtintent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBtintent);

            IntentFilter BTIntent=new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(nBroadcastReceiver1,BTIntent);
        }
        if (nBluetoothAdapter.isEnabled()){
            Log.d(TAG,"enableDisable:disable BT ");
            nBluetoothAdapter.disable();

            IntentFilter BTIntent=new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(nBroadcastReceiver1,BTIntent);
        }
    }
}
