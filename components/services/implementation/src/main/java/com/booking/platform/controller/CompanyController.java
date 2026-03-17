package com.booking.platform.controller;


import com.booking.platform.ExceptionResponse;
import com.booking.platform.iface.constants.RoutConstants;
import com.booking.platform.iface.enums.CompanyType;
import com.booking.platform.iface.request.company.CreateCompanyRequest;
import com.booking.platform.iface.request.company.UpdateCompanyRequest;
import com.booking.platform.iface.response.CompanyResponse;
import com.booking.platform.iface.service.CompanyService;
import com.booking.platform.security.RequiredSuperAdmin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@SecurityRequirement(name = "Authorization")
@RequestMapping(RoutConstants.BASE_URL + "${platform.booking.version}" + RoutConstants.COMPANIES)
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Operation(summary = "Create company with specified parameters.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Company created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request to sent endpoint", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "409", description = "Company already exists", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while creating company", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public CompanyResponse createCompany(@RequestBody @Valid CreateCompanyRequest request) {
        log.info("Received request to create company: {}", request.getCompanyName());
        CompanyResponse company = companyService.createCompany(request);
        log.info("Created company: {}", request.getCompanyName());
        return company;
    }

    @Operation(summary = "Get list of  companies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Companies returned", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while getting companies", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
    })
    @GetMapping
    public List<CompanyResponse> getCompanies(@RequestParam(required = false)CompanyType type) {
        log.info("Received request to get all companies");
        List<CompanyResponse> companies = companyService.getCompanies(type);
        log.info("Found {} companies", companies.size());
        return companies;

    }

    @Operation(summary = "Get company by given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Company not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while getting company", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
    })
    @GetMapping("/{id}")
    public  CompanyResponse getCompany(@PathVariable("id") UUID companyId){
        log.info("Received request to get company with id: {}", companyId);
        CompanyResponse company = companyService.getCompany(companyId);
        log.info("Found company: {}", company);
        return company;
    }

    @Operation(summary = "Update company with specified parameters.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request to sent endpoint", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Company not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "409", description = "Company already exists", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while updating company", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
    })
    @PutMapping("/{id}")
    public CompanyResponse updateCompany(@PathVariable("id") UUID companyId,@RequestBody @Valid UpdateCompanyRequest request) {
        log.info("Received request to update company with id: {}", companyId);
        CompanyResponse company = companyService.updateCompany(companyId, request);
        log.info("Updated company: {}", company);
        return company;
    }

    @RequiredSuperAdmin
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete company by given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Company deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Company not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while deleting company", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
    })
    @DeleteMapping("/{id}")
    public  void deleteCompany(@PathVariable("id") UUID companyId){
        log.info("Received request to delete company with id: {}", companyId);
        companyService.deleteCompany(companyId);
        log.info("Deleted {} company", companyId);
    }




}
