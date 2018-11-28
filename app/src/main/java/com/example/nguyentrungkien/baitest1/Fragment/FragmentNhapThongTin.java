package com.example.nguyentrungkien.baitest1.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nguyentrungkien.baitest1.DAO.SinhVienDAO;
import com.example.nguyentrungkien.baitest1.DTO.SinhVienDTO;
import com.example.nguyentrungkien.baitest1.MainActivity;
import com.example.nguyentrungkien.baitest1.R;

public class FragmentNhapThongTin extends Fragment {

    private EditText edtMSSV, edtHoTen, edtDiemTongKet;
    private RadioGroup radGroup;
    private RadioButton radNam, radNu;
    private Button btnThem;
    private String gioiTinh = "";


    private SinhVienDAO sinhVienDAO;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_nhap_thong_tin,container,false);

        // findViewById
        edtMSSV = view.findViewById(R.id.edtMSSV);
        edtHoTen = view.findViewById(R.id.edtHoTen);
        edtDiemTongKet = view.findViewById(R.id.edtDiemTongKet);
        radGroup = view.findViewById(R.id.radGroup);
        radNam = view.findViewById(R.id.radNam);
        radNu = view.findViewById(R.id.radNu);
        btnThem = view.findViewById(R.id.btnThem);

        gioiTinh = radNam.getText().toString();

        // new sinhvienDAO
        sinhVienDAO = new SinhVienDAO(getContext());
        sinhVienDAO.open();

        radGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.radNam:
                        gioiTinh = "" + radNam.getText().toString();
                        break;
                    case R.id.radNu:
                        gioiTinh = "" + radNu.getText().toString();
                        break;
                }
            }
        });
        // setOnClick cho btnThem
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtMSSV.getText().toString().isEmpty() || edtHoTen.getText().toString().isEmpty() || edtDiemTongKet.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "Các trường không được để trống", Toast.LENGTH_SHORT).show();
                }
                else if(sinhVienDAO.ktraMSSV(edtMSSV.getText().toString()))
                {
                    Toast.makeText(getContext(), "Trùng mã", Toast.LENGTH_SHORT).show();
                }
                else if(Float.parseFloat(edtDiemTongKet.getText().toString()) > 10)
                {
                    Toast.makeText(getContext(),"Điểm không được quá 10", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SinhVienDTO sinhVienDTO = new SinhVienDTO();

                    sinhVienDTO.setMSSV(edtMSSV.getText().toString());
                    sinhVienDTO.setHoTen(edtHoTen.getText().toString());
                    sinhVienDTO.setGioiTinh(gioiTinh);
                    sinhVienDTO.setDiemTongKet(edtDiemTongKet.getText().toString());

                    boolean check = sinhVienDAO.ThemSinhVien(sinhVienDTO);

                    MainActivity main = (MainActivity) getContext();
                    main.reloadFragmentHienThi();
                    if(check)
                    {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
