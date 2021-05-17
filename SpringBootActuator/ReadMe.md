# Spring Boot Actuator

스프링 부트에서 애플리케이션을 HTTP Endpoints와 JMX를 통해 모니터링하고 관리하는 기능을 제공

## 의존성 추가

```java
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
}
```

## Endpoints

- 애플리케이션과 상호작용 및 모니터링 제공
- 기본적으로 제공되는 몇 가지 엔드포인트와 직접 추가도 가능

---

- `auditevents`: 인증 정보
- `beans`: 애플리케이션의 스프링 빈을 모두 보여줌
- `caches`: 사용 가능한 캐시들을 보여줌
- `conditions`: configuration 또는 auto-configuration 되는 class들의 성공 여부와 이유를 보여줌
- `configprops`: 모든 @ConfigurationProperties의 리스트를 보여줌
- `env`: 스프링의 ConfigurableEnvironment로 부터 properties를 보여줌
- `flyway`: 마이그레이션 정보
- `health`: 애플리케이션의 헬스 정보를 보여줌
- `httptrace`: HTTP 추적 정보를 보여줌(디폴트 마지막 100 HTTP 요청/응답 반환) **HttpTraceRepository** 빈 등록해야 사용 가능
- `info`: 애프리케이션 관련 임의 정보
- `integrationgraph`
- `loggers`: 애플리케이션의 로거 정보를 보여주고 수정
- `liquibase`: 마이그레이션 정보
- `metrics`: metrics 정보를 보여줌
- `mappings`: Request와 맵핑되어있는 handler 정보를 보여줌
- `scheduledtasks`: 배치 정보
- `sessions`: Spring Session이 가지고 있는 정보를 보여줌
- `shutdown`
- `startup`
- `threaddump`: 스레드 덤프

**웹 애플리케이션일 경우 추가로 사용 가능**

- `heapdump`: 힙 덤프
- `jolokia`
- `logfile`
- `prometheus`

-> 민감 정보들이 포함되어 있으므로, 운영시에 사용시 철저하게 신경써야 함

## configuration (모두 사용하는 설정)

```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

## 추가

spring-boot-admin을 사용하면 웹에서 좀 더 시각적으로 볼수있음.