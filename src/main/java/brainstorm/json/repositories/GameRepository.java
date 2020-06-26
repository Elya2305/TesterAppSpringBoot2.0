package brainstorm.json.repositories;


import brainstorm.json.domains.Game;
import brainstorm.json.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findByUser(User user);

    List<Game> findByUserAndWin(User user, boolean win);

    @Query("select max(g.score) from Game g where g.user=:user")
    Integer findMaxScore(@Param("user") User user);

    @Query("select avg(g.score) from Game g where g.user=:user")
    Double findAvrScore(@Param("user") User user);
}
