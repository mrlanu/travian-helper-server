package com.lanu.travian_helper.controllers;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.lanu.travian_helper.models.Attack;
import com.lanu.travian_helper.models.AttacksString;
import com.lanu.travian_helper.services.ParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private ParsingService parsingService;

    @GetMapping
    public String getGreeting(){
        return "Hello";
    }

    @PostMapping("/attacks")
    public List<Attack> getAllAttacks(@Valid @RequestBody AttacksString stringAttacksForParse){
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("https://ts4.travian.ru/dorf1.php");
            System.out.println(page.getTitleText());

            final HtmlForm form = page.getFormByName("login");
            final HtmlButton button = form.getButtonByName("s1");
            final HtmlTextInput textField = form.getInputByName("name");
            final HtmlPasswordInput textFieldPass = form.getInputByName("password");
            textField.type("Баба Яга");
            textFieldPass.type("28333555");
            final HtmlPage page2 = button.click();

            final HtmlPage page3 = page2.getAnchorByHref("?newdid=18091&").click();

            System.out.println("KURWA++++++++>>>>>>>>>>>>>>>");
            System.out.println(page2.getAnchorByHref("?newdid=18091&"));
            System.out.println("Fuck");
            List<HtmlAnchor> list = page3.getAnchors();
            for (HtmlAnchor a : list)System.out.println(a + "\n");


        } catch (IOException e) {
            System.out.println("Error");
        }
        return parsingService.getAllAttacks(stringAttacksForParse);
    }
}
