package brainstorm.json.services;

import brainstorm.json.domains.Data;
import brainstorm.json.domains.QuestionJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class JsonParseService implements ParsingService {

    private RestTemplate restTemplate;
    private Random random;
    private static final String URL = "https://lip2.xyz/api/millionaire.php";


    @Autowired
    public JsonParseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.random = new Random();
    }

    @Override
    public QuestionJson parse(String url) {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate.getForObject(url, QuestionJson.class);
    }

    public List<Data> getRandom5(){
        List<Data> questionJsons = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            questionJsons.add(parse(URL + "?q=" +  (random.nextInt(4) + 1)).getData());
        }
        System.out.println(questionJsons.toString());
        return questionJsons;
    }
}
