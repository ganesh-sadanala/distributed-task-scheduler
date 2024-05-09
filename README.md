**Project**
- Distributed Task Scheduler using Redis
- Build a distributed task scheduler using Java and Spring Boot that utilizes Redis as a distributed cache.
- Tasks can be submitted to the scheduler, and it distributes the tasks across multiple nodes in a Redis cluster for parallel execution.
- Implement features like task prioritization, task persistence, and fault tolerance using Redis features like Pub/Sub, Lua scripting, and data structures.
- Utilize Redis Sentinel for high availability and automatic failover.
- To test (simulate a distributed environment), create docker containers where each container represents a node in distributed system.

**Redis Cluster set up**
- Multiple docker containers represents distributed systems using `bridge` network.
- Create docker network: ```docker network create redis-net```
- Create Redis containers (# nodes = 3)
  ```
     docker run -d --name redis1 --net redis-net redis redis-server --cluster-enabled yes 
     docker run -d --name redis2 --net redis-net redis redis-server --cluster-enabled yes
     docker run -d --name redis3 --net redis-net redis redis-server --cluster-enabled yes
  ```
- Configure Redis cluster. In my case: 172.18.0.2, 172.18.0.3 and 172.18.0.4
```
docker exec -it redis1 sh -c "redis-cli cluster meet <ip-of-redis2> 6379"
docker exec -it redis1 sh -c "redis-cli cluster meet <ip-of-redis3> 6379"

docker exec -it redis2 sh -c "redis-cli cluster meet <ip-of-redis1> 6379"
docker exec -it redis2 sh -c "redis-cli cluster meet <ip-of-redis3> 6379"

docker exec -it redis3 sh -c "redis-cli cluster meet <ip-of-redis1> 6379"
docker exec -it redis3 sh -c "redis-cli cluster meet <ip-of-redis2> 6379"
```
- Initialize Redis Cluster
```
docker exec -it redis1 sh -c "redis-cli --cluster create <ip-of-redis1>:6379 <ip-of-redis2>:6379 <ip-of-redis3>:6379 --cluster-replicas 0"
```
