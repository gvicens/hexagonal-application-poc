# Hexagonal application test

This application is a PoC corresponding to the use of an hexagonal architecture.

The characteristics are:

* Use of Spring/Boot for the implementation of adapters.
* REST endpoints to query about product prices, filtering by application date, product id and company id
* Search with or without priority. In the second case, only the most priority product is returned.
* Response with product id, company id, applicable rate, application dates and final price.
* Use of an in-memory database, in this case H2.
* Automated unit tests are included.


## Testing the endpoint

To test the REST endpoint run the *main class* HexagonalApp under *src/main/java* and send GET requests using your preferred tool. For instance:

http://localhost:8080/products?applicationDateTime=2020-06-16T21:00&CompanyId=1&productId=35455

To launch the tests execute the *ProductControllerTest* class under *src/main/test*.


## Implementation of Hexagonal Architecture

Hexagonal architecture was originally defined by Alistair Cockburn in his [Hexagonal Architecture](https://alistair.cockburn.us/hexagonal-architecture/) article. Please refer to that article for more information on how this architecture is structured.

For this PoC two interfaces were created:

* *ProductService*: Primary interface to query for products. 
* *ProductRepository*: Secondary interface to communicate with a database or other persistent storage.

We also have a *Product* as domain object, and a class called *ProductServiceImpl* that implements the Application layer. For the sake of the PoC it has been kept very simple, but the Application layer is where all your domain business logic would be.

None of these interfaces and classes have any dependency except for Java basic ones and Lombok as a convenience. Therefore, you could completely redo the Infrastructure part, even changing Spring Boot for plain Java or Quarkus and it should continue to work.

For this PoC, the primary adapter interface was implemented as a REST endpoint, while the secondary interface was implemented as a connection to an H2 in-memory database.