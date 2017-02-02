<?php

  // Needed for Facebook connection
  require ("facebook.php");
  define("FACEBOOK_APP_ID", '545045195518893');
  define("FACEBOOK_SECRET_KEY", 'e1ea4cb8e8fe53f4fd4294d00e4e8b84');

  $facebook = new Facebook(array('appId' => FACEBOOK_APP_ID, 'secret' => FACEBOOK_SECRET_KEY)); 
  $user_profile = $facebook->api('/me','GET');

  // Get user ID from Facebook
  $userID = $user_profile['id'];
  
  // User's skin file   					
  $file = "users/". $userID ."/skin.txt";   		

  $arr = $_POST;  										// Creates file post
  echo $_POST;    										// Echoes post to java
  $fp = fopen($file, 'w') or die ("can't open file"); 	// Attempt to open file
  foreach ($arr as $key => $value){       				// Goes through each argument
        $toFile = "$value\n";           				// Sends line to file
        fwrite($fp, "$toFile");         				// Writes to file
  }
  fclose($fp);    										// Closes file

?>