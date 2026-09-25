package utils;

import java.util.Random;

public class RandomFunctions {
    public static String getRandomEmail(String text) {
        int randomFirstPartEmail = (int) (Math.random() * (999 - 100) + 100);
        int randomSecondPartEmail = (int) (Math.random() * (999 - 100) + 100);
        String randomEmail = text + randomFirstPartEmail + "@" + text + randomSecondPartEmail + ".com";
        return randomEmail;
    }

    public static int getRandomNumber(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String getRandomText(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 4;
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
