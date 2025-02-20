import java.util.*;

import static java.lang.Thread.sleep;

public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";

    public static void main(String[] args) throws InterruptedException {
        displayIntro();

        Scanner sc = new Scanner(System.in);
        System.out.print(YELLOW + "\nEnter name of 1st person: " + RESET);
        String s1 = sc.nextLine().toLowerCase().replaceAll("\\s", "");

        System.out.print(YELLOW + "Enter name of 2nd person: " + RESET);
        String s2 = sc.nextLine().toLowerCase().replaceAll("\\s", "");

        System.out.print(PURPLE + "\nCalculating Compatibility" + RESET);
        loadingEffect();

        int remainingCount = calculateRemainingLetters(s1, s2);
        String result = fCounter(remainingCount);

        System.out.println(GREEN + "\n Relationship Status: " + RED + result + RESET);
        sc.close();
    }

    public static void displayIntro() throws InterruptedException {
        System.out.println(CYAN + "===================================");
        System.out.println(" WELCOME TO THE FLAMES GAME ");
        System.out.println("===================================" + RESET);

        System.out.print(BLUE + "Loading Game" + RESET);
        loadingEffect();
        System.out.println();
    }

    public static void loadingEffect() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            sleep(500);
        }
        System.out.println();
    }

    public static int calculateRemainingLetters(String s1, String s2) {
        ArrayList<Character> ch1 = new ArrayList<>();
        for (char ch : s1.toCharArray()) {
            ch1.add(ch);
        }

        ArrayList<Character> ch2 = new ArrayList<>();
        for (char ch : s2.toCharArray()) {
            ch2.add(ch);
        }

        for (int i = 0; i < ch1.size(); i++) {
            if (ch2.contains(ch1.get(i))) {
                ch2.remove(Character.valueOf(ch1.get(i)));
                ch1.remove(i);
                i--;
            }
        }

        return ch1.size() + ch2.size();
    }

    public static String fCounter(int count) {
        if (count == 0) {
            return "GAY";
        }
        HashMap<Character, String> map = new HashMap<>();
        map.put('f', "Friends");
        map.put('l', "Lovers");
        map.put('a', "Affair");
        map.put('m', "Marriage");
        map.put('e', "Enemies");
        map.put('s', "Siblings");

        StringBuffer sb = new StringBuffer("flames");

        int index = 0;
        while (sb.length() > 1) {
            index = (index + count - 1) % sb.length();
            sb.deleteCharAt(index);
        }

        return map.get(sb.charAt(0));
    }
}
