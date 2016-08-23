package com.example.aayangoud.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aayangoud.myapplication.DTO.TransportFormDTO;

public class DbHelper extends SQLiteOpenHelper {
 
    private static String CREATE_TABLE1;
    //    Database name
    static String DATABASE_NAME = "TransportDB";
    //    Talble name
    public static final String TABLE1_NAME = "transport";
    public static final String TABLE2_NAME = "user";

    //      Fields for table
    public static final String ID = "id";
    public static final String TRANSPORT_DATE = "transportDate";
    public static final String BUSNO = "busNo";
    public static final String DRIVER_NAME = "driverName";
    public static final String PLANNER_NAME = "plannerName";
    public static final String FROM = "from";
    public static final String FROM_TIME = "fromTime";
    public static final String TO = "to";
    public static final String TO_TIME = "toTime";
    public static final String ACFT_REGIN = "acftRegin";
    public static final String MDC_TIME_IN = "mdcTimeIn";
    public static final String MDC_TIME_OUT = "mdcTimeOut";
    public static final String REMARK = "remark";
    public static final String SIGNATURE = "sign";
    public static final String TRANS_TYPE = "transport_type";
    public static final String TEAM = "team";

    // Required resorces to manage database
    private ContentValues cValues;
    private SQLiteDatabase dataBase = null;
    private Cursor cursor;
 
    public DbHelper(Context context) {
        super(context, context.getExternalFilesDir(null).getAbsolutePath()
                + "/" + DATABASE_NAME, null, 1);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        CREATE_TABLE1 = "CREATE TABLE " + TABLE1_NAME + "(" + ID
                + " INTEGER PRIMARY KEY autoincrement, "
                + TRANSPORT_DATE + " TEXT, " + BUSNO + " TEXT, "
                + DRIVER_NAME + " TEXT, " + PLANNER_NAME + " TEXT, "
                + FROM + " TEXT, " + FROM_TIME + " TEXT, "
                + TO + " TEXT, " + TO_TIME + " TEXT, "
                + ACFT_REGIN + " TEXT, " + MDC_TIME_IN + " TEXT, "
                + MDC_TIME_OUT + " TEXT, " + REMARK + " TEXT, " + TEAM + " TEXT,"
                + SIGNATURE + " TEXT, " + TRANS_TYPE + " INTEGER)";


        //+ " TEXT, " + SALARY + " TEXT)";

        db.execSQL(CREATE_TABLE1);
        System.out
                .println("Table is created...........................!");
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
 
        onCreate(db);
    }
 
    public void inserRecord(TransportFormDTO dto) {
        dataBase = getWritableDatabase();
        cValues = new ContentValues();
        cValues.put(TRANSPORT_DATE, dto.getTransportDate());
        cValues.put(BUSNO, dto.getBusNo());
        cValues.put(DRIVER_NAME, dto.getDriverName());
        cValues.put(PLANNER_NAME, dto.getPlannerName());
        cValues.put(FROM, dto.getFrom());
        cValues.put(FROM_TIME, dto.getFromTime());
        cValues.put(TO, dto.getTo());
        cValues.put(TO_TIME, dto.getToTime());
        cValues.put(ACFT_REGIN, dto.getAcftRegin());
        cValues.put(MDC_TIME_IN, dto.getMdcTimeIn());
        cValues.put(MDC_TIME_OUT, dto.getMdcTimeOut());
        cValues.put(REMARK, dto.getRemark());
        cValues.put(SIGNATURE, dto.getSign());
        cValues.put(TRANS_TYPE, dto.getTransport_type());
        cValues.put(TEAM, dto.getTeam());

        // insert data into database
        dataBase.insert(TABLE1_NAME, null, cValues);
 
        dataBase.close();
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
        cursor = dataBase.rawQuery("select * from " + TABLE1_NAME, null);
        return cursor;
    }
 
    public void deleteRecord() {
 
        dataBase = getWritableDatabase();
 
//    Deleting all records from database table
        dataBase.delete(TABLE1_NAME, null, null);
 
        dataBase.close();
    }
 
}