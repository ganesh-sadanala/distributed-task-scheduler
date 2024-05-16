**Project**
- Distributed Task Scheduler using Redis
- Build a distributed task scheduler using Java and Spring Boot that utilizes Redis as a distributed cache.
- Tasks can be submitted to the scheduler, and it distributes the tasks across multiple nodes in a Redis cluster for parallel execution.
- Implement features like task prioritization, task persistence, and fault tolerance using Redis features like Pub/Sub, Lua scripting, and data structures.
- Utilize Redis Sentinel for high availability and automatic failover.
- To test (simulate a distributed environment), create docker containers where each container represents a node in distributed system.

**Redis Cluster set up**
- Run ```docker-compose up``` on the `docker-compose.yml` file.

