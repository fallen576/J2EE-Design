package app.model;

public class Vehicle {
	
	private long id;
	private VehicleCategory type;
	private String make;
	private String model;
	private String color;
	
	public Vehicle() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public VehicleCategory getType() {
		return type;
	}

	public void setType(VehicleCategory type) {
		this.type = type;
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

}
