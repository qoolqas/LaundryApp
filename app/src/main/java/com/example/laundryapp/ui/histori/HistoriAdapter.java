package com.example.laundryapp.ui.histori;

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

import com.example.laundryapp.R;
import com.example.laundryapp.connection.Client;
import com.example.laundryapp.connection.Service;
import com.example.laundryapp.model.delete.DeleteResponse;
import com.example.laundryapp.model.read.ProdukModel;
import com.example.laundryapp.ui.pesanan.PesananActivity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoriAdapter extends RecyclerView.Adapter<HistoriAdapter.ViewHolder> {
    private HistoriFragment produkActivity;
    private Context context;
    private List<ProdukModel> list;


    public HistoriAdapter(HistoriFragment produkActivity, Context context) {
        this.produkActivity = produkActivity;
        this.context = context;
    }

    public void setProduk(List<ProdukModel> dataGets) {
        this.list = dataGets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoriAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_card_manage, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HistoriAdapter.ViewHolder holder, int position) {
        final ProdukModel data = list.get(position);
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbols);
        BigDecimal bd = new BigDecimal(data.getPerHarga());
        String formattedNumber = PhoneNumberUtils.formatNumber(data.getTelfon());
        holder.nama.setText(list.get(position).getNama());

        String date_before = data.getCreatedAt();
        String date_after = formateDateFromstring("yyyy-MM-dd hh:mm:ss", "dd, MMM yyyy", date_before);
        holder.created.setText("Dipesan Pada : "+date_after);

        holder.harga.setText("Total : " + "RP " + formatter.format(bd.longValue()));
        holder.berat.setText(data.getBerat() + "Kg");
        holder.jenis.setText(data.getJenis());
        holder.catatan.setText("Catatan "+data.getCatatan());
        holder.alamat.setText("Alamat "+data.getAlamat());
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
            btnAmbil.setVisibility(View.GONE);

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
    public static String formateDateFromstring(String inputFormat, String outputFormat, String inputDate){

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
            Log.d("tag", "ParseException - dateFormat");
        }

        return outputDate;

    }
}
