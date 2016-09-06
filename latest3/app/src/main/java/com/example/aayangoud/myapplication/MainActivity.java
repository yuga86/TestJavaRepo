package com.example.aayangoud.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.aayangoud.myapplication.DTO.TransportFormDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnDrop, btnPickUp;
    private DbHelper dbConnection;
    private Context mContext;
    private List<TransportFormDTO> transList;
    private ProgressDialog mProgressDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Transguard - Transportation");
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        mContext =this;
       //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Transguard");
        //setSupportActionBar(toolbar);

        btnDrop =  (Button)findViewById(R.id.btnDrop);
        btnPickUp =(Button)findViewById(R.id.btnPickUp);
        dbConnection = new DbHelper(this);
        mProgressDailog = new ProgressDialog(MainActivity.this,R.style.myProgressDialog);
        mProgressDailog.setCancelable(false);
        mProgressDailog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        initListeners();

    }

    private void initListeners(){
        btnDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,DropActivity.class));
            }
        });

        btnPickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(MainActivity.this,PickupActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            try {
                new AsyncCaller().execute();

                /*for(TransportFormDTO dto : transList) {
                    Log.e("TRANSPORT_DATE ", dto.getTransportDate());
                    Log.e("BUSNO ", dto.getBusNo());
                    Log.e("DRIVER_NAME ", dto.getDriverName());
                    Log.e("DRIVER_ID ", dto.getDriverId());
                    Log.e("FROM ", dto.getFrom());
                    Log.e("FROM_TIME ", dto.getFromTime());
                    Log.e("TO ", dto.getTo());
                    Log.e("TO_TIME ", dto.getToTime());
                    Log.e("ACFT_REGIN ", dto.getAcftRegin());
                    Log.e("MDC_TIME_IN ", dto.getMdcTimeIn());
                    Log.e("MDC_TIME_OUT ", dto.getMdcTimeOut());
                    Log.e("REMARK ", dto.getRemark());
                    Log.e("SIGNATURE ", dto.getSign()+"");
                    Log.e("TRANS_TYPE ", dto.getTransportType()+"");
                    Log.e("IS_NEGATIVE ", dto.isNegative()+"");
                    Log.e("TEAM ", dto.getTeam());
                    Log.e("ACTUAL_DURATION ", dto.getActualTripDuration());
                    Log.e("DELAY_TIME ", dto.getDelayTime());
                    Log.e("====> ", "===========>");

                }*/
                /*File Db = new File("/data/data/com/example/aayangoud/myapplication/databases/TransportDB");
                File file = new File("H:\\AndroidWorkSpace\\Thiru\\transguard.db");
                file.setWritable(true);
                copyFile(new FileInputStream(Db), new FileOutputStream(file));*/
            } catch (Exception e) {
                e.printStackTrace();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public static void copyFile(FileInputStream fromFile, FileOutputStream toFile) throws IOException {
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            fromChannel = fromFile.getChannel();
            toChannel = toFile.getChannel();
            fromChannel.transferTo(0, fromChannel.size(), toChannel);
        } finally {
            try {
                if (fromChannel != null) {
                    fromChannel.close();
                }
            } finally {
                if (toChannel != null) {
                    toChannel.close();
                }
            }
        }
    }

    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
        String fileName = new Date().getTime()+".xls";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDailog.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            transList = dbConnection.getAllTraportDetails();

            WriteInExcel.saveExcelFile(mContext,fileName,transList);
            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //this method will be running on UI thread
            mProgressDailog.dismiss();
            Toast.makeText(mContext,fileName+" Created Successfully ",Toast.LENGTH_LONG).show();
        }

    }

}
