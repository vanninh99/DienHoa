package com.example.dienhoa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dienhoa.R;
import com.example.dienhoa.models.sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamApdepter extends BaseAdapter {

    Context context;
    ArrayList<sanpham> arraySP;

    public SanPhamApdepter(Context context, ArrayList<sanpham> arraySP) {
        this.context = context;
        this.arraySP = arraySP;
    }

    @Override
    public int getCount() {
        return arraySP.size();
    }

    @Override
    public Object getItem(int position) {
        return arraySP.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txtTenSP, txtGiaSP;
        public ImageView imageViewSP;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_sanpham, null);
            viewHolder.txtTenSP = convertView.findViewById(R.id.txtTenSP);
            viewHolder.txtGiaSP = convertView.findViewById(R.id.txtGiaSP);
            viewHolder.imageViewSP = convertView.findViewById(R.id.imageViewSP);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        sanpham sp = (sanpham) getItem(position);
        viewHolder.txtTenSP.setText(sp.getFlowername());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaSP.setText("Giá: " + decimalFormat.format(sp.getPrice()) + "VNĐ");
        Picasso.with(context).load(sp.getFlowerimage())
                .placeholder(R.drawable.load)
                .error(R.drawable.error_512px)
                .into(viewHolder.imageViewSP);

        return convertView;
    }
}
