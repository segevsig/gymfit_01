package segev.gimfit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Create_workout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_workout);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.navigation_running:
                        Toast.makeText(Create_workout.this,"Creating running workout",Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(Create_workout.this, Running_activity.class
                        );
                        startActivity(intent1);
                        break;

                    case R.id.navigation_biking:
                        Toast.makeText(Create_workout.this,"Creating biking workout",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(Create_workout.this, Biking_activity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_gym:
                        Toast.makeText(Create_workout.this,"Creating gym workout",Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(Create_workout.this, Gym_activity.class);
                        startActivity(intent3);
                        break;
                }


                return false;
            }
        });

    }


}