package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import backend.loading;

public class exitForm extends JFrame{
   private JLabel inAmt,title,vNo,inTime,outTime,totH,amount,bill,billO,tokenNo,inTimeI,outTimeI,totHI,amountI;
   private JTextField vNoI,tokenNoI,inAmtI;
   //private JTextArea billO;\
  String billVal;
   private JButton search,back,pay,exit;
   public String getuAmt() {
	return uAmt;
}
public void setuAmt(String uAmt) {
	this.uAmt = uAmt;
}

private String vno,inT,outT,tHrs,amt,tokNo,uAmt;
   boolean found;
   public String getVno() {
	return vno;
}
public void setTokenNode(String tokNo) {
	this.tokNo=tokNo;
}
public String getTokenNo() {
	return tokNo;
}
public void setVno(String vno) {
	this.vno = vno;
}
public String getInT() {
	return inT;
}
public void setInT(String inT) {
	this.inT = inT;
}
public String getOutT() {
	return outT;
}
public void setOutT(String outT) {
	this.outT = outT;
}
public String gettHrs() {
	return tHrs;
}
public void settHrs(String tHrs) {
	this.tHrs = tHrs;
}
public String getAmt() {
	return amt;
}
public void setAmt(String amt) {
	this.amt = amt;
}
   public exitForm() {
	    
		setLayout(null);
		title=new JLabel("VSAVIOUR");
    	title.setFont(new Font("Tahoma", Font.PLAIN, 30));
    	title.setBounds(270,40,250,80);
    	add(title);
    	
    	tokenNo=new JLabel("Token NO");
    	tokenNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	tokenNo.setBounds(150, 120, 170, 40);
    	add(tokenNo);
    	
    	tokenNoI=new JTextField();
    	tokenNoI.setColumns(10);
    	tokenNoI.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	tokenNoI.setBounds(330, 130, 170, 30);
    	add(tokenNoI);
    	
    	search=new JButton("SEARCH");
    	search.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	search.setBounds(520, 130, 100, 30);
    	add(search);
    	
    	inAmtI=new JTextField();
    	inAmtI.setColumns(10);
    	inAmtI.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	inAmtI.setBounds(330, 590, 170, 30);
    	//inAmt.setEnabled(false);
    	add(inAmtI);
    	
    	//outtime and total hours
    	search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				found=new loading().isFound(Integer.parseInt(tokenNoI.getText()));
				System.out.println(tokenNoI.getText());
				if(found) {
					setTokenNode(tokenNoI.getText());
					//new loading().outVal(tokenNoI.getText());
		    		String pattern = "dd:MM:YYYY HH:mm:ss";
					SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern, new Locale("en", "IN"));
					String date = simpleDateFormat.format(new Date());
					
					outTimeI.setText(date);
					String mainS=new loading().getOut(tokenNoI.getText());
					String inDate=mainS.substring(0,19);
					inTimeI.setText(inDate);
					String type=mainS.substring(19);
					String amount=new TimeCalc().getTotalTime(inDate,date , type);
					if(amount.equals("0")) {
						if(type.equals("car")) {
							amount="10";
						}else {
							amount="5";
						}
					}
					setAmt(amount);
					int totHours=0;
			        if(type.equals("car")) {
			        	totHours=Integer.parseInt(amount)/10;
			        }
			        else {
			        	totHours=Integer.parseInt(amount)/5;
			        }
					amountI.setText(amount);
					totHI.setText(String.valueOf(totHours));
					
					billVal="<html>*********************<br/>   THANK YOU!<br/>   Token No:"+getTokenNo()+"<br/>   Total Hours:"+totHours+"<br/>   Date:"+date.substring(0,10)+"<br/>   Amount:Rs."+amount+"<br/>*********************</html>";
					
					billO.setText(billVal);
		    	}
				else {
					JOptionPane.showMessageDialog(null, "Enter valid token number!");	
				}
				
			}
		});
    	
    	inTime=new JLabel("IN TIME");
    	inTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	inTime.setBounds(150, 180, 170, 40);
    	add(inTime);
    	
    	inTimeI=new JLabel(); 
    	inTimeI.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	inTimeI.setBounds(330, 180, 220, 30);
    	inTimeI.setBackground(Color.white);
    	inTimeI.setForeground(Color.black);
    	inTimeI.setOpaque(true);
    	add(inTimeI);
    	
    	
    	outTime=new JLabel("OUT TIME");
    	outTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	outTime.setBounds(150, 240, 170, 40);
    	add(outTime);
    	
    	outTimeI=new JLabel(); 
    	outTimeI.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	outTimeI.setBounds(330, 240, 220, 30);
    	outTimeI.setBackground(Color.white);
    	outTimeI.setForeground(Color.black);
    	outTimeI.setOpaque(true);
    	add(outTimeI);
		
    	totH=new JLabel("TOTAL HOURS");
    	totH.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	totH.setBounds(150, 300, 170, 40);
    	add(totH);
    	
    	totHI=new JLabel(); 
    	totHI.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	totHI.setBounds(330, 300, 170, 30);
    	totHI.setBackground(Color.white);
    	totHI.setForeground(Color.black);
    	totHI.setOpaque(true);
    	add(totHI);
    	
    	amount=new JLabel("AMOUNT");
    	amount.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	amount.setBounds(150, 360, 170, 40);
    	add(amount);
    	
    	amountI=new JLabel();
    	amountI.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	amountI.setBounds(330, 360, 170, 30);
    	amountI.setBackground(Color.white);
    	amountI.setForeground(Color.black);
    	amountI.setOpaque(true);
    	add(amountI);
    	
    	bill=new JLabel("BILL");
    	bill.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	bill.setBounds(150, 420, 170, 40);
    	add(bill);
    	
    	billO=new JLabel();
    	billO.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	billO.setBounds(330, 430, 170, 130);
    	billO.setBackground(Color.white);
    	billO.setForeground(Color.black);
    	billO.setOpaque(true);
    	add(billO);
    	
    	
    	inAmt=new JLabel("AMOUNT");
    	inAmt.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	inAmt.setBounds(150, 590, 170, 40);
    	add(inAmt);
    	
    	
    	back=new JButton("BACK");
    	back.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	back.setBounds(120,690,150,50);
    	add(back);
    	back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			new entry();
				
			}
		});
    	
    	pay=new JButton("PAY");
    	pay.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	pay.setBounds(370,690,150,50);
    	add(pay);
    	pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             setTokenNode(tokenNoI.getText());
             setInT(inTimeI.getText());
             setOutT(outTimeI.getText());
             settHrs(totHI.getText());
             setAmt(amountI.getText());
             setuAmt(inAmtI.getText());
             if(isFound()) {
            	   if(getAmt().equals(0)) {
               		setAmt("1");
               	  }
            	   if(getuAmt().equals(getAmt())) {
                  	
            		new loading().outVal(getTokenNo(),gettHrs());   
            		   
                  	 JOptionPane.showMessageDialog(null, "THANKS FOR USING OUR SERVICE!\nVIST US AGAIN!!");
      				 System.exit(0);
                   }
                   else if(getAmt()==null) {
                  	 JOptionPane.showMessageDialog(null, "ENTER AMOUNT!!");
                   }
                   else {
                  	 JOptionPane.showMessageDialog(null, "ENTER VALID AMOUNT!!");
                   }
             }
             else {
            	 JOptionPane.showMessageDialog(null, "ENTER VALID TOKEN NO"); 
             }
			}
		});
    	
    	exit=new JButton("EXIT");
    	exit.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	exit.setBounds(540,690,100,50);
    	add(exit);
    	exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             System.exit(0);
			}
		});
    	addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
          System.exit(0);
            }        
         });  
    	setTitle("VSAVIOUR");
    	setSize(720,800);
    	setBackground(new Color(255, 255, 255));
    	setResizable(true);
    	setVisible(true);
    	
   }
   
public boolean isFound() {
	return found;
}
public void setFound(boolean found) {
	this.found = found;
}
	public static void main(String[] args) {
		new exitForm();
	}

}
