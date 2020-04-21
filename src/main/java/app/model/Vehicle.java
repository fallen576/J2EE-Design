package app.model;

import static app.utils.StringUtils.isEmpty;

public class Vehicle {
	
	private long id;
	private VehicleCategory category;
	private String make;
	private String model;
	private String color;
	private String img;
	
	public Vehicle() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public VehicleCategory getCategory() {
		return category;
	}

	public void setCategory(VehicleCategory category) {
		this.category = category;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String getImg() {
		return this.img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}

	public boolean isValid() {
		return category == null || isEmpty(make) || isEmpty(model) || isEmpty(color) || isEmpty(img); 
	}
}
