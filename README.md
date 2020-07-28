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

Circuit breaker activate when backend service is down
```
Maheshs-MacBook-Pro:api-gateway mahesh$ http :8082/greeting/mahesh
HTTP/1.1 200 OK
Content-Length: 10
Content-Type: application/json

"Fallback"
```

### Build docker images

inorder to application up and run it required to bridge the connection between backend service and frontend service.
for that enable below 
`#person.service.endpoint=http://producer:8081`

* Better to define the image name
```
<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<imageName>person-frontend-service</imageName>
				</configuration>
			</plugin>
		</plugins>
	</build>
```

* Build image using spring-boot:build-image command
* create a docker network using `docker create network ntw-person-be-fe`
* `docker container run --network ntw-person-be-fe --name producer -p 8081:8081 -d person-backend-service`
* `docker container run --network ntw-person-be-fe --name consumer -p 8082:8082 -d person-frontend-service`

Invoke same endpoints