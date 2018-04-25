package segev.gimfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_step4 extends AppCompatActivity {
    private Firebase mRoot;
    FirebaseAuth mAuth;
    User_app Userapp;
    private Firebase mRef;
    int num = 0;
    boolean ok=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_step4);
        mAuth = FirebaseAuth.getInstance();
        Userapp = (User_app) getIntent().getSerializableExtra("myobj");
        Spinner spinner1 = (Spinner) findViewById(R.id.spinnerGoal);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.goals, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        final Button button2 = findViewById(R.id.button_FinalSignUp);


    }
    public boolean takefield() {

        Spinner mySpinner = (Spinner) findViewById(R.id.spinnerGoal);
        final String goals = mySpinner.getSelectedItem().toString();
        EditText Choches=(EditText)findViewById(R.id.codeofchoach);

        final int CoachCode=Integer.valueOf(Choches.getText().toString());

        final TextView errorText = (TextView) mySpinner.getSelectedView();
        final TextView errorText1 = (TextView) mySpinner.getSelectedView();

        if (goals.equals("Your goals")) {
            mySpinner.setBackgroundResource(R.drawable.border2);
            errorText.setError("You did not select");
        } else {
            mySpinner.setBackgroundResource(R.drawable.border);
            num = num + 1;
        }
        if (CoachCode==0) {
            errorText1.setError("You did not select");
        } else {
            mRef=new Firebase("https://gimfit-654d0.firebaseio.com/Coach");
            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int code=0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        code=snapshot.child("codeOfChoces").getValue(int.class);
                    if (code==CoachCode){
                        num++;
                        plusplus(num,goals,CoachCode);
                        Toast.makeText(login_step4.this,"you select the coach"+snapshot.child("fullName").getValue(String.class) , Toast.LENGTH_LONG).show();
                    }

                    }
                    if(code==0){
                        errorText1.setError("You did not select");
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        }

        
if (ok==true){
    return true;}
    else return false;
        }




    
    private void plusplus(int n,String goals,int CoachCode) {
        Toast.makeText(this, "plus plus and doing stuff  ", Toast.LENGTH_SHORT).show();

        if (num == 2) {
            Userapp.setGoals(goals);
            String nameOfChoches=Integer.toString(CoachCode);
            Userapp.setNameOfCoach(nameOfChoches);
            mAuth.createUserWithEmailAndPassword(Userapp.getEmail(), Userapp.getPassword()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        mRoot = new Firebase("https://gimfit-654d0.firebaseio.com/trainee/" +"x"+ Userapp.getNameOfCoach());
                        mRoot.child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).setValue(Userapp);
                        Intent intent = new Intent(login_step4.this, have_accuont.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(login_step4.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });

    }
    }

    public void signUp(View view) {
        takefield();


    }
}
