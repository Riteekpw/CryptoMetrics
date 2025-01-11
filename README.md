# Cryptocurrency Data Monitoring Application

A Spring Boot-based application to fetch, store, and analyze real-time cryptocurrency data using the CoinGecko API. This project provides RESTful APIs to access the latest statistics, market insights, and advanced metrics like price volatility for cryptocurrencies such as Bitcoin, Ethereum, and Matic.

---

## **Features**
- Automated data fetching from the CoinGecko API every 2 hours.
- RESTful APIs to:
  - Retrieve the latest cryptocurrency statistics (price, market cap, and 24-hour percentage change).
  - Calculate the standard deviation of prices based on the last 100 records.
- Persistent data storage using MySQL.
- Scalable architecture with a layered design for better maintainability.

---

## **Technologies Used**
- **Backend**: Spring Boot, Spring Data JPA
- **Database**: MySQL
- **External API**: CoinGecko API
- **Libraries & Tools**:
  - Jackson ObjectMapper (for JSON parsing)
  - RestTemplate (for HTTP requests)
  - Spring Scheduler (for scheduled tasks)

---

## **Getting Started**

### Prerequisites
Make sure you have the following installed:
- **Java** (version 17 or higher)
- **Maven**
- **MySQL**
- **Postman** or any API testing tool (optional)

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/crypto-monitoring-app.git
   cd crypto-monitoring-app
