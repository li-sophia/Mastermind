// Author: Sophia Li 
// Description: Master Mind

import javax.swing.*; //libraries used to apply GUI
import java.awt.*;
import java.awt.event.*; 
import java.util.Scanner;// scanner to get input
import java.util.Random;

public class MasterMind extends JFrame implements ActionListener 
{
  JButton okbutton = new JButton ("OK"); //add buttons
  JButton red = new JButton("1"), orange = new JButton("2"), yellow = new JButton ("3"), green = new JButton("4"), blue = new JButton("5"), purple = new JButton("6"); //buttons with different colours - shows the 6 colours that the user can choose from
  JButton ans1 = new JButton(""), ans2 = new JButton (""), ans3 = new JButton(""), ans4 = new JButton(""); //stores user's guess of colours
  JButton code1 = new JButton(""), code2 = new JButton(""), code3 = new JButton(""), code4 = new JButton(""); //stores the answer
  JButton quitbutton = new JButton("QUIT");
  JButton submitbutton = new JButton("SUBMIT");
  JLabel guessnumLabel = new JLabel ("How many guesses would you like?", JLabel.RIGHT); //asks user how many guesses they want
  JLabel validationLabel = new JLabel ("");
  JLabel guessestakenLabel = new JLabel ("GUESSES TAKEN: 0", JLabel.CENTER); //keeps track of the number of guesses user has taken
  JLabel peg1Label = new JLabel ("Code Peg Position 1:  ",JLabel.RIGHT ); //add labels
  JLabel peg2Label = new JLabel ("Code Peg Position 2:  ", JLabel.RIGHT);
  JLabel peg3Label = new JLabel ("Code Peg Position 3:  ",JLabel.RIGHT);
  JLabel peg4Label = new JLabel ("Code Peg Position 4:  ",JLabel.RIGHT);
  JLabel whitepegLabel = new JLabel("   # of white key pegs:");
  JLabel blackpegLabel = new JLabel ("# of black key pegs:");
  JLabel instructionLabel = new JLabel ("Please enter the number corresponding to its colour. Hint: No duplicates!", JLabel.CENTER);
  JLabel spacing = new JLabel(" "), spacing1 = new JLabel (" "), spacing2 = new JLabel (" "), spacing3 = new JLabel (" "), spacing4 = new JLabel (" ", JLabel.CENTER); //spacing is used to fill up spaces for the gridlayout
  JLabel answerLabel = new JLabel ("ANSWER: ", JLabel.LEFT );
  JTextField guessnumField = new JTextField ("", 5);
  JTextField peg1Field = new JTextField ("", 3); //add textfields
  JTextField peg2Field = new JTextField("", 3);
  JTextField peg3Field = new JTextField("", 3);
  JTextField peg4Field = new JTextField ("", 3);
  JTextField whitepegField = new JTextField("",2);
  JTextField blackpegField = new JTextField("",2);
  JPanel pan1 = new JPanel(), pan2 = new JPanel(), pan3 = new JPanel(), pan4 = new JPanel(), pan5 = new JPanel(); //add panels
  
  //VARIABLES
  
  int c1, c2, c3, c4;
  int guessnum = 0, guesses = 0;
  int [] code = new int [4];  //declare array to store random generated code
  int [] codeattempt = new int [4]; //declare array to store user's attempt of the code
  int whitepegs = 0, blackpegs = 0, b = 0, c = 0; //stores number of white and black key pegs
  int random, random2; //stores the random values 
  
  //END OF VARIABLES
  
  static Scanner myScanner = new Scanner(System.in);//declare scanner class as a global variable
  
  Random r = new Random(); //declare random
  
  {
    
    //RANDOM CODE GENERATING PART
    
    for (int i = 0; i < 4; i++)
    {
      random = r.nextInt(6)+1; //assign random value between 1 and 6
      code[i] = random; //store random value into array
      
      for (int k = 0; k < i; k++)
      {
        if (code[i] == code[k]) //if random value is a duplicate of another value, a different value will be generated
        {
          do
          {
            random2 = r.nextInt(6)+1;
            code[i] = random2;
            if (k == 0)
            {
              b = 0;
              c = 0;
            }
            else if (k==1)
            {
              b = 1;
              c = 0;
            }
            else if (k==2)
            {
              b = 1;
              c = 2;
            }
          } while (code[i] == code[0]||code[i] == code[b] || code[i] == code[c]);
        }
      }
    }
  }
  
  //END OF RANDOM CODE GENERATING PART
  
