package com.example.project.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.project.Models.Invoice;
import com.example.project.Repos.InvoiceRepository;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getInvoices() {
        return invoiceRepository.getInvoices();
    }

    public List<Invoice> getUnpaidMemberInvoices(Integer memberId) {
        return invoiceRepository.getUnpaidMemberInvoices(memberId);
    }

    public List<Invoice> getPaidMemberInvoices(Integer memberId) {
        return invoiceRepository.getPaidMemberInvoices(memberId);
    }

    public List<Invoice> getProcessingMemberInvoices(Integer memberId) {
        return invoiceRepository.getProcessingMemberInvoices(memberId);
    }

    public List<Invoice> getCancelledMemberInvoices(Integer memberId) {
        return invoiceRepository.getCancelledMemberInvoices(memberId);
    }

    public void payMemberInvoice(Integer paymentId) throws Exception {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(paymentId);
        if (!invoiceOptional.isPresent()) {
            // Handle the case where the invoice does not exist, e.g., throw an exception
            throw new Exception("Invoice with ID " + paymentId + " not found.");
        }       
         invoiceRepository.payMemberInvoice(paymentId);
    }

    public List<Invoice> getProcessingInvoices() {
        return invoiceRepository.getProcessingInvoices();
    }

    public ResponseEntity<?> processInvoice(Integer paymentId) {
        boolean invoiceProcessed = invoiceRepository.processInvoice(paymentId);
        if (invoiceProcessed) return ResponseEntity.ok().body("Invoice processed successfully");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice not found or update failed");
    }

}
