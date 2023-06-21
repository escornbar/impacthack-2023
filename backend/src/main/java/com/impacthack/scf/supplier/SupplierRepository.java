package com.impacthack.scf.supplier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
  // List<Supplier> findByPublished(boolean published);

  List<Supplier> findByNameContaining(String title);
}
