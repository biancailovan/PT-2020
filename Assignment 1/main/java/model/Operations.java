package model;

import java.math.BigInteger;
import java.util.ArrayList;

public class Operations {
	
	public static final String INITIAL_VALUE ="1";
	
	private String p1;
	private String p2;
	
	private Polynomial resultP;
	
	
	public String getP1() {
		return p1;
	}
	
	public void setP1(String p1) {
		this.p1 = p1;
	}
	
	public String getP2() {
		return p2;
	}
	
	public void setP2(String p2) {
		this.p2 = p2;
	}
	
	public Operations(String p1, String p2, Polynomial resultP, BigInteger m_total) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.resultP=resultP;
		this.m_total=m_total;
	}
	
	
	public Polynomial getResultP() {
		return resultP;
	}
	public void setResultP(Polynomial resultP) {
		this.resultP = resultP;
	}
	
	public Operations(){
		reset();
	}
	private BigInteger m_total; 
	
	public void reset() {
		// TODO Auto-generated method stub	
		m_total = new BigInteger(INITIAL_VALUE);
	}
	
	public void setValue(String value) {
        m_total = new BigInteger(value);
    }
	
	public String getValue() {
	        return m_total.toString();
	}

	public void addition(Polynomial p1, Polynomial p2) {
		ArrayList<Monomial> res= new ArrayList<Monomial>();
		Polynomial resultP = new Polynomial(res);
		resultP.setMonomial(res);
		
			
			for(Monomial m1: p1.getMonomials()) {
			for(Monomial m2: p2.getMonomials()) {
				Monomial resultM;
				if(m1.getDegree()==m2.getDegree()) {
					m1.hasPair=true;
					m2.hasPair=true;
					resultM = new Monomial(m1.getCoefficient()+m2.getCoefficient(), m2.getDegree());
					res.add(resultM);
				}
			}
		}
		this.setResultP(resultP);
		System.out.println(resultP);
		
		for(Monomial m1: p1.getMonomials()){
			if(m1.getHasPair() == false)																	
				resultP.add(m1);																		
		}
		
		for(Monomial m2: p2.getMonomials()){
			if(m2.getHasPair() == false){
					resultP.add(m2);
			}
		}
		
	}
	
	
		public void substraction(Polynomial p1, Polynomial p2) {
			ArrayList<Monomial> res= new ArrayList<Monomial>();
			Polynomial resultP = new Polynomial(res);
			resultP.setMonomial(res);
			
				
				for(Monomial m1: p1.getMonomials()) {
				for(Monomial m2: p2.getMonomials()) {
					Monomial resultM;
					if(m1.getDegree()==m2.getDegree()) {
						m1.hasPair=true;
						m2.hasPair=true;
						resultM = new Monomial(m1.getCoefficient()-m2.getCoefficient(), m2.getDegree());
						res.add(resultM);
					}
				}
			}
				
			this.setResultP(resultP);
			System.out.println(resultP);
			
			for(Monomial m1: p1.getMonomials()){
				if(m1.getHasPair() == false)																	
					resultP.add(m1);																		
			}
			
			for(Monomial m2: p2.getMonomials()){
				if(m2.getHasPair() == false){
						m2.setCoefficient(0 - m2.getCoefficient());
						resultP.add(m2);
				}
			}
		}
	
		
		public void multiply(Polynomial p1,Polynomial p2){
			
			ArrayList<Monomial> res= new ArrayList<Monomial>();
			Polynomial resultP = new Polynomial(res);
			resultP.setMonomial(res);

				for(Monomial m1: p1.getMonomials() ){
					for(Monomial m2: p2.getMonomials()){
						Monomial resultM;
						resultM = new Monomial(m1.getCoefficient() * m2.getCoefficient(),m1.getDegree() + m2.getDegree()) ;
						resultP.add(resultM);				
					}
				}
				
				this.setResultP(resultP);
				System.out.println(resultP);
		}

		
		public void derivative(Polynomial p1){
			
			ArrayList<Monomial> res= new ArrayList<Monomial>();
			Polynomial resultP = new Polynomial(res);
			resultP.setMonomial(res);

				for(Monomial m: p1.getMonomials()){
					m.setCoefficient(m.getCoefficient() * m.getDegree());
					m.setDegree(m.getDegree() - 1);
					resultP.add(m);
				}
				
				this.setResultP(resultP);
				System.out.println(resultP);	
		}
		
		public void integration(Polynomial p1) {
			
			ArrayList<Monomial> res= new ArrayList<Monomial>();
			Polynomial resultP = new Polynomial(res);
			resultP.setMonomial(res);
			
			for(Monomial m: p1.getMonomials()) {
				if(m.getDegree()>0) {
					double coeff=(m.getCoeff()/(m.getDegree()+1));
					m.setCoeff(coeff);
					m.setDegree(m.getDegree()+1);
					resultP.add(m);
				}
				
			}
			
			this.setResultP(resultP);
			System.out.println(resultP);
		}
		
		
		@Override
		public String toString() {
			return "Operations [p1=" + p1 + ", p2=" + p2 + ", resultP=" + resultP + ", m_total=" + m_total + "]";
		}
		
		
		
}
