package com.example.InvoiceApplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private LocalDateTime timestamp;
    private Long totalAmount;
    private Boolean isInterState;
    private Long totalCGST;
    private Long totalSGST;         // Total SGST amount for the invoice
    private Long totalIGST;         // Total IGST amount for the invoice
    private Long totalCess;
    private Long amountPaid;

    public Long getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Long amountPaid) {
        this.amountPaid = amountPaid;
    }


    public Boolean getIsInterState() {
        return isInterState;
    }

    public void setIsInterstate(Boolean interState) {
        isInterState = interState;
    }



    public Long getTotalCGST() {
        return totalCGST;
    }

    public void setTotalCGST(Long totalCGST) {
        this.totalCGST = totalCGST;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getTotalSGST() {
        return totalSGST;
    }

    public void setTotalSGST(Long totalSGST) {
        this.totalSGST = totalSGST;
    }

    public Long getTotalIGST() {
        return totalIGST;
    }

    public void setTotalIGST(Long totalIGST) {
        this.totalIGST = totalIGST;
    }

    public Long getTotalCess() {
        return totalCess;
    }

    public void setTotalCess(Long totalCess) {
        this.totalCess = totalCess;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
