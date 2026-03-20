package com.booking.platform.springJpa;

import com.booking.platform.entity.CompanyEntity;
import com.booking.platform.entity.TableEntity;
import com.booking.platform.entity.UserEntity;
import com.booking.platform.iface.enums.TableType;
import com.booking.platform.iface.exceptions.tableException.TableAlreadyExistException;
import com.booking.platform.iface.exceptions.tableException.TableApiException;
import com.booking.platform.iface.exceptions.tableException.TableNotFoundException;
import com.booking.platform.iface.request.table.CreateTableRequest;
import com.booking.platform.iface.response.TableResponse;
import com.booking.platform.iface.service.TableService;
import com.booking.platform.repository.CompanyRepository;
import com.booking.platform.repository.TableRepository;
import com.booking.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TableServiceSpringJpa implements TableService {

    @Autowired
    TableRepository  tableRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository  companyRepository;

    @Override
    public List<TableResponse> createTable(CreateTableRequest request, String email) {
        UserEntity user = null;
        CompanyEntity companyEntity = null;
        List<String> existingNumbers = new ArrayList<>();
        List<TableResponse> result = new ArrayList<>();
        try{
            user = userRepository.findByEmail(email);
            companyEntity = companyRepository.findById(user.getCompanyId())
                    .orElseThrow(()-> new TableNotFoundException("Company not found"));
            List<String> tableNumbers = request.getTableNumbers();
            for(String tableNumber : tableNumbers){
                TableEntity tableEntity = tableRepository.findByTableNumber(tableNumber);
                if(tableEntity != null){
                    existingNumbers.add(tableNumber);
                }else {
                    TableEntity newTableEntity = new TableEntity();
                    newTableEntity.setTableNumber(tableNumber);
                    newTableEntity.setTableType(TableType.valueOf(request.getTableType()));
                    newTableEntity.setSeatSCount(request.getSeatNumber());
                    newTableEntity.setCompany(companyEntity);
                    TableEntity savedTable = tableRepository.save(newTableEntity);
                    result.add(savedTable.toTableResponse());

                }
            }
        }catch (Exception e){
            throw  new TableApiException("Error while trying creating table");
        }

        if(!existingNumbers.isEmpty()){
            throw new TableAlreadyExistException("Table already exist following table numbers" +  existingNumbers);
        }
        return result;
    }
}
