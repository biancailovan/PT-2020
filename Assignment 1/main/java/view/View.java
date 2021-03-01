package view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Operations;
import model.Monomial;
import model.Polynomial;

public class View extends JFrame {

	//Components
		private JTextField m_userInput1Tf = new JTextField(10);
		private JTextField m_userInput2Tf = new JTextField(10);
		private JTextField Total = new JTextField(20);
		private JButton m_addBtn = new JButton("Add");
		private JButton m_substractBtn = new JButton("Substract");
		private JButton m_multiplyBtn = new JButton("Multiply");
		private JButton m_divideBtn = new JButton("Divide");
		private JButton m_derivateBtn = new JButton("Derivate");
		private JButton m_integrateBtn = new JButton("Integrate");
		private JButton m_clearBtn = new JButton("Clear");
		
		//private JButton incercare= new JButton("TRY");
		
		private Operations m_model;
	
		
		public View(Operations model){
			//set up de logic
			m_model=model;
			m_model.setValue(Operations.INITIAL_VALUE);
		
		//... Initialize components
		 //Total.setText(m_model.getValue());
		 //... Layout the components.
		 JPanel content = new JPanel();
		 content.setLayout(new FlowLayout());
		 content.add(new JLabel("Polynomial 1"));
		 //content.add(new JLabel("Input2"));
		 content.add(m_userInput1Tf);
		 content.add(new JLabel("Polynomial 2"));
		 content.add(m_userInput2Tf);
		 content.add(m_addBtn);
		 content.add(m_substractBtn);
		 content.add(m_multiplyBtn);
		 content.add(m_divideBtn);
		 content.add(m_derivateBtn);
		 content.add(m_integrateBtn);
		 content.add(new JLabel("Result"));
		 content.add(Total);
		 content.add(m_clearBtn);
		 //content.add(incercare);

		 //... finalize layout
		 this.setContentPane(content);
		 this.pack();

		 this.setTitle("Polynomial calculator - MVC");
		 // The window closing event should probably be passed to the
		 // Controller
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 }
		 public void reset() {
		 Total.setText(Operations.INITIAL_VALUE);
		 } 
		 
		 public String getUserInput1() {
			 return m_userInput1Tf.getText();
			 }
		 
		 public String getUserInput2() {
			 return m_userInput2Tf.getText();
			 }

		 public void setTotal(String newTotal)
			{
				Total.setText(newTotal);
			}
			
			 public void showError(String errMessage) {
			 JOptionPane.showMessageDialog(this, errMessage);
			 }

			 public void addAdditionListener(ActionListener ad) {
			 m_addBtn.addActionListener(ad);
			 }
			 
			 public void addSubstractionListener(ActionListener sub) {
				 m_substractBtn.addActionListener(sub);
			 }
			 
			 public void addMultiplyListener(ActionListener mul) {
				 m_multiplyBtn.addActionListener(mul);
			 }
			 
			 public void divideAdditionListener(ActionListener div) {
				 m_divideBtn.addActionListener(div);
			 }
			 
			 public void addDerivativeListener(ActionListener deriv) {
				 m_derivateBtn.addActionListener(deriv);
			 }
			 
			 public void addIntegrateListener(ActionListener inte) {
				 m_integrateBtn.addActionListener(inte);
			 }

			 public void addClearListener(ActionListener cal) {
			 m_clearBtn.addActionListener(cal);
			 }
			 
			 /*public void addIncercare(ActionListener mesaj){
				 incercare.addActionListener(mesaj);
			 }*/
		
		
		
		
}
