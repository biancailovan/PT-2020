package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import view.View;
import model.Monomial;
import model.Operations;
import model.Polynomial;

public class Controller {
	
	private Operations m_model;
	private View m_view;
	
	public Controller(Operations model, View view){
		
		m_model = model;
		m_view = view;
	
	view.addClearListener(new ClearListener());
	view.addAdditionListener(new AdditionListener());
	view.addSubstractionListener(new SubstractionListener());
	view.addMultiplyListener(new MultiplyListener());
	view.addDerivativeListener(new DerivativeListener());
	view.addIntegrateListener(new IntegrateListener());
	
	}
	
	
	class AdditionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			ArrayList<Monomial> m1= new ArrayList<Monomial>();
			ArrayList<Monomial> m2= new ArrayList<Monomial>();
			String userInput1 ="";
			String userInput2="";
			Polynomial p1=new Polynomial(m1); p1.setMonomial(m1);
			Polynomial p2=new Polynomial(m2);p2.setMonomial(m2);
			
			userInput1=m_view.getUserInput1();
			System.out.println(userInput1);
			userInput2=m_view.getUserInput2();
			System.out.println(userInput2);
			System.out.println(userInput1+userInput2);
			m_view.setTotal("Testare");
			
			  
			 try{
				 
				 p2.readAsRegex(userInput2);
				 System.out.println("2 nd polynomial:"+p2);
				 
				 p1.readAsRegex(userInput1);
				 System.out.println("1 st polynomial:"+p1);
				 
				 System.out.println("Result of the addition: ");
				 m_model.addition(p1,p2);

				 String mod=m_model.getResultP().toString();
				 m_view.setTotal(mod);
				 
			 }catch(Exception nfex){
				 m_view.showError("Bad input: "+userInput1+userInput2);
				 
			 }
		}
	}
	
	class SubstractionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			ArrayList<Monomial> m1= new ArrayList<Monomial>();
			ArrayList<Monomial> m2= new ArrayList<Monomial>();
			String userInput1 ="";
			String userInput2="";
			Polynomial p1=new Polynomial(m1); p1.setMonomial(m1);
			Polynomial p2=new Polynomial(m2);p2.setMonomial(m2);
			
			userInput1=m_view.getUserInput1();
			System.out.println(userInput1);
			userInput2=m_view.getUserInput2();
			System.out.println(userInput2);
			System.out.println(userInput1+userInput2);
			m_view.setTotal("Testare");
			
			  
			 try{
				 
				 p2.readAsRegex(userInput2);
				 System.out.println("2 nd polynomial:"+p2);
				 
				 p1.readAsRegex(userInput1);
				 System.out.println("1 st polynomial:"+p1);
				 
				 System.out.println("Result of the substraction: ");
				 m_model.substraction(p1,p2);
				 
				 String mod=m_model.getResultP().toString();
				 m_view.setTotal(mod);
				 
			 }catch(Exception nfex){
				 m_view.showError("Bad input: "+userInput1+userInput2);
				 
			 }				
		}
	}
	
	class MultiplyListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			ArrayList<Monomial> m1= new ArrayList<Monomial>();
			ArrayList<Monomial> m2= new ArrayList<Monomial>();
			String userInput1 ="";
			String userInput2="";
			Polynomial p1=new Polynomial(m1); p1.setMonomial(m1);
			Polynomial p2=new Polynomial(m2);p2.setMonomial(m2);
			
			userInput1=m_view.getUserInput1();
			System.out.println(userInput1);
			userInput2=m_view.getUserInput2();
			System.out.println(userInput2);
			System.out.println(userInput1+userInput2);
			m_view.setTotal("Testare");
			
			  
			 try{
				 
				 p2.readAsRegex(userInput2);
				 System.out.println("2 nd polynomial:"+p2);
				 
				 p1.readAsRegex(userInput1);
				 System.out.println("1 st polynomial:"+p1);
				 
				 System.out.println("Result of the multiplication: ");
				 m_model.multiply(p1,p2);
				
				 String mod=m_model.getResultP().toString();
				 m_view.setTotal(mod);
				 
			 }catch(Exception nfex){
				 m_view.showError("Bad input: "+userInput1+userInput2);
				 
			 }				
		}
	}
	
	class DerivativeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			ArrayList<Monomial> m1= new ArrayList<Monomial>();
			String userInput1 ="";
			
			Polynomial p1=new Polynomial(m1); p1.setMonomial(m1);
			
			userInput1=m_view.getUserInput1();
			System.out.println(userInput1);
			  
			 try{
				 
				 p1.readAsRegex(userInput1);
				 System.out.println("1 st polynomial:"+p1);
				 
				 System.out.println("Result of the derivative: ");
				 m_model.derivative(p1);
				
				 String mod=m_model.getResultP().toString();
				 m_view.setTotal(mod);
				 
			 }catch(Exception nfex){
				 m_view.showError("Bad input: "+userInput1);
				 
			 }				
		}
	}
	
	//am ales sa integrez primul polinom
	class IntegrateListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			ArrayList<Monomial> m1= new ArrayList<Monomial>();
			String userInput1 ="";
			
			Polynomial p1=new Polynomial(m1); p1.setMonomial(m1);
			
			userInput1=m_view.getUserInput1();
			System.out.println(userInput1);
			  
			 try{
				 
				 p1.readAsRegex(userInput1);
				 System.out.println("1 st polynomial:"+p1);
				 
				 System.out.println("Result of the integration of the 1st polynomial: ");
				 m_model.integration(p1);
				 
				 String mod=m_model.getResultP().toString();
				 m_view.setTotal(mod);
				 
			 }catch(Exception nfex){
				 m_view.showError("Bad input: "+userInput1);
				 
			 }				
		}
	}
	
	class ClearListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			m_model.reset();
			m_view.reset();
				
		}
	}

}
