package brainstorm.json.services;
import brainstorm.json.domains.QuestionJson;

public interface ParsingService {
    QuestionJson parse(String url);
}
