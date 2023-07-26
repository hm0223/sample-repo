# Read Me First

# Getting Started

### Reference Documentation
[Spring Boot Logging](https://docs.spring.io/spring-boot/docs/2.7.7/reference/html/features.html#features.logging)

[Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.7.7/reference/html/actuator.html#actuator.endpoints)

[Spring Boot Actuator 动态修改日志级别](https://docs.spring.io/spring-boot/docs/2.7.7/reference/html/actuator.html#actuator.loggers)

[深入浅出日志体系（logback最佳实践）](https://blog.csdn.net/significantfrank/article/details/127994390?spm=1001.2014.3001.5502)

### Guides
> 开发访问端点
```yaml
management:
  endpoints:
    web:
      exposure:
        include: loggers
```

> 按组查询日志级别
```shell
curl GET 'http://ip:port/actuator/loggers/{your-logging-group-name}'
```

> 按包查询日志级别
```shell
curl GET 'http://ip:port/actuator/loggers/{package-name}'
```


> 修改日志级别
```shell
curl -X POST 'http://ip:port/actuator/loggers/{your-logging-group-name/package-name}'
```

> 动态修改组名为 `dynamic-group-sample` 组的日志级别为 DEBUG
```shell
curl -X POST 'http://localhost:8080/actuator/loggers/dynamic-group-sample' \
--header 'Content-Type: application/json' \
--data-raw '{
    "configuredLevel": "DEBUG"
}'
```

