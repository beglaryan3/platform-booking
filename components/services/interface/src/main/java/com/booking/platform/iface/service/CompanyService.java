package com.booking.platform.iface.service;

import com.booking.platform.iface.enums.CompanyType;
import com.booking.platform.iface.request.company.CreateCompanyRequest;
import com.booking.platform.iface.request.company.UpdateCompanyRequest;
import com.booking.platform.iface.response.CompanyResponse;

import java.util.List;
import java.util.UUID;

public interface CompanyService {

    /**
     * Method for creating new company
     *
     * @param request The data containing the new company information
     * @return created company
     */
    CompanyResponse createCompany(CreateCompanyRequest request);

    /**
     * Method for getting all companies
     *
     * @param type filter of companies
     * @return List containing all companies of the specified type
     */
    List<CompanyResponse> getCompanies(CompanyType type);

    /**
     * Method to get company by ID
     *
     * @param companyId -- ID to get company
     * @return CompanyResponse object containing company data
     */
    CompanyResponse getCompany(UUID companyId);

    /**
     * Method to Update company data
     *
     * @param companyId ID of company to update
     * @param request   object containing update data
     * @return CompanyResponse updated company
     */
    CompanyResponse updateCompany(UUID companyId, UpdateCompanyRequest request);

    /**
     * Method to delete company
     *
     * @param companyId id to delete company
     */
    void deleteCompany(UUID companyId);
}
