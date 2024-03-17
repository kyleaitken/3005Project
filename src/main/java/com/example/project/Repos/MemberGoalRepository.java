package com.example.project.Repos;

import com.example.project.Models.MemberGoal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class MemberGoalRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberGoalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<MemberGoal> findById(Long goalId) {
        String sql = "SELECT * FROM MemberGoal WHERE goal_id = ?";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setLong(1, goalId);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(memberGoalRowMapper().mapRow(resultSet, 1));
            } else {
                System.out.println("No Goal found for goal ID: " + goalId);
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public List<MemberGoal> findCompleteMemberGoals(Long memberId) {
        String sql = "SELECT * FROM MemberGoal WHERE member_id = ? AND completed = True";
        List<MemberGoal> memberGoals = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MemberGoal memberGoal = memberGoalRowMapper().mapRow(resultSet, resultSet.getRow());
                    memberGoals.add(memberGoal);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberGoals;
    }

    public List<MemberGoal> findIncompleteMemberGoals(Long memberId) {
        String sql = "SELECT * FROM MemberGoal WHERE member_id = ? AND completed = False";
        List<MemberGoal> memberGoals = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MemberGoal memberGoal = memberGoalRowMapper().mapRow(resultSet, resultSet.getRow());
                    memberGoals.add(memberGoal);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberGoals;
    }

    public void updateGoal(MemberGoal updatedMemberGoal) {
        String sql = "UPDATE MemberGoal SET Description = ?, target_date = ?, completed = ?, completed_date = ? WHERE goal_id = ?";
        jdbcTemplate.update(sql,
                updatedMemberGoal.getDescription(),
                java.sql.Date.valueOf(updatedMemberGoal.getTargetDate()),
                updatedMemberGoal.isCompleted(),
                updatedMemberGoal.getCompletedDate() != null ? java.sql.Date.valueOf(updatedMemberGoal.getCompletedDate()) : null,
                updatedMemberGoal.getGoalId());
    }

    public void deleteGoal(Long memberId, Long goalId) {
        String sql = "DELETE FROM MemberGoal WHERE member_id = ? AND goal_id = ?";
        jdbcTemplate.update(sql, memberId, goalId);
    }


    public void save(MemberGoal goal) {
        String sql = "INSERT INTO MemberGoal (member_id, description, target_date, completed, completed_date) VALUES (?, ?, ?, ?, ?)";
        
        jdbcTemplate.update(sql,
            goal.getMemberId(),
            goal.getDescription(),
            java.sql.Date.valueOf(goal.getTargetDate()),
            goal.isCompleted(),
            goal.getCompletedDate() != null ? java.sql.Date.valueOf(goal.getCompletedDate()) : null);
    }


    public void updateGoalCompletion(Long goalId, Long memberId, boolean completed, LocalDate completedDate) {
        String sql = "UPDATE MemberGoal SET completed = ?, completed_date = ? WHERE goal_id = ? AND member_id = ?";
        jdbcTemplate.update(sql, completed, completedDate, goalId, memberId);
    }



    private RowMapper<MemberGoal> memberGoalRowMapper() {
        return (rs, rowNum) -> new MemberGoal(
            rs.getLong("goal_id"),
            rs.getLong("member_id"),
            rs.getString("description"),
            rs.getDate("target_date").toLocalDate(),
            rs.getBoolean("completed"), 
            rs.getDate("completed_date") != null ? rs.getDate("completed_date").toLocalDate() : null
        );
    }
    
}