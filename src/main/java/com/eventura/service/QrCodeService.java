package com.eventura.service;

import com.eventura.entity.QrCode;
import com.eventura.repository.QrCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QrCodeService {

    @Autowired
    private QrCodeRepository qrCodeRepository;

    public QrCode saveQrCode(QrCode qrCode) {
        return qrCodeRepository.save(qrCode);
    }

    public QrCode getQrCodeById(Long id) {
        return qrCodeRepository.findById(id).orElse(null);
    }
}
