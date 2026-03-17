package com.booking.platform.entity;

import com.booking.platform.iface.constants.DatabaseConstants;
import com.booking.platform.iface.enums.CompanyType;
import com.booking.platform.iface.request.company.AbstractCompanyRequest;
import com.booking.platform.iface.response.CompanyResponse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DatabaseConstants.COMPANY_TABLE_NAME, schema = DatabaseConstants.SCHEMA_NAME)
public class CompanyEntity {


    @Id
    @UuidGenerator
    @Column(name = "company_id")
    private UUID companyId;

    @Column(name = "company_name")
    private String companyName;

    @Enumerated(EnumType.STRING)
    @Column(name = "company_type")
    private CompanyType companyType;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "company_email")
    private String companyEmail;

    @Column(name = "working_hours")
    private String workingHours;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<TableEntity> tables;

    public CompanyEntity(AbstractCompanyRequest request, UUID companyId) {
        this.companyId = companyId;
        this.companyName = request.getCompanyName();
        this.companyType = request.getCompanyType();
        this.address = request.getAddress();
        this.phone = request.getPhone();
        this.companyEmail = request.getCompanyEmail();
        this.workingHours = request.getWorkingHours();
    }

    public CompanyResponse toResponse() {
        return new CompanyResponse(
                companyId,
                companyName,
                companyType,
                address,
                phone,
                companyEmail,
                workingHours,
                tables == null ? List.of() : tables.stream().map(TableEntity::toTableResponse).toList());
    }
}
