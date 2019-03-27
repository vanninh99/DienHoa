package com.example.dienhoa.activity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.dienhoa.R;
import com.example.dienhoa.adapter.GioHangAdapter;
import com.example.dienhoa.ultil.checkConnection;

import java.text.DecimalFormat;


public class GioHangActivity extends AppCompatActivity {

    Toolbar toolbarGioHang;
    Button buttonThanhtoan, buttonTieptucmua;
    TextView textViewThongbao;
    static TextView textViewTongtien;
    ListView listViewGiohang;
    GioHangAdapter gioHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        Anhxa();
        ActionToolBar();
        CheckData();
        EventUltil();
        CatchItemOnListView();
        EventButton();
    }

    private void EventButton() {
        buttonTieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        buttonThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.arrayListGH.size() > 0){
                    Intent intent = new Intent(getApplicationContext(), ThongTinKhachHang.class);
                    startActivity(intent);
                }else{
                    checkConnection.showToast_Short(getApplicationContext(), "Giỏ hàng của bạn chưa có sản phẩm!");
                    Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    private void CatchItemOnListView() {
        listViewGiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có muốn xóa sản phẩm này khỏi giỏ hàng của bạn không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.arrayListGH.size() <= 0){
                            textViewThongbao.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.arrayListGH.remove(position);
                            gioHangAdapter.notifyDataSetChanged();
                            EventUltil();
                            if(MainActivity.arrayListGH.size() <= 0){
                                textViewThongbao.setVisibility(View.VISIBLE);
                            }
                            else{
                                textViewThongbao.setVisibility(View.INVISIBLE);
                                gioHangAdapter.notifyDataSetChanged();
                                EventUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gioHangAdapter.notifyDataSetChanged();
                        EventUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }


    public static void EventUltil() {
        double tongtien = 0;
        for (int i = 0; i < MainActivity.arrayListGH.size(); i++){
            tongtien += MainActivity.arrayListGH.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textViewTongtien.setText(decimalFormat.format(tongtien)+"VNĐ");
    }

    private void CheckData() {
        if(MainActivity.arrayListGH == null){
            gioHangAdapter.notifyDataSetChanged();
            listViewGiohang.setVisibility(View.INVISIBLE);
            textViewThongbao.setVisibility(View.VISIBLE);
        }else{
            gioHangAdapter.notifyDataSetChanged();
            listViewGiohang.setVisibility(View.VISIBLE);
            textViewThongbao.setVisibility(View.INVISIBLE);
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarGioHang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarGioHang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void Anhxa() {
        toolbarGioHang = findViewById(R.id.toolbarGH);
        buttonThanhtoan = findViewById(R.id.buttonthanhtoan);
        buttonTieptucmua = findViewById(R.id.buttontieptuc);
        textViewThongbao = findViewById(R.id.textviewthongbao);
        textViewTongtien = findViewById(R.id.txtTongTien);
        listViewGiohang = findViewById(R.id.listViewGH);
        gioHangAdapter = new GioHangAdapter(GioHangActivity.this, MainActivity.arrayListGH);
        listViewGiohang.setAdapter(gioHangAdapter);
    }
}
