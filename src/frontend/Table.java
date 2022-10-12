package frontend;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import backend.loading;

public class Table extends JPanel{

    JTable jt;
    public Table(){

        String [] header={"TOKEN_NO","VEHICLE_NO","NAME","PHONE","DATE","VEHICLE_TYPE","ADDRESS","OCCUPANCY","LOCATION"};
        String [][] data=new loading().allInfo();


        DefaultTableModel model = new DefaultTableModel(data,header);

        JTable table = new JTable(model);

        table.setPreferredScrollableViewportSize(new Dimension(1050,200));
        table.setFillsViewportHeight(true);

        JScrollPane js=new JScrollPane(table);
        js.setVisible(true);
        add(js);

    }
    public void loadTable() {
    	JFrame jf=new JFrame();
        Table tab= new Table();
        jf.setTitle("Table");
        jf.setSize(1100, 300);
        //jf.setLayout(null);
        jf.setVisible(true);
        //jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(tab);
    }
    public static void main(String [] a) {

        


    }

}