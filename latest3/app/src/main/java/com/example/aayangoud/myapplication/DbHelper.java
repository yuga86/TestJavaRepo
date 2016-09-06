package com.example.aayangoud.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aayangoud.myapplication.DTO.TransportFormDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
 
    private static String CREATE_TABLE1;
    //    Database name
    static String DATABASE_NAME = "TransportDB.db";
    //    Talble name
    public static final String TABLE1_NAME = "transport";
    public static final String TABLE2_NAME = "user";

    //      Fields for table
    public static final String TNSID = "transId";
    public static final String TRANSPORT_DATE = "transportDate";
    public static final String BUSNO = "busNo";
    public static final String DRIVER_NAME = "driverName";
    public static final String PLANNER_NAME = "plannerName";
    public static final String FROM = "fromPlace";
    public static final String FROM_TIME = "fromTime";
    public static final String TO = "toPlace";
    public static final String TO_TIME = "toTime";
    public static final String ACFT_REGIN = "acftRegin";
    public static final String MDC_TIME_IN = "mdcTimeIn";
    public static final String MDC_TIME_OUT = "mdcTimeOut";
    public static final String REMARK = "remark";
    public static final String SIGNATURE = "sign";
    public static final String TRANS_TYPE = "transportType";
    public static final String TEAM = "team";
    public static final String CREATED_DATE = "createdDate";
    public static final String UPDATED_DATE = "updatedDate";
    public static final String IS_NEGATIVE = "isNegative";
    public static final String DRIVER_ID = "driverId";
    public static final String ACTUAL_DURATION = "actualDuration";
    public static final String DELAY_TIME = "delayTime";

    // Required resorces to manage database
    private ContentValues cValues;
    private SQLiteDatabase dataBase = null;
    private Cursor cursor;
    SQLiteDatabase db;
 
    public DbHelper(Context context) {
        /*super(context, context.getExternalFilesDir(null).getAbsolutePath()
                + "/" + DATABASE_NAME, null, 1);*/
        super(context, DATABASE_NAME, null, 1);
        db = getWritableDatabase();
            }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            CREATE_TABLE1 = "CREATE TABLE " + TABLE1_NAME + " (" + TNSID + " INTEGER PRIMARY KEY autoincrement, "
                    + TRANSPORT_DATE + " TEXT, " + BUSNO + " TEXT, "
                    + DRIVER_NAME + " TEXT, " + PLANNER_NAME + " TEXT, "
                    + FROM + " TEXT, " + FROM_TIME + " TEXT, "
                    + TO + " TEXT, " + TO_TIME + " TEXT, "
                    + ACFT_REGIN + " TEXT, " + MDC_TIME_IN + " TEXT, " + IS_NEGATIVE + " INTEGER, "
                    + MDC_TIME_OUT + " TEXT, " + REMARK + " TEXT, " + TEAM + " TEXT, "
                    + ACTUAL_DURATION + " TEXT, " + DELAY_TIME + " TEXT, "
                    + CREATED_DATE + " TEXT, " + UPDATED_DATE + " TEXT, " + DRIVER_ID + " TEXT,"
                    + SIGNATURE + " TEXT, " + TRANS_TYPE + " INTEGER);";
            //CREATE_TABLE1 = "CREATE TABLE " + TABLE1_NAME + " ("+ TNSID + " INTEGER PRIMARY KEY autoincrement," + TRANSPORT_DATE + " TEXT,"+ BUSNO + " TEXT," + DRIVER_NAME + " TEXT," + PLANNER_NAME + " TEXT," + FROM + " TEXT," + FROM_TIME + " TEXT," + TO + " TEXT," + TO_TIME + " TEXT," + ACFT_REGIN + " TEXT," + MDC_TIME_IN + " TEXT," + REMARK + " INTEGER," + MDC_TIME_OUT + " TEXT," + TEAM + " TEXT," + ACTUAL_DURATION + " TEXT," + DELAY_TIME + " TEXT," + CREATED_DATE + " TEXT," + UPDATED_DATE + " TEXT," + DRIVER_ID + " TEXT," + SIGNATURE + " TEXT," + TRANS_TYPE + " TEXT" + ")";
            /*CREATE_TABLE1 = "CREATE TABLE " + TABLE1_NAME + " ("+ TNSID + " INTEGER PRIMARY KEY autoincrement," + TRANSPORT_DATE + " TEXT,"
                    + BUSNO + " TEXT," + DRIVER_NAME + " TEXT,"
                    + PLANNER_NAME + " TEXT," + FROM + " TEXT,"
                    + FROM_TIME + " TEXT," + TO + " TEXT,"
                    + TO_TIME + " TEXT," + ACFT_REGIN + " TEXT,"
                    + MDC_TIME_IN + " TEXT," + REMARK + " INTEGER,"
                    + MDC_TIME_OUT + " TEXT," + TEAM + " TEXT,"
                    + ACTUAL_DURATION + " TEXT," + DELAY_TIME + " TEXT,"
                    + CREATED_DATE + " TEXT," + UPDATED_DATE + " TEXT,"
                    + DRIVER_ID + " TEXT," + SIGNATURE + " TEXT,"
                    + TRANS_TYPE + " TEXT" + ")";*/


            //+ " TEXT, " + SALARY + " TEXT)";

            db.execSQL(CREATE_TABLE1);
            Log.e("Databaes====>","created Sucessfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
 
        onCreate(db);
    }
 
    public void insertRecord(TransportFormDTO dto) {
        try {
            dataBase = getWritableDatabase();
            cValues = new ContentValues();
            cValues.put(TRANSPORT_DATE, dto.getTransportDate());
            cValues.put(BUSNO, dto.getBusNo());
            cValues.put(DRIVER_NAME, dto.getDriverName());
            cValues.put(DRIVER_ID, dto.getDriverId());
            cValues.put(FROM, dto.getFrom());
            cValues.put(FROM_TIME, dto.getFromTime());
            cValues.put(TO, dto.getTo());
            cValues.put(TO_TIME, dto.getToTime());
            cValues.put(ACFT_REGIN, dto.getAcftRegin());
            cValues.put(MDC_TIME_IN, dto.getMdcTimeIn());
            cValues.put(MDC_TIME_OUT, dto.getMdcTimeOut());
            cValues.put(REMARK, dto.getRemark());
            cValues.put(SIGNATURE, dto.getSign());
            cValues.put(TRANS_TYPE, dto.getTransportType());
            cValues.put(IS_NEGATIVE, dto.isNegative());
            cValues.put(TEAM, dto.getTeam());
            cValues.put(ACTUAL_DURATION, dto.getActualTripDuration());
            cValues.put(DELAY_TIME, dto.getDelayTime());
            cValues.put(CREATED_DATE, new Date().toString());
            cValues.put(CREATED_DATE, new Date().toString());

            // insert data into database
            dataBase.insert(TABLE1_NAME, null, cValues);

            dataBase.close();
        } catch (Exception e) {
            e.printStackTrace();
            dataBase.close();
        }
    }
 
 /*   public void updateRecord(String name, String salary) {
 
        dataBase = getWritableDatabase();
 
        cValues = new ContentValues();
 
        cValues.put(NAME, name);
        cValues.put(SALARY, salary);
//    Update data from database table
        dataBase.update(DbHelper.TABLE1_NAME, cValues,
                null, null);
 
        dataBase.close();
    }*/
 
    public Cursor selectRecords() {
 
        dataBase = getReadableDatabase();
 
//    Getting data from database table
        cursor = dataBase.rawQuery("select * from " + TABLE1_NAME+ " ORDER BY "+TNSID+" DESC", null);
        return cursor;
    }

    public List<TransportFormDTO> getAllTraportDetails(){
        List<TransportFormDTO>  transList = new ArrayList<TransportFormDTO>();
        try {
            dataBase = getReadableDatabase();
            cursor = dataBase.rawQuery("select * from " + TABLE1_NAME+ " ORDER BY "+TNSID+" DESC", null);
            if (cursor.moveToFirst()) {
                do {
                    TransportFormDTO transDTO = new TransportFormDTO();
                    transDTO.setId(cursor.getString(0));
                    transDTO.setTransportDate(cursor.getString(1));
                    transDTO.setBusNo(cursor.getString(2));
                    transDTO.setDriverName(cursor.getString(3));
                    //4->planner number
                    transDTO.setFrom(cursor.getString(5));
                    transDTO.setFromTime(cursor.getString(6));
                    transDTO.setTo(cursor.getString(7));
                    transDTO.setToTime(cursor.getString(8));
                    transDTO.setAcftRegin(cursor.getString(9));
                    transDTO.setMdcTimeIn(cursor.getString(10));
                    transDTO.setNegative((cursor.getInt(11)==1) ? true : false);
                    transDTO.setMdcTimeOut(cursor.getString(12));
                    transDTO.setRemark(cursor.getString(13));
                    transDTO.setTeam(cursor.getString(14));
                    transDTO.setActualTripDuration(cursor.getString(15));
                    transDTO.setDelayTime(cursor.getString(16));
                    transDTO.setCreatedDate(cursor.getString(17));
                    //18-updated date
                    transDTO.setDriverId(cursor.getString(19));
                    //20- sign
                    transDTO.setTransportType(cursor.getInt(21));
                    transList.add(transDTO);
                } while (cursor.moveToNext());
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
            return transList;
    }

    public void deleteRecord() {
 
        dataBase = getWritableDatabase();
 
//    Deleting all records from database table
        dataBase.delete(TABLE1_NAME, null, null);
 
        dataBase.close();
    }
 
}