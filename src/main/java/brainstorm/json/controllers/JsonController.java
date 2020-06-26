package brainstorm.json.controllers;

import brainstorm.json.services.JsonParseService;
import brainstorm.json.services.ParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class JsonController {
    @Autowired
    private ParsingService parsingService;
    @Autowired
    private JsonParseService jsonParseService;

/*    @GetMapping(value = "/json")
    public String getQvs(Model model){
        List<QuestionJson> list = jsonParseService.getRandom5();
        return "test";
    }*/

}
