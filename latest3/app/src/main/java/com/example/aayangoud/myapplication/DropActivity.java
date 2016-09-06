package com.example.aayangoud.myapplication;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.Dialog;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.aayangoud.myapplication.DTO.DriverDTO;
import com.example.aayangoud.myapplication.DTO.TransportFormDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aayan Goud on 8/20/2016.
 */
public class DropActivity extends AppCompatActivity {

    private EditText fldDate,  fldDriverName, fldFromPlace, fldFromTime,
            fldToPlace, fldToTime, fldMdcInTime, fldMdcOutTime,fldAcftRegn,fldRemark,fldTeam;
    private AutoCompleteTextView fldBusNumber,fldDriverId;
    private int year, month, day;
    static final int DATE_PICKER_ID = 1111;
    private Button btnStartTrip, btnStopTrip;
    private SimpleDateFormat dateFormat;
    private ProgressDialog mProgressDailog;
    private DbHelper dbConnection;
    private Context mContext;
    private boolean isNegative=false, isOnTrip=false;
    private static final String[] busNumbers = new String[] {
            "6238", "6239", "4587", "5861"
    };
    private static final String[] driverIds = new String[] {
            "TG000713", "TG069844", "TG069829", "TG005162", "TG050080", "TG030585",
            "TG057765", "TG024587", "TG001420", "TG057608", "TG031366", "TG050754","TG073299"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mContext = this;
            setContentView(R.layout.activity_signup);
            initView();
            initListners();
            dbConnection = new DbHelper(mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void initView() {
        try {
            final ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("Drop");
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            fldDate = (EditText) findViewById(R.id.fldDate);
            fldBusNumber = (AutoCompleteTextView) findViewById(R.id.fldBusNumber);
            fldDriverId = (AutoCompleteTextView) findViewById(R.id.fldDriverId);
            fldDriverName = (EditText) findViewById(R.id.fldDriverName);

            fldFromPlace = (EditText) findViewById(R.id.fldFromPlace);
            fldFromTime = (EditText) findViewById(R.id.fldFromTime);
            fldToPlace = (EditText) findViewById(R.id.fldToPlace);
            fldToTime = (EditText) findViewById(R.id.fldToTime);
            fldMdcInTime = (EditText) findViewById(R.id.fldMdcInTime);
            fldMdcOutTime = (EditText) findViewById(R.id.fldMdcOutTime);
            fldAcftRegn = (EditText) findViewById(R.id.fldAcftRegn);
            fldRemark = (EditText) findViewById(R.id.fldRemark);
            fldTeam = (EditText) findViewById(R.id.fldTeam);

            btnStartTrip = (Button) findViewById(R.id.btnStartTrip);
            btnStopTrip = (Button) findViewById(R.id.btnStopTrip);

            mProgressDailog = new ProgressDialog(DropActivity.this,R.style.myProgressDialog);
            mProgressDailog.setCancelable(false);
            mProgressDailog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,busNumbers);
            fldBusNumber.setAdapter(adapter);
            fldBusNumber.setThreshold(1);

            ArrayAdapter<String> idsadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,driverIds);
            fldDriverId.setAdapter(idsadapter);
            fldDriverId.setThreshold(1);

            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
            fldDate.setText(dateFormat.format(c.getTime()));

            //fldFromTime.setText( Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE));
            //fldToTime.setText( Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE));
            fldMdcInTime.setText(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE));
            DriverDTO driverDto = AppController.getInstance().getDriverDTO();
            if(driverDto !=null){
                fldBusNumber.setText(driverDto.getBusNumber());
                fldDriverId.setText(driverDto.getDriverId());
                fldDriverName.setText(driverDto.getDriverName());
                fldAcftRegn.setText(driverDto.getAcftRegn());
                fldTeam.setText(driverDto.getTeam());
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initListners() {
       /* fldDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
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
                showTimePicker(fldMdcInTime, "MDCINTIME");
            }
        });

        fldMdcOutTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(fldMdcOutTime, "MDCOUTTIME");
            }
        });
        fldDriverId.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                HashMap<String, String> driversInfoMap = AppController.getInstance().getDriversInfoMap();
                if(fldDriverId.getText().toString().length()>4 && driversInfoMap != null){
                    fldDriverName.setText(driversInfoMap.get(fldDriverId.getText().toString().trim())==null ? "" :driversInfoMap.get(fldDriverId.getText().toString().trim()));
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        btnStartTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateTripForm()){
                    fldFromTime.setText(String.valueOf(new SimpleDateFormat("HH:mm:ss").format(new Date())));
                    btnStartTrip.setVisibility(View.GONE);
                    btnStopTrip.setVisibility(View.VISIBLE);
                    isOnTrip=true;
                }
            }
        });
        btnStopTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOnTrip=false;
                fldToTime.setText(String.valueOf(new SimpleDateFormat("HH:mm:ss").format(new Date())));
                saveItDatabase();
                btnStartTrip.setVisibility(View.VISIBLE);
                btnStopTrip.setVisibility(View.GONE);
                Toast.makeText(mContext,"Trip successfully saved!!!",Toast.LENGTH_LONG).show();
                finish();
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
        }
    };

    private void showTimePicker(final EditText timeObject, final String type) {
        try {
            int hour = 0, minute = 0;
            Calendar mcurrentTime = Calendar.getInstance();
            if (timeObject.getText().toString().trim().length() > 0) {
                String timer = timeObject.getText().toString().trim();
                if (timer.contains(":") && !Comman.isNullOrEmpty(timer.split(":")[0]) && !Comman.isNullOrEmpty(timer.split(":")[1])) {
                    hour = Comman.convertToInt(timer.split(":")[0]);
                    minute = Comman.convertToInt(timer.split(":")[1]);
                }
            } else {
                hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                minute = mcurrentTime.get(Calendar.MINUTE);
            }
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(DropActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                   /* if (type.equalsIgnoreCase("MDCINTIME") && timeObject.getText().toString().length() > 0) {
                        String fromtimer = fldMdcInTime.getText().toString().trim();
                        String totimer = fldMdcOutTime.getText().toString().trim();
                        if ((!Comman.isNullOrEmpty(fromtimer.split(":")[0])) && (!Comman.isNullOrEmpty(totimer.split(":")[0]))) {
                            if ((Comman.convertToInt(fromtimer.split(":")[0])) < (Comman.convertToInt(totimer.split(":")[0]))) {
                                Toast.makeText(SignUp.this, "MDC In Time should be less than MDC Out Time", Toast.LENGTH_LONG).show();
                                fldMdcInTime.setText("");
                                fldMdcOutTime.setText("");
                                return;
                            }
                        }

                    } else if (type.equalsIgnoreCase("MDCOUTTIME")) {
                        String fromtimer = fldMdcInTime.getText().toString().trim();
                        String totimer = fldMdcOutTime.getText().toString().trim();
                        if ((!Comman.isNullOrEmpty(fromtimer.split(":")[0])) && (!Comman.isNullOrEmpty(totimer.split(":")[0]))) {
                            if ((Comman.convertToInt(fromtimer.split(":")[0])) > (Comman.convertToInt(totimer.split(":")[0]))) {
                                Toast.makeText(SignUp.this, "MDC Out Time should be greater than MDC in Time", Toast.LENGTH_LONG).show();
                                fldMdcInTime.setText("");
                                fldMdcOutTime.setText("");
                                return;
                            }
                        }
                    }*/
                    timeObject.setText(selectedHour + ":" + selectedMinute);
                    timeObject.setError(null);

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
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(!isOnTrip)
            super.onBackPressed();
    }

    private boolean validateTripForm() {
        boolean result = false;
        if (fldDate.getText().toString().trim().length() == 0) {
            fldDate.setFocusable(true);
            fldDate.setError("Please enter Date of Journey");
        } else if (fldBusNumber.getText().toString().trim().length() == 0) {
            fldBusNumber.setFocusable(true);
            fldBusNumber.setError("Please enter Bus Number");
        } else if (fldDriverName.getText().toString().trim().length() == 0) {
            fldDriverName.setFocusable(true);
            fldDriverName.setError("Please enter Driver Name");
        }  else if (fldDriverId.getText().toString().trim().length() == 0) {
            fldDriverId.setFocusable(true);
            fldDriverId.setError("Please enter Driver Id");
        }else if (fldTeam.getText().toString().trim().length() == 0) {
            fldTeam.setFocusable(true);
            fldTeam.setError("Please enter Team");
        } else if (fldMdcInTime.getText().toString().trim().length() == 0) {
            fldMdcInTime.setFocusable(true);
            fldMdcInTime.setError("Please select MDC In Time");
        } else if (fldMdcOutTime.getText().toString().trim().length() == 0) {
            fldMdcOutTime.setFocusable(true);
            fldMdcOutTime.setError("Please select MDC Out Time");
        } else if (fldFromPlace.getText().toString().trim().length() == 0) {
            fldFromPlace.setFocusable(true);
            fldFromPlace.setError("Please enter From Place");
        } else if (fldToPlace.getText().toString().trim().length() == 0) {
            fldToPlace.setFocusable(true);
            fldToPlace.setError("Please enter To Place");
        }else if ((fldMdcInTime.getText().toString().trim().length() > 0) && (fldMdcOutTime.getText().toString().trim().length() > 0)) {
            String fromtimer = fldMdcInTime.getText().toString().trim();
            String totimer = fldMdcOutTime.getText().toString().trim();
            if ((!Comman.isNullOrEmpty(fromtimer.split(":")[0])) && (!Comman.isNullOrEmpty(totimer.split(":")[0]))) {
                if ((Comman.convertToInt(fromtimer.split(":")[0])) > (Comman.convertToInt(totimer.split(":")[0]))) {
                    //Toast.makeText(SignUp.this, "MDC In Time should be less than MDC Out Time", Toast.LENGTH_LONG).show();
                    fldMdcInTime.setText("");
                    fldMdcOutTime.setText("");
                    fldMdcInTime.setFocusable(true);
                    fldMdcInTime.setError("MDC In Time should be less than MDC Out Time");
                }else{
                    result=true;
                }
            }
        }else{
            result=true;
        }
        return result;
    }

    private void saveItDatabase(){
        try {
            TransportFormDTO dto = new TransportFormDTO();
            dto.setTransportType(2); //Drop
            dto.setTransportDate(fldDate.getText().toString().trim());
            dto.setBusNo(fldBusNumber.getText().toString().trim());
            dto.setDriverName(fldDriverName.getText().toString().trim());
            dto.setPlannerName(fldDriverId.getText().toString().trim());
            dto.setMdcTimeIn(fldMdcInTime.getText().toString().trim());
            dto.setMdcTimeOut(fldMdcOutTime.getText().toString().trim());
            dto.setFrom(fldFromPlace.getText().toString().trim());
            dto.setTo(fldToPlace.getText().toString().trim());
            dto.setFromTime(fldFromTime.getText().toString().trim());
            dto.setToTime(fldToTime.getText().toString().trim());
            dto.setDriverName(fldDriverName.getText().toString().trim());
            dto.setDriverId(fldDriverId.getText().toString().trim());
            dto.setAcftRegin(fldAcftRegn.getText().toString().trim());
            dto.setRemark(fldRemark.getText().toString().trim());
            if(dto.getToTime() != null && !Comman.isNullOrEmpty(dto.getToTime()) &&
                    dto.getFromTime() != null && !Comman.isNullOrEmpty(dto.getFromTime())) {
                dto.setActualTripDuration(Comman.calculateTimeDifference(dto.getToTime(),dto.getFromTime()));
            }else{
                dto.setActualTripDuration("-NA-");
            }

            if(dto.getToTime() != null && !Comman.isNullOrEmpty(dto.getToTime()) &&
                    dto.getMdcTimeIn() != null && !Comman.isNullOrEmpty(dto.getMdcTimeIn())) {
                dto.setDelayTime(calculateDelay(dto.getToTime(),dto.getMdcTimeIn()+":00"));
            }else{
                dto.setDelayTime("-NA-");
            }
            dto.setTeam(fldTeam.getText().toString().trim());
            dto.setNegative(isNegative);
            dto.setDriverName(fldDriverName.getText().toString().trim());
            dto.setDriverName(fldDriverName.getText().toString().trim());

            dbConnection.insertRecord(dto);

            DriverDTO driverDTO = new DriverDTO();
            driverDTO.setBusNumber(Comman.isNullOrEmpty(dto.getBusNo()) ? "" : dto.getBusNo());
            driverDTO.setDriverId(Comman.isNullOrEmpty(dto.getDriverId()) ? "" : dto.getDriverId());
            driverDTO.setDriverName(Comman.isNullOrEmpty(dto.getDriverName()) ? "" : dto.getDriverName());
            driverDTO.setTeam(Comman.isNullOrEmpty(dto.getTeam()) ? "" : dto.getTeam());
            driverDTO.setDriverId(Comman.isNullOrEmpty(dto.getDriverId()) ? "" : dto.getDriverId());
            driverDTO.setAcftRegn(Comman.isNullOrEmpty(dto.getAcftRegin()) ? "" : dto.getAcftRegin());
            AppController.getInstance().setDriverDTO(driverDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String calculateDelay(String toTime, String fromTime) {
        String result = "00:00:00";
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date1 = format.parse(fromTime);
            Date date2 = format.parse(toTime);
            long diff = date2.getTime() - date1.getTime();
            if(diff < 0){
                isNegative=true;
                diff = -(diff);
            }else{
                isNegative=false;
            }
            result = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(diff),
                    TimeUnit.MILLISECONDS.toMinutes(diff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diff)),
                    TimeUnit.MILLISECONDS.toSeconds(diff) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff)));

            //long difference = date2.getTime() - date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}
