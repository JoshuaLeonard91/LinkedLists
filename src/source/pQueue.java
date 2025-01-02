package source;

/**
 * <b>COP 3530: Project 23 – Stacks and Priority Queues with Linked Lists </b>
 * <p>
 * This class handles inserting, removing, and printing the priority queue.<br>
 * <b>Methods:<br>
 * </b>
 * 
 * <pre>
 * {@linkplain #insert(Link) insert}     : Inserting items lowest death rate = highest priority.
 * {@linkplain #remove() remove}     : Removes highest priority item (Lowest item) O(1).
 * {@linkplain #isEmpty() isEmpty}    : Checks if the queue is empty.
 * {@linkplain #printpQueue(Link) printQueue} : Prints the priority queue.
 * </pre>
 * 
 * @author Joshua Leonard
 * @version 10/27/2022
 */
public class pQueue {
	public Link first;
	public Link last;
	public int counter; // counter used to check if countries are deleted, checked in if statement
						// whether to return true or false
						// also used to let the user know how many countries were deleted from the
						// priority queue.

	/**
	 * <b>Required constructor for project</b>
	 * <p>
	 * Constructor for the priority queue.
	 * <p>
	 * Initializes the first and last variable to null.
	 */
	public pQueue() {
		this.first = null;
		this.last = null;
	}

	/**
	 * <b>Required method for project</b>
	 * <p>
	 * This method inserts a new country object into the appropriate position based
	 * on its priority.<br>
	 * <p>
	 * 
	 * <pre>
	 * This method uses 4 different insert methods.
	 * 
	 * {@linkplain #insertFirst(Link) insertFirst}  : if the death rate for the link is less than the first links death rate.
	 * {@linkplain #insertLast(Link) insertLast}   : if the death rate for the link is greater than the last links death rate. 
	 * {@linkplain #insertBefore(Link) insertBefore} : finds the first link that is greater than the parameter link and inserts before it.
	 * </pre>
	 * 
	 * <pre>
	 * <b>
	 * NOTE: leftover references from stack queue are cleared before inserting into the priority queue.
	 * link.next = null
	 * link.previous = null
	 * </b>
	 * </pre>
	 * 
	 * <PRE>
	 * 
	 * <B>O(N)</B>
	 * 
	 * </PRE>
	 * 
	 * @param Link Link object that has the country data
	 */
	public void insert(Link link) {
		link.next = null;
		link.previous = null;
		if (isEmpty()) {
			first = link;
			last = link;
			return;
		}
		if (link.dr < first.dr) {
			insertFirst(link);
			return;
		}
		if (link.dr > last.dr) {
			insertLast(link);
			return;
		}
		insertBefore(link);
	}

	/**
	 * Inserts the link to the left of the old first link, new first link is the
	 * Link that is passed through.
	 * 
	 * @param link
	 */
	public void insertFirst(Link link) {
		if (isEmpty()) {
			last = link;
		} else {
			first.previous = link;
		}
		link.next = first;
		first = link;
	}

	/**
	 * Inserts the link to the right of the old last link, new last link is the Link
	 * that is passed through.
	 * 
	 * @param link
	 */
	public void insertLast(Link link) {
		if (isEmpty()) {
			first = link;
		} else {
			last.next = link;
			link.previous = last;
		}
		last = link;
	}

	/**
	 * Inserts link to the left of the current link that has a death rate higher
	 * than the link that is passed through.
	 * 
	 * @param link
	 */
	public void insertBefore(Link link) {
		Link current = first;
		while (current.dr < link.dr) {
			current = current.next;
		}
		current.previous.next = link;
		link.previous = current.previous;
		current.previous = link;
		link.next = current;
	}

	/**
	 * <b>Required method for project</b>
	 * <p>
	 * This method is used to remove the first link in the priority queue.
	 * 
	 * <PRE>
	 * 
	 *<B>O(1)</B>
	 * 
	 * </PRE>
	 * 
	 * @return Link that was removed
	 */
	public Link remove() {
		Link temp = first;
		if (isEmpty()) {
			System.out.println("The priority queue is empty");
			return null;
		}
		if (first.next == null) {
			last = null;
		}
		first = first.next;
		return temp;
	}

	/**
	 * This method takes in two integer values from the project 3 class file that
	 * comes from the user in the form of [x,y], x has to be lower than y. The
	 * method will delete death rates that are x>= && < y. The method calls on the
	 * traversal method and passes the x an y variables.
	 * 
	 * <pre>
	 * {@linkplain #Traversal(int, int) Traversal}
	 * {@linkplain #deletion(int, int, Link) Deletion}
	 * </pre>
	 * 
	 * @param x the first value
	 * @param y the second value
	 * @return true or false (if deleted or not)
	 */
	public Boolean intervalDelete(int x, int y) {
		if (isEmpty()) {
			return false;
		} else {
			Traversal(x, y);
			if (counter > 0) {
				return true;
			}
			return false;
		}
	}

	/**
	 * This method traverses through the Linked lists, before it moves to the next
	 * link it will call on the deletion method.
	 * 
	 * @param x
	 * @param y
	 */
	public void Traversal(int x, int y) {
		Link current = first;
		while (current != null) {
			current = deletion(x, y, current);
		}
	}

	/**
	 * This method will either delete the current link being looked at, and change
	 * the links references around if the current links death rates are within range
	 * and return the current.next. Otherwise it will hit the else condition and
	 * return the current.next only.
	 * 
	 * @param x
	 * @param y
	 * @param current link that needs to be looked at
	 * @return link link to be returned to the traversal method.
	 */
	public Link deletion(int x, int y, Link current) {
		Link temp;
		if (current.dr >= x && current.dr <= y) {
			counter++;
			temp = current.next;
			if (first == current) {
				first = current.next;
			}
			if (current.next != null) {
				current.next.previous = current.previous;
			}
			if (current.previous != null) {
				current.previous.next = current.next;
			}
			current = temp;
		} else {
			current = current.next;
		}
		return current;
	}

	/**
	 * <b>Required method for project</b>
	 * <p>
	 * This method will print the priority queue from the highest priority to the
	 * lowest.
	 */
	public Link printpQueue(Link first) {
		Link current = first;
		if (current == null) {// base case
			return null;
		} else { // recursive case
			current.displayLink();
		}
		return printpQueue(current.next);
	}

	/**
	 * <b>Required method for project</b>
	 * <p>
	 * Checks if the priority queue is empty.
	 * 
	 * @return true or false
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * @return the first link
	 */
	public Link getFirst() {
		return first;
	}

	/**
	 * @param first the first to set
	 */
	public void setFirst(Link first) {
		this.first = first;
	}

	/**
	 * @return the last link
	 */
	public Link getLast() {
		return last;
	}

	/**
	 * @param last the last to set
	 */
	public void setLast(Link last) {
		this.last = last;
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}
}
