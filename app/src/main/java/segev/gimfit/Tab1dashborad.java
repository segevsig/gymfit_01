
package segev.gimfit;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.api.services.calendar.CalendarScopes;


/**
 * Created by LENOVO on 21/12/2017.
 */

public class Tab1dashborad extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab1dashborad, container, false);
        final TextView namecohach=(TextView) rootView.findViewById(R.id.hellow_name_coach);

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Coach")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()) {
                    if (dataSnapshot1.getKey().toString().equals("fullName")) {
                        namecohach.setText("Hello : "+ dataSnapshot1.getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        CalendarView simpleCalendarView = rootView.findViewById(R.id.calendarViewCoach);

google_calendar goo=new google_calendar();
        return rootView;
    }

}
