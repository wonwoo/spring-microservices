# spring-microservices

* spring microservices


## clone

```
git clone https://github.com/wonwoo/spring-microservices.git
```

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

## test

* eureka server 
    1. [http://localhost:8761/](http://localhost:8761/)
    2. Instances currently registered with Eureka 
        - SPRING-ZUUL-SERVER (192.168.0.6:spring-zuul-server:8765)
        - USERS (192.168.0.6:users:8081 , 192.168.0.6:users:8080)
        - BOARD (192.168.0.6:board:8082 , 192.168.0.6:board:8083)

* users1 server
    1. curl [http://localhost:8080/](http://localhost:8080/)
        
            [
                {
                    id: 1,
                    name: "wonwoo",
                    password: "123123"
                },
                {
                    id: 2,
                    name: "kebin",
                    password: "8888"
                }
            ]
        
* user2 server
    1. curl [http://localhost:8081/](http://localhost:8081/)
        
            [
                {
                    id: 1,
                    name: "wonwoo",
                    password: "123123"
                },
                {
                    id: 2,
                    name: "kebin",
                    password: "8888"
                }
            ]

* board1 server
    1. curl [http://localhost:8082/](http://localhost:8082/)
        
            [
                {
                    id: 1,
                    title: "title",
                    content: "content"
                },
                {
                    id: 2,
                    title: ""spring boot microservices",
                    content: "Hello spring boot microservices"
                }
            ]


* board2 server
    1. curl [http://localhost:8083/](http://localhost:8083/)
        
            [
                {
                    id: 1,
                    title: "title",
                    content: "content"
                },
                {
                    id: 2,
                    title: ""spring boot microservices",
                    content: "Hello spring boot microservices"
                }
            ]
        

* edge server
    1. curl [http://localhost:8765/users](http://localhost:8765/users)

            [
                {
                    id: 1,
                    name: "wonwoo",
                    password: "123123"
                },
                {
                    id: 2,
                    name: "kebin",
                    password: "8888"
                }
            ]
    2. curl [http://localhost:8765/board](http://localhost:8765/board)
    
            [
                {
                    id: 1,
                    title: "title",
                    content: "content"
                },
                {
                    id: 2,
                    title: ""spring boot microservices",
                    content: "Hello spring boot microservices"
                }
            ]
        
    3. load balancing
        * curl [http://localhost:8765/users/env/server.port](http://localhost:8765/users/env/server.port)
        
                {
                    server.port: "8081"
                }
        
        * curl [http://localhost:8765/users/env/server.port](http://localhost:8765/users/env/server.port)
        
                {
                    server.port: "8080"
                }
        
* config server (cloud git my https://github.com/wonwoo/microservices-config.git)
    1. your git create repository
    2. curl [http://localhost:8765/info](http://localhost:8765/info)
        
            {
                component: "Zuul Server"
            }
        
    3. component property modify

        ```
        cd microservices-config/
        vi spring-zuul-server.yml 
        ```
        
        ```
        info:
              component: Zuul Server modify test
        ```
        
    4. git push 
   
        ```
        git add spring-zuul-server.yml 
        git commit spring-zuul-server.yml -m 'zuul server modify'
        git push -u origin master
        ```
        
    5. curl [http://localhost:8765/info](http://localhost:8765/info)
    
            {
                component: "Zuul Server"
            }
        
    6. curl -X POST [http://localhost:8765/refresh](http://localhost:8765/refresh)
        
            [
                "info.component"
            ]
        
    7. curl [http://localhost:8765/info](http://localhost:8765/info)
        
            {
                component: "Zuul Server modify test"
            }
        
