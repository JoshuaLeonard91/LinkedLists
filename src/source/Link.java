package source;

/**
 * <b>COP 3530: Project 3 – Stacks and Priority Queues with Linked Lists </b>
 * <p>
 * The link class is used to store data for the next and previous link as well
 * as the country data.<br>
 * 
 * 
 * <pre>
 * <b>data</b> = country data
 * <b>dr</b> = death rate (easier to program out other classes example link.data.getDeathRate vs link.dr)
 * <b>next</b> = next links reference/pointer
 * <b>previous</b> = previous links reference pointer (Only used in the priority)
 * <b>Link(country data)</b> = Country object for the data variable
 * <b>Link(Link link)</b> = Link country data used to recreate a link
 * </pre>
 * 
 * @author Joshua Leonard
 * @version 10/27/2022
 */
public class Link {
	public Country data;
	public double dr; // adds death rate directly to link for to easily debug and/or compare
	public Link next; // used in both stack and priority queue
	public Link previous; // used only in the priority queue

	/**
	 * This method takes in a country object and stores it into the data
	 * variable.<br>
	 * Sets next to null and previous to null.
	 * 
	 * @param data
	 */
	public Link(Country data) {
		this.data = data;
		this.dr = data.getDeathRate();
		next = null;
		previous = null;
	}

	/**
	 * This method is used to print out each links countries data that is formatted
	 * to work with the print data method in the project3 class file.
	 */
	public void displayLink() {
		System.out.println(data.getData());
	}
}
