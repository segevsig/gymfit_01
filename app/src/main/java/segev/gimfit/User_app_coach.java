package segev.gimfit;

import java.io.Serializable;

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
    public static int id=0;
    private int CodeOfChoces=0;

    public User_app_coach() {
        id++;
        CodeOfChoces=id;
    }
    public  int getCodeOfChoces() {
        return CodeOfChoces;
    }

    public  void setCodeOfChoces(int codeOfChoces) {
        CodeOfChoces = codeOfChoces;
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



