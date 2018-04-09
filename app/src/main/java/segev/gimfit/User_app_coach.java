package segev.gimfit;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User_app_coach implements Serializable {
    private String Gender;

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    private String FullName;
    private String UserName;
    private String Password;
    private String Email;
    private int CodeOfChoces=0;



    public int getCodeOfChoces() {
        return CodeOfChoces;
    }

    public void setCodeOfChoces(int codeOfChoces) {
        CodeOfChoces = codeOfChoces;
    }


    public User_app_coach() {
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Coach");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("NumberOfCoach")){
                    String number =String.valueOf(1+ Integer.valueOf((String)dataSnapshot.child("NumberOfCoach").getValue()));
                    ref.child("NumberOfCoach").setValue(number);
                    CodeOfChoces = Integer.valueOf( number);

                }else {
                    ref.child("NumberOfCoach").setValue("1");
                    CodeOfChoces = 1;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
      }







    private String NameOfCoach;
    private String Goals;
    private double Height;
    private boolean Coach=false;

    public boolean isCoach() {
        return Coach;
    }

    public void setCoach(boolean coach) {
        Coach = coach;
    }



    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



    public String getNameOfCoach() {
        return NameOfCoach;
    }

    public void setNameOfCoach(String nameOfCoach) {
        NameOfCoach = nameOfCoach;
    }

    public String getGoals() {
        return Goals;
    }

    public void setGoals(String goals) {
        Goals = goals;
    }

    public double getHeight(){return Height;}

    public void setHeight(double height){Height=height;}


}



