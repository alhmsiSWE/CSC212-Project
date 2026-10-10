package Fahad;

/**
 * Person is the abstract parent class of Rider and Driver.
 * It holds the fields that both of them have: id, name, phone number
 * and the ride history. It is abstract so it can not be created directly.
 */
public abstract class Person implements IPerson {

	private final int id;               // unique id, given in the constructor only (no setter)
	private String name;                // full name
	private String phoneNumber;         // must be exactly 10 digits
	private LinkedList<IRide> rideHistory;  // rides of this person (as rider or as driver)

	/** Creates a new person (called from the Rider and Driver constructors) */
	public Person(int id, String name, String phoneNumber) {
		this.id = id;
		this.name = name;
		setPhoneNumber(phoneNumber);    // use the setter so the phone number is validated
		rideHistory = new LinkedList<IRide>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	// The phone number must be exactly 10 characters and all of them are digits (0-9)
	public void setPhoneNumber(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.length() != 10)
			throw new IllegalArgumentException("Phone number must be exactly 10 digits: " + phoneNumber);

		for (int i = 0; i < phoneNumber.length(); i++) {
			char c = phoneNumber.charAt(i);
			if (c < '0' || c > '9')
				throw new IllegalArgumentException("Phone number must contain digits only: " + phoneNumber);
		}
		this.phoneNumber = phoneNumber;
	}

	public LinkedList<IRide> getRideHistory() {
		return rideHistory;
	}

	// Common part of the description, Rider and Driver add their own fields to it
	public String toString() {
		return "ID: " + id + ", Name: " + name + ", Phone: " + phoneNumber;
	}
}
