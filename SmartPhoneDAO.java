package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.SmartPhone;
public class SmartPhoneDAO {
	private ArrayList<SmartPhone> smartArrayList;
	private Connection con;//�������
	private Statement st;//������ �������
	private PreparedStatement pstmt;//������ �������
	//�غ�� ������Ʈ��Ʈ (? ����ǥ ���� ����)
	//? �غ���ѳ��� -> ���� -> ����
	//insert into ~~~ values (a,b,c,d,e,f);
	//insert,update,delete...�� �ַ� ���δ�.
	
	private ResultSet rs;//���� ������ ����� ���� ����Ʈ ��(�������)
	public SmartPhoneDAO(){
		smartArrayList=new ArrayList<SmartPhone>();
		//java database connectivity
		//����Ŭ �� ���� (express����)-18c,19c,21c (express)
		//express,enterprise
		//localhost:�� ��ǻ�� (127.0.0.1)
		//1521-��Ʈ��ȣ (���α׷� ��ȣ)
		//xe-SID, SID-xe �Ǵ� orcl
		try {
			String user="c##scott";//system
			String pw="tiger";//1234
			String url="jdbc:oracle:thin:@localhost:1521:xe";//�ּ�
			Class.forName("oracle.jdbc.driver.OracleDriver");//����Ŭ ����̹� ������
			try {
				con=DriverManager.getConnection(url,user,pw);//����
				st=con.createStatement();//������Ʈ��Ʈ ��ü ����
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//�����ͺ��̽��� SQL������ ������ ���� ������Ʈ��Ʈ ��ü�� �����Ѵ�
			//Creates a Statement object for sending SQL statements to the database
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<SmartPhone> Search() {
		String SQL="SELECT * FROM smartphone";
		try {
			rs=st.executeQuery(SQL);//������ ������ ����� ResultSet rs�� ����
			while(rs.next()) {//�� �����Ͱ� �����Ҷ� ���� �ݺ��� ������
				String name=rs.getString("name");//Į���� �ش��ϴ� ���� �о ��ȯ��
				String company=rs.getString("company");
				int price=rs.getInt("price");
				String provider=rs.getString("provider");
				int weight=rs.getInt("weight");	
				String color=rs.getString("color");

				SmartPhone vo=new SmartPhone(name,company,price,provider,weight,color);
				smartArrayList.add(vo);//������ ��ü�� ArrayList�� ���ҷ� �߰�
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return smartArrayList;
	}
	
	public void SearchByCompany(String input_company) {
		String SQL="SELECT * FROM smartphone where company="+"'"+input_company+"'";
		try {
			rs=st.executeQuery(SQL);
			while(rs.next()) {
				String name=rs.getString("name");
				System.out.print(name+" ");
				
				String company=rs.getString("company");
				System.out.print(company+" ");
				
				int price=rs.getInt("price");
				System.out.print(price+" ");
				
				String provider=rs.getString("provider");
				System.out.print(provider+" ");
				
				int weight=rs.getInt("weight");
				System.out.print(weight+" ");
				
				String color=rs.getString("color");
				System.out.print(color+" ");
				System.out.println("");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void SearchLikeName(String input_name) {
		String SQL="SELECT * FROM smartphone where name like ?";//%������%
		//�̿ϼ��� ������ ���°� preparedStatement (�غ�� ������Ʈ��Ʈ)
		//�̿ϼ��� ������ �Ἥ ���߿� �������Ѵ�.
		try {
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1, "%"+input_name+"%");//ù��° ����ǥ�� %������%�� �ְڴ�.
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				String name=rs.getString("name");
				System.out.print(name+" ");
				
				String company=rs.getString("company");
				System.out.print(company+" ");
				
				int price=rs.getInt("price");
				System.out.print(price+" ");
				
				String provider=rs.getString("provider");
				System.out.print(provider+" ");
				
				int weight=rs.getInt("weight");
				System.out.print(weight+" ");
				
				String color=rs.getString("color");
				System.out.print(color+" ");
				System.out.println("");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void InsertSmartPhoneInfo(String name, String company, int price, String provider, int weight,
			String color) {
		String SQL="insert into smartphone(name,company,price,provider,weight,color) "
				+ "values(?,?,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1,name);//ù��° ����ǥ�� name����ֱ�
			pstmt.setString(2,company);
			pstmt.setInt(3,price);
			pstmt.setString(4,provider);
			pstmt.setInt(5,weight);
			pstmt.setString(6,color);
			pstmt.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void UpdateSmartPhoneInfo(String name, String color, int price) {
		String SQL="update smartphone set price=? "
				+ "where name=? and color=?";
		try {
			pstmt=con.prepareStatement(SQL);
			pstmt.setInt(1,price);//ù��° ����ǥ�� price����ֱ�
			pstmt.setString(2,name);
			pstmt.setString(3,color);
			pstmt.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteSmartPhoneInfo(String name, String color) {
		String SQL="delete from smartphone where name=? and color=?";
		try {
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1,name);
			pstmt.setString(2,color);
			pstmt.executeQuery();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
