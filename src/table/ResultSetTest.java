/*
 * 레코드 결과를 배열로 받을때의 단점
 * 레코드의 총 갯수를 알 수가 없다..
 * */
package table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetTest {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "batman";
	String password = "1234";

	Connection con; //접속시도가 아니라 접속한 결과를 담은 객체다.
	PreparedStatement pstmt;
	ResultSet rs;
	
	
	//레코드셋 객체를 이용하여 총 레코드 수 알아맞춰보기!!
	public ResultSetTest() {
		try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, user, password);
			if(con!=null){
				String sql="select * from  company";
				
				//rs의 커서를 전방향, 후방향으로 자유롭게 움직이거나, 한꺼번에 건너뛰게 하려면 스크롤 가능한 상수옵션을 부여해야한다.
				//select문의 결과집합을 대상으로 단지 보기만 할꺼면, READ_ONLY로 수정을 가할꺼면 UPDATABLE, 보통은 select문은 읽기용이다.
				pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = pstmt.executeQuery();
				//제일 마지막 레코드로 보내기
				rs.last();
				int num=rs.getRow(); //현재 커서가 가리키는 레코드 번호, 즉 레코드의 위치!
				System.out.println(num);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
		new ResultSetTest();

	}

}
