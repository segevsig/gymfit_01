package segev.gimfit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.concurrent.TimeUnit;

import static com.google.android.gms.common.Scopes.FITNESS_ACTIVITY_READ_WRITE;
import static com.google.android.gms.common.Scopes.FITNESS_BODY_READ_WRITE;
import static com.google.android.gms.common.Scopes.FITNESS_LOCATION_READ_WRITE;
import static com.google.android.gms.common.Scopes.FITNESS_NUTRITION_READ_WRITE;


public class athlete_area_playground extends AppCompatActivity implements
        ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {


    private boolean fabExpanded = false;
    private FloatingActionButton fabSettings;
    private LinearLayout layoutFabAddEvent;
    private LinearLayout layoutFabSync;
    private LinearLayout layoutFabLogOut;
    private GoogleApiClient mGoogleApiClient;
    private String steps;
    private String cal;
    private String HrBpm;
    private String Distance;
    private TextView text_1;
    private TextView text_2;
    private TextView text_3;
    private Button syncButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.athlete_layout_playground);
        fabSettings = (FloatingActionButton) this.findViewById(R.id.fabSetting);
        layoutFabAddEvent = (LinearLayout) this.findViewById(R.id.layoutFabEvent);
        layoutFabSync = (LinearLayout) this.findViewById(R.id.layoutFabSync);
        layoutFabLogOut = (LinearLayout) this.findViewById(R.id.layoutFabLogOut);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(FITNESS_ACTIVITY_READ_WRITE))
                .addScope(new Scope(FITNESS_BODY_READ_WRITE))
                .addScope(new Scope(FITNESS_LOCATION_READ_WRITE))
                .addScope(new Scope(FITNESS_NUTRITION_READ_WRITE))
                .addConnectionCallbacks(this)
                .enableAutoManage(this, 0, this)
                .build();
        mGoogleApiClient.connect();
        fabSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabExpanded == true){
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
            }
        });
        layoutFabAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(athlete_area_playground.this,add_session.class);
                startActivity(intent);
            }
        });
        layoutFabLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(athlete_area_playground.this,MainActivity.class);
                startActivity(intent);
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });
        layoutFabSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new ViewTodaysStepCountTask().execute();
            }
        });
        //Only main FAB is visible in the beginning
        closeSubMenusFab();

    }


    public void onConnected(@Nullable Bundle bundle) {
        Log.e("HistoryAPI", "onConnected");
    }

    //In use, call this every 30 seconds in active mode, 60 in ambient on watch faces
    private void displayStepDataForToday() {
        DailyTotalResult stepsToday = Fitness.HistoryApi.readDailyTotal( mGoogleApiClient, DataType.TYPE_STEP_COUNT_DELTA ).await(1, TimeUnit.MINUTES);
        steps = showDataSet(stepsToday.getTotal());
        DailyTotalResult caloriesBurned = Fitness.HistoryApi.readDailyTotal(mGoogleApiClient, DataType.TYPE_CALORIES_EXPENDED).await(1, TimeUnit.MINUTES);
        cal = showDataSet(caloriesBurned.getTotal());
        DailyTotalResult heartRate = Fitness.HistoryApi.readDailyTotal( mGoogleApiClient, DataType.TYPE_HEART_RATE_BPM).await(1, TimeUnit.MINUTES);
        HrBpm = showDataSet(heartRate.getTotal());
        DailyTotalResult distanceToday = Fitness.HistoryApi.readDailyTotal( mGoogleApiClient, DataType.TYPE_DISTANCE_DELTA).await(1, TimeUnit.MINUTES);
        Distance = showDataSet(distanceToday.getTotal());
    }

    private String showDataSet(DataSet dataSet) {
        Log.e("Historysegev", "Data returned for Data type: " + dataSet.getDataType().getName());
        DateFormat dateFormat = DateFormat.getDateInstance();
        DateFormat timeFormat = DateFormat.getTimeInstance();
        String counter = " ";
        for (DataPoint dp : dataSet.getDataPoints()) {
            Log.e("HistoryNiv", "Data point:");
            Log.e("HistoryNiv", "\tType: " + dp.getDataType().getName());
            Log.e("HistoryNiv", "\tStart: " + dateFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)) + " " + timeFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
            Log.e("HistoryNiv", "\tEnd: " + dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)) + " " + timeFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
            for(Field field : dp.getDataType().getFields()) {
                Log.e("HistoryNiv", "\tField: " + field.getName() +
                        " Value: " + dp.getValue(field));
                counter = dp.getValue(field).toString();

            }
        }
        return counter;
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("HistoryAPI", "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("HistoryAPI", "onConnectionFailed");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private class ViewTodaysStepCountTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            displayStepDataForToday();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            text_1 = (TextView)findViewById(R.id.stepsTextView) ;
            text_1.setText(steps);
            text_1.setVisibility(View.VISIBLE);
            text_2 = (TextView)findViewById(R.id.caloriesTextView);
            text_2.setText(cal);
            text_2.setVisibility(View.VISIBLE);
            text_3 = (TextView)findViewById(R.id.distanceTextView);
            text_3.setText(Distance);
            text_3.setVisibility(View.VISIBLE);


        }
    }
    //closes FAB submenus
    private void closeSubMenusFab(){
        layoutFabAddEvent.setVisibility(View.INVISIBLE);
        layoutFabSync.setVisibility(View.INVISIBLE);
        layoutFabLogOut.setVisibility(View.INVISIBLE);
        fabSettings.setImageResource(R.drawable.facebuttom);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab(){
        layoutFabAddEvent.setVisibility(View.VISIBLE);
        layoutFabSync.setVisibility(View.VISIBLE);
        layoutFabLogOut.setVisibility(View.VISIBLE);
        //Change settings icon to 'X' icon
        fabSettings.setImageResource(R.drawable.closebutton);
        fabExpanded = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
