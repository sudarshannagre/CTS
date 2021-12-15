package com.vois.vtv_cts.model;

public class Token {

	private int ctstid;
	private Hclaims hclaims;
	private Bclaims bclaims;
	public int getCtstid() {
		return ctstid;
	}
	public void setCtstid(int ctstid) {
		this.ctstid = ctstid;
	}
	public Hclaims getHclaims() {
		return hclaims;
	}
	public void setHclaims(Hclaims hclaims) {
		this.hclaims = hclaims;
	}
	public Bclaims getBclaims() {
		return bclaims;
	}
	public void setBclaims(Bclaims bclaims) {
		this.bclaims = bclaims;
	}
}
