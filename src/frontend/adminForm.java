package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class adminForm extends JFrame{
	private String id,pass;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	private JLabel userId,password;
	private JTextField userIdI;
	private JPasswordField passwordI;
	private JButton back,submit;
    public adminForm() {
    	setLayout(null);
    	userId=new JLabel("USER ID");
    	userId.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	userId.setBounds(70,50,170,80);
    	add(userId);
    	
    	userIdI=new JTextField();
    	userIdI.setColumns(10);
    	userIdI.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	userIdI.setBounds(170,70,200,35);
    	add(userIdI);
    	
    	password=new JLabel("PASSWORD");
    	password.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	password.setBounds(70,110,170,80);
    	add(password);
    	
    	passwordI=new JPasswordField();
    	passwordI.setColumns(10);
    	passwordI.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	passwordI.setBounds(170,140,200,35);
    	passwordI.setEchoChar('*');
    	add(passwordI);
    	
    	back=new JButton("BACK");
    	back.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	back.setBounds(90,200,100,35);
    	back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new entry();
			}
		});
    	add(back);
 
    	
    	submit=new JButton("SUBMIT");
    	submit.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	submit.setBounds(240,200,100,35);
    	submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setId(userIdI.getText());
				setPass(String.valueOf(passwordI.getPassword()));
				if(getId()!=null && getId().length()>0 && getPass()!=null && getPass().length()>0){
					boolean f1 =getPass().equals("12345"), f2 = getId().equals("bcghh"); 
					System.out.println(f1+" "+f2);
					if(f1&&f2) {
						dispose();
						new SwingTester().showTableDemo();
					}else {
						JOptionPane.showMessageDialog(null, "Enter valid Details");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Enter userId and Password");
				}
			}
		});
    	add(submit);
    	addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
          System.exit(0);
            }        
         });  
    	setSize(500,350);
    	setBackground(new Color(255, 255, 255));
    	setResizable(true);
    	setVisible(true);
    }
	public static void main(String[] args) {
		new adminForm();

	}

}
