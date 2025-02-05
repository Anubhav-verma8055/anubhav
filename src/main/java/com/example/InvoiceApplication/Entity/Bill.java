package com.example.InvoiceApplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private LocalDateTime timestamp;
    private Long totalAmount;

    private Long totalCGST;
    private Long totalSGST;         // Total SGST amount for the invoice
    private Long totalIGST;         // Total IGST amount for the invoice
    private Long totalCess;

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


//    private List<Item> listOFItems;
//   private  List<Quantity> quantityOfItem;

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

//    public List<Item> getListOFItems() {
//        return listOFItems;
//    }
//
//    public void setListOFItems(List<Item> listOFItems) {
//        this.listOFItems = listOFItems;
//    }
//
//    public List<Quantity> getQuantityOfItem() {
//        return quantityOfItem;
//    }
//
//    public void setQuantityOfItem(List<Quantity> quantityOfItem) {
//        this.quantityOfItem = quantityOfItem;
//    }


}
