//////////////////// Question ////////////////////
import java.util.*;
/**
 * Question represents a question of type multiple choice,
 * short answer, or voice.
 * @author Group 2
 */
public class Question implements Comparable<Question> {
	
	
	////////// ATTRIBUTES //////////
	/**
	 * The Category the Question belongs to
	 */
	private Category category;
	
	/**
	 * Question description
	 */
	private String description;
	
	/**
	 * The correct answer to the Question
	 */
	private String answer;
	
	/**
	 * Type of question
	 */
	private int type;
	
	/**
	 * Constant integer representing a lexicographic match
	 */
	private final int MATCH = 0;
	
	/**
	 * Constant integer representing multiple choice type
	 */
	private final int MULTIPLE_CHOICE = 1;
	
	/**
	 * Constant integer representing short answer type
	 */
	private final int VOICE_QUESTION = 2;
	
	/**
	 * Constant integer representing voice type
	 */
	private final int SHORT_ANSWER = 3;
	

	////////// CONSTRUCTOR //////////
	/**
	 * Default constructor initializes Question
	 * @param category		The category of the Question
	 * @param description	The Question description
	 * @param answer		The correct answer
	 * @param type			The Question type
	 */
	public Question (Category category, String description, String answer, int type) {
		this.category = category;
		this.description = description;
		this.answer = answer;
		this.type = type;
	}
	
	
	////////// METHODS //////////
	/**
	 * Set a new Category
	 * @param newCategory 	The new Category
	 */
	public void setCategory(Category newCategory) {
		category = newCategory;
	}
	
	/**
	 * Get the current Category
	 * @return category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Set the Question description
	 * @param description 	The new description
	 */
	public void setDescription(String newDescription) {
		description = newDescription;
	}

	/**
	 * Get the current Question description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the correct Answer
	 * @param newAnswer		The new answer
	 */
	public void setAnswer(String newAnswer) {
		answer = newAnswer;
	}
	
	/**
	 * Get the correct Answer
	 * @return answer
	 */
	public String getAnswer() {
		return answer;
	}
	
	/**
	 * Set the type of the Question
	 * @param newType		The new type
	 */
	public void setType (String newType) {
		// Check the type of question
		if (newType.compareTo("MultipleChoice") == MATCH)
			type = MULTIPLE_CHOICE;
		
		else if (newType.compareTo("VoiceQuestion") == MATCH)
			type = VOICE_QUESTION;
		
		else
			type = SHORT_ANSWER;
	}

	/**
	 * Get the type of the Question, either multiple choice,
	 * short answer, or voice
	 * @return type
	 */
	public String getType() {
		// Check the type of question
		if (type == MULTIPLE_CHOICE)
			return "MultipleChoice";
		
		else if (type == VOICE_QUESTION)
			return "VoiceQuestion";
		
		else
			return "ShortAnswer";
	}
	
	/**
	 * Returns a Vector of formatted String objects
	 * @return	toBulkload
	 */
	public Vector<String> toBulkLoad() {
		// Create a Vector for bulk file
		Vector<String> toBulkLoad = new Vector<String>();
		toBulkLoad.add("@QUS: "+ getDescription());
		toBulkLoad.add("@ANS: "+ getAnswer());
		return toBulkLoad;
	}
	
	/**
	 * toString returns the description for visibility in a JList
	 * @return description
	 */
	public String toString() {
		return description;
	}
	
	/**
	 * Compares two Questions by description
	 * @return	an integer representing a lexicographic comparison
	 */
	@Override
    public int compareTo(Question other){
        return description.compareTo(other.toString());
    }

} //////////////////// End of Question ////////////////////