**Project**
- Distributed Task Scheduler using Redis
- Build a distributed task scheduler using Java and Spring Boot that utilizes Redis as a distributed cache and pub/sub.
- Tasks can be submitted to the scheduler, and it distributes the tasks across multiple nodes in a Redis cluster for parallel execution.
- Implement features like task prioritization, task persistence, and fault tolerance using Redis features like Pub/Sub, Lua scripting, and data structures.
- Utilize Redis Sentinel for high availability and automatic failover.
- To test (simulate a distributed environment), create docker containers where each container represents a node in distributed system.

**Redis Cluster set up**
- Run ```docker-compose up``` on the `docker-compose.yml` file.

```docker-compose.yml

version: '3'

services:
  redis1:
    image: redis
    ports:
      - "6379:6379"

  redis2:
    image: redis
    ports:
      - "6380:6379"
    command: redis-server --port 6379 --slaveof redis1 6379

  redis3:
    image: redis
    ports:
      - "6381:6379"
    command: redis-server --port 6379 --slaveof redis1 6379
```

- `redis1` acts as master. The data is replicated in slave containers.
- port mapping is done to map redis ports to host ports so that outside applications can send requests.
- To get the IP address of your Docker host machine, you can follow these steps(filters the output of the ifconfig command to show only the IP addresses (the second column) that are not the loopback address (127.0.0.1).):
  ```dtd
    ifconfig | grep 'inet' | grep -v 127.0.0.1 | awk '{print $2}'
  ```
- The ipaddress is used to send connection to redis docker containers. Every process has the same ip address but 
    different port from outside view.
- The rest api `/tasks` request:
```dtd
 curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"id": "123456", "type": "EMAIL_SENDING", "priority": 1, "status": "pending", "payload": "Hello, world!"}' \
  http://localhost:8080/tasks
```
- Response/Result: 
![api-tasks-response.png](src%2Fmain%2Fresources%2Fapi-tasks-response.png)
- Redis state change:
![redis-api-tasks-state.png](src%2Fmain%2Fresources%2Fredis-api-tasks-state.png)
