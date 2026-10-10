package Fahad;

/**
 * RiderList is a singly linked list that stores the riders.
 * The riders are always kept sorted by rider ID (smallest ID at the head).
 */
public class RiderList implements IRiderList {

	private Node<IRider> head;
	private int size;

	/** Creates a new empty RiderList */
	public RiderList() {
		head = null;
		size = 0;
	}

	// Insert the rider in its correct place (sorted by ID).
	// Returns false if a rider with the same ID is already in the list.
	public boolean add(IRider rider) {
		if (rider == null)
			return false;

		Node<IRider> tmp = new Node<IRider>(rider);

		// Case 1: list is empty or the new rider has the smallest ID -> insert at the head
		if (head == null || rider.compareTo(head.data) < 0) {
			tmp.next = head;
			head = tmp;
			size++;
			return true;
		}

		// Case 2: move until p reaches the first rider with ID >= the new ID
		// q is always the node before p
		Node<IRider> p = head;
		Node<IRider> q = null;
		while (p != null && rider.compareTo(p.data) > 0) {
			q = p;
			p = p.next;
		}

		// same ID already exists -> insertion fails
		if (p != null && rider.compareTo(p.data) == 0)
			return false;

		// insert the new node between q and p
		tmp.next = p;
		q.next = tmp;
		size++;
		return true;
	}

	// Returns the rider with this ID, or null if not found
	public IRider findById(int riderId) {
		Node<IRider> p = head;
		while (p != null) {
			if (p.data.getId() == riderId)
				return p.data;
			p = p.next;
		}
		return null;
	}

	// Returns all riders whose full name is exactly equal to fullName (empty list if none)
	public LinkedList<IRider> findByName(String fullName) {
		LinkedList<IRider> result = new LinkedList<IRider>();
		Node<IRider> p = head;
		while (p != null) {
			if (p.data.getName().equals(fullName))
				result.insert(p.data);
			p = p.next;
		}
		return result;
	}

	// Returns the rider with this email, or null if not found
	public IRider findByEmail(String email) {
		Node<IRider> p = head;
		while (p != null) {
			if (p.data.getEmail().equals(email))
				return p.data;
			p = p.next;
		}
		return null;
	}

	// Returns all riders from this home city (empty list if none)
	public LinkedList<IRider> findByHomeCity(String homeCity) {
		LinkedList<IRider> result = new LinkedList<IRider>();
		Node<IRider> p = head;
		while (p != null) {
			if (p.data.getHomeCity().equals(homeCity))
				result.insert(p.data);
			p = p.next;
		}
		return result;
	}

	// Returns all riders in the same order as the list (sorted by ID)
	public LinkedList<IRider> getAll() {
		LinkedList<IRider> result = new LinkedList<IRider>();
		Node<IRider> p = head;
		while (p != null) {
			result.insert(p.data);
			p = p.next;
		}
		return result;
	}

	// ID is unique, so we stop after the first match
	public boolean removeById(int riderId) {
		Node<IRider> p = head;
		Node<IRider> q = null;      // node before p
		while (p != null) {
			if (p.data.getId() == riderId) {
				if (q == null)      // the rider is the first node
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

	// Email is unique, so we stop after the first match
	public boolean removeByEmail(String email) {
		Node<IRider> p = head;
		Node<IRider> q = null;      // node before p
		while (p != null) {
			if (p.data.getEmail().equals(email)) {
				if (q == null)      // the rider is the first node
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
		Node<IRider> p = head;
		Node<IRider> q = null;      // node before p
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

	// Home city is not unique, so we go through the whole list and remove every match
	public int removeByHomeCity(String homeCity) {
		int count = 0;
		Node<IRider> p = head;
		Node<IRider> q = null;      // node before p
		while (p != null) {
			if (p.data.getHomeCity().equals(homeCity)) {
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

