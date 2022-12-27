package ru.itis.services.impl;

import ru.itis.dao.SupportRepository;
import ru.itis.models.Support;
import ru.itis.services.SupportService;

public class SupportServiceImpl implements SupportService {

    private final SupportRepository supportRepository;

    public SupportServiceImpl(SupportRepository supportRepository) {
        this.supportRepository = supportRepository;
    }

    @Override
    public void save(Support support) {
        supportRepository.save(support);
    }
}
