/*
 * � ���ø����̼��� �����ϴ� ������������ ���ƾ� ����� �����ǰ�, ���ø����̼��� ǰ���� �ö󰣴�. Ư��, �����ΰ� ������ �����ִ� GUI�� �ִ� ���ø����̼��� ���,  
 * �������� ���� ������ �ʴ� ���������� ������Ʈ�� ���� �����η����� ���� ����������, ���� ���������� ����Ǿ�����, �����ϱ� �����, ������������ ��������.
 * �̷��� ������ ������ �������� ��������, ������ ����鿡 ���� �̹� �����ߴ� ������. 
 * ������ ������(��)�� �и����� �����ߴ��� ������������ ���� �ö󰬴ٴ� ������ ������ MVC(model view controller) �����̴�. 
 * JTable�� swing ������Ʈ �� MVC ������ ��� �ִ� ������Ʈ�̸�, �����ο� �ش��ϴ� JTable�� ������ �ش��ϴ� DB�����͸� �и���Ű�� ����
 * TableModel�̶�� �߰� �и���(controller)�� �������ش�. 
 * 
 * */

package model;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ModelTest extends JFrame {
	JTable table;
	JScrollPane scroll;
	 
	public ModelTest() {
		table= new JTable(new MariaModel());
		scroll= new JScrollPane(table);
		
		add(scroll);
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}

	public static void main(String[] args) {
		new ModelTest();
		

	}

}
