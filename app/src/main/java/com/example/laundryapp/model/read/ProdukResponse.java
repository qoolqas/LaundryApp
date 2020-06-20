package com.example.laundryapp.model.read;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProdukResponse {

	@SerializedName("result")
	private List<ProdukModel> result;

	public void setResult(List<ProdukModel> result){
		this.result = result;
	}

	public List<ProdukModel> getResult(){
		return result;
	}

	@Override
 	public String toString(){
		return 
			"ReadResponse{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}