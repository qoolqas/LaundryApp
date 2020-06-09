package com.q.laundryapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.q.laundryapp.connection.Client;
import com.q.laundryapp.connection.Service;
import com.q.laundryapp.model.read.ProdukResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private MutableLiveData<ProdukResponse> produk;
    void loadProduct() {

        Service service = Client.getClient().create(Service.class);
        Call<ProdukResponse> call = service.getLaundry();
        call.enqueue(new Callback<ProdukResponse>() {

            @Override
            public void onResponse(@NotNull Call<ProdukResponse> call, @NotNull Response<ProdukResponse> response) {
                produk.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<ProdukResponse> call, @NotNull Throwable t) {
                Log.e("failure", t.toString());

            }
        });
    }

    public LiveData<ProdukResponse> liveGet() {
        if (produk == null) {
            produk = new MutableLiveData<>();
            loadProduct();
        }
        return produk;
    }
    public void loadBasah() {

        Service service = Client.getClient().create(Service.class);
        Call<ProdukResponse> call = service.getStatus("0");
        call.enqueue(new Callback<ProdukResponse>() {

            @Override
            public void onResponse(@NotNull Call<ProdukResponse> call, @NotNull Response<ProdukResponse> response) {
                produk.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<ProdukResponse> call, @NotNull Throwable t) {
                Log.e("failure", t.toString());

            }
        });
    }

    public LiveData<ProdukResponse> liveGetStatus() {
        if (produk == null) {
            produk = new MutableLiveData<>();
            loadBasah();
        }
        return produk;
    }
    void loadKering() {

        Service service = Client.getClient().create(Service.class);
        Call<ProdukResponse> call = service.getStatus("1");
        call.enqueue(new Callback<ProdukResponse>() {

            @Override
            public void onResponse(@NotNull Call<ProdukResponse> call, @NotNull Response<ProdukResponse> response) {
                produk.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<ProdukResponse> call, @NotNull Throwable t) {
                Log.e("failure", t.toString());

            }
        });
    }

    public LiveData<ProdukResponse> liveGetHistori() {
        if (produk == null) {
            produk = new MutableLiveData<>();
            loadKering();
        }
        return produk;
    }
}
