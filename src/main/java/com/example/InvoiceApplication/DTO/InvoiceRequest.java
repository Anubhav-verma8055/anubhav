package com.example.InvoiceApplication.DTO;

import com.example.InvoiceApplication.Entity.Item;
import com.example.InvoiceApplication.Entity.Quantity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class InvoiceRequest {
    private List<ItemRequest> items;
    private List<Quantityrequest> quantities;
    private boolean isInterstate;
    public List<ItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemRequest> items) {
        this.items = items;
    }

    public List<Quantityrequest> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Quantityrequest> quantities) {
        this.quantities = quantities;
    }

    public boolean isInterstate() {
        return isInterstate;
    }

    public void setInterstate(boolean interstate) {
        isInterstate = interstate;
    }

    public static class InvoiceDTO {
        private Long billId;
        private String customerName;
        private String customerEmail;
        private List<Item> items;
        private List<Quantity> quantities;
        private LocalDateTime timestamp;
        private Long totalAmount;
        private String companyName;
        private String approvedBy;
        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getApprovedBy() {
            return approvedBy;
        }

        public void setApprovedBy(String approvedBy) {
            this.approvedBy = approvedBy;
        }



    //
    //    public InvoiceDTO(Long billId, String customerName, String customerEmail, List<Item> items, List<Quantity> quantities, LocalDate timestamp, Long totalAmount) {
    //        this.billId = billId;
    //        this.customerName = customerName;
    //        this.customerEmail = customerEmail;
    //        this.items = items;
    //        this.quantities = quantities;
    //        this.timestamp = timestamp;
    //        this.totalAmount = totalAmount;
    //    }

        public String getCustomerEmail() {
            return customerEmail;
        }

        public void setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
        }

        public Long getBillId() {
            return billId;
        }

        public void setBillId(Long billId) {
            this.billId = billId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public List<Quantity> getQuantities() {
            return quantities;
        }

        public void setQuantities(List<Quantity> quantities) {
            this.quantities = quantities;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public Long getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Long totalAmount) {
            this.totalAmount = totalAmount;
        }


    }
}
