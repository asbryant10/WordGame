package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.lang.*;


public class Main {

    //use oxford dictionary api
    public static String[] fourWords = {"cats", "dogs", "lost"};
    public static String[] fiveWords = {"coats","socks", "house", "robed", "green"};
    public static String targetWord = "";
    public static int level;


    public static void selectTargetWord(int numOfLetters){
        String[] dictionary;
        switch (numOfLetters){
            case 4: dictionary = fourWords.clone();
            break;

            case 5: dictionary = fiveWords.clone();
            break;

            default: dictionary = fourWords.clone();
        }

        //now that you gave a pool of words choose one randomly

        Random random = new Random();
        if(level != 1) {
            while (targetWord.equals("")) {
                int wordIdx = random.nextInt(dictionary.length);
                if (!repeatingLettersHuh(dictionary[wordIdx]).equals("")) {
                    targetWord = dictionary[wordIdx];
                }
            }
        }else {
            int wordIdx = random.nextInt(dictionary.length);
            targetWord = dictionary[wordIdx];
        }
    }

    private static boolean evaluateGuess(String guess) {
        //compare guess to target word
        //first see if any of the letter from the guess is in the target word

        String numLetterAcc = "";
        int correctPosAcc = 0;
        boolean result = false; //will only be true when numletterAcc = correctPosAcc = targetword.lenthg
        int targetLen = targetWord.length();

        for (char letterG : guess.toCharArray()){
            for (char letterT : targetWord.toCharArray()){
                //if guess letter matches a target letter
                //add to the numLetterAcc
                //but first check the num letter accumulator
                //to make sure that letter isnt already in there
                if(letterG == letterT){
                    String letterGString = Character.toString(letterG);
                    if(!(numLetterAcc.contains(letterGString))){
                        numLetterAcc = numLetterAcc + letterGString;
                    }

                }
            }
        }

        int numCorrectLetter = numLetterAcc.length();

        //Next see if any of those letter are in the correct spot

        for (int i = 0; i < numCorrectLetter; i++){
            //get the next letter out of num letter Acc
            String letter = numLetterAcc.substring(i, (i+1));
            if (guess.indexOf(letter) == targetWord.indexOf(letter)){
                correctPosAcc++;
            }
        }

        if (numCorrectLetter == targetLen && correctPosAcc == targetLen){
            result = true;
        }

        if(guess.length() > 0) {
            System.out.println(guess + " " + numCorrectLetter + " " + correctPosAcc);
        }

        return result;
    }

    private static String repeatingLettersHuh(String targetWord){
        int targetLen = targetWord.length();
        char[] targetCharArr = targetWord.toCharArray();

        //repeating letter accumulator
        String repeatingL = "";

        //find the repeating letter(s)
        for (char letter : targetCharArr) {
            for (int letterIdx = 0; letterIdx < targetLen; letterIdx++) {
                //make sue you are not comparing letter to itself
                //then check if letter equals a char in target word that has a different index
                if (targetWord.indexOf(letter) != letterIdx
                        && letter == targetCharArr[letterIdx]
                        && (!repeatingL.contains(Character.toString(letter)))){
                    repeatingL = repeatingL + letter;
                }
            }
        }

        //youre going to have to do a different check to see how many times each letter repates itself
        //use this function in the new check
        return repeatingL;
    }

    private static boolean evaluateGuessSingleRepeat(String guess) {
        //compare guess to target word
        //first see if any of the letter from the guess is in the target word

        String numLetterAcc = "";
        int correctPosAcc = 0;
        boolean result = false; //will only be true when numletterAcc = correctPosAcc = targetword.lenthg
        int targetLen = targetWord.length();



        for (char letterG : guess.toCharArray()){
            for (char letterT : targetWord.toCharArray()){
                //if guess letter matches a target letter
                //add to the numLetterAcc
                //but first check the num letter accumulator
                //to make sure that letter isnt already in there AND
                //there
                if(letterG == letterT){
                    String letterGString = Character.toString(letterG);
                    //if guess letter is not in letter accumulator
                    //add letter g to numLetAcc
                    if(!(numLetterAcc.contains(letterGString))) {
                        numLetterAcc = numLetterAcc + letterGString;
                        //System.out.println("Num Letter Acc from if "+numLetterAcc);
                        break;
                    }
                    //else (if letterg is in accumulator)
                    //check to see if letterG is in repeatingLetter Acc
                    //if so add it to numLetterAcc

                    //You have a bug because the one s in the guess is being counted towards both s' in the target

                    //try removing the letter in question from the word and
                    //then check if that substring contains the letter in question
                    else if (repeatingLettersHuh(targetWord).contains(letterGString)){
                        numLetterAcc = numLetterAcc + letterGString;
                        //System.out.println("Num Letter Acc from else "+numLetterAcc);
                        break;
                    }

                }
            }
        }

        int numCorrectLetter = numLetterAcc.length();

        //Next see if any of those letter are in the correct stop

        for (int i = 0; i < numCorrectLetter; i++){
            //get the next letter out of num letter Acc
            String letter = numLetterAcc.substring(i, (i+1));
            if (guess.indexOf(letter) == targetWord.indexOf(letter)){
                correctPosAcc++;
            }
        }

        if (numCorrectLetter == targetLen && correctPosAcc == targetLen){
            result = true;
        }

        if(guess.length() > 0) {
            System.out.println(guess + " " + numCorrectLetter + " " + correctPosAcc);
        }

        return result;
    }

    public static void ifElseCheck(){

    }

    public static void main(String[] args) {

        //System.out.println(repeatingLettersHuh("street"));

        // write your code here
        // This main method will invoke all other methods my program needs

        //Ask the user how many letters they want
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the desired word length . . .");
        int numLetters = input.nextInt();
        System.out.println("Choose difficulty level. Enter: " +
                "\n1 for no repeating letter" +
                "\n2 for one letter repeating once" +
                "\n3 for one letter repeating more than once OR more than one repeating letter");
        level = input.nextInt();
        System.out.println("A " + numLetters + " letter word of level " + level + " difficulty has been chosen for you! \nYou may begin guessing.");
        //Eventually add in choice options for repeats


        selectTargetWord(numLetters);

        //capture entered word
        String guess;
        if(level == 1) {
            do {
                guess = input.nextLine();
                //if (guess.length() != numLetters)
            } while (!evaluateGuess(guess));
            //if evalute word returns true then st

        }else{
            do {
                guess = input.nextLine();
                //if (guess.length() != numLetters)
            } while (!evaluateGuessSingleRepeat(guess));
            //if evalute word returns true then st
        }

    }



}
