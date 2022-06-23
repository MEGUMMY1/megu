package vendingMachine;

public class setMoney {
	private int countOfMoney;		// 동전별 개수
	private int unitOfMoney;		// 동전별 금액
	private String money;			// 동전별 이름

	public int getcountOfMoney() {
		return countOfMoney;
	}
	
	public void setcountOfMoney(int countOfMoney) {
		this.countOfMoney = countOfMoney;
	}
	
	public int getunitOfMoney() {
		return unitOfMoney;
	}
	
	public void setunitOfMoney(int unitOfMoney) {
		this.unitOfMoney = unitOfMoney;
	}
	
	public String getMoney() {
		return money;
	}
	
	public void setMoney(String money) {
		this.money = money;
	}
}
