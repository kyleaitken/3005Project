package com.example.project.Repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.project.Models.Admin;

@Repository
public class AdminRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Admin> findByEmail(String email) {
        String sql = "SELECT * FROM Admin WHERE email = ?";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, email);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(adminRowMapper().mapRow(resultSet, 1));
            } else {
                System.out.println("No Trainer found with email: " + email);
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private RowMapper<Admin> adminRowMapper() {
        return (rs, rowNum) -> new Admin(
            rs.getInt("admin_id"),
            rs.getString("email"),
            rs.getString("password")
        );
    }
}
