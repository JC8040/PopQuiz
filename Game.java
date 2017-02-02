//////////////////// Game ////////////////////
import java.io.*;
import java.net.*;
/**
 * Game represents the game being played between two users, and contains 
 * all the components displayed on the game screen
 * @author Group2
 */
public class Game {
	
	
	////////// ATTRIBUTES //////////
	
	/**
	 * The chosen category
	 */
	private Category chosenCategory;
	
	/**
	 * An array of five questions 
	 */
	private Question[] questions ;
	
	/**
	 * An array of five indexes for the five questions we have.
	 */
	private int[] questionsIndex;
	
	/**
	 * The current user
	 */
	private User currentUser;
	
	/**
	 * The current user's score
	 */
	private int userScore;
	
	/**
	 * The current user's score on each question
	 */
	private int[] userScores;
	
	/**
	 * The current user's time on each question
	 */
	private int[] userTimes;
	
	/**
	 * The friend playing against the user
	 */
	private User friend;
	
	/**
	 * The friend's score
	 */
	private int friendScore;
	
	/**
	 * The friend's score on each question
	 */
	private int[] friendScores;
	
	/**
	 * The friend's time on each question
	 */
	private int[] friendTimes;
	
	/**
	 * How many days until the due date 
	 */
	private int challengeTimer;
	
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
	
	
	////////// CONSTRUCTORS //////////
	/**
	  * Default constructor initializes a new Game
	  * @param newChallenge		The new challenge that was just created by the User
	  */
	public Game(Challenge newChallenge) {
		path = newChallenge.getPath();
		currentUser = newChallenge.getUser();
		friend = newChallenge.getFriend();
		friendScore = newChallenge.getFriendScore();
		chosenCategory = newChallenge.getCategory();
		questions = chosenCategory.getFiveRandomQuestions();
	}
	
	/**
	 * Additional constructor initializes Game with input users, and selected category
	 * @param currentUser			The current user
	 * @param selectedFriend		The friend they have selected
	 * @param selectedCategory		The category to be played
	 */
	public Game(User currentUser, User selectedFriend, Category selectedCategory) {
		this.currentUser = currentUser;
		this.friend = selectedFriend;
		this.chosenCategory = selectedCategory;
		this.questions = chosenCategory.getFiveRandomQuestions();
		this.questionsIndex= chosenCategory.getIndexOfFiveRandomQuestions();
		
		//Some empty arrays need to be initialized.
		this.userScores = new int[5];
		this.userTimes = new int[5];
		this.friendScores = new int[5];
		this.friendTimes = new int[5];
		this.friendScore = 0;
		this.questionsIndex = new int[5];
	}
	
	
	////////// METHODS //////////
	/**
	 * Gets the player's first name
	 * @return The current user's first name
	 */
	public String getPlayerName() {
		return currentUser.getFirstName();
	}
	
	/**
	 * Gets the opponent's first name
	 * @return The friend's first name
	 */
	public String getOpponentName() {
		return friend.getFirstName();
	}
	
	/**
	 * Gets the name of the category being played
	 * @return	The name of the category
	 */
	public String getCategoryName() {
		return chosenCategory.getCategoryName();
	}
	
	/**
	 * Sets the array of scores
	 * @param scores	The scores to be set
	 */
	public void setScores (int[] scores) {
		userScores = scores;
	}
	
	/**
	 * Gets the player's score
	 * @return The current user's score
	 */
	public int getPlayerScore() {
		return userScore;
	}
	
	/**
	 * Sets the player's score
	 * @param score		The user's score to be set
	 */
	public void setPlayerScore(int score) {
		userScore = score;
	}
	
	/**
	 * Gets the opponent's score
	 * @return The friend's score
	 */
	public int getOpponentScore() {
		return friendScore;
	}
	
	/**
	 * Sets the opponent's score
	 * @param score		The friend's score to be set
	 */
	public void setOpponentScore(int score) {
		friendScore = score;
	}
	
	/** 
	 * Gets the five random questions selected from the category
	 * @return Five questions from the category
	 */
	public Question[] getQuestions() {
		return questions;
	}
	
	/**
	 * Function sets the 5 questions given an array of indicies
	 * @param indicies - array of indicies to the questions to be used.
	 */
	public void setQuestions(int[] indicies) {
		
		for (int i = 0; i <5 ; i++) {
			questions[i] = chosenCategory.getQuestions()[indicies[i]];
		}
	}
	
	/**
	 * If the friend has already played, set the friend scores.
	 * @param friendScores
	 */
	public void setFriendScores(int[] friendScores) {
		this.friendScores = friendScores;
	}
	
	/**
	 * Method that writes the game into the history file
	 */
	 public void writeHistoryToFile() {
		// Try to open a new connection and write history to file
		try {
	    	openConnection("writeHistory.php");
	        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
	        writer.write(formatHistory());
	        writer.flush();
	        writer.close();
	        connection.getInputStream();
	    } 
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
	 }
	 
	 
	 //////////PRIVATE HELPER METHODS //////////
	 /**
	  * Formats the category information to be saved in the friend's 
	  * pending challenges file.
	  * @return  A String containing all the required challenge information
	  */
	 private String formatHistory() {
			String output = "friendID=" + friend.getID();
			output = output + "&" + "info=" + currentUser.toString() + ": " + userScore;
			output = output + ", " + friend.toString() + ": " + friendScore;
			return output;
	 }
	 
	 /**
	  * Opens up a URL connection to the server
	  * @param fileName The file name of the desired connection
	  */
	 private void openConnection (String fileName) {
		try{
			url = new URL (path + fileName);
			connection = url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
		}
		catch (IOException ioe) {
			System.out.println("Error: History file cannot be read");
		}
	 }
	 
} //////////////////// End of Game ////////////////////