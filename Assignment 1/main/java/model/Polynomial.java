package model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
	
	//private List<Monomial>monomials= new List<Monomial>();
	//We need a list of monomials to get our polynomial
	private ArrayList<Monomial> monomials= new ArrayList<Monomial>();
	private ArrayList<String> strMon = new ArrayList<String>();

	public Polynomial(ArrayList<Monomial> m1) {
		// TODO Auto-generated constructor stub
		this.monomials=monomials;
	}

	public ArrayList<Monomial> getMonomials() {
		return monomials;
	}

	public void setMonomial(ArrayList<Monomial> monomials) {
		this.monomials = monomials;
	}
	
	//for adding a monomial into a polynomial
	public void add(Monomial m2) {
		// TODO Auto-generated method stub
		monomials.add(m2);
	}
	
	
	public Monomial createMonomial(String monomial){
		Monomial m= new Monomial();
		
		//coefficient of the monomial
		String[] coefficient=monomial.split("[x](\\^?)");
		m.setCoefficient(Integer.parseInt(coefficient[0]));
		
		//degree of the monomial
		String[] degree=monomial.split("[x](\\^?)");
		m.setDegree(Integer.parseInt(degree[degree.length-1]));
		
		return m;
		
	}
	
	public void readAsRegex(String polynomial){
		regexChecker("(-)?(\\d+)?[x]?(\\^?(\\d+))?",polynomial);
	}
	
	public void regexChecker(String theRegex,String strCheck){
		
		Monomial m= new Monomial();
		
		Pattern checkRegex= Pattern.compile(theRegex);
		Matcher regexMatcher= checkRegex.matcher(strCheck);
		
		while(regexMatcher.find()){
			if(regexMatcher.group().length()!=0){
				System.out.println("Monomial as a String:"+regexMatcher.group().trim());
				strMon.add(regexMatcher.group().trim());
			}
		}
		
		for(String i: strMon){
			monomials.add(createMonomial(i));
		}

		
	}
	
	public String toString() {
		String s="";
		for(Monomial strMon: monomials){
			s+="+"+strMon;
		}
		return s;
	}
	

}
