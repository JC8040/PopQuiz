//////////////////// User ////////////////////
/**
 * User holds the basic info collected from a Facebook user, 
 * plus the user's game and challenge history, and their skin choice.
 * Implements Comparable ADT for alphabetical ordering.
 * @author Group 2
 */
public class User implements Comparable<User>{
	

	////////// ATTRIBUTES //////////
	/**
	 * The user ID number
	 */
	private String ID;
	
	/**
	 * The users full name
	 */
	private String name;
	
	
	////////// CONSTRUCTOR //////////
	/**
	 * Default constructor initializes all of the User's attributes.
	 * @param userID			user ID number
	 * @param fullName			user's full name
	 */
	public User (String userID, String fullName) {
		ID = userID;
		name = fullName;
	}
	
	
	////////// METHODS //////////
	/**
	 * Gets the user ID number.
	 * @return		user ID number
	 */
	public String getID() {
		
		return ID;
	}
	
	/**
	 * Gets a user's first name
	 * @return 		first name
	 */
	public String getFirstName() {
		int split = name.indexOf(" ");
		return name.substring(0, split);
	}

	/**
	 * Returns the name of the User.
	 * @return		full name
	 */
	public String toString() {
		
		return name;
	}
	
	/**
	 * Compares two User's by name.
	 * @return		an integer representing a lexicographic comparison
	 */
    public int compareTo(User other){
        return name.compareTo(other.toString());
    }
	
} //////////////////// End of Friend ////////////////////