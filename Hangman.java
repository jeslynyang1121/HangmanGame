import java.util.*;

// https://en.wikipedia.org/wiki/Hangman_(game)
public class Hangman {

    private static Scanner input = new Scanner(System.in);
    private static String word = wordInput(input);

    public Hangman(String word) {
        Game newGame = new Game(word);
        newGame.BuildGame();
        String answer = newGame.Guess(0, 0);
        ReplayOrNot(answer);
    }

    public static String wordInput(Scanner input) {
        System.out.println("Please input a word between one letter and ten letters (inclusive).");
        String word = input.next();
        while (word.length() > 10) {
            System.out.println("This word has more than ten letters. Please input a word between one letter and ten letters (inclusive).");
            word = input.next();
        }
        while (!(word == null)) {
            for (int i = 0; i < word.length(); i++) {
                if (!(Character.isLetter(word.charAt(i)))) {
                    System.out.println("This word contains a character that is not a letter. Please input a new word that only contains letters of the alphabet.");
                    word = input.next();
                }
            }
            break;
        }
        return word;
    }

    public static void ReplayOrNot(String a) {
        if (a.equals("replay")) {
            word = wordInput(input);
            Hangman game = new Hangman(word);
        } else if (a.equals("end")) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Hangman game = new Hangman(word);
    }
}
