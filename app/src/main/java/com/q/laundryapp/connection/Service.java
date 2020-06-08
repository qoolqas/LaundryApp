package com.q.laundryapp.connection;



import com.q.laundryapp.model.login.LoginResponse;
import com.q.laundryapp.model.register.RegisterResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface Service {


//    @GET("read.php")
//    Call<ProdukResponse> getProduk();
//
//    @GET("read.php")
//    Call<ProdukResponse> getProdukKategori(@Query("kategori") String kategori);

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

//    @DELETE("delete.php")
//    Call<DeleteResponse> delete(@Query("produk_id") String id);
//
//    @Multipart
//    @POST("create.php")
//    Call<CreateResponse> create(
//            @Part("nama") RequestBody nama,
//            @Part("rating") RequestBody rating,
//            @Part("harga") RequestBody harga,
//            @Part("kategori") RequestBody kategori,
//            @Part("deskripsi") RequestBody deskripsi,
//            @PartMap Map<String, RequestBody> map
//    );
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
