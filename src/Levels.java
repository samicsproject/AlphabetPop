import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * 
 * <p>
 * <b> Version Information: </b>
 * <p>
 * <b>Author</b> Esther Yoo
 * <b>Version #</b> 1
 * <b>Date</b> 05.31.16
 * <b>Time Spent</b> 3 hours
 * <p>
 * <b>Author</b> Samantha Unger
 * <b>Version #</b> 1.1
 * <b>Date</b> 06.02.16
 * <b>Time Spent</b> 5 hours
 * <b>What Was Changed</b> The code organization was improved and startup() method was created.  Some problems involving
 * file IO were fixed.
 * <p>
 * <b>Author</b> Samantha Unger
 * <b>Version #</b> 1.2
 * <b>Date</b> 06.05.16
 * <b>Time Spent</b> 1 hour
 * <b>What Was Changed</b> The code now properly displays the letters.  Brackets within the LettersCanvas class had to 
 * be moved to the correct location.
 * <p>
 * <b>Author</b> Samantha Unger and Esther Yoo
 * <b>Version #</b> 2
 * <b>Date</b> 06.09.16
 * <b>Time Spent</b> 5 hours
 * <b>What Was Changed</b> The code now plays audio correctly and looks more appealing with backgrounds and such.
 * 
 * 
 * 
 * <p>
 * <b> Instance Variables: </b>
 * <p>
 * <b>ball</b> This creates an array of Ball objects that are used as the bubbles.
 * <p>
 * <b>UPDATE_RATE</b> This final int is used to store the update rate.
 * <p>
 * <b>ball</b> This creates an array of Ball objects that are used as the bubbles.
 * <p>
 * <b>NUM_BUBBLES</b> This final int stores the number of bubbles to be used.
 * <p>
 * <b>word</b> This String stores the word the user must spell.
 * <p>
 * <b>words</b> This ArrayList stores all of the words from the file.
 * <p>
 * <b>box</b> This stores an instance of the ContainerBox class for putting the bubbles.
 * <p>
 * <b>box2</b> This stores an instance of the ContainerBox class for putting the bubbles.
 * <p>
 * <b>canvas</b> This creates an instance of the DrawCanvas class for drawing the box and bubbles on.
 * <p>
 * <b>canvas2</b> This creates an instance of the DrawLetters class for drawing the letters on.
 * <p>
 * <b>canvasWidth</b> This int stores the width of the canvas.
 * <p>
 * <b>canvasHeight</b> This int stores the height of the canvas.
 * <p>
 * <b>xCoord</b> This int stores the x-coordinate of where the user presses.
 * <p>
 * <b>yCoord</b> This int stores the y-coordinate of where the user presses.
 * <p>
 * <b>radius</b> This int stores the radius size for the bubbles.
 * <p>
 * <b>x</b> This int is not used in the current version and may be removed in future versions.
 * <p>
 * <b>y</b> This int is not used in the current version and may be removed in future versions.
 * <p>
 * <b>speed</b> This int is not used in the current version and may be removed in future versions.
 * <p>
 * <b>angleInDegree</b> This int is not used in the current version and may be removed in future versions.
 * <p>
 * <b>background</b> This int is not used in the current version and may be removed in future versions.
 * <p>
 * <b>letters</b> This char array stores the letters of the current word to be spelled.
 * <p>
 * <b>currentLetter</b> This int stores the index of the current letter that the user is finding.
 * <p>
 * <b>clip</b> This Clip is not used in the current version and may be removed in future versions.
 * <p>
 * <b>music</b> This Clip is not used in the current version and may be removed in future versions.
 * <p>
 * <b>audio</b> This Clip array stores the music.
 * <p>
 * <b>alphabet</b> This Clip array stores the sound of the letters.
 * <p>
 * <b>click</b> This Clip array stores the sounds to be played when the user clicks.
 * <p>
 * <b>pics</b> This array of BufferedImage objects stores the background images.
 * <p>
 * <b>t</b> This GameTimer is used to time the game.
 * <p>
 * <b>gameThread</b> This Thread is used to run the game.
 * <p>
 * <b>roundTimes</b> This array is used to store the times for the rounds.
 * <p>
 * <b>temp</b> This int is used to hold the previous letter required.
 * <p>
 * <b>rand</b> This is used to store a random number, in the case of the hard level.
 * <p>
 * <b>paused</b> This boolean is used to determine whether or not the game has been paused.
 * <p>
 * <b>tempTime</b> This int stores the temporary time while pausing.
 * 
 * 
 * 
 * @author Chua Hock-Chuan for original graphics code, modifed by Esther Yoo, Samantha Unger
 * @version 1.2 06.05.16
 */
