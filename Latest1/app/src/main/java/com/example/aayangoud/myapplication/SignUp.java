package com.example.aayangoud.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.Dialog;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Aayan Goud on 8/20/2016.
 */
public class SignUp extends AppCompatActivity {

    private EditText fldDate, fldBusNumber, fldDriverName,
            fldPlanerName, fldFromPlace, fldFromTime,
            fldToPlace, fldToTime,fldMdcInTime,fldMdcOutTime;
    private int year, month, day;
    static final int DATE_PICKER_ID = 1111;
    private Button btnStartTrip,btnStopTrip;
private SimpleDateFormat dateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_signup);
            initView();
            initListners();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void initView() {
        try {
            Toolbar toolbar = (Toolbar) findViewById(R.id.tsToolbar);
            toolbar.setTitle(getResources().getString(R.string.lbl_transport));
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            fldDate = (EditText) findViewById(R.id.fldDate);
            fldBusNumber = (EditText) findViewById(R.id.fldBusNumber);
            fldDriverName = (EditText) findViewById(R.id.fldDriverName);
            fldPlanerName = (EditText) findViewById(R.id.fldPlanerName);
            fldFromPlace = (EditText) findViewById(R.id.fldFromPlace);
            fldFromTime = (EditText) findViewById(R.id.fldFromTime);
            fldToPlace = (EditText) findViewById(R.id.fldToPlace);
            fldToTime = (EditText) findViewById(R.id.fldToTime);
            fldMdcInTime = (EditText) findViewById(R.id.fldMdcInTime);
            fldMdcOutTime = (EditText) findViewById(R.id.fldMdcOutTime);
            btnStartTrip = (Button) findViewById(R.id.btnStartTrip);
            btnStopTrip = (Button) findViewById(R.id.btnStopTrip);

            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
            fldDate.setText(dateFormat.format(c.getTime()));

            //fldFromTime.setText( Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE));
            //fldToTime.setText( Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE));
            fldMdcInTime.setText( Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE));
            //fldMdcOutTime.setText( Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initListners() {
        fldDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_PICKER_ID);
            }
        });
        fldFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showTimePicker(fldFromTime);
            }
        });
        fldToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showTimePicker(fldToTime);
            }
        });

        fldMdcInTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(fldMdcInTime,"MDCINTIME");
            }
        });

        fldMdcOutTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(fldMdcOutTime,"MDCOUTTIME");
            }
        });

        btnStartTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fldFromTime.setText(String.valueOf( new SimpleDateFormat("HH:mm:ss").format(new Date())));
                //fldToTime.setText(String.valueOf( new SimpleDateFormat("HH:mm:ss").format(new Date())));

                //Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE)
                btnStartTrip.setVisibility(View.GONE);
                btnStopTrip.setVisibility(View.VISIBLE);
            }
        });
        btnStopTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               fldToTime.setText(String.valueOf( new SimpleDateFormat("HH:mm:ss").format(new Date())));
                //fldFromTime.setText("");
                //fldToTime.setText("");
                btnStartTrip.setVisibility(View.VISIBLE);
                btnStopTrip.setVisibility(View.GONE);
            }
        });
        //Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE)
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                // open datepicker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
                return new DatePickerDialog(this, pickerListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, month, day);
            fldDate.setText(dateFormat.format(newDate));
            /*// Show selected date
            fldDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));*/

        }
    };

    private void showTimePicker(final EditText timeObject, final String type){
        try {
            int hour=0,minute=0;
            Calendar mcurrentTime = Calendar.getInstance();
            if(timeObject.getText().toString().trim().length() > 0){
                String timer = timeObject.getText().toString().trim();
                if(timer.contains(":") && !Comman.isNullOrEmpty(timer.split(":")[0]) && !Comman.isNullOrEmpty(timer.split(":")[1])){
                    hour = Comman.convertToInt(timer.split(":")[0]);
                    minute = Comman.convertToInt(timer.split(":")[1]);
                }
            }else{
                hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                minute = mcurrentTime.get(Calendar.MINUTE);
            }
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(SignUp.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                   if(type.equalsIgnoreCase("MDCINTIME") && timeObject.getText().toString().length() > 0){
                       String fromtimer = fldMdcInTime.getText().toString().trim();
                       String totimer = fldMdcOutTime.getText().toString().trim();
                       if((!Comman.isNullOrEmpty(fromtimer.split(":")[0])) && (!Comman.isNullOrEmpty(totimer.split(":")[0]))){
                           if((Comman.convertToInt(fromtimer.split(":")[0])) < (Comman.convertToInt(totimer.split(":")[0]))){
                               Toast.makeText(SignUp.this,"MDC In Time should be less than MDC Out Time",Toast.LENGTH_LONG).show();
                               fldMdcInTime.setText("");
                               fldMdcOutTime.setText("");
                               return;
                           }
                       }

                    }else if(type.equalsIgnoreCase("MDCOUTTIME")){
                       String fromtimer = fldMdcInTime.getText().toString().trim();
                       String totimer = fldMdcOutTime.getText().toString().trim();
                       if((!Comman.isNullOrEmpty(fromtimer.split(":")[0])) && (!Comman.isNullOrEmpty(totimer.split(":")[0]))){
                           if((Comman.convertToInt(fromtimer.split(":")[0])) > (Comman.convertToInt(totimer.split(":")[0]))){
                               Toast.makeText(SignUp.this,"MDC Out Time should be greater than MDC in Time",Toast.LENGTH_LONG).show();
                               fldMdcInTime.setText("");
                               fldMdcOutTime.setText("");
                               return;
                           }
                       }
                    }
                    timeObject.setText( selectedHour + ":" + selectedMinute);

                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            Toast.makeText(SignUp.this,"Tested",Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validateTripForm(){
        boolean result=false;
        if(fldDate.getText().toString().trim().length() > 0){
            fldDate.setFocusable(true);
            fldDate.setError("Please enter Date of Journey");
        }else if(fldBusNumber.getText().toString().trim().length() > 0){
            fldBusNumber.setFocusable(true);
            fldBusNumber.setError("Please enter Bus Number");
        }else if(fldDriverName.getText().toString().trim().length() > 0){
            fldDriverName.setFocusable(true);
            fldDriverName.setError("Please enter Driver Name");
        }else if(fldMdcInTime.getText().toString().trim().length() > 0){
            fldMdcInTime.setFocusable(true);
            fldMdcInTime.setError("Please select MDC In Time");
        }else if(fldMdcOutTime.getText().toString().trim().length() > 0){
            fldMdcOutTime.setFocusable(true);
            fldMdcOutTime.setError("Please select MDC Out Time");
        }else if(fldFromPlace.getText().toString().trim().length() > 0){
            fldFromPlace.setFocusable(true);
            fldFromPlace.setError("Please enter From");
        }else if(fldToPlace.getText().toString().trim().length() > 0){
            fldToPlace.setFocusable(true);
            fldToPlace.setError("Please enter To");
        }
        return result;
    }
}
