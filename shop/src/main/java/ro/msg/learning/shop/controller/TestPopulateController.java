package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.service.TestService;

@Profile("test")
@RestController
public class TestPopulateController {

    @Autowired
    private TestService testService;

    @GetMapping("/populate")
    public void populate(){
        testService.populate();
    }

    @GetMapping("/populate/stockFail")
    public void pupulate(){
        testService.populateForFailStock();
    }

    @GetMapping("/clear")
    public void clear(){
        testService.clear();
    }
}
