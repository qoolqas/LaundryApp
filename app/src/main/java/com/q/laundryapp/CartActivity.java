package com.q.laundryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ArrayList<String> customer = new ArrayList<String>();
        customer.add("Supomo");
        customer.add("H. Sulthon");
        customer.add("Zaki");
        ArrayList<String> bayar = new ArrayList<String>();
        bayar.add("GOPAY");
        bayar.add("OVO");
        bayar.add("Cash");

        SearchableSpinner spnCust = findViewById(R.id.spinnerPilihCustomer);
        ArrayAdapter adaptercab = new ArrayAdapter(CartActivity.this, android.R.layout.simple_spinner_item, customer);
        adaptercab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCust.setAdapter(adaptercab);
        spnCust.setTitle("");

        SearchableSpinner spnTipeBayar = findViewById(R.id.spinnerTipeBayar);
        ArrayAdapter adapterTB = new ArrayAdapter(CartActivity.this, android.R.layout.simple_spinner_item, bayar);
        adaptercab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTipeBayar.setAdapter(adapterTB);
        spnTipeBayar.setTitle("");
    }
}
