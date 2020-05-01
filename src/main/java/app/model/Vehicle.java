package app.model;

import static app.utils.StringUtils.isEmpty;

import java.io.Serializable;

public class Vehicle implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private VehicleCategory category;
	private String make;
	private String model;
	private String color;
	private String base64Img;
	private double costPerDay;
	
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
	
	public String getBase64Img() {
		return this.base64Img;
	}
	
	public void setBase64Img(String base64Img) {
		this.base64Img = base64Img;
	}
	
	public double getCostPerDay() {
		return this.costPerDay;
	}
	
	public void setCostPerDay(double costPerDay) {
		this.costPerDay = costPerDay;
	}

	public boolean isValid() {
		return category != null && !isEmpty(make) && !isEmpty(model) && !isEmpty(color) && !isEmpty(base64Img) && costPerDay > 0; 
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", category=" + category + ", make=" + make + ", model=" + model + ", color="
				+ color + ", base64Img=" + base64Img + "]";
	}
}
