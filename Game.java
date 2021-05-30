import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Game {
    DrawingPanel p = new DrawingPanel(1000,800);
    Graphics g = p.getGraphics();
    private final String word;
    private final int length;
    ArrayList<String> guessed = new ArrayList<>();
    ArrayList<String> w;
    Scanner input = new Scanner(System.in);

    //Game constructor
    public Game(String input) {
        word = input;
        length = input.length();
        w = wordAsList();
    }

    //builds blank board
    public void BuildGame() {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(10));

        g.drawLine(50, 50, 50, 500);
        g.drawLine(50, 50, 300, 50);
        g.drawLine(300, 50, 300, 100);
        g.drawLine(100, 50, 50, 100);
        g.drawLine(25, 500, 75, 500);

        for (int i = 0; i < length; i++) {
            g.drawLine(25+(100*i), 700, 75+(100*i), 700);
        }
    }

    /*guess a letter
        if correct -> write letter on board
        if wrong   -> call Hang method
     */
    public void Guess(int numMistakes, int numCorrect) {
        if ((numMistakes == 6) || (numCorrect == length)) {
            GameEnd(numMistakes);
        } else {
            System.out.println("Please guess a letter.");
            String gLetter = input.next().toLowerCase();
            while (gLetter.length() > 1) {
                System.out.println("Your input has more than one letter. Please guess one letter.");
                gLetter = input.next().toLowerCase();
            }
            int index = w.indexOf(gLetter);

            if (guessed.contains(gLetter)) {
                System.out.println("You have already guessed this letter before.\nThese are all the words you have guessed so far: ");
                printGuesses(guessed);
                System.out.println("Please guess a different letter.");
            } else {
                guessed.add(gLetter);

                if (word.contains(gLetter)) {
                    System.out.println("Correct");
                    while (index != -1) {
                        numCorrect++;
                        g.drawString(gLetter, 25 + (100 * index), 690);
                        w.set(index, "-");
                        index = w.indexOf(gLetter);
                    }
                } else {
                    System.out.println("Wrong");
                    numMistakes++;
                    Hang(numMistakes);
                }
            }
            Guess(numMistakes, numCorrect);
        }
    }

    //draws part of hanging man after mistake
    public void Hang(int m) {
        if (m == 1) {
            g.drawOval(250, 100, 100, 100);	//head
        } else if (m == 2) {
            g.drawLine(300, 200, 300, 400);		//body
        } else if (m == 3) {
            g.drawLine(300, 275, 200, 175);		//left arm
        } else if (m == 4) {
            g.drawLine(300, 275, 400, 175);		//right arm
        } else if (m == 5) {
            g.drawLine(300, 400, 200, 500);		//left leg
        } else if (m == 6) {
            g.drawLine(300, 400, 400, 500);		//right leg
        }
    }

    //returns guessing word as ArrayList
    public ArrayList<String> wordAsList() {
        ArrayList<String> wordList = new ArrayList<>();
        String letter = "";
        for (int i = 0; i < length; i++) {
            letter = word.charAt(i) + "";
            wordList.add(letter);
        }
        return wordList;
    }

    //prints previous letter guesses
    public void printGuesses(ArrayList<String> g) {
        for (String guess : g) {
            System.out.print(guess + " ");
        }
    }

    /*starts another game if player types p
      ends game if anything else is typed
     */
    public void GameEnd(int mistakes) {
        if (mistakes == 6) {
            System.out.println("Game Over!");
            System.out.println("The word was " + word);
        } else {
            System.out.println("Congrats! You guessed the word!");
        }
        Scanner input = new Scanner(System.in);
        System.out.println("\nIf you would like to play again, type p. If not, type anything else.");
        String word = input.next();
        if (word.equals("P")||word.equals("p")){
            Hangman game = new Hangman(word);
        } else {
            System.exit(0);
        }
    }

}
