package com.booking.platform.controller;

import com.booking.platform.iface.constants.RoutConstants;
import com.booking.platform.iface.request.table.CreateTableRequest;
import com.booking.platform.iface.response.TableResponse;
import com.booking.platform.iface.service.TableService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Log4j2
@RestController
@SecurityRequirement(name = "Authorization")
@RequestMapping(RoutConstants.BASE_URL + "${platform.booking.version}" + RoutConstants.TABLES)

public class TableController {


    @Autowired
    private TableService tableService;



    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<TableResponse> createTable(@RequestBody CreateTableRequest request, Principal principal) {
        String email = principal.getName();
        log.info("Received request to create table");
        List<TableResponse> tables = tableService.createTable(request, email);
        log.info("Table created");
        return tables;
    }
}
