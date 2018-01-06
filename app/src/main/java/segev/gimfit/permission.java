package segev.gimfit;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class permission extends AppCompatActivity implements View.OnClickListener {

    private Button btnBodySensors;
    private Button btnStorage;
    private Button btnLocation;
    private Button btnGetAccounts;
    private Button btnCalender;

    private static final int MY_PERMISSIONS_REQUEST = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission_layout);

        initialize();
    }

    private void initialize() {

        btnBodySensors = (Button) findViewById(R.id.permission_btn_body_sensors);
        btnStorage = (Button) findViewById(R.id.permission_btn_storage);
        btnGetAccounts = (Button) findViewById(R.id.permission_btn_accounts);
        btnLocation = (Button) findViewById(R.id.permission_btn_location);
        btnCalender = (Button) findViewById(R.id.permission_btn_calender);

        //onClick of this event

        btnBodySensors.setOnClickListener(this);
        btnStorage.setOnClickListener(this);
        btnGetAccounts.setOnClickListener(this);
        btnLocation.setOnClickListener(this);
        btnCalender.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        if (v == btnBodySensors) {
            requestPermission(new String[]{Manifest.permission.BODY_SENSORS});
        } else if (v == btnStorage) {
            requestPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE});
        } else if (v == btnGetAccounts) {
            requestPermission(new String[]{Manifest.permission.GET_ACCOUNTS});
        } else if (v == btnLocation) {
            requestPermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION});
        } else if (v == btnCalender) {
            requestPermission(new String[]{Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR});
        }
    }

    /**
     * This is common method for requesting any kind of permissions
     *
     * @param permission
     */
    public void requestPermission(String[] permission) {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                permission[0])
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    permission[0])) {



        Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
            } else {

                ActivityCompat.requestPermissions(this,
                        permission,
                        MY_PERMISSIONS_REQUEST);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Granted permission...", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();

                }
                return;
            }

        }
    }
}