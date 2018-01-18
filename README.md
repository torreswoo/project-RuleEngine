# Rule Engine
- Gradle기반 프로젝트
- 여러개의 Rule을 추가 및 유지보수를 고려한 Flexible한 룰 엔진 구현
- Rest API 구현

## Start project
- build, run
```
$ ./gradlew build
$ ./gradlew bootrun
```

---
## dashboard
```
- http://localhost:8080/          # Swagger
- http://localhost:8080/intro     # intro page (Thymeleaf)
- http://localhost:8080/dashboard # App Dashboard
- http://localhost:8080/console   # H2 console (URL : jdbc:h2:mem:mydb / username : sa)
```

---
## Library
| name | version  | download link |
| --- | --- | --- |
| SpringBoot | v1.5.9 | https://projects.spring.io/spring-boot/ |
| Lombok | v1.16.8 | https://projectlombok.org/ |
| jQuery | v1.12.1 | http://jquery.com | 
| jQuery UI | v1.12.1 | https://jqueryui.com |
| Bootstrap | v3.3.7 | https://getbootstrap.com/docs/3.3/ |
| Font Awesome | v4.7.0 | http://fontawesome.io/ |
| Highcharts | v6.0.4 (2017-12-15) | https://www.highcharts.com/download |
| jolokia | v1.3.7 | https://jolokia.org/download.html |
| springfox | v2.6.0 | http://springfox.github.io/springfox/ |
| H2 | v1.4.190 | http://www.h2database.com/ |
| Spring Data JPA | 1.11.9 | https://projects.spring.io/spring-data-jpa |
| QueryDSL | v4.1.4 | http://www.querydsl.com/ |