# springboot_Backend - lost and found - final Coursework

# 🎒 Lost and Found Backend - Final Coursework

This is the complete backend for the **Lost and Found** application, developed as part of the CMJD Final Coursework (Batch 108/109).
The application is built using Spring Boot and provides APIs for user authentication, item reporting, and request management.

---

## 📚 Technologies Used

- **Spring Boot 3.5.0**
- **Spring Security with JWT**
- **Spring Data JPA**
- **MySQL 8.x**
- **Lombok**
- **Java 21**
- **JJWT 0.11.5**

---

## 🗂️ Project Structure
com.example.finalcoursework_lostfound
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
└── fliter
└── util


---

## ⚙️ Configuration

### `application.properties`

```properties
spring.application.name=finalcoursework-lostfound

# Database
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/lostfound_db
spring.datasource.username=root
spring.datasource.password=1234yashi
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# JWT
jwt.secret=YourSecretKeyMustBeAtLeast32CharsLongForHS256
jwt.expiration=3600000
jwt.refresh-expiration=604800000

# Server
server.port=8080

🛡️ API Endpoints Overview
Endpoint	Method	Description
/api/v1/auth/register	POST	Register new user
/api/v1/auth/login	POST	Login and receive JWT token
/api/v1/items	GET	Get all items
/api/v1/items	POST	Create a new item (LOST/FOUND)
/api/v1/requests	POST	Request to claim an item
/api/v1/requests/approve	POST	Admin approves request
...	...	More routes based on role access
🛡️ API Endpoints Overview
Endpoint	Method	Description
/api/v1/auth/register	POST	Register new user
/api/v1/auth/login	POST	Login and receive JWT token
/api/v1/items	GET	Get all items
/api/v1/items	POST	Create a new item (LOST/FOUND)
/api/v1/requests	POST	Request to claim an item
/api/v1/requests/approve	POST	Admin approves request
...	...	More routes based on role access

github link - 