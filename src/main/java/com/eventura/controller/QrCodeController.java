package com.eventura.controller;

import com.eventura.entity.QrCode;
import com.eventura.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qr-codes")
public class QrCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @PostMapping
    public QrCode createQrCode(@RequestBody QrCode qrCode) {
        return qrCodeService.saveQrCode(qrCode);
    }

    @GetMapping("/{id}")
    public QrCode getQrCodeById(@PathVariable Long id) {
        return qrCodeService.getQrCodeById(id);
    }
}
