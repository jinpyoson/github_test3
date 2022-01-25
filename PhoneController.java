package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.SmartPhoneDAO;
import DTO.SmartPhone;

public class PhoneController {

	public static void main(String[] args) {
		SmartPhoneDAO dao=new SmartPhoneDAO();
		Scanner sc = new Scanner(System.in);
		int choice=0;
		String company="";
		String name="";
		int price=0;
		int weight=0;
		String provider="";
		String color="";
		ArrayList<SmartPhone> smartArrayList=new ArrayList<SmartPhone>();
		while(true) {
			menu();
			choice=sc.nextInt();
			if(choice==1) {
				smartArrayList=dao.Search();//모두조회
				//dao접근해서 데이터 arrayList형태로 넘겨받아서
				//화면에 출력한다. (MVC흉내...)
				for(int i=0; i<smartArrayList.size(); i++) {
					smartArrayList.get(i).showSmartphoneInfo();
				}
				
			}else if(choice==2) {
				System.out.print("제조사 입력:");
				company=sc.next();
				dao.SearchByCompany(company);
			}else if(choice==3) {
				System.out.print("모델명 입력:");
				name=sc.next();
				dao.SearchLikeName(name);
			}else if(choice==4) {
				System.out.print("모델명 입력:");
				name=sc.next();
				
				System.out.print("제조사 입력:");
				company=sc.next();
				
				System.out.print("가격 입력:");
				price=sc.nextInt();
				
				System.out.print("통신사 입력:");
				provider=sc.next();
				
				System.out.print("무게 입력:");
				weight=sc.nextInt();
				
				System.out.print("색깔 입력:");
				color=sc.next();
				
				dao.InsertSmartPhoneInfo(name,company,price,provider,weight,color);
			}else if(choice==5) {
				//수정...
				sc.nextLine();//엔터처리
				System.out.print("모델명 입력:");
				name=sc.nextLine();
				
				System.out.print("색깔 입력:");
				color=sc.next();
				
				System.out.print("가격 입력:");
				price=sc.nextInt();
				
				dao.UpdateSmartPhoneInfo(name,color,price);
				
			}else if(choice==6) {
				sc.nextLine();//엔터처리
				System.out.print("모델명 입력:");
				name=sc.nextLine();
				
				System.out.print("색깔 입력:");
				color=sc.next();
				
				dao.deleteSmartPhoneInfo(name,color);
			}
		}
	}
	public static void menu() {
		System.out.println("1.스마트폰 정보 모두 조회");
		System.out.println("2.스마트폰 정보 조건 조회");
		System.out.println("3.스마트폰 모델명 검색");
		System.out.println("4.스마트폰 정보 추가");
		System.out.println("5.스마트폰 정보 수정");
		System.out.println("6.스마트폰 정보 삭제");
		System.out.println("7.종료");
	}
}
