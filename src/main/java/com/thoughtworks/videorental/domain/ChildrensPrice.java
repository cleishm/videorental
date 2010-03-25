package com.thoughtworks.videorental.domain;

public class ChildrensPrice extends Price {

	@Override
	public int getPriceCode() {
		return Movie.CHILDRENS;
	}

}
