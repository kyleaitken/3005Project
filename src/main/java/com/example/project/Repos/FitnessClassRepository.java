package com.example.project.Repos;

import com.example.project.Models.FitnessClass;
import com.example.project.dto.AddClassRequest;
import com.example.project.dto.ClassUpdateRequest;
import com.example.project.dto.FitnessClassView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
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
import java.util.Optional;


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
                    "ORDER BY FC.date ASC, FC.time ASC";

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
                    "AND FC.date >= CURRENT_DATE " +
                    "ORDER BY FC.date ASC, FC.time ASC";

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


    public boolean removeMemberFromClass(Integer memberId, Integer classId) {
        String sql = "DELETE FROM ClassParticipants WHERE member_id = ? AND class_id = ?";
        int affectedRows = jdbcTemplate.update(sql, memberId, classId);
        return affectedRows > 0;
    }

    @SuppressWarnings("deprecation")
    public ResponseEntity<Object> addMemberToClass(Integer memberId, Integer classId) {
        String sql = "SELECT add_class_participant(?, ?)";

        try {
            jdbcTemplate.query(sql, new Object[]{classId, memberId},
                rs -> null); 
                return ResponseEntity.ok().body("{\"message\": \"Success\"}");
        } catch (UncategorizedSQLException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("{\"message\": \"Schedule conflict detected:\"}");
        } catch (DuplicateKeyException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("{\"message\": \"Schedule conflict detected:\"}");
        } catch (DataAccessException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"Unable to add member to class:\"}");
        }
    }


    public List<FitnessClassView> getAllUpcomingClasses() {
        String sql = "SELECT " +
                        "FC.class_id, " +
                        "FC.date, " +
                        "FC.time, " +
                        "FC.name AS class_name, " +
                        "T.name AS trainer_name, " +
                        "Room.room_name " +
                    "FROM FitnessClass FC " +
                    "JOIN Trainers T ON FC.trainer_id = T.trainer_id " +
                    "JOIN Room ON FC.room_id = Room.room_id " +
                    "ORDER BY FC.date DESC, FC.time DESC";

        List<FitnessClassView> fitnessClasses = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
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

  
    public Optional<FitnessClass> checkForConflictingClass(Integer classId, Integer roomId) {
        String sql = "SELECT FC.* FROM FitnessClass FC " +
                     "WHERE FC.class_id <> ? " +
                     "AND FC.room_id = ? " +
                     "AND EXISTS ( " +
                         "SELECT 1 FROM FitnessClass FC2 " +
                         "WHERE FC2.class_id = ? " +
                         "AND FC.date = FC2.date " +
                         "AND FC.time = FC2.time " +
                     ")";
    
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, classId);
            preparedStatement.setInt(2, roomId);
            preparedStatement.setInt(3, classId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(fitnessClassRowMapper().mapRow(resultSet, 1));
                } else {
                    System.out.println("No conflicting class found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public boolean updateClassRoom(Integer classId, Integer roomId) {
        String sql = "UPDATE FitnessClass SET room_id = ? WHERE class_id = ?";
        int affectedRows = jdbcTemplate.update(sql, roomId, classId);
        return affectedRows > 0;
    }
    

    public boolean removeClass(Integer classId) {
        String sql = "DELETE FROM FitnessClass where class_id = ?";
        int affectedRows = jdbcTemplate.update(sql, classId);
        return affectedRows > 0;
    }


    @SuppressWarnings("deprecation")
    public ResponseEntity<Object> addClass(AddClassRequest newClass) {
        String sql = "SELECT insert_fitness_class((SELECT trainer_id " + 
            "FROM trainers WHERE name = ?), ?, ?, ?, (SELECT room_id FROM Room where room_name = ?))";
    
        try {
            jdbcTemplate.query(sql, new Object[]{newClass.getTrainerName(), 
                java.sql.Date.valueOf(newClass.getDate()), newClass.getTime(), newClass.getClassName(), newClass.getRoomName()},
                rs -> null); 
            return ResponseEntity.ok().body("Class successfully added.");
        } catch (UncategorizedSQLException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Scheduling conflict detected: " + e.getMostSpecificCause().getMessage());
        }  
    }


    public boolean updateClassTime(Integer classId, ClassUpdateRequest classUpdate) {
        String sql = "UPDATE FitnessClass SET date = ?, time = ? WHERE class_id = ?";

        try {
            int affectedRows = jdbcTemplate.update(sql, java.sql.Date.valueOf(classUpdate.getDate()), 
            classUpdate.getTime(), classId);
            return affectedRows > 0;
        } catch (DuplicateKeyException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
        
    }


    private RowMapper<FitnessClass> fitnessClassRowMapper() {
        return (rs, rowNum) -> new FitnessClass(
            rs.getInt("class_id"),
            rs.getInt("trainer_id"),
            rs.getDate("date").toLocalDate(),
            rs.getInt("time"),
            rs.getString("name"),
            rs.getInt("room_id")
        );
    }


}
