# 日志demo

> 日志在项目中起到重要的排错，测试，运维功能，本例是spring boot自带的logback常用基本配置，用于示例日志的用法，以及什么时候用。


## 日志等级划分和使用建议
日志分为trace，debug，info，warn，error
trace，debug可以在调试环境使用，用来打印一些调试信息

info，warn，error可用在生产环境。
info：适合用来打印一些功能的入口和关键性的参数值。
warn：在可容错的环节产生不影响运行的错误时打印信息
error：超出预期外的错误和影响程序运行的错误打印出的信息

## 配置说明
spring boot自带logback支持，可在application.properties里配置一些基本配置，也可另外在logback-spring.xml(spring boot支持的配置文件)中配置一些复杂的信息，如按日期拆分日志等配置。

```xml
<!-- 循环打印日志 -->
    <appender name="dailyRollingFileAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <File>/var/logdemo/log/log.log</File>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- daily rollover -->
            <FileNamePattern>log.%d{yyyy-MM-dd}.log</FileNamePattern>
            <!-- keep 30 days' worth of history -->
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <Pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{35} - %msg %n</Pattern>
        </encoder>
    </appender>
```

上面一段说明在`/var/logdemo/log/`目录下会生成log.log日志文件，文件格式在encoder标签里定义了，而每天的日志则会另外生成以log开头名字带日期的log文件，文件存在30天，随后会被新生成的文件覆盖。

```xml
    <!-- 默认打印级别 -->
    <logger name="com.routz.springdemo.logdemo" level="TRACE" >
        <appender-ref ref="dailyRollingFileAppender"/>
    </logger>
```
定义了appender后在这里引用，表名`com.routz.springdemo.logdemo`包下的类在运行时打印错误都会使用这个appender定义的策略进行输出
