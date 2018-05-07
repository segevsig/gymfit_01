package segev.gimfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Athletes_List extends AppCompatActivity {
    String codeOfChoces;
    private Firebase mRef;
    List<String> name_of_traning = new ArrayList<String>();
    ListView listView ;
    String triningchoose;
    String ageChose;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athletes__list);
        listView=findViewById(R.id.list_of_traning);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Coach")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("codeOfChoces");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                synchronized (this){
                    setCode(dataSnapshot.getValue().toString());
                    addtraining();
                    }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void setCode(String code){
        codeOfChoces=code;
    }
    public void addtraining(){
        mRef=new Firebase("https://gimfit-654d0.firebaseio.com").child("trainee");
        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                for (com.firebase.client.DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if (snapshot.child("nameOfCoach").getValue().toString().equals(codeOfChoces)) {
                        addtrainingtolist(snapshot.child("fullName").getValue().toString(),snapshot.child("age").getValue().toString(),snapshot.child("gender").getValue().toString());


                    }
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });}
        public void addtrainingtolist(String name,String age,String gender){
            name_of_traning.add(
                    String.format("%s %s %s" ," Name : " +name ," Age : "+age," Gender : "+gender));
            String[] array = name_of_traning.toArray(new String[0]);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Athletes_List.this, android.R.layout.simple_list_item_1, array);

            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                  triningchoose=(String) listView.getItemAtPosition(i);
                  //Toast.makeText(Athletes_List.this,triningchoose,Toast.LENGTH_LONG).show();
                    int index = triningchoose.indexOf(" A"); // find int position of "I

                    triningchoose.substring(8,index);
                    Toast.makeText(Athletes_List.this,triningchoose.substring(8,index),Toast.LENGTH_LONG).show();
                    triningchoose=triningchoose.substring(8,index-1);

                    showEvent(triningchoose);

                }
            });

        }

    private void showEvent(String substring) {
        Intent intent=new Intent(Athletes_List.this,list_event_for_traning.class);
        intent.putExtra("name",substring);
        startActivity(intent);




    }


}
