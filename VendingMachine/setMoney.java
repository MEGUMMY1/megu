package vendingMachine;

public class setMoney {
	private int countOfMoney;		// ������ ����
	private int unitOfMoney;		// ������ �ݾ�
	private String money;			// ������ �̸�

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
