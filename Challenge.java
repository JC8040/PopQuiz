///////////////////// Challenge /////////////////////
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JList;
/**
 * Challenge class represents a new challenge between the current
 * user and the friend that they have selected.
 * @author Group 2
 */
public class Challenge {
	
	
	////////// ATTRIBUTES //////////
	/**
	 * The current user
	 */
	private User user;
	
	/**
	 * The current user's score 
	 */
	private int score;
	
	/**
	 * The friend the user has selected to challenge
	 */
	private User friend;
	
	/**
	 * The score of the friend 
	 */
	private int friendScore;
	
	/**
	 * The category that has been selected
	 */
	private Category category;
	
	/**
	 * The time limit for accepting challenges
	 */
	private int challengeTimer;
	
	/**
	 * The due date of the challenge
	 */
	private String dueDate;
	
	/**
	 * Path of the server files
	 */
	private String path;
	
	/**
	 * URL
	 */
	private URL url;
	
	/**
	 * URL connection
	 */
    private URLConnection connection ;
    
    /**
     * Boolean indicating whether or not this challenge was accepted
     */
    private boolean isAccepted;
	
    
	////////// CONSTRUCTORS //////////
    /**
     * Default constructor initializes Challenge
     * @param serverPath				The path of the server
     * @param currentUser			The current user
     * @param selectedFriend		The friend they have selected to challenge
     * @param selectedCategory		The selected category
     */
    public Challenge(String serverPath, User currentUser, User selectedFriend, Category selectedCategory) {
    	path = serverPath;
    	user = currentUser;
		setScore(0);
		friend = selectedFriend;
		setFriendScore(0);
		category = selectedCategory;
		loadChallengeTimer();
		loadDueDate();
		isAccepted = false;
    }
	
    /**
     * Additional constructor initializes an accepted Challenge with a friend's score
     * @param serverPath			The path of the server
     * @param currentUser			The current user
     * @param selectedFriend		The friend that created the challenge
     * @param fscore				The friend's score
     * @param selectedCategory		The selected category
     */
    public Challenge(String serverPath, User currentUser, User selectedFriend, int fScore, Category selectedCategory) {
    	path = serverPath;
    	user = currentUser;
		setScore(0);
		friend = selectedFriend;
		setFriendScore(fScore);
		category = selectedCategory;
		loadChallengeTimer();
		loadDueDate();
		// This challenge is accepted from friend
		isAccepted = true;
    }

	
	////////// METHODS //////////
    /**
     * Gets the current server path
     */
    public String getPath() {
    	return path;
    }
    
	/**
	 * Gets the current user
	 * @return  user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Gets the user's score
	 * @return  score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the user's score
	 * @param score
	 */
	public void setScore(int userScore) {
		score = userScore;
	}

	/**
	 * Gets the user they have selected to challenge
	 * @return friend
	 */
	public User getFriend() {
		return  friend;
	}

	/**
	 * Gets friend's score
	 * @return freindScore
	 */
	public int getFriendScore() {
		return friendScore;
	}

	/**
	 * Sets the friend's score
	 * @param friendScore
	 */
	public void setFriendScore(int friendScore) {
		this.friendScore = friendScore;
	}
	
	/**
	 * Gets the selected category
	 * @return	category
	 */
	public Category getCategory() {
		return category;
	}
	
	/**
	 * Formats the category information to be saved in the friend's 
	 * pending challenges file.
	 * @return  A String containing all the required challenge information
	 */
	public String formatChallenge() {
		String output = "friendID=" + friend.getID();
		output = output + "&" + "dueDate=" + dueDate;
		output = output + "&" + "category=" + category;
		output = output + "&" + "userID=" + user.getID();
		output = output + "&" + "userName=" + user.toString();
		output = output + "&" + "score=" + score;
		return output;
	}
	
	/**
	 * Writes the challenge into the pending challenge file.
	 */
	 public void writePendingToFile() {
		// Try to open a new connection and write challenge to file
	    try {
	    	openConnection("writePending.php");
	    	OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
	        writer.write(formatChallenge());
	        writer.flush();
	        writer.close();
	        connection.getInputStream();
	    } 
	    catch (IOException ioe) {
		    ioe.printStackTrace();
	    }
	}
	
	/**
	 * Challenge info to be displayed in pending challenge list
	 */
	public String toString() {
		return dueDate + "," + friend.toString() + "," + friendScore;
	}
	
	/**
	 * Gets a boolean indicating whether or not this challenge was accepted
	 * @return
	 */
	public boolean isAccepted() {
		return isAccepted;
	}
	
	
	//////////PRIVATE HELPER METHODS //////////
	/**
	 * Opens up a URL connection to the server
	 * @param fileName The file name of the desired connection
	 */
	private void openConnection (String fileName) {
		try{
			// Try to open a new connection to the URL
			url = new URL (path + fileName);
			connection = url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
		}
		catch (IOException ioe) {
			System.out.println("Error: History file cannot be read");
		}
	}
	
	/**
	 * Loads the Challenge Timer stored on the server
	 */
	private void loadChallengeTimer() {
		try {
			// Create a URL connection to the password file
			openConnection("timer.txt");

			// Read in the saved password
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			challengeTimer = Integer.parseInt(in.readLine());
			in.close();
		}
		catch(MalformedURLException e) {
			System.out.println("Error: Invalid URL");
		}
		catch (IOException ioe) {
			System.out.println("Error: timer file cannot be read");
		}
	}
	
	/**
	 * Loads the due date, which is the current date plus the number of 
	 * days set in the challenge timer.
	 */
	private void loadDueDate() {
		// Get current date and calendar
		Date date; 
		Calendar cal = Calendar.getInstance();  
		date = cal.getTime();
		// Add challenge timer to current date
		cal.add(Calendar.DATE, challengeTimer);
		date = cal.getTime();
		// Format due date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		dueDate = dateFormat.format(date);
	}

} //////////////////// End of Challenge ////////////////////