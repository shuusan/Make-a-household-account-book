package dto;

public class DeleteDTO {
	private int re;
	private String content;
	private int cost;

	DeleteDTO(){

	}

	public DeleteDTO(int re, String content, int cost) {
		super();
		this.re = re;
		this.content = content;
		this.cost = cost;
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

}
