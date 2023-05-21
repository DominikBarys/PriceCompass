# PriceCompass

## Description
PriceCompass is a system for finding the cheapest products in selected counties. Products can be searched on the basis of such criteria as: country, product category or a specific point of sale.
After finding the products of interest by the user, all the necessary information is displayed, such as the price, the address where the point of sale offering this product is located and the district in which it is located.

##


In the application, an appropriate endpoint is created for each entity on which CRUD operations such as
GET, POST, PUT, PATCH or DELETE can be performed. Available endpoints: /addresses ,/categories, /countries, /offers, /products, /salesPoints

##

The application is written according to the second level of the Richardson Maturity Model, so it does not support HATEOAS.

## Technologies used to write the application:
* **Java 17**
* **Spring Boot with dependencies:**
  * *Spring Web*
  * *Spring Data JPA*
  * *MySQL Driver*
* **MySQL 8.0**   

#

#### The project is still in development, the following things to implement in the future:
* Add the ability to log in as a user
* Add the ability to comment offers by users
* Add the ability to add offers to the "favorites" tab by the user
* Add a front end to application for better data visualization
* Add Spring Security
