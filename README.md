# Spring Cloud API Gateway
API Gateway is benifical for many cases starting from security...etc. For mor information please refer 
https://cloud.spring.io/spring-cloud-gateway/reference/html/

In this repository 
* Forwarding and rewriting
* Resiliency4j circuit breaker

Run each microservices individually and execute below endpoints

```
Maheshs-MacBook-Pro:person-frontend-service mahesh$ http :8082/greeting/
HTTP/1.1 200 OK
Content-Length: 14
Content-Type: text/plain;charset=UTF-8

Hello world..1

Maheshs-MacBook-Pro:person-frontend-service mahesh$ http :8082/greeting/mahesh
HTTP/1.1 200 OK
Content-Length: 11
Content-Type: text/plain;charset=UTF-8

Hellomahesh

Maheshs-MacBook-Pro:person-frontend-service mahesh$ http :8082/edge/mahesh
HTTP/1.1 200 OK
Content-Length: 11
Content-Type: text/plain;charset=UTF-8

Hellomahesh


```

Circuit breaker 
```
Maheshs-MacBook-Pro:api-gateway mahesh$ http :8082/greeting/mahesh
HTTP/1.1 200 OK
Content-Length: 10
Content-Type: application/json

"Fallback"
```