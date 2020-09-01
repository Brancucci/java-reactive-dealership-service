package com.brancucci.ramblinwrecks.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class VendorController {
    private VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping(path = "/vendor/lookup")
    public Mono<Vendor> vendorLookup(@RequestBody  Mono<VendorKey> vendorKey){
        return vendorKey.flatMap(vk -> vendorService.vendorLookup(vk));
    }

    @PostMapping(path = "/vendor/add")
    public Mono<Vendor> vendorAdd(@RequestBody Mono<Vendor> vendor){
        return vendor.flatMap(vend -> vendorService.vendorAdd(vend));
    }
}
