package com.booking.platform.repository;


import com.booking.platform.entity.CompanyEntity;
import com.booking.platform.iface.enums.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    CompanyEntity findByCompanyEmail(String email);

    List<CompanyEntity> findByCompanyType(CompanyType type);

    CompanyEntity findByCompanyEmailAndCompanyIdNot(String email, UUID companyId);

}
