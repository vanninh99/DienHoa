package com.example.dienhoa.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import com.example.dienhoa.R;
import com.example.dienhoa.models.GioHang;
import com.example.dienhoa.models.sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSP extends AppCompatActivity {

    Toolbar toolbarChitietSP;
    ImageView imageViewChitietSP;
    TextView txtTenSP, txtGiaSP;
    Spinner spinner;
    Button addBuy;
    int id = 0;
    String ten = "";
    double gia = 0;
    String hinhanh = "";
    int idspct = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sp);
        Anhxa();
        Actiontoolbar();
        GetInformation();
        CatchEventSpinner();
        EventButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        addBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.arrayListGH.size() > 0){
                    int sluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists = false;
                    for (int i = 0; i < MainActivity.arrayListGH.size(); i++){
                        if(MainActivity.arrayListGH.get(i).getIdsp() == id){
                            MainActivity.arrayListGH.get(i).setSoluongsp(MainActivity.arrayListGH.get(i).getSoluongsp() + sluong);
                            if(MainActivity.arrayListGH.get(i).getSoluongsp() > 10){
                                MainActivity.arrayListGH.get(i).setSoluongsp(10);
                            }
                            MainActivity.arrayListGH.get(i).setGiasp(MainActivity.arrayListGH.get(i).getSoluongsp() * MainActivity.arrayListGH.get(i).getGiasp());
                            exists = true;
                        }
                    }
                    if(exists == false){
                        int solg = Integer.parseInt(spinner.getSelectedItem().toString());
                        double gia_moi = solg * gia;
                        MainActivity.arrayListGH.add(new GioHang(id,ten,gia_moi,hinhanh,solg));
                    }

                }else{
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    double gia_moi = soluong * gia;
                    MainActivity.arrayListGH.add(new GioHang(id,ten,gia_moi,hinhanh,soluong));
                }
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer>  arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInformation() {

        sanpham sp = (sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sp.getFlowerid();
        ten = sp.getFlowername();
        gia = sp.getPrice();
        hinhanh = sp.getFlowerimage();
        idspct = sp.getIdloaisp();
        txtTenSP.setText(ten);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaSP.setText("Giá: " + decimalFormat.format(sp.getPrice()) + "VNĐ");
        Picasso.with(getApplicationContext()).load(hinhanh)
                .placeholder(R.drawable.load)
                .error(R.drawable.error_512px)
                .into(imageViewChitietSP);
    }

    private void Actiontoolbar() {
        setSupportActionBar(toolbarChitietSP);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitietSP.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarChitietSP = findViewById(R.id.toobarChitietSP);
        imageViewChitietSP = findViewById(R.id.imageViewChitietSP);
        txtTenSP = findViewById(R.id.tenChitietSP);
        txtGiaSP = findViewById(R.id.giaChitietSP);
        spinner = findViewById(R.id.spinnerChitietSP);
        addBuy = findViewById(R.id.buttonBuyFlower);
    }
}
