package com.example.InvoiceApplication.DTO;

public class ItemRequest {
    private String itemName;
    private String itemDescription;
    private String itemHsaCode;
    private Long itemPrice;
    private Long itemGst;
    private Long itemCess;
    private Long billId;

    public String getItemHsaCode() {
        return itemHsaCode;
    }

    public void setItemHsaCode(String itemHsaCode) {
        this.itemHsaCode = itemHsaCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Long getItemGst() {
        return itemGst;
    }

    public void setItemGst(Long itemGst) {
        this.itemGst = itemGst;
    }

    public Long getItemCess() {
        return itemCess;
    }

    public void setItemCess(Long itemCess) {
        this.itemCess = itemCess;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

}
