package Fahad;

/**
 * Driver represents one driver in the Ride-Sharing System.
 * It extends Person and adds the vehicle plate and the vehicle type.
 * Drivers are compared by their ID.
 */
public class Driver extends Person implements IDriver {

	private String vehiclePlate;        // 3 uppercase letters then 4 digits, ex: ABC1234
	private VehicleType vehicleType;

	/** Creates a new instance of Driver (same order as drivers_30.csv) */
	public Driver(int id, String name, String phoneNumber, String vehiclePlate, VehicleType vehicleType) {
		super(id, name, phoneNumber);
		setVehiclePlate(vehiclePlate);  // use the setter so the plate is validated
		this.vehicleType = vehicleType;
	}

	public String getVehiclePlate() {
		return vehiclePlate;
	}

	// The plate must be exactly 7 characters: first 3 are uppercase letters (A-Z), last 4 are digits (0-9)
	public void setVehiclePlate(String vehiclePlate) {
		if (vehiclePlate == null || vehiclePlate.length() != 7)
			throw new IllegalArgumentException("Vehicle plate must be 3 uppercase letters followed by 4 digits: " + vehiclePlate);

		// check the 3 letters
		for (int i = 0; i < 3; i++) {
			char c = vehiclePlate.charAt(i);
			if (c < 'A' || c > 'Z')
				throw new IllegalArgumentException("Vehicle plate must start with 3 uppercase letters: " + vehiclePlate);
		}
		// check the 4 digits
		for (int i = 3; i < 7; i++) {
			char c = vehiclePlate.charAt(i);
			if (c < '0' || c > '9')
				throw new IllegalArgumentException("Vehicle plate must end with 4 digits: " + vehiclePlate);
		}
		this.vehiclePlate = vehiclePlate;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	// Compare by driver ID only: negative if this ID is smaller, 0 if equal, positive if larger
	public int compareTo(IDriver other) {
		if (getId() < other.getId())
			return -1;
		else if (getId() > other.getId())
			return 1;
		else
			return 0;
	}

	public String toString() {
		return "Driver [" + super.toString() + ", Plate: " + vehiclePlate + ", Vehicle Type: " + vehicleType + "]";
	}
}
