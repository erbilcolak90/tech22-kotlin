
### Properties
 - JDK 17
 - Kotlin 1.8.20
 - Ktor 2.2.4
 
 ### Postman Collection

[Click Here For Postman Collection.zip](https://github.com/erbilcolak90/tech22-kotlin/files/11326972/exchange.postman_collection.zip)

 
### How to start project

Backend : Run 'Application.kt' then use the postman collection for request.

Frontend : First, run the development server:

```bash
-npm install
-npm run dev
```
Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

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
