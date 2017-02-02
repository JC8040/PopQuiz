//////////////////// ShortAnswerQuestion ////////////////////
import java.util.Vector;
/**
 * ShortAnswerQuestion represents a short answer question
 * with one correct answer
 * @author Group 2
 */
public class ShortAnswerQuestion extends Question {

	
	////////// ATTRIBUTES //////////
	/**
	 * The answer to the question
	 */
	private String userAnswer;

	
	////////// CONSTRUCTOR //////////
	/**
	 * Default constructor initializes ShortAnswerQuestion
	 * @param category		The category of the question
	 * @param description 	The question description
	 * @param rightAnswer 	The correct answer
	 */
	public ShortAnswerQuestion(Category category, String description, String rightAnswer) {
		// Short answer question is type 3
		super(category, description, rightAnswer, 3);
	}
	

	////////// METHODS //////////
	/**
	 * Sets the answer
	 * @param userAnswer 	The answer to the question
	 */
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	/**
	 * Gets the answer
	 * @return The answer
	 */
	public String getUserAnswer() {
		return this.userAnswer;
	}

	/**
	 * Prints the info of the current question
	 */
	public void printQuestion() {
		System.out.println (this.getType());
		System.out.println ( this.getDescription());
		System.out.println (this.getRightAnswer());
	}

	/**
	 * toString returns a string representation of the current question
	 */
	public String toString() {
		String toString ="Question: "+ this.getDescription()
						+"\n"+ "Answer: "+ this.getRightAnswer();
		return toString;
	}

	/**
	 * Gets the answer from the super class
	 * @return	The answer
	 */
	private String getRightAnswer() {
		return super.getAnswer();
	}

	/**
	 * Returns a Vector of formatted String objects
	 * @return	toBulkload
	 */
	public Vector<String> toBulkLoad() {
		// Create a new vector to save question info
		Vector<String> toBulkLoad = new Vector<String>();
		toBulkLoad.add("@QUS: "+ this.getDescription());
		toBulkLoad.add("@ANS: "+this.getRightAnswer());
		return toBulkLoad;
	}

} //////////////////// End of ShortAnswerQuestion ////////////////////