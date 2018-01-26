# Rule Engine
- Gradle기반 프로젝트
- 여러개의 Rule을 추가 및 유지보수를 고려한 Flexible한 룰 엔진 구현
- Rest API 구현.
- RuleConfig에서 초기 룰을 세팅.
- Rule은 여러개의 Condition으로 구성됨. 최종적으로 CheckCondition을 통해, FDS를 판단.
- RuleEngineManager가 세팅된 룰들을 가지고 user_id에 FDS체크.
- RuleEngine은 내부적으로 threadpool을 가지고 각각의 룰FDS체크를 multithread로 동작함.
- 애플리케이션 실행시 H2 DB에 초기 데이터가 세팅되어 테스트함(data.sql)

## Start project
- build, run
```
$ ./gradlew build
$ ./gradlew bootrun
```
---

## example (resources/data.sql)
- user_id 10021이 계좌개설하고 7일이내 10만원금액을 5번 받음
```
$ curl -X GET 'http://localhost:8080/v1/fraud/10021'
{
  "user_id": 10021,
  "is_fraud": true,
  "rule": "RuleB"
}

$ curl -X GET 'http://localhost:8080/v1/fraud/10023'
{
  "user_id": 10023,
  "is_fraud": false,
  "rule": ""
}

```
![swagger-ui](/docs/images/swagger_ui.png)

## processing log
![multithread-processing](/docs/images/multithread_processing.png)

---

## dashboard
```
- http://localhost:8080/          # Swagger
- http://localhost:8080/console   # H2 console (URL : jdbc:h2:mem:mydb / username : sa)
```

---
## Library
| name | version  | download link |
| --- | --- | --- |
| SpringBoot | v1.5.9 | https://projects.spring.io/spring-boot/ |
| Lombok | v1.16.8 | https://projectlombok.org/ |
| springfox | v2.6.0 | http://springfox.github.io/springfox/ |
| H2 | v1.4.190 | http://www.h2database.com/ |
| Spring Data JPA | 1.11.9 | https://projects.spring.io/spring-data-jpa |
| QueryDSL | v4.1.4 | http://www.querydsl.com/ |