package segev.gimfit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class sendnotifiction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String trineeName=getIntent().getStringExtra("name").toString();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendnotifiction);
        TextView nametranee=findViewById(R.id.name_trainee);
        nametranee.setText(trineeName);

    }
}
