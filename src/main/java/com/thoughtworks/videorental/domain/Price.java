package com.thoughtworks.videorental.domain;

public abstract class Price {
	abstract public int getPriceCode();
	abstract public double getCharge(int daysRented); 
}
