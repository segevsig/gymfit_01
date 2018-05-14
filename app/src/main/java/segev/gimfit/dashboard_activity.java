/**
 * Created by segevcohen on 26/12/2017.
 */

package segev.gimfit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.google.android.gms.common.Scopes.FITNESS_ACTIVITY_READ_WRITE;
import static com.google.android.gms.common.Scopes.FITNESS_BODY_READ_WRITE;
import static com.google.android.gms.common.Scopes.FITNESS_LOCATION_READ_WRITE;
import static com.google.android.gms.common.Scopes.FITNESS_NUTRITION_READ_WRITE;


public class dashboard_activity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks
        ,GoogleApiClient.OnConnectionFailedListener {

    private String steps;
    private String cal;
    private String Distance;
    private TextView stepsText;
    private TextView caloriesText;
    private TextView distanceText;
    private TextView userName;
    private ImageButton setting;
    private ImageButton createSingleWorkout;
    private ImageButton coacherGroupWorkout;
    private ImageButton coacherNotes;
    private ImageButton coacherMessaging;
    private ImageButton coacherAthletesList;
    private ImageButton coacherSupport;
    private ImageButton coacherLogout;
    private FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    private String mUserId;

    private GoogleApiClient mGoogleApiClient;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
        mAuth  = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mUserId = mAuth.getCurrentUser().getUid();
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation2);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
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
        userName = (TextView)findViewById(R.id.userName);
        coacherSupport = (ImageButton) this.findViewById(R.id.coacherSupport) ;
        coacherSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_activity.this, support_mail.class);
                startActivity(intent);
            }
        });
        coacherAthletesList=(ImageButton) this.findViewById(R.id.coacherAthletesList);
        coacherAthletesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(dashboard_activity.this,Athletes_List.class);
                startActivity(intent);
            }
        });

        coacherMessaging=(ImageButton) this.findViewById(R.id.coacherMessaging);
        coacherMessaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(dashboard_activity.this,messaging_screen.class);
                startActivity(intent);
            }
        });
        setting = (ImageButton) this.findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_activity.this, permission.class);
                startActivity(intent);
                finish();
            }
        });
        coacherLogout = (ImageButton) this.findViewById(R.id.coacherLogout);
        coacherLogout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Map <String,Object> tokenMapRemove = new HashMap<>();
             tokenMapRemove.put("token_id", FieldValue.delete());
             mFirestore.collection("Users").
                     document(mUserId).update(tokenMapRemove).
                     addOnSuccessListener(new OnSuccessListener<Void>() {
                         public void onSuccess(Void aVoid) {

                         }
                     });
             mAuth.signOut();
             Intent intent3 = new Intent(dashboard_activity.this, MainActivity.class);
             startActivity(intent3);
         }
     });



        createSingleWorkout = (ImageButton)findViewById(R.id.coacherSingleWorkout);
        createSingleWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent0 = new Intent(dashboard_activity.this, list_tranine_forWorkout.class);
                startActivity(intent0);
            }
        });
       new ViewTodaysStepCountTask().execute();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Coach")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("fullName");
        ref.addValueEventListener(new ValueEventListener() {
            String full_name;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               full_name = dataSnapshot.getValue().toString();
               userName.setText(full_name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.navigation_agenda:
                        Intent intent1 = new Intent(dashboard_activity.this, agenda_activity.class);
                        startActivity(intent1);
                        break;


                    case R.id.navigation_dashboard:
                        break;

                    case R.id.navigation_data_analisys:
                        Intent intent2 = new Intent(dashboard_activity.this, data_analysis_activity.class);
                        startActivity(intent2);
                        break;
                }


                return false;
            }
        });
    }

    public void onConnected(@Nullable Bundle bundle) {
        Log.e("HistoryAPI", "onConnected");
    }

    //In use, call this every 30 seconds in active mode, 60 in ambient on watch faces
    private void displayStepDataForToday() {
        DailyTotalResult stepsToday = Fitness.HistoryApi.readDailyTotal(mGoogleApiClient, DataType.TYPE_STEP_COUNT_DELTA).await(1, TimeUnit.MINUTES);
        steps = showDataSet(stepsToday.getTotal());
        DailyTotalResult caloriesBurned = Fitness.HistoryApi.readDailyTotal(mGoogleApiClient, DataType.TYPE_CALORIES_EXPENDED).await(1, TimeUnit.MINUTES);
        cal = showDataSet(caloriesBurned.getTotal());
        DailyTotalResult distanceToday = Fitness.HistoryApi.readDailyTotal(mGoogleApiClient, DataType.TYPE_DISTANCE_DELTA).await(1, TimeUnit.MINUTES);
        Distance = showDataSet(distanceToday.getTotal());
    }



    private String showDataSet(DataSet dataSet) {
        Log.e("Historysegev", "Data returned for Data type: " + dataSet.getDataType().getName());
        DateFormat dateFormat = DateFormat.getDateInstance();
        DateFormat timeFormat = DateFormat.getTimeInstance();
        String counter = " ";
        for (DataPoint dp : dataSet.getDataPoints()) {
            Log.e("Historysegev", "Data point:");
            Log.e("Historysegev", "\tType: " + dp.getDataType().getName());
            Log.e("Historysegev", "\tStart: " + dateFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)) + " " + timeFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
            Log.e("Historysegev", "\tEnd: " + dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)) + " " + timeFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
            for (Field field : dp.getDataType().getFields()) {
                Log.e("Historysegev", "\tField: " + field.getName() +
                        " Value: " + dp.getValue(field));
                counter = dp.getValue(field).toString();

            }
        }
        return counter;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.dashboard_settings) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(dashboard_activity.this,MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class  ViewTodaysStepCountTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            displayStepDataForToday();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            stepsText= (TextView) findViewById(R.id.stepsText);
            stepsText.setText(steps);
            stepsText.setVisibility(View.VISIBLE);
            caloriesText = (TextView) findViewById(R.id.caloriesText);
            caloriesText.setText(cal);
            caloriesText.setVisibility(View.VISIBLE);
            distanceText = (TextView) findViewById(R.id.distanceText);
            distanceText.setText(Distance);
            distanceText.setVisibility(View.VISIBLE);



        }
    }
    @Override
    public void onConnectionSuspended(int i) {
        Log.e("HistoryAPI", "onConnectionSuspended");
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("HistoryAPI", "onConnectionFailed");
    }

}