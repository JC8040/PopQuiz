//////////////////// Category ////////////////////
import java.util.*;
/**
 * Category represents a category of questions
 * @author Group 2
 */
public class Category implements Comparable<Category> {


	////////// ATTRIBUTES //////////
	/**
	 * The Category name
	 */
	private String categoryName;

	/**
	 * The Category difficulty
	 */
	private String difficultyLevel;

	/**
	 * An array of Questions belonging to the Category
	 */
	private Question [] questions;

	/**
	 * The number of Questions in the Category
	 */
	private int questionCount;

	/**
	 * Constant representing five questions per game
	 */
	private final int FIVE = 5;

	/**
	 * Constant representing an arbitrary maximum of 100 Questions
	 */
	private final int MAX = 100;
	
	private int[] indexOfRandomQuestions;


	////////// CONSTRUCTOR //////////
	/**
	 * Constructor
	 * @param categoryName The name of the category
	 * @param level The difficulty level of the category
	 */
	public Category(String name, String level) {
		categoryName = name;
		difficultyLevel = level;
		questions = new Question [MAX];
		questionCount = 0;
		indexOfRandomQuestions = new int[FIVE];
	}


	////////// METHODS //////////
	/**
	 * Get the Category name
	 * @return categoryName
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * Set the Category difficulty level
	 * @param level The difficulty level
	 */
	public void setLevel(String level) {
		difficultyLevel = level;
	}

	/**
	 * Get the Category difficulty level
	 * @return difficultyLevel
	 */
	public String getLevel() {
		return difficultyLevel;
	}

	/**
	 * addQuestion adds a new Question to the Category
	 * @param newQuestion The new Question
	 */
	public void addQuestion(Question newQuestion) {
		// Add new Question and increment count by 1
		questions[questionCount] = newQuestion;
		questionCount++;
	}

	/**
	 * deleteQuestion removes a Question from the Category
	 * @param question The Question to be deleted
	 */
	public void deleteQuestion(Question question) {

		for (int i = 0; i < questionCount; i++) {

			if (question.equals(questions[i])) {
				reSort(i);
				questionCount--;
			}
		}
	}

	/**
	 * Get the number of Questions in the current Category
	 * @return an integer representing the number of questions in this category
	 */
	public int numQuestions() {
		return questionCount;
	}

	/**
	 * Get all of the Questions in the current Category
	 * @return an array containing of all the Questions
	 */
	public Question [] getQuestions() {
		return Arrays.copyOfRange(questions, 0, questionCount);
	}

	/**
	 * Get the Question with the matching description
	 * @param questionDescription The Question description
	 * @return The Question if it is found, null otherwise
	 */
	public Question getQuestion(String questionDescription)	{
		// Search through Question array
		for (int i = 0; i < questionCount; i++) {

			if (questionDescription.equals(questions[i].toString()))
				return questions[i];
		}
		// If not found, return null
		return null;
	}

	/**
	 * A method that gets 5 questions from the category for the user to answer them.
	 * @return An array of 5 questions.
	 */
	public Question[] getFiveRandomQuestions() {
		// Create temporary arrays to store questions
		Question [] fiveQuestions = new Question[FIVE];
		Question [] alreadyChosen = new Question [FIVE];
		int count = 0;

		// Loop until 5 Questions are randomly chosen
		while (count < FIVE) {

			// Pick random number
			int random = getRandom(0, questionCount);

			// Check if the Question is already chosen
			if (!alreadyChosen(questions[random], alreadyChosen, count)) {
				// If not, then chose Question and increment count by 1
				fiveQuestions[count] = questions[random];
				alreadyChosen[count] = questions[random];
				this.indexOfRandomQuestions[count]=random;
				count++;
			}
			// Else repeat loop
		}
		return fiveQuestions;
	}
	
	/**
	 * Method that returns the indexes of the five random questions that we need in the game class. 
	 * @return
	 */
	public int[] getIndexOfFiveRandomQuestions() {
		return this.indexOfRandomQuestions;
	}

	/**
	 * listQuestion return a list of all the Questions descriptions in the Category
	 * @return a string array containing all of the Questions
	 */
	public String[] listQuestions() {
		// Temporary array
		String [] questionList;

		// If Category is empty, return an empty array
		if (isEmpty())
			return new String[0];
		else {
			// Else loop through all the questions and add them to the array
			questionList = new String[questionCount+1];

			for (int i = 0; i < questionCount; i++) {
				questionList[i] = questions[i].getDescription();
			}
			// Return array of all the questions
			return questionList;
		}
	}

