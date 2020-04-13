package com.q.laundryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PopupJenisActivity extends AppCompatActivity {
    Button popupSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_jenis);

        popupSimpan = findViewById(R.id.popupJenisSimpan);
        popupSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopupJenisActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });


    }
}
