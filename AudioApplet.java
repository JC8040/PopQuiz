import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
/**
 * This is a simple Sound Recorder program written in java.
 * Retrieved from StackOverflow
 * @author Tapas kumar jena
 * @mail tapas.friends@gmail.com
 */
//Main class
public class AudioApplet extends JPanel implements ActionListener, ItemListener {
 //Global declarations
 protected boolean running;
 ByteArrayOutputStream out = null;
 AudioFileFormat.Type fileType;
 Object lock = new Object();
 TargetDataLine line = null;
 SourceDataLine sline = null;
 volatile boolean paused = false;
 boolean first;

 JButton record;
 JButton play;
 JButton stop;
 JButton save;

 JComboBox servercombo;

 
 JLabel vol1 = null;
 JLabel vol2 = null;
 JSlider volslider = null;
 JToggleButton mute = null;
 FloatControl volCtrl = null;
 Port lineIn = null;
 String list[];
 volatile String msg;

 public void init()
 {
  setLayout(null);
  JLabel recorder = new JLabel("Recorder");

  servercombo = new JComboBox();

  record = new JButton("Record");
  play = new JButton("Play");
  stop = new JButton("Stop");
  save = new JButton("Save");

  mute = new JToggleButton("Mute");
  vol1 = new JLabel("Volume  -");
  vol2 = new JLabel("+");
  volslider = new JSlider(0,100);
  volslider.setToolTipText("Volume");
  volslider.setPaintTicks(true);
  volslider.setMinorTickSpacing(10);

  recorder.setBounds(10,10,70,25);
  record.setBounds(70,10,80,25);
  play.setBounds(155,10,80,25);
  stop.setBounds(240,10,80,25);
  save.setBounds(325,10,80,25);

  vol1.setBounds(75, 70, 100, 20);
  volslider.setBounds(130, 80, 150, 20);
  vol2.setBounds(280, 72, 30, 20);
  mute.setBounds(330, 70, 65, 30);

  add(recorder);
  add(record);
  add(play);
  add(stop);
  add(save);

  add(servercombo);

  add(vol1);
  add(volslider);
  add(vol2);
  add(mute);

  record.setEnabled(true);
  play.setEnabled(true);
  stop.setEnabled(true);
  save.setEnabled(true);

  record.addActionListener(this);
  play.addActionListener(this);
  stop.addActionListener(this);
  save.addActionListener(this);
  mute.addActionListener(this);
  servercombo.addItemListener(this); 
  this.setSize(350, 200);
 }//End of init method

 //***************************************************/   
 //******* StateChanged method for ChangeListener*****/
 //***************************************************/



 public void itemStateChanged(ItemEvent ie) {
  msg = "  Listening from server [buffering]...";
  listenAudio();       
 }

 //***************************************************/   
 //***** ActionPerformed method for ActionListener****/
 //***************************************************/

 public void actionPerformed(ActionEvent e) {
  if(e.getSource()==record){
   msg = "  Capturing audio from mic.....";
   record.setEnabled(false);
   stop.setEnabled(true);
   play.setEnabled(false);
   save.setEnabled(true);
   if(paused)
   {
    resumeRecord();
   }       
   else
   {
    recordAudio();
   }
  }
  else if (e.getSource()==play) {
   msg = "  Playing recorded audio.....";
   stop.setEnabled(true);
   if(first)
   {
    playAudio();
   }
   else
   {
    resumePlay();           
   }
  }
  else if (e.getSource()==stop) {
   msg = "  Action stopped by user.....";
   record.setEnabled(true);
   stop.setEnabled(false);
   play.setEnabled(true);
   running = false;
   stopAudio();

  }
  else if (e.getSource()==save) {
   msg = "  Saving file to user's System....";
   saveAudio();
  }
  else {
   muteControl();

  }
 }

 //******************************************/
 //**************   Method Declarations  ****/
 //******************************************/ 

 private void recordAudio() {
  first=true;
  try {
   final AudioFileFormat.Type fileType = AudioFileFormat.Type.AU;                      
   final AudioFormat format = getFormat();
   DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
   line = (TargetDataLine)AudioSystem.getLine(info);               
   line.open(format);
   line.start();

   Runnable runner = new Runnable() {
    int bufferSize = (int) format.getSampleRate()* format.getFrameSize();
    byte buffer[] = new byte[bufferSize];           

    public void run() {
     out = new ByteArrayOutputStream();
     running = true;
     try {                      
     while (running) {                           
      int count = line.read(buffer, 0, buffer.length);
      if (count > 0) {
       out.write(buffer, 0, count);
       InputStream input = new ByteArrayInputStream(buffer);
       final AudioInputStream ais = new AudioInputStream(input, format, buffer.length /format.getFrameSize());
      }                           
     }
     out.close();
     }catch (IOException e) {                    
      System.exit(-1);
     }
    }
   };
   Thread recordThread = new Thread(runner);
   recordThread.start();
  }catch(LineUnavailableException e) {
   System.err.println("Line Unavailable:"+ e);
   e.printStackTrace();
   System.exit(-2);
  }
  catch (Exception e) {
   System.out.println("Direct Upload Error");
   e.printStackTrace();
  }
 }//End of RecordAudio method

