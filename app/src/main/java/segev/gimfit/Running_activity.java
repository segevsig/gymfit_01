package segev.gimfit;

/**
 * Created by LENOVO on 24/12/2017.
 */

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;


import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;



public class Running_activity extends AppCompatActivity implements View.OnClickListener {

    private SeekBar distanceSeekbar;
    private SeekBar durationSeekbar;
    private Spinner workoutType;
    private MultiAutoCompleteTextView description;
    private EditText btnDatePicker;
    private EditText btnTimePicker;
    private int mYear, mMonth, mDay, mHour, mMinute;







    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.running_layout);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        distanceSeekbar =(SeekBar) findViewById(R.id.distanseSeekBar);
        durationSeekbar = (SeekBar) findViewById(R.id.durationSeekBar);
        workoutType = (Spinner) findViewById(R.id.spinnerWorkoutType) ;
        description = (MultiAutoCompleteTextView) findViewById(R.id.description);
        btnDatePicker=(EditText) findViewById(R.id.date_picker_id);
        btnTimePicker=(EditText)findViewById(R.id.time_picker_id);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        Button button=findViewById(R.id.sendmail);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    get_data_and_send();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });




        Spinner spinnerRunning = (Spinner) findViewById(R.id.spinnerWorkoutType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.TypeOfTraningRunning, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRunning.setAdapter(adapter);

        distanceSeekbar.setMax(50);
        distanceSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0 ;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressSeek, boolean fromUser) {
                progress = progressSeek ;
              //  runningDistanceKmId.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
              //  runningDistanceKmId.setText(String.valueOf(progress));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
              //  runningDistanceKmId.setText(String.valueOf(progress));


            }
        });
        durationSeekbar.setMax(50);
        durationSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0 ;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressSeek, boolean fromUser) {
                progress = progressSeek ;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.navigation_running:
                        Toast.makeText(Running_activity.this,"You are on the requested page ",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.navigation_biking:
                        Toast.makeText(Running_activity.this,"Creating biking workout",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(Running_activity.this, Biking_activity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_gym:
                        Toast.makeText(Running_activity.this,"Creating gym workout",Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(Running_activity.this, Gym_activity.class);
                        startActivity(intent3);
                        break;
                }


                return false;
            }
        });
    }
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            btnDatePicker.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            btnTimePicker.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }


    public void get_data_and_send() throws IOException {

        Event event = new Event()
                .setSummary("Google I/O 2015")
                .setLocation("800 Howard St., San Francisco, CA 94103")
                .setDescription("A chance to hear more about Google's developer products.");

        DateTime startDateTime = new DateTime("2015-05-28T09:00:00-07:00");
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setStart(start);

        DateTime endDateTime = new DateTime("2015-05-28T17:00:00-07:00");
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setEnd(end);

        String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
        event.setRecurrence(Arrays.asList(recurrence));

        EventAttendee[] attendees = new EventAttendee[] {
                new EventAttendee().setEmail("lpage@example.com"),
                new EventAttendee().setEmail("sbrin@example.com"),
        };
        event.setAttendees(Arrays.asList(attendees));

        EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);

        String calendarId = "primary";
    //    event = .insert(calendarId, event).execute();
        System.out.printf("Event created: %s\n", event.getHtmlLink());



    }


}