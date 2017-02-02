<?php

	// Needed for Facebook connection
	require("facebook.php");
	define("APP_ID", '545045195518893');
	define("SECRET_KEY", 'e1ea4cb8e8fe53f4fd4294d00e4e8b84');
	
	$facebook = new Facebook(array('appId' => APP_ID, 'secret' => SECRET_KEY));
	$userID = $facebook->getUser();
	
	// User's root directory
	$userDir = "users/" . $userID;
	
	$question = $_POST['question'].PHP_EOL;
	$answer = $_POST['answer'].PHP_EOL;

	// User's security question and answer file
	$securityFile = $userDir . "/securityQA.txt";
	
	// Save to file
	$fh = fopen($securityFile, 'w') or die("can't open file");
	fwrite($fh, $question.$answer);
	fclose($fh);
	chmod($securityFile, 0777);
?>