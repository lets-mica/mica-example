# Mica Redis 示例

本示例演示了 mica-redis 的所有核心功能，基于官方文档：https://gitee.com/596392912/mica/raw/master/mica-redis/README.md

## 功能列表

### 1. Redis Cache 增强
- ✅ 支持 `#` 号分隔缓存名和超时时间
- ✅ 支持多种时间单位：ms（毫秒）、s（秒）、m（分）、h（小时）、d（天）
- ✅ `MicaRedisCache` 简化 Redis 使用

**示例文件：**
- `RedisService.java` - 使用 `@Cacheable` 注解示例
- `RedisController.java` - 使用 `MicaRedisCache` 示例

**API 端点：**
```bash
# 使用 @Cacheable 注解，缓存 5 分钟
GET /redis/cache/user/{id}

# 使用 MicaRedisCache
GET /redis/cache/simple/{id}

# 使用 MicaRedisCache 带过期时间
GET /redis/cache/ttl/{id}
```

### 2. 分布式限流
- ✅ 注解方式 `@RateLimiter`
- ✅ Client 方式 `RateLimiterClient`
- ✅ 支持参数化限流（使用 Spring EL 表达式）

**示例文件：**
- `RedisController.java` - 限流功能示例

**API 端点：**
```bash
# 注解方式限流（每分钟最多 10 次）
GET /redis/limiter/annotation

# 带参数的注解限流（每个用户每分钟最多 5 次）
GET /redis/limiter/user/{userId}

# Client 方式限流
GET /redis/limiter/client

# Client Allow 方式限流
GET /redis/limiter/allow
```

### 3. Redis Stream 消息队列
- ✅ 发送单个消息、批量消息
- ✅ 删除消息、修剪流
- ✅ 注解方式监听 `@RStreamListener`
- ✅ Bean 方式监听 `RStreamListenerCustomizer`
- ✅ 支持集群模式和广播模式
- ✅ 支持不同的读取偏移量模式

**示例文件：**
- `RedisController.java` - Stream 发送示例
- `RedisStreamListener.java` - 注解方式监听示例
- `RedisStreamListenerCustomizer.java` - Bean 方式监听示例

**API 端点：**
```bash
# 发送单个消息
POST /redis/stream/send
Content-Type: application/json
{
  "orderId": "ORDER-001",
  "userId": "USER-001",
  "amount": 99.99,
  "status": "PENDING",
  "createTime": 1701408000000
}

# 发送带 key 的消息
POST /redis/stream/send-with-key

# 批量发送
POST /redis/stream/send-batch

# 删除消息
DELETE /redis/stream/delete/{recordId}

# 修剪流
POST /redis/stream/trim/{count}
```

### 4. Redis PubSub 发布订阅
- ✅ 发布消息 `RPubSubPublisher`
- ✅ 注解方式监听 `@RPubSubListener`
- ✅ Bean 方式监听 `RPubSubListenerCustomizer`

**示例文件：**
- `RedisPubSubController.java` - PubSub 发布示例
- `RedisPubSubListener.java` - 注解方式监听示例
- `RedisPubSubListenerCustomizer.java` - Bean 方式监听示例

**API 端点：**
```bash
# 发布消息到指定频道
POST /redis/pubsub/publish/{channel}
Content-Type: application/json
{
  "orderId": "ORDER-001",
  "userId": "USER-001",
  "amount": 99.99
}

# 发布订单消息
POST /redis/pubsub/order

# 发布测试消息
POST /redis/pubsub/test
```

### 5. Redis Key 过期事件
- ✅ 监听 Redis Key 过期事件
- ✅ 异步处理过期事件

**示例文件：**
- `RedisKeyExpiredListener.java` - Key 过期事件监听示例

## 配置说明

在 `application.yml` 中添加以下配置：

```yaml
mica:
  redis:
    # redis key 失效事件
    key-expired-event:
      enable: true
    # 分布式限流
    rate-limiter:
      enable: true
    # 序列化方式：JSON、JDK
    serializer-type: JSON
    # Redis Stream 配置
    stream:
      enable: true
      # consumer group，默认：服务名 + 环境
      consumer-group: ${spring.application.name}
      # 消费者名称，默认：ip + 端口
      consumer-name: ${spring.application.name}-consumer
```

## 依赖说明

在 `pom.xml` 中已添加：

```xml
<dependency>
    <groupId>net.dreamlu</groupId>
    <artifactId>mica-redis</artifactId>
</dependency>
```

## 测试步骤

### 前置条件
1. 安装并启动 Redis 服务
2. 配置 Spring Boot Redis 连接信息（在 application.yml 中）：
```yaml
spring:
  data:
    redis:
      host: localhost
      port: 6379
      password: # 如果有密码
```

### 测试缓存功能
```bash
# 第一次访问会查询数据库（查看日志）
curl http://localhost:8080/redis/cache/user/1

# 第二次访问直接从缓存获取（不会打印查询日志）
curl http://localhost:8080/redis/cache/user/1
```

### 测试限流功能
```bash
# 快速连续请求，超过限制后会返回限流异常
for i in {1..15}; do curl http://localhost:8080/redis/limiter/annotation; done
```

### 测试 Stream 消息
```bash
# 发送消息
curl -X POST http://localhost:8080/redis/stream/send \
  -H "Content-Type: application/json" \
  -d '{
    "orderId": "ORDER-001",
    "userId": "USER-001",
    "amount": 99.99,
    "status": "PENDING",
    "createTime": 1701408000000
  }'

# 查看日志，监听器会自动接收并处理消息
```

### 测试 PubSub 消息
```bash
# 发布消息
curl -X POST http://localhost:8080/redis/pubsub/order \
  -H "Content-Type: application/json" \
  -d '{
    "orderId": "ORDER-002",
    "userId": "USER-002",
    "amount": 199.99
  }'

# 查看日志，监听器会自动接收并处理消息
```

### 测试 Key 过期事件
```bash
# 使用 redis-cli 设置一个带过期时间的 key
redis-cli
> SET user:test "test-value" EX 10

# 等待 10 秒后，查看应用日志，会打印 key 过期事件
```

## 文件说明

| 文件名 | 说明 |
|--------|------|
| `RedisController.java` | Redis 缓存、限流、Stream 功能测试接口 |
| `RedisService.java` | 使用 @Cacheable 注解的缓存服务 |
| `RedisPubSubController.java` | PubSub 发布消息接口 |
| `RedisPubSubListener.java` | PubSub 注解方式监听器 |
| `RedisPubSubListenerCustomizer.java` | PubSub Bean 方式监听器 |
| `RedisStreamListener.java` | Stream 注解方式监听器 |
| `RedisStreamListenerCustomizer.java` | Stream Bean 方式监听器 |
| `RedisKeyExpiredListener.java` | Redis Key 过期事件监听器 |
| `OrderDto.java` | 订单数据传输对象 |

## 注意事项

1. **Redis 服务必须运行** - 所有功能都依赖 Redis 服务
2. **Key 过期事件** - 需要在 Redis 配置中启用 `notify-keyspace-events`：
   ```bash
   redis-cli config set notify-keyspace-events Ex
   ```
3. **限流功能** - 高并发场景下建议使用 Redis Cluster
4. **Stream 消费组** - 首次使用需要创建消费组，框架会自动创建
5. **序列化** - 默认使用 JSON 序列化，对象需要实现 `Serializable` 接口

## 参考链接

- [mica-redis 官方文档](https://gitee.com/596392912/mica/raw/master/mica-redis/README.md)
- [Redis 官方文档](https://redis.io/docs/)
- [Spring Data Redis](https://spring.io/projects/spring-data-redis)