	/**
	 * A method that saves the name of the category and all it's questions in a vector
	 * to get it ready to save it into a file
	 * @return vector of strings
	 */
	public Vector<String> toBulkLoad() {
		// Create a new vector to store category info
		Vector<String> toBulkLoad = new Vector<String>();
		// Add category name and difficulty
		toBulkLoad.add("@CAT: " + categoryName+"^"+ difficultyLevel);

		// Loop through all the questions in the category
		for(int i = 0; i < questionCount; i++) {
			// If question is multiple choice
			if (questions[i].getType().equals("MultipleChoice")) {
				MultipleChoiceQuestion question = (MultipleChoiceQuestion) questions[i];
				toBulkLoad.addAll(question.toBulkLoad());
			}
			// Else if question is voice question
			else if(questions[i].getType().equals("VoiceQuestion")) {
				VoiceQuestion question = (VoiceQuestion) questions[i];
				toBulkLoad.addAll(question.toBulkLoad());
			}
			// Else if question is short answer
			else if(questions[i].getType().equals("ShortAnswer") ) {
				Question question = questions[i];
				toBulkLoad.addAll(question.toBulkLoad());
			}
			// Else invalid question type
			else
				System.out.println("Error: Unknown Question type");
		}
		// Return vector containing category info
		return toBulkLoad;
	}

	/**
	 * isEmpty returns a boolean indicating whether or not a Category is empty
	 * @return true if Category is empty, false otherwise
	 */
	public boolean isEmpty() {
		return questionCount == 0;
	}

	/**
	 * toString returns the description for visibility in a JList
	 * @return description
	 */
	public String toString() {
		return categoryName;
	}

	/**
	 * Compares two Categories by name (for alphabetical sort)
	 * @return an integer representing a lexicographic comparison
	 */
	public int compareTo(Category other) {
		return categoryName.compareTo(other.toString());
	}
	
	/**
	 * Finds the last question in the list
	 * @return the last question
	 */
	public Question getLast() {
		return questions[questionCount-1];
	}
	
	/**
	 * Saves a new question at a specified index
	 * @param index		The location of the question
	 * @param saved		The new question to be saved
	 */
	public void saveQuestion (int index, Question saved) {
		questions[index] = saved;
	}
	
	/**
	 * Saves a new multiple choice question at a specified index
	 * @param index		The location of the question
	 * @param saved		The new multiple choice question to be saved
	 */
	public void saveMCQuestion (int index, MultipleChoiceQuestion saved) {
		questions[index] = saved;
	}
	

	////////// PRIVATE HELPER METHODS //////////
	/**
	 * Re-Sorts Question array after a Question is deleted
	 * @param index The index of the deleted Question
	 */
	private void reSort(int index) {
		// Resort each question, starting at index
		for (int i = index; i < questionCount; i++) {
			questions[i] = questions[i + 1];
		}
		// Set last question to null
		questions[questionCount] = null;
	}

	/**
	 * A method that generates a random number (for random question generator)
	 * @param from
	 * @param to
	 * @return
	 */
	private int getRandom(int from, int to) {
		// Check which number is larger
		if (from < to) {
			// Return random number
			return from + new Random().nextInt(Math.abs(to - from));
		}
		else {
			// Return random number
			return from - new Random().nextInt(Math.abs(to - from));
		}
	}

	/**
	 * alreadyChosen returns a boolean indicating whether or not a Question
	 * has already been chosen
	 * @param question The Question
	 * @param chosen An array of already chosen Questions
	 * @param count The number of chosen Questions
	 * @return True if the Question has been chosen, false otherwise
	 */
	private boolean alreadyChosen(Question question, Question [] chosen, int count) {
		// Set default boolean to false
		boolean alreadyChosen = false;

		// Loop through chosen questions
		for (int i = 0; i < count; i++) {
			// If question matches chosen Question, then output true
			if (chosen[i] == question)
				alreadyChosen = true;
		}
		// Else if no match, output false
		return alreadyChosen;
	}

} //////////////////// End of Category ////////////////////