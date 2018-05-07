package segev.gimfit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class login extends AppCompatActivity {
    User_app Userapp=new User_app();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences("gimfit", 0);
        SharedPreferences.Editor sedt = sp.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.button_female).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Userapp.setGender("female");
                Intent intent = new Intent(login.this, login_step2.class);
                intent.putExtra("myobj",Userapp);
                startActivity(intent);
            }
        });
        findViewById(R.id.button_male).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Userapp.setGender("male");
                Intent intent = new Intent(login.this, login_step2.class);
                intent.putExtra("myobj",Userapp);
                startActivity(intent);
            }
        });
    }

    }


