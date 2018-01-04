package segev.gimfit;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.firebase.client.Firebase;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.api.services.calendar.Calendar;

import static com.google.android.gms.common.Scopes.FITNESS_ACTIVITY_READ_WRITE;
import static com.google.android.gms.common.Scopes.FITNESS_BODY_READ_WRITE;
import static com.google.android.gms.common.Scopes.FITNESS_LOCATION_READ_WRITE;
import static com.google.android.gms.common.Scopes.FITNESS_NUTRITION_READ_WRITE;

/**
 * Created by LENOVO on 02/12/2017.
 */

public class Gimfit extends Application implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private GoogleApiClient mGoogleApiClient2;

    public Calendar getService() {
        return service;
    }

    public void setService(Calendar service) {
        this.service = service;
    }

    public static Calendar service;
    private static final String PREF_ACCOUNT_NAME = "accountName";


    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(FITNESS_ACTIVITY_READ_WRITE))
                .addScope(new Scope(FITNESS_BODY_READ_WRITE))
                .addScope(new Scope(FITNESS_LOCATION_READ_WRITE))
                .addScope(new Scope(FITNESS_NUTRITION_READ_WRITE))
                .addConnectionCallbacks(this)
                .build();

        mGoogleApiClient.connect();








    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}










