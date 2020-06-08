package com.q.laundryapp.ui.jenis;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.q.laundryapp.JenisActivity;
import com.q.laundryapp.PopupJenisActivity;
import com.q.laundryapp.R;

public class JenisFragment extends Fragment {
    CardView jenisKiloan, cardSelimut, cardHelm, cardKarpet;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        jenisKiloan = root.findViewById(R.id.jenisKiloan);
        cardSelimut = root.findViewById(R.id.cardSelimut);
        cardHelm = root.findViewById(R.id.cardHelm);
        cardKarpet = root.findViewById(R.id.cardKarpet);
        jenisKiloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PopupJenisActivity.class);
                startActivity(intent);
            }
        });
        cardSelimut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PopupJenisActivity.class);
                startActivity(intent);
            }
        });
        cardHelm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PopupJenisActivity.class);
                startActivity(intent);
            }
        });
        cardKarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PopupJenisActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}
