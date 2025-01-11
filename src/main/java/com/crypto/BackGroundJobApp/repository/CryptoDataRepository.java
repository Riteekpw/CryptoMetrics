package com.crypto.BackGroundJobApp.repository;

import com.crypto.BackGroundJobApp.model.CryptoData;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CryptoDataRepository extends JpaRepository<CryptoData, Long> {

    // Fetch the latest data by coinId
    Optional<CryptoData> findTopByCoinIdOrderByTimestampDesc(String coinId);

    // Fetch the latest 100 records by coinId
    List<CryptoData> findTop100ByCoinIdOrderByTimestampDesc(String coinId);

    // Example custom query to fetch data within a specific date range
    @Query("SELECT c FROM CryptoData c WHERE c.coinId = :coinId AND c.timestamp BETWEEN :startTimestamp AND :endTimestamp ORDER BY c.timestamp DESC")
    List<CryptoData> findByCoinIdAndTimestampBetween(String coinId, long startTimestamp, long endTimestamp);
}
