//////////////////// Data ////////////////////
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
/**
 * Data handles the server side data functions and facilitates data persistence
 * @author Group 2
 */
@SuppressWarnings("serial")
public class Data extends JApplet {


	////////////////////////////////

	////////// ATTRIBUTES //////////
	/**
	 * Path of the server files
	 */
	private String path;

	/**
	 * Admin password
	 */
	private String password;

	/**
	 * Challenge timer for users
	 */
	private String timer;

	/**
	 * String used to hold ID to be used to save files for each user
	 */
	private String id;
	
	/**
	 * Linked list holding all categories
	 */
	public LinkedList<LinearNode<Category>> categoryList;
	
	/**
	 * Vector to receive entire bulk load, indexing each line
	 */
	Vector<String> arrayBulk;
	
	/**
	 * Vector to receive category names
	 */
	Vector<String> arrayCATs;
	
	/**
	 * URL connection variable used to connect to a PHP server
	 */
	URLConnection connection;
	
	/**
	 * Constant String representing a category
	 */
	private final String CATEGORY = "@CAT";

	/**
	 * Constant String representing a multiple choice question
	 */
	private final String MULTIPLECHOICE = "@QUM";
	
	/**
	 * Constant String representing a multiple choice question
	 */
	private final String SHORTANSWER = "@QUS";

	/**
	 * Constant String representing a multiple choice question
	 */
	private final String ANSWER = "@ANS";
	
	/**
	 * Constant String representing a multiple choice question
	 */
	private final String CHOICE = "@CHO";


