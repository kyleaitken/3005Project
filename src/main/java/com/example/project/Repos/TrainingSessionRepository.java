package com.example.project.Repos;

import com.example.project.Models.TrainingSession;
import com.example.project.dto.MemberTrainingSessionView;
import com.example.project.dto.TrainingSessionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;


@Repository
public class TrainingSessionRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TrainingSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TrainingSession> findAllMemberSessions(Long memberId) {
        String sql = "SELECT * FROM TrainingSession WHERE member_id = ?";
        List<TrainingSession> sessions = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    TrainingSession session = trainingSessionRowMapper().mapRow(resultSet, resultSet.getRow());
                    sessions.add(session);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }


    public List<MemberTrainingSessionView> findAllFutureMemberSessions(Long memberId) {
        String sql = "SELECT TS.session_id, TS.trainer_id, T.name AS trainer_name, TS.date, TS.time " +
                     "FROM TrainingSession TS " +
                     "JOIN Trainers T ON TS.trainer_id = T.trainer_id " +
                     "WHERE TS.member_id = ? " +
                     "AND (TS.date > CURRENT_DATE OR " +
                     "(TS.date = CURRENT_DATE AND TS.time > EXTRACT(HOUR FROM CURRENT_TIME)::INT)) " + 
                     "ORDER BY TS.date, TS.time";

        List<MemberTrainingSessionView> sessions = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MemberTrainingSessionView session = new MemberTrainingSessionView();
                    session.setSessionId(resultSet.getLong("session_id"));
                    session.setTrainerId(resultSet.getLong("trainer_id"));
                    session.setTrainerName(resultSet.getString("trainer_name"));
                    session.setDate(resultSet.getDate("date").toLocalDate());
                    session.setTime(resultSet.getInt("time"));

                    sessions.add(session);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }


    public List<MemberTrainingSessionView> findAllPastMemberSessions(Long memberId) {
        String sql = "SELECT TS.session_id, TS.trainer_id, T.name AS trainer_name, TS.date, TS.time " +
                     "FROM TrainingSession TS " +
                     "JOIN Trainers T ON TS.trainer_id = T.trainer_id " +
                     "WHERE TS.member_id = ? " +
                     "AND (TS.date < CURRENT_DATE OR " +
                     "(TS.date = CURRENT_DATE AND TS.time < EXTRACT(HOUR FROM CURRENT_TIME)::INT)) " + 
                     "ORDER BY TS.date, TS.time";

        List<MemberTrainingSessionView> sessions = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MemberTrainingSessionView session = new MemberTrainingSessionView();
                    session.setSessionId(resultSet.getLong("session_id"));
                    session.setTrainerId(resultSet.getLong("trainer_id"));
                    session.setTrainerName(resultSet.getString("trainer_name"));
                    session.setDate(resultSet.getDate("date").toLocalDate());
                    session.setTime(resultSet.getInt("time"));

                    sessions.add(session);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    
    public void deleteSession(Long sessionId) {
        String sql = "DELETE FROM TrainingSession WHERE session_id = ?";
        jdbcTemplate.update(sql, sessionId);
    }


    @SuppressWarnings("deprecation")
    public ResponseEntity<Object> addNewMemberSession(Integer memberId, TrainingSessionRequest session) {
        String sql = "SELECT insert_training_session((SELECT trainer_id FROM trainers WHERE name = ?), ?, ?, ?)";
    
        try {
            jdbcTemplate.query(sql, new Object[]{session.getTrainerName(), memberId, java.sql.Date.valueOf(session.getDate()), session.getTime()},
                rs -> null); 
            return ResponseEntity.ok().body("Session successfully added.");
        } catch (UncategorizedSQLException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Scheduling conflict detected: " + e.getMostSpecificCause().getMessage());
        }  
    }


    private RowMapper<TrainingSession> trainingSessionRowMapper() {
        return (rs, rowNum) -> new TrainingSession(
            rs.getLong("session_id"),
            rs.getLong("trainer_id"),
            rs.getLong("member_id"),
            rs.getDate("date").toLocalDate(),
            rs.getInt("time")
        );
    }
    
}
