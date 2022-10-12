package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import backend.loading;

public class entry extends JFrame{
	private JButton forIn,forOut,admin;
	private JLabel title,vacant;
	private JLabel vacantCnt;
	private String vacCount;
	 loading obj;
    public String getVacCount() {
		return vacCount;
	}
	public void setVacCount(String vacCount) {
		this.vacCount = vacCount;
	}
	public entry() {
    	setLayout(null);
    	obj=new loading();
    	setVacCount(new loading().totVacCnt());
    	title=new JLabel("VSAVIOUR");
    	title.setFont(new Font("Tahoma", Font.PLAIN, 30));
    	title.setBounds(230,90,250,80);
    	add(title);
    	
    	forIn=new JButton("IN");
    	forIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	forIn.setBounds(130,245,115,40);
    	forIn.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				
				if(getVacCount().equals("0")) {
					JOptionPane.showMessageDialog(null, "Oops Parking lot is full!!");
				}
				else
				{
					dispose();
				new inputForm();
				}
			}
		});
    	
    	forOut=new JButton("OUT");
    	forOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	forOut.setBounds(340, 245, 115, 40);
    	forOut.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			//dispose();
			if(getVacCount().equals("100"))
			JOptionPane.showMessageDialog(null, "No Vechilce in parking lot");
			else
			{
				dispose();
			new exitForm();
			}
			}
		});
    	
    	vacant=new JLabel("VACANT");
    	vacant.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	vacant.setBounds(230,300,250,80);
    	add(vacant);
    	vacantCnt=new JLabel();
    	vacantCnt.setBounds(240,370,100,70);
        vacantCnt.setFont(new Font("Tahoma", Font.PLAIN, 50));
    	add(vacantCnt);
    	add(forIn);
    	add(forOut);
        vacantCnt.setText(getVacCount());
        
        admin=new JButton("ADMIN LOGIN");
    	admin.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	admin.setBounds(175, 460, 220, 40);
    	admin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//dispose();
				new adminForm();
			}
		});
    	add(admin);
    	addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
          System.exit(0);
            }        
         });  
    	setSize(600,600);
    	setBackground(new Color(255, 255, 255));
    	setResizable(true);
    	setVisible(true);
    	
    	//loading obj=new loading();
    	
    	
    }
	public static void main(String[] args) {
		new entry();
       
	}

}
