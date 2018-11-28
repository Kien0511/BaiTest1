package com.example.nguyentrungkien.baitest1;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nguyentrungkien.baitest1.Adapter.MainAdapter;
import com.example.nguyentrungkien.baitest1.Fragment.FragmentHienThiThongTin;
import com.example.nguyentrungkien.baitest1.Fragment.FragmentNhapThongTin;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    MainAdapter adapter;
    FragmentNhapThongTin fragmentNhapThongTin;
    FragmentHienThiThongTin fragmentHienThiThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        viewPager.setOffscreenPageLimit(2);

        fragmentNhapThongTin = new FragmentNhapThongTin();
        fragmentHienThiThongTin = new FragmentHienThiThongTin();
        setViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setViewPager(ViewPager viewPager)
    {
        adapter = new MainAdapter(getSupportFragmentManager());
        adapter.addFragment(fragmentNhapThongTin, "Nhập Thông Tin");
        adapter.addFragment(fragmentHienThiThongTin, "Hiển Thị");
        viewPager.setAdapter(adapter);
    }

    public void reloadFragmentHienThi()
    {
       fragmentHienThiThongTin = (FragmentHienThiThongTin) adapter.getFragmentList().get(1);
       fragmentHienThiThongTin.reload();
    }
}
