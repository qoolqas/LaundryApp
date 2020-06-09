package com.q.laundryapp.model.read;

import com.google.gson.annotations.SerializedName;

public class ResultItem{

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
}