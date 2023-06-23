package com.impacthack.scf.invoiceStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceStatusRepository extends JpaRepository<InvoiceStatus, Long> {
  // List<InvoiceStatus> findByPublished(boolean published);

  // List<InvoiceStatus> findByTitleContaining(String title);
}
