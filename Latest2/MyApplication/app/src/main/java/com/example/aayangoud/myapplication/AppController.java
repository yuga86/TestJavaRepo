package com.example.aayangoud.myapplication;

/**
 * Created by Aayan Goud on 8/28/2016.
 */

import android.app.Application;
import android.content.Context;

import com.example.aayangoud.myapplication.DTO.DriverDTO;

import java.util.HashMap;

/**
 * This class creates the single instance for same request, multiple calls.
 *
 * @version 1.0.0
 */
public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;
    private Context mContext;
    private HashMap<String, String> driversInfoMap;

    public HashMap<String, String> getDriversInfoMap() {
        return driversInfoMap;
    }

    /*public void setDriversInfoMap() {
        driversInfoMap;
    }*/


    public DriverDTO getDriverDTO() {
        return driverDTO;
    }

    public void setDriverDTO(DriverDTO driverDTO) {
        this.driverDTO = driverDTO;
    }

    private DriverDTO driverDTO;

    @Override
    public void onCreate() {

        super.onCreate();
        mInstance = this;
        mContext = this;
        driversInfoMap = new HashMap<String, String>();
        driversInfoMap.put("TG000713", "Mohana Murali Reddy Kovvuri");
        driversInfoMap.put("TG069844", "Joshua Miano Gichuki");
        driversInfoMap.put("TG069829", "Danish Masood");
        driversInfoMap.put("TG005162", "Pradeepan Adunkudi");
        driversInfoMap.put("TG050080", "Kanaka Raju Seelam");
        driversInfoMap.put("TG030585", "Navesh Peedikakandy");
        driversInfoMap.put("TG057765", "Ameer Puthanangadputhenpura Abdul Khader");
        driversInfoMap.put("TG024587", "Saheer Vilangalil");
        driversInfoMap.put("TG001420", "Amjad Ali");
        driversInfoMap.put("TG057608", "Shefeeq Karengal Hussain");
        driversInfoMap.put("TG031366", "Sreejith Mattemal Thuyyath");
        driversInfoMap.put("TG050754", "Rameen Ellikkal Kalathil");
        driversInfoMap.put("TG073299", "Patrick Mochu Njunge");

        //String value = (String) newMap.get("my_code");
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }


    private void handleUncaughtException(Thread thread, Throwable e) {
        try {
            System.exit(1);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }
}
