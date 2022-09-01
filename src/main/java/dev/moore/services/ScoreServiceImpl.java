package dev.moore.services;

import dev.moore.entities.Score;
import dev.moore.exceptions.NegativeScoreException;
import dev.moore.exceptions.ScoreNotFoundException;
import dev.moore.repos.ScoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    ScoreRepo scoreRepo;

    @Override
    public Score addScore(Score score) {
        if(score.getPoints() < 0){
            throw new NegativeScoreException();
        }
        if(score.getInitials().length() > 3){
            score.setInitials(score.getInitials().substring(0,3));
        }
        score.setInitials(score.getInitials().toUpperCase());
        Score savedScore = this.scoreRepo.save(score);
        return savedScore;
    }

    @Override
    public Score getScoreById(int id) {
        Optional<Score> optionalScore = this.scoreRepo.findById(id);

        if(optionalScore.isPresent()){
            return optionalScore.get();
        }else{
            throw new ScoreNotFoundException();
        }
    }

    @Override
    public List<Score> getAllScores(String sortBy) {
        List<Score> scores = this.scoreRepo.findAll();
        if(sortBy.equals("initials")){
            scores.sort(new Comparator<Score>() {
                @Override
                public int compare(Score o1, Score o2) {
                    return o1.getInitials().compareTo(o2.getInitials());
                }
            });
        }else{
            scores.sort(new Comparator<Score>() {
                @Override
                public int compare(Score o1, Score o2) {
                    return o2.getPoints() - o1.getPoints();
                }
            });
        }
        return scores;
    }

    @Override
    public Score updateScore(Score score) {
        Score updatedScore = this.scoreRepo.save(score);
        return updatedScore;
    }

    @Override
    public void deleteScore(int id) {
        if(this.scoreRepo.existsById(id)){
            this.scoreRepo.deleteById(id);
        }else{
            throw new ScoreNotFoundException();
        }
    }
}
