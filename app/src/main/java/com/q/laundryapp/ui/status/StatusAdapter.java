package com.q.laundryapp.ui.status;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.q.laundryapp.R;
import com.q.laundryapp.SharedPrefManager;
import com.q.laundryapp.connection.Client;
import com.q.laundryapp.connection.Service;
import com.q.laundryapp.model.delete.DeleteResponse;
import com.q.laundryapp.model.read.ProdukModel;
import com.q.laundryapp.ui.pesanan.PesananActivity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder> {
    private StatusFragment produkActivity;
    private Context context;
    private List<ProdukModel> list;


    public StatusAdapter(StatusFragment produkActivity, Context context) {
        this.produkActivity = produkActivity;
        this.context = context;
    }

    public void setProduk(List<ProdukModel> dataGets) {
        this.list = dataGets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_card_manage, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StatusAdapter.ViewHolder holder, int position) {
        final ProdukModel data = list.get(position);
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbols);
        BigDecimal bd = new BigDecimal(data.getPerHarga());
        String formattedNumber = PhoneNumberUtils.formatNumber(data.getTelfon());
        holder.nama.setText(list.get(position).getNama());

        holder.harga.setText("Total : " + "RP " + formatter.format(bd.longValue()));
        holder.berat.setText(data.getBerat() + "Kg");
        holder.jenis.setText(data.getJenis());
        holder.catatan.setText("Catatan "+data.getCatatan());
        holder.alamat.setText("Alamat"+data.getAlamat());
        holder.created.setText("Dipesan Pada : "+data.getCreatedAt());
        holder.telfon.setText(formattedNumber);
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
                    final ProdukModel data = list.get(position);
                    PopupMenu popup = new PopupMenu(produkActivity.getActivity(), btnAmbil);
                    popup.inflate(R.menu.crud);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.edit:
                                    Intent intent = new Intent(produkActivity.getActivity(), PesananActivity.class);
                                    intent.putExtra("id", String.valueOf(data.getBarangId()));
                                    intent.putExtra("edit", "1");
                                    intent.putExtra("data", data);
                                    produkActivity.getActivity().startActivity(intent);
                                    return true;
                                case R.id.delete:
                                    Service service = Client.getClient().create(Service.class);
                                    Call<DeleteResponse> delete = service.delete(data.getBarangId());

                                    delete.enqueue(new Callback<DeleteResponse>() {
                                        @Override
                                        public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                                            if (response.isSuccessful()) {
                                                Toast.makeText(context, view.getContext().getString(R.string.msg_success), Toast.LENGTH_SHORT).show();
                                                list.remove(list.get(position));
                                                notifyDataSetChanged();

                                            } else {
                                                Toast.makeText(context, view.getContext().getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<DeleteResponse> call, Throwable t) {
                                            Toast.makeText(context, view.getContext().getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                    break;
                            }
                            return false;
                        }
                    });
                    popup.show();

                }
            });
        }
    }
}
