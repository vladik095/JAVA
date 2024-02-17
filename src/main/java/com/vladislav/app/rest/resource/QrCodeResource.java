package com.vladislav.app.rest.resource;

import com.vladislav.app.rest.model.DecodedQrResponse;
import com.vladislav.app.rest.model.GenerateQrRequest;
import com.vladislav.app.rest.service.QrCodeService;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import org.springframework.http.HttpHeaders;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class QrCodeResource {

    @Autowired
    private QrCodeService qrCodeService;

    @PostMapping(path = "/api/qr/generate", produces = MediaType.IMAGE_JPEG_VALUE)
    public void generateQr(@RequestBody GenerateQrRequest request, HttpServletResponse response)
            throws MissingRequestValueException, WriterException, IOException {
        if (request == null || request.getQrString() == null || request.getQrString().trim().equals("")) {
            throw new MissingRequestValueException("QR String is required");
        }
        qrCodeService.generateQr(request.getQrString(), response.getOutputStream());
        response.getOutputStream().flush();
    }

    @PostMapping(path = "/api/qr/decode")
    public DecodedQrResponse decodeQr(@RequestParam("qrCode") MultipartFile qrCode)
            throws IOException, NotFoundException {
        String qrCodeString = qrCodeService.decodeQr(qrCode.getBytes());
        return new DecodedQrResponse(qrCodeString);
    }

    @GetMapping("/api/qr/generate")
    public ResponseEntity<byte[]> generateQrFromUrl(@RequestParam String qrString) {
        try {
            if (qrString == null || qrString.trim().isEmpty()) {
                throw new MissingRequestValueException("QR String is required");
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            qrCodeService.generateQr(qrString, outputStream);

            byte[] qrCodeBytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(qrCodeBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new byte[0]);
        }
    }

}
