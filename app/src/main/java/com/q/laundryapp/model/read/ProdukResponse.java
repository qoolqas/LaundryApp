package com.q.laundryapp.model.read;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProdukResponse {

	@SerializedName("result")
	private List<ResultItem> result;

	public void setResult(List<ResultItem> result){
		this.result = result;
	}

	public List<ResultItem> getResult(){
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