//////////////////// PopQuizGUI ////////////////////
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 * PopQuizGUI represents the main user interface for the 2212 group project.
 * @author Group 2
 */
@SuppressWarnings("serial")
public class PopQuizGUI extends JApplet {


	////////////////////////////////

	////////// ATTRIBUTES //////////
	/**
	 * Database is an Object that controls data functions
	 */
	private Data database;
	
	/**
	 * Path is a string that stores the path of the applet
	 */
	private String path;

	/**
	 * An object representing the current User
	 */
	private User currentUser;

	/**
	 * Background is Blue(Default), Green, or Purple
	 */
	private JPanel background;

	/**
	 * Header is a fixed image ("popquiz-header.png")
	 */
	private JLabel header;

	/**
	 * Welcome message displays User's first name
	 */
	private JLabel welcomeMessage;

	/**
	 * Welcome panel is the first visible screen
	 */
	private JPanel welcomePanel;

	/**
	 * Play game label
	 */
	private JLabel playGameLabel;

	/**
	 * Play game button takes the User to the Player screen
	 */
	private JButton playGame;

	/**
	 * Select a skin button allows the User to pick a skin
	 */
	private JButton selectASkin;

	/**
	 * Option panel that pops up when User clicks "Select a Skin"
	 */
	private JPanel skinOptionPanel;

	/**
	 * Blue skin button
	 */
	private JRadioButton blue;

	/**
	 * Green skin button
	 */
	private JRadioButton green;

	/**
	 * Purple skin button
	 */
	private JRadioButton purple;

	/**
	 * Green skin image
	 */
	private JLabel greenSkin;

	/**
	 * Purple skin image
	 */
	private JLabel purpleSkin;

	/**
	 * Skin options
	 */
	private ButtonGroup skinOptions;

	/**
	 * Admin login label
	 */
	private JLabel adminLoginLabel;

	/**
	 * Password label
	 */
	private JLabel passwordLabel;

	/**
	 * Password field is where the admin can enter the password
	 */
	private JPasswordField passwordField;

	/**
	 * Enter button authenticates password and takes User to admin screen
	 */
	private JButton enterPassword;

	/**
	 * Count of the admin login attempts (Max. 3)
	 */
	private int pwdAttemptCount;

	/**
	 * Admin panel is the first screen that is visible in admin mode
	 */
	private JPanel adminPanel;

	/**
	 * Admin mode label
	 */
	private JLabel adminModeLabel;

	/**
	 * Access bulk-load file button
	 */
	private JButton accessBulkload;

	/**
	 * Change admin password button
	 */
	private JButton changePassword;

	/**
	 * Set challenge time
	 */
	private JButton setTime;

	/**
	 * Back to main menu button
	 */
	private JButton backToMainMenu;

	/**
	 * Category list label
	 */
	private JLabel categoryLabel;

	/**
	 * List of categories
	 */
	private JList<String> categoryList;

	/**
	 * Scroll bar for category list
	 */
	private JScrollPane categoryScroller;

	/**
	 * Add category button
	 */
	private JButton addCategory;

	/**
	 * Edit category button
	 */
	private JButton editCategory;

	/**
	 * Remove category button
	 */
	private JButton removeCategory;

	/**
	 * Edit category panel is the screen that is visible when editing a category in admin mode
	 */
	private JPanel editCategoryPanel;

	/**
	 * Edit category label
	 */
	private JLabel editCategoryLabel;

	/**
	 * Category difficulty label
	 */
	private JLabel categoryDifficulty;

	/**
	 * Easy category difficulty button
	 */
	private JRadioButton easy;

	/**
	 * Medium category difficulty button
	 */
	private JRadioButton medium;

	/**
	 * Hard category difficulty button
	 */
	private JRadioButton hard;

	/**
	 * Set category difficulty button
	 */
	private JButton setDifficulty;

	/**
	 * Back to admin panel button
	 */
	private JButton backToAdmin;

	/**
	 * Question list label
	 */
	private JLabel questionLabel;

	/**
	 * List of questions
	 */
	private JList<String> questionList;

	/**
	 * Scroll bar for question list
	 */
	private JScrollPane questionScroller;

	/**
	 * Add question button
	 */
	private JButton addQuestion;

	/**
	 * Edit question button
	 */
	private JButton editQuestion;

	/**
	 * Remove question button
	 */
	private JButton removeQuestion;

	/**
	 * Edit question panel is the screen that is visible when editing a question in admin mode
	 */
	private JPanel editQuestionPanel;

	/**
	 * Edit question label
	 */
	private JLabel editQuestionLabel;

	/**
	 * Back to edit category panel button
	 */
	private JButton backToEditCategory;
	
	/**
	 * Change the category of the selected question button
	 */
	private JButton changeQuestionCategory;

	/**
	 * Description label
	 */
	private JLabel descriptionLabel;

	/**
	 * Question description (*editable*)
	 */
	private JTextPane descriptionPane;

	/**
	 * Editable answer/choices box
	 */
	private JComboBox<String> answerBox;

	/**
	 * Button to save changes to the current Question
	 */
	private JButton saveQuestion;

	/**
	 * Challenge panel is the screen that is visible after the User clicks "play game"
	 */
	private JPanel challengePanel;

	/**
	 * Challenge label
	 */
	private JLabel challengeLabel;

	/**
	 * View pending challenges button
	 */
	private JButton viewChallenges;

	/**
	 * View game history button
	 */
	private JButton viewHistory;
	
	/**
	 * View high scores button
	 */
	private JButton highScores;

	/**
	 * Back to welcome screen button
	 */
	private JButton backToWelcome;

	/**
	 * Friend list label
	 */
	private JLabel friendLabel;

	/**
	 * Array of user's Friends
	 */
	private User [] userFriends;

	/**
	 * List of users
	 */
	private JList<User> friendList;

	/**
	 * Scroll bar for friend list
	 */
	private JScrollPane friendScroller;

	/**
	 * Challenge friend button
	 */
	private JButton challengeFriend;
	
	/**
	 * The screen that displays the game instructions
	 */
	private JPanel instructionPanel;
	
	/**
	 * The instruction label
	 */
	private JLabel instructionLabel;
	
	/**
	 * The 1st item on the instruction list
	 */
	private JLabel instruction1;
	
	/**
	 * The 2nd item on the instruction list
	 */
	private JLabel instruction2;
	
	/**
	 * The 3rd item on the instruction list
	 */
	private JLabel instruction3;
	
	/**
	 * The 4th item on the instruction list
	 */
	private JLabel instruction4;
	
	/**
	 * The button that starts the game
	 */
	private JButton startGame;

	/**
	 * The screen that is visible during game play
	 */
	private JPanel gamePlayPanel;

	/**
	 * The panel that houses the game clock
	 */
	private JPanel clockPanel;
	
	/**
	 * The game clock label
	 */
	private JLabel clock;
	
	/**
	 * Game clock timer, counts every second down from 11
	 */
	private Timer gameClock;
	
	/**
	 * The seconds left in the round
	 */
	private int seconds;
	
	/**
	 * Constant, 1 second each tick of the game clock
	 */
	private final int ONE = 1000; // 1,000 milliseconds = 1 second
	
	/**
	 * The User's first name
	 */
	private JLabel userName;

	/**
	 * The User's score
	 */
	private JLabel userScore;

	/**
	 * The Opponent's first name
	 */
	private JLabel opponentName;

	/**
	 * The Opponent's score
	 */
	private JLabel opponentScore;

	/**
	 * The panel displaying the Question
	 */
	private JPanel questionPanel;

	/**
	 * The Question description
	 */
	private JTextPane question;
	
	/**
	 * Editable text field for input answer (*ShortAnswer/Voice only*)
	 */
	private JTextPane answer;
	
	/**
	 * Enter button to submit answer from text field (ShortAnswer/Voice only)
	 */
	private JButton enter;

	/**
	 * Multiple choice answer button #1
	 */
	private JButton answer1;

	/**
	 * Multiple choice answer button #2
	 */
	private JButton answer2;

	/**
	 * Multiple choice answer button #3
	 */
	private JButton answer3;

	/**
	 * Multiple choice answer button #4
	 */
	private JButton answer4;
	
	/**
	 * The screen that is visible once a game has finished
	 */
	private JPanel resultsPanel;
	
	/**
	 * Game over label
	 */
	private JLabel gameOver;
	
	/**
	 * Your score label
	 */
	private JLabel yourScoreLabel;
	
	/**
	 * Users score
	 */
	private JLabel yourScore;
	
	/**
	 * Question score results
	 */
	private JLabel q1;
	private JLabel q2;
	private JLabel q3;
	private JLabel q4;
	private JLabel q5;
	
	/**
	 * Question indicator (correct or incorrect)
	 */
	private JLabel r1;
	private JLabel r2;
	private JLabel r3;
	private JLabel r4;
	private JLabel r5;
	
	/**
	 * Win or lose label
	 */
	private JLabel winOrLose;
	
	/**
	 * A new challenge from current user
	 */
	private Challenge newChallenge;
	
	/**
	 * The current game being played
	 */
	private Game game;
	
	/**
	 * Binary array of game results (1 = correct, 0 = incorrect)
	 */
	private int[] gameResults;
	
	/**
	 * The running score of the current game being played
	 */
	private int runningScore;
	
	/**
	 * The current streak of the game being played
	 */
	private int currentStreak;

	/**
	 * Constant for no selection of JList
	 */
	private final int NOT_SELECTED = -1;

	/**
	 * Constant representing voice type
	 */
	private final int SHORT_ANSWER = 3;
	
	/**
	 * Current category being edited
	 */
	private Category selectedCategory;
	
	/**
	 * Remembers the index of the answer or choice being edited
	 */
	private int remember;

	/**
	 * Current question being edited
	 */
	private Question currentQuestion;
	
	/**
	 * Current multiple choice question being edited
	 */
	private Question currentMC;
	
	/**
	 * The current round, if a game is being played
	 */
	private int round;
	
	/**
	 * The five questions selected for the current game
	 */
	private Question [] fiveQuestions;
	
	/**
	 * Scores of each question
	 */
	private int [] scores;
	
	/**
	 * A timer for the game
	 */
	private Timer gameTimer;
	
	/**
	 * Constant, 11 seconds to answer each question during the game
	 */
	private final int ELEVEN = 11000; // 11,000 milliseconds = 11 seconds

	/**
	 * Exit button closes the applet
	 */
	private JButton exit;
	
	/**
	 * Audio recorder applet
	 */
	private AudioApplet recorder;
	
	/**
	 * Array of challenges
	 */
	private Challenge [] challengeArray;
	
	/**
	 * Temporary list of challenges
	 */
	private JList<Challenge> temp;
	
	/**
	 * Number of Challenges
	 */
	private int numChallenges;
	
	/**
	 * Which challenge was selected from pending
	 */
	private int whichChallenge;
	
	/**
	 * PlayAgain button lets the user start a new game
	 */
	private JButton playAgain;
	
	/**
	 * Admin secret questions
	 */
	private final String[] PWQuestions = new String[] {"What is the course name?", "What is the instructor's name?", "What is my favorite color?"};
	
    /**
     * Array for storing challenge expiry dates
     */
    private String [] expiryDates;
    
	
	/////////////////////////////////

	////////// CONSTRUCTOR //////////
	/**
	 * Default empty constructor.
	 */
	public PopQuizGUI() {
		
	}


	/////////////////////////////

	////////// METHODS //////////
	/**
	 * init creates all swing components and initializes GUI.
	 */
	public void init() {

		/*
		 * Database
		 */
		path = getCodeBase().toString();
		database = new Data(path);
		
		/*
		 * Current User
		 */
		currentUser = new User (getParameter("userID"), getParameter("name"));
		database.setID(currentUser.getID());
	
		/*
		 * Background (Default = Blue)
		 */
		background = new JPanel();
		background.setBackground(new Color(0x00,0x00,0xD9));	
		background.setLayout(null);

		/*
		 * Header image
		 */
		header = new JLabel();
		header.setBounds(0, 0, 600, 100);
		BufferedImage headerPic = null;
		// Try to load header image
		try {
			URL url = new URL(getCodeBase(), "popquiz-header.png");
			headerPic = ImageIO.read(url);
			header.setIcon(new ImageIcon(headerPic));
		}
		// If error loading header, print result
		catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Error loading header image");
		}
		background.add(header);

		
		////////////////////////////////////
		
