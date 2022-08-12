package ru.iteco.teachbase.springjunior.gateway.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class GatewayController {
    private final CurrencyApiService currencyApiService;

    public GatewayController(CurrencyApiService currencyApiService) {
        this.currencyApiService = currencyApiService;
    }

    @GetMapping("/")
    public String getHome(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            log.info("USER: {}", principal.getClaims());
            log.info("AUTH: {}", principal.getAuthorities());
            model.addAttribute("profile", principal.getClaims());
        }
        return "index";
    }

    @GetMapping("/all-exchange")
    public @ResponseBody String getAllExchange() {
        return currencyApiService.getAllExchange();
    }


    @GetMapping("/convert")
    public @ResponseBody String getConvert() {
        return currencyApiService.convert();
    }

}