	////////// CONSTRUCTOR //////////
	/**
	 * Default constructor takes in a bulk load file from the server, 
	 * loads it into the applet, and processes all categories and questions.
	 * @param filename 		The bulk-load file to be loaded
	 */
	public Data(String filename){
		// Set the path for the Database
		path = filename;
		// Get the saved password
		loadPassword();
		arrayBulk = new Vector<String>();
		categoryList = new LinkedList<LinearNode<Category>>();
		String filePath = path + "bulkload.txt";
		int count = 0;

		/*Open the bulkload file on the current server and try reading from it
		 * and building the array of category objects.
		 */
		try {
			// Create a URL for the desired page
			URL url = new URL(filePath);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;

			// Read through the text file and saving each line as a value
			while ((str = in.readLine()) != null) {
				arrayBulk.add(str);
				count++;
				System.out.println(str);
			}
			in.close();
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		if (count > 0){		// If file is not empty
			for (int i = 0; i < count; i++) {	// Loops through each line of file
				if (arrayBulk.get(i).substring(0, 4).equals(CATEGORY)){		// Checks if line at index 'i' is a category declaration
					try {
						int loc = arrayBulk.get(i).toString().indexOf('^');	// Searches command for '^' symbol for difficulty
						if (loc == -1)	// If '^' symbol was not found, does not set difficulty
						{
							loc = arrayBulk.get(i).length();
						}
						String level;
						try{
							level = arrayBulk.get(i).toString().substring(loc+1);
						}catch (StringIndexOutOfBoundsException ie)
						{
							level = "Easy";
						}
						// Otherwise, difficulty is loaded in and set when creating new category
						addCategory(arrayBulk.get(i).substring(6, loc) .toString(), level);
					} catch (CategoryException | WrongNameException e) {
						e.printStackTrace();
					}
				}
				else if (arrayBulk.get(i).substring(0, 4).equals(MULTIPLECHOICE)){	// Checks if line at index 'i' is a multiple choice declaration
					Question newQuestion = new MultipleChoiceQuestion(categoryList.getLast().getElement(),
							arrayBulk.get(i).substring(6).toString(), null, null);	// Creates multiple choice question out of line
					categoryList.getLast().getElement().addQuestion(newQuestion);	

				}
				else if (arrayBulk.get(i).substring(0, 4).equals(SHORTANSWER)){		// Checks if line at index 'i' is a short answer declaration
					Question newQuestion = new ShortAnswerQuestion(categoryList.getLast().getElement(),
							arrayBulk.get(i).substring(6).toString(), null);		// Creates short answer question out of line
					categoryList.getLast().getElement().addQuestion(newQuestion);

				}
				else if (arrayBulk.get(i).substring(0, 4).equals(ANSWER))	// Checks if line at index 'i' is a question answer
				{
					// Sets as answer to most recent question
					categoryList.getLast().getElement().getLast().setAnswer(arrayBulk.get(i).substring(6).toString());	
				}
				else if (arrayBulk.get(i).substring(0, 4).equals(CHOICE))	// Checks if line at index 'i' is a multiple choice
				{
					if (categoryList.getLast().getElement().getLast().getType().compareTo("MultipleChoice") == 0)
					{
						// Sets as a choice to most recent multiple choice question
						((MultipleChoiceQuestion) categoryList.getLast().getElement().getLast()).setDecoyChoice(arrayBulk.get(i).substring(6).toString());
					}

				}
			}
		}
	}
	
	
	/////////////////////////////
	
	////////// METHODS //////////
	/**
	 * Gets the current password stored in the database
	 * @return Password within database.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Changes the current password within the database
	 * @param newPassword New password to be set
	 */
	public void changePassword(String newPassword) {
		password = newPassword;
		writePasswordToFile(password);
	}

	/**
	 * Sets the challenge timer within the server
	 * @param newTimer New timer to be set
	 */
	public void changeChallengeTimer(String newTimer) {
		timer = newTimer;
		writeTimerToFile(timer);
	}

	/**
	 * Loads the password stored on the server
	 */
	public void loadPassword() {
		try {
			// Create a URL connection to the password file
			openConnection("password.txt");

			// Read in the saved password
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			password = in.readLine();
			in.close();
		}
		catch(MalformedURLException e) {
			System.out.println("Error: Invalid URL");
		}
		catch (IOException ioe) {
			System.out.println("Error: Password file cannot be read");
		}
	}

	/**
	 * Saves the password to the server
	 */
	public void writePasswordToFile(String password) {
		try {
			// Create a URL connection to the password file
			openConnection("writePassword.php");

			// Write the new password
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("0=" + password);
			writer.flush();
			writer.close();
			connection.getInputStream();
		}
		catch (IOException ioe){
			System.out.println("Error writing password to file");
		}
	}

	/**
	* Sets the ID attribute for saving files.
	* @param ident String identifier to be set
	*/
	public void setID(String ident) {
		id = ident;
	}

	/**
	 * Retrieves the security question and answer of the Facebook user from the server
	 * @return ArrayList where first value is question, second value is answer
	 */
	public ArrayList<String> getSecurityQA() {
		try {
			URL securityFile = new URL(path+ "users/" + id + "/securityQA.txt");

			BufferedReader br = new BufferedReader(new InputStreamReader(
					securityFile.openStream()));
			ArrayList<String> securityQA = new ArrayList<String>();
			String line = "";
			while ((line = br.readLine()) != null) {
				securityQA.add(line);
			}
			br.close();
			this.revalidate();

			return securityQA;
		} 
		catch (FileNotFoundException e) {
			return null;
		} 
		catch (IOException e) {
			return null;
		}
	}

	/**
	 * Saves a new security question and answer for the user
	 * @param question New question to be saved
	 * @param answer New answer to be saved
	 * @return Boolean of whether QA was saved to file
	 */
	public boolean saveSecurityQA(String question, String answer) {
		try {
			URL passwordFile = new URL(path + "writesecurityQA.php");
			URLConnection connection = passwordFile.openConnection();
			connection.setDoOutput(true); // Open output connection
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// Get the output stream writer, so information can be written to
			// the URL
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());
			out.write("question=" + question);
			out.write("&answer=" + answer);

			out.flush();
			out.close();
			connection.getInputStream();

			return true;
		} 
		catch (IOException e1) {
			return false;
		}
	}

	/**
	 * Writes timer to server
	 * @param timer New timer to be set
	 */
	public void writeTimerToFile(String timer) {
		try {
			// Create a URL connection to the password file
			openConnection("writeTimer.php");
			// Write the new password
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("0=" + timer);
			writer.flush();
			writer.close();
			connection.getInputStream();
		}
		catch (IOException ioe){
			System.out.println("Error writing password to file");
		}
	}

