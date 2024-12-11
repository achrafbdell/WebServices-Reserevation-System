package com.WebServices_Reservation_System.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDTO {

    private String clientName;
    private Date startDate;
    private Date endDate;
    private String roomPreferences;
}
