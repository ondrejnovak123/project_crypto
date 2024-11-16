package com.example.project_crypto.Controller;

import com.example.project_crypto.Model.Crypto;
import com.example.project_crypto.Service.CryptoService;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class CryptoController {
    CryptoService cryptoService = new CryptoService();

    @GetMapping("/cryptos/{id}")
    public String getCryptoById(
            @PathVariable("id") Integer id
    ){
       return cryptoService.getCryptoInfo(id);
    }
    @PutMapping("/cryptos/{id}")
    public String updateCrypto(
            @PathVariable("id") Integer id, @RequestBody Crypto crypto
    ){
        return cryptoService.updateCrypto(id, crypto);
    }

    @GetMapping("/portfolio-value")
    public String getPortfolioValue(
    ){
        return cryptoService.getPortfolioValue();
    }

    @PostMapping("/cryptos")
    public String addCrypto(@RequestBody Crypto crypto){
        return cryptoService.addCrypto(crypto);
    }

    @GetMapping("/cryptos")
    public String getAllCryptoInfo(@RequestParam(value = "sort" , defaultValue = "") String sort){
        switch (sort) {
            case "price" -> cryptoService.sortCryptosByPrice();
            case "name" -> cryptoService.sortCryptosByName();
            case "quantity" -> cryptoService.sortCryptosByQuantity();
        }

        return cryptoService.getAllInfo();
    }

}
