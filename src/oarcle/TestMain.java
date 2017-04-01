/*
 * 우리가 사용중인 데이터베이스 제품은 모두 DBMS이다. DB(저장),MS(관리시스템)
 * 네트워크 기반이라서 원격 접속 가능하다!
 * 원격지의 호스트를 접속하려면 그 호스트의 주소를 알아야 하는데, 현재 사용중인 네트워크 프로토콜은 TCP/IP기반이므로 IP 주소를 알아야 한다.
 * 
 * */

package oarcle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestMain {

	public static void main(String[] args) {
		Connection con = null; //finally 에서 닫으려고,
		PreparedStatement pstmt = null; //finally 에서 닫으려고,
		
		//1단계 오라클을 자바가 제어할 수 있는 코드가 들어있는 jar파일을 메모리에 로드해야 한다. 이런 데이터베이스 제어 jar파일을 자바에서는 드라이버(db 제조사 제공)라 한다.
		//2단계 오라클에 접속하자
		
		//드라이버 클래스 로드!! 희한한게 스트링형으로 넣으면 됨
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
			
			//오라클에 접속하자!
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "batman", "1234"); 
			if(con!=null){
				System.out.println("접속성공!!");
				
				//접속 성공했으면 쿼리 날리쟈!
				//현재 유저가 보유한 테이블에 insert
				//쿼리문을 String 형으로 보내야 한다.
				String sql="insert into company(company_id, brand) values(seq_company.nextval, '나이키')";
				//쿼리문 수행을 위해서는 쿼리문 전담 객체를 이용해야 하는데, 이 객체는 PreparedStatement
				pstmt=con.prepareStatement(sql);
				int result =pstmt.executeUpdate(); //쿼리문 실행 메서드
				//이 쿼리문 수행에 의해 반영된 레코드의 수를 반환해준다. insert의 경우는 항상 레코드 1개만 넣을 수 있기에... 1일꺼다
				
				
				if(result==1){
					System.out.println("입력성공");
				}else{
					System.out.println("입력실패");
				}
				
				
				
				
			}else{
				System.out.println("접속실패!!");
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("오라클에 접속 실패");
			e.printStackTrace();
		} finally{
			//스트림과 DB 연동작업 후엔 반드시 닫는 처리를 해야한다!!
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
}
