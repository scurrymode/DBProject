/*
 * �� ��ü�� JTable�� �����ڿ��� �䱸�ϴ� ��Ʈ�ѷ� ��ü�̴�.
 * �� ��ü�� ������ �����ΰ� ������ �и������ִ� �߰��ڿ���...!
 * */

package model;

import java.sql.PreparedStatement;

import javax.swing.table.AbstractTableModel;

public class OracleModel extends AbstractTableModel{
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	String[][] data = new String[4][2];
		
	public OracleModel() {
		data[0][0]="¥���";
		data[0][1]="�ϰ����";
		data[1][0]="�Ķ��̵�";
		data[1][1]="BHC";
		data[2][0]="ġ������ũ";
		data[2][1]="�ĸ��ٰ�Ʈ";
		data[3][0]="������ġ";
		data[3][1]="�������";
	}
	
	//�÷��� ������ ��ȯ
	public int getColumnCount() {
		return data[0].length;
	}

	//���ڵ��� ������ ��ȯ
	public int getRowCount() {
		return data.length;
	}

	//Ư����ġ�� ���� ��ȯ
	public Object getValueAt(int row, int col) {
		System.out.println("row="+row+"col="+col+"�� �� �־���ؿ�?");
		return data[row][col];
	}
 
}
