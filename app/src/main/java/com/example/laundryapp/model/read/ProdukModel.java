package com.example.laundryapp.model.read;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProdukModel implements Parcelable {

	@SerializedName("per_harga")
	private String perHarga;

	@SerializedName("tambahan")
	private String tambahan;

	@SerializedName("catatan")
	private String catatan;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("history")
	private String history;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("total")
	private String total;

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("berat")
	private String berat;

	@SerializedName("jenis")
	private String jenis;

	@SerializedName("barang_id")
	private String barangId;

	@SerializedName("telfon")
	private String telfon;

	@SerializedName("status")
	private String status;

	public void setPerHarga(String perHarga){
		this.perHarga = perHarga;
	}

	public String getPerHarga(){
		return perHarga;
	}

	public void setTambahan(String tambahan){
		this.tambahan = tambahan;
	}

	public String getTambahan(){
		return tambahan;
	}

	public void setCatatan(String catatan){
		this.catatan = catatan;
	}

	public String getCatatan(){
		return catatan;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setHistory(String history){
		this.history = history;
	}

	public String getHistory(){
		return history;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setTotal(String total){
		this.total = total;
	}

	public String getTotal(){
		return total;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setBerat(String berat){
		this.berat = berat;
	}

	public String getBerat(){
		return berat;
	}

	public void setJenis(String jenis){
		this.jenis = jenis;
	}

	public String getJenis(){
		return jenis;
	}

	public void setBarangId(String barangId){
		this.barangId = barangId;
	}

	public String getBarangId(){
		return barangId;
	}

	public void setTelfon(String telfon){
		this.telfon = telfon;
	}

	public String getTelfon(){
		return telfon;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"per_harga = '" + perHarga + '\'' + 
			",tambahan = '" + tambahan + '\'' + 
			",catatan = '" + catatan + '\'' + 
			",created_at = '" + createdAt + '\'' +
			",history = '" + history + '\'' +
			",alamat = '" + alamat + '\'' + 
			",total = '" + total + '\'' + 
			",nama = '" + nama + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",berat = '" + berat + '\'' + 
			",jenis = '" + jenis + '\'' + 
			",barang_id = '" + barangId + '\'' + 
			",telfon = '" + telfon + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.perHarga);
		dest.writeString(this.tambahan);
		dest.writeString(this.catatan);
		dest.writeString(this.createdAt);
		dest.writeString(this.history);
		dest.writeString(this.alamat);
		dest.writeString(this.total);
		dest.writeString(this.nama);
		dest.writeString(this.updatedAt);
		dest.writeString(this.berat);
		dest.writeString(this.jenis);
		dest.writeString(this.barangId);
		dest.writeString(this.telfon);
		dest.writeString(this.status);
	}

	public ProdukModel() {
	}

	protected ProdukModel(Parcel in) {
		this.perHarga = in.readString();
		this.tambahan = in.readString();
		this.catatan = in.readString();
		this.createdAt = in.readString();
		this.history = in.readString();
		this.alamat = in.readString();
		this.total = in.readString();
		this.nama = in.readString();
		this.updatedAt = in.readString();
		this.berat = in.readString();
		this.jenis = in.readString();
		this.barangId = in.readString();
		this.telfon = in.readString();
		this.status = in.readString();
	}

	public static final Parcelable.Creator<ProdukModel> CREATOR = new Parcelable.Creator<ProdukModel>() {
		@Override
		public ProdukModel createFromParcel(Parcel source) {
			return new ProdukModel(source);
		}

		@Override
		public ProdukModel[] newArray(int size) {
			return new ProdukModel[size];
		}
	};
}