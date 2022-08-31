package dev.moore.app;

import dev.moore.entities.Score;
import dev.moore.repos.ScoreRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional //putting it here applies it to all tests!
public class ScoreRepoTests {
    @Autowired
    ScoreRepo scoreRepo;

    @Test
    //@Transactional // there won't be any persistent changes to the database
    // all statements are rolled back
    void create_score(){
        Score score = new Score(0, "AAA", 1000);
        Score savedScore = this.scoreRepo.save(score);
        System.out.println(savedScore);
        Assertions.assertNotEquals(0,savedScore.getId());
    }

    @Test
    void all_scores(){
        Score score1 = new Score(0, "AAA", 1000);
        Score savedScore1 = this.scoreRepo.save(score1);
        Score score2 = new Score(0, "BBB", 1000);
        Score savedScore2 = this.scoreRepo.save(score2);
        List<Score> scores = this.scoreRepo.findAll();
        System.out.println(scores);
        Assertions.assertNotEquals(0, scores.size());
    }
}
