/*
 * swing�� ������Ʈ �� �����ͺ��̽��� ��� ������ �ð�ȭ�ϱ⿡ ����ȭ�� ������Ʈ�� JTable�̴�.
 * 
 * ���ڵ��� ������ ���� �迭�� ũ�⸦ �����ؼ� �����غ���
 * */
package table;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableTest2 extends JFrame{
	
	JTable table;
	JScrollPane scroll;
	
	//�����Ƶ�� ������Ű��
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	String sql="select * from emp";
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs; //select���� ��츸 �ʿ��ϴ�.
	
	String[][] data;
	String[] column={"empno","ename","job","mgr","hiredate","sal","comm","deptno"};
	
	
	public TableTest2() {
		loadData();
		
		table = new JTable(data, column);
		scroll = new JScrollPane(table);
		
		add(scroll);
		setSize(900, 350);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	//���ڵ� ä�� �ֱ�, ���̺��� �����ϱ� ���� ������ ��� �����Ͽ� ������̺� ���ڵ带 2���� �迭�� ��Ƴ���! �׷��� JTable�� �������� �μ��� 2���� �迭�� ���Ǵϱ�!
	public void loadData(){
			try {
				//1�ܰ� ����̹� �ε��϶�
				
				Class.forName(driver);
				//2�ܰ� �����϶�
				con = DriverManager.getConnection(url, user, password);
				//3�ܰ� ������ ������
				pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				//������ ��� ���
				rs = pstmt.executeQuery();
				//2���� �迭�� ���
				
				//rs�� last()�� ������ ��ġ�� ����!
				rs.last();
				int row = rs.getRow();
				//�ټ� �˷������� ó������ �ٽ� ����~!
				rs.beforeFirst();
				
				data = new String[row][column.length]; //���߿��� �̰� ������ �ؾ��Ѵ�!
				
				int index =0;
				while(rs.next()){
					data[index][0]=Integer.toString(rs.getInt("empno"));
					data[index][1]=rs.getString("ename");
					data[index][2]=rs.getString("job");
					data[index][3]=Integer.toString(rs.getInt("mgr"));
					data[index][4]=rs.getString("hiredate");
					data[index][5]=Integer.toString(rs.getInt("sal"));
					data[index][6]=rs.getString("comm");
					data[index][7]=Integer.toString(rs.getInt("deptno"));
					index++;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//4�ܰ� ��� �ݾƶ� 
			finally {
				if(rs!=null){
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(pstmt!=null){
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(con!=null){
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}		
	}
	
	
	
	public static void main(String[] args) {
		new TableTest2();

	}

}
