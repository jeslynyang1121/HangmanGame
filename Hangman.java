import java.util.*;

// https://en.wikipedia.org/wiki/Hangman_(game)
public class Hangman {

    private static Scanner input = new Scanner(System.in);
    private static String word = "";

    public Hangman(String word) {
        word = wordInput(input);
        Game newGame = new Game(word);
        newGame.BuildGame();
        newGame.Guess(0, 0);
        //ReplayOrNot(answer);
    }

    public static String wordInput(Scanner input) {
        System.out.println("Please input a word between one letter and ten letters (inclusive).");
        String word = input.next();
        while (!(word == null)) {
            while (word.length() > 10) {
                System.out.println("This word has more than ten letters. Please input a word between one letter and ten letters (inclusive).");
                word = input.next();
            }

            for (int i = 0; i < word.length(); i++) {
                if (!(Character.isLetter(word.charAt(i)))) {
                    System.out.println("This word contains a character that is not a letter. Please input a new word that only contains letters of the alphabet.");
                    word = input.next();
                }
            }
            break;
        }
        for (int i = 0; i <= 10; i++) {
            System.out.println();
        }
        return word;
    }

    public static void ReplayOrNot(String a) {
        System.out.println(a + "done");
        a = a.toLowerCase();
        if (a.equals("p")) {
            word = wordInput(input);
            Hangman game = new Hangman(word);
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Hangman game = new Hangman(word);
    }
}
