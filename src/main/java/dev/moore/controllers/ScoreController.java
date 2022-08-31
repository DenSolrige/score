package dev.moore.controllers;

import dev.moore.entities.Score;
import dev.moore.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    //Create
    @PostMapping("/scores")
    @ResponseBody
    public ResponseEntity<Score> addScore(@RequestBody Score score){
        Score savedScore = this.scoreService.addScore(score);
        return new ResponseEntity(savedScore, HttpStatus.CREATED);
    }

    //Read
    @GetMapping("/scores")
    @ResponseBody
    public List<Score> getAllScores(@RequestParam(required = false) String sortBy){
        if(sortBy == null){
            return this.scoreService.getAllScores("");
        }else{
            return this.scoreService.getAllScores("initials");
        }
    }

    @GetMapping("/scores/{id}")
    @ResponseBody
    public Score getScoreById(@PathVariable String id){
        int scoreId = Integer.parseInt(id);
        return scoreService.getScoreById(scoreId);
    }

    //Update
    @PutMapping("/scores")
    @ResponseBody
    public Score updateScore(@RequestBody Score score){
        Score savedScore = this.scoreService.updateScore(score);
        return savedScore;
    }

    //Delete
    @DeleteMapping("/scores/{id}")
    @ResponseBody
    public void deleteScore(@PathVariable String id){
        int scoreId = Integer.parseInt(id);
        scoreService.deleteScore(scoreId);
    }
}
