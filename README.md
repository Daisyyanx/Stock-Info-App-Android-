# 📈 Stock Price Analysis App


## 🔍 Overview

This project includes:
- A native Android app
- A Java Servlet backend (hosted on Codespace)
- MongoDB Atlas for logging
- A web-based dashboard for analytics

Users enter a company name in the app, which fetches the latest 10-day stock prices via a servlet. The backend logs each request to MongoDB, and the dashboard visualizes usage and system stats.

---

## 🧩 System Structure

- **Android App**:  
  User input → HTTP GET → displays company name, ticker, and 10-day price list

- **Java Servlet**:  
  Receives request → calls Financial Modeling Prep API → stores logs in MongoDB → returns JSON response

- **MongoDB Log**:  
  Each record includes: date, companyName, priceList, volumeList, phoneModel, status, errorMessage, response time, etc.

- **Dashboard**:  
  Displays analytics from logs including search frequency, API latency, phone model usage, error counts, etc.

---

## 📊 Dashboard Modules

1. Top 10 most searched companies
2. API average response time
3. Phone model ranking
4. Full log table view
5. Stock price volatility (standard deviation)
6. Company search frequency (`requestCount`)
7. Status + error message summary

---

## 🛠️ Tech Stack

- Android (Java)
- Java Servlet (deployed on Codespace)
- MongoDB Atlas
- HTML + JavaScript (dashboard)
- External API: Financial Modeling Prep

---

## 🚀 How to Run

> Note: This project is for demonstration purposes and may require setup steps depending on your environment.

- The Android app can be run in Android Studio (`Project4Android`)
- The servlet is deployed on Codespace and listens to GET requests at `/stockinfo`
- MongoDB Atlas stores all logs automatically; the connection string is configured in the servlet
- To view the dashboard, open `dashboard.html` in browser (data loads from MongoDB)

## 📄 Notes

This project is developed for educational purposes only as part of the CMU distributed systems course.