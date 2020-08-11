package com.example.ps08611_nguyencaominh_gd1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ps08611_nguyencaominh_gd1.Adapter.productAdapter;
import com.example.ps08611_nguyencaominh_gd1.DAO.ProductsDAO;
import com.example.ps08611_nguyencaominh_gd1.Model.products;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnThem, btnSua;
    public EditText txtName, txtPrice, txtDescription;
    public String id;
    public ListView lv;
    com.example.ps08611_nguyencaominh_gd1.Model.products sp;
    ProductsDAO spDao;
    ArrayList<com.example.ps08611_nguyencaominh_gd1.Model.products> products;
    productAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv1);
        txtName = findViewById(R.id.edtTen);
        txtPrice = findViewById(R.id.edtGia);
        txtDescription = findViewById(R.id.edtMota);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);

        spDao = new ProductsDAO(this);

        capnhatLV();


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp = new products();
                sp.name = txtName.getText().toString();
                sp.price = Double.parseDouble(txtPrice.getText().toString());
                sp.description = txtDescription.getText().toString();

                spDao.insert(sp);
                clearText();
                //Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

            }
        });




        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp = new products();
                sp.id = id;
                sp.name = txtName.getText().toString();
                sp.price = Double.parseDouble(txtPrice.getText().toString());
                sp.description = txtDescription.getText().toString();

                spDao.update(sp);
            }
        });

    }

    public void clearText(){
        txtName.setText("");
        txtPrice.setText("");
        txtDescription.setText("");
    }

    public void xoaSp(String id) {

        spDao.delete(id);


    }

    public void capnhatLV() {


        products = (ArrayList<products>) spDao.getAll();

        // gan adapter

        adapter = new productAdapter(this, products);

        lv.setAdapter(adapter);
    }
}
