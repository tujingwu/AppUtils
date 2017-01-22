package com.example.apputil.network.api.basemodule;


import java.io.Serializable;

public class ObjResponse<T> implements Serializable {
	
	private String error;

	private String msg;

	private T result;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}



	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	
}
