This is a [Next.js](https://nextjs.org/) project bootstrapped with [`create-next-app`](https://github.com/vercel/next.js/tree/canary/packages/create-next-app).

### Properties
 - JDK 17
 - Kotlin 1.8.20
 - Ktor 2.2.4
 
### How to start project

Frontend : First, run the development server:

```bash
-npm install
-npm run dev
```
Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

Backend : Run 'Application.kt'


### Entities

- ExchangeRateInput
- ExchangeRatePayload

### ExchangeRateInput

-   baseCurrency: ``String``
-   targetCurrency: ``String``,
-   amount: ````Double````

### ExchangeRatePayload
- success: ````Boolean?````
- timestamp: ````Long?````
- base: ````String````
- date: ````Date?````
- rates: ````Map<String, Double>````
- amount: ````Double````


### Rest API

- routing

### Request
````
method: POST
url: /exchange
requestSample : http://localhost:8080/exchange
requestParams : -
requestBody: ExchangeRateInput
````

### Response

````
{
"success": true,
"timestamp": 1682435463,
"base": "TRY",
"date": "2023-04-25",
"rates": {
    "USD": 0.051481
},
"amount": 5.1480999999999995
}