package www.iam_kira.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(383501000001234,'Vijay Biradar',0.00,'vijaybiradar@gmail.com','9876543210','XYZA0003835')");
        db.execSQL("insert into user_table values(383501000001245,'Shit Prasad',10456.232,'shitprasad@gmail.com','8765432109','XYZA0003835')");
        db.execSQL("insert into user_table values(383501000001256,'Baburao Ganpatrao Ampte',50.00,'babubhaiya@gmail.com','7654321098','XYZA0003835')");
        db.execSQL("insert into user_table values(383501000001267,'Jethalal Champaklal Gada',3636363.478,'jethalalgada@gmail.com','6543210987','XYZA0003835')");
        db.execSQL("insert into user_table values(383501000001278,'Babita Iyer',52068093.787,'babitaji@gmail.com','5432109876','BCA65432109')");
        db.execSQL("insert into user_table values(383501000001289,'Atmaram Tukaram Bhide',10934465.387,'bhidetuitionclasses@gmail.com','4321098765','XYZA0003835')");
        db.execSQL("insert into user_table values(383501000001201,'John Wick',502346566.040,'BabaYaga@gmail.com','3210987654','XYZA0003835')");
        db.execSQL("insert into user_table values(383501000001212,'Chandler Bing',1864657.22,'sarcasm@gmail.com','2109876543','XYZA0003835')");
        db.execSQL("insert into user_table values(383501000001223,'Lionel Messi',6209447000.00,'lionelmessi10@gmail.com','1098765432','XYZA0003835')");
        db.execSQL("insert into user_table values(383501000001334,'Cristiano Ronaldo',2710468822.96,'cristianoronaldo07@gmail.com','0987654321','XYZA0003835')");
        db.execSQL("insert into user_table values(383501000001345,'Neymar',3217588796.29,'Neymar10@gmail.com','9987654321','XYZA0003835')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
