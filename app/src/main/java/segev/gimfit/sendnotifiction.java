package segev.gimfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class sendnotifiction extends AppCompatActivity {

    String tokentrinee;
    private Firebase mRef;
    String massgetrinee;
    String tokenChoch;
    private Firebase mRoot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String trineName=getIntent().getStringExtra("name").toString();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendnotifiction);
        TextView nametranee=findViewById(R.id.name_trainee);
        nametranee.setText(trineName);

        mRef=new Firebase("https://gimfit-654d0.firebaseio.com").child("trainee");
        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                for (com.firebase.client.DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if (snapshot.child("fullName").getValue().toString().equals(trineName)) {
                        settoken(snapshot.getKey().toString());

                    }
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
       final Button send_feedback=findViewById(R.id.send_feedback);
       send_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text=findViewById(R.id.text_to);
                massgetrinee=text.getText().toString();

tokenChoch= FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

          mRoot=new Firebase("https://gimfit-654d0.firebaseio.com/notification/"+tokenChoch+"/"+tokentrinee+"/message");
mRoot.setValue(massgetrinee);

                Intent intent=new Intent(sendnotifiction.this,dashboard_activity.class);
                startActivity(intent);



            }
        });

    }

    private void settoken(String device_token) {
        tokentrinee=device_token;
    }
}
