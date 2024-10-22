package com.eventura.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eventura.repository.QrCodeRepository;
import com.eventura.entity.QrCode;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class QrCodeService {

    @Autowired
    private QrCodeRepository qrCodeRepository;  // Add this line to inject the repository

    public String generateQRCode(String data) throws Exception {
        String filePath = "qr-codes/qr_" + System.currentTimeMillis() + ".png";
        int width = 300;
        int height = 300;
        String fileType = "png";

        // QR code generation logic
        BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, fileType, path);

        return filePath;
    }

    public QrCode saveQrCode(QrCode qrCode) {
        return qrCodeRepository.save(qrCode);  // Now the repository should be available
    }

    public QrCode getQrCodeById(Long id) {
        Optional<QrCode> qrCode = qrCodeRepository.findById(id);
        return qrCode.orElseThrow(() -> new RuntimeException("QR code not found with id " + id));
    }
}
