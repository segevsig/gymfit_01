package segev.gimfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class Biking_activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biking_layout);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_arrow:
                        Intent intent0 = new Intent(Biking_activity.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.navigation_biking:
                        Toast.makeText(Biking_activity.this,"You are on the requested page",Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.navigation_running:
                        Toast.makeText(Biking_activity.this,"Creating running workout",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(Biking_activity.this, Running_activity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_gym:
                        Toast.makeText(Biking_activity.this,"Creating gym workout",Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(Biking_activity.this, Gym_activity.class);
                        startActivity(intent3);
                        break;
                }


                return false;
            }
        });
    }

}