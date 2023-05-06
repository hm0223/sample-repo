
# SpringBoot Logging trace

## Features
> 1. record trace id with normal sync log
> 
> 2. record trace id with async log
> 
> 3. record trace id with mq consumer


## Quick Start
### 1. import maven dependency
```xml
  <dependency>
    <groupId>org.example</groupId>
    <artifactId>spring-boot-logging-trace-mq</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### 2. config your own log config logback.xml or log4j2.xml eg...
sample
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration debug="false">
    <!--日志存储路径-->
    <property name="log" value="D:/test/log" />
    <!-- 控制台输出 -->
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--输出格式化-->
            <pattern>[%X{TRACE_ID}] %d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>
    <!-- 按天生成日志文件 -->
    <appender name="file" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--日志文件名-->
            <FileNamePattern>${log}/%d{yyyy-MM-dd}.log</FileNamePattern>
            <!--保留天数-->
            <MaxHistory>30</MaxHistory>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>[%X{TRACE_ID}]  %d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
        <!--日志文件最大的大小-->
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <MaxFileSize>10MB</MaxFileSize>
        </triggeringPolicy>
    </appender>

    <!-- 日志输出级别 -->
    <root level="INFO">
        <appender-ref ref="console" />
        <appender-ref ref="file" />
    </root>
</configuration>
```

### 3. how to get trace id with mq ?

send mq use
`JmsMessagingTemplateTraceWrapper`
such as
```java
@Slf4j
@RestController
public class LogController {
    @Resource
    private JmsTemplateTraceWrapper jmsTemplateWrapper;
    
    @GetMapping("/log")
    public void log() {
        log.info("log is start...");
        Map<String, Object> map = new HashMap<>();
        map.put("Name", "Mark");
        map.put("Age", 47);
        String message = JSON.toJSONString(map);
        jmsTemplateWrapper.convertAndSend("someQueue1", message, new HashMap<>());
        log.info("log is end...");
    }
}
```

receive mq use `@JmsListener`
and impl `JmsTemplateTraceConsumerWrapper`
such as 
```java
@Slf4j
@Component
public class MqTraceConsumerSample implements JmsTemplateTraceConsumerWrapper {

    @JmsListener(destination = "someQueue1")
    @Override
    public void onMessage(Message<?> message) {
        log.info("Rec message >>> \r\n  {}", JSON.toJSONString(message, true));
        log.info("Rec message Payload >>> {}", JSON.toJSONString(message.getPayload(), true));
    }
}
```



