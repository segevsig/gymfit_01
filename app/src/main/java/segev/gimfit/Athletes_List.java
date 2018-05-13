package segev.gimfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
    List<String> age_of_traning = new ArrayList<String>();
    List<String> gender_of_traning = new ArrayList<String>();

    ListView listView ;
    String triningchoose;
    String[] arrayName;
    String[] arrayGender;
    String[] arrayAge;





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
            name_of_traning.add(String.format("%s" ," Name : " +name));
            age_of_traning.add(String.format("%s" , "Age : "+age));
            gender_of_traning.add(String.format("%s" , "Gender : "+gender));

             arrayName = name_of_traning.toArray(new String[0]);
             arrayGender = age_of_traning.toArray(new String[0]);
             arrayAge = gender_of_traning.toArray(new String[0]);

            //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Athletes_List.this, android.R.layout.simple_list_item_1, array);

            CustomAdpter1 customAdpter = new CustomAdpter1();
            listView.setAdapter(customAdpter);



        }

    private void showEvent(String substring) {
        Intent intent=new Intent(Athletes_List.this,list_event_for_traning.class);
        intent.putExtra("name",substring);
        startActivity(intent);




    }
    class CustomAdpter1 extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayName.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.custom_list_tranie, viewGroup, false);
            TextView age=(TextView) view.findViewById(R.id.age_traniee);
            final TextView name1=(TextView) view.findViewById(R.id.nametranie);
            TextView gender=(TextView) view.findViewById(R.id.gender_traniee);
            age.setText(arrayAge[i]);
            name1.setText(arrayName[i]);
            gender.setText(arrayGender[i]);

            name1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    triningchoose=name1.getText().toString();
                    showEvent(triningchoose);

                }
            });


            return view;
        }
    }


}
