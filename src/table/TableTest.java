/*
 * swing의 컴포넌트 중 데이터베이스의 결과 집합을 시각화하기에 최적화된 컴포넌트가 JTable이다.
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
	
	//마리아디비 연동시키기
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331";
	String user="root";
	String password="";
	
	String sql="select * from member";
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs; //select문인 경우만 필요하다.
	
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
	
	//레코드 채워 넣기, 테이블을 생성하기 전에 마리아 디비를 연동하여 멤버테이블에 레코드를 2차원 배열에 담아놓자! 그래야 JTable의 생성자의 인수로 2차원 배열이 사용되니까!
	public void loadData(){
			try {
				//1단계 드라이버 로드하라
				Class.forName(driver);
				//2단계 접속하라
				con = DriverManager.getConnection(url, user, password);
				//3단계 쿼리문 날려라
				pstmt = con.prepareStatement(sql);
				//쿼리문 결과 담기
				rs = pstmt.executeQuery();
				//2차원 배열에 담기
				
			
				ResultSetMetaData rsmd= rs.getMetaData();
				int cc= rsmd.getColumnCount();
				data = new String[4][cc]; //나중에는 이걸 변수로 해야한다!
				
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
			}//4단계 디비를 닫아라 
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
