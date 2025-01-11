package com.crypto.BackGroundJobApp.service;

import com.crypto.BackGroundJobApp.model.CryptoData;
import com.crypto.BackGroundJobApp.repository.CryptoDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@Service
public class CryptoDataService {

    @Autowired
    private CryptoDataRepository cryptoDataRepository;

    // Method to fetch the latest cryptocurrency data
    public Map<String, Object> getLatestCryptoData(String coinId) {
        Optional<Optional<CryptoData>> latestData = Optional.ofNullable(cryptoDataRepository.findTopByCoinIdOrderByTimestampDesc(coinId));

        if (latestData.isPresent()) {
            Optional<CryptoData> data = latestData.get();
            Map<String, Object> response = new HashMap<>();
            response.put("price", data.get().getPrice());
            response.put("marketCap", data.get().getMarketCap());
            response.put("24hChange", data.get().getChange24h());
            return response;
        } else {
            throw new RuntimeException("No data found for coin: " + coinId);
        }
    }

    // Method to calculate standard deviation
    public Map<String, Object> getPriceStandardDeviation(String coinId) {
        List<CryptoData> dataList = cryptoDataRepository.findTop100ByCoinIdOrderByTimestampDesc(coinId);
        if (dataList.size() < 2) {
            throw new RuntimeException("Insufficient data to calculate standard deviation.");
        }

        double[] prices = dataList.stream().mapToDouble(CryptoData::getPrice).toArray();
        double mean = calculateMean(prices);
        double stdDev = calculateStandardDeviation(prices, mean);

        Map<String, Object> result = new HashMap<>();
        result.put("deviation", stdDev);
        return result;
    }

    private double calculateMean(double[] prices) {
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        return sum / prices.length;
    }

    private double calculateStandardDeviation(double[] prices, double mean) {
        double sumSquaredDiff = 0;
        for (double price : prices) {
            sumSquaredDiff += Math.pow(price - mean, 2);
        }
        return Math.sqrt(sumSquaredDiff / prices.length);
    }
}
