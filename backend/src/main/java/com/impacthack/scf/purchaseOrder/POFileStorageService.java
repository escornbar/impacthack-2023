package com.impacthack.scf.purchaseOrder;

import java.nio.file.Path;
import java.util.stream.Stream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class POFileStorageService {

  @Autowired
  private PurchaseOrderRepository purchaseOrderRepository;

  public PurchaseOrder store(MultipartFile file, PurchaseOrder purchaseOrderData) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    PurchaseOrder _purchaseOrder = purchaseOrderRepository.save(
				new PurchaseOrder(purchaseOrderData.getTotal(), purchaseOrderData.getOrderDate(), purchaseOrderData.getSupplier(), purchaseOrderData.getDistributor(), fileName, file.getContentType(), file.getBytes()));

      return _purchaseOrder;
  }

  public PurchaseOrder getFile(Long id) {
    return purchaseOrderRepository.findById(id).get();
  }
  
  public Stream<PurchaseOrder> getAllFiles() {
    return purchaseOrderRepository.findAll().stream();
  }
}