 private void playAudio() {
  try{
   byte audio[] = out.toByteArray();
   InputStream input = new ByteArrayInputStream(audio);
   final AudioFormat format = getFormat();
   final AudioInputStream ais = new AudioInputStream(input, format, audio.length /format.getFrameSize());
   DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
   sline = (SourceDataLine)AudioSystem.getLine(info);
   sline.open(format);
   sline.start();              
   Float audioLen = (audio.length / format.getFrameSize()) * format.getFrameRate();

   Runnable runner = new Runnable() {
    int bufferSize = (int) format.getSampleRate() * format.getFrameSize();
    byte buffer[] = new byte[bufferSize];

    public void run() {
     try {
      int count;
      synchronized(lock){
       while((count = ais.read( buffer, 0, buffer.length)) != -1) {
        while(paused)  {
         if(sline.isRunning()) {
          sline.stop();
         }
         try{
          lock.wait();
         }
         catch(InterruptedException e) {
         }
        }
        if(!sline.isRunning()) {
         sline.start();
        }
        if(count > 0) {
         sline.write(buffer, 0, count);
        }           
       }                                             
      }
      first=true;
      sline.drain();
      sline.close();
     }catch(IOException e) {
      System.err.println("I/O problems:" + e);
      System.exit(-3);
     }
    }
   };

   Thread playThread  = new Thread(runner);
   playThread.start();
  }catch(LineUnavailableException e) {
   System.exit(-4);
  }
 }//End of PlayAudio method

 private void resumeRecord(){
  synchronized(lock) {
   paused = false;
   lock.notifyAll();
   first = true;
  }
 }//End of ResumeRecord method

 private void stopAudio() {
  if (sline != null) {
   sline.stop();
   sline.close();
  }else {
   line.stop();
   line.close();
  }
 }//End of StopAudio method

 private void resumePlay(){
  synchronized(lock) {
   paused = false;
   lock.notifyAll();
   System.out.println("inside resumeplay method");
  }
 }//End of ResumePlay method

 private void pauseAudio(){
  paused = true;
 }

 private void saveAudio() {
  Thread thread = new saveThread();
  thread.start();
 }

 private void uploadAudio() {
  Thread th= new uploadThread();
  th.start();
 }

 private void listenAudio() {
  Thread thread = new listenThread();
  thread.start();
 }

 private AudioFormat getFormat() {
  Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
  float sampleRate = 44100.0F;
  int sampleSizeInBits = 16;
  int channels = 2;
  int frameSize = 4;
  float frameRate = 44100.0F;
  boolean bigEndian = false;
  return new AudioFormat(encoding, sampleRate, sampleSizeInBits, channels, frameSize, frameRate, bigEndian);
 }//End of getAudioFormat method

 class saveThread extends Thread  {
  public void run(){
   AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
   FileDialog fd = new FileDialog(new Frame(), "Save as WAVE", FileDialog.SAVE);
   fd.setFile("*.wav");
   fd.setVisible(true);
   String name = fd.getDirectory() + fd.getFile();
   File file = new File(name);

   try{
    byte audio[] = out.toByteArray();
    InputStream input = new ByteArrayInputStream(audio);
    final AudioFormat format = getFormat();
    final AudioInputStream ais = new AudioInputStream(input, format, audio.length /format.getFrameSize());
    AudioSystem.write(ais,fileType,file);
   }catch (Exception e){
    e.printStackTrace();
   }
  }
 }//End of inner class saveThread

 class uploadThread extends Thread{
  public void run(){
   AudioFileFormat.Type fileType = AudioFileFormat.Type.AU;

   try{                   
    line.flush();
    line.close();
   }
   catch(Exception e){
    e.printStackTrace();
    System.err.println("Error during upload");
   }               
  }
 }//End of inner class uploadThread

 class listenThread extends Thread{
  public void run() {
   try {
    URL upload=new URL("http://localhost:8080/TapasApplet/upload");
    HttpURLConnection conn = (HttpURLConnection) upload.openConnection();
    conn.setRequestMethod("POST");

    conn.setDoOutput(true);
    conn.setDoInput(true);
    conn.setUseCaches(false);
    conn.setDefaultUseCaches(false);
    conn.setChunkedStreamingMode(1000);
    conn.setRequestProperty("Content-Type", "application/octet-stream");
    InputStream is = conn.getInputStream();
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String serfile = br.readLine();
    while(line != null){
     //un complete code here   
     serfile=br.readLine();
    }
   } catch (IOException e) {
    System.err.println("Error in UserThread run() method");
    e.printStackTrace();
   }
  }

 }

 public void volumeControl() {
  try {
   if(AudioSystem.isLineSupported(Port.Info.LINE_OUT))
   {
    lineIn = (Port)AudioSystem.getLine(Port.Info.LINE_OUT);
    lineIn.open();
   }
   else if(AudioSystem.isLineSupported(Port.Info.HEADPHONE))
   {
    lineIn = (Port)AudioSystem.getLine(Port.Info.HEADPHONE);
    lineIn.open();
   }
   else if(AudioSystem.isLineSupported(Port.Info.SPEAKER))
   {
    lineIn = (Port)AudioSystem.getLine(Port.Info.SPEAKER);
    lineIn.open();
   }
   else
   {
    System.out.println("Unable to get Output Port");
    return;
   }
   final FloatControl controlIn = (FloatControl)lineIn.getControl(FloatControl.Type.VOLUME);
   final float volume = 100 * (controlIn.getValue() / controlIn.getMaximum());
   System.out.println(volume);
   int sliderValue=volslider.getValue();
   controlIn.setValue((float)sliderValue / 100);

  } catch (Exception e) {
   System.out.println(" VOLUME control: exception = " + e);
  }
 }//End of volumeControl method

 public void muteControl() {
  BooleanControl mControl;
  try {
   mControl = (BooleanControl) sline.getControl(BooleanControl.Type.MUTE);

   if (mControl.getValue() == true)
   {
    mControl.setValue(false);
   }
   else
   {
    mControl.setValue(true);
   }             
  } catch (Exception e) {
   System.out.println(" MUTE control: exception = " + e);
  }
 }       
}//End of main class AudioBroadcast