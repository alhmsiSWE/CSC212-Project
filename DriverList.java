package Fahad;

/**
 * DriverList is a singly linked list that stores the drivers.
 * The drivers are always kept sorted by driver ID (smallest ID at the head).
 */
public class DriverList implements IDriverList {

	private Node<IDriver> head;
	private int size;

	/** Creates a new empty DriverList */
	public DriverList() {
		head = null;
		size = 0;
	}

	// Insert the driver in its correct place (sorted by ID).
	// Returns false if a driver with the same ID or the same vehicle plate is already in the list.
	public boolean add(IDriver driver) {
		if (driver == null)
			return false;

		// vehicle plate must be unique for all drivers
		if (findByVehiclePlate(driver.getVehiclePlate()) != null)
			return false;

		Node<IDriver> tmp = new Node<IDriver>(driver);

		// Case 1: list is empty or the new driver has the smallest ID -> insert at the head
		if (head == null || driver.compareTo(head.data) < 0) {
			tmp.next = head;
			head = tmp;
			size++;
			return true;
		}

		// Case 2: move until p reaches the first driver with ID >= the new ID
		// q is always the node before p
		Node<IDriver> p = head;
		Node<IDriver> q = null;
		while (p != null && driver.compareTo(p.data) > 0) {
			q = p;
			p = p.next;
		}

		// same ID already exists -> insertion fails
		if (p != null && driver.compareTo(p.data) == 0)
			return false;

		// insert the new node between q and p
		tmp.next = p;
		q.next = tmp;
		size++;
		return true;
	}

	// Returns the driver with this ID, or null if not found
	public IDriver findById(int driverId) {
		Node<IDriver> p = head;
		while (p != null) {
			if (p.data.getId() == driverId)
				return p.data;
			p = p.next;
		}
		return null;
	}

	// Returns all drivers whose full name is exactly equal to fullName (empty list if none)
	public LinkedList<IDriver> findByName(String fullName) {
		LinkedList<IDriver> result = new LinkedList<IDriver>();
		Node<IDriver> p = head;
		while (p != null) {
			if (p.data.getName().equals(fullName))
				result.insert(p.data);
			p = p.next;
		}
		return result;
	}

	// Returns the driver with this vehicle plate, or null if not found
	public IDriver findByVehiclePlate(String vehiclePlate) {
		Node<IDriver> p = head;
		while (p != null) {
			if (p.data.getVehiclePlate().equals(vehiclePlate))
				return p.data;
			p = p.next;
		}
		return null;
	}

	// Returns all drivers with this vehicle type (empty list if none)
	public LinkedList<IDriver> findByVehicleType(VehicleType vehicleType) {
		LinkedList<IDriver> result = new LinkedList<IDriver>();
		Node<IDriver> p = head;
		while (p != null) {
			if (p.data.getVehicleType() == vehicleType)   // enum values are compared with ==
				result.insert(p.data);
			p = p.next;
		}
		return result;
	}

	// Returns all drivers in the same order as the list (sorted by ID)
	public LinkedList<IDriver> getAll() {
		LinkedList<IDriver> result = new LinkedList<IDriver>();
		Node<IDriver> p = head;
		while (p != null) {
			result.insert(p.data);
			p = p.next;
		}
		return result;
	}

	// ID is unique, so we stop after the first match
	public boolean removeById(int driverId) {
		Node<IDriver> p = head;
		Node<IDriver> q = null;     // node before p
		while (p != null) {
			if (p.data.getId() == driverId) {
				if (q == null)      // the driver is the first node
					head = head.next;
				else
					q.next = p.next;
				size--;
				return true;
			}
			q = p;
			p = p.next;
		}
		return false;
	}

	// Vehicle plate is unique, so we stop after the first match
	public boolean removeByVehiclePlate(String vehiclePlate) {
		Node<IDriver> p = head;
		Node<IDriver> q = null;     // node before p
		while (p != null) {
			if (p.data.getVehiclePlate().equals(vehiclePlate)) {
				if (q == null)      // the driver is the first node
					head = head.next;
				else
					q.next = p.next;
				size--;
				return true;
			}
			q = p;
			p = p.next;
		}
		return false;
	}

	// Name is not unique, so we go through the whole list and remove every match
	public int removeByName(String fullName) {
		int count = 0;
		Node<IDriver> p = head;
		Node<IDriver> q = null;     // node before p
		while (p != null) {
			if (p.data.getName().equals(fullName)) {
				if (q == null)      // removing the first node
					head = p.next;
				else
					q.next = p.next;
				size--;
				count++;
				p = p.next;         // q does not move because its next is now the new p
			}
			else {
				q = p;
				p = p.next;
			}
		}
		return count;
	}

	// Vehicle type is not unique, so we go through the whole list and remove every match
	public int removeByVehicleType(VehicleType vehicleType) {
		int count = 0;
		Node<IDriver> p = head;
		Node<IDriver> q = null;     // node before p
		while (p != null) {
			if (p.data.getVehicleType() == vehicleType) {
				if (q == null)      // removing the first node
					head = p.next;
				else
					q.next = p.next;
				size--;
				count++;
				p = p.next;         // q does not move because its next is now the new p
			}
			else {
				q = p;
				p = p.next;
			}
		}
		return count;
	}

	public int size() {
		return size;
	}
}
