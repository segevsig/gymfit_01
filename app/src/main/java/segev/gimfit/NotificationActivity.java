package segev.gimfit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by segevcohen on 12/05/2018.
 */

public class NotificationActivity extends AppCompatActivity{

    private TextView mNotifyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        String dataMessage = getIntent().getStringExtra("message");
        String dataFrom = getIntent().getStringExtra("from_user");

        mNotifyData = (TextView) findViewById(R.id.notifiy_text);

        mNotifyData.setText("FROM : " + dataFrom + " MESSAGE : " + dataMessage );

    }
}
