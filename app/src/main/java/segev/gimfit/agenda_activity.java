/**
 * Created by segevcohen on 26/12/2017.
 */

package segev.gimfit;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.constraint.ConstraintLayout;
        import android.support.design.widget.BottomNavigationView;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.MotionEvent;
        import android.widget.Toast;



public class agenda_activity extends AppCompatActivity {
    float x1,x2,y1,y2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda_layout);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation2);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.navigation_agenda:
                        Toast.makeText(agenda_activity.this,"You are on the requested page",Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.navigation_dashboard:
                        Toast.makeText(agenda_activity.this,"Welcome to Dashboard",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(agenda_activity.this, dashboard_activity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_data_analisys:
                        Toast.makeText(agenda_activity.this,"Welcome to Data analisys",Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(agenda_activity.this, data_analysis_activity.class);
                        startActivity(intent3);
                        break;
                }


                return false;
            }
        });
    }
/*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
        switch (event.getAction()){
            case   event.ACTION_DOWN:
                x1 = event.

        }*/
    }

