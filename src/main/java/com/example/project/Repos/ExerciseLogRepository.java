package com.example.project.Repos;

import com.example.project.Models.ExerciseLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import java.util.Optional;


@Repository

public class ExerciseLogRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExerciseLogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<ExerciseLog> findById(Long logId) {
        String sql = "SELECT * FROM ExerciseLog WHERE log_id = ?";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setLong(1, logId);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(exerciseLogRowMapper().mapRow(resultSet, 1));
            } else {
                System.out.println("No Exercise Log found for log ID: " + logId);
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean save(ExerciseLog log) {
        String sql = "INSERT INTO ExerciseLog (routine_id, exercise_name, num_sets, num_reps, duration, weight) VALUES (?, ?, ?, ?, ?, ?)";
        
        int rowsAffected = jdbcTemplate.update(sql,
            log.getRoutineId(),
            log.getExerciseName(),
            log.getNumSets(),
            log.getNumReps(),
            log.getDuration(),
            log.getWeight());
        
        return rowsAffected > 0;
    }

    public void deleteLog(Long logId) {
        String sql = "DELETE FROM ExerciseLog WHERE log_id = ?";
        jdbcTemplate.update(sql, logId);
    }

    public void updateExerciseLog(ExerciseLog updatedExerciseLog) {
        String sql = "UPDATE ExerciseLog SET exercise_name = ?, num_sets = ?, num_reps = ?, duration = ?, weight = ? WHERE log_id = ?";
        jdbcTemplate.update(sql,
                updatedExerciseLog.getExerciseName(),
                updatedExerciseLog.getNumSets(),
                updatedExerciseLog.getNumReps(),
                updatedExerciseLog.getDuration(),
                updatedExerciseLog.getWeight(),
                updatedExerciseLog.getLogId());
    }

    private RowMapper<ExerciseLog> exerciseLogRowMapper() {
        return (rs, rowNum) -> new ExerciseLog(
            rs.getLong("log_id"),
            rs.getLong("routine_id"),
            rs.getString("exercise_name"),
            rs.getInt("num_sets"),
            rs.getInt("num_reps"),
            rs.getInt("duration"),
            rs.getInt("weight")
        );
    }

}
