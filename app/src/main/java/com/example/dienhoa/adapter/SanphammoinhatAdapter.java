package com.example.dienhoa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dienhoa.R;
import com.example.dienhoa.models.sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanphammoinhatAdapter extends RecyclerView.Adapter<SanphammoinhatAdapter.ItemHolder> {

    Context context;
    ArrayList<sanpham> arrSP;

    public SanphammoinhatAdapter(Context context, ArrayList<sanpham> arrSP) {
        this.context = context;
        this.arrSP = arrSP;
    }


    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dong_spmoinhat,null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder itemHolder, int i) {
        sanpham sp = arrSP.get(i);
        itemHolder.tenSP.setText(sp.getFlowername());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        itemHolder.giaSP.setText("Giá: " + decimalFormat.format(sp.getPrice()) + "VNĐ");
        Picasso.with(context).load(sp.getFlowerimage())
                .placeholder(R.drawable.load)
                .error(R.drawable.error_512px)
                .into(itemHolder.imgSP);
    }

    @Override
    public int getItemCount() {
        return arrSP.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgSP;
        public TextView tenSP, giaSP;

        public ItemHolder(View itemView) {
            super(itemView);
            imgSP = itemView.findViewById(R.id.imgViewSP);
            tenSP = itemView.findViewById(R.id.txtTenSP);
            giaSP = itemView.findViewById(R.id.txtGiaSP);
        }
    }
}
