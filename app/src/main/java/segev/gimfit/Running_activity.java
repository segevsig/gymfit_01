package segev.gimfit;

/**
 * Created by LENOVO on 24/12/2017.
 */

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.design.widget.BottomNavigationView;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.ImageView;
        import android.widget.SeekBar;
        import android.widget.TextView;
        import android.widget.Toast;


public class Running_activity extends AppCompatActivity {
    private ImageView boxDistanceId;
    private TextView headLineBoxText;
    private SeekBar runningSeekId;
    private TextView runningDistanceKmId;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.running_layout);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        boxDistanceId =(ImageView) findViewById(R.id.boxDistanceId);
        headLineBoxText = (TextView) findViewById(R.id.headLineBoxText);
        runningSeekId = (SeekBar)findViewById(R.id.runningSeekId) ;
        runningDistanceKmId = (TextView)findViewById(R.id.runningDistanceKmId);

        runningSeekId.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0 ;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressSeek, boolean fromUser) {
                progress = progressSeek ;
                runningDistanceKmId.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                runningDistanceKmId.setText(String.valueOf(progress));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                runningDistanceKmId.setText(String.valueOf(progress));


            }
        });
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.navigation_running:
                        Toast.makeText(Running_activity.this,"You are on the requested page ",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.navigation_biking:
                        Toast.makeText(Running_activity.this,"Creating biking workout",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(Running_activity.this, Biking_activity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_gym:
                        Toast.makeText(Running_activity.this,"Creating gym workout",Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(Running_activity.this, Gym_activity.class);
                        startActivity(intent3);
                        break;
                }


                return false;
            }
        });
    }

}