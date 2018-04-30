package segev.gimfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class list_event_for_traning extends AppCompatActivity {
    Intent intent = getIntent();

    String name_of_traning=intent.getStringExtra("name");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event_for_traning);


    }
}
