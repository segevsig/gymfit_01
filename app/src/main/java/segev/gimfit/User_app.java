package segev.gimfit;

import java.io.Serializable;

public class User_app implements Serializable {
    public String Gender;
    private String FullName;
    private String UserName;
    private String Password;
    private String Email;
    private double Age;
    private double Weight;
    public static class Birth implements Serializable{
        int day;
        int month;
        int year;
        public Birth(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getDay() {

            return day;
        }

        public int getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }
    }
    private Birth birth;
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

    public Birth getBirth() {
        return birth;
    }

    public void setBirth(Birth birth) {
        this.birth = birth;
    }
}


