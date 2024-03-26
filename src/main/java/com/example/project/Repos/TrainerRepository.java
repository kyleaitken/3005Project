package com.example.project.Repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.example.project.Models.Trainer;
import com.example.project.dto.TrainerScheduleUpdateRequest;
import com.example.project.dto.TrainerTrainingSessionView;

@Repository
public class TrainerRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TrainerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Trainer> findByEmail(String email) {
        String sql = "SELECT * FROM Trainers WHERE email = ?";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, email);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(trainerRowMapper().mapRow(resultSet, 1));
            } else {
                System.out.println("No Trainer found with email: " + email);
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Trainer> getTrainer(Integer trainerId) {
        String sql = "SELECT * FROM Trainers WHERE trainer_id = ?";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, trainerId);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(trainerRowMapper().mapRow(resultSet, 1));
            } else {
                System.out.println("No Trainer found");
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean updateSchedule(Integer trainerId, TrainerScheduleUpdateRequest newSched) {
        System.out.println("trainer update in repo");
        String sql = "UPDATE Trainers SET start_time = ?, end_time = ? WHERE trainer_id = ?";

        try {
            int affectedRows = jdbcTemplate.update(sql, newSched.getStartTime(), newSched.getEndTime(), trainerId);
            return affectedRows > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public List<TrainerTrainingSessionView> getUpcomingSessions(Integer trainerId) {
        String sql = "SELECT TS.date, TS.time, M.member_id, M.first_name AS memberFirstName, M.last_name AS memberLastName, TS.session_id " +
                 "FROM trainingSession TS " +
                 "JOIN members M ON TS.member_id = M.member_id " +
                 "WHERE TS.trainer_id = ? " +
                 "AND (TS.date > CURRENT_DATE OR " +
                 "(TS.date = CURRENT_DATE AND TS.time >= EXTRACT(HOUR FROM CURRENT_TIME)::INT)) " +
                 "ORDER BY TS.date ASC, TS.time ASC";

        List<TrainerTrainingSessionView> trainerSessions = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, trainerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    TrainerTrainingSessionView trainerSession = new TrainerTrainingSessionView();
                    trainerSession.setSessionId(resultSet.getInt("session_id"));
                    trainerSession.setMemberId(resultSet.getInt("member_id"));
                    trainerSession.setDate(resultSet.getDate("date").toLocalDate());
                    trainerSession.setTime(resultSet.getInt("time"));
                    trainerSession.setMemberFirstName(resultSet.getString("memberFirstName"));
                    trainerSession.setMemberLastName(resultSet.getString("memberLastName"));
                    trainerSessions.add(trainerSession);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainerSessions;
    }


    public List<TrainerTrainingSessionView> getPastSessions(Integer trainerId) {
        String sql = "SELECT TS.date, TS.time, M.member_id, M.first_name AS memberFirstName, M.last_name AS memberLastName, TS.session_id " +
                 "FROM trainingSession TS " +
                 "JOIN members M ON TS.member_id = M.member_id " +
                 "WHERE TS.trainer_id = ? " +
                 "AND (TS.date < CURRENT_DATE OR " +
                 "(TS.date = CURRENT_DATE AND TS.time < EXTRACT(HOUR FROM CURRENT_TIME)::INT)) " +
                 "ORDER BY TS.date ASC, TS.time ASC";

        List<TrainerTrainingSessionView> trainerSessions = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, trainerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    TrainerTrainingSessionView trainerSession = new TrainerTrainingSessionView();
                    trainerSession.setSessionId(resultSet.getInt("session_id"));
                    trainerSession.setMemberId(resultSet.getInt("member_id"));
                    trainerSession.setDate(resultSet.getDate("date").toLocalDate());
                    trainerSession.setTime(resultSet.getInt("time"));
                    trainerSession.setMemberFirstName(resultSet.getString("memberFirstName"));
                    trainerSession.setMemberLastName(resultSet.getString("memberLastName"));
                    trainerSessions.add(trainerSession);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainerSessions;
    }

    public List<Map<String, Object>> getAvailableTrainers() {
        String sql = "SELECT trainer_id, name, start_time, end_time FROM Trainers";
        List<Map<String, Object>> trainers = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Map<String, Object> trainer = new HashMap<>();
                    trainer.put("trainerId", resultSet.getInt("trainer_id"));
                    trainer.put("trainerName", resultSet.getString("name"));
                    trainer.put("startTime", resultSet.getInt("start_time"));
                    trainer.put("endTime", resultSet.getInt("end_time"));
                    trainers.add(trainer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainers;
    }
    

    private RowMapper<Trainer> trainerRowMapper() {
        return (rs, rowNum) -> new Trainer(
            rs.getInt("trainer_id"),
            rs.getString("email"),
            rs.getString("name"),
            rs.getString("password"),
            rs.getInt("start_time"),
            rs.getInt("end_time")
        );
    }

}
