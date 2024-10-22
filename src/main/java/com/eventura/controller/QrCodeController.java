package com.eventura.controller;

import com.eventura.entity.QrCode;
import com.eventura.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qr-codes")
public class QrCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @PostMapping
    public ResponseEntity<QrCode> createQrCode(@RequestBody QrCode qrCode) {
        QrCode savedQrCode = qrCodeService.saveQrCode(qrCode);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQrCode);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QrCode> getQrCodeById(@PathVariable Long id) {
        QrCode qrCode = qrCodeService.getQrCodeById(id);
        if (qrCode != null) {
            return ResponseEntity.ok(qrCode);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
