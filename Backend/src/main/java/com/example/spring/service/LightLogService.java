package com.example.spring.service;

import com.example.spring.entity.LightLog;
import com.example.spring.payload.request.LedStatus;
import com.example.spring.repository.LightLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LightLogService {

    @Autowired
    private LightLogRepository lightLogRepository;

    public void saveLedStatus(LedStatus ledStatus) {
        // Tạo một đối tượng LightLog để lưu trạng thái LED vào cơ sở dữ liệu
        LightLog lightLog = new LightLog();
        lightLog.setLightId(ledStatus.getLightId());
        lightLog.setDescription("LED status change");
        lightLog.setTimestamp(new Date());
        lightLog.setStatus(ledStatus.getStatus());

        // Lưu trạng thái LED vào cơ sở dữ liệu
        lightLogRepository.save(lightLog);
    }
}
