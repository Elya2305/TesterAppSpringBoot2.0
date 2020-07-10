# Millionaire game 2.0.

This is the 2th version of the simulation of
millionaire game! It's identical 
to [this](https://github.com/Elya2305/TesterAppSpringBoot)
one (check out the [description](https://github.com/Elya2305/TesterAppSpringBoot/blob/master/README.md)), 
BUT this time questions are given
not from local in-memory H2 db, but
from https://opentdb.com in json format.
So it'll be much 
more interesting as there're random
questions about IT.

This guy made all magic: 
```java
@Service
public class JsonParseService implements ParsingService {

    private RestTemplate restTemplate;
    private static final String URL = "https://opentdb.com/api.php?amount=5&category=18";

    @Override
    public QuestionWrapper parse() {
        return restTemplate.getForObject(URL, QuestionWrapper.class);
    }
}
``` 
For more information check out 
this [tutorial](https://www.baeldung.com/rest-template) 



Interface and used stack (in particular Spring) stayed 
the same. To test the project right click on class ApplicationSpringBoot > Run as .. > Spring Boot App.
 Then go to http://localhost:8080.
 
 Good luck :)
 
