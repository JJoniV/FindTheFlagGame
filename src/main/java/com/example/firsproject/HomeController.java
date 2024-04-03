package com.example.firsproject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


@Controller
public class HomeController {

    private final FlagService flagService;
    public HomeController(FlagService flagService){
        this.flagService = flagService;
    }


    @GetMapping("/")
    public String MainPage() {
        return "index";
    }

    @PostMapping("/FlagGame")
    public String gameIndex(Model model){
        model.addAttribute("AlreadyAnswered",flagService.getAlreadyAnswered());
        if (flagService.getAlreadyAnswered() == 53){
            if (flagService.isGameEnded()){
                model.addAttribute("AlreadyAnswered",52);
                flagService.setEndTextMessage("The End");
                model.addAttribute("Flag",flagService.getCurrentFlag());
                String []tempArray;
                tempArray = flagService.getArrayOfButtons();
                model.addAttribute("button1",tempArray[0]);
                model.addAttribute("button2",tempArray[1]);
                model.addAttribute("button3",tempArray[2]);
                model.addAttribute("button4",tempArray[3]);
                model.addAttribute("EndTextMessage",flagService.getEndTextMessage());
                model.addAttribute("rightOrWrongText",flagService.getRightOrWrongText());
                model.addAttribute("correctAnswers",flagService.getCorrectAnswers());
                return "gameIndex";

            }
                model.addAttribute("AlreadyAnswered", 52);
                flagService.setEndTextMessage("The End");
                model.addAttribute("Flag", flagService.getCurrentFlag());
                String []tempArray;
                tempArray = flagService.getArrayOfButtons();
                model.addAttribute("button1", tempArray[0]);
                model.addAttribute("button2", tempArray[1]);
                model.addAttribute("button3", tempArray[2]);
                model.addAttribute("button4", tempArray[3]);
                model.addAttribute("EndTextMessage", flagService.getEndTextMessage());

                if (flagService.getCheck() == 1) {
                    flagService.setRightOrWrongText("Bravooo");
                    model.addAttribute("rightOrWrongText", flagService.getRightOrWrongText());
                    flagService.setCorrectAnswers(flagService.getCorrectAnswers() + 1);
                    model.addAttribute("correctAnswers", flagService.getCorrectAnswers());
                } else if (flagService.getCheck() == -1) {
                    flagService.setRightOrWrongText("Nooooooo");
                    model.addAttribute("rightOrWrongText", flagService.getRightOrWrongText());
                    model.addAttribute("correctAnswers", flagService.getCorrectAnswers());
                } else if (flagService.getCheck() == 0) {
                    flagService.setRightOrWrongText("");
                    model.addAttribute("rightOrWrongText", flagService.getRightOrWrongText());
                    model.addAttribute("correctAnswers", flagService.getCorrectAnswers());
                }
                flagService.setGameEnded(true);
                return "gameIndex";

        }

        model.addAttribute("EndTextMessage",flagService.getEndTextMessage());
        String []tempArray = this.flagService.getArrayOfButtons();
        int []rightAnswers = new int[52];
        int rngNumber = this.flagService.randomNumberFlag();
        this.flagService.setCurrentFlag(flagService.getFlags().get(rngNumber));
        String flagTemp = flagService.getCurrentFlag();
        rightAnswers[rngNumber]++;
        model.addAttribute("Flag",flagTemp);
        Random random = new Random();
        int RngButton = random.nextInt(0,4);
        tempArray[RngButton] = flagService.getButton().get(rngNumber);
        model.addAttribute("button" + (RngButton + 1),tempArray[RngButton]);
        for (int i = 0; i < 4; i++){
            if (i != RngButton){
                int RngButtonNames = random.nextInt(0,52);
                while(rightAnswers[RngButtonNames] == 1){
                    RngButtonNames = random.nextInt(0,52);
                }
                tempArray[i] = flagService.getButton().get(RngButtonNames);
                model.addAttribute("button" + (i+1),tempArray[i]);
                rightAnswers[RngButtonNames]++;
            }
        }
        if (flagService.getCheck() == 1){
            model.addAttribute("rightOrWrongText","Bravooo");
            flagService.setCorrectAnswers(flagService.getCorrectAnswers() + 1);
            model.addAttribute("correctAnswers",flagService.getCorrectAnswers());
        } else if(flagService.getCheck() == -1){
            model.addAttribute("rightOrWrongText","Nooooooo");
            model.addAttribute("correctAnswers",flagService.getCorrectAnswers());
        } else if (flagService.getCheck() == 0){
            model.addAttribute("rightOrWrongText","");
            model.addAttribute("correctAnswers",flagService.getCorrectAnswers());
        }
        return "gameIndex";
    }


    @PostMapping("/reset")
    public String reset(Model model) {
        flagService.setCheckNUll();
        flagService.setEndTextMessage("");
        model.addAttribute("EndTextMessage",flagService.getEndTextMessage());
        flagService.setAlreadyAnswered(1);
        model.addAttribute("AlreadyAnswered",flagService.getAlreadyAnswered());
        flagService.setCorrectAnswers(0);
        model.addAttribute("correctAnswers",flagService.getCorrectAnswers());
        flagService.setArrayToZero(flagService.getAlreadyUsedFlags());
        flagService.setGameEnded(false);
        model.addAttribute("rightOrWrongText","");
        String []tempArray = this.flagService.getArrayOfButtons();
        int []rightAnswers = new int[53];
        int rngNumber = this.flagService.randomNumberFlag();

        this.flagService.setCurrentFlag(flagService.getFlags().get(rngNumber));
        String flagTemp = flagService.getCurrentFlag();
        rightAnswers[rngNumber]++;
        model.addAttribute("Flag",flagTemp);
        Random random = new Random();
        int RngButton = random.nextInt(0,4); // rng number for the right answer
        tempArray[RngButton] = flagService.getButton().get(rngNumber);
        model.addAttribute("button" + (RngButton + 1),tempArray[RngButton]);
        for (int i = 0; i < 4; i++){
            if (i != RngButton){
                int RngButtonNames = random.nextInt(0,52);
                while(rightAnswers[RngButtonNames] == 1){
                    RngButtonNames = random.nextInt(0,52);
                }
                tempArray[i] = flagService.getButton().get(RngButtonNames);
                model.addAttribute("button" + (i+1),tempArray[i]);
                rightAnswers[RngButtonNames]++;
            }
        }
        return "gameIndex";
    }

    @PostMapping("/button1")
    public String button1() {
        String []tempArray = flagService.getArrayOfButtons();
        flagService.checkAnswer(flagService.getCurrentFlag(),tempArray[0]);
        return "forward:/FlagGame";
    }

    @PostMapping("/button2")
    public String button2() {
        String []tempArray = flagService.getArrayOfButtons();
        flagService.checkAnswer(flagService.getCurrentFlag(),tempArray[1]);
        return "forward:/FlagGame";
    }

    @PostMapping("/button3")
    public String button3() {
        String []tempArray = flagService.getArrayOfButtons();
        flagService.checkAnswer(flagService.getCurrentFlag(),tempArray[2]);
        return "forward:/FlagGame";
    }


    @PostMapping("/button4")
    public String button4() {
        String []tempArray = flagService.getArrayOfButtons();
        flagService.checkAnswer(flagService.getCurrentFlag(),tempArray[3]);
        return "forward:/FlagGame";
    }






}
