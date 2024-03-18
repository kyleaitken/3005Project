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
import com.example.project.Models.Trainer;

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
