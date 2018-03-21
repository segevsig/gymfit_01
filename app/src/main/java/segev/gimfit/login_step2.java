package segev.gimfit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.graphics.Color.RED;


public class login_step2 extends AppCompatActivity {
    String email;
    String fullName;
    String userName;
    String password;
    int num=0;
    FirebaseAuth mAuth;
    private Firebase mRoot;

User_app Userapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_step2);
        mAuth = FirebaseAuth.getInstance();
        Userapp=(User_app) getIntent().getSerializableExtra("myobj");

       final Button button=findViewById(R.id.button_nextStepTo3);
       final Button button1=findViewById(R.id.button_CoachSignUp);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num=InValid();
                if (num==4){
                    Intent intent = new Intent(login_step2.this, login_step3.class);
                    intent.putExtra("myobj",Userapp);
                    startActivity(intent);
                }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num=InValid();
                if (num==4){
                    final User_app_coach userAppCoach=new User_app_coach();
                    userAppCoach.setGender(Userapp.getGender());
                    userAppCoach.setFullName(Userapp.getFullName());
                    userAppCoach.setEmail(Userapp.getEmail());
                    userAppCoach.setUserName(Userapp.getUserName());
                    userAppCoach.setPassword(Userapp.getPassword());
                    mAuth.createUserWithEmailAndPassword(userAppCoach.getEmail(), userAppCoach.getPassword()).addOnCompleteListener(login_step2.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mRoot = new Firebase("https://gimfit-654d0.firebaseio.com/Coach");
                                mRoot.child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).setValue(userAppCoach);
                                Intent intent = new Intent(login_step2.this, have_accuont.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(login_step2.this, "User not connected", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
    public int InValid(){
        User_app Userapp=(User_app) getIntent().getSerializableExtra("myobj");
        int num=0;
        EditText editText1=(EditText)findViewById(R.id.Full_name);
        fullName=editText1.getText().toString();
        EditText editText2=(EditText)findViewById(R.id.Email);
        email=editText2.getText().toString();
        EditText editText3=(EditText)findViewById(R.id.Password);
        password=editText3.getText().toString();
        EditText editText4=(EditText)findViewById(R.id.User_name);
        userName=editText4.getText().toString();
        if(fullName.matches("")){
            editText1.setBackgroundResource(R.drawable.border2);
            editText1.setError("You did not fill this field");}
        else{ editText1.setBackgroundResource(R.drawable.border);
        num=num+1;
        Userapp.setFullName(fullName);}
        if(!isEmailValid(email)){
            editText2.setBackgroundResource(R.drawable.border2);
            editText2.setError("You have registered an invalid email");}
        else {editText2.setBackgroundResource(R.drawable.border);
            num=num+1;
            Userapp.setEmail(email);
        }
        if(password.length()<8){
            editText3.setBackgroundResource(R.drawable.border2);
            editText3.setError("8-32 characters\n" +
                    "at least one lowercase letter\n" +
                    "at least one uppercase letter\n" +
                    "at least 1 number\n" +
                    "optional special character [!@#$%^&*]");}
        else {editText3.setBackgroundResource(R.drawable.border); num=num+1;
        Userapp.setPassword(password);
        }

        if (userName.matches("")){
            editText4.setBackgroundResource(R.drawable.border2);
            editText4.setError("You did not fill this field");
            }
        else {editText4.setBackgroundResource(R.drawable.border); num=num+1;
        Userapp.setUserName(userName);}

    return num;


    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
