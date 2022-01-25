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
	private Connection con;//연결관련
	private Statement st;//쿼리문 실행관련
	private PreparedStatement pstmt;//쿼리문 실행관련
	//준비된 스테이트먼트 (? 물음표 들어가는 쿼리)
	//? 준비시켜놓고 -> 세팅 -> 실행
	//insert into ~~~ values (a,b,c,d,e,f);
	//insert,update,delete...에 주로 쓰인다.
	
	private ResultSet rs;//쿼리 실행한 결과를 담을 리절트 셋(결과집합)
	public SmartPhoneDAO(){
		smartArrayList=new ArrayList<SmartPhone>();
		//java database connectivity
		//오라클 씬 버전 (express버전)-18c,19c,21c (express)
		//express,enterprise
		//localhost:내 컴퓨터 (127.0.0.1)
		//1521-포트번호 (프로그램 번호)
		//xe-SID, SID-xe 또는 orcl
		try {
			String user="c##scott";//system
			String pw="tiger";//1234
			String url="jdbc:oracle:thin:@localhost:1521:xe";//주소
			Class.forName("oracle.jdbc.driver.OracleDriver");//오라클 드라이버 가져옴
			try {
				con=DriverManager.getConnection(url,user,pw);//연결
				st=con.createStatement();//스테이트먼트 객체 생성
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//데이터베이스에 SQL문자을 보내기 위한 스테이트먼트 객체를 생성한다
			//Creates a Statement object for sending SQL statements to the database
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<SmartPhone> Search() {
		String SQL="SELECT * FROM smartphone";
		try {
			rs=st.executeQuery(SQL);//쿼리를 실행할 결과를 ResultSet rs에 담음
			while(rs.next()) {//행 데이터가 존재할때 까지 반복을 수행함
				String name=rs.getString("name");//칼럼에 해당하는 값을 읽어서 반환함
				String company=rs.getString("company");
				int price=rs.getInt("price");
				String provider=rs.getString("provider");
				int weight=rs.getInt("weight");	
				String color=rs.getString("color");

				SmartPhone vo=new SmartPhone(name,company,price,provider,weight,color);
				smartArrayList.add(vo);//생성한 객체를 ArrayList의 원소로 추가
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
		String SQL="SELECT * FROM smartphone where name like ?";//%갤럭시%
		//미완성형 쿼리를 쓰는게 preparedStatement (준비된 스테이트먼트)
		//미완성형 쿼리를 써서 나중에 값세팅한다.
		try {
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1, "%"+input_name+"%");//첫번째 물음표에 %갤럭시%를 넣겠다.
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
			pstmt.setString(1,name);//첫번째 물음표에 name집어넣기
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
			pstmt.setInt(1,price);//첫번째 물음표에 price집어넣기
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
