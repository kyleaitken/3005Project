package com.example.project.Repos;

import com.example.project.Models.MemberExerciseRoutine;
import com.example.project.dto.ExerciseRoutineDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository

public class ExerciseRoutineRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExerciseRoutineRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(MemberExerciseRoutine routine) {
        String sql = "INSERT INTO MemberExerciseRoutine (member_id) VALUES (?)";

        jdbcTemplate.update(sql, routine.getMemberId());
    }

    public List<MemberExerciseRoutine> findMemberRoutines(Long memberId) {
        String sql = "SELECT * FROM MemberExerciseRoutine WHERE member_id = ?";
        List<MemberExerciseRoutine> memberRoutines = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MemberExerciseRoutine memberRoutine = memberExerciseRoutineRowMapper().mapRow(resultSet, resultSet.getRow());
                    memberRoutines.add(memberRoutine);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberRoutines;
    }

    // get routines and associated logs

    public List<ExerciseRoutineDto> findRoutinesAndExercises(Long memberId) {
        String sql = "SELECT MER.routine_id, EL.log_id, EL.exercise_name, EL.num_sets, EL.num_reps, EL.duration, EL.weight " +
                     "FROM MemberExerciseRoutine MER " +
                     "LEFT JOIN ExerciseLog EL ON MER.routine_id = EL.routine_id " +
                     "WHERE MER.member_id = ? " +
                     "ORDER BY MER.routine_id, EL.exercise_name";

        Map<Long, ExerciseRoutineDto> routines = new HashMap<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Long routineId = resultSet.getLong("routine_id");
                    ExerciseRoutineDto.ExerciseDetail detail = new ExerciseRoutineDto.ExerciseDetail(
                        resultSet.getLong("log_id"),
                        resultSet.getString("exercise_name"),
                        resultSet.getInt("num_sets"),
                        resultSet.getInt("num_reps"),
                        resultSet.getInt("duration"),
                        resultSet.getInt("weight"));

                    ExerciseRoutineDto routine = routines.computeIfAbsent(routineId, k -> new ExerciseRoutineDto(routineId, new ArrayList<>()));
                    routine.getExercises().add(detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging this exception or rethrowing it as a runtime exception
        }

        return new ArrayList<>(routines.values());
    }


    public void deleteRoutine(Long routineId) {
        String sql = "DELETE FROM MemberExerciseRoutine WHERE routine_id = ?";
        jdbcTemplate.update(sql, routineId);
    }



    private RowMapper<MemberExerciseRoutine> memberExerciseRoutineRowMapper() {
        return (rs, rowNum) -> new MemberExerciseRoutine(
            rs.getLong("routine_id"),
            rs.getLong("member_id")
        );
    }



}
