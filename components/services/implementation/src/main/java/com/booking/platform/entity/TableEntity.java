package com.booking.platform.entity;

import com.booking.platform.iface.constants.DatabaseConstants;
import com.booking.platform.iface.enums.TableType;
import com.booking.platform.iface.response.TableResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DatabaseConstants.TABLE_TABLE_NAME, schema = DatabaseConstants.SCHEMA_NAME)
public class TableEntity {


    @Id
    @UuidGenerator
    @Column(name = "table_id")
    private UUID tableId;

    @Column(name = "table_number")
    private String tableNumber;

    @Column(name = "seats_count")
    private int seatSCount;

    @Column(name = "table_type")
    @Enumerated(EnumType.STRING)
    private TableType tableType;

    @Column(name = "is_free")
    private Boolean isFree;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate;


    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

   public TableResponse toTableResponse(){
        return new TableResponse(tableId,tableNumber,seatSCount,tableType,isFree,bookingDate,departureDate,company.toResponse());
    }

}
