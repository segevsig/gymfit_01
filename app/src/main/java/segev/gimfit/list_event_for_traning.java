package segev.gimfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class list_event_for_traning extends AppCompatActivity {

    String name_of_traning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event_for_traning);
       name_of_traning= getIntent().getStringExtra("name").toString();
        Toast.makeText(list_event_for_traning.this,name_of_traning,Toast.LENGTH_LONG).show();


    }
}
