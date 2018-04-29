package segev.gimfit;

/**
 * Created by segevcohen on 23/04/2018.
 */

public class session_data {

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    private String date;
    private String time;
    private String description;
    private String hour;
    private String minute;
    private String second;
    private String step;

    public String getTypeoftrining() {
        return typeoftrining;
    }

    public void setTypeoftrining(String typeoftrining) {
        this.typeoftrining = typeoftrining;
    }

    private String calories;
    private String distance;
    private String typeoftrining;


}
