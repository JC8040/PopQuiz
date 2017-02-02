<?php
		// Temporary file to save to
        $testfile = "temp.txt";
        // Get bulk-load address from applet
        $fbulk = $_POST['bulkloadaddress'];

        echo "This is what the bulkloadaddress contained:"; 
        echo $fbulk;
        $infile = fopen($fbulk, "r");
        $testArray = array();

		// Read entire input file
        while (!feof($infile)) {
                $testArray[] = fgets($infile);
        }
        fclose($infile);

		// Write contents of file to temporary file
        $fh = fopen($testfile, 'w+') or die("can't open file");
        foreach($testArray as $key => $value){
                fwrite($fh,$value);
        }
        fclose($fh);
?>
