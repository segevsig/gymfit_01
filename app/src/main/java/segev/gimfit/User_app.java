package segev.gimfit;

import java.io.Serializable;

public class User_app implements Serializable {
    private String Gender;
    private String FullName;
    private String UserName;
    private String Password;
    private String Email;
    private double Age;
    private double Weight;
    private String birth;



    private String NameOfCoach;
    private String Goals;
    private double Height;


    public void setBirth(String birth) {
        this.birth = birth;
    }
    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
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

    public double getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
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


