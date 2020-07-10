package brainstorm.json.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionWrapper {
    private Integer response_code;
    private List<Question> results;

    public List<Question> getResults() {
        return results;
    }

    public void setResults(List<Question> results) {
        this.results = results;
    }

    public Integer getResponse_code() {
        return response_code;
    }

    public void setResponse_code(Integer response_code) {
        this.response_code = response_code;
    }

    @Override
    public String toString() {
        return "QuestionWrapper{" +
                "response_code=" + response_code +
                ", results=" + results +
                '}';
    }
}
