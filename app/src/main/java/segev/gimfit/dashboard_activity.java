/**
 * Created by segevcohen on 26/12/2017.
 */

package segev.gimfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class dashboard_activity extends AppCompatActivity {

    Button create_single;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation2);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        create_single = (Button)findViewById(R.id.button5);
        create_single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent0 = new Intent(dashboard_activity.this, Running_activity.class);
                startActivity(intent0);
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.navigation_agenda:
                        Toast.makeText(dashboard_activity.this,"Welcome to Agenda",Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(dashboard_activity.this, agenda_activity.class);
                        startActivity(intent1);
                        break;


                    case R.id.navigation_dashboard:
                        Toast.makeText(dashboard_activity.this,"You are on the requested page",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.navigation_data_analisys:
                        Toast.makeText(dashboard_activity.this,"welcome to Data analisys",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(dashboard_activity.this, data_analysis_activity.class);
                        startActivity(intent2);
                        break;
                }


                return false;
            }
        });
    }

}