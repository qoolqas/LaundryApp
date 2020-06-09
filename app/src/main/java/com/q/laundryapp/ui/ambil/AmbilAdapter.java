package com.q.laundryapp.ui.ambil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.q.laundryapp.R;
import com.q.laundryapp.connection.Client;
import com.q.laundryapp.connection.Service;
import com.q.laundryapp.model.delete.DeleteResponse;
import com.q.laundryapp.model.edit.EditResponse;
import com.q.laundryapp.model.read.ProdukModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AmbilAdapter extends RecyclerView.Adapter<AmbilAdapter.ViewHolder> {
    private AmbilFragment produkActivity;
    private Context context;
    private List<ProdukModel> list;
    String domain = "192.168.1.9:8080";


    public AmbilAdapter(AmbilFragment produkActivity, Context context) {
        this.produkActivity = produkActivity;
        this.context = context;
    }

    public void setProduk(List<ProdukModel> dataGets) {
        this.list = dataGets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AmbilAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_card_ambil, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AmbilAdapter.ViewHolder holder, int position) {
        final ProdukModel data = list.get(position);
        holder.nama.setText(list.get(position).getNama());
        holder.harga.setText("RP " + data.getPerHarga());
        holder.berat.setText(data.getBerat());
        holder.jenis.setText(data.getJenis());
        holder.catatan.setText(data.getCatatan());
        holder.alamat.setText(data.getAlamat());
        holder.created.setText(data.getCreatedAt());
        holder.telfon.setText(data.getTelfon());

    }

    @Override
    public int getItemCount() {
        Log.d("size", String.valueOf(list.size()));
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView berat, jenis, harga, catatan, nama, alamat, telfon, created;
        private Button btnAmbil;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.card_nama);
            jenis = itemView.findViewById(R.id.card_jenis);
            berat = itemView.findViewById(R.id.card_berat);
            catatan = itemView.findViewById(R.id.card_catatan);
            alamat = itemView.findViewById(R.id.card_alamat);
            telfon = itemView.findViewById(R.id.card_telfon);
            created = itemView.findViewById(R.id.card_created);
            nama = itemView.findViewById(R.id.card_nama);
            harga = itemView.findViewById(R.id.card_total);
            btnAmbil = itemView.findViewById(R.id.buttonAmbil);

            btnAmbil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    final int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        final ProdukModel data = list.get(position);
                        new AlertDialog.Builder(context)
                                .setMessage("Pesanan sudah selesai?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Service service = Client.getClient().create(Service.class);
                                        Call<EditResponse> delete = service.update(data.getBerat(), data.getJenis(), data.getPerHarga(), data.getTambahan(), data.getCatatan(), data.getNama(), data.getAlamat(), data.getTelfon(), "0", "1", data.getBarangId());
                                        delete.enqueue(new Callback<EditResponse>() {
                                            @Override
                                            public void onResponse(Call<EditResponse> call, Response<EditResponse> response) {
                                                if (response.isSuccessful()) {
                                                    Toast.makeText(context, view.getContext().getString(R.string.msg_success), Toast.LENGTH_SHORT).show();
                                                    list.remove(list.get(position));
                                                    notifyDataSetChanged();

                                                } else {
                                                    Toast.makeText(context, view.getContext().getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<EditResponse> call, Throwable t) {
                                                Toast.makeText(context, view.getContext().getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();

                                            }
                                        });

                                    }
                                })

                                // A null listener allows the button to dismiss the dialog and take no further action.
                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                }
            });
        }
    }
}
