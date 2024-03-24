package com.example.project.Repos;

import java.util.Optional;

import com.example.project.Models.MemberHealthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

@Repository
public class MemberHealthInfoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberHealthInfoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @SuppressWarnings("deprecation")
    public Optional<MemberHealthInfo> findById(Long memberId) {
        try {
            String sql = "SELECT * FROM memberhealthinfo WHERE member_id = ?";
            MemberHealthInfo memberHealthInfo = jdbcTemplate.queryForObject(sql, new Object[]{memberId}, memberHealthInfoRowMapper());
            return Optional.ofNullable(memberHealthInfo);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No MemberHealthInfo found for member ID: " + memberId);
            return Optional.empty();
        }
    }

    public void save(MemberHealthInfo memberHealthInfo) {
        String sql = "INSERT INTO MemberHealthInfo (member_id, height, weight, bmi, resting_heart_rate, " +
            "systolic_bp, diastolic_bp, waist_girth) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


        jdbcTemplate.update(sql,
            memberHealthInfo.getMemberId(),
            memberHealthInfo.getHeight(),
            memberHealthInfo.getWeight(),
            memberHealthInfo.getBmi(),
            memberHealthInfo.getRestingHeartRate(),
            memberHealthInfo.getSystolicBp(),
            memberHealthInfo.getDiastolicBp(),
            memberHealthInfo.getWaistGirth());
    }

    public boolean update(MemberHealthInfo memberHealthInfo) {
        String sql = "UPDATE memberhealthinfo SET height = ?, weight = ?, bmi = ?, " +
            "resting_heart_rate = ?, systolic_bp = ?, diastolic_bp = ?, waist_girth = ? " +
            "WHERE member_id = ?";

        int rowsAffected = jdbcTemplate.update(sql,
            memberHealthInfo.getHeight(),
            memberHealthInfo.getWeight(),
            memberHealthInfo.getBmi(),
            memberHealthInfo.getRestingHeartRate(),
            memberHealthInfo.getSystolicBp(),
            memberHealthInfo.getDiastolicBp(),
            memberHealthInfo.getWaistGirth(),
            memberHealthInfo.getMemberId());

        return rowsAffected > 0;
    }

    private RowMapper<MemberHealthInfo> memberHealthInfoRowMapper() {
        return (rs, rowNum) -> {
            Long memberId = rs.getLong("member_id");
            Integer height = rs.getInt("height");
            Integer weight = rs.getInt("weight");
            Integer bmi = rs.getInt("bmi");
            Integer restingHeartRate = rs.getInt("resting_heart_rate");
            Integer systolicBp = rs.getInt("systolic_bp");
            Integer diastolicBp = rs.getInt("diastolic_bp");
            Integer waistGirth = rs.getInt("waist_girth");
    
            return new MemberHealthInfo(memberId, height, weight, bmi, restingHeartRate, systolicBp, diastolicBp, waistGirth);
        };
    }
    
    // Other CRUD operations
}