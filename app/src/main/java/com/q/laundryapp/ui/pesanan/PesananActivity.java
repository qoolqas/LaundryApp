package com.q.laundryapp.ui.pesanan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.q.laundryapp.R;
import com.q.laundryapp.connection.Client;
import com.q.laundryapp.connection.Service;
import com.q.laundryapp.model.create.CreateResponse;
import com.q.laundryapp.model.read.ProdukModel;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesananActivity extends AppCompatActivity {
    ProdukModel produkModel;
    RadioGroup kategori;
    RadioButton ekspres, normal;
    String edit = "0";
    String jenis = "0";
    String per_harga = "0";
    EditText nama, handphone, alamat, berat, tambahan, catatan;
    Button simpan;
    public ProgressDialog pDialog;

    //region wait
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);
        produkModel = getIntent().getParcelableExtra("data");

        nama = findViewById(R.id.createNama);
        handphone = findViewById(R.id.createHandphone);
        alamat = findViewById(R.id.createAlamat);
        berat = findViewById(R.id.createBerat);
        tambahan = findViewById(R.id.createTambahan);
        catatan = findViewById(R.id.createCatatan);
        simpan = findViewById(R.id.bt_simpan);

        ekspres = findViewById(R.id.rd_ekspress);
        normal = findViewById(R.id.rd_normal);

        try {
            edit = getIntent().getStringExtra("edit");

        } catch (Exception e) {
            edit = "0";
        }
        kategori.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rd_ekspress:
                        jenis = "Ekspress";
                        per_harga = "20000";
                        break;
                    case R.id.rd_normal:
                        jenis = "Normal";
                        per_harga = "7000";
                        break;
                }
            }
        });
        if (edit.equals("1")) {
            simpan.setText("Simpan Perubahan");
            setEdit();
        }
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit.equals("1")) {
                    editPesanan();
                } else {
                    createPesanan();
                }

            }
        });
        initDialog();
    }

    //endregion
    private void setEdit() {

    }

    private void editPesanan() {
    }

    private void createPesanan() {
        String dataNama = nama.getText().toString();
        String dataHandphone = handphone.getText().toString();
        String dataAlamat = alamat.getText().toString();
        String dataBerat = berat.getText().toString();
        String dataTambahan = tambahan.getText().toString();
        String dataCatatan = catatan.getText().toString();
        double dHarga = Double.parseDouble(per_harga);
        double dTambahan = Double.parseDouble(dataTambahan);
        double dBerat = Double.parseDouble(dataBerat);
        double total = (dHarga * dBerat) + dTambahan;
        String dataHarga = String.valueOf(total);
        //jenis
        Service service = Client.getClient().create(Service.class);

        Call<CreateResponse> create = service.create(dataBerat, jenis, dataHarga,dataTambahan,dataCatatan,dataNama,dataAlamat,dataHandphone);
        create.enqueue(new Callback<CreateResponse>() {
            @Override
            public void onResponse(@NotNull Call<CreateResponse> call, @NotNull Response<CreateResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        hidepDialog();
                        Toast.makeText(getApplicationContext(), getString(R.string.msg_success), Toast.LENGTH_SHORT).show();
                        finish();

                    }
                } else {
                    hidepDialog();
                    Toast.makeText(getApplicationContext(), getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<CreateResponse> call, @NotNull Throwable t) {
                hidepDialog();
                Log.v("Response gotten is", Objects.requireNonNull(t.getMessage()));
                Toast.makeText(getApplicationContext(), getString(R.string.msg_gagal) + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    protected void initDialog() {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.msg_loading));
        pDialog.setCancelable(false);
    }


    protected void showpDialog() {

        if (!pDialog.isShowing()) pDialog.show();
    }

    protected void hidepDialog() {

        if (pDialog.isShowing()) pDialog.dismiss();
    }
}
