package com.example.nguyentrungkien.baitest1.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyentrungkien.baitest1.DAO.SinhVienDAO;
import com.example.nguyentrungkien.baitest1.DTO.SinhVienDTO;
import com.example.nguyentrungkien.baitest1.MainActivity;
import com.example.nguyentrungkien.baitest1.R;

import java.util.ArrayList;

public class ListSinhVienAdapter extends RecyclerView.Adapter<ListSinhVienAdapter.ViewHolder>{

    Context context;
    ArrayList<SinhVienDTO> list;
    String gioitinh;

    public ListSinhVienAdapter(Context context, ArrayList<SinhVienDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_sinh_vien,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.txtMSSV.setText(list.get(position).getMSSV());
        holder.txtHoTen.setText(list.get(position).getHoTen());
        holder.txtGioiTinh.setText(list.get(position).getGioiTinh());
        holder.txtDiemTongKet.setText(list.get(position).getDiemTongKet());

        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.sinhVienDAO = new SinhVienDAO(context);
                holder.sinhVienDAO.open();
                holder.sinhVienDAO.deleteSinhVien(list.get(position).getMSSV());
                list.remove(position);
                notifyItemRemoved(position);
            }
        });

        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setTitle("Sửa thông tin sinh viên");
                dialog.setContentView(R.layout.dialog_sua_sinh_vien);

                final EditText edtMSSV = dialog.findViewById(R.id.edtMSSV);
                final EditText edtHoTen = dialog.findViewById(R.id.edtHoTen);
                final EditText edtDiemTongKet = dialog.findViewById(R.id.edtDiemTongKet);
                RadioGroup radGroup = dialog.findViewById(R.id.radGroup);
                final RadioButton radNam = dialog.findViewById(R.id.radNam);
                final RadioButton radNu = dialog.findViewById(R.id.radNu);

                final Button btnSua = dialog.findViewById(R.id.btnSua);
                Button btnHuy = dialog.findViewById(R.id.btnHuy);

                gioitinh = list.get(position).getGioiTinh().toString();
                edtMSSV.setText(list.get(position).getMSSV().toString());
                edtHoTen.setText(list.get(position).getHoTen().toString());
                edtDiemTongKet.setText(list.get(position).getDiemTongKet());
                if(list.get(position).getGioiTinh().equals("Nam"))
                {
                    radNam.setChecked(true);
                }
                else
                {
                    radNu.setChecked(true);
                }


                radGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i)
                        {
                            case R.id.radNam:
                                gioitinh = "" + radNam.getText().toString();
                                break;
                            case R.id.radNu:
                                gioitinh = "" + radNu.getText().toString();
                                break;
                        }
                    }
                });
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SinhVienDAO sinhVienDAO = new SinhVienDAO(context);
                        sinhVienDAO.open();

                        if(edtHoTen.getText().toString().isEmpty() || edtDiemTongKet.getText().toString().isEmpty())
                        {
                            Toast.makeText(context, "Các trường không được để trống", Toast.LENGTH_SHORT).show();
                        }
                        else if(Float.parseFloat(edtDiemTongKet.getText().toString()) > 10)
                        {
                            Toast.makeText(context,"Điểm không được quá 10", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            SinhVienDTO sinhVienDTO = new SinhVienDTO();
                            sinhVienDTO.setMSSV(list.get(position).getMSSV());
                            sinhVienDTO.setHoTen(edtHoTen.getText().toString());
                            sinhVienDTO.setGioiTinh(gioitinh);
                            sinhVienDTO.setDiemTongKet(edtDiemTongKet.getText().toString());

                            Log.e("sinhvienabc", sinhVienDTO.getMSSV() + " " + sinhVienDTO.getHoTen() + " " + sinhVienDTO.getGioiTinh() + " " + sinhVienDTO.getDiemTongKet());
                            sinhVienDAO.updateSinhVien(sinhVienDTO);
                            notifyItemChanged(position);
                            MainActivity main = (MainActivity) context;
                            main.reloadFragmentHienThi();
                            dialog.dismiss();
                        }
                    }
                });

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMSSV, txtHoTen, txtGioiTinh, txtDiemTongKet;
        Button btnSua, btnXoa;
        SinhVienDAO sinhVienDAO;
        public ViewHolder(View itemView) {
            super(itemView);


            txtMSSV = itemView.findViewById(R.id.txtMSSV);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtGioiTinh = itemView.findViewById(R.id.txtGioiTinh);
            txtDiemTongKet = itemView.findViewById(R.id.txtDiemTongKet);

            btnSua = itemView.findViewById(R.id.btnSua);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }


}