public abstract class Levels extends JPanel {
  
  protected Ball[] ball = new Ball[26];
  private static final int UPDATE_RATE = 30;  // Frames per second (fps)
  private static final int NUM_BUBBLES = 26;
  String word;
  protected ArrayList<String>words;
  
  private DrawCanvas canvas; // Custom canvas for drawing the box/ball
  private DrawLetters canvas2;
  protected int canvasWidth;
  protected int canvasHeight;
  int xCoord;
  int yCoord;
  
  ContainerBox box;  
  private ContainerBox box2 = new ContainerBox (0, 0, canvasWidth, canvasHeight, "lettersBack.jpg");
  
  int radius = 50;
  int x;
  int y;
  int speed;
  int angleInDegree;
  
  int background;
  
  char [] letters;
  int currentLetter;
  Clip clip;
  Clip music;
  
  Clip[] audio = new Clip[8];
  Clip[] alphabet = new Clip[26];
  Clip[] click = new Clip[3];
  PauseScreen p;
  
  BufferedImage [] pics = new BufferedImage [8];
  
  protected GameTimer t;
  
  Thread gameThread;
  
  int currentWord;
  int[] roundTimes = new int[3];
  
  int temp;
  int rand;
  boolean paused = false;
  int tempTime;
  
  /**
   * The readWords method reads the words in from a file to an ArrayList and shuffles the order of the elements in the
   * ArrayList. A try block is used to catch in case of file problems.  A while loop is used to read in all of the
   * words.
   * 
   * @param file String that stores the name of the file to read the words in from 
   */
  public void readWords(String file)
  {
    BufferedReader input;
    int randNum;
    String tempString;
    words=new ArrayList<String>();      
    
    try
    {
      
      String fileName = file;
      input = new BufferedReader (new FileReader (fileName));
      if (!input.readLine().equals("This is a Green Eggs & Sam file."))
      {
        JOptionPane.showMessageDialog (this, "This is not a .GSE file!", "Incompatible File Type", JOptionPane.ERROR_MESSAGE);
      }
      while (true)
      {
        tempString=input.readLine();
        if (tempString!=null)
        {
          words.add(tempString);
        }
        else
        {
          break;
        }
      }
    }
    catch (FileNotFoundException l)
    {
    }
    catch (IOException q)
    {
    }
    for (int x=words.size()-1; x>0;x--)
    {
      randNum=(int)(Math.random()*x);
      if (randNum!=x)
      {
        tempString=words.get(x);
        words.set(x,words.get(randNum));
        words.set(randNum, tempString);
      }
    }
  }
  
  /**
   * This constructor sets the value of of the canvasHeight, canvasWidth and calls the loadAudio method.
   * 
   * @param width int that contains the screen width
   * @param height int that contains the screen height
   */
  public Levels(int width, int height) {
    
    canvasWidth = width;
    canvasHeight = height;
    p = new PauseScreen();
  }
  
