## Java Stock Data Viewer

This is a beginner-friendly Java project that fetches and displays **end-of-day (EOD) stock market data** using the [Marketstack API](https://marketstack.com/). It allows users to input a stock ticker (e.g., AAPL for Apple), and retrieves market data such as:

- Stock **symbol**
- **Stock exchange** name
- **Closing date**
- **Closing price**

This was my **first Java project**, and I used the `Gson` library to parse JSON responses from the API.

## Tech Stack
- Java
- Gson for JSON parsing
- Marketstack Free API (limited data)

## How to Use
1. Clone this repository or download the `.java` file.
2. Install the `gson-2.10.1.jar` library (or add to your classpath).
3. Get a **free Marketstack API key** at [https://marketstack.com](https://marketstack.com).
4. Paste your key into the **API Key constant at the top of the code**
5. Run the program and enter a stock symbol when prompted.

##  Example Output
Stock: AAPL
Date: 2025-07-15T00:00:00+0000
Closing Price: $209.11
Exchange: NASDAQ

## Code
- uses user input from the built in `Scanner` class in Java
- runs on a `while (true)` loop that breaks when the user enters "exit"
- since all stock tickers are uppercase, user input is converted to uppercase to make searching for the stock easier
- uses 2 methods
- uses Http requests to to get data from API
- parses Json response to display data
- output printed
- uses `if else` statements to make sure there is actually data in the array
- there is a `try catch` exception around the code to make sure the program doesn't crash when the API cannot be reached

## What I Learned
I made this code with the help of ChatGPT.
- I expanded my knowledge on using Gson to parse Json
- Using APIs effectively
- handling user input
- using HTTP requests to get API data
- debugging
- I used the VS code editor for this project and it took me about a week to build and debug.  This was a great first step into Java and backend development and I am excited to create more commplex projects in the future.

## UPDATE REQUIRED 
Changes required to the way the date is displayed into a more readable format(e.g., `YYYY-MM-DD`).
