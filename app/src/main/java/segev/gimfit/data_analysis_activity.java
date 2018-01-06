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



public class data_analysis_activity extends AppCompatActivity {

    Button btnPer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_analysis_layout);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation2);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        btnPer=(Button)findViewById(R.id.permission);
        btnPer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),permission.class);
                startActivity(i);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.navigation_agenda:
                        Toast.makeText(data_analysis_activity.this,"Welcome to Agenda",Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(data_analysis_activity.this, agenda_activity.class);
                        startActivity(intent1);
                        break;


                    case R.id.navigation_dashboard:
                        Toast.makeText(data_analysis_activity.this,"You are on the requested page",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(data_analysis_activity.this, dashboard_activity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_data_analisys:
                        Toast.makeText(data_analysis_activity.this,"You are on the requested page",Toast.LENGTH_SHORT).show();
                        break;
                }


                return false;
            }
        });
    }

}