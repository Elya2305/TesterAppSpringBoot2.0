package brainstorm.json.controllers;


import brainstorm.json.domains.Data;
import brainstorm.json.services.JsonParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class JsonGameController {
    private List<Data> questions;
    private JsonParseService jsonService;
    private List<String> rightAnswers;
    private int counter;

    @Autowired
    public JsonGameController(JsonParseService jsonService) {
        this.jsonService = jsonService;
        this.questions = jsonService.getRandom5();
        rightAnswers = questions.stream()
                .map(o -> o.getAnswers().get(0))
                .collect(Collectors.toList());
    }

    @GetMapping("/start")
    public String startGame(Model model){
        if(counter == 5){
            return "end";
        }
        List<String> answers = questions.get(counter++).getAnswers();
        Collections.shuffle(answers);
        model.addAttribute("question", questions.get(counter).getQuestion());
        model.addAttribute("answers",answers);
        return "start";
    }




}
