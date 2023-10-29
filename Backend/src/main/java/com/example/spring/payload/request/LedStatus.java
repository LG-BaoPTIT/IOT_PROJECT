package com.example.spring.payload.request;

import com.example.spring.entity.Device;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LedStatus {
    private String lightId;

    private String description;

    private Device device;

    private Date timestamp;

    private String status; // Trạng thái (Bật/Tắt) -> (1/0)
}
