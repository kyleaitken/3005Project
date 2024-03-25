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

    public List<Invoice> getInvoices() {
        String sql = "SELECT * FROM Invoice ORDER BY status DESC";

        List<Invoice> invoices = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setPaymentId(resultSet.getInt("payment_id"));
                    invoice.setMemberId(resultSet.getInt("member_id"));
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

    public List<Invoice> getUnpaidMemberInvoices(Integer memberId) {
        String sql = "SELECT I.*, COALESCE(FC.date, TS.date) AS invoice_date "
            + "FROM invoice I "
            + "LEFT JOIN ClassInvoice CI ON I.payment_id = CI.payment_id "
            + "LEFT JOIN SessionInvoice SI ON I.payment_id = SI.payment_id "
            + "LEFT JOIN fitnessClass FC ON CI.class_id = FC.class_id "
            + "LEFT JOIN TrainingSession TS ON SI.session_id = TS.session_id "
            + "WHERE I.member_id = ? AND I.status = 'Unpaid' "
            + "ORDER BY I.type;";

        List<Invoice> invoices = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setPaymentId(resultSet.getInt("payment_id"));
                    invoice.setMemberId(memberId);
                    java.sql.Date sqlDate = resultSet.getDate("invoice_date");
                    if (sqlDate != null) {
                        invoice.setDate(sqlDate.toLocalDate());
                    } else {
                        invoice.setDate(null); 
                    }
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


    public List<Invoice> getPaidMemberInvoices(Integer memberId) {
        String sql = "SELECT I.*, COALESCE(FC.date, TS.date) AS invoice_date "
            + "FROM invoice I "
            + "LEFT JOIN ClassInvoice CI ON I.payment_id = CI.payment_id "
            + "LEFT JOIN SessionInvoice SI ON I.payment_id = SI.payment_id "
            + "LEFT JOIN fitnessClass FC ON CI.class_id = FC.class_id "
            + "LEFT JOIN TrainingSession TS ON SI.session_id = TS.session_id "
            + "WHERE I.member_id = ? AND I.status = 'Paid' "
            + "ORDER BY I.type;";

        List<Invoice> invoices = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setPaymentId(resultSet.getInt("payment_id"));
                    invoice.setMemberId(memberId);

                    java.sql.Date sqlDate = resultSet.getDate("invoice_date");
                    if (sqlDate != null) {
                        invoice.setDate(sqlDate.toLocalDate());
                    } else {
                        invoice.setDate(null); 
                    }

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
        String sql = "SELECT I.*, COALESCE(FC.date, TS.date) AS invoice_date "
            + "FROM invoice I "
            + "LEFT JOIN ClassInvoice CI ON I.payment_id = CI.payment_id "
            + "LEFT JOIN SessionInvoice SI ON I.payment_id = SI.payment_id "
            + "LEFT JOIN fitnessClass FC ON CI.class_id = FC.class_id "
            + "LEFT JOIN TrainingSession TS ON SI.session_id = TS.session_id "
            + "WHERE I.member_id = ? AND I.status = 'Processing' "
            + "ORDER BY I.type;";

        List<Invoice> invoices = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setPaymentId(resultSet.getInt("payment_id"));
                    invoice.setMemberId(memberId);

                    java.sql.Date sqlDate = resultSet.getDate("invoice_date");
                    if (sqlDate != null) {
                        invoice.setDate(sqlDate.toLocalDate());
                    } else {
                        invoice.setDate(null); 
                    }    

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

    public List<Invoice> getProcessingInvoices() {
        String sql = "SELECT * FROM Invoice WHERE status = 'Processing'";

        List<Invoice> invoices = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setPaymentId(resultSet.getInt("payment_id"));
                    invoice.setMemberId(resultSet.getInt("member_id"));
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
        String sql = "SELECT I.*, COALESCE(FC.date, TS.date) AS invoice_date "
            + "FROM invoice I "
            + "LEFT JOIN ClassInvoice CI ON I.payment_id = CI.payment_id "
            + "LEFT JOIN SessionInvoice SI ON I.payment_id = SI.payment_id "
            + "LEFT JOIN fitnessClass FC ON CI.class_id = FC.class_id "
            + "LEFT JOIN TrainingSession TS ON SI.session_id = TS.session_id "
            + "WHERE I.member_id = ? AND I.status = 'Cancelled' "
            + "ORDER BY I.type;";
            
        List<Invoice> invoices = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setPaymentId(resultSet.getInt("payment_id"));
                    invoice.setMemberId(memberId);
                    
                    java.sql.Date sqlDate = resultSet.getDate("invoice_date");
                    if (sqlDate != null) {
                        invoice.setDate(sqlDate.toLocalDate());
                    } else {
                        invoice.setDate(null); 
                    }

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

    public boolean payMemberInvoice(Integer paymentId) {
        String sql = "UPDATE Invoice Set status = 'Processing' where payment_id = ?";
        int affectedRows = jdbcTemplate.update(sql, paymentId);
        return affectedRows > 0;
    }

    public boolean processInvoice(Integer paymentId) {
        String sql = "UPDATE Invoice Set status = 'Paid' where payment_id = ?";
        try {
            int affectedRows = jdbcTemplate.update(sql, paymentId);
            return affectedRows > 0;
        } catch (Exception e) {
            return false;
        }

    }


    private RowMapper<Invoice> invoiceRowMapper() {
        return (rs, rowNum) -> new Invoice(
            rs.getInt("payment_id"),
            rs.getInt("member_id"),
            rs.getDate("date").toLocalDate(),
            rs.getString("type"),
            rs.getInt("cost"),
            rs.getString("status")
        );
    }
    
}