  public static void changeColour (int codes, JButton answer) //method used to determine what colour to set the buttons
  {
    if (codes == 1)
    {
      answer.setBackground(Color.RED);
    }
    else if (codes == 2)
    {
      answer.setBackground(Color.ORANGE);
    }
    else if (codes == 3)
    {
      answer.setBackground(Color.YELLOW);
    }
    else if (codes == 4)
    {
      answer.setBackground(Color.GREEN);
    }
    else if (codes == 5)
    {
      answer.setBackground(Color.CYAN);
    }
    else if (codes == 6)
    {
      answer.setBackground(Color.MAGENTA);
    }
  }
  
  public MasterMind()
  {
    setTitle("Mastermind Game");
    setSize(600,500);
    
    okbutton.addActionListener(this); //adding listeners
    submitbutton.addActionListener(this);
    quitbutton.addActionListener(this);
    
    red.setBackground(Color.RED); //set buttons different colours
    orange.setBackground(Color.ORANGE);
    yellow.setBackground(Color.YELLOW);
    green.setBackground(Color.GREEN);
    blue.setBackground(Color.CYAN);
    purple.setBackground(Color.MAGENTA);
    
    red.setForeground(Color.WHITE);
    orange.setForeground(Color.WHITE);
    yellow.setForeground(Color.WHITE);
    green.setForeground(Color.WHITE);
    blue.setForeground(Color.WHITE);
    purple.setForeground(Color.WHITE);
    
    GridLayout layout1 = new GridLayout(5,5);
    FlowLayout layout2 = new FlowLayout();
    GridLayout layout3 = new GridLayout(5,4);
    
    setLayout(layout1);
    pan1.setLayout(layout2);
    pan2.setLayout(layout2);
    pan3.setLayout(layout2);
    pan4.setLayout(layout2);
    pan5.setLayout(layout2);
    
    pan1.add(guessnumLabel);
    pan1.add(guessnumField);
    pan1.add(okbutton);
    pan1.add(validationLabel);
    
    pan2.add(red);
    pan2.add(orange);
    pan2.add(yellow);
    pan2.add(green);
    pan2.add(blue);
    pan2.add(purple);
    
    pan3.add(blackpegLabel);
    pan3.add(blackpegField);
    pan3.add(whitepegLabel);
    pan3.add(whitepegField);
    
    pan4.add(instructionLabel);
    
    pan5.setLayout(layout3);
    pan5.add(peg1Label);
    pan5.add(peg1Field);
    pan5.add(ans1);
    pan5.add(spacing1);
    pan5.add(peg2Label);
    pan5.add(peg2Field);
    pan5.add(ans2);
    pan5.add(spacing2);
    pan5.add(peg3Label);
    pan5.add(peg3Field);
    pan5.add(ans3);
    pan5.add(spacing3);
    pan5.add(peg4Label);
    pan5.add(peg4Field);
    pan5.add(ans4);
    pan5.add(spacing4);
    pan5.add(spacing);
    pan5.add(submitbutton);
    pan5.add(quitbutton);
    pan5.add(guessestakenLabel);
    
    add(pan1);
    add(pan2);
    add(pan3);
    add(pan4);
    add(pan5);
    
    setVisible(true); //display GUI on the screen
  }
  
  public static void main (String[] args) throws Exception
  {
    
    MasterMind frame = new MasterMind();
    
  } // main method
  
