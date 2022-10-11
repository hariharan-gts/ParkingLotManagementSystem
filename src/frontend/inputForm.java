package frontend;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import backend.*;
public class inputForm extends JFrame{
	
	private JLabel title;
	private JTextField vNoI,nameI,pNoI;
	private JLabel vNo,name,pNo,sDate,type,address,sDateI;
	private JTextArea addI;
	private JButton date,back,submit,exit;
	private  Choice cType;
	private String vno,nameO,pno,sdate,typV,add;
	public static final String[]veh= {"select","car","bike"};
	boolean flag=false;
    public inputForm() {
    	setLayout(null);
    	
    	title=new JLabel("VSAVIOUR");
    	title.setFont(new Font("Tahoma", Font.PLAIN, 30));
    	title.setBounds(270,40,250,80);
    	add(title);
    	
    	vNo=new JLabel("VEHICLE NO");
    	vNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	vNo.setBounds(150, 120, 170, 40);
    	add(vNo);
    	
    	vNoI=new JTextField();
    	vNoI.setColumns(10);
    	vNoI.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	vNoI.setBounds(330, 130, 170, 30);
    	add(vNoI);
    	

    	name=new JLabel("NAME");
    	name.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	name.setBounds(150, 180, 170, 40);
    	add(name);
    	
    	nameI=new JTextField();
    	nameI.setColumns(10);
    	nameI.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	nameI.setBounds(330, 190, 170, 30);
    	add(nameI);
    	
    	
    	pNo=new JLabel("PHONE NO");
    	pNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	pNo.setBounds(150, 240, 170, 40);
    	add(pNo);
    	
    	pNoI=new JTextField();
    	pNoI.setColumns(10);
    	pNoI.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	pNoI.setBounds(330, 250, 170, 30);
    	add(pNoI);
    	
    	sDate=new JLabel("IN TIME");
    	sDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	sDate.setBounds(150, 300, 170, 40);
    	add(sDate);
    	
    	sDateI=new JLabel();
    	sDateI.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	sDateI.setBounds(330, 310, 170, 30);
    	add(sDateI);
    	
    	date=new JButton("DATE");
    	date.setBounds(520,305,110,30);
    	add(date);
    	date.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pattern = "dd:MM:YYYY HH:mm:ss";
				SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern, new Locale("en", "IN"));
				String date = simpleDateFormat.format(new Date());
				System.out.println(date);
				sDateI.setText(date);
			}
		});
    	type=new JLabel("TYPE");
    	type.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	type.setBounds(150, 360, 170, 40);
    	
    	add(type);
    	
    	cType=new Choice();
    	cType.add("SELECT");
    	cType.add("CAR");
    	cType.add("BIKE");
    	cType.setFont(new Font("Tahoma", Font.PLAIN, 10));
    	cType.setBounds(330,370,170,90);
    	cType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setTypV(veh[cType.getSelectedIndex()]);
			}
		});
    	add(cType);
    	
    	
    	address=new JLabel("ADDRESS");
    	address.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	address.setBounds(150, 420, 170, 40);
    	add(address);
    	
    	addI=new JTextArea();
    	addI.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	addI.setBounds(330, 420, 250, 90);
    	add(addI);
    	
    	back=new JButton("BACK");
    	back.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	back.setBounds(120,590,150,50);
    	add(back);
    	back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			new entry();
				
			}
		});
    	
    	submit=new JButton("SUBMIT");
    	submit.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	submit.setBounds(370,590,150,50);
    	add(submit);
    	submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            setVno(vNoI.getText());
            setName(nameI.getText());
            setPno(pNoI.getText());
            setSdate(sDateI.getText());
            boolean al=new loading().isAlready(getVno());
           System.out.println(al);
            setAdd(addI.getText());
            if(!isValid()) {
            	flag=false;
            	JOptionPane.showMessageDialog(null, "Enter Valid Number");
            }
            else if(al) {
            	flag=false;
        		JOptionPane.showMessageDialog(null, "Vehicle number already exist");
        	}
            else  if(getName().length()==0) {
            	flag=false;
            	JOptionPane.showMessageDialog(null, "Enter a valid Name!");
            }
            else  if(!isValidNum()){
            	flag=false;
            	JOptionPane.showMessageDialog(null, "Enter a valid Mobile Number!");
        	}
            else  if(getSdate().length()==0) {
            	flag=false;
            	JOptionPane.showMessageDialog(null, "Please select date!");
            }
            else if(getTypV()!=null&&getTypV().equals("select")) {
            	flag=false;
            	JOptionPane.showMessageDialog(null, "Select!");
            	
            }
            else  if(getAdd()!=null&&getAdd().length()==0) {
            	flag=false;
            	JOptionPane.showMessageDialog(null, "Enter a valid address");
            }
            else   if(getAdd().length()<5) {
            	 flag=false;
             	JOptionPane.showMessageDialog(null, "Enter a valid address");
             }
          //  System.out.println(!getTypV().equals("select")&&isValid()&&new loading().isAlready(getVno()) &&getName().length()!=0&&isValidNum() && getSdate().length()!=0&&getAdd().length()!=0&&getAdd().length()>5);
            if(getTypV()!=null&&!getTypV().equals("select")&&isValid()&&!new loading().isAlready(getVno()) &&getName()!=null&&getName().length()!=0&&isValidNum() &&getSdate()!=null&& getSdate().length()!=0&&getAdd()!=null&&getAdd().length()>5) {
            	flag=true;
            }
            
            if(flag){
            	insertInfo();
            	JOptionPane.showMessageDialog(null, "Successfully vehicle parked!");
            }
            else {
            	JOptionPane.showMessageDialog(null, "Please check all the parameters!");
            }
			}
		});
    	exit=new JButton("EXIT");
    	exit.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	exit.setBounds(540,590,100,50);
    	add(exit);
    	exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             System.exit(0);
			}
		});
    	  
    	setFont(new Font("Tahoma", Font.PLAIN, 15));
    	setSize(720,800);
    	setBackground(new Color(255, 255, 255));
    	setResizable(true);
    	setVisible(true);
    }
    public boolean isValid() {
    	 String regex = "^[A-Za-z]{2}[0-9]{2}[A-Za-z]{2}[0-9]{4}$";
         Pattern p = Pattern.compile(regex);
         if(getVno()==null)
        	 return false;
         Matcher m = p.matcher(getVno());
         return m.matches();
    }
	public static void main(String[] args) {
	new inputForm();

	}
	public String getVno() {
		return vno;
	}
	public void setVno(String vno) {
		this.vno = vno;
	}
	public String getNameO() {
		return nameO;
	}
	public void setNameO(String nameO) {
		this.nameO = nameO;
	}
	public String getPno() {
		return pno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getTypV() {
		return typV;
	}
	public void setTypV(String typV) {
		this.typV = typV;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public void printInfo() {
		System.out.println(getVno()+" "+getName()+" "+getPno()+" "+getTypV()+""+getAdd());
	}
	public boolean isValidNum() {
		Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 
		if(getPno()==null)
			return false;
		Matcher match = ptrn.matcher(getPno());
		return (match.find() && match.group().equals(getPno()));  
	}
	public void insertInfo() {
		String q="insert into info(v_no,name,phno,date,v_type,address,occupancy) values(?,?,?,?,?,?,?);";
		String arr[]= {getVno(),getName(),getPno(),getSdate(),getTypV(),getAdd(),"in"};
		new loading().insertable(q, arr);
		new loading().insertInTable("insert into in_info(intoken_no) values(?);","select token_no from info where v_no='"+getVno() +"';");
		dispose();
		new entry();
		
	}

}
