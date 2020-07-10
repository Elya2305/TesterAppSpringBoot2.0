package brainstorm.json.services;

import brainstorm.json.domains.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JsonParseService implements ParsingService {

    private RestTemplate restTemplate;
    private static final String URL = "https://opentdb.com/api.php?amount=5&category=18";

    @Autowired
    public JsonParseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public QuestionWrapper parse() {
        return restTemplate.getForObject(URL, QuestionWrapper.class);
    }
}
