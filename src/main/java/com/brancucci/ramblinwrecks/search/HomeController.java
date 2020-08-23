package com.brancucci.ramblinwrecks.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private SearchService searchService;

    @Autowired
    public HomeController(SearchService searchService){
        this.searchService = searchService;
    }

    @GetMapping(value = "/")
    public HomeDto home(){
        return searchService.getLandingPage();
    }
}
