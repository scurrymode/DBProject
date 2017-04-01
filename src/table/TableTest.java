/*
 * swing�� ������Ʈ �� �����ͺ��̽��� ��� ������ �ð�ȭ�ϱ⿡ ����ȭ�� ������Ʈ�� JTable�̴�.
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

public class TableTest extends JFrame{
	
	JTable table;
	JScrollPane scroll;
	
	//�����Ƶ�� ������Ű��
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331";
	String user="root";
	String password="";
	
	String sql="select * from member";
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs; //select���� ��츸 �ʿ��ϴ�.
	
	String[][] data;
	String[] column={"member_id","name","age"};
	
	
	public TableTest() {
		loadData();
		
		table = new JTable(data, column);
		scroll = new JScrollPane(table);
		
		add(scroll);
		setSize(500, 150);
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
				pstmt = con.prepareStatement(sql);
				//������ ��� ���
				rs = pstmt.executeQuery();
				//2���� �迭�� ���
				
			
				ResultSetMetaData rsmd= rs.getMetaData();
				int cc= rsmd.getColumnCount();
				data = new String[4][cc]; //���߿��� �̰� ������ �ؾ��Ѵ�!
				
				int index =0;
				while(rs.next()){
					data[index][0]=Integer.toString(rs.getInt("member_id"));
					data[index][1]=rs.getString("name");
					data[index][2]=Integer.toString(rs.getInt("age"));
					
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
		new TableTest();

	}

}
