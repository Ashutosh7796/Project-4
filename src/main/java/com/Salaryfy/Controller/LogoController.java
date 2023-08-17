package com.Salaryfy.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class LogoController {

    private static final String CLEARBIT_LOGO_API = "https://logo.clearbit.com/";

    @GetMapping("/logo/{companyName}")
    public String getCompanyLogo(@PathVariable String companyName) {
        String logoUrl = CLEARBIT_LOGO_API + companyName.toLowerCase().replace(" ", "") + ".com";
        return logoUrl;
    }
}
