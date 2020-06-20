package com.example.laundryapp.ui.status;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundryapp.R;
import com.example.laundryapp.ViewModel;
import com.example.laundryapp.model.read.ProdukModel;
import com.example.laundryapp.model.read.ProdukResponse;

import java.util.ArrayList;
import java.util.List;

public class StatusFragment extends Fragment {
    private RecyclerView recyclerView;
    private ViewModel viewModel;
    private StatusAdapter adapter;
    private LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    private ProgressBar pb;
    private List<ProdukModel> produk = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        adapter = new StatusAdapter(this, getActivity());
        adapter.setProduk(produk);
        recyclerView = view.findViewById(R.id.rv);
        pb = view.findViewById(R.id.pb);
        initRV();
        getData();
        pb.setVisibility(View.VISIBLE);
    }

    private void getData() {


        viewModel.liveGetStatus().observe(getViewLifecycleOwner(), new Observer<ProdukResponse>() {
            @Override
            public void onChanged(ProdukResponse produkResponse) {

                try {
                    produk.clear();
                    produk.addAll(produkResponse.getResult());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    pb.setVisibility(View.GONE);
                } catch (Exception e) {
                    Log.d("catch", "produk");
                }
            }
        });
    }

    private void initRV() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        pb.setVisibility(View.VISIBLE);
        viewModel.loadBasah();
    }
}
