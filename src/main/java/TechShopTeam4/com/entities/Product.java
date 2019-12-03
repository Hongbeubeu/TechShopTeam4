package TechShopTeam4.com.entities;

public class Product {
	private int id;
	private int descriptionId;
	private int quantity;
	private float price;
	private int createAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDescriptionId() {
		return descriptionId;
	}
	public void setDescriptionId(int descriptionId) {
		this.descriptionId = descriptionId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getCreateAt() {
		return createAt;
	}
	public void setCreateAt(int createAt) {
		this.createAt = createAt;
	}
	
}
