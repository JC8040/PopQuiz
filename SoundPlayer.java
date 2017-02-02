import java.io.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

/**
 * Taken from last term's class (CS2210)
 * A class that plays the voiceQuestion description 
 * @author CS2212 group2
 *
 */
public class SoundPlayer {

  public SoundPlayer() {}

  /**
   * The constructor
   * @param fileName The name of the file to be played
   **/
  public  void play(String fileName) throws MultimediaException{    
   /* Play a file of type .wav or .mid. If the file cannot be played a 
      MultimediaException is thrown.                                        */
    try {
      File file = new File(fileName);
      @SuppressWarnings("deprecation")
	URL url = file.toURL();
      AudioClip ac = Applet.newAudioClip(url);
      ac.play();         

      System.out.println("Press RET to continue.");
      BufferedReader keyboard =  new BufferedReader
		                   (new InputStreamReader(System.in));
      String c = keyboard.readLine();
      ac.stop();
    } catch (Exception e) {
       throw new MultimediaException("Error playing sound file "+fileName);
    }
  }
}