package assignments;
import java.util.*;

import javax.swing.JOptionPane;
/**
 * 
 * @author Tessa Nesci
 * @version 1.0
 * 
 * This class allows users to store, search and manipulate a 
 * list of birds in an array as per the requirements of
 * the client
 */
public class BirdArrayList 
{
	/**
	 * Main method: creates and populates the birdList array
	 * and creates a simple menu to help the user 
	 * the program based on their choices
	 * 
	 */
	public static void main(String[] args) 
	{
		char response;
		ArrayList<String> birdList = new ArrayList<String>(); //create new ArrayList called birdList
		
		//populate array birdList with 5 default entries
		birdList.add("Mallard");
		birdList.add("Kookaburrah");
		birdList.add("Australian King Parrot");
		birdList.add("Magpie");
		birdList.add("Black Crested Cockatoo");
		
		//menu loop
		do
		{
			//create and display a simple menu
			// get user input to navigate program
			String answer = JOptionPane.showInputDialog(null, "MENU \n" +
					"A to add a bird \n" +
					"R to remove a bird \n" + 
					"S to search for a a bird \n" + 
					"D to display all elements in the array \n" + 
					"Q to quit this program"); 
			
			answer = answer.toUpperCase(); 	//makes all menu entries uppercase, so user input is not case sensitive
			response = answer.charAt(0); 	// assign variable response to the first letter of user input variable answer
			
			//test user choice against menu option and call appropriate methods
			if(response == 'A') //add a bird
				addToArray(birdList);
			else if(response == 'R')		//remove a bird
				deleteEntry(birdList);
			else if(response == 'S')		//search for a bird
				searchArray(birdList);
			else if (response == 'D')		//display array elements
				displayArray(birdList);
			else if(response != 'Q')		//user input error
				JOptionPane.showMessageDialog(null, "entry was not valid \nPlease enter a valid menu option"); //produces error message if user inputs a non-menu character
		} while(response != 'Q');
	}
	/**
	 * 
	 * adds a user entry to the array
	 * @param birdList: Array location value
	 * 
	 */
	public static void addToArray(ArrayList<String> birdList)
	{
		String answer = JOptionPane.showInputDialog(null,"Enter name of the bird to add to the array");  //get user input to add to array
		birdList.add(answer);				//add user input to array
		displayArray(birdList);				//call to method to display array elements
	}
	/**
	 * Deletes an entry as defined by the user from the array
	 * uses a sequential search comparing the user input
	 * to every element in the array, the search is case sensitive
	 * 
	 * @param birdList: Array location value
	 */
	public static void deleteEntry(ArrayList<String> birdList)
	{
		boolean found = false;
		//get user input for search, search is case sensitive
		String answer = JOptionPane.showInputDialog(null,"Enter the name of the bird tp be deleted from the array");
		//compare user input with the value of each location within the array
		for(int i=0; i<birdList.size(); i++)	
		{
			if(answer.equals(birdList.get(i)))	//if exact match is found..
			{
				//user to confirm search result is correct before deleting array element
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " 
						+ birdList.get(i) + " from the array?", "Confirm Delete",JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION)	//if user confirms THEN...
				{
					found = true;				//search term was found within the array
					birdList.remove(i);			//remove selected array element
				}
				else
					found = true;
			}
		}
		if(found == false)						//if search did not produce a match with the array...
			JOptionPane.showMessageDialog(null, "Error: Bird not found");	//display error message
		displayArray(birdList);					//call to method to display array elements
	}
	/**
	 * uses a binary search to efficiently find a match between
	 * user input and an array element, the search is case sensitive
	 * 
	 * @param birdList: Array location value
	 */
	public static void searchArray(ArrayList<String> birdList)
	{
		int high, mid, low;
		int returnValue = -1;					//index number of matching array element
		boolean found = false;
		//get user input for search, search is case sensitive
		String searchValue = JOptionPane.showInputDialog(null,"Enter the name of the bird you want to search for");
		Collections.sort(birdList);				//sorts the array elements in alphabetical order
		low = 0;								//lowest element in the array search range
		high = birdList.size();					//highest element in the array search range
		
		//search loop
		while(!found)
		{
			mid = (high + low)/2;				//finds mid point of search range
			if(searchValue.compareTo(birdList.get(mid)) == 0)	//if match is found THEN...
			{
				returnValue = mid;				
				found = true;					//end loop
			}
			else if((high - low)< 2)			//if high and mid values are consecutive no match can be found THEN...
			{
				returnValue = -1;				//no match found error
				found = true;					//end loop
			}
			else if(searchValue.compareTo(birdList.get(mid)) > 0)	//if user input value is higher than mid value THEN...
				low = mid;						//adjust range by eliminating lower half
			else
				high = mid;						//adjust range by eliminating higher half
		}
		if(returnValue == -1)					//if "no match found" error THEN
			JOptionPane.showMessageDialog(null,"bird not found.");	//display error message
		else
			JOptionPane.showMessageDialog(null, birdList.get(returnValue) + " is found at index " + returnValue);	//display found message		
	}
	/**
	 * displays all elements in the array on the console
	 * @param birdList: Array location value 
	 */
	private static void displayArray(ArrayList<String> birdList)
	{
		//Displays the value at each location within the array on a separate line
		for(int i=0; i<birdList.size(); i++)
			System.out.println(birdList.get(i));
	}
}
