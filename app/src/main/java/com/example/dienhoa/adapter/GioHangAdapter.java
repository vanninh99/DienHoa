package com.example.dienhoa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dienhoa.R;
import com.example.dienhoa.activity.GioHangActivity;
import com.example.dienhoa.activity.MainActivity;
import com.example.dienhoa.models.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {

    Context context;
    ArrayList<GioHang> arrayGioHang;

    public GioHangAdapter(Context context, ArrayList<GioHang> arrayGioHang) {
        this.context = context;
        this.arrayGioHang = arrayGioHang;
    }

    @Override
    public int getCount() {
        return arrayGioHang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayGioHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public  class  ViewHolder{
        public TextView txtTenGH, txtGiaGH;
        public ImageView imgGioHang;
        public Button buttonMinus, buttonValues, buttonPlus;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_giohang,null);
            viewHolder.txtTenGH = convertView.findViewById(R.id.txtViewTenGH);
            viewHolder.txtGiaGH = convertView.findViewById(R.id.txtViewGiaSPGH);
            viewHolder.imgGioHang = convertView.findViewById(R.id.imageViewGioHang);
            viewHolder.buttonMinus = convertView.findViewById(R.id.buttonminus);
            viewHolder.buttonValues = convertView.findViewById(R.id.buttonvalues);
            viewHolder.buttonPlus = convertView.findViewById(R.id.buttonplus);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GioHang gioHang = (GioHang) getItem(position);
        viewHolder.txtTenGH.setText(gioHang.getTensp());
        DecimalFormat format = new DecimalFormat("###,###,###");
        viewHolder.txtGiaGH.setText("Giá: " + format.format(gioHang.getGiasp()) + "VNĐ");
        Picasso.with(context).load(gioHang.getImgsp())
                .placeholder(R.drawable.load)
                .error(R.drawable.error_512px)
                .into(viewHolder.imgGioHang);
        viewHolder.buttonValues.setText(String.valueOf(gioHang.getSoluongsp()));
        int slg = Integer.parseInt(viewHolder.buttonValues.getText().toString());
        if (slg >= 10){
            viewHolder.buttonPlus.setVisibility(View.INVISIBLE);
            viewHolder.buttonMinus.setVisibility(View.VISIBLE);
        }
        else{
            if(slg <= 1){
                viewHolder.buttonPlus.setVisibility(View.VISIBLE);
                viewHolder.buttonMinus.setVisibility(View.INVISIBLE);
            }
            else{
                viewHolder.buttonPlus.setVisibility(View.VISIBLE);
                viewHolder.buttonMinus.setVisibility(View.VISIBLE);
            }
        }
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slm = Integer.parseInt(finalViewHolder.buttonValues.getText().toString()) + 1;
                int slht = MainActivity.arrayListGH.get(position).getSoluongsp();
                double giaht = MainActivity.arrayListGH.get(position).getGiasp();
                MainActivity.arrayListGH.get(position).setSoluongsp(slm);
                double  giamoi = (slm * giaht) / slht;
                MainActivity.arrayListGH.get(position).setGiasp(giamoi);
                DecimalFormat format = new DecimalFormat("###,###,###");
                finalViewHolder.txtGiaGH.setText("Giá: " + format.format(giamoi) + "VNĐ");
                GioHangActivity.EventUltil();
                if (slm > 9){
                    finalViewHolder.buttonPlus.setVisibility(View.INVISIBLE);
                    finalViewHolder.buttonMinus.setVisibility(View.VISIBLE);
                    finalViewHolder.buttonValues.setText(String.valueOf(slm));
                }
                else{
                    finalViewHolder.buttonPlus.setVisibility(View.VISIBLE);
                    finalViewHolder.buttonMinus.setVisibility(View.VISIBLE);
                    finalViewHolder.buttonValues.setText(String.valueOf(slm));
                }
            }
        });
        viewHolder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmn = Integer.parseInt(finalViewHolder.buttonValues.getText().toString()) - 1;
                int slght = MainActivity.arrayListGH.get(position).getSoluongsp();
                double giahtai = MainActivity.arrayListGH.get(position).getGiasp();
                MainActivity.arrayListGH.get(position).setSoluongsp(slmn);
                double  giamoin = (slmn * giahtai) / slght;
                MainActivity.arrayListGH.get(position).setGiasp(giamoin);
                DecimalFormat format = new DecimalFormat("###,###,###");
                finalViewHolder.txtGiaGH.setText("Giá: " + format.format(giamoin) + "VNĐ");
                GioHangActivity.EventUltil();
                if (slmn  < 2){
                    finalViewHolder.buttonPlus.setVisibility(View.VISIBLE);
                    finalViewHolder.buttonMinus.setVisibility(View.INVISIBLE);
                    finalViewHolder.buttonValues.setText(String.valueOf(slmn));
                }
                else{
                    finalViewHolder.buttonPlus.setVisibility(View.VISIBLE);
                    finalViewHolder.buttonMinus.setVisibility(View.VISIBLE);
                    finalViewHolder.buttonValues.setText(String.valueOf(slmn));
                }
            }
        });
        viewHolder = finalViewHolder;
        return convertView;
    }
}
