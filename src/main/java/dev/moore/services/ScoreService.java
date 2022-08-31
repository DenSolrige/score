package dev.moore.services;

import dev.moore.entities.Score;

import java.util.List;

public interface ScoreService {
    // Create
    Score addScore(Score score);

    // Read
    Score getScoreById(int id);
    List<Score> getAllScores(String sortBy);

    // Update
    Score updateScore(Score score);

    // Delete
    void deleteScore(int id);
}
