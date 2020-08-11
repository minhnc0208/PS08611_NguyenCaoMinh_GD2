package com.example.ps08611_nguyencaominh_gd1.DAO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ps08611_nguyencaominh_gd1.Adapter.productAdapter;
import com.example.ps08611_nguyencaominh_gd1.MainActivity;
import com.example.ps08611_nguyencaominh_gd1.Model.products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsDAO {

    Context context;
    String thanhcong = "";
//    String insertUrl = "http://192.168.1.131/MOB403/model/createproduct.php";
//    String getAllUrl = "http://192.168.1.131/MOB403/model/getallproducts.php";
//    String updateUrl = "http://192.168.1.131/MOB403/model/updateproduct.php";
//    String deleteUrl = "http://192.168.1.131/MOB403/model/deleteproduct.php";
    String API = "http://10.82.128.158/MOB403/IndexProduct.php";
   // String API = "http://192.168.1.131/MOB403/IndexProduct.php";

    ArrayList<products> list = new ArrayList<products>();

    public ProductsDAO(Context context) {
        this.context = context;
    }


    public void insert(final products sp) {


        StringRequest stringrequest = new StringRequest(
                Request.Method.POST, API, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String thanhcong = "";
                try {
                    JSONObject jsonobject = new JSONObject(response);
                    thanhcong = jsonobject.getString("thanhcong");
                    Log.d("test", thanhcong);
                    //doc tat ca du lieu tu json bo vao ArrayList

                    if (Integer.parseInt(thanhcong) == 1)//thanh cong
                    {

                        Toast.makeText(context, "Add Done", Toast.LENGTH_SHORT).show();
                        ((MainActivity) context).capnhatLV();

                    } else //that bai
                    {

                        Toast.makeText(context, "Add Fail", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Volley error", Toast.LENGTH_SHORT).show();
                Log.d("loi", error.toString());

            }
        }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();

                param.put("name", sp.name);
                param.put("price", sp.price.toString());
                param.put("description", sp.description);
                param.put("tag", "insertProduct");


                return param;
            }
        };
        Volley.newRequestQueue(context).add(stringrequest);

    }

    public ArrayList<products> getAll() {

        list.clear();

        StringRequest stringrequest = new StringRequest(Request.Method.POST,API
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonobject = new JSONObject(response);
                    thanhcong = jsonobject.getString("thanhcong");

                    //doc tat ca du lieu tu json bo vao ArrayList
                    if (Integer.parseInt(thanhcong) == 1)//thanh cong
                    {
                        Toast.makeText(context, "getAll Done", Toast.LENGTH_SHORT).show();

                        JSONArray sps = jsonobject.getJSONArray("product");

                        for (int i = 0; i < sps.length(); i++) {
                            JSONObject spsJSONObject = sps.getJSONObject(i);
                            String id = spsJSONObject.getString("id");
                            String name = spsJSONObject.getString("name");
                            Double price = spsJSONObject.getDouble("price");
                            String description = spsJSONObject.getString("description");

                            products sp = new products(id,name,price,description);
                            list.add(sp);
                            productAdapter productAdapter = new productAdapter(context,list);
                             ((MainActivity)context).lv.setAdapter(productAdapter);

                        }

                    } else //that bai
                    {
                        Toast.makeText(context, "getAll Fail", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Volley error", Toast.LENGTH_SHORT).show();
                Log.d("loi", error.toString());

            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<String, String>();
                param.put("tag", "selectProduct");
                return param;
            }
        }
                ;
        Volley.newRequestQueue(context).add(stringrequest);


        return list;
    }

    public int update(final products sp) {

        StringRequest stringrequest = new StringRequest(
                Request.Method.POST, API, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonobject = new JSONObject(response);
                    thanhcong = jsonobject.getString("thanhcong");

                    //doc tat ca du lieu tu json bo vao ArrayList
                    if (Integer.parseInt(thanhcong) == 1)//thanh cong
                    {
                        Toast.makeText(context, "Update Done", Toast.LENGTH_SHORT).show();
                        ((MainActivity) context).capnhatLV();

                    } else //that bai
                    {
                        Toast.makeText(context, "Update Fail", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Volley error", Toast.LENGTH_SHORT).show();
                Log.d("loi", error.toString());

            }
        }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();

                param.put("id", sp.id);
                param.put("name", sp.name);
                param.put("price", sp.price.toString());
                param.put("description", sp.description);
                param.put("tag", "updateProduct");


                return param;
            }
        };
        Volley.newRequestQueue(context).add(stringrequest);
        return Integer.parseInt(thanhcong);
    }

    public int delete(final String id) {


        StringRequest stringrequest = new StringRequest(
                Request.Method.POST, API, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonobject = new JSONObject(response);
                    thanhcong = jsonobject.getString("thanhcong");

                    //doc tat ca du lieu tu json bo vao ArrayList
                    if (Integer.parseInt(thanhcong) == 1)//thanh cong
                    {
                        Toast.makeText(context, "Delete Done", Toast.LENGTH_SHORT).show();
                        ((MainActivity) context).capnhatLV();

                    } else //that bai
                    {
                        Toast.makeText(context, "Delete Fail", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Volley error", Toast.LENGTH_SHORT).show();
                Log.d("loi", error.toString());

            }
        }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();

                param.put("id", id);
                param.put("tag", "deleteProduct");


                return param;
            }
        };
        Volley.newRequestQueue(context).add(stringrequest);
        return Integer.parseInt(thanhcong);
    }
}
