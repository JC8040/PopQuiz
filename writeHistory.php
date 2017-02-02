<?php

// Needed for Facebook connection
require ("facebook.php");
define("FACEBOOK_APP_ID", '545045195518893'); 
define("FACEBOOK_SECRET_KEY", 'e1ea4cb8e8fe53f4fd4294d00e4e8b84');  

// getting into the facebook app
$facebook = new Facebook(array('appId' => FACEBOOK_APP_ID, 'secret' => FACEBOOK_SECRET_KEY)); 
$user_profile = $facebook->api('/me','GET');

// User
$userID = $user_profile['id'];
// Users's root directory
$userDir = "users/" . $userID;
// Users's game history
$userHistory = $userDir . "/history.txt";

// Friend
$friendID = $_POST['friendID'];
// Friend's root directory
$friendDir = "users/" . $friendID;
// Friend's game history
$friendHistory = $friendDir . "/history.txt";

$arr = $_POST;
echo $_POST;

// Save game history for both user and friend
$fp1 = fopen($userHistory, 'a') or die ("can't open file");
$fp2 = fopen($friendHistory, 'a') or die ("can't open file");
foreach ($arr as $key =>  $value) {
   // $toFile= "Key: $key; Value: $value \n";
   $toFile= "$value" . ",";
   fwrite($fp1,"$toFile");
   fwrite($fp2,"$toFile");
}
fwrite($fp1, ";\n");
fwrite($fp2, ";\n");
fclose($fp1);
fclose($fp2);

?>