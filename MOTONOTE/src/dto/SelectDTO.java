package dto;

public class SelectDTO {
	private int re;
	private String content;
	private String calender;
	private int cost;
	private int sum;
	private int spending;
	private int income;
	public SelectDTO(){

	}
	public SelectDTO(int re, String content, int cost, String calender) {
		super();
		this.re = re;
		this.content = content;
		this.cost = cost;
		this.setCalender(calender);
	}

	public SelectDTO(int sum, int spending, int income) {
		super();
		this.sum = sum;
		this.spending = spending;
		this.income = income;
	}
	public int getSpending() {
		return spending;
	}
	public void setSpending(int spending) {
		this.spending = spending;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getRe() {
		return re;
	}
	public void setRe(int re) {
		this.re = re;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getCalender() {
		return calender;
	}
	public void setCalender(String calender) {
		this.calender = calender;
	}

}