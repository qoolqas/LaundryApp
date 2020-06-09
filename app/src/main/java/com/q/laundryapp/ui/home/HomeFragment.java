package com.q.laundryapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.q.laundryapp.R;

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

        return root;
    }
}
