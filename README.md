# spring-microservices

* spring microservices

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

* edge server run

```
./spring-eureka-server/start-server-zuul.sh
```

* users1 server run

```
./users-webservices/start-server-users1.sh
```

* users2 server run

```
./users-webservices/start-server-users2.sh
```

## test

* eureka server 
    1. http://localhost:8761/
    2. Instances currently registered with Eureka 
        - SPRING-ZUUL-SERVER (192.168.0.6:spring-zuul-server:8765)
        - USERS (192.168.0.6:users:8081 , 192.168.0.6:users:8080)

* users1 server
    1. curl http://localhost:8080/
        
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
    1. curl http://localhost:8081/
        
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
        

* edge server
    1. curl http://localhost:8765/users
        
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
        
    2. load balancing
        * curl http://localhost:8765/users/env/server.port 
        
        ```
        {
            server.port: "8081"
        }
        ```
        * curl http://localhost:8765/users/env/server.port 
        
        ```
        {
            server.port: "8080"
        }
        ```

* config server (cloud git my https://github.com/wonwoo/microservices-config.git)
    1. your git create repository
    2. curl http://localhost:8765/info
        ```
        {
            component: "Zuul Server"
        }
        ```
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
    5. curl http://localhost:8765/info
        ```
            {
                component: "Zuul Server"
            }
        ```
    6. curl -X POST http://localhost:8765/refresh
        ```
        [
            "info.component"
        ]
        ```
    7. curl http://localhost:8765/info
        ```server.port
            {
                component: "Zuul Server modify test"
            }
        ```
