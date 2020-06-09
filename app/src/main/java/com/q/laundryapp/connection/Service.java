package com.q.laundryapp.connection;


import com.q.laundryapp.model.create.CreateResponse;
import com.q.laundryapp.model.delete.DeleteResponse;
import com.q.laundryapp.model.edit.EditResponse;
import com.q.laundryapp.model.login.LoginResponse;
import com.q.laundryapp.model.read.ProdukResponse;
import com.q.laundryapp.model.register.RegisterResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {


    @GET("read.php")
    Call<ProdukResponse> getLaundry();

    @GET("read.php")
    Call<ProdukResponse> getStatus(@Query("status") String status);

    @GET("read.php")
    Call<ProdukResponse> getHistori(@Query("histori") String histori);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> loginRequest(@Field("email") String email,
                                     @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> registerRequest(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("alamat") String alamat
    );

    @DELETE("delete.php")
    Call<DeleteResponse> delete(@Query("barang_id") String id);

    @Multipart
    @POST("create.php")
    Call<CreateResponse> create(
            @Part("nama") RequestBody nama,
            @Part("rating") RequestBody rating,
            @Part("harga") RequestBody harga,
            @Part("kategori") RequestBody kategori,
            @Part("deskripsi") RequestBody deskripsi,
            @PartMap Map<String, RequestBody> map
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<EditResponse> update(
            @Field("berat") String berat,
            @Field("jenis") String jenis,
            @Field("per_harga") String per_harga,
            @Field("tambahan") String tambahan,
            @Field("catatan") String catatan,
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("telfon") String telfon,
            @Field("status") String status,
            @Field("history") String history,
            @Field("barang_id") String barang_id);
//
//    @Multipart
//    @POST("update.php")
//    Call<EditResponse> update(
//            @Part("nama") RequestBody nama,
//            @Part("rating") RequestBody rating,
//            @Part("harga") RequestBody harga,
//            @Part("kategori") RequestBody kategori,
//            @Part("deskripsi") RequestBody deskripsi,
//            @Part("produk_id") RequestBody id,
//            @PartMap Map<String, RequestBody> map
//    );
//
//    @Multipart
//    @POST("update.php")
//    Call<EditResponse> updateNo(
//            @Part("nama") RequestBody nama,
//            @Part("rating") RequestBody rating,
//            @Part("harga") RequestBody harga,
//            @Part("kategori") RequestBody kategori,
//            @Part("deskripsi") RequestBody deskripsi,
//            @Part("produk_id") RequestBody id
//    );

}
