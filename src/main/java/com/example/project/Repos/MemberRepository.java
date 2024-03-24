package com.example.project.Repos;

import java.util.Optional;
import com.example.project.Models.Member;
import com.example.project.dto.FitnessClassView;
import com.example.project.dto.MemberScheduleView;
import com.example.project.dto.MemberTrainingSessionView;
import com.example.project.dto.TrainerMemberProfileView;
import com.example.project.dto.TrainerMemberView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Member> findAll() {
        String sql = "SELECT * FROM members";
        return jdbcTemplate.query(sql, memberRowMapper());
    }

    @SuppressWarnings("deprecation")
    public Optional<Member> findById(Long memberId) {
        try {
            String sql = "SELECT * FROM members WHERE member_id = ?";
            Member member = jdbcTemplate.queryForObject(sql, new Object[]{memberId}, memberRowMapper());
            return Optional.ofNullable(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @SuppressWarnings("deprecation")
    public Optional<Member> findByEmail(String email) {
        try {
            String sql = "SELECT * FROM members WHERE email = ?";
            Member member = jdbcTemplate.queryForObject(sql, new Object[]{email}, memberRowMapper());
            return Optional.ofNullable(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void save(Member member) {
        String sql = "INSERT INTO Members (email, password, first_name, last_name, birth_date, phone, address, emergency_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        jdbcTemplate.update(sql, member.getEmail(), member.getPassword(), member.getFirstName(),
            member.getLastName(), java.sql.Date.valueOf(member.getBirthDate()), 
            member.getPhone(), member.getAddress(), member.getEmergencyPhone());
    }

    public boolean update(Member member) {
        String sql = "UPDATE members SET email = ?, phone = ?, address = ?, emergency_phone = ? WHERE member_id = ?";
        int affectedRows = jdbcTemplate.update(sql,
                                                member.getEmail(), 
                                                member.getPhone(), 
                                                member.getAddress(), 
                                                member.getEmergencyPhone(), 
                                                member.getMemberId());
        System.out.println(affectedRows);
        return affectedRows > 0;
    }

    public List<TrainerMemberView> getMembersForTrainer() {
        String sql = "SELECT member_id, first_name, last_name FROM Members ORDER BY last_name";
        List<TrainerMemberView> members = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    TrainerMemberView member = new TrainerMemberView();
                    member.setMemberId(resultSet.getInt("member_id"));
                    member.setMemberFirstName(resultSet.getString("first_name"));
                    member.setMemberLastName(resultSet.getString("last_name"));
                    members.add(member);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }


    public Optional<TrainerMemberProfileView> getMemberProfileForTrainer(Integer memberId) {
        String sql = "SELECT M.member_id, M.first_name, " + 
        "M.last_name, M.email, M.birth_date, M.phone, " + 
        "MHI.height, MHI.weight, MHI.bmi, MHI.resting_heart_rate, " + 
        "MHI.systolic_bp, MHI.diastolic_bp, MHI.waist_girth " + 
        "FROM Members M " + 
        "JOIN MemberHealthInfo MHI ON M.member_id = MHI.member_id " + 
        "WHERE M.member_id = ?";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, memberId);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                TrainerMemberProfileView memberProfile = new TrainerMemberProfileView();
                memberProfile.setMemberId(memberId);
                memberProfile.setFirstName(resultSet.getString("first_name"));
                memberProfile.setLastName(resultSet.getString("last_name"));
                memberProfile.setMemberBirthDate(resultSet.getDate("birth_date").toLocalDate());
                memberProfile.setMemberEmail(resultSet.getString("email"));
                memberProfile.setMemberPhone(resultSet.getString("phone"));
                memberProfile.setMemberHeight(resultSet.getInt("height"));
                memberProfile.setMemberWeight(resultSet.getInt("weight"));
                memberProfile.setMemberBMI(resultSet.getInt("bmi"));
                memberProfile.setMemberRestingHR(resultSet.getInt("resting_heart_rate"));
                memberProfile.setMemberSysBP(resultSet.getInt("systolic_bp"));
                memberProfile.setMemberDiaBP(resultSet.getInt("diastolic_bp"));
                memberProfile.setMemberWaist(resultSet.getInt("waist_girth"));
                
                return Optional.of(memberProfile);
            } else {
                Optional.empty();
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    public List<MemberScheduleView> getMemberSchedule(Integer memberId) {
        String sql = "SELECT " +
                            "TS.session_id AS session_class_id, " +
                            "TS.date, " +
                            "TS.time, " +
                            "TS.trainer_id, " +
                            "T.name AS trainer_name, " +
                            "NULL AS class_id, " +
                            "NULL AS class_name " +
                    "FROM " +
                            "Members M " +
                    "JOIN TrainingSession TS ON M.member_id = TS.member_id " +
                    "JOIN Trainers T ON TS.trainer_id = T.trainer_id " +
                    "WHERE " +
                            "M.member_id = ? AND " +
                            "TS.date >= CURRENT_DATE " +
                "UNION ALL " +
                    "SELECT " +
                            "NULL AS session_id, " +
                            "FC.date, " +
                            "FC.time, " +
                            "FC.trainer_id, " +
                            "T.name AS trainer_name, " +
                            "FC.class_id, " +
                            "FC.name AS class_name " +
                     "FROM " +
                             "Members M " +
                    "JOIN ClassParticipants CP ON M.member_id = CP.member_id " +
                    "JOIN FitnessClass FC ON CP.class_id = FC.class_id " +
                    "JOIN Trainers T ON FC.trainer_id = T.trainer_id " +
                     "WHERE " +
                            "M.member_id = ? AND " +
                            "FC.date >= CURRENT_DATE " +
                     "ORDER BY " +
                            "date, " +
                            "time;";

        
        List<MemberScheduleView> scheduleItems = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, memberId);
            preparedStatement.setInt(2, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MemberScheduleView scheduleItem = new MemberScheduleView();
                    scheduleItem.setDate(resultSet.getDate("date").toLocalDate());
                    scheduleItem.setTime(resultSet.getInt("time"));
                    scheduleItem.setClassName(resultSet.getString("class_name"));
                    scheduleItem.setTrainerName(resultSet.getString("trainer_name"));
                    scheduleItems.add(scheduleItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scheduleItems;
    }
    


    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> new Member(
            rs.getLong("member_id"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getDate("birth_date").toLocalDate(),
            rs.getString("phone"),
            rs.getString("address"),
            rs.getString("emergency_phone")
        );
    }
    
    // Other CRUD operations
}

