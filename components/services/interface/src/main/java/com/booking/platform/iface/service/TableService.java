package com.booking.platform.iface.service;


import com.booking.platform.iface.request.table.CreateTableRequest;
import com.booking.platform.iface.response.TableResponse;

import java.util.List;

public interface TableService {

    List<TableResponse> createTable(CreateTableRequest request, String email);
}
