package com.impacthack.scf.company;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
  // List<Company> findByPublished(boolean published);

  List<Company> findByNameContaining(String title);
}
