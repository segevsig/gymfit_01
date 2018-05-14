package segev.gimfit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class have_accuont extends AppCompatActivity {
    EditText Username;
    EditText Password;
    EditText login;
    FirebaseAuth mAuth;
    String email  ;
    String password;
    private DatabaseReference mdatabase;
    private FirebaseFirestore mFirestore;
    ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_have_accuont);
        SharedPreferences sp = getSharedPreferences("gimfit", 0);
        SharedPreferences.Editor sedt = sp.edit();
        sedt.commit();
        mAuth  = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mdatabase=FirebaseDatabase.getInstance().getReference();
        EditText editText;
        mProgress = new ProgressDialog(this);
        mProgress.setProgress(100);
        mProgress.setTitle("User verifying");
        mProgress.setMessage("Please wait patiently");

        Username = (EditText) findViewById(R.id.user_name_login_ID);
        Password = (EditText) findViewById(R.id.password_login_ID);

        findViewById(R.id.log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgress.show();
                email  = Username.getText().toString();
                password  = Password.getText().toString();
                checklogin(view);
            }
        });



    }


    public void checklogin(View view) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                            String token_id = FirebaseInstanceId.getInstance().getToken();
                            String current_id = mAuth.getCurrentUser().getUid();
                            Map<String, Object> tokenMap = new HashMap<>();
                            tokenMap.put("token_id",token_id);

                            mFirestore.collection("Users").document(current_id).update(tokenMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                }
                            });

                        }


                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Coach")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    ref.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                        @Override
                        public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                           if (dataSnapshot.getValue()!=null){

                               Intent intent=new Intent(have_accuont.this,dashboard_activity.class);
                               Toast.makeText(have_accuont.this,"User is Loged In pass to private page",Toast.LENGTH_LONG).show();
                               startActivity(intent);
                               mProgress.hide();
                            }else if (true){
                               FirebaseDatabase.getInstance().getReference().child("trainee")
                                       .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("device_token")
                                       .setValue(FirebaseInstanceId.getInstance().getToken());
                                Intent intent = new Intent(have_accuont.this,athlete_area_playground.class);
                                startActivity(intent);
                                Toast.makeText(have_accuont.this,"User is Loged In pass to private page",Toast.LENGTH_LONG).show();
                               mProgress.hide();

                           }
                            else  Toast.makeText(have_accuont.this,"Connection Error ",Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            });
        }

    }



