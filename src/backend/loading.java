package backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.*;

import com.mysql.cj.protocol.Resultset;
public class loading {
	 connectionDB connection = new connectionDB();
     Connection con = null;
     PreparedStatement p = null;
     ResultSet rs = null;
     ArrayList<String>inv_no=new ArrayList<>();
     ArrayList<Integer>in_tok_info=new ArrayList<>();
     
     boolean arr[][];
     
     public String inDate,vType;
     //con = connection.connectDB();
 
     //opens at loading time
     public loading() {
		con=connection.connectDB();
		arr=new boolean[10][10];
		//Arrays.fill(arr, false);
		try {
			//loading vehicle no for input form and displaying vacant count in entry form
			p=con.prepareStatement("select v_no,loc from info where occupancy='in'");
			rs=p.executeQuery();
			while(rs.next()) {
				inv_no.add(rs.getString(1));
				int n=Integer.parseInt(rs.getString(2));
				int i=n/10;
				int j=n%10;
				arr[i-1][j-1]=true;
				System.out.println(i+" "+j);
			}
			
			
			//select toke no from out_info table for checking if veh is parked or not
			p=con.prepareStatement("select intoken_no from in_info;");
			rs=p.executeQuery();
			while(rs.next()) {
				in_tok_info.add(rs.getInt(1));
			}
			
			//System.out.println(in_tok_info);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
     public String getvType() {
		return vType;
	}
	public void setvType(String vType) {
		this.vType = vType;
	}
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String outDate) {
		this.inDate = outDate;
	}
	//function found if tokeno of vehicle in parking lot
     public boolean isFound(int tokno) {
    	if(in_tok_info.contains(tokno)) {
    		try {
				p=con.prepareStatement("select v_no from info where occupancy='in'");
				rs=p.executeQuery();
				if(rs.next()) {
					inv_no.add(rs.getString(1));
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
    	}
    	return in_tok_info.contains(tokno);
     }
     
     //over all insertion
	 public void insertable(String q,String ar[]) {
		   try {
				String s=findEmpty();
				p=con.prepareStatement(q);
				p.setString(1, ar[0]);
				p.setString(2, ar[1]);
				p.setString(3, ar[2]);
				p.setString(4, ar[3]);
				p.setString(5, ar[4]);
				p.setString(6, ar[5]);
				p.setString(7, ar[6]);
				p.setString(8, s);
				p.executeUpdate();
				//con.setAutoCommit(true);
				//con.close();
				String tokno=token_no(ar[0]);
				
				JOptionPane.showMessageDialog(null, "Token_no:"+tokno+"\nSlot:"+s);
				
	   	}catch(Exception e) {
	   		JOptionPane.showMessageDialog(null, "Exception--->"+e.getMessage());
	   	}
	   }
	 
	 //getting token no
	 public String token_no(String v_no) {
		 String s="";
		 try {
			   
			    p=con.prepareStatement("select token_no from info where v_no='"+v_no+"';");
			    rs=p.executeQuery();
			    if(rs.next()) {
			    	s=String.valueOf(rs.getInt(1));
			    }
			    return s;
			    //p.executeUpdate();
				//con.setAutoCommit(true);
				
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Exception--->"+e.getMessage());
		}
		 return s;
	 }
	 
	 //insert into input info table
	  public void insertInTable(String q,String q1) {
		try {
			    String s="";
			    p=con.prepareStatement(q1);
			    rs=p.executeQuery();
			    if(rs.next()) {
			    	s=rs.getString(1);
			    }
			    p=con.prepareStatement(q);
			    p.setString(1, s);
			    p.executeUpdate();
				con.setAutoCommit(true);
				//con.close();
				//JOptionPane.showMessageDialog(null, "Token No:"+token_no(s));
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Exception--->"+e.getMessage());
		}
		
	  }
	  //function for returning vacant space
	  public String totVacCnt() {
		  return String.valueOf(100-inv_no.size());
	  }
	  
	  //return input date and v_type
	  public String getOut(String q) {
		  try {

				 p=con.prepareStatement("select date from info where token_no="+Integer.parseInt(q)+";");
				 rs=p.executeQuery();
				 if(rs.next()) {
				  setInDate(rs.getString(1));
				 }
				 System.out.println(getInDate());
				 
				 p=con.prepareStatement("select v_type from info where token_no="+Integer.parseInt(q)+";");
				 rs=p.executeQuery();
				 if(rs.next()) {
				  setvType(rs.getString(1));
				 }
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}	
		 // System.out.println(getvType());
		  return getInDate()+getvType();
	  }
	  
	  //insert into out table info
	  public void outVal(String q,String totH) {
		try {
			//updation main info table
			p=con.prepareStatement("update info set occupancy='out' where token_no=(?);");
			p.setString(1, q);
			p.executeUpdate();
			
			//delete value from in_info table
			p=con.prepareStatement("delete from in_info where intoken_no=(?);");
			p.setString(1, q);
			p.executeUpdate();
			
			//inserting into out_info table
			p=con.prepareStatement("insert into out_info(intoken_no,tot_hours) values(?,?);");
			p.setString(1, q);
			p.setString(2, totH);
			p.executeUpdate();
			
			con.setAutoCommit(true);
			con.close();
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}	
	}
	  //find empty slot
	  public String findEmpty() {
		  for(int i=0;i<arr.length;i++) {
			  for(int j=0;j<arr[0].length;j++) {
				  if(!arr[i][j]) {
					 String ans=String.valueOf((10*(i+1))+(j+1));
					 return ans;
				  }
			  }
		  }
		  return "";
	  }
	  //check vehicle already exist or not
	  public boolean isAlready(String s) {
		  return inv_no.contains(s);
	  }
	  public String[][] allInfo(){
		  
		  try {
			  p=con.prepareStatement("select * from info");
			  rs=p.executeQuery();
			  int size = 0;
			  if(rs!=null) {
				  rs.last();
				  size = rs.getRow();
			  }
			  //System.out.println(size);
			  int index = 0;
			  String arr[][]= new String[size][9];
			  p=con.prepareStatement("select * from info");
			  rs=p.executeQuery();
			  while(rs.next()) {
				  arr[index][0] = String.valueOf(rs.getInt(1));
				  arr[index][1] = rs.getString(2);
				  arr[index][2] = rs.getString(3);
				  arr[index][3] = rs.getString(4);
				  arr[index][4] = rs.getString(5);
				  arr[index][5] = rs.getString(6);
				  arr[index][6] = rs.getString(7);
				  arr[index][7] = rs.getString(8);
				  arr[index][8]=rs.getString(9);
				  //System.out.println(Arrays.toString(arr[index]));
				  index++;
			  }
			  return arr;
		  }catch (Exception e) {
				
				e.printStackTrace();
			}
		  return new String[][] {};
	  }
	public static void main(String[] args) {
		
	}

}
