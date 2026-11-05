# Baku City Tour & Landmark Guide

Spring Boot 3 + Thymeleaf + JPA + H2, ready for Railway.

## Run locally
    mvn clean package
    java -jar target/bakuguide.jar
Open http://localhost:8080

## Deploy to Railway
1. Push this folder to a GitHub repo.
2. railway.com -> New Project -> Deploy from GitHub repo.
3. Service -> Settings -> Networking -> Generate Domain.

Optional images: put baku photos in src/main/resources/static/images/
named icherisheher.jpg, flame-towers.jpg, heydar-aliyev-center.jpg,
baku-boulevard.jpg, maiden-tower.jpg

Security note: H2 console is enabled at /h2-console. Set
spring.h2.console.enabled=false before sharing your public URL widely.
