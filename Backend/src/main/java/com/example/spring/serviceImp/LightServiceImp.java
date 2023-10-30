package com.example.spring.serviceImp;

import com.example.spring.entity.LightLog;
import com.example.spring.repository.LightRepository;
import com.example.spring.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LightServiceImp implements LightService {
    @Autowired
    private LightRepository lightRepository;

    @Override
    public LightLog save(LightLog lightLog) {
        return lightRepository.save(lightLog);
    }
}