  public void actionPerformed (ActionEvent event) 
  {
    String command = event.getActionCommand();
    
    for (int i = 0; i <4; i ++) 
    {
      System.out.println("code " + (i+1) + ": " + code[i]); //printing out the random code, for testing purposes
    }
    
    if (command.equals("OK")) //if user clicks okay button
    {
      System.out.println("ok button was pressed");
      guessnum = Integer.parseInt(guessnumField.getText());
      System.out.println(guessnum + " guesses");
      
      if (guessnum < 0) //if answer is invalid
      {
        validationLabel.setText("Invalid number. Try again.");
        submitbutton.setEnabled(false); //disables submit button until user enters valid answer
      }
      else if (guessnum > 0) //if answer is valid
      {
        validationLabel.setText("Valid.");
        okbutton.setEnabled(false); //disables ok button, so user can only enter a valid answer once
        submitbutton.setEnabled(true); //enables submit button once answer is valid
      }
    }
    
    if (command.equals("QUIT")) //if user clicks the quit button
    {
      System.out.println("quit button was pressed");
      submitbutton.setEnabled(false);
      quitbutton.setEnabled(false);
      okbutton.setEnabled(false);
      instructionLabel.setText("Sorry. You did not break the code.");
      pan2.removeAll(); //clears panel
      pan2.updateUI();
      pan2.add(answerLabel); //displays the real code
      pan2.add(code1);
      pan2.add(code2);
      pan2.add(code3);
      pan2.add(code4);
      c1 = code[0]; //int array values are stored into integer variables in order to be invoked by the changeColour method
      c2 = code[1];
      c3 = code[2];
      c4 = code[3];
      changeColour(c1,code1); //real code will be revealed when user quits
      changeColour(c2,code2); //invoking changeColour method
      changeColour(c3,code3);
      changeColour(c4,code4);
    }
    
    if (command.equals("SUBMIT"))
    {
      
      System.out.println("submit button was pressed");
      
      if ((peg1Field.getText().length() >0 ) && peg2Field.getText().length() > 0  && peg3Field.getText().length() >0 && peg4Field.getText().length() > 0) //textfield has to be filled in in order to parse
      {
        codeattempt[0] = Integer.parseInt(peg1Field.getText()); //converts string to integer
        codeattempt[1] = Integer.parseInt(peg2Field.getText());
        codeattempt[2] = Integer.parseInt(peg3Field.getText());
        codeattempt[3] = Integer.parseInt(peg4Field.getText());
        
        for (int j = 0; j < 4; j++)
        {
          System.out.println("user's choice, peg " + (j+1) + ": " + codeattempt[j]); //for testing purposes
        }
        
        c1 = codeattempt[0]; //int array values are stored into integer variables in order for values to be invoked by changeColour method
        c2 = codeattempt[1];
        c3 = codeattempt[2];
        c4 = codeattempt[3];
        
        changeColour(c1,ans1); //shows the colours that the user guessed
        changeColour(c2,ans2);
        changeColour(c3,ans3);
        changeColour(c4,ans4);
        
        for (int a = 0; a <4; a++)
        {
          if (code[a] == codeattempt[a]) //if peg has the correct colour and position
          {
            blackpegs++; //add 1 to the number of black key pegs
          }
        }
        
        if (blackpegs != 4) //if number of black key pegs are not equal to 4, user has not cracked the code 
        {
          for (int o = 0; o < 4; o++) //finds number of pegs that have the correct colour, but different position
          {
            for (int p = 0; p < 4; p++)
            {
              if (code[o] == codeattempt[p]) //checks each position 
              {
                whitepegs++; //add 1 to the number of white key pegs
              }
            }
          }
        }
        
        whitepegs = whitepegs - blackpegs; 
        
        if (blackpegs == 4)
        {
          pan2.removeAll(); //clears panel 
          pan2.updateUI();
          pan2.add(answerLabel); //reveals the code
          pan2.add(code1);
          pan2.add(code2);
          pan2.add(code3);
          pan2.add(code4);
          c1 = code[0];
          c2 = code[1];
          c3 = code[2];
          c4 = code[3];
          changeColour(c1,code1); 
          changeColour(c2,code2);
          changeColour(c3,code3);
          changeColour(c4,code4);
          whitepegs = 0;
          instructionLabel.setText("CONGRATULATIONS! YOU BROKE THE CODE. IT TOOK YOU " + (guesses+1) + " GUESS(ES)."); //outputs congratulations message with number of guesses that it took  
          submitbutton.setEnabled(false); //disable submit button
        }
        
        if ((guesses == guessnum) && (blackpegs!= 4)) //if the number of guesses taken is equal to the maximum number of guesses and number of black key pegs are not equal to 4
        {
          submitbutton.setEnabled(false); //disable submit button, so user cannot guess again
          instructionLabel.setText("Sorry. You did not break the code.");
          spacing4.setText("No more guesses left!");
          pan2.removeAll();
          pan2.updateUI();
          pan2.add(answerLabel);
          pan2.add(code1);
          pan2.add(code2);
          pan2.add(code3);
          pan2.add(code4);
          c1 = code[0];
          c2 = code[1];
          c3 = code[2];
          c4 = code[3];
          changeColour(c1,code1);
          changeColour(c2,code2);
          changeColour(c3,code3);
          changeColour(c4,code4);
        }
        
        blackpegField.setText(Integer.toString(blackpegs)); //converts integer value to string
        whitepegField.setText(Integer.toString(whitepegs));
        blackpegs = 0; //number resets to 0
        whitepegs = 0;
        guesses++;
        guessestakenLabel.setText("GUESSES TAKEN: " + guesses); //lets user keep track of how many guesses they took
        
      }
    }
  }
} // Master Mind  class
