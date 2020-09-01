package com.brancucci.ramblinwrecks.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VendorService {
    private VendorRepository vendorRepository;

    @Autowired
    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public Mono<Vendor> vendorLookup(VendorKey vk){
        return vendorRepository.findById(vk);
    }

    public Mono<Vendor> vendorAdd(Vendor vendor) {
        return vendorRepository.save(vendor);
    }
}
