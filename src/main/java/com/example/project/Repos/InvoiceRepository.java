package com.example.project.Repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.project.Models.Invoice;

@Repository
public class InvoiceRepository {
        private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InvoiceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Invoice> findById(Integer paymentId) {
        String sql = "SELECT * FROM Invoice WHERE payment_id = ?";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, paymentId);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(invoiceRowMapper().mapRow(resultSet, 1));
            } else {
                System.out.println("No Invoice found for invoice ID: " + paymentId);
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Invoice> getUnpaidMemberInvoices(Integer memberId) {
        String sql = "SELECT * FROM Invoice WHERE member_id = ? AND status = 'Unpaid'";

        List<Invoice> invoices = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setPaymentId(resultSet.getInt("payment_id"));
                    invoice.setMemberId(memberId);
                    invoice.setCost(resultSet.getInt("cost"));
                    invoice.setType(resultSet.getString("type"));
                    invoice.setStatus(resultSet.getString("status"));
                    invoices.add(invoice); // Add the created invoice to the list
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }


    public List<Invoice> getPaidMemberInvoices(Integer memberId) {
        String sql = "SELECT * FROM Invoice WHERE member_id = ? AND status = 'Paid'";

        List<Invoice> invoices = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setPaymentId(resultSet.getInt("payment_id"));
                    invoice.setMemberId(memberId);
                    invoice.setCost(resultSet.getInt("cost"));
                    invoice.setType(resultSet.getString("type"));
                    invoice.setStatus(resultSet.getString("status"));
                    invoices.add(invoice); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }


    public List<Invoice> getProcessingMemberInvoices(Integer memberId) {
        String sql = "SELECT * FROM Invoice WHERE member_id = ? AND status = 'Processing'";

        List<Invoice> invoices = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setPaymentId(resultSet.getInt("payment_id"));
                    invoice.setMemberId(memberId);
                    invoice.setCost(resultSet.getInt("cost"));
                    invoice.setType(resultSet.getString("type"));
                    invoice.setStatus(resultSet.getString("status"));
                    invoices.add(invoice); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }


    public List<Invoice> getCancelledMemberInvoices(Integer memberId) {
        String sql = "SELECT * FROM Invoice WHERE member_id = ? AND status = 'Cancelled'";

        List<Invoice> invoices = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setPaymentId(resultSet.getInt("payment_id"));
                    invoice.setMemberId(memberId);
                    invoice.setCost(resultSet.getInt("cost"));
                    invoice.setType(resultSet.getString("type"));
                    invoice.setStatus(resultSet.getString("status"));
                    invoices.add(invoice); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(invoices);
        return invoices;
    }

    public void payMemberInvoice(Integer paymentId) {
        String sql = "UPDATE Invoice Set status = 'Processing' where payment_id = ?";
        jdbcTemplate.update(sql, paymentId);
    }


    private RowMapper<Invoice> invoiceRowMapper() {
        return (rs, rowNum) -> new Invoice(
            rs.getInt("payment_id"),
            rs.getInt("member_id"),
            rs.getString("type"),
            rs.getInt("cost"),
            rs.getString("status")
        );
    }
    
}
