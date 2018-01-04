package segev.gimfit;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.android.gms.fitness.Fitness;

/**
 * Created by LENOVO on 02/12/2017.
 */

public class Gimfit extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
