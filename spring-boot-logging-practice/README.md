# Read Me First

# Getting Started

### Reference Documentation
[Spring Boot Logging](https://docs.spring.io/spring-boot/docs/2.7.7/reference/html/features.html#features.logging)

[Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.7.7/reference/html/actuator.html#actuator.endpoints)

[Spring Boot Actuator 动态修改日志级别](https://docs.spring.io/spring-boot/docs/2.7.7/reference/html/actuator.html#actuator.loggers)


### Guides
> 开发访问端点
```yaml
management:
  endpoints:
    web:
      exposure:
        include: loggers
```

> 修改日志级别
```shell
curl -X POST 'http://ip:port/actuator/loggers/{your-logging-group-name/package-name}'
```

