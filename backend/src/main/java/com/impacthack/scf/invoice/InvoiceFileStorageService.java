package com.impacthack.scf.invoice;

import java.nio.file.Path;
import java.util.stream.Stream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class InvoiceFileStorageService {

  @Autowired
  private InvoiceRepository invoiceRepository;

  public Invoice store(MultipartFile file, Invoice invoiceData) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    Invoice _invoice = invoiceRepository.save(
				new Invoice(invoiceData.getTotal(), invoiceData.getIssuedDate(), invoiceData.getPaymentDeadline(), invoiceData.getPurchaseOrder(), invoiceData.getInvoiceStatus(), fileName, file.getContentType(), invoiceData.getDownPayment(), invoiceData.getRemainingPayment(), file.getBytes()));

      return _invoice;
  }

  // public Invoice getFile(Long id) {
  //   return invoiceRepository.findById(id).get();
  // }
  
  // public Stream<Invoice> getAllFiles() {
  //   return invoiceRepository.findAll().stream();
  // }
}
