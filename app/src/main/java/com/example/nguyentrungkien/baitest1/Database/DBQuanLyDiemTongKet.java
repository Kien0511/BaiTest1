package com.example.nguyentrungkien.baitest1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBQuanLyDiemTongKet extends SQLiteOpenHelper {

    public static final String DB_NAME = "qlsv";
    public static final int DB_VERSION = 1;
    public static final String DB_TABLE = "sinhvien";
    public static final String TB_MSSV = "mssv";
    public static final String TB_HOTEN = "hoten";
    public static final String TB_GIOITINH = "gioitinh";
    public static final String TB_DIEMTONGKET = "diemtongket";

    public DBQuanLyDiemTongKet(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + DB_TABLE + " ( " + TB_MSSV + " text primary key, " + TB_HOTEN + " text, " + TB_GIOITINH + " text, " + TB_DIEMTONGKET + " text )";
        sqLiteDatabase.execSQL(query);

        for(int i = 0; i < 10; i++)
        {
            String queryInsert = "insert into " + DB_TABLE + " values ( " + i + ", " + " 'A', 'Nam', '9') " ;
            sqLiteDatabase.execSQL(queryInsert);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + DB_TABLE);
        onCreate(sqLiteDatabase);
    }
}
