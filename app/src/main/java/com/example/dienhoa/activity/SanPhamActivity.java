package com.example.dienhoa.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dienhoa.R;
import com.example.dienhoa.adapter.SanPhamApdepter;
import com.example.dienhoa.models.sanpham;
import com.example.dienhoa.ultil.checkConnection;
import com.example.dienhoa.ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SanPhamActivity extends AppCompatActivity {

    Toolbar toolbarsp;
    ListView listViewSP;
    SanPhamApdepter sanPhamApdepter;
    ArrayList<sanpham> arrayListSP;
    int idlsp = 0;
    int page = 1;
    View footerView;
    boolean isLoadding = false;
    myHandler myHandler;
    boolean limitadata = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham);
        Anhxa();
        if(checkConnection.haveNetworkConnection(getApplicationContext())){
            GetIDLoaiSP();
            ActionToolBar();
            GetData(page);
            LoadData();
        }else{
            checkConnection.showToast_Short(getApplicationContext(),"Bạn chưa kết nối internet. Vui lòng kiểm tra lại");
            finish();
        }
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

    private void LoadData() {
        listViewSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ChiTietSP.class);
                intent.putExtra("thongtinsanpham",arrayListSP.get(position));
                startActivity(intent);
            }
        });
        listViewSP.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && isLoadding == false && limitadata == false){
                    isLoadding = true;
                    threadData threadData = new threadData();
                    threadData.start();
                }
            }
        });
    }

    private void GetData(int p) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = server.urlSanPham + p;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String tenSP = "";
                String hinhanhSP = "";
                double giaSP = 0;
                int idsp = 0;
                if(response != null && response.length() != 2){
                    listViewSP.removeFooterView(footerView);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i =  0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("flowerid");
                            tenSP = jsonObject.getString("flowername");
                            hinhanhSP = jsonObject.getString("flowerimage");
                            giaSP = jsonObject.getDouble("price");
                            idsp = jsonObject.getInt("idloaisp");
                            arrayListSP.add(new sanpham(id,tenSP,hinhanhSP,giaSP,idsp));
                            sanPhamApdepter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    limitadata = true;
                    listViewSP.removeFooterView(footerView);
                    checkConnection.showToast_Short(getApplicationContext(),"Bạn đã load hết dữ liệu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("idloaisp",String.valueOf(idlsp));
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarsp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarsp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIDLoaiSP() {
        idlsp = getIntent().getIntExtra("idloaisp", -1);
        Log.d("giatriloaisp",idlsp + "");
    }

    private void Anhxa() {
        toolbarsp = findViewById(R.id.toolBarSP);
        listViewSP = findViewById(R.id.listViewSP);
        arrayListSP = new ArrayList<>();
        sanPhamApdepter = new SanPhamApdepter(getApplicationContext(),arrayListSP);
        listViewSP.setAdapter(sanPhamApdepter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerView = inflater.inflate(R.layout.progressbar,null);
        myHandler = new myHandler();

    }
    public class myHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0: {
                    listViewSP.addFooterView(footerView);
                    break;
                }

                case 1: {
                    GetData(++page);
                    isLoadding = false;
                    break;
                }
            }
            super.handleMessage(msg);
        }
    }

    public class threadData extends Thread{
        @Override
        public void run() {
            myHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = myHandler.obtainMessage(1);
            myHandler.sendMessage(message);
            super.run();
        }
    }
}
