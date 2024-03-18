package com.example.project.Repos;

import java.util.Optional;
import com.example.project.Models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;


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

    public void update(Member member) {
        String sql = "UPDATE members SET email = ?, password = ?, first_name = ?, last_name = ?, birth_date = ?, phone = ?, address = ?, emergency_phone = ? WHERE member_id = ?";
        jdbcTemplate.update(sql, member.getEmail(), member.getPassword(), member.getFirstName(), member.getLastName(), java.sql.Date.valueOf(member.getBirthDate()), member.getPhone(), member.getAddress(), member.getEmergencyPhone(), member.getMemberId());
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

