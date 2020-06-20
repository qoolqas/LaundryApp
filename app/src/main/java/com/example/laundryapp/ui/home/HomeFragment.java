package com.example.laundryapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.laundryapp.R;
import com.example.laundryapp.ui.pesanan.PesananActivity;

public class HomeFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        CardView cardAmbil = root.findViewById(R.id.home_ambil);
        cardAmbil.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_home_to_nav_slideshow, null));

        CardView cardStatus = root.findViewById(R.id.home_status);
        cardStatus.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_home_to_nav_gallery, null));

        CardView cardHistori = root.findViewById(R.id.home_histori);
        cardHistori.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_home_to_nav_histori, null));

        CardView cardTambah = root.findViewById(R.id.home_buat);
        cardTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PesananActivity.class);
                intent.putExtra("edit", "0");
                startActivity(intent);
            }
        });

        return root;
    }
}
