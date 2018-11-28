package com.example.nguyentrungkien.baitest1.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyentrungkien.baitest1.Adapter.ListSinhVienAdapter;
import com.example.nguyentrungkien.baitest1.DAO.SinhVienDAO;
import com.example.nguyentrungkien.baitest1.DTO.SinhVienDTO;
import com.example.nguyentrungkien.baitest1.R;

import java.util.ArrayList;

public class FragmentHienThiThongTin extends Fragment {
    private RecyclerView rcvListSinhVien;
    private ArrayList<SinhVienDTO> list;
    SinhVienDAO sinhVienDAO;
    ;
    ListSinhVienAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_hien_thi_thong_tin,container,false);

        list = new ArrayList<>();

        sinhVienDAO = new SinhVienDAO(getContext());
        sinhVienDAO.open();
        rcvListSinhVien = view.findViewById(R.id.rcvListSinhVien);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvListSinhVien.setLayoutManager(layoutManager);

        list = sinhVienDAO.getList();

        adapter = new ListSinhVienAdapter(getContext(),list);

        rcvListSinhVien.setAdapter(adapter);



        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void reload()
    {
        list = sinhVienDAO.getList();

        adapter = new ListSinhVienAdapter(getContext(),list);

        rcvListSinhVien.setAdapter(adapter);
    }
}
