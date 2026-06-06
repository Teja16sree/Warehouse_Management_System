package com.wms.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Service
public class BarcodeService {

    @Value("${qrcode.output.dir:qrcodes}")
    private String outputDir;

    /**
     * Generate QR code for the given SKU
     * 
     * @param sku the product SKU
     * @return the file path where QR code was saved
     * @throws Exception if QR code generation fails
     */
    public String generateQRCode(String sku) throws Exception {
        if (sku == null || sku.isEmpty()) {
            throw new IllegalArgumentException("SKU cannot be null or empty");
        }

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(
                sku,
                BarcodeFormat.QR_CODE,
                300,
                300);

        BufferedImage image = new BufferedImage(
                300,
                300,
                BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < 300; x++) {
            for (int y = 0; y < 300; y++) {
                image.setRGB(
                        x,
                        y,
                        bitMatrix.get(x, y)
                                ? 0x000000
                                : 0xFFFFFF);
            }
        }

        File folder = new File(outputDir);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath = outputDir + File.separator + sku + ".png";

        ImageIO.write(
                image,
                "png",
                new File(filePath));

        return filePath;
    }
}
