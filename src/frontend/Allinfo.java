package frontend;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import backend.loading;

public class Allinfo extends JFrame{
	private JTable table;
	private JScrollPane sp;
	private JPanel jp;
	public Allinfo() {
		setLayout(null);
		setSize(800,720);
		setLayout(new GridLayout(0, 1));
		setTitle("All Information Table Window");
		jp = new JPanel();
		jp.setLayout(new FlowLayout());
    	setBackground(new Color(255, 255, 255));
    	setResizable(true);
    	setVisible(true);
	}
	private void createTable() {
		String arr[][] = new loading().allInfo();
		System.out.println(Arrays.toString(arr[0]));
		String parameter[] = {"TOKEN_NO","VEHICLE_NO","NAME","PHONE","DATE","VEHICLE_TYPE","ADDRESS","OCCUPANCY"};
		table = new JTable(arr,parameter);
		table.setBounds(30, 40, 200, 280);
		sp = new JScrollPane(table);
		add(sp);
		table.setFillsViewportHeight(true);
	}
	public static void main(String[] args) {
		new Allinfo().createTable();
	}
}
