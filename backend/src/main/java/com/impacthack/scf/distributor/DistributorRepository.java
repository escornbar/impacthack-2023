package com.impacthack.scf.distributor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DistributorRepository extends JpaRepository<Distributor, Long> {
  // List<Distributor> findByPublished(boolean published);

  List<Distributor> findByNameContaining(String title);
}
