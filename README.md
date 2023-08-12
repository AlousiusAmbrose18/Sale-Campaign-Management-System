# Sale-Campaign-Management-System

Welcome to the Ecommerce Sale Campaign Management System project! This project is designed to manage an extensive catalog of products and their associated sale campaigns in an ecommerce startup.

## Table of Contents
- [Introduction](#introduction)
- [Technologies](#technologies)
- [Features](#features)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)

## Introduction

This project is an innovative solution for managing product information and sale campaigns. It offers APIs to interact with the system, including creating products, 
setting up sale campaigns, and Restoring product price after Sale end.

## Technologies

- Java (JDK-17)
- Spring Boot
- maven (build tool)
- H2 Database (In-memory database)

## Features

- Create, retrieve, update, and delete products
- Create, retrieve, and manage sale campaigns
- Dynamic price adjustments during campaigns
- Automatic price restoration after campaign ends

## Getting Started

1. Clone the repository: `git clone https://github.com/AlousiusAmbrose18/Sale-Campaign-Management-System.git`
2. Navigate to the project directory: `cd Sale-Campaign-Management-System`
3. Build the project: `./mvnw clean install`
4. Run the application: `./mvnw spring-boot:run`


## API Endpoints
Application Host : `http://localhost:8080/`

   ## Product-Controller

      1. To Get All Products with PageNumber and PageSize   :  `http://localhost:8080/products`
         ex  : `localhost:8080/products?page=1&pageSize=10`

         ## Product Response
          
           {
            "content": [
                         {
                            "id": "jeiu8f1",
                            "mrp": 1010.0,
                            "currentPrice": 910.0,
                            "discount": 10.0,
                            "inventoryCount": 2
                          },
                       ],
                            "totalElements": 5,
                            "totalPages": 1,
                            "size": 10,
                            "number": 0
                            etc..,
            }
            
   
      2. To Update Product Price By id  :  `http://localhost:8080/products/{productId}/update-price`
         ex : `http://localhost:8080/products/{jeiu8f1}/100`

   ## Pricing-History-Controller

     1. To Get All History of Product Price : `http://localhost:8080/products/{productId}/priceHistory`
        ex : `http://localhost:8080/products/{1}/priceHistory` 

   ## Sale-Campaign-Controller

     1. To Create Sale Campaign    :  `http://localhost:8080/campaigns/create`

       1. JSON Request

       {
            "title" : "Diwali Discount",
            "startDate" : "2023-08-10",
            "endDate" : "2023-08-12",
            "campaignDiscounts" : [
                {
                    "productId" : "jeiu8f3",
                    "discount" : "20"
                },
                {
                    "productId" : "jeiu8f4",
                    "discount" : "20"
                }
            ]
        }

          

     2. To Get Sale Campaign By Id :  `http://localhost:8080/campaigns/{id}`
        ex : `http://localhost:8080/campaigns/{1}`

        1. JSON Response

       {
              "id": 1,
              "title": "Christmas Discount",
              "startDate": "2023-08-10",
              "endDate": "2023-08-12",
              "campaignDiscounts": [
                  {
                      "id": 1,
                      "productId": "jeiu8f1",
                      "discount": "20"
                  },
                  {
                      "id": 2,
                      "productId": "jeiu8f3",
                      "discount": "20"
                  }
              ],
              "isDiscount": true
          }

        

     3. To Get All Sale Campaigns :  `http://localhost:8080/campaigns`

       1. JSON Response

         [
            {
                "id": 1,
                "title": "Christmas Discount",
                "startDate": "2023-08-10",
                "endDate": "2023-08-12",
                "campaignDiscounts": [
                    {
                        "id": 1,
                        "productId": "jeiu8f1",
                        "discount": "20"
                    },
                    {
                        "id": 2,
                        "productId": "jeiu8f3",
                        "discount": "20"
                    }
                ],
                "isDiscount": true
            },
            {
                "id": 2,
                "title": "Diwali Discount",
                "startDate": "2023-08-10",
                "endDate": "2023-08-12",
                "campaignDiscounts": [
                    {
                        "id": 3,
                        "productId": "jeiu8f3",
                        "discount": "20"
                    },
                    {
                        "id": 4,
                        "productId": "jeiu8f4",
                        "discount": "20"
                    }
                ],
                "isDiscount": true
            }
        ]

## How Product Price Restored

When the new Sale campaign is made, the property called "isDiscount" will be set to true. The price of the product will change based on 
how much it's discounted in the CampaignDiscount (products that are on sale). If we want to get information about the sale campaign, first 
it will see if the discount period has finished or not. If it's not finished, the sale campaign details will be shown as they are. But 
if the sale period is finished, a method called "restoreProductPrice()" will be used to bring back the original product price, and I will 
change "isDiscount" to false in the sale campaign.
     

## Contributing

Contributions are welcome! If you find a bug or want to add a new feature, feel free to open an issue or submit a pull request.
