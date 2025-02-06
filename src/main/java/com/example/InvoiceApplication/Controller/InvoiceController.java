package com.example.InvoiceApplication.Controller;

import com.example.InvoiceApplication.DTO.InvoiceRequest;
import com.example.InvoiceApplication.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/generateInvoice")
    public InvoiceRequest.InvoiceDTO generateInvoice(
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long billId,

            @RequestParam Long amountPaid
    ) {
        try {
            return invoiceService.generateInvoice(customerId, billId, amountPaid);

        } catch (Exception e) {
            throw new RuntimeException("the invoice record is not found for given details");
        }
    }

}