		////////// WELCOME SCREEN //////////
		/*
		 * Welcome panel
		 */
		welcomePanel = new JPanel();
		welcomePanel.setBackground(new Color(0x33, 0x33, 0x33));
		welcomePanel.setBounds(34, 200, 531, 243);
		welcomePanel.setLayout(null);

		/*
		 * Personalized welcome message
		 */
		welcomeMessage = new JLabel("Welcome " + getParameter("firstName"));
		welcomeMessage.setBounds(150, 131, 403, 50);
		welcomeMessage.setFont(new Font("Arial Rounded MT Bold", 1, 32));
		welcomeMessage.setForeground(Color.WHITE);
		background.add(welcomeMessage);
		welcomeMessage.setVisible(true);
		
		/*
		 * Play game label
		 */
		playGameLabel = new JLabel("Game Mode");
		playGameLabel.setForeground(Color.WHITE);
		playGameLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		playGameLabel.setBounds(43, 26, 109, 35);
		welcomePanel.add(playGameLabel);

		/*
		 * Play game button
		 */
		playGame = new JButton("play game");
		playGame.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 32));
		playGame.setBounds(42, 73, 203, 103);
		playGame.addActionListener(new PlayGameHandler());
		welcomePanel.add(playGame);

		/*
		 * Select a skin button
		 */
		selectASkin = new JButton("Select a Skin");
		selectASkin.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		selectASkin.setBounds(65, 188, 157, 35);
		selectASkin.addActionListener(new SelectSkinHandler());
		welcomePanel.add(selectASkin);

		/*
		 * Select a skin option pane
		 */
		skinOptionPanel = new JPanel();
		blue = new JRadioButton("Blue");
		green = new JRadioButton("Green");
		purple = new JRadioButton("Purple");

		/*
		 * Skin button group
		 */
		skinOptions = new ButtonGroup();
		skinOptions.add(blue);
		skinOptions.add(green);
		skinOptions.add(purple);
		blue.setSelected(true);

		/*
		 * Green skin image
		 */
		greenSkin = new JLabel();
		greenSkin.setBounds(24, 444, 63, 50);
		BufferedImage greenPic = null;
		// Try to load green skin image
		try {
			URL url = new URL(getCodeBase(), "green-skin.png");
			greenPic = ImageIO.read(url);
			greenSkin.setIcon(new ImageIcon(greenPic));
		}
		// If error loading green skin image, print result
		catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Error: Could not load green skin image");
		}
		background.add(greenSkin);
		greenSkin.setVisible(false);

		/*
		 * Purple skin image
		 */
		purpleSkin = new JLabel();
		purpleSkin.setBounds(31, 444, 63, 50);
		BufferedImage purplePic = null;
		// Try to load purple skin image
		try {
			URL url = new URL(getCodeBase(), "purple-skin.png");
			purplePic = ImageIO.read(url);
			purpleSkin.setIcon(new ImageIcon(purplePic));
		}
		// If error loading purple skin image, print result
		catch (IOException ioe) {
			System.out.println("Error: Could not load purple skin image");
		}
		background.add(purpleSkin);
		purpleSkin.setVisible(false);
		
		/*
		 * User's saved skin
		 */
		String savedSkin = getSkin(currentUser.getID());
		// If saved skin is purple
		if (savedSkin != null && savedSkin.equals("purple")) {
			background.setBackground(new Color(0x46,0x00,0x8C));
			purpleSkin.setVisible(true);	
		}
		// If saved skin is green
		else if (savedSkin != null && savedSkin.equals("green")) {
			background.setBackground(new Color(0x00,0xD9,0x00));
			greenSkin.setVisible(true);
		}

		/*
		 * Admin login label
		 */
		adminLoginLabel = new JLabel("Admin Mode");
		adminLoginLabel.setForeground(Color.WHITE);
		adminLoginLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		adminLoginLabel.setBounds(283, 26, 109, 35);
		welcomePanel.add(adminLoginLabel);

		/*
		 * Password label
		 */
		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		passwordLabel.setBounds(283, 90, 64, 16);
		welcomePanel.add(passwordLabel);

		/*
		 * Password text field
		 */
		passwordField = new JPasswordField();
		passwordField.setBounds(283, 127, 195, 28);
		welcomePanel.add(passwordField);

		/*
		 * Enter password button
		 */
		enterPassword = new JButton("Enter");
		enterPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		enterPassword.setBounds(416, 174, 79, 29);
		enterPassword.addActionListener(new EnterPasswordHandler());
		welcomePanel.add(enterPassword);

		
		//////////////////////////////////
		
		////////// ADMIN SCREEN //////////
		/*
		 * Admin panel
		 */
		adminPanel = new JPanel();
		adminPanel.setBackground(new Color(0x33, 0x33, 0x33));
		adminPanel.setBounds(40, 135, 515, 302);
		adminPanel.setLayout(null);

		/*
		 * Admin mode label
		 */
		adminModeLabel = new JLabel("Admin Mode");
		adminModeLabel.setForeground(Color.WHITE);
		adminModeLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		adminModeLabel.setBounds(30, 35, 109, 35);
		adminPanel.add(adminModeLabel);

		/*
		 * Access bulk-load button
		 */
		accessBulkload = new JButton("Access Bulkload");
		accessBulkload.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		accessBulkload.setBounds(30, 106, 150, 29);
		accessBulkload.addActionListener(new BulkloadHandler());
		adminPanel.add(accessBulkload);

		/*
		 * Change password button
		 */
		changePassword = new JButton("Change Password");
		changePassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		changePassword.setBounds(30, 147, 150, 29);
		changePassword.addActionListener(new ChangePasswordHandler());
		adminPanel.add(changePassword);

		/*
		 * Set challenge time button
		 */
		setTime = new JButton("Set Challenge Time");
		setTime.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		setTime.setBounds(30, 189, 150, 29);
		setTime.addActionListener(new SetChallengeTimeHandler());
		adminPanel.add(setTime);

		/*
		 * Back to main menu button
		 */
		backToMainMenu = new JButton("Back to Main Menu");
		backToMainMenu.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		backToMainMenu.setBounds(30, 230, 150, 29);
		backToMainMenu.addActionListener(new BackToMainHandler());
		adminPanel.add(backToMainMenu);

		/*
		 * Category list label
		 */
		categoryLabel = new JLabel("Category List");
		categoryLabel.setForeground(Color.WHITE);
		categoryLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		categoryLabel.setBounds(215, 78, 138, 16);
		adminPanel.add(categoryLabel);

		/*
		 * Category list with scroll bar
		 */
		categoryList = new JList<String>();
		// Check to make sure there are questions to load
		if (database.numberOfCategories() > 0) {
			categoryList.setListData(database.listCategories());
		}
		// If there are no categories, print result
		else {
			System.out.println("Warning: No Categories to Load!");
		}
		categoryScroller = new JScrollPane(categoryList);
		categoryScroller.setBounds(215, 106, 138, 158);
		adminPanel.add(categoryScroller);

		/*
		 * Add category button
		 */
		addCategory = new JButton("Add");
		addCategory.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		addCategory.setBounds(370, 106, 100, 29);
		addCategory.addActionListener(new AddCategoryHandler());
		adminPanel.add(addCategory);

		/*
		 * Edit category button
		 */
		editCategory = new JButton("Edit");
		editCategory.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		editCategory.setBounds(370, 147, 100, 29);
		editCategory.addActionListener(new EditCategoryHandler());
		adminPanel.add(editCategory);

		/*
		 * Remove category button
		 */
		removeCategory = new JButton("Remove");
		removeCategory.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		removeCategory.setBounds(370, 189, 100, 29);
		removeCategory.addActionListener(new RemoveCategoryHandler());
		adminPanel.add(removeCategory);

		
		//////////////////////////////////////////
		
		////////// EDIT CATEGORY SCREEN //////////
		/*
		 * Edit category panel
		 */
		editCategoryPanel = new JPanel();
		editCategoryPanel.setBackground(new Color(0x33, 0x33, 0x33));
		editCategoryPanel.setBounds(40, 135, 515, 302);
		editCategoryPanel.setLayout(null);

		/*
		 * Edit category label
		 */
		editCategoryLabel = new JLabel("Edit Category");
		editCategoryLabel.setForeground(Color.WHITE);
		editCategoryLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		editCategoryLabel.setBounds(30, 35, 120, 35);
		editCategoryPanel.add(editCategoryLabel);

		/*
		 * Category difficulty label
		 */
		categoryDifficulty = new JLabel("Category Difficulty");
		categoryDifficulty.setForeground(Color.WHITE);
		categoryDifficulty.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		categoryDifficulty.setBounds(30, 78, 138, 16);
		editCategoryPanel.add(categoryDifficulty);

		/*
		 * Easy difficulty button
		 */
		easy = new JRadioButton("Easy", true);
		easy.setForeground(Color.WHITE);
		easy.setBackground(new Color(0x33, 0x33, 0x33));
		easy.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		easy.setBounds(30, 103, 141, 23);
		editCategoryPanel.add(easy);

		/*
		 * Medium difficulty button
		 */
		medium = new JRadioButton("Medium", true);
		medium.setForeground(Color.WHITE);
		medium.setBackground(new Color(0x33, 0x33, 0x33));
		medium.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		medium.setBounds(30, 131, 141, 23);
		editCategoryPanel.add(medium);

		/*
		 * Hard difficulty button
		 */
		hard = new JRadioButton("Hard", true);
		hard.setForeground(Color.WHITE);
		hard.setBackground(new Color(0x33, 0x33, 0x33));
		hard.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		hard.setBounds(30,159, 141, 23);
		editCategoryPanel.add(hard);

		/*
		 * Category difficulty button group
		 */
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(easy);
		btnGroup.add(medium);
		btnGroup.add(hard);

		/*
		 * Set category difficulty button
		 */
		setDifficulty = new JButton("Set Difficulty");
		setDifficulty.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		setDifficulty.setBounds(30, 194, 163, 29);
		setDifficulty.addActionListener(new SetDifficultyHandler());
		editCategoryPanel.add(setDifficulty);

		/*
		 * Back to admin panel button
		 */
		backToAdmin = new JButton("Back to Admin Menu");
		backToAdmin.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		backToAdmin.setBounds(30, 235, 163, 29);
		backToAdmin.addActionListener(new BackToAdminHandler());
		editCategoryPanel.add(backToAdmin);

		/*
		 * Question list label
		 */
		questionLabel = new JLabel("Question List");
		questionLabel.setForeground(Color.WHITE);
		questionLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		questionLabel.setBounds(215, 78, 138, 16);
		editCategoryPanel.add(questionLabel);

		/*
		 * Add question button
		 */
		addQuestion = new JButton("Add");
		addQuestion.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		addQuestion.setBounds(370, 106, 100, 29);
		addQuestion.addActionListener(new AddQuestionHandler());
		editCategoryPanel.add(addQuestion);

		/*
		 * Edit question button
		 */
		editQuestion = new JButton("Edit");
		editQuestion.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		editQuestion.setBounds(370, 147, 100, 29);
		editQuestion.addActionListener(new EditQuestionHandler());
		editCategoryPanel.add(editQuestion);

		/*
		 * Remove question button
		 */
		removeQuestion = new JButton("Remove");
		removeQuestion.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		removeQuestion.setBounds(370, 189, 100, 29);
		removeQuestion.addActionListener(new RemoveQuestionHandler());
		editCategoryPanel.add(removeQuestion);

		
		//////////////////////////////////////////
		
		////////// EDIT QUESTION SCREEN //////////
		/*
		 * Edit question panel
		 */
		editQuestionPanel = new JPanel();
		editQuestionPanel.setBackground(new Color(0x33, 0x33, 0x33));
		editQuestionPanel.setBounds(40, 135, 515, 302);
		editQuestionPanel.setLayout(null);

		/*
		 * Edit question label
		 */
		editQuestionLabel = new JLabel("Edit Question");
		editQuestionLabel.setForeground(Color.WHITE);
		editQuestionLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		editQuestionLabel.setBounds(30, 35, 120, 35);
		editQuestionPanel.add(editQuestionLabel);

		/*
		 * Question list with scroll bar
		 */
		questionList = new JList<String>();
		questionScroller = new JScrollPane(questionList);
		questionScroller.setBounds(215, 106, 138, 158);
		editQuestionPanel.add(questionScroller);

		/*
		 * Back to edit category button
		 */
		backToEditCategory = new JButton("Back to Edit Category");
		backToEditCategory.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		backToEditCategory.setBounds(30, 261, 163, 29);
		backToEditCategory.addActionListener(new BackToEditCategoryHandler());
		editQuestionPanel.add(backToEditCategory);

		/*
		 * Change category of question button
		 */
		changeQuestionCategory = new JButton("Change Category");
		changeQuestionCategory.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		changeQuestionCategory.setBounds(287, 41, 180, 29);
		changeQuestionCategory.addActionListener(new ChangeQuestionCategoryHandler());
		editQuestionPanel.add(changeQuestionCategory);
		
		/*
		 * Description label
		 */
		descriptionLabel = new JLabel("Description");
		descriptionLabel.setForeground(Color.WHITE);
		descriptionLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		descriptionLabel.setBounds(40, 78, 85, 16);
		editQuestionPanel.add(descriptionLabel);
		
		/*
		 * Description pane
		 */
		descriptionPane = new JTextPane();
		descriptionPane.setBounds(39, 106, 428, 51);
		descriptionPane.setEditable(true);
		editQuestionPanel.add(descriptionPane);

		/*
		 * Editable answer/choices box
		 */
		answerBox = new JComboBox<String>();
		answerBox.setBounds(35, 174, 432, 65);
		answerBox.setEditable(true);
		// Add an action listener to remember index of choice
		answerBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        if (answerBox.getSelectedIndex() != -1) {
		        	remember = answerBox.getSelectedIndex();
		        }
		        System.out.println(remember);
		    }
		});
		editQuestionPanel.add(answerBox);
		
		/*
		 * Save Question
		 */
		saveQuestion = new JButton("Save");
		saveQuestion.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		saveQuestion.setBounds(416, 261, 79, 29);
		saveQuestion.addActionListener(new SaveQuestionHandler());
		editQuestionPanel.add(saveQuestion);

		
		//////////////////////////////////////
		
		////////// CHALLENGE SCREEN //////////
		/*
		 * Challenge panel
		 */
		challengePanel = new JPanel();
		challengePanel.setBounds(40, 135, 515, 302);
		challengePanel.setBackground(new Color(0x33, 0x33, 0x33));
		challengePanel.setLayout(null);

		/*
		 * Challenge label
		 */
		challengeLabel = new JLabel("Challenge a Friend");
		challengeLabel.setForeground(Color.WHITE);
		challengeLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		challengeLabel.setBounds(30, 35, 197, 35);
		challengePanel.add(challengeLabel);

		/*
		 * View game history button
		 */
		viewHistory = new JButton("Game History");
		viewHistory.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		viewHistory.setBounds(30, 147, 150, 29);
		viewHistory.addActionListener(new GameHistoryHandler());
		challengePanel.add(viewHistory);

		/*
		 * View pending challenges button
		 */
		viewChallenges = new JButton("Pending Challenges");
		viewChallenges.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		viewChallenges.setBounds(30, 106, 150, 29);
		viewChallenges.addActionListener(new PendingChallengeHandler());
		challengePanel.add(viewChallenges);
		
		/*
		 * View high Scores button
		 */
		highScores = new JButton("High Scores");
		highScores.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		highScores.setBounds(30, 189, 150, 29);
		challengePanel.add(highScores);

		/*
		 * Back to main menu button
		 */
		backToWelcome = new JButton("Back to Main Menu");
		backToWelcome.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		backToWelcome.setBounds(30, 230, 150, 29);
		backToWelcome.addActionListener(new BackToWelcomeHandler());
		challengePanel.add(backToWelcome);

		/*
		 * Friend list label
		 */
		friendLabel = new JLabel("Friend List");
		friendLabel.setForeground(Color.WHITE);
		friendLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		friendLabel.setBounds(215, 78, 138, 16);
		challengePanel.add(friendLabel);
		// Try to load User's Facebook friends
		try {
			loadFriends();
		}
		// Print error message if friends do not load properly
		catch(IOException ioe) {
			System.out.println("Could not load data. " + ioe);
			System.exit(-1);
		}

		/*
		 * Friend list with scroll bar
		 */
		friendList = new JList<User>(userFriends);
		friendScroller = new JScrollPane(friendList);
		friendScroller.setBounds(215, 106, 138, 158);
		challengePanel.add(friendScroller);

		/*
		 * Challenge friend button
		 */
		challengeFriend = new JButton("Challenge!");
		challengeFriend.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		challengeFriend.setBounds(367, 235, 127, 50);
		challengeFriend.addActionListener(new ChallengeFriendHandler());
		challengePanel.add(challengeFriend);

		
		/////////////////////////////////////////////
		
		////////// GAME INSTRUCTION SCREEN //////////
		/*
		 * Instruction panel
		 */
		instructionPanel = new JPanel();
		instructionPanel.setBounds(40, 135, 515, 302);
		instructionPanel.setBackground(new Color(0x33, 0x33, 0x33));
		instructionPanel.setLayout(null);
		
		/*
		 * Instruction label
		 */
		instructionLabel = new JLabel("How to Play:");
		instructionLabel.setForeground(Color.WHITE);
		instructionLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 24));
		instructionLabel.setBounds(27, 38, 152, 29);
		instructionPanel.add(instructionLabel);
		
		/*
		 * First instruction
		 */
		instruction1 = new JLabel("•  Each game consists of 5 questons" );
		instruction1.setForeground(Color.WHITE);
		instruction1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		instruction1.setBounds(27, 79, 459, 16);
		instructionPanel.add(instruction1);
		
		/*
		 * Second instruction
		 */
		instruction2 = new JLabel("•  You have 11 seconds to answer each question");
		instruction2.setForeground(Color.WHITE);
		instruction2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		instruction2.setBounds(27, 107, 459, 16);
		instructionPanel.add(instruction2);
		
		/*
		 * Third instruction
		 */
		instruction3 = new JLabel("•  The faster you answer correctly, the higher your score");
		instruction3.setForeground(Color.WHITE);
		instruction3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		instruction3.setBounds(27, 135, 459, 16);
		instructionPanel.add(instruction3);
		
		/*
		 * Fourth instruction
		 */
		instruction4 = new JLabel("•  You can quit at any time, but you will forfeit the remaining questions");
		instruction4.setForeground(Color.WHITE);
		instruction4.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		instruction4.setBounds(27, 163, 459, 16);
		instructionPanel.add(instruction4);
		
		/*
		 * Start game button
		 */
		startGame = new JButton("start");
		startGame.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 32));
		startGame.setBounds(155, 206, 176, 70);
		startGame.addActionListener(new StartGameHandler());
		instructionPanel.add(startGame);
		
		
		//////////////////////////////////////
		
		////////// GAME PLAY SCREEN //////////
		/*
		 * Game play screen
		 */
		gamePlayPanel = new JPanel();
		gamePlayPanel.setBounds(40, 135, 515, 306);
		gamePlayPanel.setBackground(new Color(0x33, 0x33, 0x33));
		gamePlayPanel.setLayout(null);
		
		/*
		 * Game clock & game timer
		 */
		gameClock = new Timer (ONE, new ClockTickHandler());
		gameTimer = new Timer (ELEVEN, new EndRoundHandler());

		/*
		 * Clock panel houses game clock
		 */
		clockPanel = new JPanel();
		clockPanel.setLayout(null);
		clockPanel.setBackground(Color.WHITE);
		clockPanel.setBounds(206, 17, 72, 53);
		gamePlayPanel.add(clockPanel);
		
		/*
		 * Clock label displays time left
		 */
		clock = new JLabel();
		clock.setFont(new Font("OCR A Std", Font.PLAIN, 42));
		clock.setBounds(6, 6, 60, 57);
		clockPanel.add(clock);
		
		/*
		 * User's first name
		 */
		userName = new JLabel();
		userName.setForeground(Color.WHITE);
		userName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		userName.setBounds(21, 17, 120, 35);
		gamePlayPanel.add(userName);

		/*
		 * User's score
		 */
		userScore = new JLabel();
		userScore.setForeground(Color.YELLOW);
		userScore.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
		userScore.setBounds(31, 64, 85, 16);
		gamePlayPanel.add(userScore);

		/*
		 * Opponent's first name
		 */
		opponentName = new JLabel();
		opponentName.setForeground(Color.WHITE);
		opponentName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		opponentName.setBounds(364, 17, 120, 35);
		gamePlayPanel.add(opponentName);

		/*
		 * Opponent's score
		 */
		opponentScore = new JLabel();
		opponentScore.setForeground(Color.YELLOW);
		opponentScore.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
		opponentScore.setBounds(390, 60, 94, 24);
		gamePlayPanel.add(opponentScore);

		/*
		 * Question panel
		 */
		questionPanel = new JPanel();
		questionPanel.setBackground(Color.WHITE);
		questionPanel.setBounds(24, 99, 460, 90);
		questionPanel.setLayout(null);

		/*
		 * Question description
		 */
		question = new JTextPane();
		question.setEditable(false);
		question.setBounds(18, 19, 248, 53);
		questionPanel.add(question);
		gamePlayPanel.add(questionPanel);
		
		/*
		 * Editable answer text field
		 */
		answer = new JTextPane();
		answer.setEditable(true);
		answer.setBounds(24, 214, 460, 35);
		gamePlayPanel.add(answer);
		answer.setVisible(false);
		
		/*
		 * Enter button
		 */
		enter = new JButton("Enter");
		enter.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		enter.setBounds(416, 261, 79, 29);
		enter.addActionListener(new EndRoundHandler());
		gamePlayPanel.add(enter);
		enter.setVisible(false);

		/*
		 * Answer button 1
		 */
		answer1 = new JButton();
		answer1.setBounds(21, 252, 219, 43);
		answer1.addActionListener(new EndRoundHandler());
		gamePlayPanel.add(answer1);
		answer1.setVisible(false);

		/*
		 * Answer button 2
		 */
		answer2 = new JButton();
		answer2.setBounds(253, 201, 231, 44);
		answer2.addActionListener(new EndRoundHandler());
		gamePlayPanel.add(answer2);
		answer2.setVisible(false);

		/*
		 * Answer button 3
		 */
		answer3 = new JButton();
		answer3.setBounds(21, 202, 219, 43);
		answer3.addActionListener(new EndRoundHandler());
		gamePlayPanel.add(answer3);
		answer3.setVisible(false);

		/*
		 * Answer button 4
		 */
		answer4 = new JButton();
		answer4.setBounds(253, 251, 231, 44);
		answer4.addActionListener(new EndRoundHandler());
		gamePlayPanel.add(answer4);
		answer4.setVisible(false);
		
		
		/////////////////////////////////////////
		
		////////// GAME RESULTS SCREEN //////////
		/*
		 * Game results screen
		 */
		resultsPanel = new JPanel();
		resultsPanel.setBounds(40, 135, 515, 306);
		resultsPanel.setBackground(new Color(0x33, 0x33, 0x33));
		resultsPanel.setLayout(null);
		
		/*
		 * Game over
		 */
		gameOver = new JLabel("Game Over");
		gameOver.setForeground(Color.WHITE);
		gameOver.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		gameOver.setBounds(189, 24, 100, 35);
		resultsPanel.add(gameOver);
		
		/*
		 * Your score label
		 */
		yourScoreLabel = new JLabel("Your Score:");
		yourScoreLabel.setForeground(Color.YELLOW);
		yourScoreLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 24));
		yourScoreLabel.setBounds(277, 85, 190, 33);
		resultsPanel.add(yourScoreLabel);
		
		/*
		 * Score results (Total score)
		 */
		yourScore = new JLabel();
		yourScore.setForeground(Color.YELLOW);
		yourScore.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 24));
		yourScore.setBounds(318, 130, 179, 41);
		resultsPanel.add(yourScore);
		
		/*
		 * Question score results
		 */
		// Question 1 score
		q1 = new JLabel();
		q1.setForeground(Color.WHITE);
		q1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		q1.setBounds(49, 77, 144, 30);
		resultsPanel.add(q1);
		// Question 2 score
		q2 = new JLabel();
		q2.setForeground(Color.WHITE);
		q2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		q2.setBounds(49, 119, 144, 30);
		resultsPanel.add(q2);
		// Question 3 score
		q3 = new JLabel();
		q3.setForeground(Color.WHITE);
		q3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		q3.setBounds(49, 162, 144, 30);
		resultsPanel.add(q3);
		// Question 4 score
		q4 = new JLabel();
		q4.setForeground(Color.WHITE);
		q4.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		q4.setBounds(49, 204, 144, 30);
		resultsPanel.add(q4);
		// Question 5 score
		q5 = new JLabel();
		q5.setForeground(Color.WHITE);
		q5.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		q5.setBounds(49, 246, 144, 30);
		resultsPanel.add(q5);
		
		/*
		 * Question results indicators
		 */
		// Question 1 result
		r1 = new JLabel();
		r1.setBounds(200, 77, 30, 30);
		resultsPanel.add(r1);
		// Question 2 result
		r2 = new JLabel();
		r2.setBounds(200, 119, 30, 30);
		resultsPanel.add(r2);
		// Question 3 result
		r3 = new JLabel();
		r3.setBounds(200, 162, 30, 30);
		resultsPanel.add(r3);
		// Question 4 result
		r4 = new JLabel();
		r4.setBounds(200, 204, 30, 30);
		resultsPanel.add(r4);
		// Question 5 result
		r5 = new JLabel();
		r5.setBounds(200, 246, 30, 30);
		resultsPanel.add(r5);
		
		/*
		 * Win or lose label
		 */
		winOrLose = new JLabel("You Lose");
		winOrLose.setBounds(265, 204, 240, 41);
		winOrLose.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 28));
		resultsPanel.add(winOrLose);
		
		/*
		 * Exit button
		 */
		exit = new JButton("Exit PopQuiz 2");
		exit.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		exit.setBounds(445, 452, 120, 39);
		exit.addActionListener(new ExitButtonHandler());
		background.add(exit);
		
		/*
		 * Start a new game 
		 */
		playAgain = new JButton("Play Again");
		playAgain.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		playAgain.setBounds(315,250,120,39);
		playAgain.addActionListener(new PlayAgainHandler());
		resultsPanel.add(playAgain);
		
		/*
		 * Audio recorder
		 */
		recorder = new AudioApplet();
		recorder.init();
		recorder.setSize(350, 200);

		// Add welcome panel to background
		background.add(welcomePanel);
		welcomePanel.setVisible(true);

		// Add admin panel to background
		background.add(adminPanel);
		adminPanel.setVisible(false);

		// Add edit category panel to background
		background.add(editCategoryPanel);
		editCategoryPanel.setVisible(false);

		// Add edit question panel to background
		background.add(editQuestionPanel);
		editQuestionPanel.setVisible(false);

		// Add challenge panel to background
		background.add(challengePanel);
		challengePanel.setVisible(false);
		
		// Add game play panel to background
		background.add(instructionPanel);
		instructionPanel.setVisible(false);

		// Add game play panel to background
		background.add(gamePlayPanel);
		gamePlayPanel.setVisible(false);
		
		// Add game results panel to background
		background.add(resultsPanel);
		resultsPanel.setVisible(false);

		// Make background visible
		getContentPane().add(background);
		this.setSize(600, 500);
	}

	/**
	 * getPasswordField returns what the User entered into the password text field
	 * @return The password text field
	 */
	public JTextField getPasswordField () {
		return passwordField;
	}

	/**
	 * loadFriends loads the user's Facebook friends into an array.
	 * @throws IOException
	 */
	public void loadFriends() throws IOException {
		// Get data from "friends" parameter
		String friendParam = getParameter("friends");

		// Separate data into an array of friends
		String [] friends = friendParam.split(";");

		// Initialize array of size friend count
		int friendCount = friends.length;
		userFriends = new User[friendCount];

		// Add Friend's to the array one by one
		for (int i = 0; i < friendCount; i++) {
			userFriends[i] = getFriend(friends[i]);
		}
		// Sort friend list alphabetically
		Arrays.sort(userFriends);
	}

	/**
	 * Gets a User object with specified information.
	 * @param friend A string containing user's info
	 * @return User
	 */
	public User getFriend(String friend) {
		// Separate string into id and name
		String[] friendInfo = friend.split(",");
		// Return a new User object
		return new User(friendInfo[0], friendInfo[1]);
	}

	/**
	 * Gets a category name from an unformatted string
	 * @param cat 		A string from categoryList
	 * @return The Category name
	 */
	private String getName (String cat) {
		// Get the unformatted category name
		String[] parts = new String[2];
		parts = cat.split(" = ");
		// Return the name of the category
		return parts[0];
	}


	/////////////////////////////////////////////

	////////// PRIVATE BUTTON HANDLERS //////////

	/**
	 * PlayGameHandler opens up the challenge panel when the User clicks
	 * "play game", and makes the welcome panel invisible.
	 */
	private class PlayGameHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Make the welcome panel invisible
			welcomePanel.setVisible(false);
			welcomeMessage.setVisible(false);

			// Make the challenge panel visible
			challengePanel.setVisible(true);

			// Print result
			System.out.println("Opening challenge screen...");
		}
	}

	/**
	 * SelectSkinHandler opens up an option panel when the User clicks
	 * "Select a Skin" and allows them to pick Blue, Green, or Purple.
	 */
	private class SelectSkinHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Create an option panel to place the buttons in
			skinOptionPanel = new JPanel(new GridLayout(0, 3));

			// Add the buttons to the option panel
			skinOptionPanel.add(blue);
			skinOptionPanel.add(green);
			skinOptionPanel.add(purple);

			// Show skin selector pop-up option panel
			int result = JOptionPane.showConfirmDialog(null, skinOptionPanel, "Select a Skin", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				// If blue is selected, set skin to blue and print result
				if (blue.isSelected()) {
					background.setBackground(new Color(0x00,0x00,0xD9));
					greenSkin.setVisible(false);
					purpleSkin.setVisible(false);
					System.out.println("Changing Skin to Blue");
					writeSkin("blue");
				}
				// Else if green is selected, set skin to green and print result
				else if (green.isSelected()) {
					background.setBackground(new Color(0x00,0xD9,0x00));
					greenSkin.setVisible(true);
					purpleSkin.setVisible(false);
					System.out.println("Changing Skin to Green");
					writeSkin("green");
				}
				// Else if purple is selected, set skin to purple and print result
				else if (purple.isSelected()){
					background.setBackground(new Color(0x46,0x00,0x8C));
					greenSkin.setVisible(false);
					purpleSkin.setVisible(true);
					System.out.println("Changing Skin to Purple");
					writeSkin("purple");
				}
				// Else nothing was selected, print error message
				else {
					System.out.println("Error: Invalid Skin Selection");
				}
			}
		}
	}

	/**
	 * EnterPasswordHandler authenticates the password when User clicks "Enter".
	 * If password is correct, this opens up the admin panel and makes the
	 * welcome panel invisible, otherwise it displays an error message.
	 */
	private class EnterPasswordHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Get password attempt and clear password field
			String pwdAttempt = getPasswordField().getText();
			passwordField.setText("");
			// Print attempt and stored password for testing
			System.out.println("Attempt: " + pwdAttempt);
			System.out.println("Stored Password: " + database.getPassword());

			// Check if the password attempt is empty or not the correct length
			if (!(pwdAttempt.equals(null) || pwdAttempt.length() < 3 || pwdAttempt.length() > 6)) {
				// Get the stored password
				String storePwd = database.getPassword();
				
				// Check if the password attempt matches the saved password
				if(storePwd!=null && storePwd.equals(pwdAttempt)){
					// Clear password text field
					passwordField.setText(null);
					passwordField.repaint();

					// Reset login attempts to 0
					pwdAttemptCount = 0;

					// Make welcome screen invisible
					welcomePanel.setVisible(false);
					welcomeMessage.setVisible(false);

					// Make admin screen visible
					adminPanel.setVisible(true);
					ArrayList<String> QAList = database.getSecurityQA();

					// If no security question and answer is saved
					if (QAList==null || QAList.isEmpty()) {
						Boolean check = false;
						// Create a new option pane containing security questions
						JComboBox<String> questionBox = new JComboBox<String>();
						questionBox.addItem(PWQuestions[0]);
						questionBox.addItem(PWQuestions[1]);
						questionBox.addItem(PWQuestions[2]);
						JPanel entryPW  = new JPanel(new GridLayout(0, 3));
						JTextField PWText = new JTextField();
						entryPW.add(questionBox);
						entryPW.add(PWText);
						// Force admin user to answer security question until a valid answer is saved
						while (check == false){
							// Show security question selector option pane
							int result = JOptionPane.showConfirmDialog(null, entryPW, "Enter new recovery answer.", JOptionPane.OK_CANCEL_OPTION);
							if (result == JOptionPane.OK_OPTION) {
								String answerQA = PWText.getText();
								if (answerQA.length() >= 1 && answerQA.length() <= 30){
									check = true;
									int PWIndex = questionBox.getSelectedIndex();
									// Save security question and answer
									database.saveSecurityQA(PWQuestions[PWIndex], answerQA);
								}
							}
						}
					}
					// Print result
					System.out.println("Opening admin screen...");
				}
				else {
					// Increment login attempt count by 1
					pwdAttemptCount++;

					// If login attempt count is greater than or equal to 3, then reset password to default
					if (pwdAttemptCount >= 3){
						// Get security question and answer
						ArrayList<String> securityQA = database.getSecurityQA();
						
						// Check if there's no security question set yet
						if (securityQA.isEmpty()) {
							// Reset password to default
							database.changePassword("cs2212");
							JOptionPane.showMessageDialog(
									welcomePanel,
									"You haven't answered a security question yet. \n"
											+ "Password has been reset to the default password.",
											"Error", JOptionPane.DEFAULT_OPTION);
						}
						else {
							// Prompt user to enter an answer to the security question
							String answerEntered = JOptionPane.showInputDialog("Please answer the following question.",
									securityQA.get(0));
							// If they answered correctly
							if (securityQA.get(1).equalsIgnoreCase(answerEntered)) {
								// Prompt to enter a new password, and save it
								String newPassword = JOptionPane.showInputDialog("Please enter a new password.",
										"Enter a new password: ");
								database.changePassword(newPassword);
							}
							// Else, if answered incorrectly
							else {
								// Reset password to default
								database.changePassword("cs2212");
								JOptionPane.showMessageDialog(welcomePanel,
										"Incorrect security answer. Password has been reset to the default password.",
										"Error", JOptionPane.DEFAULT_OPTION);
							}
						}
						// Reset password attempt count
						pwdAttemptCount = 0;
					}
					// Display incorrect password message
					else{
						JOptionPane.showMessageDialog(welcomePanel, "Incorrect password, please try again");
					}
				}
			}
			// Display password attempt error message
			else {
				JOptionPane.showMessageDialog(welcomePanel, "Password must be 3 to 6 characters");
			}
		}
	}

	/**
	 * BulkloadHandler opens up an option panel when the User clicks "Access Bulkload"
	 * and allows them to enter a URL location of a bulklaod file to load.
	 */
	private class BulkloadHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// Open up an option pane for the User to enter a URL to bulk-load from
			JTextField bulkloadURL = new JTextField(13);
			JPanel optionPane = new JPanel(new GridLayout(0,2));
			optionPane.add(bulkloadURL);
			
			// Show new access bulk-load pop-up option pane
			int result = JOptionPane.showConfirmDialog(null, optionPane, "Enter a URL for Bulkload", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				// Get user input URL and print for reference
				String url = bulkloadURL.getText();
				System.out.println(url);
				
				// Make sure input is a valid URL
				if (exists(url)) {
					// If URL is valid, read bulk file
					database.readFromBulk(url);
					// Load file into temporary data file
					String fileURL = "temp.txt";
					String[] validation = database.loadBulk(fileURL);
					// If file passes validation, load file into database
					if (validation[0].equals("")){
						categoryList.setListData(database.listCategories());
					}
					// Else file must be invalid, display pop-up message
					else{
						if (!(validation[1].equals("-1"))) JOptionPane.showMessageDialog(background, validation[0] + " LINE: " + validation[1]);
						else JOptionPane.showMessageDialog(background, validation[0]);
					}
				}
				// If URL doesn't exist, show error message
				else {
					JOptionPane.showMessageDialog(welcomePanel, "Enter a valid URL!");
				}
			}
		}	
	}
	
	/**
	 * Modified from a method obtained from StackOverflow.
	 * @author Jigar Joshi
	 * @param URLName
	 * @return true if the URL exists, false otherwise
	 */
	private boolean exists(String URLName){
		// Try to connect to given URL
	    try {
	      HttpURLConnection.setFollowRedirects(false);
	      HttpURLConnection con =
	         (HttpURLConnection) new URL(URLName).openConnection();
	      con.setRequestMethod("HEAD");
	      // If connection returns with okay status code, then URL exists
	      return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
	    }
	    // If connection fails, print result and return false
	    catch (Exception e) {
	       e.printStackTrace();
	       return false;
	    }
	  } 

	/**
	 * ChangePasswordHandler opens up an option panel when the user clicks "Change Password"
	 * and allows them to enter a new admin password to save to the server.
	 */
	private class ChangePasswordHandler implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// Open up an option pane for the User to enter a new password
			JTextField newPassword = new JTextField(13);
			JPanel optionPane = new JPanel(new GridLayout(0,2));
			optionPane.add(newPassword);
			
			// Show change password pop-up option pane
			int result = JOptionPane.showConfirmDialog(null, optionPane, "Enter New Password", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION && newPassword.getText().length() >= 3 && newPassword.getText().length() <= 6) {
				database.changePassword(newPassword.getText());
				// Print result
				System.out.println("New Password: " + database.getPassword());
			}
			// Else print an error message
			else {
				JOptionPane.showMessageDialog(challengePanel, "Enter a valid password!");
				System.out.println("Error: Invalid Password");
			}
		}
	}

	/**
	 * SetChallengeTimeHandler opens up an option panel when the user clicks "Set Challenge Time"
	 * and allows them to enter a new time limit for accepting challenges.
	 */
	private class SetChallengeTimeHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			// Open up an option pane for the user to enter a new challenge time limit
			JTextField newChallengeTime = new JTextField(13);
			JPanel optionPane = new JPanel(new GridLayout(0,2));
			optionPane.add(newChallengeTime);
			
			// Show set challenge timer pop-up option pane
			int result = JOptionPane.showConfirmDialog(null, optionPane, "Enter A New Challenge Time (Days)", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				// Check format of time
				try {
					int checkInt = Integer.parseInt(newChallengeTime.getText());
					// Make sure timer is more than 1 day
					if (checkInt < 1) {
						// If not, show error message and print result
						JOptionPane.showMessageDialog(challengePanel, "Enter a valid time!");
						System.out.println("Error: Invalid Time");
					}
					else {
						database.changeChallengeTimer(newChallengeTime.getText());
						// Print result
						System.out.println("New Challenge Time: " + newChallengeTime.getText());
					}
				}
				// Else shown an error message and print result
				catch (NumberFormatException e){
					JOptionPane.showMessageDialog(challengePanel, "Enter a valid time!");
					System.out.println("Error: Invalid Time");
				}
			}
		}
	}

	/**
	 * BackToMainHandler opens up the welcome panel when the User
	 * clicks "Back to Main Menu", and makes the admin panel invisible.
	 */
	private class BackToMainHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Saved data to server
			database.writeToFile();
			
			// Make admin screen invisible
			adminPanel.setVisible(false);

			// Make welcome screen visible
			welcomePanel.setVisible(true);
			welcomeMessage.setVisible(true);

			// Print result
			System.out.println("Opening welcome screen...");
		}
	}

	/**
	 * AddCategoryHandler opens up an option panel when the User clicks
	 * "Add" and allows them to add a category to the game.
	 */
	private class AddCategoryHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			// Create pop-up option pane for User to add Category
			JPanel optionPane = new JPanel(new GridLayout(0, 4));

			// Create a text field for User to input category name
			JTextField inputNewCategory = new JTextField(15);

			// Create a button group for User to input category difficulty
			ButtonGroup btnGroup = new ButtonGroup();
			JRadioButton easy = new JRadioButton("Easy", true);
			JRadioButton medium = new JRadioButton("Medium", true);
			JRadioButton hard = new JRadioButton("Hard", true);

			// Add radio buttons to button group
			btnGroup.add(easy);
			btnGroup.add(medium);
			btnGroup.add(hard);

			// Add text field and radio buttons to option pane
			optionPane.add(inputNewCategory);
			optionPane.add(easy);
			optionPane.add(medium);
			optionPane.add(hard);

			// Show add new category pop-up option pane
			int result = JOptionPane.showConfirmDialog(null, optionPane, "Add New Category", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				// Get new category name
				String categoryName = inputNewCategory.getText();

				// Check if category name is correct length
				if (categoryName.length() >= 1 && categoryName.length() <= 30) {
					// Default difficulty level is easy
					String categoryLevel = "Easy";
					// Check which difficulty level was actually selected
					if (easy.isSelected()) {
						categoryLevel = "Easy";
					}
					else if (medium.isSelected()) {
						categoryLevel = "Medium";
					}
					else if (hard.isSelected()) {
						categoryLevel = "Hard";
					}
					else {
						System.out.println("Error: Invalid Difficulty Level");
					}
					
					// Try to add new category to database
					try {
						database.addCategory(categoryName, categoryLevel);
						database.writeToFile();
					}
					// If error adding category, show message and print result
					catch (Exception e) {
						JOptionPane.showMessageDialog(background, "Category already exists");
						System.out.println("Error: Category exists");
					}
				}
				// Else show an error message and print result
				else {
					JOptionPane.showMessageDialog(background, "Category name must be between 1 to 30 characters.");
					System.out.println("Error: Invalid Category name");
				}
			}
			// Reload category list
			categoryList.setListData(database.listCategories());
			adminPanel.repaint();
		}
	}

	/**
	 * EditCategoryHandler opens up the edit category panel when the User
	 * selects a category and clicks "Edit", and makes the admin panel invisible.
	 */
	private class EditCategoryHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Get selected category
			int catIndex = categoryList.getSelectedIndex();

			// Make sure User has selected a Category to edit
			if (catIndex != NOT_SELECTED) {
				// Get selected Category
				String categoryName = getName(categoryList.getSelectedValue());
				selectedCategory = database.getCategory(categoryName);

				// Check the difficulty level of the category
				if (selectedCategory.getLevel().equals("Easy")) {
					easy.setSelected(true);
				}
				else if (selectedCategory.getLevel().equals("Medium")) {
					medium.setSelected(true);
				}
				else {
					hard.setSelected(true);
				}
				
				// Set the question list with the selected category's questions
				questionList.setListData(selectedCategory.listQuestions());
				editCategoryPanel.add(questionScroller);

				// Make admin screen invisible
				adminPanel.setVisible(false);

				// Make edit category screen visible
				editCategoryPanel.setVisible(true);
				editCategoryPanel.repaint();

				// Print result
				System.out.println("Opening edit category screen...");	
			}
			// If not, display a warning message
			else {	
				JOptionPane.showMessageDialog(challengePanel, "Select a Category to Edit!");
				System.out.println("Error: No category selected");
			}
		}
	}

	/**
	 * RemoveCategoryHandler removes a category from the game.
	 */
	private class RemoveCategoryHandler implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// Get selected category
			int catIndex = categoryList.getSelectedIndex();

			// Make sure User has selected a Category to remove
			if (catIndex != NOT_SELECTED) {

				JPanel optionPane = new JPanel(new GridLayout(0, 1));
				JLabel deleteLabel = new JLabel("Are you sure you want to delete?");
				optionPane.add(deleteLabel);

				// Show confirmation pop-up option panel
				int result = JOptionPane.showConfirmDialog(null, optionPane, "Remove Category", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {

					if(categoryList != null && categoryList.getSelectedValue()!= null) {
						String toDelete = getName(categoryList.getSelectedValue());
						// Try to delete category from database
						try {
							database.deleteCategory(toDelete);
							database.writeToFile();
						}
						// If error deleting category, print result
						catch (CategoryException e) {
							System.out.println("Error Deleting Category");
						}
					}
				}
				// Reset Category list
				categoryList.setListData(database.listCategories());
				adminPanel.repaint();
			}
			// If not, display a warning message
			else {	
				JOptionPane.showMessageDialog(challengePanel, "Select a Category to Remove!");
				System.out.println("Error: No category selected");
			}
		}
	}

	/**
	 * SetDifficultyHandler retrieves the selected difficulty when the User clicks
	 * "Set Difficulty" and allows them to change the difficulty of a category.
	 */
	private class SetDifficultyHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Get selected Category
			String categoryName = getName(categoryList.getSelectedValue());
			Category selectedCategory = database.getCategory(categoryName);

			// Check if easy is selected
			if (easy.isSelected()) {
				database.changeCategoryLevel(categoryName, "Easy");
				selectedCategory.setLevel("Easy");
			}
			// Else check if medium is selected
			else if (medium.isSelected()) {
				database.changeCategoryLevel(categoryName, "Medium");
				selectedCategory.setLevel("Medium");
			}
			// Else hard must be selected
			else {
				database.changeCategoryLevel(categoryName, "Hard");
				selectedCategory.setLevel("Hard");
			}

			// Reset Category list
			categoryList.setListData(database.listCategories());
			adminPanel.repaint();
		}	
	}

	/**
	 * BackToAdminHandler opens up the admin panel when the User clicks
	 * "Back to Admin Menu", and makes the edit category panel invisible.
	 */
	private class BackToAdminHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Saved data to server
			database.writeToFile();
			
			// Make edit category screen invisible
			editCategoryPanel.setVisible(false);

			// Make admin screen visible
			adminPanel.setVisible(true);

			// Print result
			System.out.println("Opening admin screen...");
		}
	}

	/**
	 * AddQuestionHandler opens up an option panel when the User clicks
	 * "Add" and allows them to add a question to the selected category.
	 */
	private class AddQuestionHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Create pop-up option pane for user to add Question
			JPanel optionPane = new JPanel(new GridLayout(0, 3));
			// Create a button group for user to input Question type
			ButtonGroup btnGroup = new ButtonGroup();
			JRadioButton shortAnswer = new JRadioButton("Short Answer", true);
			JRadioButton multipleChoice = new JRadioButton("Multiple Choice", true);
			JRadioButton voiceQuestion = new JRadioButton("Voice Question", true);
			// Add type choices to button group
			btnGroup.add(shortAnswer);
			btnGroup.add(multipleChoice);
			btnGroup.add(voiceQuestion);
			// Add components option pane
			optionPane.add(shortAnswer);
			optionPane.add(multipleChoice);
			optionPane.add(voiceQuestion);

			// Show add question pop-up option panel
			int result = JOptionPane.showConfirmDialog(editCategoryPanel, optionPane, "Select Question Type", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				// Create pop-up option pane for user to add Question
				JPanel newQuestionPane;
				// Create a text field for user to input description
				JTextField inputDescription = new JTextField("Enter description here");
				inputDescription.setEditable(true);
				// Create a text field for user to input answer
				JTextField inputAnswer = new JTextField("Enter answer here");
				inputAnswer.setEditable(true);

				// Check if multiple choice is selected
				if (multipleChoice.isSelected()) {
					// Create pop-up new question pane for user to add Question
					newQuestionPane = new JPanel(new GridLayout(0, 6));
					// Create a text field for user to input choice 1
					JTextField inputChoice1 = new JTextField("Enter choice 1 here");
					inputAnswer.setEditable(true);
					// Create a text field for user to input choice 2
					JTextField inputChoice2 = new JTextField("Enter choice 2 here");
					inputAnswer.setEditable(true);
					// Create a text field for user to input choice 3
					JTextField inputChoice3 = new JTextField("Enter choice 3 here");
					inputAnswer.setEditable(true);
					// Add all components to new question pane
					newQuestionPane.add(inputDescription);
					newQuestionPane.add(inputAnswer);
					newQuestionPane.add(inputChoice1);
					newQuestionPane.add(inputChoice2);
					newQuestionPane.add(inputChoice3);

					// Show add multiple choice question pop-up option panel
					int action = JOptionPane.showConfirmDialog(editCategoryPanel, newQuestionPane, "Add New Question", JOptionPane.OK_CANCEL_OPTION);
					if (action == JOptionPane.OK_OPTION) {
						// Add components to input question pane
						String description = inputDescription.getText();
						String answer = inputAnswer.getText();
						String choice1 = inputChoice1.getText();
						String choice2 = inputChoice2.getText();
						String choice3 = inputChoice3.getText();
						String [] choices = new String [] {choice1, choice2, choice3};

						// Check Question description length
						if (description.length() < 1 || description.length() > 200) {
							JOptionPane.showMessageDialog(editCategoryPanel, "Question descripton must be 1 to 200 characters!");
							System.out.println("Error: Invalid Question description");
						}
						// Check Question answer length
						else if (answer.length() < 1 || answer.length() > 100) {
							JOptionPane.showMessageDialog(editCategoryPanel, "Question answer must be 1 to 100 characters!");
							System.out.println("Error: Invalid Question answer");
						}
						// Check Question answer length
						else if (choice1.length() < 1 || choice1.length() > 100) {
							JOptionPane.showMessageDialog(editCategoryPanel, "Question choice must be 1 to 100 characters!");
							System.out.println("Error: Invalid Question answer");
						}
						// Check Question answer length
						else if (choice2.length() < 1 || choice2.length() > 100) {
							JOptionPane.showMessageDialog(editCategoryPanel, "Question choice must be 1 to 100 characters!");
							System.out.println("Error: Invalid Question answer");
						}
						// Check Question answer length
						else if (choice3.length() < 1 || choice3.length() > 100) {
							JOptionPane.showMessageDialog(editCategoryPanel, "Question choice must be 1 to 100 characters!");
							System.out.println("Error: Invalid Question answer");
						}
						//Check for duplicate answers
						else if (choice1.equals(choice2) || choice1.equals(choice3) || choice2.equals(choice3) || answer.equals(choice1) ||
								answer.equals(choice2) || answer.equals(choice3)){
							JOptionPane.showMessageDialog(editCategoryPanel, "Question cannot have duplicate answers!");
							System.out.println("Error: Invalid Question answer");
						}
						// Else Question input must be okay, add question
						else {
							// Get selected Category
							String categoryName = getName(categoryList.getSelectedValue().toString());
							Category selectedCategory = database.getCategory(categoryName);
							// Create a new short answer Question
							MultipleChoiceQuestion newQuestion = new MultipleChoiceQuestion (selectedCategory, description, answer, choices);
							selectedCategory.addQuestion(newQuestion);
							// Reset Question list
							questionList.setListData(selectedCategory.listQuestions());
							editCategoryPanel.repaint();
							// Print result
							System.out.println("Success adding Question: " + description);
						}
					}
				}
				// Else check if voice question is selected
				else if (voiceQuestion.isSelected()) {
					// Create pop-up new question pane for user to add Question
					newQuestionPane = new JPanel(new GridLayout(0, 1));
					// Add all components to new question pane
					newQuestionPane.add(inputAnswer);
					
					// Show add voice question pop-up option panel
					int action = JOptionPane.showConfirmDialog(editCategoryPanel, newQuestionPane, "Add New Question", JOptionPane.OK_CANCEL_OPTION);
					if (action == JOptionPane.OK_OPTION) {
						// Get answer from option pane
						String answer = inputAnswer.getText();
						
						// Check Question answer length
						if (answer.length() < 1 || answer.length() > 100) {
							JOptionPane.showMessageDialog(editCategoryPanel, "Question answer must be 1 to 100 characters!");
							System.out.println("Error: Invalid Question answer");
						}
						// Else answer input must be okay, continue to add voice question
						else {
							JOptionPane recorderPane = new JOptionPane (recorder, JOptionPane.OK_CANCEL_OPTION);
							recorderPane.setLayout(null);
							recorderPane.setSize(350, 200);
							// Record audio description
							action = JOptionPane.showConfirmDialog(editCategoryPanel, recorderPane, "Record Description", JOptionPane.OK_CANCEL_OPTION);
							
							if (action == JOptionPane.OK_OPTION) {
								// Get selected Category
								String categoryName = getName(categoryList.getSelectedValue().toString());
								Category selectedCategory = database.getCategory(categoryName);
								// Create a new short answer Question
								Question newQuestion = new Question (selectedCategory, answer, answer, SHORT_ANSWER);
								selectedCategory.addQuestion(newQuestion);
								// Reset Question list
								questionList.setListData(selectedCategory.listQuestions());
								editCategoryPanel.repaint();
								// Print result
								System.out.println("Success adding Question: " + answer);
							}
						}
					}
				}
				// Else short answer must be selected
				else {
					// Create pop-up new question pane for user to add Question
					newQuestionPane = new JPanel(new GridLayout(0, 2));
					// Add all components to new question pane
					newQuestionPane.add(inputDescription);
					newQuestionPane.add(inputAnswer);

					// Show add short answer question pop-up option panel
					int action = JOptionPane.showConfirmDialog(editCategoryPanel, newQuestionPane, "Add New Question", JOptionPane.OK_CANCEL_OPTION);
					if (action == JOptionPane.OK_OPTION) {
						// Add components to input question pane
						String description = inputDescription.getText();
						String answer = inputAnswer.getText();

						// Check Question description length
						if (description.length() < 1 || description.length() > 200) {
							JOptionPane.showMessageDialog(editCategoryPanel, "Question descripton must be 1 to 200 characters!");
							System.out.println("Error: Invalid Question description");
						}
						// Check Question answer length
						else if (answer.length() < 1 || answer.length() > 100) {
							JOptionPane.showMessageDialog(editCategoryPanel, "Question answer must be 1 to 100 characters!");
							System.out.println("Error: Invalid Question answer");
						}
						// Else Question input must be okay, add question
						else {
							// Get selected Category
							String categoryName = getName(categoryList.getSelectedValue().toString());
							Category selectedCategory = database.getCategory(categoryName);
							// Create a new short answer Question
							Question newQuestion = new Question (selectedCategory, description, answer, SHORT_ANSWER);
							selectedCategory.addQuestion(newQuestion);
							// Reset Question list
							questionList.setListData(selectedCategory.listQuestions());
							editCategoryPanel.repaint();
							// Print result
							System.out.println("Success adding Question: " + description);
						}
					}
				}	
			}
		}
	}

	/**
	 * EditQuestionHandler opens up the edit question panel when the User
	 * selects a question and clicks "Edit", and makes the edit category panel invisible.
	 */
	private class EditQuestionHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Get selected Category
			String categoryName = getName(categoryList.getSelectedValue());
			Category selectedCategory = database.getCategory(categoryName);

			// Get selection index from question list
			int questionIndex = questionList.getSelectedIndex();

			// If category is empty, show pop up warning and print result
			if (selectedCategory.isEmpty()) {
				JOptionPane.showMessageDialog(editCategoryPanel, "Category is Empty!");
				System.out.println("Error: Category is Empty");
			}
			// Make sure the current User selected a Question to edit
			else if (questionIndex != NOT_SELECTED) {
				// Check if the selected question is multiple choice
				if (selectedCategory.getQuestions()[questionIndex].getType().equals("MultipleChoice")) {
					// Add selected question description, answer, and choices to edit question panel
					currentMC = selectedCategory.getQuestions()[questionIndex];
					currentQuestion = currentMC;
					descriptionPane.setText(currentMC.getDescription());
					answerBox.addItem(currentMC.getAnswer());
					answerBox.addItem(((MultipleChoiceQuestion) currentMC).getDecoy1());
					answerBox.addItem(((MultipleChoiceQuestion) currentMC).getDecoy2());
					answerBox.addItem(((MultipleChoiceQuestion) currentMC).getDecoy3());
				}
				// Else if not multiple choice, must be only one answer
				else {
					// Add selected question description and answer to edit question panel
					Question [] questions = selectedCategory.getQuestions();
					currentQuestion = questions[questionIndex];
					descriptionPane.setText(currentQuestion.getDescription());
					answerBox.addItem(currentQuestion.getAnswer());
				}

				// Make edit category screen invisible
				editCategoryPanel.setVisible(false);
				// Make edit question screen visible
				editQuestionPanel.setVisible(true);
				// Print result
				System.out.println("Opening edit question screen...");
			}
			// If not, display a warning message
			else {	
				JOptionPane.showMessageDialog(editCategoryPanel, "Select a Question to Edit!");
				System.out.println("Error: No Question selected");
			}
		}
	}

	/**
	 * RemoveQuestionHandler removes a question from the selected category.
	 */
	private class RemoveQuestionHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Get selected Category
			String categoryName = getName(categoryList.getSelectedValue());
			Category selectedCategory = database.getCategory(categoryName);

			// Get selection index from question list
			int questionIndex = questionList.getSelectedIndex();

			// If category is empty, show pop up warning and print result
			if (selectedCategory.isEmpty()) {
				JOptionPane.showMessageDialog(editCategoryPanel, "Category is Empty!");
				System.out.println("Error: Category is Empty");
			}
			// Make sure the current User selected a Question to edit
			else if (questionIndex != NOT_SELECTED) {
				// Add selected question description and answer to edit question panel
				Question [] questions = selectedCategory.getQuestions();
				Question current = questions[questionIndex];
				selectedCategory.deleteQuestion(current);

				// Reset Question list
				questionList.setListData(selectedCategory.listQuestions());
				editCategoryPanel.repaint();

				// Print result
				System.out.println("Deleted Question: " + current.getDescription());
			}
			// If not, display a warning message
			else {	
				JOptionPane.showMessageDialog(editCategoryPanel, "Select a Question to Delete!");
				System.out.println("Error: No Question selected");
			}
		}
	}
	
	/**
	 * ChangeQuestionCategoryHandler opens up a list of categories and allows the
	 * user to change the category that a question belongs to.
	 */
	private class ChangeQuestionCategoryHandler implements ActionListener {
		
		public void actionPerformed (ActionEvent arg0) {
			// Get temporary Category list
			JList<String> tempCatList = new JList<String>();

			// Check to make sure there are categories to load
			if (database.catQuestionCount() > 0) {
				// If there are categories to load, get them
				tempCatList.setListData(database.listCategoriesChallenge());
				JScrollPane scroller = new JScrollPane(tempCatList);
				// Make User select a Category
				JPanel optionPane = new JPanel();
				optionPane.add(scroller);

				// Make user select a category
				int result = JOptionPane.showConfirmDialog(challengePanel, optionPane, "Select a Category", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {
					// Check to make sure the User selected a category
					if (tempCatList.getSelectedIndex() != NOT_SELECTED) {
						// Get selected category
						String categoryName = getName(tempCatList.getSelectedValue());
						// Try to change the category of the selected question
						try {
							database.changeCategoryOfQuestion(categoryName, currentQuestion);
							System.out.println("Success! New category is " + categoryName);
						}
						catch (WrongNameException e) {
							System.out.println(e.getMessage());
						}
					}
					// If not, display a warning message
					else {

						JOptionPane.showMessageDialog(challengePanel, "Select a Category!");
						System.out.println("Error: No category selected");
					}
				}
			}
		}
	}

	/**
	 * BackToEditCategoryHandler opens up the edit category panel when the User
	 * clicks "Back to Edit Category", and makes the edit question panel invisible.
	 */
	private class BackToEditCategoryHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Remove all the questions from the answer box
			answerBox.removeAllItems();
			
			// Make edit question screen invisible
			editQuestionPanel.setVisible(false);

			// Make edit category screen visible
			editCategoryPanel.setVisible(true);
			questionList.setListData(selectedCategory.listQuestions());
			// Print result
			System.out.println("Opening edit category screen...");
		}
	}
	
	/** 
	 * SaveQuestionHandler saves the changes the user has made to the question
	 */
	private class SaveQuestionHandler implements ActionListener {

		public void actionPerformed ( ActionEvent arg0) {
			// Get selected Category
			String categoryName = getName(categoryList.getSelectedValue());
			Category selectedCategory = database.getCategory(categoryName);
			// Get new description and answer from the user
			String newDesc = descriptionPane.getText();

			System.out.println(answerBox.getEditor().getItem().toString());
			// Get the Question object and modify it
			int questionIndex = questionList.getSelectedIndex();
			currentQuestion = selectedCategory.getQuestions()[questionIndex];
			// Set Question description
			currentQuestion.setDescription(newDesc);

			// Check if it's a multiple choice question
			if (currentQuestion.getType().equals("MultipleChoice")) {
				// Declaring local multiple choice question variable
				MultipleChoiceQuestion mc = (MultipleChoiceQuestion) currentQuestion;

				if (remember == NOT_SELECTED) {
					// If no question or answer is selected, do nothing
				}
				else if (remember == 0) {
					// Answer selected, get it
					String newAns = answerBox.getEditor().getItem().toString();
					// Save new answer to the multiple choice question
					mc.setAnswer(newAns);
					// Save question
					selectedCategory.saveMCQuestion(questionIndex, mc);
				}
				else if (remember == 1) {
					// Choice 1 selected, get it
					String choice1 = answerBox.getEditor().getItem().toString();
					// Save new choice to the multiple choice question
					mc = (MultipleChoiceQuestion) currentQuestion;
					mc.setDecoy1(choice1);
					// Save question
					selectedCategory.saveMCQuestion(questionIndex, mc);
				}
				else if (remember == 2) {
					// Choice 2 selected, get it
					String choice2 = answerBox.getEditor().getItem().toString();
					// Save new choice to the multiple choice question
					mc = (MultipleChoiceQuestion) currentQuestion;
					mc.setDecoy2(choice2);
					// Save question
					selectedCategory.saveMCQuestion(questionIndex, mc);
				}	
				else {
					// Choice 3 selected, get it
					String choice3 = answerBox.getEditor().getItem().toString();
					// Save new choice to the multiple choice question
					mc = (MultipleChoiceQuestion) currentQuestion;
					mc.setDecoy3(choice3);
					// Save question
					selectedCategory.saveMCQuestion(questionIndex, mc);
				}
			}
			else {
				// Else if short answer or voice question, just get the answer and save it
				String newAns = answerBox.getEditor().getItem().toString();
				System.out.println(newAns);
				currentQuestion.setAnswer(newAns);
				// Save question
				selectedCategory.saveQuestion(questionIndex, currentQuestion);
			}
			// Reset Question list and save to server
			questionList.setListData(selectedCategory.listQuestions());
			editCategoryPanel.repaint();
			database.writeToFile();

			// Remove all the questions from the answer box
			answerBox.removeAllItems();

			// Make edit question screen invisible
			editQuestionPanel.setVisible(false);

			// Make edit category screen visible
			editCategoryPanel.setVisible(true);
			questionList.setListData(selectedCategory.listQuestions());
			// Print result
			System.out.println("Question saved, opening edit category screen...");
		}
	}

	/**
	 * BackToWelcomeHandler opens up the welcome panel when the User clicks
	 * "Back to Main Menu", and makes the challenge panel invisible.
	 */
	private class BackToWelcomeHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Make challenge screen invisible
			challengePanel.setVisible(false);

			// Make welcome screen visible
			welcomePanel.setVisible(true);
			welcomeMessage.setVisible(true);
			// Print result
			System.out.println("Opening welcome screen...");
		}
	}

	/**
	 * ChallengeFriendHandler creates a new Challenge and opens up the instruction 
	 * screen if successful, otherwise a warning message is displayed.
	 */
	private class ChallengeFriendHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Make sure the current User selected a Friend to challenge
			if (friendList.getSelectedIndex() != NOT_SELECTED) {
				// Get user selected friend
				int friendIndex = friendList.getSelectedIndex();
				User selectedFriend = userFriends[friendIndex];

				// Get temporary Category list
				JList<String> tempCatList = new JList<String>();

				// Check to make sure there are categories to load
				
				if (database.catQuestionCount() > 0) {
					tempCatList.setListData(database.listCategoriesChallenge());

					JScrollPane scroller = new JScrollPane(tempCatList);
					// Make User select a Category
					JPanel optionPane = new JPanel();
					optionPane.add(scroller);

					// Show select a category for challenge pop-up option panel
					int result = JOptionPane.showConfirmDialog(challengePanel, optionPane, "Select a Category", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						// Check to make sure the User selected a Category
						if (tempCatList.getSelectedIndex() != NOT_SELECTED) {
							// Get selected Category
							String categoryName = getName(tempCatList.getSelectedValue());
							Category selectedCategory = database.getCategory(categoryName);

							// Create a new challenge, game, and game user interface
							newChallenge = new Challenge (path,currentUser, selectedFriend, selectedCategory);
							game = new Game (newChallenge);

							// Make challenge screen invisible
							challengePanel.setVisible(false);

							// Make instruction screen visible
							instructionPanel.setVisible(true);

							// Print result
							System.out.println("Challenge successful. Opening game instruction screen...");
						}
						// If not, display a warning message
						else {
							JOptionPane.showMessageDialog(challengePanel, "Select a Category!");
							System.out.println("Error: Invalid Category selection, Challenge unsuccesful");
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(challengePanel, "No categories with 5 or more questions exist.");
				}
			}
			// If not, display a warning message
			else {
				JOptionPane.showMessageDialog(challengePanel, "Select a Friend to Challenge!");
				System.out.println("Error: No Friend selected, Challenge unsuccesful");
			}
		}
	}
	
	/**
	 * PendingChallengeHandler creates a pending challenge list and starts a new
	 * game if the user chooses to, otherwise a warning message is displayed.
	 */
	private class PendingChallengeHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			
			// Make a temporary challenge list with scroll bar
			temp = loadChallenges();
			JScrollPane scroller = new JScrollPane(temp);
			JPanel optionPane = new JPanel();
			optionPane.add(scroller);
			
			// Show select from pending challenges pop-up option panel
			int result = JOptionPane.showConfirmDialog(challengePanel, optionPane, "Select a Challenge", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				whichChallenge = temp.getSelectedIndex();
				
				// Make sure user selected a challenge
				if(whichChallenge != NOT_SELECTED) {
					Challenge accepted = temp.getSelectedValue();
					newChallenge = accepted;
					game = new Game (newChallenge);

					// Make challenge screen invisible
					challengePanel.setVisible(false);

					// Make instruction screen visible
					instructionPanel.setVisible(true);

					// Print result
					System.out.println("Accepted challenge. Opening game instruction screen...");
				}
				// Else show error message and print result
				else {
					JOptionPane.showMessageDialog(challengePanel, "Select a challenge to play!");
					System.out.println("Error: No challenge selected, game unsuccesful");
				}
			}
		}
	}
	
	/**
	 * Loads pending challenges from the user's pending challenge file
	 * @return  A list of pending challenges
	 */
	@SuppressWarnings("deprecation")
	public JList<Challenge> loadChallenges() {
		// Create an array of challenges
		challengeArray = new Challenge [100];
		int index = 0;
		numChallenges = 0;
		expiryDates = new String [100];
		String challengeInfo;
		// Try to load challenges from file
		try {
			URL url = new URL(path + "users/" + currentUser.getID() + "/pending.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			// Read from file line by line until end of file is reached
			while ((challengeInfo = in.readLine()) != null) {
				String [] parts = challengeInfo.split(",");
				// Make sure line has data
				if (parts.length > 1) {
					// Compare right now to challenge due date
					Calendar rightnow = Calendar.getInstance();
					Calendar expiryDate = Calendar.getInstance();
					expiryDate.setTime(new Date(parts[1]));
					// Only load challenge if before game end date
					if(rightnow.before(expiryDate)) {
						expiryDates[numChallenges] = parts[1];
						User friend = new User (parts[3], parts[4]);
						int score = Integer.parseInt(parts[5]);
						Category cat = database.getCategory(parts[2]);
						Challenge challenge = new Challenge(path, currentUser, friend, score, cat);
						challengeArray[index] = challenge;
						index++;
						numChallenges++;
					}
				}
			}
			in.close();
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		// Create new list of challenges
		JList<Challenge> list = new JList<Challenge>(challengeArray);
		// Return challenge list, or null if error reading from file
		return list;
	}

	/**
	 * Remove challenge removes the already accepted challenge from the
	 * pending challenge file
	 * @param line		The line to be removed from the file
	 */
	public void removeChallenge(String line) {
		// Try to open a new connection and erase pending file
		try {
			// Create a URL connection
			URL url = new URL(path + "overWritePending.php");
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			// Pass line to be removed from pending challenge file
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("challenge=" + line);
			writer.flush();
			writer.close();
			connection.getInputStream();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Error: Category cannot be removed");
		}
	}
	
	/**
	 * GameHistoryHandler allows the user to see a list of their game history.
	 */
	private class GameHistoryHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Get the users history list
			JList<String> historyList = new JList<String>();
			historyList = loadHistory();
			JScrollPane scroller = new JScrollPane(historyList);
			// Add history list to a scroll pane
			JPanel optionPane = new JPanel();
			optionPane.add(scroller);
			// Show history list and print result
			System.out.println("Viewing game history...");
			int result = JOptionPane.showConfirmDialog(challengePanel, optionPane, "Game History", JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				// Just need to show history, user cannot remove or add to game history
			}
		}
	}

	/**
	 * Loads game history from the user's game history file
	 * @return  A list of pending challenges
	 */
	public JList<String> loadHistory() {
		String [] historyArray = new String [100];
		String historyInfo;
		int index = 0;
		// Try to load challenges from file
		try {
			URL url = new URL(path + "users/" + currentUser.getID() + "/history.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			// Read from file line by line until end of file is reached
			while ((historyInfo = in.readLine()) != null) {
				String [] parts = historyInfo.split(",");
				String gameResult = parts[1] + "," + parts[2];
				// Add game result to history and increment index by 1
				historyArray[index] = gameResult;
				index++;
			}
			in.close();
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		// Create new game history list
		JList<String> list = new JList<String>(historyArray);
		// Return challenge list, or null if error reading from file
		return list;
	}
	
	/**
	 * StartGameHandler opens up the game play screen when the user 
	 * clicks "start" on the game instruction screen.
	 */
	private class StartGameHandler implements ActionListener {
		
		public void actionPerformed (ActionEvent arg0) {
			// Make the instruction screen invisible
			instructionPanel.setVisible(false);
			
			// Get player and opponent names
			userName.setText(game.getPlayerName());
			opponentName.setText(game.getOpponentName());
			
			// Get player and opponent scores
			userScore.setText("" + 0);
			opponentScore.setText("" + game.getOpponentScore());
			
			// Make the game play screen visible
			gamePlayPanel.setVisible(true);
			// Print result
			System.out.println("Starting game...");
			
			// Reset new game variables
			round = 0;
			seconds = 11;
			clock.setText("" + 11);
			runningScore = 0;
			currentStreak = 0;
			fiveQuestions = game.getQuestions();
			scores = new int [5];
			gameResults = new int [5];
			winOrLose.setText("");
			
			// Load first question
			loadQuestion(fiveQuestions[round]);
			
			// Start game timer 
			gameTimer.start();
			// Start game clock
			gameClock.start();
		}
	}
	
	/**
	 * EndRoundHandler loads a new question and starts a new round, either
	 * when the current round expires or when the user enters their answer.
	 */
	private class EndRoundHandler implements ActionListener {
		
		public void actionPerformed (ActionEvent arg0) {
			// Check where the action originated
			if (arg0.getSource().equals(answer1)) {
				// Check if the multiple choice answer is correct
				if (answer1.getText().equals(currentQuestion.getAnswer())) {
					currentStreak++;
					int score = score(11 - seconds, currentStreak);
					runningScore += score;
					scores[round] = score;
					gameResults[round] = 1;
				}
				else {
					// Wrong answer, add nothing to the score and set streak to 0
					currentStreak = 0;
					scores[round] = 0;
					gameResults[round] = 0;
				}
			}
			else if (arg0.getSource().equals(answer2)) {
				// Check if the multiple choice answer is correct
				if (answer2.getText().equals(currentQuestion.getAnswer())) {
					currentStreak++;
					int score = score(11 - seconds, currentStreak);
					runningScore += score;
					scores[round] = score;
					gameResults[round] = 1;
				}
				else {
					// Wrong answer, add nothing to the score and set streak to 0
					currentStreak = 0;
					scores[round] = 0;
					gameResults[round] = 0;
				}
			}
			else if (arg0.getSource().equals(answer3)) {
				// Check if the multiple choice answer is correct
				if (answer3.getText().equals(currentQuestion.getAnswer())) {
					currentStreak++;
					int score = score(11 - seconds, currentStreak);
					runningScore += score;
					scores[round] = score;
					gameResults[round] = 1;
				}
				else {
					// Wrong answer, add nothing to the score and set streak to 0
					currentStreak = 0;
					scores[round] = 0;
					gameResults[round] = 0;
				}
			}
			else if (arg0.getSource().equals(answer4)) {
				// Check if the multiple choice answer is correct
				if (answer4.getText().equals(currentQuestion.getAnswer())) {
					currentStreak++;
					int score = score(11 - seconds, currentStreak);
					runningScore += score;
					scores[round] = score;
					gameResults[round] = 1;
				}
				else {
					// Wrong answer, add nothing to the score and set streak to 0
					currentStreak = 0;
					scores[round] = 0;
					gameResults[round] = 0;
				}
			}
			else if (arg0.getSource().equals(enter)) {
				// Check if the short answer/voice question answer is correct
				if (answer.getText().equalsIgnoreCase(currentQuestion.getAnswer())) {
					currentStreak++;
					int score = score(11 - seconds, currentStreak);
					runningScore += score;
					scores[round] = score;
					gameResults[round] = 1;
				}
				else {
					// Wrong answer, add nothing to the score and set streak to 0
					currentStreak = 0;
					scores[round] = 0;
					gameResults[round] = 0;
				}
				// Reset answer box to null
				answer.setText("");
			}
			// Else time is up
			else {
				// Add nothing to the score and set streak to 0
				currentStreak = 0;
				scores[round] = 0;
				gameResults[round] = 0;
			}
			
			// Set user's displayed score
			userScore.setText("" + runningScore);
			
			// Reset game clock
			clock.setText("" + 0);
			clockPanel.repaint();
			gameTimer.stop();
			gameClock.stop();
			
			// Increment round by 1
			round++;
			
			// If game is not over, load next question
			if (round < 5) {
				loadQuestion(fiveQuestions[round]);
				// Reset game clock and game timer
				seconds = 11;
				gameTimer.restart();
				gameClock.restart();
				opponentScore.setText("" + game.getOpponentScore());
			}
			else {
				endGame();
			}
		}
	}
	
	/**
	 * endGame ends the current game
	 */
	private void endGame() {
		game.setPlayerScore(runningScore);
		game.setScores(scores);
		newChallenge.setScore(runningScore);
		// If challenge isn't accepted, write it to pending
		if (!newChallenge.isAccepted())
			newChallenge.writePendingToFile();
		// Set scores
		opponentScore.setText("" + game.getOpponentScore());
		yourScore.setText("" + runningScore);
		q1.setText("Question 1:  " + scores[0]);
		q2.setText("Question 2:  " + scores[1]);
		q3.setText("Question 3:  " + scores[2]);
		q4.setText("Question 4:  " + scores[3]);
		q5.setText("Question 5:  " + scores[4]);
		// Set results indicators
		setResultsIndicator(r1, gameResults[0]);
		setResultsIndicator(r2, gameResults[1]);
		setResultsIndicator(r3, gameResults[2]);
		setResultsIndicator(r4, gameResults[3]);
		setResultsIndicator(r5, gameResults[4]);
		// Check if challenge was accepted
		if (newChallenge.isAccepted()) {
			// Check who won, and set message accordingly
			if (runningScore > game.getOpponentScore()) {
				winOrLose.setText("You Win");
				winOrLose.setForeground(Color.GREEN);
				game.setPlayerScore(runningScore);
				game.writeHistoryToFile();
			}
			else if (runningScore < game.getOpponentScore()) {
				winOrLose.setText("You Lose");
				winOrLose.setForeground(Color.RED);
				game.setPlayerScore(runningScore);
				game.writeHistoryToFile();
			}
			else {
				winOrLose.setText("Tie Game");
				winOrLose.setForeground(Color.WHITE);
				game.setPlayerScore(runningScore);
				game.writeHistoryToFile();
			}	
			Challenge toBeRemoved = temp.getSelectedValue();
			// Make a string in the same format as saved in the pending file
			String line = currentUser.getID() + "," + expiryDates[temp.getSelectedIndex()]+ "," + toBeRemoved.getCategory().getCategoryName();
			line = line + "," + toBeRemoved.getFriend().getID() + "," + toBeRemoved.getFriend().toString() + ",";
			line = line + toBeRemoved.getFriendScore() + "," + ";";
			System.out.println("Removing line: " + line);
			// Remove the line from the pending file
			removeChallenge(line);
		}
		// Else don't save game yet, challenge yet to be accepted
		else {
			winOrLose.setText("Challenge Saved");
			winOrLose.setForeground(Color.WHITE);
		}
		// Make game play screen invisible
		gamePlayPanel.setVisible(false);
		// Make results screen visible
		resultsPanel.setVisible(true);
		// Print result
		System.out.println("Game is finished");
	}
	
	/**
	 * Loads the input question into the game play screen
	 * @param next		The next question to be displayed
	 */
	private void loadQuestion(Question next) {
		currentQuestion = next;
		// Load description
		question.setText(next.getDescription());
		// Check if question is multiple choice
		if (next.getType() == "MultipleChoice") {
			// Cast next as as a multiple choice question
			MultipleChoiceQuestion nextMC = (MultipleChoiceQuestion) next;
			// Make answer text field and answer button invisible
			answer.setVisible(false);
			enter.setVisible(false);
			// Make multiple choice answer buttons visible
			answer1.setVisible(true);
			answer2.setVisible(true);
			answer3.setVisible(true);
			answer4.setVisible(true);
			// Get all 4 possible choices, shuffle them, and set the choices into the 4 answer buttons
			String [] choices = {nextMC.getAnswer(), nextMC.getDecoy1(), nextMC.getDecoy2(), nextMC.getDecoy3()};
			Collections.shuffle(Arrays.asList(choices));
			answer1.setText(choices[0]);
			answer2.setText(choices[1]);
			answer3.setText(choices[2]);
			answer4.setText(choices[3]);
		}
		else if (next.getType() == "ShortAnswer"){
			// Make multiple choice answer buttons invisible
			answer1.setVisible(false);
			answer2.setVisible(false);
			answer3.setVisible(false);
			answer4.setVisible(false);
			// Make answer text field and answer button visible
			answer.setVisible(true);
			enter.setVisible(true);
		}
	}
	
	/**
	 * ClockTickHandler changes the time displayed in the game clock
	 * by counting down from 11 every second.
	 */
	private class ClockTickHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Decrement seconds by 1
			seconds--;
			// Display seconds remaining on game clock
			clock.setText("" + seconds);
			clockPanel.repaint();
		}
	}
	
	/**
	 * This method sends a variable to a PHP that writes the user's skin choice to a file
	 * @param skinName 		The name of the skin the user prefers
	 **/
	public void writeSkin(String skinName){
		// Try to write selected skin to user's file
		try{
			// Open a URL connection
			URL u = new URL(path + "writeSkin.php");
			URLConnection connection = u.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);	
		
			// Write skin name to user's skin file
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write("0=" + skinName);
            writer.flush();
            writer.close();
           	connection.getInputStream();
		}
        catch (IOException ioe) {
			System.out.println("Error: Skin file cannot be read");
		}
	}
	
	/**
	 * getSkin returns the users previously chosen skin
	 * @param id 		The user id of the player
	 * @return The previously chosen skin for the player as a string
	 **/
	public String getSkin(String id){
		// Create a null string
		String inputLine = "";
		
		//Try to load saved skin from user's file
		try{
	        URL skin = new URL(path + "users/" + id + "/skin.txt");
	        BufferedReader in = new BufferedReader(new InputStreamReader(skin.openStream()));
	        inputLine = in.readLine();
	        in.close();
		}
	    catch(IOException ioe){
	        	ioe.printStackTrace();
	    }
		// Return saved skin, or null if file cannot be read
		return inputLine;        
    }	
	
	/**
	 * Algorithm to find the amount scored per question.
	 * This algorithm is taken directly from the game SongPop.
	 * @param time 		The amount of time taken to answer the question
	 * @param streak 	The amount of questions scored in a row
	 * @return The integer representing the score
	 */
	public int score(int time, int streak) {
		double base = (11 - (double)time)*100;
		double multiplier = Math.pow((11 - (double)time), ((double)streak - 1)/4);
		return (int) (base*multiplier);
	}
	
	/**
	 * Sets the results indicator on the game results screen
	 * @param label		The indicator to be set
	 * @param result	The question result
	 */
	public void setResultsIndicator(JLabel label, int result) {
		BufferedImage pic = null;
		// If answer is correct, try to load "check-mark" icon
		if (result == 1) {
			try {
				URL url = new URL(getCodeBase(), "check.png");
				pic = ImageIO.read(url);
				label.setIcon(new ImageIcon(pic));
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		// Else answer is incorrect, try to load "X" icon
		else {
			try {
				URL url = new URL(getCodeBase(), "x.png");
				pic = ImageIO.read(url);
				label.setIcon(new ImageIcon(pic));
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	/**
	 * PlayAgainHandler allows the user to go back to the challenge screen and play a new game
	 */
	private class PlayAgainHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Make challenge screen invisible
			resultsPanel.setVisible(false);

			// Make welcome screen visible
			challengePanel.setVisible(true);
			
			// Print result
			System.out.println("Opening challenge screen...");
		}
	}

	/**
	 * ExitButtonHandler allows the user to exit to the main welcome screen
	 * during any point in the game.
	 */
	private class ExitButtonHandler implements ActionListener {

		public void actionPerformed (ActionEvent arg0) {
			// Create a pop-up screen to confirm
			JPanel optionPane = new JPanel(new GridLayout(0, 2));
			JLabel exitLabel = new JLabel("Are you sure you want to exit?");
			optionPane.add(exitLabel);

			// Show exit confirmation pop-up option panel
			int result = JOptionPane.showConfirmDialog(null, optionPane, "Edit Category", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				// Stop game clock and game timer
				gameClock.stop();
				clock.setText("" + 11);
				gameTimer.stop();
				
				// Make all other screens invisible
				database.writeToFile();
				adminPanel.setVisible(false);
				editCategoryPanel.setVisible(false);
				editQuestionPanel.setVisible(false);
				challengePanel.setVisible(false);
				instructionPanel.setVisible(false);
				gamePlayPanel.setVisible(false);
				resultsPanel.setVisible(false);

				// Make the welcome screen visible
				welcomePanel.setVisible(true);
				welcomeMessage.setVisible(true);
				// Print result
				System.out.println("Opening welcome screen...");
			}
		}
	}

} //////////////////// End of PopQuizGUI ////////////////////