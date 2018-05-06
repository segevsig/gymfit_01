package segev.gimfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class support_mail extends AppCompatActivity implements View.OnClickListener {

    //Declaring EditText
    private EditText editTextSubject;
    private EditText editTextMessage;

    //Send button
    private ImageButton buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_mail_activity);

        //Initializing the views
        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);

        buttonSend = (ImageButton) findViewById(R.id.buttonSend);

        //Adding click listener
        buttonSend.setOnClickListener(this);
    }


    private void sendEmail() {
        //Getting content for email
        String emailSegevCohen = "segevuni85@gmail.com";
        String emailSegevSigron = "segevsig1@gmail.com";
        String subject = editTextSubject.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, emailSegevCohen, emailSegevSigron, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onClick(View v) {
        sendEmail();
        Intent intent=new Intent(support_mail.this,dashboard_activity.class);
        startActivity(intent);
    }
}
