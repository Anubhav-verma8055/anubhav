package com.example.InvoiceApplication.DTO;

import com.example.InvoiceApplication.Entity.Item;
import com.example.InvoiceApplication.Entity.Quantity;

import java.time.LocalDateTime;
import java.util.List;
public class InvoiceRequest {
    public static class InvoiceDTO {
        private Long billId;
        private String customerName;
        private String customerEmail;
        private String customerPhone;
        private List<Item> items;
        private List<Quantity> quantities;
        private LocalDateTime timestamp;
        private Long totalAmount;
        private Long amountPaid;
        private String companyName;
        private String approvedBy;
        private Long customerId;
        private Long changeAmount;

        public Long getChangeAmount() {
            return changeAmount;
        }

        public void setChangeAmount(Long changeAmount) {
            this.changeAmount = changeAmount;
        }



        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }



        public Long getAmountPaid() {
            return amountPaid;
        }

        public void setAmountPaid(Long amountPaid) {
            this.amountPaid = amountPaid;
        }


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
