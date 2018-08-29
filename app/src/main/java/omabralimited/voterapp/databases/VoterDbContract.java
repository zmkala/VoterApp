package omabralimited.voterapp.databases;

import android.provider.BaseColumns;

public final class VoterDbContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String REAL_TYPE = " REAL";
    public static final String COMMA_SEP = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public VoterDbContract(){

    }

    /* Inner class that defines the table contents */
    public static abstract class User implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_USER_ID = "userId";
        public static final String COLUMN_NAME_PROFILE_PICTURE = "profilePicture";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PHONENUMBER = "phoneNumber";
        public static final String COLUMN_NAME_PASSWORD= "password";
        public static final String COLUMN_NAME_COUNTRY= "country";


        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + User.TABLE_NAME + " (" +
                        User._ID + " INTEGER PRIMARY KEY," +
                        User.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                        User.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                        User.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                        User.COLUMN_NAME_COUNTRY + TEXT_TYPE + COMMA_SEP +
                        User.COLUMN_NAME_PHONENUMBER + TEXT_TYPE + COMMA_SEP +
                        User.COLUMN_NAME_PROFILE_PICTURE + TEXT_TYPE + COMMA_SEP +
                        User.COLUMN_NAME_USER_ID + TEXT_TYPE+
                        " )";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + User.TABLE_NAME;

        public static final String SQL_DELETE_ALL_RECORDS =
                "DELETE FROM " + User.TABLE_NAME;
    }

    public static abstract class Voters implements BaseColumns {
        public static final String TABLE_NAME = "voters";
        public static final String COLUMN_NAME_VOTER_NAME = "voter_name";
        public static final String COLUMN_NAME_VOTER_DOB = "dob";
        public static final String COLUMN_NAME_TIME_OF_REG = "timeOfRegistration";
        public static final String COLUMN_NAME_PICTURE = "picture";
        public static final String COLUMN_NAME_ID_NUMBER  = "idNumber";
        public static final String COLUMN_NAME_PHONE_NUMBER  = "phoneNumber";
        public static final String COLUMN_AREA_REG  = "areaOfRegistration";
        public static final String COLUMN_STATUS  = "status";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + Voters.TABLE_NAME + " (" +
                        Voters._ID + " INTEGER PRIMARY KEY," +
                        Voters.COLUMN_NAME_VOTER_NAME                  + TEXT_TYPE     + COMMA_SEP +
                        Voters.COLUMN_NAME_VOTER_DOB                  + INTEGER_TYPE     + COMMA_SEP +
                        Voters.COLUMN_NAME_TIME_OF_REG    + INTEGER_TYPE  + COMMA_SEP +
                        Voters.COLUMN_NAME_PICTURE                    + TEXT_TYPE     + COMMA_SEP +
                        Voters.COLUMN_NAME_ID_NUMBER                    + REAL_TYPE     + COMMA_SEP +
                        Voters.COLUMN_NAME_PHONE_NUMBER                    + REAL_TYPE     + COMMA_SEP +
                        Voters.COLUMN_AREA_REG                   + TEXT_TYPE     + COMMA_SEP +
                        Voters.COLUMN_STATUS                   + TEXT_TYPE+

                       " )";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + Voters.TABLE_NAME;

        public static final String SQL_DELETE_ALL_RECORDS =
                "DELETE FROM " + Voters.TABLE_NAME;
    }
    public static abstract class Deaths implements BaseColumns {
        public static final String TABLE_NAME = "deaths";
        public static final String COLUMN_NAME_DEATH_NAME = "dead_name";
        public static final String COLUMN_NAME_DEATH_DOB = "dob";
        public static final String COLUMN_NAME_TIME_OF_REG = "timeOfRegistration";
        public static final String COLUMN_NAME_TIME_OF_DEATH = "timeOfDeath";
        public static final String COLUMN_NAME_PICTURE = "picture";
        public static final String COLUMN_NAME_ID_NUMBER  = "idNumber";
        public static final String COLUMN_AREA_REG  = "areaOfRegistration";
        public static final String COLUMN_DEATH_CAUSE  = "causeOfDeath";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + Deaths.TABLE_NAME + " (" +
                        Deaths._ID + " INTEGER PRIMARY KEY," +
                        Deaths.COLUMN_NAME_DEATH_NAME                  + TEXT_TYPE     + COMMA_SEP +
                        Deaths.COLUMN_NAME_DEATH_DOB                  + INTEGER_TYPE     + COMMA_SEP +
                        Deaths.COLUMN_NAME_TIME_OF_REG    + INTEGER_TYPE  + COMMA_SEP +
                        Deaths.COLUMN_NAME_TIME_OF_DEATH    + INTEGER_TYPE  + COMMA_SEP +
                        Deaths.COLUMN_NAME_PICTURE                    + TEXT_TYPE     + COMMA_SEP +
                        Deaths.COLUMN_NAME_ID_NUMBER                    + REAL_TYPE     + COMMA_SEP +
                        Deaths.COLUMN_AREA_REG                   + TEXT_TYPE     + COMMA_SEP +
                        Deaths.COLUMN_DEATH_CAUSE                   + TEXT_TYPE     +

                        " )";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + Deaths.TABLE_NAME;

        public static final String SQL_DELETE_ALL_RECORDS =
                "DELETE FROM " + Deaths.TABLE_NAME;
    }
    }


