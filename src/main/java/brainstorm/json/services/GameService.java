package brainstorm.json.services;

import brainstorm.json.domains.Game;
import brainstorm.json.domains.User;
import brainstorm.json.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GameService {
    private int score;
    private GameRepository gameRepository;
    private Game game;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.score = 0;
    }

    public void checkAnswer(boolean right, boolean endGame, User user){
        if(right) score++;
    }

    public void endGame(User user) {
        game = new Game(user, score, score >= 3);
        gameRepository.save(game);
    }

    public void getVerdicts(Model model, User user, boolean stopGame) {
        if (stopGame) {
            game = new Game(user, score, score >= 3);
            gameRepository.save(game);
        }
        if(game.getScore() >= 3){
            model.addAttribute("verdict","Congratulations! You've won. Your score is " + game.getScore());
            model.addAttribute("win", true);
        }else {
            model.addAttribute("verdict", "You've lost :(. Your score is " + game.getScore() + " but you need at least 3");
            model.addAttribute("win", false);
        }
    }

}
