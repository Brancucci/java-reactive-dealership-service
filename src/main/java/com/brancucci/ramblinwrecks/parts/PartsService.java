package com.brancucci.ramblinwrecks.parts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PartsService {
    private PartsRepository partsRepository;

    @Autowired
    public PartsService(PartsRepository partsRepository) {
        this.partsRepository = partsRepository;
    }

    public Mono<PartsOrder> addPart(PartsOrder po) {
        return partsRepository.save(po);
    }
}
