package source;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * <b>COP 3530: Project 3 – Stacks and Priority Queues with Linked Lists </b>
 * <p>
 * This class handles anything related to files.<br>
 * <br>
 * Methods:<br>
 * <br>
 * {@linkplain #readCountry(String) readCountry}: Reads file contents to get
 * country data.<br>
 * {@linkplain #classify(Country) classify}: returns true or false depending on
 * the death rate range<br>
 * 
 * @author Joshua Leonard
 * @version @version 10/18/2022
 */

public class FileHandler {
	/**
	 * Scans through a csv file and delimits with comma, or a new line character
	 * \n.<br>
	 * The first line in the csv file is scanned first as it normally contains a
	 * header. This method will scan each comma separated string and store it
	 * directly into a object that will then be stored into into the stack list
	 * only.<br>
	 * 
	 * @param file file named to be scanned
	 * @throws IOException this will be caught in main method
	 */
	public void readCountry(String file) throws IOException {

		Scanner scanFile = null;
		scanFile = new Scanner(new File(file));
		Country newCountry;
		scanFile.useDelimiter(",|\n");
		scanFile.nextLine();
		int i = 0;
		// scans the file and adds each delimited text to the appropriate category
		while (scanFile.hasNext()) {
			newCountry = new Country(scanFile.next(), scanFile.next(), Double.parseDouble(scanFile.next()),
					Double.parseDouble(scanFile.next()), Double.parseDouble(scanFile.next()),
					Double.parseDouble(scanFile.next()), Double.parseDouble(scanFile.next()));
			if (classify(newCountry) == true) {
				project3.cStack.push(newCountry);
			}
			i++;
		} // end while
		scanFile.close();
	}// end readCountry

	/**
	 * This method takes in a country object and compares if the death rate is not
	 * lower than 20 and less than 350.
	 * 
	 * <pre>
	 * VGOOD =  >= 20  AND < 100
	 * GOOD  =  >= 100 AND < 200
	 * FAIR  =  >= 200 AND < 350
	 * </pre>
	 * 
	 * @param newCountry
	 * @return true or false if country is within these ranges
	 */
	public Boolean classify(Country newCountry) {
		return (newCountry.getDeathRate() >= 20 && newCountry.getDeathRate() < 350);
	}
}
