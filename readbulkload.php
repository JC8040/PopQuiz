<?php

  $checkbool = is_file('bulkload.txt');    
  echo("<param name=\"prevbool\" value=\"  $checkbool\">" );// Sends the boolean value to the user
        
  if ($checkbool == 1){   									// Checks if file has already been created
        $lines = count(file('bulkload.txt')) - 1;
        echo("<param name=\"lines\" value=\"  $lines\">" );
        $file = "bulkload.txt";     						// Creates file name
        $filevalues = file_get_contents($file); 			// Gets content from file
        $array = split("\n", $filevalues);      			// Splits content at each newline
        $max = count($array);
        $counter = 0;
        
        while($counter <= $max-1){
            echo("<param name=\"bulk$counter\" value=\"" .  $array[$counter] . "\">" ); // Creates name variable and echos to java
        }
  }
  
?>