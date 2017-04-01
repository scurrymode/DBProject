/*
 * 이 객체는 JTable의 생성자에서 요구하는 컨트롤러 객체이다.
 * 이 객체의 역할은 디자인과 로직을 분리시켜주는 중간자역할...!
 * */

package model;

import java.sql.PreparedStatement;

import javax.swing.table.AbstractTableModel;

public class MariaModel extends AbstractTableModel{
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	String[][] data = new String[3][3];
		
	public MariaModel() {
		data[0][0]="청바지";
		data[0][1]="지오다노";
		data[0][2]="20,000원";
		data[1][0]="가디건";
		data[1][1]="뱅뱅";
		data[1][2]="30,000원";
		data[2][0]="치마";
		data[2][1]="나이키";
		data[2][2]="50,000원";
	}
	
	//컬럼의 갯수를 반환
	public int getColumnCount() {
		return data[0].length;
	}

	//레코드의 갯수를 반환
	public int getRowCount() {
		return data.length;
	}

	//특정위치의 값을 반환
	public Object getValueAt(int row, int col) {
		System.out.println("row="+row+"col="+col+"에 뭘 넣어야해요?");
		return data[row][col];
	}
 
}
