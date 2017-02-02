<?php

// Needed for Facebook connection
require ("facebook.php");
define("FACEBOOK_APP_ID", '545045195518893'); 
define("FACEBOOK_SECRET_KEY", 'e1ea4cb8e8fe53f4fd4294d00e4e8b84');  

$facebook = new Facebook(array('appId' => FACEBOOK_APP_ID, 'secret' => FACEBOOK_SECRET_KEY)); 
$user_profile = $facebook->api('/me','GET');

// Get user id and pending challenges file
$userID = $user_profile['id'];
$pendingFile = "users/" . $userID . "/pending.txt";

// Get challenge string to be removed
$challenge = $_POST['challenge'];

// Remove challange from file and rewrite
$contents = file_get_contents($pendingFile);
$contents = str_replace($challenge, "", $contents);
file_put_contents($pendingFile, $contents); 

?>