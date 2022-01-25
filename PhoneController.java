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
				smartArrayList=dao.Search();//�����ȸ
				//dao�����ؼ� ������ arrayList���·� �Ѱܹ޾Ƽ�
				//ȭ�鿡 ����Ѵ�. (MVC�䳻...)
				for(int i=0; i<smartArrayList.size(); i++) {
					smartArrayList.get(i).showSmartphoneInfo();
				}
				
			}else if(choice==2) {
				System.out.print("������ �Է�:");
				company=sc.next();
				dao.SearchByCompany(company);
			}else if(choice==3) {
				System.out.print("�𵨸� �Է�:");
				name=sc.next();
				dao.SearchLikeName(name);
			}else if(choice==4) {
				System.out.print("�𵨸� �Է�:");
				name=sc.next();
				
				System.out.print("������ �Է�:");
				company=sc.next();
				
				System.out.print("���� �Է�:");
				price=sc.nextInt();
				
				System.out.print("��Ż� �Է�:");
				provider=sc.next();
				
				System.out.print("���� �Է�:");
				weight=sc.nextInt();
				
				System.out.print("���� �Է�:");
				color=sc.next();
				
				dao.InsertSmartPhoneInfo(name,company,price,provider,weight,color);
			}else if(choice==5) {
				//����...
				sc.nextLine();//����ó��
				System.out.print("�𵨸� �Է�:");
				name=sc.nextLine();
				
				System.out.print("���� �Է�:");
				color=sc.next();
				
				System.out.print("���� �Է�:");
				price=sc.nextInt();
				
				dao.UpdateSmartPhoneInfo(name,color,price);
				
			}else if(choice==6) {
				sc.nextLine();//����ó��
				System.out.print("�𵨸� �Է�:");
				name=sc.nextLine();
				
				System.out.print("���� �Է�:");
				color=sc.next();
				
				dao.deleteSmartPhoneInfo(name,color);
			}
		}
	}
	public static void menu() {
		System.out.println("1.����Ʈ�� ���� ��� ��ȸ");
		System.out.println("2.����Ʈ�� ���� ���� ��ȸ");
		System.out.println("3.����Ʈ�� �𵨸� �˻�");
		System.out.println("4.����Ʈ�� ���� �߰�");
		System.out.println("5.����Ʈ�� ���� ����");
		System.out.println("6.����Ʈ�� ���� ����");
		System.out.println("7.����");
	}
}
