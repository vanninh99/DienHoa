package com.example.dienhoa.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dienhoa.R;
import com.example.dienhoa.ultil.checkConnection;
import com.example.dienhoa.ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHang extends AppCompatActivity {
    EditText tenkh, emailkh, addresskh, phonekh;
    Button xacnhan, trove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        Anhxa();
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(checkConnection.haveNetworkConnection(getApplicationContext())){
            EventButtonXacnhan();
        }else{
            checkConnection.showToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối internet");
        }
    }

    private void EventButtonXacnhan() {
        xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten = tenkh.getText().toString();
                final String email = emailkh.getText().toString().trim();
                final String phone = phonekh.getText().toString();
                final String diachi = addresskh.getText().toString();
                if(ten.length() > 0 && phone.length() > 0 && email.length() > 0 && diachi.length() > 0){
                    final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server.urlThongtinkhachang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String response) {
                            Log.d("madonhang",response);
                            if(Integer.parseInt(response) > 0){
                                RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
                                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, server.urlChitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("1")){
                                            MainActivity.arrayListGH.clear();
                                            checkConnection.showToast_Short(getApplicationContext(),"Đặt hàng thành công");
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                            checkConnection.showToast_Short(getApplicationContext(),"Mời bạn tiếp tục mua hàng");
                                        }else{
                                            checkConnection.showToast_Short(getApplicationContext(),"Error!!!");
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for(int i=0; i<MainActivity.arrayListGH.size(); i++){
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("madonhang", response);
                                                jsonObject.put("masanpham",MainActivity.arrayListGH.get(i).getIdsp());
                                                jsonObject.put("tensanpham",MainActivity.arrayListGH.get(i).getTensp());
                                                jsonObject.put("soluong",MainActivity.arrayListGH.get(i).getSoluongsp());
                                                jsonObject.put("giasanpham",MainActivity.arrayListGH.get(i).getGiasp());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);

                                        }
                                        HashMap<String,String> hashMap = new HashMap<>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                requestQueue1.add(stringRequest1);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String , String>();
                            hashMap.put("fullname",ten);
                            hashMap.put("email",email);
                            hashMap.put("address",diachi);
                            hashMap.put("phonenumber",phone);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else{
                    checkConnection.showToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại dữ liệu");
                }
            }
        });
    }

    private void Anhxa() {
        tenkh = findViewById(R.id.fullNameKH);
        emailkh = findViewById(R.id.emailKH);
        addresskh = findViewById(R.id.addressKH);
        phonekh = findViewById(R.id.phoneKH);
        xacnhan = findViewById(R.id.buttonOk);
        trove = findViewById(R.id.buttonHuy);
    }
}
