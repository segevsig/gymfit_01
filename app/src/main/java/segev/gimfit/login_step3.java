package segev.gimfit;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class login_step3 extends AppCompatActivity implements View.OnClickListener {
    Integer age=0;
    double weight=0;
    double height=0;
    int num=0;
    private int mYear, mMonth, mDay;
    String datePicker;
    private EditText btnDatePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_step3);
        btnDatePicker=(EditText) findViewById(R.id.date_picker_id);
        btnDatePicker.setOnClickListener(this);}
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

        final Button button1=findViewById(R.id.button_nextStepTo4);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                isValid();
            }
        });
    }

    public void isValid(){

        User_app Userapp=(User_app) getIntent().getSerializableExtra("myobj");
        EditText editText=(EditText) findViewById(R.id.age);
            age=Integer.valueOf(editText.getText().toString());
        EditText editText1=(EditText) findViewById(R.id.height);
        try
        {
            height= Double.parseDouble(editText1.getText().toString());
            // it means it is double
        } catch (Exception e1) {
            // this means it is not double
            e1.printStackTrace();
        }
        EditText editText2=(EditText) findViewById(R.id.weight);
        try
        {
            weight= Double.parseDouble(editText2.getText().toString());
            // it means it is double
        } catch (Exception e1) {
            // this means it is not double
            e1.printStackTrace();
        }
        if ((age<8)||(age>70)||(age==0)){
            editText.setBackgroundResource(R.drawable.border2);
            editText.setError("You did not fill this field or Invalid entry");}
        else { editText.setBackgroundResource(R.drawable.border);
            num=num+1;
            Userapp.setAge(age);
        }
        if ((height<=0)||(height>220)){
            editText1.setBackgroundResource(R.drawable.border2);
            editText1.setError("You did not fill this field or Invalid entry");
        }
        else { editText1.setBackgroundResource(R.drawable.border);
            num=num+1;
            Userapp.setHeight(height);
        }
        if ((weight<=0)||(weight>300)){
            editText2.setBackgroundResource(R.drawable.border2);
            editText2.setError("You did not fill this field or Invalid entry");
        }
        else { editText2.setBackgroundResource(R.drawable.border);
            num=num+1;
            Userapp.setWeight(weight);
        }

        if(btnDatePicker.getText().toString()==null){
            btnDatePicker.setBackgroundResource((R.drawable.border2));
            btnDatePicker.setError("You did not fill this field or Invalid entry");
        }
        else {
            num++;
            Userapp.setBirth(btnDatePicker.getText().toString());
        }

             if (num==4){
            Intent intent = new Intent(login_step3.this, login_step4.class);
            intent.putExtra("myobj",Userapp);
            startActivity(intent);
        }
        else
            Toast.makeText(login_step3.this,"you dont fill all the answer",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}









