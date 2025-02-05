package com.example.InvoiceApplication.DTO;

public class Quantityrequest {

        private Long itemId;

        private String itemName;
        private Long availableQuantity;

        public Long getItemId() {
            return itemId;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public Long getAvailableQuantity() {
            return availableQuantity;
        }

        public void setAvailableQuantity(Long availableQuantity) {
            this.availableQuantity = availableQuantity;
        }

    }
