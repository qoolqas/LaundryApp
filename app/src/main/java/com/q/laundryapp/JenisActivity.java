package com.q.laundryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JenisActivity extends AppCompatActivity {
    CardView jenisKiloan, cardSelimut, cardHelm, cardKarpet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis);
        jenisKiloan = findViewById(R.id.jenisKiloan);
        cardSelimut = findViewById(R.id.cardSelimut);
        cardHelm = findViewById(R.id.cardHelm);
        cardKarpet = findViewById(R.id.cardKarpet);
        jenisKiloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JenisActivity.this, PopupJenisActivity.class);
                startActivity(intent);
            }
        });
        cardSelimut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JenisActivity.this, PopupJenisActivity.class);
                startActivity(intent);
            }
        });
        cardHelm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JenisActivity.this, PopupJenisActivity.class);
                startActivity(intent);
            }
        });
        cardKarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JenisActivity.this, PopupJenisActivity.class);
                startActivity(intent);
            }
        });
    }
}
