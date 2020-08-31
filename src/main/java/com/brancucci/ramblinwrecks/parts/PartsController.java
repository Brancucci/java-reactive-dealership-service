package com.brancucci.ramblinwrecks.parts;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PartsController {
    private PartsService partsService;
    private static final String PARTS_ORDER_ADD_URI = "/parts/add";

    public PartsController(PartsService partsService) {
        this.partsService = partsService;
    }

    @PostMapping(path = PARTS_ORDER_ADD_URI)
    public Mono<PartsOrder> addPart(@RequestBody Mono<PartsOrder> partsOrder){
        return partsOrder.flatMap(po -> partsService.addPart(po));
    }


}
