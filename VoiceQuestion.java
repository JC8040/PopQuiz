//////////////////// VoiceQuestion ////////////////////
import java.util.*;
/**
 * VoiceQuestion represents a voice question with audio
 * @author Group 2
 */
public class VoiceQuestion extends Question {
	
	
	////////// ATTRIBUTES //////////
	/**
	 * Audio
	 */
	private SoundPlayer voiceDescription ;

	
	////////// CONSTRUCTOR //////////
	/**
	 * Default constructor initializes VoiceQuestion
	 * @param category		The category of the question
	 * @param description 	The question description
	 * @param rightAnswer 	The correct answer
	 * @param audio			The audio description
	 */
	public VoiceQuestion(Category category, String description, String rightAnswer, SoundPlayer audio) {
		// Voice question is type 2
		super(category, description, rightAnswer, 2);
		this.voiceDescription = audio;
	}

	
	////////// METHODS //////////
	/**
	 * Sets the voiceDescription of the question
	 * @param voiceDescription 		The audio file to be set
	 */
	public void SetVoiceDescription (SoundPlayer voiceDescription) {
		this.voiceDescription= voiceDescription;
	}

	/**
	 * Gets the voiceDescription
	 * @return The audio file
	 */
	public SoundPlayer getVoiceDescription () {
		return this.voiceDescription;
	}

	/**
	 * Returns a Vector of formatted String objects
	 * @return	toBulkload
	 */
	public Vector<String> toBulkLoad() {
		Vector<String> toBulkLoad = new Vector<String>();
		toBulkLoad.add("@QUS: "+ this.getDescription());
		toBulkLoad.add("@ANS: "+this.getAnswer());
		return toBulkLoad;
	}

} //////////////////// End of VoiceQuestion ////////////////////