  /**
   * The pause method is called to pause and unpause the game.  It stops audio recordings and records the timer's 
   * current time so that it can be reset later.  It also inverts the value of the boolean paused variable.
   */
  private void pause()
  {
    paused = !paused;
    if (paused)
    {
      AudioRecordings.alphabet[letters[currentLetter]-65].stop();
      if (letters[currentLetter]>=65 && letters[currentLetter] <= 89)
        AudioRecordings.alphabetB[letters[currentLetter]-65].stop();
      if (letters[currentLetter]>=66)
        AudioRecordings.alphabetA[letters[currentLetter]-66].stop();
      tempTime = t.getTimeElapsed();
    }
    else
    {
      t.setTimeElapsed(tempTime);
      if (getLevel() < 3)
      {
        AudioRecordings.playLevelsOneTwo(temp, letters[currentLetter]);
      }
      else
      {
        if (rand == 1)
        {
          AudioRecordings.alphabetB[letters[currentLetter]-65].start();
        }
        else
        {
          AudioRecordings.alphabetA[letters[currentLetter]-66].start();
        }
      }
    }
  }
  
  /**
   * This method constructs the necessary objects to play the game and adds them to the screen and its layout.  
   * A nested MouseAdapter class is necessary so that
   * the user's click location can be taken.  A mouse listener is added to the screen to register when a user clicks on 
   * a location.  Here, if statements determine if they have clicked on an appropriate location. A for loop is used to 
   * check each letter.  If statements are used to check the individual letters.  The gameStart() method is called.  
   * Audio is also stopped if the user clicks on specific locations.  A component listener handles window resize.
   */
  public void startup()
  {
    //box = new ContainerBox(0, 0, canvasWidth, canvasHeight, "grass2.jpg", Color.BLACK);
    
    canvas = new DrawCanvas();
    canvas2 = new DrawLetters();
    this.setLayout(new BorderLayout());
    this.add(canvas, BorderLayout.CENTER);
    this.add(canvas2, BorderLayout.SOUTH);
    
    addKeyListener(new KeyAdapter() { 
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 80)
        {
          pause();
        }
        else
        {
          if (e.getKeyCode() ==  KeyEvent.VK_F1)
          {
            Main.openCHM();
          }
        }
      }});
    
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        
        
        xCoord = e.getX();
        yCoord = e.getY();
        
        
        if (yCoord>=610 && yCoord<=670)
        {
          if (xCoord >= 1000 && xCoord <= 1060)
          {
            pause();
          }
        }
        if (!paused){
          if (yCoord>=610 && yCoord<=670)
          {
            if (xCoord >= 1070 && xCoord <= 1180)
            {
              if (getLevel() < 3)
              {
                AudioRecordings.playLevelsOneTwo(temp, letters[currentLetter]);
              }
              else
              {
                AudioRecordings.playLevelThree(rand, temp, letters[currentLetter]);
              }
            }
            return;
          }
          for (int z = NUM_BUBBLES-1;z>=0;z--)
          {
            if (Math.sqrt(Math.pow(xCoord-ball[z].returnHorizontalCenter(),2)+Math.pow(yCoord-ball[z].returnVerticalCenter(),2))<=50 && !ball[z].getWasClicked())
            {
              if (ball[z].getLetter() == letters[currentLetter])
              {
                AudioRecordings.alphabet[temp-65].stop();
                if (rand == 1)
                {
                  AudioRecordings.alphabetB[temp-65].stop();
                }
                else
                {
                  if (rand == 2)
                    AudioRecordings.alphabetA[temp-66].stop();
                }
                
                ball[z].setWasClicked(true);
                ball[z].setSpeed(0);
                ball[z].setRadius(-100);
                ball[z].setLocation(-100,-100);
                currentLetter++;
                if (currentLetter <= letters.length-1)
                {
                  AudioRecordings.effects[0].setMicrosecondPosition(0);
                  AudioRecordings.effects[0].start();
                  if (getLevel() < 3)
                  {
                    AudioRecordings.playLevelsOneTwo(temp, letters[currentLetter]);
                    temp = letters[currentLetter];
                  }
                  else
                  {
                    if (letters[currentLetter] == 'Z')
                    {
                      rand = 2;
                    }
                    else if (letters[currentLetter] == 'A')
                    {
                      rand = 1;
                    }
                    else
                    {
                      rand = (int)(Math.random()*2)+1;
                    }
                    AudioRecordings.playLevelThree(rand, temp, letters[currentLetter]);
                    temp = letters[currentLetter];
                  }
                }
              }
              else
              {
                AudioRecordings.effects[1].setMicrosecondPosition(0);
                AudioRecordings.effects[1].start();
              }
              if (currentLetter > letters.length-1)
              {
                currentLetter = 0;
                roundTimes[currentWord]=t.getTimeElapsed();
                if (currentWord==2)
                {
                  AudioRecordings.alphabet[letters[currentLetter]-65].stop();
                  if (letters[currentLetter]>=65 && letters[currentLetter] <= 89)
                    AudioRecordings.alphabetB[letters[currentLetter]-65].stop();
                  if (letters[currentLetter]>=66)
                    AudioRecordings.alphabetA[letters[currentLetter]-66].stop();
                  AudioRecordings.background[getLevel()].stop();
                  Main.frame.getContentPane().removeAll();
                  Main.frame.add(new DisplayTime(roundTimes[0], roundTimes[1], roundTimes[2], getLevel()));
                  Main.frame.repaint();
                  Main.frame.revalidate();
                  return;
                }
                else
                {              
                  AudioRecordings.effects[0].setMicrosecondPosition(0);
                  AudioRecordings.effects[0].start();
                  currentWord++;
                  word = words.get(currentWord).toUpperCase();
                  t.setTimeElapsed(0);
                  setBubbles();
                  
                  if (getLevel() < 3)
                  {
                    AudioRecordings.playLevelsOneTwo(temp, letters[currentLetter]);
                    temp = letters[currentLetter];
                  }
                  else
                  {
                    if (letters[currentLetter] == 'Z')
                    {
                      rand = 2;
                    }
                    else if (letters[currentLetter] == 'A')
                    {
                      rand = 1;
                    }
                    else
                    {
                      rand = (int)(Math.random()*2)+1;
                    }
                    AudioRecordings.playLevelThree(rand, temp, letters[currentLetter]);
                    temp = letters[currentLetter];
                  }
                }
              }
              break;
            }
          }
        }}
    });
    
    
    
    // Handling window resize.
    this.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        Component c = (Component)e.getSource();
        Dimension dim = c.getSize();
        canvasWidth = dim.width;
        canvasHeight = dim.height;
        // Adjust the bounds of the container to fill the window
        box.set(0, 0, canvasWidth, canvasHeight);
        box2.set(0, 0, canvasWidth, 100);
      }
    });
    
    // Start the ball bouncing
    gameStart();
  }
  
  /**
   * This method starts the bubble movement.
   */
  public void gameStart() {
    // Run the game logic in its own thread.
    gameThread = new Thread() {
      public void run() {
        while (true) {
          // Execute one time-step for the game 
          gameUpdate();
          // Refresh the display
          repaint();
          // Delay and give other thread a chance
          try {
            Thread.sleep(1000 / UPDATE_RATE);
          } catch (InterruptedException ex) {}
        }
      }
    };
    gameThread.start();  // Invoke GameThread.run()
  }
  
  /** 
   * This method updates the game objects, with proper collision detection and response. It uses a for loop to update
   * each object.
   */
  public void gameUpdate() {
    for (int z = 0; z < 26; z++)
    {
      ball[z].moveOneStepWithCollisionDetection(box);
    }
    //ball[0].moveOneStepWithCollisionDetection(box);
  }
  
  /** This inner class is a custom drawing panel for the bouncing ball. It overrides the paintComponent() method and 
    * uses for loops to draw each bubble.
    */
  class DrawCanvas extends JPanel {
    /**
     * This method paints components on the screen.  It uses a for loop to draw each bubble.
     * @param g Graphics passed in to allow painting on the frame.
     */
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);    // Paint background
      // Draw the box and the ball
      //draw(g);
      box.draw(g);
      if (paused)
      {
        g.drawImage(Images.screens[0],0,0,null);
      }
      else
      {
        for (int z = 0; z < NUM_BUBBLES; z++)
        {
          ball[z].draw(g);
        }
      }
    }
    
    /** This method is overriden to get the preferred size of the component. 
      * @return Dimension that indicates the preferred size
      */
    @Override
    public Dimension getPreferredSize() {
      return (new Dimension(canvasWidth, canvasHeight));
    }
  }
  
  /** This inner class is a custom drawing panel for the letters. It overrides the paintComponent() method and 
    * uses for loops to draw each bubble.
    */
  class DrawLetters extends JPanel {
    /**
     * This method paints components on the screen. 
     * @param g Graphics passed in to allow painting on the frame.
     */
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);    // Paint background
      box2.draw (g);
      g.setColor(Colors.boxes);
      for (int x = 0; x < letters.length; x++)
      {
        g.fillRect((x*110) + 300, 0, 100,100);
      }
      g.setFont(new Font("Courier New", Font.PLAIN, 15));
      g.setColor(Color.red);
      g.fillRect(1000, 20, 60, 60);
      g.setColor(Color.white);
      g.fillRect(1010, 30, 15, 40);
      g.fillRect(1035, 30, 15, 40);
      g.setColor(Color.black);
      g.fillRect(1070, 20, 120, 60);
      g.setColor(Color.white);
      g.drawString("Listen again!", 1075, 60);
      g.setFont(new Font("Courier New", Font.PLAIN, 40));
      g.setColor(Colors.letters);
      for (int x = -1; x < currentLetter; x++)
      {
        if (x != -1)
          g.drawString("" + letters[x], (x*100) + 350, 50);
      }
      // Draw the box and the ball
      g.setColor(Color.black);
      g.setFont(new Font("Comic Sans", Font.PLAIN, 50));
      if (paused)
        g.drawString("Time: " + tempTime, 70, 70);
      else
        g.drawString("Time: " + t.getTimeElapsed(), 70, 70);
      BufferedImage back = null;
    }
    
    /** This method is overriden to get the preferred size of the component. 
      * @return Dimension that indicates the preferred size
      */
    @Override
    public Dimension getPreferredSize() {
      return (new Dimension(canvasWidth, 100));
    }
  }
  
  
  /**
   * The setBubbles method resets the array of bubbles and draws them about the screen.
   */
  public void setBubbles()
  {
    if (getLevel()!=1)
    {
      Random rand = new Random();
      for (int q = 0; q < word.length(); q++)
      {
        letters[q] = word.charAt(q);
        ball[q].setLocation(rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10,rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10);
        ball[q].setSpeed(generateSpeed());
        //angleInDegree = rand.nextInt(360);
        ball[q].setRadius(radius);
        if (getLevel() == 2)
          ball[q].setSpeed( (int)(Math.random() * (3 - 1) + 1) + 1);
        else
          ball[q].setSpeed( (int)(Math.random() * (8 - 1) + 1) + 1);
        ball[q].setLetter(letters[q]);
        ball[q].setWasClicked(false);
      }
      for (int z = word.length(); z < 26; z++)
      {
        ball[z].setLocation(rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10,rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10);
        ball[z].setSpeed(generateSpeed());
        //angleInDegree = rand.nextInt(360);
        ball[z].setRadius(radius);
        if (getLevel() == 2)
          ball[z].setSpeed( (int)(Math.random() * (3 - 1) + 1) + 1);
        else
          ball[z].setSpeed( (int)(Math.random() * (8 - 1) + 1) + 1);
        ball[z].setLetter((char)(65+(int)(Math.random() * (25)) + 1));
        ball[z].setWasClicked(false);
      }
    }
    else
    {
      for (int q = 0; q < word.length(); q++)
      {
        letters[q] = word.charAt(q);
      }
      for (int z = 0 ; z < 26; z ++)
      {
        ball[z].setLocation(50+(z%9)*(2*radius+30), 50+(z/9)*(2*radius+30));
        ball[z].setRadius(radius);
        ball[z].setWasClicked(false);
      }
    }
  }
  
  /**
   * This method returns the proper speed of the bubbles.
   * @return an int representing the speed the bubbles should travel at
   */
  public abstract int generateSpeed();
  /**
   * This method returns the level that the user is currently playing.
   * @return an int representing which level the user is playing
   */
  public abstract int getLevel();
}