package segev.gimfit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_have_accuont);
        SharedPreferences sp = getSharedPreferences("gimfit", 0);
        SharedPreferences.Editor sedt = sp.edit();
        sedt.commit();
        mAuth  = FirebaseAuth.getInstance();
        mdatabase=FirebaseDatabase.getInstance().getReference();
        EditText editText;
        Username = (EditText) findViewById(R.id.user_name_login_ID);
        Password = (EditText) findViewById(R.id.password_login_ID);
        findViewById(R.id.log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Coach")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    ref.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                        @Override
                        public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                           if (dataSnapshot.getValue()!=null){
                               Intent intent=new Intent(have_accuont.this,Coach_zone.class);
                               Toast.makeText(have_accuont.this,"User is Loged In pass to private page",Toast.LENGTH_LONG).show();
                               startActivity(intent);
                            }else if (true){
                                Intent intent = new Intent(have_accuont.this,TrainerPageArea.class);
                                startActivity(intent);
                                Toast.makeText(have_accuont.this,"User is Loged In pass to private page",Toast.LENGTH_LONG).show();
                            }
                            else  Toast.makeText(have_accuont.this,"Connection Error ",Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            }
        });

    }
}


