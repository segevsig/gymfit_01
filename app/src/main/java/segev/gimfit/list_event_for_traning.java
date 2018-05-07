package segev.gimfit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class list_event_for_traning extends AppCompatActivity {
    private Firebase mRef;
    private Firebase mRef1;
    ListView listView ;
    List<String> step = new ArrayList<String>();
    List<String> calories = new ArrayList<String>();
    List<String> hour = new ArrayList<String>();
    List<String> description = new ArrayList<String>();
    List<String> time = new ArrayList<String>();
    List<String> typeoftrininig = new ArrayList<String>();
    List<String> minute = new ArrayList<String>();
    List<String> date = new ArrayList<String>();
    List<String> distance = new ArrayList<String>();
    List<String> second = new ArrayList<String>();
    String[] array;
    String[] step1;
    String[] calories1;
    String[] hour1;
    String[] descripition1;
    String[] time1;
    String[] type1;
    String[] min1;
    String[] date1;
    String[] distance1;
    String[] second1;


    List<String> traningdata = new ArrayList<String>();
String kEYOFTRANEE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event_for_traning);
        final String trineeName=getIntent().getStringExtra("name").toString();
        listView=findViewById(R.id.listevent);



        mRef=new Firebase("https://gimfit-654d0.firebaseio.com").child("trainee");
        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                for (com.firebase.client.DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if (snapshot.child("fullName").getValue().toString().equals(trineeName)) {
                       keyoftranee(snapshot.getKey().toString());


                    }
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mRef1=new Firebase("https://gimfit-654d0.firebaseio.com").child("trainee").child("kEYOFTRANEE").child("traning data");
        mRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    addtoarray(snapshot.getValue().toString());

                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    private void addtoarray(String s) {
        traningdata.add(s);
        arraytolist();
    }



    private void arraytolist() {
        int index;
        int index1;
        String str;
        array = traningdata.toArray(new String[0]);

        for (int i = 0; i < array.length; i++) {
            index = array[i].indexOf("c");
            str = array[i].substring(1, index - 2);
            str = str.replace('=',':');
            step.add(str);
            index1 = array[i].indexOf("h");
            str = array[i].substring(index, index1 - 2);
            str = str.replace('=',':');
            calories.add(str);
            index = array[i].indexOf("d");
            str = array[i].substring(index1, index - 2);
            str = str.replace('=',':');
            hour.add(str);
            index1 = array[i].indexOf("tim");
            str = array[i].substring(index, index1 - 2);
            str = str.replace('=',':');
            description.add(str);
            index = array[i].indexOf("typ");
            str = array[i].substring(index1, index - 2);
            str = str.replace('=',':');
            time.add(str);
            index1 = array[i].indexOf("minute");
            str = array[i].substring(index, index1 - 2);
            str = str.replace('=',':');
            typeoftrininig.add(str);
            index = array[i].indexOf("dat");
            str = array[i].substring(index1, index - 2);
            str = str.replace('=',':');
            minute.add(str);
            index1 = array[i].indexOf("dis");
            str = array[i].substring(index, index1 - 2);
            str = str.replace('=',':');
            date.add(str);
            index = array[i].indexOf("sec");
            str = array[i].substring(index1, index - 2);
            str = str.replace('=',':');
            distance.add(str);
            index1 = array[i].indexOf("}");
            str = array[i].substring(index,index1);
            str = str.replace('=',':');
            second.add(str);

        }
        step1 = step.toArray(new String[0]);
        calories1 = calories.toArray(new String[0]);
        hour1 = hour.toArray(new String[0]);
        descripition1 = description.toArray(new String[0]);
        time1 = time.toArray(new String[0]);
        type1 = typeoftrininig.toArray(new String[0]);
        min1 = minute.toArray(new String[0]);
        date1 = date.toArray(new String[0]);
        distance1 = distance.toArray(new String[0]);
        second1 = second.toArray(new String[0]);
        CustomAdpter customAdpter = new CustomAdpter();
        listView.setAdapter(customAdpter);
    }


    private void keyoftranee(String s) {
        kEYOFTRANEE=s;
        mRef1=new Firebase("https://gimfit-654d0.firebaseio.com").child("trainee").child(kEYOFTRANEE).child("traning data");
        mRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    addtoarray(snapshot.getValue().toString());}

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    class CustomAdpter extends BaseAdapter{

        @Override
        public int getCount() {
            return array.length;
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
            view = getLayoutInflater().inflate(R.layout.customlistview, viewGroup, false);
            TextView date=(TextView) view.findViewById(R.id.dateevent);
            TextView time=(TextView) view.findViewById(R.id.timeoftr);
            TextView descripition=(TextView) view.findViewById(R.id.discption);
            TextView hour=(TextView) view.findViewById(R.id.hour);
            TextView minuets=(TextView) view.findViewById(R.id.minute);
            TextView second=(TextView) view.findViewById(R.id.second);
            TextView step=(TextView) view.findViewById(R.id.stepof);
            TextView calories=(TextView) view.findViewById(R.id.calories);
            TextView distance=(TextView) view.findViewById(R.id.distance);
            TextView typeof=(TextView) view.findViewById(R.id.type);

            step.setText(step1[i]);
            date.setText(date1[i]);
            time.setText(time1[i]);
            descripition.setText(descripition1[i]);
            hour.setText(hour1[i]);
            minuets.setText(min1[i]);
            second.setText(second1[i]);
            calories.setText(calories1[i]);
            distance.setText(distance1[i]);
            date.setText(date1[i]);
            typeof.setText(type1[i]);


            return view;
        }
    }
}
