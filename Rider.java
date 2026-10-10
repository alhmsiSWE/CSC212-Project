package Fahad;

/**
 * Rider represents one rider in the Ride-Sharing System.
 * It extends Person and adds the email and the home city.
 * Riders are compared by their ID.
 */
public class Rider extends Person implements IRider {

	private String email;
	private String homeCity;

	/** Creates a new instance of Rider (same order as riders_100.csv) */
	public Rider(int id, String name, String email, String phoneNumber, String homeCity) {
		super(id, name, phoneNumber);
		this.email = email;
		this.homeCity = homeCity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	// Compare by rider ID only: negative if this ID is smaller, 0 if equal, positive if larger
	public int compareTo(IRider other) {
		if (getId() < other.getId())
			return -1;
		else if (getId() > other.getId())
			return 1;
		else
			return 0;
	}

	public String toString() {
		return "Rider [" + super.toString() + ", Email: " + email + ", Home City: " + homeCity + "]";
	}
}
