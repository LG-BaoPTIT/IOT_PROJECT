package com.example.spring.service;

import com.example.spring.entity.LightLog;
import org.springframework.stereotype.Service;

@Service
public interface LightService {
    LightLog save(LightLog lightLog);
}
