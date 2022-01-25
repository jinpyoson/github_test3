package DTO;

public class SmartPhone {
	private String name;//모델명
	private String company;//제조사
	private int price;//가격
	private String provider;//통신사
	private int weight;//가격
	private String color;//색깔
	
	public SmartPhone() {
		
	}
	public SmartPhone(String name,String company,int price,String provider,
			int weight,String color) {
		this.name=name;
		this.company=company;
		this.price=price;
		this.provider=provider;
		this.weight=weight;
		this.color=color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void showSmartphoneInfo() {
		System.out.printf("모델명:%s\n", name);
		System.out.printf("제조사:%s\n", company);
		System.out.printf("가격:%d\n", price);
		System.out.printf("통신사:%s\n", provider);
		System.out.printf("무게:%d\n", weight);
		System.out.printf("색깔:%s\n", color);
	}
}
