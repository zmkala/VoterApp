package omabralimited.voterapp.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import omabralimited.voterapp.DeadInfo;
import omabralimited.voterapp.VoterInfo;


public class VoterDbHelper extends SQLiteOpenHelper {


    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION =10;
    public static final String DATABASE_NAME = "Shop.db";
    private SQLiteDatabase database;
    public VoterDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        database = db;
        db.execSQL(VoterDbContract.User.SQL_CREATE_TABLE);
        db.execSQL(VoterDbContract.Voters.SQL_CREATE_TABLE);
        db.execSQL(VoterDbContract.Deaths.SQL_CREATE_TABLE);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(VoterDbContract.User.SQL_DELETE_TABLE);
        db.execSQL(VoterDbContract.Voters.SQL_DELETE_TABLE);
        db.execSQL(VoterDbContract.Deaths.SQL_CREATE_TABLE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public boolean insertUser(String name, String country, String email, String phoneNumber, String profPic, String password
             ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VoterDbContract.User.COLUMN_NAME_NAME, name);
        contentValues.put(VoterDbContract.User.COLUMN_NAME_PROFILE_PICTURE, profPic);
        contentValues.put(VoterDbContract.User.COLUMN_NAME_EMAIL, email);
        contentValues.put(VoterDbContract.User.COLUMN_NAME_PHONENUMBER, phoneNumber);
        contentValues.put(VoterDbContract.User.COLUMN_NAME_PASSWORD, password);
        contentValues.put(VoterDbContract.User.COLUMN_NAME_COUNTRY, country);
        db.insert(VoterDbContract.User.TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updatePerson(Integer id, String name, String country, String email, String phoneNumber, String profPic, String password
            ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VoterDbContract.User.COLUMN_NAME_NAME, name);
        contentValues.put(VoterDbContract.User.COLUMN_NAME_PROFILE_PICTURE, profPic);
        contentValues.put(VoterDbContract.User.COLUMN_NAME_EMAIL, email);
        contentValues.put(VoterDbContract.User.COLUMN_NAME_PHONENUMBER,phoneNumber);
        contentValues.put(VoterDbContract.User.COLUMN_NAME_PASSWORD, password);
        contentValues.put(VoterDbContract.User.COLUMN_NAME_COUNTRY, country);
        db.update(VoterDbContract.User.TABLE_NAME, contentValues, VoterDbContract.User.COLUMN_NAME_USER_ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }
    public Cursor getPerson(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + VoterDbContract.User.TABLE_NAME + " WHERE " +
                VoterDbContract.User.COLUMN_NAME_EMAIL + "=?", new String[] { email } );
        return res;
    }
    public Cursor getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + VoterDbContract.User.TABLE_NAME, null);
        return res;
    }
    public String getSingleEntry(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + VoterDbContract.User.TABLE_NAME + " WHERE " +
                VoterDbContract.User.COLUMN_NAME_EMAIL + "=?", new String[]{email});
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(VoterDbContract.User.COLUMN_NAME_PASSWORD));
        cursor.close();
        return password;
    }
    public boolean insertVoter(String voter_name, String profPic, String dob, String idNum, String regArea, String regTime, String phoneNumber) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VoterDbContract.Voters.COLUMN_NAME_VOTER_NAME,voter_name);
        contentValues.put(VoterDbContract.Voters.COLUMN_NAME_PICTURE, profPic);
        contentValues.put(VoterDbContract.Voters.COLUMN_NAME_VOTER_DOB, dob);
        contentValues.put(VoterDbContract.Voters.COLUMN_NAME_ID_NUMBER, idNum);
        contentValues.put(VoterDbContract.Voters.COLUMN_NAME_PHONE_NUMBER, phoneNumber);
        contentValues.put(VoterDbContract.Voters.COLUMN_AREA_REG, regArea);
        contentValues.put(VoterDbContract.Voters.COLUMN_NAME_TIME_OF_REG, regTime);
        System.out.println(contentValues);

        db.insert(VoterDbContract.Voters.TABLE_NAME, null, contentValues);
        return true;
    }
    public boolean insertDeath(String dead_name, String profPic, String dob, String idNum, String regArea, String regTime, String deathCause, String deathTime) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VoterDbContract.Deaths.COLUMN_NAME_DEATH_NAME,dead_name);
        contentValues.put(VoterDbContract.Deaths.COLUMN_NAME_PICTURE, profPic);
        contentValues.put(VoterDbContract.Deaths.COLUMN_NAME_DEATH_DOB, dob);
        contentValues.put(VoterDbContract.Deaths.COLUMN_NAME_ID_NUMBER, idNum);
        contentValues.put(VoterDbContract.Deaths.COLUMN_AREA_REG, regArea);
        contentValues.put(VoterDbContract.Deaths.COLUMN_NAME_TIME_OF_REG, regTime);
        contentValues.put(VoterDbContract.Deaths.COLUMN_DEATH_CAUSE, deathCause);
        contentValues.put(VoterDbContract.Deaths.COLUMN_NAME_TIME_OF_DEATH, deathTime);
        System.out.println(contentValues);

        db.insert(VoterDbContract.Deaths.TABLE_NAME, null, contentValues);
        return true;
    }
    public ArrayList<VoterInfo> getAllVoters() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<VoterInfo> arrVoters = new ArrayList<VoterInfo>();
        VoterInfo voterInfo=null;
        Cursor res = db.rawQuery("SELECT * FROM " + VoterDbContract.Voters.TABLE_NAME, null);
       res.moveToFirst();
        while (res.isAfterLast() == false)
        {
        String voter_name = res.getString(res.getColumnIndex(VoterDbContract.Voters.COLUMN_NAME_VOTER_NAME));
        String picture = res.getString(res.getColumnIndex(VoterDbContract.Voters.COLUMN_NAME_PICTURE));
        String dob = res.getString(res.getColumnIndex(VoterDbContract.Voters.COLUMN_NAME_VOTER_DOB));
        String idNum = res.getString(res.getColumnIndex(VoterDbContract.Voters.COLUMN_NAME_ID_NUMBER));
        String phoneNumber = res.getString(res.getColumnIndex(VoterDbContract.Voters.COLUMN_NAME_PHONE_NUMBER));
        String areaReg = res.getString(res.getColumnIndex(VoterDbContract.Voters.COLUMN_AREA_REG));
        //String timeOfReg = res.getString(res.getColumnIndex(VoterDbContract.Voters.COLUMN_NAME_TIME_OF_REG));
        String id = res.getString(res.getColumnIndex(VoterDbContract.Voters._ID));

        VoterInfo voter  = new VoterInfo();
        voter.setAreaReg(areaReg);
        voter.setVoter_name(voter_name);
        voter.setProfilePic(picture);
        voter.setDob(dob);
        voter.setIdNum(idNum);
        voter.setPhoneNumber(phoneNumber);
        voter.setId(id);
//        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            String formattedDate = df.format(c.getTime());
        voter.setTimeOfReg(formattedDate);


        if (voter != null)
        {

            arrVoters.add(voter);

              }
           res.moveToNext();
        }
        res.close();
        return arrVoters;
    }
    public ArrayList<DeadInfo> getAllDeaths() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DeadInfo> arrDeaths = new ArrayList<DeadInfo>();
        VoterInfo deadInfo=null;
        Cursor res = db.rawQuery("SELECT * FROM " + VoterDbContract.Deaths.TABLE_NAME, null);
        res.moveToFirst();
        while (res.isAfterLast() == false)
        {
            String dead_name = res.getString(res.getColumnIndex(VoterDbContract.Deaths.COLUMN_NAME_DEATH_NAME));
            String picture = res.getString(res.getColumnIndex(VoterDbContract.Voters.COLUMN_NAME_PICTURE));
            String dob = res.getString(res.getColumnIndex(VoterDbContract.Deaths.COLUMN_NAME_DEATH_DOB));
            String idNum = res.getString(res.getColumnIndex(VoterDbContract.Deaths.COLUMN_NAME_ID_NUMBER));
            String areaReg = res.getString(res.getColumnIndex(VoterDbContract.Deaths.COLUMN_AREA_REG));
            String deathCause = res.getString(res.getColumnIndex(VoterDbContract.Deaths.COLUMN_DEATH_CAUSE));
            String deathTime = res.getString(res.getColumnIndex(VoterDbContract.Deaths.COLUMN_NAME_TIME_OF_DEATH));
            //String timeOfReg = res.getString(res.getColumnIndex(VoterDbContract.Voters.COLUMN_NAME_TIME_OF_REG));
            String id = res.getString(res.getColumnIndex(VoterDbContract.Voters._ID));

            DeadInfo dead  = new DeadInfo();
            dead.setAreaReg(areaReg);
            dead.setDead_name(dead_name);
            dead.setProfilePic(picture);
            dead.setDob(dob);
            dead.setIdNum(idNum);
            dead.setId(id);
            dead.setDeathCause(deathCause);
            dead.setDeathTime(deathTime);
//        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            String formattedDate = df.format(c.getTime());
            dead.setTimeOfReg(formattedDate);


            if (dead != null)
            {

                arrDeaths.add(dead);

            }
            res.moveToNext();
        }
        res.close();
        return arrDeaths;
    }
    public Cursor getVoter(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + VoterDbContract.Voters.TABLE_NAME + " WHERE " +
                VoterDbContract.Voters._ID + "=?", new String[]{Integer.toString(id)});

        return res;
    }
    public Cursor getDeadVictim(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + VoterDbContract.Deaths.TABLE_NAME + " WHERE " +
                VoterDbContract.Deaths.COLUMN_NAME_ID_NUMBER+ "=?", new String[]{Integer.toString(id)});

        return res;
    }
    public String getDeadVoter(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + VoterDbContract.Voters.TABLE_NAME + " WHERE " +
                VoterDbContract.Voters.COLUMN_NAME_ID_NUMBER + "=?", new String[]{id});
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.close();
        return  "EXISTS";
    }
    public boolean updateVoter(Integer id, String voter_name, String profPic, String dob, String idNum, String regArea, String regTime, String phoneNumber, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VoterDbContract.Voters.COLUMN_NAME_VOTER_NAME,voter_name);
        contentValues.put(VoterDbContract.Voters.COLUMN_NAME_PICTURE, profPic);
        contentValues.put(VoterDbContract.Voters.COLUMN_NAME_VOTER_DOB, dob);
        contentValues.put(VoterDbContract.Voters.COLUMN_NAME_ID_NUMBER, idNum);
        contentValues.put(VoterDbContract.Voters.COLUMN_NAME_PHONE_NUMBER, phoneNumber);
        contentValues.put(VoterDbContract.Voters.COLUMN_AREA_REG, regArea);
        contentValues.put(VoterDbContract.Voters.COLUMN_NAME_TIME_OF_REG, regTime);
        contentValues.put(VoterDbContract.Voters.COLUMN_STATUS, status);

        db.update(VoterDbContract.Voters.TABLE_NAME, contentValues, VoterDbContract.Voters._ID  + " = ? ", new String[]{Integer.toString(id)} );
        return true;
    }
    public boolean updateDead(Integer id, String dead_name, String profPic, String dob, String idNum, String regArea, String regTime, String deathCause, String deathTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VoterDbContract.Deaths.COLUMN_NAME_DEATH_NAME,dead_name);
        contentValues.put(VoterDbContract.Deaths.COLUMN_NAME_PICTURE, profPic);
        contentValues.put(VoterDbContract.Deaths.COLUMN_NAME_DEATH_DOB, dob);
        contentValues.put(VoterDbContract.Deaths.COLUMN_NAME_ID_NUMBER, idNum);
        contentValues.put(VoterDbContract.Deaths.COLUMN_AREA_REG, regArea);
        contentValues.put(VoterDbContract.Deaths.COLUMN_NAME_TIME_OF_REG, regTime);
        contentValues.put(VoterDbContract.Deaths.COLUMN_DEATH_CAUSE, deathCause);
        contentValues.put(VoterDbContract.Deaths.COLUMN_NAME_TIME_OF_DEATH, deathTime);

        db.update(VoterDbContract.Deaths.TABLE_NAME, contentValues, VoterDbContract.Deaths._ID  + " = ? ", new String[]{Integer.toString(id)} );
        return true;
    }
    public boolean updateVoterStatus (int idNum, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VoterDbContract.Voters.COLUMN_STATUS, status);
        db.update(VoterDbContract.Voters.TABLE_NAME, contentValues, VoterDbContract.Voters.COLUMN_NAME_ID_NUMBER  + " = ? ", new String[]{Integer.toString(idNum)} );
        return true;
    }

}
