# spring-microservices

* spring microservices


## clone

```
git clone https://github.com/wonwoo/spring-microservices.git
```

## config
* config server (cloud git my https://github.com/wonwoo/microservices-config.git)
    1. your git create repository
    2. spring-config-server application.yml your git url
    2. git push
    3. [h2](http://www.h2database.com/html/download.html) database download 


## run 
```
cd spring-microservices
```

* config server run

```
./spring-config-server/start-server-config.sh
```

* eureka server run

```
./spring-eureka-server/start-server-eureka.sh
```


* users1 server run

```
./users-webservices/start-server-users1.sh
```

* users2 server run

```
./users-webservices/start-server-users2.sh
```

* board1 server run

```
./board-webservices/start-server-board1.sh
```

* board2 server run

```
./board-webservices/start-server-board2.sh
```

* edge server run

```
./spring-zuul-server/start-server-zuul.sh
```

* hystrix server run

```
./hystrix-dashboard/start-server-hystrix.sh
```

* auth server run

```
./spring-microservices-auth/start-server-auth.sh
```

## test

* eureka server 
    1. [http://localhost:8761/](http://localhost:8761/)
    2. Instances currently registered with Eureka 
        - SPRING-ZUUL-SERVER (192.168.0.6:spring-zuul-server:8765)
        - USERS (192.168.0.6:users:8081 , 192.168.0.6:users:8080)
        - BOARD (192.168.0.6:board:8082 , 192.168.0.6:board:8083)
        - AUTH-SERVER (192.168.0.6:auth-server:8900)


* auth server
    1. open webbrowser [http://localhost:8765/user](http://localhost:8765/user)
    2. spring security custom login
    3. username : `wonwoo` password : `111`
    4. authorize Confirm 

* edge server
    1. open webbrowser [http://localhost:8765/user](http://localhost:8765/user)

            [
                {
                    id: 1,
                    name: "wonwoo",
                },
                {
                    id: 2,
                    name: "kebin",
                }
            ]
    2. open webbrowser [http://localhost:8765/board](http://localhost:8765/board)
    
            [
                {
                    id: 1,
                    title: "title",
                },
                {
                    id: 2,
                    title: ""spring boot microservices",
                }
            ]
        
    3. load balancing
        * log check
        

* hystrix server
    1. [http://localhost:8001/hystrix](http://localhost:8001/hystrix)
    2. http://localhost:8765/hystrix.stream/ 
    3. Monitor Stream click
    4. [http://localhost:8765/user](http://localhost:8765/user) refresh
    5. [http://localhost:8765/board](http://localhost:8765/board) refresh
    6. hystrix check 

