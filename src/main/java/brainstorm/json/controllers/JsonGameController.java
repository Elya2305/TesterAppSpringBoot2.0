package brainstorm.json.controllers;

import brainstorm.json.domains.Question;
import brainstorm.json.domains.User;
import brainstorm.json.services.GameService;
import brainstorm.json.services.JsonParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class JsonGameController {
    JsonParseService service;
    List<Question> questions;
    private List<String> userAnswers;
    private int counter;
    private GameService gameService;

    @Autowired
    public JsonGameController(JsonParseService service, GameService gameService) {
        this.service = service;
        this.gameService = gameService;
        this.userAnswers = new ArrayList<>();
        this.questions = service.parse().getResults();
        System.out.println(questions);
    }

    @GetMapping("/game")
    public String startGame(Model model,  @AuthenticationPrincipal User user){
        if(counter == 0) {
        userAnswers.clear();
        questions = service.parse().getResults();
        }
        if(counter > 0){
            userAnswers.add("");
        }
        if(counter == 5){
            return endGame(model, user, false);
        }
        return getNewQuestion(model);
    }

    @PostMapping("/game")
    public String game(@RequestParam String answer, @AuthenticationPrincipal User user, Model model){
        gameService.checkAnswer(questions.get(counter-1).getCorrect_answer().equals(answer), counter==5, user);
        userAnswers.add(answer);
        if(counter == 5){
            return endGame(model, user, false);
        }
        return getNewQuestion(model);
    }

    private String getNewQuestion(Model model) {
        List<String> answers = questions.get(counter).getIncorrect_answers();
        answers.add(questions.get(counter).getCorrect_answer());
        Collections.shuffle(answers);
        model.addAttribute("question", questions.get(counter++).getQuestion());
        model.addAttribute("answers",answers);
        return "game";
    }

    private String endGame(Model model, User user, boolean stopGame) {
        counter = 0;
        gameService.endGame(user);
        gameService.getVerdicts(model, user, stopGame);
        System.out.println("END: " + userAnswers.toString());
        model.addAttribute("questions", questions);
        model.addAttribute("answers", userAnswers);
        return "end";
    }


    @GetMapping("/endgame")
    public String endGame(@AuthenticationPrincipal User user, Model model){
        return endGame(model, user, true);
    }
}
