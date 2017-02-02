<?php

// Needed for Facebook connection
require ("facebook.php");
define("FACEBOOK_APP_ID", '545045195518893'); 
define("FACEBOOK_SECRET_KEY", 'e1ea4cb8e8fe53f4fd4294d00e4e8b84');  

$facebook = new Facebook(array('appId' => FACEBOOK_APP_ID, 'secret' => FACEBOOK_SECRET_KEY)); 
$user_profile = $facebook->api('/me','GET');

$userID = $user_profile['id'];
$friendID = $_POST['friendID'];

// Friend's root directory
$friendDir = "users/" . $friendID;
// If directory doesn't exist, create it
if (!is_dir($friendDir)) {
        mkdir("$friendDir");
        chmod("$friendDir", 0777);
}

// Friend's pending challenges
$friendPending = $friendDir . "/pending.txt";

$arr = $_POST;
echo $_POST;

// Write contents to pending challenge file
$fp = fopen($friendPending, 'a') or die ("can't open file");
foreach ($arr as $key =>  $value) {
   // $toFile= "Key: $key; Value: $value \n";
   $toFile= "$value" . ",";
   fwrite($fp,"$toFile");
}
fwrite($fp, ";\n");
fclose($fp);
chmod($friendPending, 0777);

?>
