package com.example.project.Repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.project.Models.Equipment;

@Repository
public class EquipmentRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EquipmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Equipment> getEquipment() {
        String sql = "SELECT * FROM Equipment";
        List<Equipment> equipments = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentId(resultSet.getInt(("equipment_id")));
                    equipment.setEquipmentName(resultSet.getString("name"));
                    equipment.setNeedsRepair(resultSet.getBoolean("needs_repair"));
                    equipments.add(equipment); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipments;
    }

    public boolean repairEquipment(Integer equipmentId) {
        String sql = "UPDATE Equipment SET needs_repair = FALSE where equipment_id = ?";
        try {
            int affectedRows = jdbcTemplate.update(sql, equipmentId);
            return affectedRows > 0;
        } catch (Exception e) {
            return false;
        }

    }
}
