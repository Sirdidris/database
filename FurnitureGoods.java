package data;

public class FurnitureGoods {
	
	private String name = "";
	private String code = "";
	private float amount = -1;
	private String unitOfCount = "";
	private float buyPrice = 0;
	private float sellPrice = 0;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getUnitOfCount() {
		return unitOfCount;
	}
	public void setUnitOfCount(String unitOfCount) {
		this.unitOfCount = unitOfCount;
	}
	public float getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}
	public float getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}
	
}
