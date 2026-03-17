package com.booking.platform.springJpa;

import com.booking.platform.entity.CompanyEntity;
import com.booking.platform.iface.enums.CompanyType;
import com.booking.platform.iface.exceptions.companyException.CompanyAlreadyException;
import com.booking.platform.iface.exceptions.companyException.CompanyApiException;
import com.booking.platform.iface.exceptions.companyException.CompanyBadRequestException;
import com.booking.platform.iface.exceptions.companyException.CompanyNotFoundException;
import com.booking.platform.iface.request.company.CreateCompanyRequest;
import com.booking.platform.iface.request.company.UpdateCompanyRequest;
import com.booking.platform.iface.response.CompanyResponse;
import com.booking.platform.iface.service.CompanyService;
import com.booking.platform.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceSpringJpa implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyResponse createCompany(CreateCompanyRequest request) {
        try {
            CompanyEntity companyEntity = companyRepository.findByCompanyEmail(request.getCompanyEmail());
            if (companyEntity != null) {
                throw new CompanyAlreadyException("Company already exists with  email" + request.getCompanyEmail());
            }
            return companyRepository.save(new CompanyEntity(request, null)).toResponse();
        } catch (Exception e) {
            if (e instanceof CompanyAlreadyException) {
                throw e;
            }
            throw new CompanyApiException("Error while creating company entity");
        }
    }

    @Override
    public List<CompanyResponse> getCompanies(CompanyType type) {
        List<CompanyEntity> result;
        try {
            if (type == null) {
                result = companyRepository.findAll();
            } else {
                result = companyRepository.findByCompanyType(type);
            }
            return result
                    .stream()
                    .map(CompanyEntity::toResponse)
                    .toList();
        } catch (Exception e) {
            throw new CompanyApiException("Error while getting companies");
        }
    }

    @Override
    public CompanyResponse getCompany(UUID companyId) {
        try {
            return companyRepository.findById(companyId)
                    .orElseThrow(() -> new CompanyNotFoundException("Company not found with id" + companyId))
                    .toResponse();
        } catch (Exception e) {
            if (e instanceof CompanyNotFoundException) {
                throw e;
            }
            throw new CompanyApiException("Error while getting company");
        }

    }

    @Override
    public CompanyResponse updateCompany(UUID companyId, UpdateCompanyRequest request) {
        CompanyEntity companyEntity;

        if (request.getCompanyId() != null && !request.getCompanyId().equals(companyId)) {
            throw new CompanyBadRequestException("ID of path and ID of request are different");
        }
        getCompany(companyId);
        try {
            companyEntity = companyRepository
                    .findByCompanyEmailAndCompanyIdNot(request.getCompanyEmail(), companyId);

        } catch (Exception e) {
            throw new CompanyApiException("Error while getting company");
        }
        if (companyEntity != null) {
            throw new CompanyAlreadyException("Company already exists with  email" + request.getCompanyEmail());
        }

        try {
            return companyRepository.save(new CompanyEntity(request, companyId)).toResponse();
        } catch (Exception e) {
            throw new CompanyApiException("Error while saving company");
        }
    }

    @Override
    public void deleteCompany(UUID companyId) {
        getCompany(companyId);
        try {
            companyRepository.deleteById(companyId);
        } catch (Exception e) {
            throw new CompanyApiException("Error while deleting company");
        }
    }


}
