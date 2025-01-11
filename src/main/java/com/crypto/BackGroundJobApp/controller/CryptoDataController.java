package com.crypto.BackGroundJobApp.controller;

import com.crypto.BackGroundJobApp.service.CryptoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CryptoDataController {

    @Autowired
    private CryptoDataService cryptoDataService;

    /**
     * Endpoint to get the latest statistics of a cryptocurrency.
     *
     * @param coinId Query parameter specifying the cryptocurrency (e.g., "bitcoin", "ethereum", "matic-network")
     * @return A map containing price, marketCap, and 24hChange for the requested cryptocurrency
     */
    @GetMapping("/stats")
    public Map<String, Object> getCryptoStats(@RequestParam("coin") String coinId) {
        return cryptoDataService.getLatestCryptoData(coinId);
    }

    /**
     * Endpoint to get the standard deviation of the price for the last 100 records.
     *
     * @param coinId Query parameter specifying the cryptocurrency (e.g., "bitcoin", "ethereum", "matic-network")
     * @return A map containing the standard deviation of the prices
     */
    @GetMapping("/deviation")
    public Map<String, Object> getCryptoPriceDeviation(@RequestParam("coin") String coinId) {
        return cryptoDataService.getPriceStandardDeviation(coinId);
    }
}