	/**
	 * Calls on the PHP file to save data to bulk-load file
	 */
	public void writeToFile(){ // Method to write to file
		try{
			openConnection("writeCategories.php"); // Open connection to PHP file
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream()); // Open connection
			writer.write(outputString()); // Write output to file
			writer.flush();
			writer.close(); // Closer writer
			connection.getInputStream(); // Return input stream
		} catch (IOException e){
			System.out.println(e);
			e.printStackTrace();
		}
	}

	/**
	 * Use the PHP connection to read in data from another server
	 * @param filename		 The complete URL of the other file
	 */
	public void readFromBulk(String bulkloadURL){ // Method to write to file
		try {
			URL url = new URL(path + "readBulk.php");
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream()); // Open connection
			writer.write("bulkloadaddress="+bulkloadURL); // Write output to file
			writer.flush();
			writer.close(); // Closer writer
			connection.getInputStream(); // Return input stream
		} 
		catch(MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Open a file and read it into a Vector.
	 * @param file to be read
	 * @return Vector of strings holding each line of the bulk load
	 */
	private Vector<String> retrieveBulk(String file) {
		Vector<String> outputs = new Vector<String>();
		try {
			// Create a URL for the desired page
			URL url = new URL(path + file);
			URLConnection infoConnection = url.openConnection();
			infoConnection.setDoInput(true);
			infoConnection.setDoOutput(true);

			BufferedReader in = new BufferedReader(new InputStreamReader(infoConnection.getInputStream()));

			String str;
			// Read through the text file and saving each line as a value
			while ((str = in.readLine()) != null) {
				outputs.add(str);
			}
			in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return outputs;
	}

	/**
	 * Load the bulk file, first by validating it
	 * And then saving the correct objects in the category vectors.
	 * @param file File read from the web
	 * @return An array of strings signifying the success or failure of the validation method
	 */
	public String[] loadBulk(String file) {

		String[] validation = new String[1];
		validation = validateBulk(file);

		if (validation[0].equals("")){	// If no errors were found within bulk-load
			arrayBulk = retrieveBulk(file); // Retrieves file from server
			categoryList = new LinkedList<LinearNode<Category>>();

			for (int i = 0; i < arrayBulk.size(); i++){		// Goes through entire file, setting categories and questions
				if (arrayBulk.get(i).substring(0, 4).equals(CATEGORY)){
					try {
						int loc = arrayBulk.get(i).toString().indexOf('^');
						if (loc == -1)
						{
							loc = arrayBulk.get(i).length();
						}
						String level;
						try{
							level = arrayBulk.get(i).toString().substring(loc+1);
						}catch (StringIndexOutOfBoundsException ie)
						{
							level = "Easy";
						}
						addCategory(arrayBulk.get(i).substring(6, loc) .toString(), level);
					} catch (CategoryException | WrongNameException e) {
						e.printStackTrace();
					}
				}
				else if (arrayBulk.get(i).substring(0, 4).equals(MULTIPLECHOICE)){
					Question newQuestion = new MultipleChoiceQuestion(categoryList.getLast().getElement(),
							arrayBulk.get(i).substring(6).toString(), null, null);
					categoryList.getLast().getElement().addQuestion(newQuestion);

				}
				else if (arrayBulk.get(i).substring(0, 4).equals(SHORTANSWER)){
					Question newQuestion = new ShortAnswerQuestion(categoryList.getLast().getElement(),
							arrayBulk.get(i).substring(6).toString(), null);
					categoryList.getLast().getElement().addQuestion(newQuestion);

				}
				else if (arrayBulk.get(i).substring(0, 4).equals(ANSWER))
				{
					categoryList.getLast().getElement().getLast().setAnswer(arrayBulk.get(i).substring(6).toString());
				}
				else if (arrayBulk.get(i).substring(0, 4).equals(CHOICE))
				{
					if (categoryList.getLast().getElement().getLast().getType().compareTo("MultipleChoice") == 0)
					{
						((MultipleChoiceQuestion) categoryList.getLast().getElement().getLast()).setDecoyChoice(arrayBulk.get(i).substring(6).toString());
					}

				}

			}

		}
		return validation;
	}

	/**
	 * Validate that the file is in the correct format.
	 * @param file File to be read from a server
	 * @return Array of strings of length 2, where the first string is the error message, and the second string is the line number
	 * If no error is found, strings will be an empty string
	 */
	public String[] validateBulk(String file){
		// Wrong file extension
		if (!(file.substring(file.length() - 4).equals(".txt"))){
			String[] output = new String[2];
			output[0] = "Please choose a text file.";
			output[1] = "-1";
			return output;
		}
		Vector<String> bulk = retrieveBulk(file);
		// Goes through each line of file
		if (bulk.size() == 0){
			String[] output = new String[2];
			output[0] = "File is empty.";
			output[1] = "-1";
			return output;
		}
		if (bulk.elementAt(0).length() < 7){
			String[] output = new String[2];
			output[0] = "Incorrect/empty command.";
			output[1] = "1";
			return output;
		}
		if (!(bulk.elementAt(0).substring(0,6).equals("@CAT: "))){
			String[] output = new String[2];
			output[0] = "File must start with a category declaration.";
			output[1] = "1";
			return output;
		}
		else if (bulk.elementAt(0).length() < 1 || bulk.elementAt(0).length() > 36){
			String[] output = new String[2];
			output[0] = "Category name incorrect length.";
			output[1] = "1";
			return output;
		}
		for (int i = 1; i < bulk.size(); i++){
			String current = bulk.elementAt(i); // Current line
			// Checks if incorrect command or misplaced question
			if (current.length() < 7){
				String[] output = new String[2];
				output[0] = "Incorrect/empty command.";
				output[1] = "" + (i + 1);
				return output;
			}
			if (!(current.substring(0,6).equals("@CAT: ") || current.substring(0,6).equals("@QUS: ") || current.substring(0,6).equals("@QUM: "))){
				String[] output = new String[2];
				output[0] = "Incorrect command in bulkload.";
				output[1] = "" + (i + 1);
				return output;
			}
			// Error-proofs multiple choice questions
			else if (current.substring(0,6).equals("@QUM: ")){
				for (int j = i + 1; j < bulk.size(); j++){ // Checks for repeats
					if (current.substring(6).equals(bulk.elementAt(j).substring(6))){
						String[] output = new String[2];
						output[0] = "Two identical questions, please fix.";
						output[1] = (i + 1) + " and " + (j + 1);
						return output;
					}
				}
				// Checks for proper description length
				if (current.length() < 1 || current.length()> 206){
					String[] output = new String[2];
					output[0] = "Question description too long.";
					output[1] = "" + (i + 1);
					return output;
				}
				// Checks for proper choice/answer count
				int choCount = 0;
				int ansCount = 0;
				for (int q = 1; q <= 4; q++){
					if ((i + q) < bulk.size()){
						if (bulk.elementAt(i + q).length() < 1 || bulk.elementAt(i + q).length() > 106){
							String[] output = new String[2];
							output[0] = "Question answer/distractor too long.";
							output[1] = "" + (i + q + 1);
							return output;
						}
						else{
							if (bulk.elementAt(i + q).substring(0,6).equals("@CHO: ")) choCount++;
							else if (bulk.elementAt(i + q).substring(0,6).equals("@ANS: ")) ansCount++;
						}
					}
					else{
						String[] output = new String[2];
						output[0] = "Incorrect question format at end of file.";
						output[1] = "" + (i + q + 1);
						return output;
					}
				}
				if (!(choCount == 3 && ansCount == 1)){
					String[] output = new String[2];
					output[0] = "Incorrect command in multiple choice question.";
					output[1] = "" + (i + 1);
					return output;
				}
				i++;
				if (bulk.elementAt(i).substring(6).equals(bulk.elementAt(i+1).substring(6)) ||
						bulk.elementAt(i).substring(6).equals(bulk.elementAt(i+2).substring(6)) ||
						bulk.elementAt(i).substring(6).equals(bulk.elementAt(i+3).substring(6)) ||
						bulk.elementAt(i+1).substring(6).equals(bulk.elementAt(i+2).substring(6)) ||
						bulk.elementAt(i+1).substring(6).equals(bulk.elementAt(i+3).substring(6)) ||
						bulk.elementAt(i+2).substring(6).equals(bulk.elementAt(i+3).substring(6)))
				{
					String[] output = new String[2];
					output[0] = "Duplicate answers in multiple choice question.";
					output[1] = "" + (i + 1);
					return output;
				}
				i = i + 3; 
			}
			// Error-proofs short answer questions
			else if (current.substring(0,6).equals("@QUS: ")){
				// Checks for repeats
				for (int j = i + 1; j < bulk.size(); j++){
					if (current.substring(6).equals(bulk.elementAt(j).substring(6))){
						String[] output = new String[2];
						output[0] = "Two identical questions, please fix.";
						output[1] = (i + 1) + " and " + (j + 1);
						return output;
					}
				}
				// Checks for proper description length
				if (current.length() < 1 || current.length()> 206){
					String[] output = new String[2];
					output[0] = "Question description too long.";
					output[1] = "" + (i + 1);
					return output;
				}
				// Checks if answer follows
				if ((i + 1) < bulk.size()){
					if (!(bulk.elementAt(i + 1).substring(0,6).equals("@ANS: "))){
						String[] output = new String[2];
						output[0] = "Incorrect command in short answer question.";
						output[1] = "" + (i + 2);
						return output;
					}
					else if (bulk.elementAt(i + 1).length() < 1 || bulk.elementAt(i + 1).length() > 106){
						String[] output = new String[2];
						output[0] = "Short answer too long.";
						output[1] = "" + (i + 2);
						return output;
					}
				}
				else{
					String[] output = new String[2];
					output[0] = "Incorrect question format at end of file.";
					output[1] = "" + (i + 1);
					return output;
				}
				i++;
			}
			// Error-proofs categories
			else if (current.substring(0,6).equals("@CAT: ")){
				// Checks for proper category description length
				if (current.length() > 36 || current.length() < 1){
					String[] output = new String[2];
					output[0] = "Incorrect category name length.";
					output[1] = "" + (i + 1);
					return output;
				}
				// Checks for repeats
				for (int j = i + 1; j < bulk.size(); j++){
					if (current.substring(6).equals(bulk.elementAt(j).substring(6))){
						String[] output = new String[2];
						output[0] = "Two identical categories, please fix.";
						output[1] = (i + 1) + " and " + (j + 1);
						return output;
					}
				}
			}
		}
		// If no errors, returns null strings to signify correct bulkload syntax
		String[] output = new String[2];
		output[0] = "";
		output[1] = "";
		return output;
	}

	/**
	 * Adds a new category to database 
	 * @param categoryName name of category being added
	 * @param categoryLevel level of category being added
	 */
	public void addCategory(String categoryName , String categoryLevel) throws CategoryException, WrongNameException {
		// Testing the category name
		if (categoryList.isEmpty() ) {
			// Checking if the name is between 1-30 characters.
			if( categoryName.length()>1 && categoryName.length()<30) {
				Category newCategory = new Category(categoryName, categoryLevel);

				LinearNode<Category> newNode = new LinearNode<Category> (newCategory);
				categoryList.add(newNode);

				System.out.println (newCategory.getCategoryName() + " has been added");
			}
			else {
				throw new CategoryException("category name is too long or too short");
			}
		}
		else {
			// Testing the category name
			if(categoryNameTest (categoryName)) {
				// Checking if the name is between 1-30 characters.
				if( categoryName.length()>1 && categoryName.length()<30) {
					// Creating a new category and adding it to the category list
					Category newCategory = new Category(categoryName, categoryLevel);
					LinearNode<Category> newNode = new LinearNode<Category> (newCategory);
					categoryList.add(newNode);

					System.out.println (newNode.getElement().getCategoryName() + " has been added");
				}
				else {
					throw new CategoryException("category name is too long or too short");
				}
			}
			else {
				// Name already exists exception
				throw new WrongNameException (categoryName +" category name already exists");
			}
		}
	}

	/**
	 * Deletes a category from the database
	 * @param categoryName name of category to be removed
	 */
	public void deleteCategory(String categoryName) throws CategoryException {
		LinearNode<Category> categoryNode = searchCategory(categoryName);

		if ( categoryNode!= null) {
			categoryList.remove(categoryNode);
			System.out.println(categoryName + " is deleted");
		}
		else {
			// Category does not exist
			throw new CategoryException (categoryName +" category name does not exist");
		}
	}

	/**
	 * A method that search for a category by it's name and return the node that contain the category
	 * @param String categoryName
	 * @return LinearNode that contains the category
	 */
	public LinearNode<Category> searchCategory (String categoryName) {
		// Loop through every category
		for( LinearNode<Category> testNode :categoryList ) {
			// If match found
			if(testNode.getElement().getCategoryName().equals(categoryName)) {
				return testNode;
			}
		}
		return null;
	}

	/**
	 * A method that changes the name of any category
	 * This method will delete the category and add a new category with a new name
	 * @param category Category to be changed
	 * @param newCategoryName New string to be set as name for category
	 */
	public void changeCategoryName(Category category , String newCategoryName) throws CategoryException , WrongNameException {
		String categoryLevel = category.getLevel();
		this.deleteCategory(category.getCategoryName());
		this.addCategory(newCategoryName, categoryLevel);
	}

	/**
	 * A method that changes the level of the category
	 * @param categoryName Name of category to be changed
	 * @param categoryLevel New level to be set
	 */
	public void changeCategoryLevel(String categoryName, String categoryLevel) {
		// Loop through every category
		for( LinearNode<Category> testNode: categoryList ) {
			// If match found
			if(testNode.getElement().getCategoryName().equals(categoryName)) {
				testNode.getElement().setLevel(categoryLevel);
				System.out.println("done changing level to " + categoryLevel);
			}
		}
	}

	/**
	 * A method that changes the category of a question
	 * @param String categoryName
	 * @param Question question
	 * @throws WrongNameException
	 */
	public void changeCategoryOfQuestion(String categoryName, Question question) throws WrongNameException {
		// Loop through every category
		for( LinearNode<Category> testNode: categoryList ) {
			// If match found
			if(testNode.getElement().getCategoryName().equals(categoryName)) {
				Category oldCat = question.getCategory();
				oldCat.deleteQuestion(question);
				Category newCat = testNode.getElement();
				newCat.addQuestion(question);
				question.setCategory(newCat);
			}
		}	
	}

	/**
	 * A method that lists all the categories
	 * @return String[] categoryList
	 */
	public String[] listCategories() {
		// Check if category is empty
		if (categoryList.isEmpty()) {
			return new String[0];
		}
		else {
			String[] listOfCategories= new String[categoryList.size()+1];
			int i =0;

			// Loop through every category
			for( LinearNode<Category> categoryNode: categoryList ) {
				listOfCategories[i]=categoryNode.getElement().getCategoryName() +" = "+ categoryNode.getElement().getLevel();
				categoryNode = categoryNode.getNext();
				i++;
			}
			for (int j = 0; j < listOfCategories.length-1; j++) {
				String small = listOfCategories[j];
				int index = j;
				for (int q = j; q < listOfCategories.length-1; q++){
					if (small.compareTo(listOfCategories[q]) > 0) {
						small = listOfCategories[q];
						index = q;
					}
				}
				listOfCategories[index] = listOfCategories[j];
				listOfCategories[j] = small;
			}
			return listOfCategories;
		}
	}

	/**
	 * A method that counts the number of categories in the game
	 * @return The number of categories in the game
	 */
	public int numberOfCategories() {
		return categoryList.size();
	}

	/**
	 * A method that returns the category object that has the name in the promenader
	 * @param categoryName Category to be searched.
	 * @return Category to be returned.
	 */
	public Category getCategory(String categoryName) {
		// Loop through every category
		for( LinearNode<Category> testNode: categoryList ) {
			// If match found
			if(testNode.getElement().getCategoryName().equals(categoryName)) {
				return testNode.getElement();
			}
		}
		// the name of the category is not found.
		return null;
	}


	/**
	 * A method that saves all the category names and questions and their answers into a vector
	 * that has the same layout as the bulk load file
	 * this method is used to save all the data in a file
	 * @return Vector of strings ready to be placed in bulkload
	 */
	public Vector<String> toBulkLoad () {
		Vector<String> toBulkLoad = new Vector<String>();
		for(LinearNode<Category> categoryNode: categoryList )
		{
			toBulkLoad.addAll(categoryNode.getElement().toBulkLoad());
		}

		return toBulkLoad;
	}

	/**
	 * Method that gets all categories from the database.
	 * @return Array of all categories within the database.
	 */
	public Category[] getCategories() {
		int index = 0, max = numberOfCategories();
		Category [] categories = new Category[max];

		for(LinearNode<Category> categoryNode: categoryList ) {
			categories[index] = categoryNode.getElement();
			categoryNode = categoryNode.getNext();
			index++;
		}
		// Return an array containing all categories
		return categories;
	}

	/**
	 * Method that counts the number of categories with 5 or more questions.
	 * @return Number of categories with 5 or more questions.
	 */
	public int catQuestionCount()	{
		int count = 0;
		for( LinearNode<Category> testNode: categoryList ) {
			if(testNode.getElement().numQuestions() >= 5) {
				count++;
			}
		}
		// Return number of categories with 5 or more questions
		return count;
	}

	/**
	 * Method that gets all categories from the database that have 5 or more questions.
	 * @return Array of categories with 5 or more questions.
	 */
	public String[] listCategoriesChallenge() {
		if (categoryList.isEmpty()) {
			return new String[0];
		}
		else {
			String[] listOfCategories= new String[categoryList.size()+1];
			int i =0;

			for( LinearNode<Category> categoryNode: categoryList ) {
				if (categoryNode.getElement().numQuestions() >= 5){
					listOfCategories[i]=categoryNode.getElement().getCategoryName() +" = "+ categoryNode.getElement().getLevel();
					i++;
				}
				categoryNode = categoryNode.getNext();
			}
			if (i == 0){
				return new String[0];
			}
			return listOfCategories;
		}
	}
	
	
	///////////////////////////////////////////
	
	//////////PRIVATE HELPER METHODS //////////
	/**
	 * Opens up a URL connection to the server
	 * @param fileName The file name of the desired connection
	 */
	private void openConnection (String fileName) {
		try{
			URL url = new URL (path + fileName);
			connection = url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
		}
		catch (IOException ioe) {
			System.out.println("Error: Password file cannot be read");
		}
	}
	
	/**
	 * A method that tests the category name if it exists
	 * @param categoryName name of category to be searched
	 * @return true if pass the test and false otherwise
	 */
	private boolean categoryNameTest (String categoryName) {
		// test if the category name already exists.
		for( LinearNode<Category> testNode :categoryList ) {
			if(testNode.getElement().getCategoryName().equals(categoryName)) {
				return false;
			}
		}
		// the category name passed all the tests, it's valid
		return true;
	}
	
	/**
	 * Format string for output to file
	 * @return String in proper post format to be sent to php
	 */
	private String outputString(){
		String output = "";

		LinkedList<LinearNode<Category>> spareList = new LinkedList<LinearNode<Category>>();
		int count = 0;
		while (!(categoryList.isEmpty())){
			Vector<String> bulk = categoryList.peek().getElement().toBulkLoad();
			Category current = categoryList.peek().getElement();
			spareList.add(categoryList.pop());
			System.out.println(current.getCategoryName());
			for (int j = 0; j < bulk.size(); j++){
				output = output + count + "=" + bulk.elementAt(j) + "&";
				count++;
			}
		}
		categoryList = spareList;
		return output;
	}

} //////////////////// End of Data ////////////////////