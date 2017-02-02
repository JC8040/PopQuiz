<?php

// Header contains navigation bar
include '2212-header.php.inc';

// Needed for Facebook connection
require("facebook.php");
define("APP_ID", '545045195518893');
define("SECRET_KEY", 'e1ea4cb8e8fe53f4fd4294d00e4e8b84');

$facebook = new Facebook(array('appId' => APP_ID, 'secret' => SECRET_KEY));
$userID = $facebook->getUser();
       
// If user ID is null, make user sign-in
if (!$userID) {
    $loginUrl = $facebook->getLoginUrl(array('scope'=>'friends_birthday','redirect_ul'=>$app_url));
    echo "<script type='text/javascript'>top.location.href = '$loginUrl';</script>";
    exit;
}

try {
    $user_profile = $facebook->api('/me','GET');
    $name = $user_profile['name'];
    $firstName = $user_profile['first_name'];

    // Make sure user directory exists, and if not, create it
    $userDir = "users/" . $userID;
    if (!is_dir($userDir)) {
        // User's root directory
        mkdir("$userDir");
        chmod("$userDir", 0777);
    }
    // Make sure user pending file exists, and if not, create it
    $userPending = $userDir . "/pending.txt";
    if (!is_file($userPending)) {
        // User's pending challenges
        $fp = fopen($userPending, 'x');
        fclose($fp);
        chmod($userPending, 0777);
    }
    // Make sure user history file exists, and if not, create it
    $userHistory = $userDir . "/history.txt";
    if (!is_file($userHistory)) {
        // User's game history
        $fp = fopen($userHistory, 'x');
        fclose($fp);
        chmod($userHistory, 0777);
    }
    // Make sure user skin file exists, and if not, create it
    $userSkin = $userDir . "/skin.txt";
    if (!is_file($userSkin)) {
        // User's skin preferences
        $fp = fopen($userSkin, 'x');
        fwrite($fp, "blue");
        fclose($fp);
        chmod($userSkin, 0777);
    }

	$friends = $facebook->api('/me/friends?fields=id,name,birthday');

    echo "<p></p>";
    echo "<div style=\"float:left; margin-left:160px; padding-bottom:75px; overflow:auto;\">";
    echo "<applet archive=\"PopQuizGUI.jar\" code=\"PopQuizGUI.class\" width=600 height=500 id=\"popquiz2\">";
    echo "<param name=\"userID\" value=\"$userID\">";
    echo "<param name=\"name\" value=\"$name\">";
    echo "<param name=\"firstName\" value=\"$firstName\">";
    echo "<param name=\"friends\" value=\"";
	
	// Include each Facebook friend's userID and full name in "friends" parameter
    foreach ($friends["data"] as $value) {
        echo $value["id"];
        echo ",";
        echo $value["name"];
        echo ";";
    }
    echo "\">";
    echo "</applet>";
    echo "</div>";
    
    // Facebook Like/Send Button
    ?>
      <div style="float:left; margin-top:5px; margin-left:5px;">
         <div class="fb-like" data-href="https://vmarche.cs2212.ca/popquiz2/popquiz2.php" data-send="true" data-layout="box_count" data-width="450" data-show-faces="false" data-font="tahoma" $
      </div>
    <?
    echo "<p></p>";
}

catch (FacebookApiException $e) {
    print "in error exception";
    echo ($e);
}

include '2212-footer.php.inc';

?>