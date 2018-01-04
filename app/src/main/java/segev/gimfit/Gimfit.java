package segev.gimfit;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.Fitness;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.calendar.CalendarScopes;

import java.util.Arrays;

/**
 * Created by LENOVO on 02/12/2017.
 */

public class Gimfit extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        GoogleAccountCredential mCredential;
         final String[] SCOPES = { CalendarScopes.CALENDAR };

        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
         com.google.api.services.calendar.Calendar mService = null;

        mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());

      //  mService = new com.google.api.services.calendar.Calendar.Builder(
        //        transport, jsonFactory, credential)
          //      .setApplicationName("Google Calendar API Android Quickstart")
            //    .build();
    }
}
