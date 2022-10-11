package frontend;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import backend.loading;

public class SwingTester {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   //private JButton back;
   public SwingTester(){
	  
      prepareGUI();
   }
   public static void main(String[] args){
      SwingTester swingControlDemo = new SwingTester();      
      swingControlDemo.showTableDemo();
   }
   private void prepareGUI(){
      mainFrame = new JFrame("ALL TABLE INFO");
      mainFrame.setSize(600,600);
      mainFrame.setLayout(new GridLayout(0, 1));

      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            mainFrame.dispose();
        	 new entry();
         }        
      });  
      
    
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    
      statusLabel.setSize(350,400);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());
      
      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
//      back = new JButton("BACK");
//		back.setFont(new Font("Tahoma", Font.PLAIN, 15));
//	  	back.setBounds(90,600,50,25);
//	  	back.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					mainFrame.dispose();
//					new entry();
//				}
//			});
//		mainFrame.add(back);
      mainFrame.setVisible(true);  
   }
   public void showTableDemo(){
      headerLabel.setText("Customer Details using JTable"); 

      String[] columnNames = {"TOKEN_NO","VEHICLE_NO","NAME","PHONE","DATE","VEHICLE_TYPE","ADDRESS","OCCUPANCY"};
      Object[][] data = new loading().allInfo();
      JTable table = new JTable(data, columnNames);
      JScrollPane scrollPane = new JScrollPane(table);
      
      
      scrollPane.setSize(600, 600);
      table.setFillsViewportHeight(true);
      controlPanel.add(scrollPane);      
      mainFrame.setVisible(true);
      
   } 
}
