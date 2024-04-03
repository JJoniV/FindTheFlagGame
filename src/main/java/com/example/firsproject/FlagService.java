package com.example.firsproject;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FlagService {

    private int correctAnswers = 0;
    private int alreadyAnswered = 1;
    private String rightOrWrongText = "";

    public String getRightOrWrongText() {
        return rightOrWrongText;
    }

    public void setRightOrWrongText(String rightOrWrongText) {
        this.rightOrWrongText = rightOrWrongText;
    }



    private String endTextMessage = "";
    private String [] arrayOfButtons = new String[4];
    private String currentFlag;

    private int []alreadyUsedFlags = new int[52];
    private boolean gameEnded = false;

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void setAlreadyAnswered(int alreadyAnswered) {
        this.alreadyAnswered = alreadyAnswered;
    }

    public void setEndTextMessage(String endTextMessage) {
        this.endTextMessage = endTextMessage;
    }



    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getAlreadyAnswered() {
        return alreadyAnswered;
    }

    public String getEndTextMessage() {
        return endTextMessage;
    }


    public int[] getAlreadyUsedFlags() {
        return alreadyUsedFlags;
    }

    public static void setArrayToZero(int[] array) {
        for (int i = 0; i < array.length; i++) {
                array[i] = 0;
        }
    }


    public void setCurrentFlag(String idk){
         this.currentFlag = idk;
    }
    public String getCurrentFlag(){
        return this.currentFlag;
    }


    public String[] getArrayOfButtons() {
        return arrayOfButtons;
    }

    public int randomNumberFlag(){
        Random random = new Random();
        int temp = random.nextInt(0,52);
        while (alreadyUsedFlags[temp] == 1){
            temp = random.nextInt(0,52);
        }
        alreadyUsedFlags[temp]++;
        alreadyAnswered++;
        return temp;
    }
    private List<String> Flags = List.of("Albania.png","Honduras.jpg","Brazil.png","Ireland.jpg","Northern Ireland.png","Greenland.png","Iceland.png","Faroe Island.png","Isle of Man.png","Norway.jpg"
    ,"Andorra.png","Austria.png","Belarus.png","Belgium.png","Bosnia and Herzegovina.png"
    ,"Bulgaria.png","Croatia.png","Cyprus.png","Czech Republic.png","Denmark.png","Estonia.png","Finland.png","France.png","Germany.png","Gibraltar.png","Greece.png",
     "Hungary.png","Italy.png","Kosovo.png","Latvia.png","Lithuania.png","Luxembourg.png","Malta.png","Moldova.png","Monaco.png","Montenegro.png","Netherlands.png","North Macedonia.png",
     "Papal States.png","Poland.png","Portugal.png","Romania.png","Russia.png","San Marino.png","Serbia.png","Slovakia.png","Slovenia.png","Spain.png","Sweden.png","Switzerland.png","Turkey.png","Ukraine.png");
    private List<String> buttonNames = List.of("Albania","Honduras","Brazil","Ireland","Northern Ireland","Greenland","Iceland","Faroe Island","Isle of Man","Norway",
            "Andorra","Austria","Belarus","Belgium","Bosnia and Herzegovina","Bulgaria","Croatia","Cyprus","Czech Republic","Denmark","Estonia","Finland","France","Germany","Gibraltar","Greece",
            "Hungary","Italy","Kosovo","Latvia","Lithuania","Luxembourg","Malta","Moldova","Monaco","Montenegro","Netherlands","North Macedonia",
            "Papal States","Poland","Portugal","Romania","Russia","San Marino","Serbia","Slovakia","Slovenia","Spain","Sweden","Switzerland","Turkey","Ukraine");
    private int check;
    public int getCheck() {
        return this.check;
    }
    public void setCheckNUll() {
        this.check = 0;
    }
    public List<String> getFlags(){
        return this.Flags;
    }
    public List<String> getButton(){
        return this.buttonNames;
    }
    public void checkAnswer(String theFlag,String theButton){
        String toBechecked = theFlag.substring(0,theFlag.length() - 4);
        if (theButton.matches(toBechecked)){
            this.check = 1;
        } else {
            this.check = -1;
        }
    }
}
