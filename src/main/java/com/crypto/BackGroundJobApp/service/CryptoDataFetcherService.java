package com.crypto.BackGroundJobApp.service;

import com.crypto.BackGroundJobApp.model.CryptoData;
import com.crypto.BackGroundJobApp.repository.CryptoDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CryptoDataFetcherService {

    @Autowired
    private CryptoDataRepository cryptoDataRepository;

    private static final String COINGECKO_API_URL =
            "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,matic-network,ethereum" +
                    "&vs_currencies=usd&include_market_cap=true&include_24hr_change=true";

    /**
     * Scheduled task to fetch cryptocurrency data every 2 hours and save it to the database.
     */
    @Scheduled(fixedRate = 7200000) // Run every 2 hours (7200000 milliseconds)
    public void fetchAndSaveCryptoData() {
        try {
            System.out.println("Fetching crypto data...");
            RestTemplate restTemplate = new RestTemplate();
            String jsonResponse = restTemplate.getForObject(COINGECKO_API_URL, String.class);

            // Check if the API response is valid
            if (jsonResponse != null) {
                System.out.println("API Response: " + jsonResponse);
                // Parse the JSON response using Jackson
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(jsonResponse);

                // Process Bitcoin
                saveCryptoData(root, "bitcoin");
                // Process Matic
                saveCryptoData(root, "matic-network");
                // Process Ethereum
                saveCryptoData(root, "ethereum");
            } else {
                System.err.println("Error: API response is null.");
            }
        } catch (Exception e) {
            // Log the error
            System.err.println("Error fetching or saving crypto data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Helper method to parse and save cryptocurrency data for a given coin.
     *
     * @param root JSON response root node
     * @param coinId The cryptocurrency ID (e.g., "bitcoin", "matic-network", "ethereum")
     */
    private void saveCryptoData(JsonNode root, String coinId) {
        try {
            if (root.has(coinId)) {
                JsonNode coinData = root.get(coinId);

                double price = coinData.get("usd").asDouble();
                double marketCap = coinData.get("usd_market_cap").asDouble();
                double change24h = coinData.get("usd_24h_change").asDouble();

                // Create a new CryptoData object
                CryptoData cryptoData = new CryptoData(coinId, price, marketCap, change24h);

                // Save the data to the database
                cryptoDataRepository.save(cryptoData);

                System.out.println("Saved data for: " + coinId);
            } else {
                System.err.println("No data found for: " + coinId);
            }
        } catch (Exception e) {
            System.err.println("Error saving data for " + coinId + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
