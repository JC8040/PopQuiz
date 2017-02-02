//////////////////// MultipleChoiceQuestion ////////////////////
import java.util.*;
/**
 * MultipleChoiceQuestion represents a multiple choice question
 * with one correct answer and three decoy answers
 * @author Group 2
 */
public class MultipleChoiceQuestion extends Question {
	
	
	////////// ATTRIBUTES //////////
	/**
	 * Decoy choice #1
	 */
	private String decoy1;
	
	/**
	 * Decoy choice #2
	 */
	private String decoy2;
	
	/**
	 * Decoy choice #3
	 */
	private String decoy3;
	
	
	////////// CONSTRUCTOR //////////
	/**
	 * Default constructor initializes MultipleChoiceQuestion
	 * @param category		The category of the question
	 * @param description 	The question description
	 * @param rightAnswer 	The correct answer
	 * @param decoys 		The incorrect answers
	 */
	public MultipleChoiceQuestion(Category category, String description, String rightAnswer, String [] decoys) {
		// Multiple choice question is type 1
		super(category, description, rightAnswer, 1);

		// Set the decoy answers
		try {
		decoy1 = decoys[0];
		decoy2 = decoys[1];
		decoy3 = decoys[2];
		}
		catch (NullPointerException e) {
			decoy1 = "";
			decoy2 = "";
			decoy3 = "";
		}
	}
	
	
	////////// METHODS //////////
	/**
	 * Sets the first decoy answer
	 * @param newDecoy	The new decoy
	 */
	public void setDecoy1(String newDecoy) {
		decoy1 = newDecoy;
	}
	
	/**
	 * Gets the first decoy answer
	 * @return	decoy1
	 */
	public String getDecoy1() {
		return decoy1;
	}
	
	/**
	 * Sets the second decoy answer
	 * @param newDecoy	The new decoy
	 */
	public void setDecoy2(String newDecoy) {
		decoy2 = newDecoy;
	}
	
	/**
	 * Gets the second decoy answer
	 * @return	decoy2
	 */
	public String getDecoy2() {
		return decoy2;
	}
	
	/**
	 * Sets the third decoy answer
	 * @param newDecoy	The new decoy
	 */
	public void setDecoy3(String newDecoy) {
		decoy3 = newDecoy;
	}
	
	/**
	 * Gets the third decoy answer
	 * @return	decoy3
	 */
	public String getDecoy3() {
		return decoy3;
	}

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
		toBulkLoad.add("@QUM: "+ getDescription());
		toBulkLoad.add("@ANS: "+ getAnswer());
		toBulkLoad.add("@CHO: "+ decoy1);
		toBulkLoad.add("@CHO: "+ decoy2);
		toBulkLoad.add("@CHO: "+ decoy3);
		return toBulkLoad;
	}
	
	/**
	 * Set the decoy answers from the bulk-loading using this method
	 * @param choice
	 */
	public void setDecoyChoice(String choice) {
		if (decoy1.compareTo("") == 0) {
			// Set decoy 1
			decoy1 = choice;
		}
		else if (decoy2.compareTo("") == 0) {
			// Set decoy 2
			decoy2 = choice;
		}
		else if (decoy3.compareTo("") == 0) {
			// Set decoy 3
			decoy3 = choice;
		}
		else {
			// Do nothing
		}
	}

} //////////////////// End of MultipleChoiceQuestion ////////////////////