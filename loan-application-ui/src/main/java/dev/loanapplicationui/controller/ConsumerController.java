package dev.loanapplicationui.controller;

import dev.loanapplicationui.DTO.Consumer;
import dev.loanapplicationui.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ConsumerController {
    private final ConsumerService consumerService;

    @GetMapping("/consumers")
    public String consumerList(Model model){
        List<Consumer> consumerList = consumerService.getConsumers();
        model.addAttribute("consumers", consumerList);
        return "consumers-page";
    }

    @PostMapping("/consumers")
    public String saveConsumer(@Valid Consumer consumer){
        ResponseEntity<Consumer> responseEntity;
        try {
            responseEntity = consumerService.save(consumer);
            log.warn("Response entity is: "+ responseEntity);
        }
        catch (HttpClientErrorException e){
            // Bad practice
            if(e.getMessage().contains("Consumer already exists with the given identification number.")){
                return "/error/consumer-already-exists-page";
            }
            else{
                return "/error/something-went-wrong-page";
            }
        }
        return "redirect:/consumers";
    }
    @PostMapping("/consumers/update")
    public String updateCustomer(@Valid Consumer consumer,
                                 @RequestParam long identificationNumber
    ){
        consumer.setIdentificationNumber(identificationNumber);
        log.info("Updating consumer with the id of "+identificationNumber+". New info : "+consumer);
        consumerService.update(consumer);
        return "redirect:/consumers";
    }

    @GetMapping("/consumers/delete")
    public String deleteConsumer(@RequestParam long identificationNumber){
        consumerService.delete(identificationNumber);
        return "redirect:/consumers";
    }

    @GetMapping("/consumers/update-form")
    public String updateForm(@RequestParam @ModelAttribute(name = "identificationNumber") long identificationNumber,
                             @RequestParam @ModelAttribute(name = "forename") String forename,
                             @RequestParam @ModelAttribute(name = "surname")String surname,
                             @RequestParam @ModelAttribute(name = "creditScore")int creditScore,
                             @ModelAttribute(name = "consumer") Consumer consumer){
        return "consumer-update-page";
    }

    @GetMapping("/new-consumer")
    public String consumerForm(@ModelAttribute(name = "consumer")  Consumer consumer){
        return "new-consumer-page";
    }
}
