package com.example.project.Repos;

import com.example.project.dto.FitnessClassView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;


@Repository
public class FitnessClassRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FitnessClassRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FitnessClassView> findAllPastMemberFitnessClasses(Integer memberId) {
        String sql = "SELECT " +
                        "FC.class_id, " +
                        "FC.date, " +
                        "FC.time, " +
                        "FC.name AS class_name, " +
                        "T.name AS trainer_name, " +
                        "Room.room_name " +
                    "FROM FitnessClass FC " +
                    "JOIN ClassParticipants CP ON FC.class_id = CP.class_id " +
                    "JOIN Trainers T ON FC.trainer_id = T.trainer_id " +
                    "JOIN Room ON FC.room_id = Room.room_id " +
                    "WHERE CP.member_id = ? " +
                    "AND (FC.date < CURRENT_DATE OR " +
                        "(FC.date = CURRENT_DATE AND FC.time <= EXTRACT(HOUR FROM CURRENT_TIME)::INT)) " +
                    "ORDER BY FC.date DESC, FC.time DESC";

        List<FitnessClassView> fitnessClasses = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    FitnessClassView fitnessClass = new FitnessClassView();
                    fitnessClass.setClassId(resultSet.getInt("class_id"));
                    fitnessClass.setDate(resultSet.getDate("date").toLocalDate());
                    fitnessClass.setTime(resultSet.getInt("time"));
                    fitnessClass.setClassName(resultSet.getString("class_name"));
                    fitnessClass.setTrainerName(resultSet.getString("trainer_name"));
                    fitnessClass.setRoomName(resultSet.getString("room_name"));
                    fitnessClasses.add(fitnessClass);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fitnessClasses;
    }


    public List<FitnessClassView> findAllFutureMemberFitnessClasses(Integer memberId) {
        String sql = "SELECT " +
                        "FC.class_id, " +
                        "FC.date, " +
                        "FC.time, " +
                        "FC.name AS class_name, " +
                        "T.name AS trainer_name, " +
                        "Room.room_name " +
                    "FROM FitnessClass FC " +
                    "JOIN ClassParticipants CP ON FC.class_id = CP.class_id " +
                    "JOIN Trainers T ON FC.trainer_id = T.trainer_id " +
                    "JOIN Room ON FC.room_id = Room.room_id " +
                    "WHERE CP.member_id = ? " +
                    "AND (FC.date > CURRENT_DATE OR " +
                        "(FC.date = CURRENT_DATE AND FC.time > EXTRACT(HOUR FROM CURRENT_TIME)::INT)) " +
                    "ORDER BY FC.date DESC, FC.time DESC";

        List<FitnessClassView> fitnessClasses = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    FitnessClassView fitnessClass = new FitnessClassView();
                    fitnessClass.setClassId(resultSet.getInt("class_id"));
                    fitnessClass.setDate(resultSet.getDate("date").toLocalDate());
                    fitnessClass.setTime(resultSet.getInt("time"));
                    fitnessClass.setClassName(resultSet.getString("class_name"));
                    fitnessClass.setTrainerName(resultSet.getString("trainer_name"));
                    fitnessClass.setRoomName(resultSet.getString("room_name"));
                    fitnessClasses.add(fitnessClass);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fitnessClasses;
    }

    public List<FitnessClassView> findAllAvailableMemberFitnessClasses(Integer memberId) {
        String sql = "SELECT " +
                        "FC.class_id, " +
                        "FC.date, " +
                        "FC.time, " +
                        "FC.name AS class_name, " +
                        "T.name AS trainer_name, " +
                        "Room.room_name " +
                    "FROM FitnessClass FC " +
                    "LEFT JOIN ClassParticipants CP ON FC.class_id = CP.class_id AND CP.member_id = ? " +
                    "JOIN Trainers T ON FC.trainer_id = T.trainer_id " +
                    "JOIN Room ON FC.room_id = Room.room_id " +
                    "WHERE CP.class_id IS NULL " +
                    "ORDER BY FC.date DESC, FC.time DESC";

        List<FitnessClassView> fitnessClasses = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    FitnessClassView fitnessClass = new FitnessClassView();
                    fitnessClass.setClassId(resultSet.getInt("class_id"));
                    fitnessClass.setDate(resultSet.getDate("date").toLocalDate());
                    fitnessClass.setTime(resultSet.getInt("time"));
                    fitnessClass.setClassName(resultSet.getString("class_name"));
                    fitnessClass.setTrainerName(resultSet.getString("trainer_name"));
                    fitnessClass.setRoomName(resultSet.getString("room_name"));
                    fitnessClasses.add(fitnessClass);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fitnessClasses;
    }


    public void removeMemberFromClass(Integer memberId, Integer classId) {
        String sql = "DELETE FROM ClassParticipants WHERE member_id = ? AND class_id = ?";
        jdbcTemplate.update(sql, memberId, classId);
    }

    @SuppressWarnings("deprecation")
    public ResponseEntity<Object> addMemberToClass(Integer memberId, Integer classId) {
        String sql = "SELECT add_class_participant(?, ?)";

        try {
            jdbcTemplate.query(sql, new Object[]{classId, memberId},
                rs -> null); 
            return ResponseEntity.ok().body("Class successfully added to member's schedule.");
        } catch (UncategorizedSQLException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Scheduling conflict detected: " + e.getMostSpecificCause().getMessage());
        } catch (DuplicateKeyException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Member is already enrolled in this class.");
        } catch (DataAccessException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to process the request: " + e.getMostSpecificCause().getMessage());
        }
    }



}
