package com.example.nguyentrungkien.baitest1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nguyentrungkien.baitest1.DTO.SinhVienDTO;
import com.example.nguyentrungkien.baitest1.Database.DBQuanLyDiemTongKet;

import java.util.ArrayList;

public class SinhVienDAO {

    DBQuanLyDiemTongKet dbQuanLyDiemTongKet;
    SQLiteDatabase database;

    public SinhVienDAO(Context context) {
        dbQuanLyDiemTongKet = new DBQuanLyDiemTongKet(context);
    }

    public void open()
    {
        database = dbQuanLyDiemTongKet.getWritableDatabase();
    }

    public void close()
    {
        database.close();
    }

    public boolean ThemSinhVien(SinhVienDTO sinhVienDTO)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBQuanLyDiemTongKet.TB_MSSV,sinhVienDTO.getMSSV());
        contentValues.put(DBQuanLyDiemTongKet.TB_HOTEN,sinhVienDTO.getHoTen());
        contentValues.put(DBQuanLyDiemTongKet.TB_GIOITINH,sinhVienDTO.getGioiTinh());
        contentValues.put(DBQuanLyDiemTongKet.TB_DIEMTONGKET,sinhVienDTO.getDiemTongKet());

        long id = database.insert(DBQuanLyDiemTongKet.DB_TABLE,null,contentValues);

        if(id != 0)
        {
            Log.e("avcv","cscscs");
            return true;
        }
        else
        {
            return false;
        }
    }


    public ArrayList<SinhVienDTO> getList ()
    {
        ArrayList<SinhVienDTO> list = new ArrayList<>();

        String query = "select * from " + DBQuanLyDiemTongKet.DB_TABLE;

        Cursor cursor = database.rawQuery(query,null);

        Log.e("countcursor", String.valueOf(cursor.getCount()));
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            SinhVienDTO sv = new SinhVienDTO();
            sv.setMSSV(cursor.getString(0));
            sv.setHoTen(cursor.getString(1));
            sv.setGioiTinh(cursor.getString(2));
            sv.setDiemTongKet(cursor.getString(3));

            list.add(sv);

            Log.e("listsv",sv.getMSSV());
            cursor.moveToNext();
        }

        cursor.close();

        return list;
    }

    public boolean ktraMSSV (String MSSV)
    {
        String query = "select * from " + DBQuanLyDiemTongKet.DB_TABLE + " where " + DBQuanLyDiemTongKet.TB_MSSV + " = '" + MSSV + "'";
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0)
        {
            cursor.close();
            return true;
        }
        else
        {
            cursor.close();
            return false;
        }

    }

    public void updateSinhVien(SinhVienDTO sinhVienDTO)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBQuanLyDiemTongKet.TB_HOTEN,sinhVienDTO.getHoTen());
        contentValues.put(DBQuanLyDiemTongKet.TB_GIOITINH,sinhVienDTO.getGioiTinh());
        contentValues.put(DBQuanLyDiemTongKet.TB_DIEMTONGKET,sinhVienDTO.getDiemTongKet());
        database.update(DBQuanLyDiemTongKet.DB_TABLE,contentValues,DBQuanLyDiemTongKet.TB_MSSV + " = '" + sinhVienDTO.getMSSV() + "'",null);
    }

    public void deleteSinhVien(String MSSV)
    {
        database.delete(DBQuanLyDiemTongKet.DB_TABLE,DBQuanLyDiemTongKet.TB_MSSV + " = '" + MSSV + "'",null);
    }
}
