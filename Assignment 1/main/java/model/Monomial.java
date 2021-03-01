package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Monomial {

	private int coefficient;
	private int degree;
	private double coeff;//pt integrare coeficientul devine de tip double
	boolean hasPair;
	
	public Monomial(int coefficient, int degree) {
		super();
		this.coefficient = coefficient;
		this.degree = degree;
		this.hasPair = hasPair;
	}
	
	public Monomial(double coeff) {
		this.coeff=coeff;
	}
	
	public double getCoeff() {
		return coeff;
	}
	
	public void setCoeff(double coeff) {
		this.coeff = coeff;
	}
	

	public int getCoefficient() {
		return coefficient;
	}
	
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}
	
	public int getDegree() {
		return degree;
	}
	
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	//hasPair-for veryfing if a monomial has a pair with the same degree. 
	//For operations
	public boolean getHasPair() {
		return hasPair;
	}
	
	public void setHasPair(boolean hasPair) {
		this.hasPair=hasPair;
	}
	
	private String format() {
		String container = new String(this.coeff + "x^" + this.degree);
		return container;
	}
	
	
	public Monomial() {
		// TODO Auto-generated constructor stub
		this.coefficient=0;
		this.degree=0;	
	}

	public String toString()
	{
		if(degree == 0 )
			return coefficient+"";
		if(degree == 1 )
			return coefficient+"x"; 
		else if(degree > 1)
			return coefficient+"x^"+degree+" ";
		else
			return null;
	}
}
