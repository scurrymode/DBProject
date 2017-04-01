/*
 * �츮�� ������� �����ͺ��̽� ��ǰ�� ��� DBMS�̴�. DB(����),MS(�����ý���)
 * ��Ʈ��ũ ����̶� ���� ���� �����ϴ�!
 * �������� ȣ��Ʈ�� �����Ϸ��� �� ȣ��Ʈ�� �ּҸ� �˾ƾ� �ϴµ�, ���� ������� ��Ʈ��ũ ���������� TCP/IP����̹Ƿ� IP �ּҸ� �˾ƾ� �Ѵ�.
 * 
 * */

package oarcle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestMain {

	public static void main(String[] args) {
		Connection con = null; //finally ���� ��������,
		PreparedStatement pstmt = null; //finally ���� ��������,
		
		//1�ܰ� ����Ŭ�� �ڹٰ� ������ �� �ִ� �ڵ尡 ����ִ� jar������ �޸𸮿� �ε��ؾ� �Ѵ�. �̷� �����ͺ��̽� ���� jar������ �ڹٿ����� ����̹�(db ������ ����)�� �Ѵ�.
		//2�ܰ� ����Ŭ�� ��������
		
		//����̹� Ŭ���� �ε�!! �����Ѱ� ��Ʈ�������� ������ ��
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����");
			
			//����Ŭ�� ��������!
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "batman", "1234"); 
			if(con!=null){
				System.out.println("���Ӽ���!!");
				
				//���� ���������� ���� ������!
				//���� ������ ������ ���̺� insert
				//�������� String ������ ������ �Ѵ�.
				String sql="insert into company(company_id, brand) values(seq_company.nextval, '����Ű')";
				//������ ������ ���ؼ��� ������ ���� ��ü�� �̿��ؾ� �ϴµ�, �� ��ü�� PreparedStatement
				pstmt=con.prepareStatement(sql);
				int result =pstmt.executeUpdate(); //������ ���� �޼���
				//�� ������ ���࿡ ���� �ݿ��� ���ڵ��� ���� ��ȯ���ش�. insert�� ���� �׻� ���ڵ� 1���� ���� �� �ֱ⿡... 1�ϲ���
				
				
				if(result==1){
					System.out.println("�Է¼���");
				}else{
					System.out.println("�Է½���");
				}
				
				
				
				
			}else{
				System.out.println("���ӽ���!!");
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("����̹��� ã�� �� �����ϴ�.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("����Ŭ�� ���� ����");
			e.printStackTrace();
		} finally{
			//��Ʈ���� DB �����۾� �Ŀ� �ݵ�� �ݴ� ó���� �ؾ��Ѵ�!!
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
