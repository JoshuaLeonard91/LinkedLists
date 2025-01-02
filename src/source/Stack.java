package source;

/**
 * <b>COP 3530: Project 3 – Stacks and Priority Queues with Linked Lists</b>
 * <p>
 * This class handles pushing, popping, and printing the stack.<br>
 * <p>
 * 
 * <pre>
 * {@linkplain #push(Country) push}      : country object
 * {@linkplain #pop() pop}       : country object
 * {@linkplain #isEmpty() isEmpty}   : checks if the stack is empty
 * {@linkplain #printStack(Link) printStack}: prints out the entire stack recursively
 * </pre>
 * 
 * @author Joshua Leonard
 * @version 10/27/2022
 */
public class Stack {
	private Link first;
	private Link last;

	/**
	 * <b>Required method for project</b>
	 * <p>
	 * Constructor for the stack.
	 * <p>
	 * 
	 * <pre>
	 * Initializes 
	 * top = null  
	 * last = null
	 * </pre>
	 */
	public Stack() {
		first = null;
		last = null;
	}

	/**
	 * <b>Required method for project</b>
	 * <p>
	 * This method pushes a country object to the stack.
	 * <p>
	 * It creates a new Link with the country data and then pushes it to the stack.
	 * 
	 * O(1) insert
	 * 
	 * @param item Country object created by the user
	 */
	public void push(Country data) {
		Link newLink = new Link(data);
		if (isEmpty()) {
			last = newLink;
		}
		newLink.next = first;
		first = newLink;
	}

	/**
	 * <b>Required method for project</b>
	 * <p>
	 * This method will pop the stack. If the stack is empty it will return
	 * null.<br>
	 * Otherwise it will return the link. Top will become top.next (reference to the
	 * next link).
	 * 
	 * O(1) removal
	 * 
	 * @return The popped object (removed object)
	 */
	public Link pop() {
		if (isEmpty()) {
			return null;
		}
		Link temp = first;
		first = first.next;
		return temp;
	}

	/**
	 * <b>Required method for project</b>
	 * <p>
	 * This method will print the stack from top to last. Top will be the first link
	 * and last will be the last link. O(1)
	 */
	public Link printStack(Link top) {
		Link current = top;
		if (current == null) {// base case
			return null;
		} else { // recursive case
			current.displayLink();
		}
		return printStack(current.next);
	}

	/**
	 * <b>Required method for project</b>
	 * <p>
	 * Checks if the stack is empty.
	 * 
	 * @return true or false
	 */
	public boolean isEmpty() {
		return (first == null);
	}

	/**
	 * @return the top
	 */
	public Link getTop() {
		return first;
	}

	/**
	 * @param top the top link (first)
	 */
	public void setTop(Link top) {
		this.first = top;
	}

	/**
	 * @return last the last link
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
}
