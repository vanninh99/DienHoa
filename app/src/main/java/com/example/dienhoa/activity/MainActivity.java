package com.example.dienhoa.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dienhoa.R;
import com.example.dienhoa.adapter.SanphammoinhatAdapter;
import com.example.dienhoa.adapter.loaispadapter;
import com.example.dienhoa.models.GioHang;
import com.example.dienhoa.models.loaisp;
import com.example.dienhoa.models.sanpham;
import com.example.dienhoa.ultil.checkConnection;
import com.example.dienhoa.ultil.server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    RecyclerView recyclerViewMain;
    ListView listViewMain;
    DrawerLayout drawerLayout;
    ArrayList<loaisp> arrlsp;
    loaispadapter lspadapter;
    int id = 0;
    String ten = "";
    String img = "";
    //---------------//
    int idsp = 0;
    String tensp = "";
    String imgsp = "";
    double giasp = 0;
    int idlsp = 0;
    ArrayList<sanpham> arrspmoinhat;
    SanphammoinhatAdapter sanphammoinhatAdapter;
    //-----------//
    public static ArrayList<GioHang> arrayListGH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        if(checkConnection.haveNetworkConnection(getApplicationContext())){
            ActionBar();
            ActionViewFlipper();
            getDataLoaisp();
            getDataSPMoinhat();
            catchOnItemListView();
        }else{
            checkConnection.showToast_Short(getApplicationContext(),"Không có kết nối internet");
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

    private void catchOnItemListView() {
        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(checkConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else {
                            checkConnection.showToast_Short(getApplicationContext(),"Không có kết nối internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(checkConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, SanPhamActivity.class);
                            intent.putExtra("idloaisp", arrlsp.get(position).getIdloaisp());
                            startActivity(intent);
                        }else {
                            checkConnection.showToast_Short(getApplicationContext(),"Không có kết nối internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(checkConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, SanPhamActivity.class);
                            intent.putExtra("idloaisp", arrlsp.get(position).getIdloaisp());
                            startActivity(intent);
                        }else {
                            checkConnection.showToast_Short(getApplicationContext(),"Không có kết nối internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(checkConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, SanPhamActivity.class);
                            intent.putExtra("idloaisp", arrlsp.get(position).getIdloaisp());
                            startActivity(intent);
                        }else {
                            checkConnection.showToast_Short(getApplicationContext(),"Không có kết nối internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(checkConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, SanPhamActivity.class);
                            intent.putExtra("idloaisp", arrlsp.get(position).getIdloaisp());
                            startActivity(intent);
                        }else {
                            checkConnection.showToast_Short(getApplicationContext(),"Không có kết nối internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if(checkConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, LienHeActivity.class);
                            startActivity(intent);
                        }else {
                            checkConnection.showToast_Short(getApplicationContext(),"Không có kết nối internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 6:
                        if(checkConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, ThongTinActivity.class);
                            startActivity(intent);
                        }else {
                            checkConnection.showToast_Short(getApplicationContext(),"Không có kết nối internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }


    private void getDataSPMoinhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.urlSPmoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){

                    for (int i = 0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            idsp = jsonObject.getInt("flowerid");
                            tensp = jsonObject.getString("flowername");
                            imgsp = jsonObject.getString("flowerimage");
                            giasp  = jsonObject.getDouble("price");
                            idlsp = jsonObject.getInt("idloaisp");
                            arrspmoinhat.add(new sanpham(idsp, tensp, imgsp, giasp, idlsp));

                            sanphammoinhatAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void getDataLoaisp() {
        final RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArr = new JsonArrayRequest(server.urlLoaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    for (int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("idloaisp");
                            ten = jsonObject.getString("ten");
                            img = jsonObject.getString("img");
                            arrlsp.add(new loaisp(id,ten,img));
                            lspadapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                arrlsp.add(5, new loaisp(0,"Liên hệ","https://goo.gl/QWxx4n"));
                arrlsp.add(6, new loaisp(0,"Thông tin","https://goo.gl/CsgbX7"));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                checkConnection.showToast_Short(getApplicationContext(),error.toString());
            }
        });
        request.add(jsonArr);
    }

    private void ActionViewFlipper() {
        ArrayList<String> viewQuangCao = new ArrayList<>();
        viewQuangCao.add("https://goo.gl/s2zfHy");
        viewQuangCao.add("https://goo.gl/F9gxtf");
        viewQuangCao.add("https://goo.gl/usm28D");
        viewQuangCao.add("https://goo.gl/krWH34");
        for(int i = 0; i < viewQuangCao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(viewQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });
    }
    private void anhXa() {
        toolbar = findViewById(R.id.toolBarMain);
        viewFlipper = findViewById(R.id.viewFlipper);
        navigationView = findViewById(R.id.navigationView);
        recyclerViewMain = findViewById(R.id.recyclerViewMain);
        listViewMain = findViewById(R.id.listViewMain);
        drawerLayout = findViewById(R.id.drawerLayout);
        arrlsp = new ArrayList<>();
        arrlsp.add(0, new loaisp(0,"Trang chủ","https://goo.gl/viDiLM"));
        lspadapter = new loaispadapter(arrlsp,getApplicationContext());
        listViewMain.setAdapter(lspadapter);
        arrspmoinhat = new ArrayList<>();
        sanphammoinhatAdapter = new SanphammoinhatAdapter(getApplicationContext(), arrspmoinhat);
        recyclerViewMain.setHasFixedSize(true);
        recyclerViewMain.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewMain.setAdapter(sanphammoinhatAdapter);
        if(arrayListGH != null){

        }else{
            arrayListGH = new ArrayList<>();
        }
    }
}
