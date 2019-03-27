package com.example.dienhoa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dienhoa.R;
import com.example.dienhoa.models.loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class loaispadapter extends BaseAdapter {

    ArrayList<loaisp> arr;
    Context context;

    public loaispadapter() {
    }

    public loaispadapter(ArrayList<loaisp> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView textViewLSP;
        ImageView imageViewLSP;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHolder.textViewLSP = convertView.findViewById(R.id.textViewLSP);
            viewHolder.imageViewLSP = convertView.findViewById(R.id.imageViewLSP);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();

        }
        loaisp lsp = (loaisp) getItem(position);
        viewHolder.textViewLSP.setText(lsp.getTen());
        Picasso.with(context).load(lsp.getImg())
                .placeholder(R.drawable.load)
                .error(R.drawable.error_512px)
                .into(viewHolder.imageViewLSP);
        return convertView;
    }
}
