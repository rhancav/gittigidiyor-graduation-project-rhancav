package dev.loanapplicationui.controller;

import dev.loanapplicationui.DTO.Consumer;
import dev.loanapplicationui.service.ConsumerService;
import dev.loanapplicationui.utilities.UtilityMethods;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ConsumerController {
    private final ConsumerService consumerService;

    @GetMapping("/consumers")
    public String consumerList(Model model){
        List<Consumer> consumerList = consumerService.getConsumers();
        log.warn("Consumer list at controller level: "+ consumerList);
        model.addAttribute("consumers", consumerList);
        return "consumers-page";
    }

    @PostMapping("/consumers")
    public String saveConsumer(@ModelAttribute @Valid Consumer consumer, Model model, BindingResult bindingResult){
        UtilityMethods.logErrors(bindingResult);
        model.addAttribute("consumer", consumer);
        ResponseEntity<Consumer> responseEntity = consumerService.save(consumer);
        log.warn("Response entity is: "+ responseEntity.getBody().toString());
        return "redirect:/consumers";
    }

    @GetMapping("/new-consumer")
    public String newConsumer(@ModelAttribute Consumer consumer, Model model, BindingResult bindingResult){
        UtilityMethods.logErrors(bindingResult);
        model.addAttribute("consumer", consumer);
        return "new-consumer-page";
    }
}
