# APM

APM은 Application Performance Monitoring의 약어

- `성능 정보`
- 발생한 `Error 정보`
- Application이 동작중인 서버의 기본적인 `Metric 정보` 수집
- MSA 환경에서 서비스를 구성하는 여러 Application간의 Request를 하나의 Trace로 묶어서 추적 할 수 있는 `분산 Tracing`기능 지원

→ 해당 정보들을 통해 `병목 구간`을 찾아 낼 수 있는 모니터링 서비스

## 시스템 구성

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a411e22b-6e19-40fd-be85-60174cc9ed04/flow.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a411e22b-6e19-40fd-be85-60174cc9ed04/flow.jpg)

- `APM Agent`
    - 실시간으로 Performance 및 Error 데이터를 수집하여 `APM Server`로 보냄
    - APM 서버 연동 실패시 데이터 저장을 위한 `Memory Buffer`가 존재
- `APM Server`
    - `APM Agent`에서 수집된 데이터에 대한 유효성 체크
    - 수집된 데이터를 `ElasticSearch`의 Document 포멧으로 변환
- `ElasticSearch`
    - APM 데이터에 대한 저장, 검색, 분석 기능 제공
    - 성능 데이터에 대한 집계 기능 제공
- `Kibana`
    - UI에서 데이터 필터링 및 Service, Trace, Transaction, Error, Metric에 대한 개요 및 상세 기능 제공
    - DashBoard, Visualization을 이용하여 이용자가 원하는 형태로 데이터 가시화

## APM 데이터 모델 (7.14 최신버전 기준)

- `Spans`
    - 특정 코드의 실행 및 소요시간에 대한 정보를 포함
    - 활동의 시작부터 끝까지 측정
    - `span`사이의 부모/자식 관계를 가질 수 있음
    - 데이터 구성
        - `trasction.id`상위 트랜잭션을 나타내는 속성
        - `parent.id`상위 `span` 또는 트랜잭션을 나타내는 속성
        - `name`
        - `type`, `subtype`, `action`
        - 선택사항으로 `stack trace`
- `Transactions`
    - 트랜잭션과 관련된 추가 속성이 있는 특별한 종류의 `Span`
    - Application 내에서 측정하고 있는 가장 높은 레벨의 작업
        - 서버에 요청
        - Batch Job
        - Background Job
        - 사용자 정의 트랜잭션 타입
    - 데이터 구성
        - `event timestemp`
        - `unique id`, `type`, `name`
        - Event가 기록된 환경에 대한 데이터
            - `Service`- `environment`, `framework`, `language`, etc
            - `Host`- `architecture`, `hostname`, `IP`, etc
            - `Process`- `args`, `PID`, `PPID`, etc
            - `URL`- `full`, `domain`, `port`, `query`, etc
            - `User`- (제공되는 경우) `email`, `ID`, `username`, etc
- `Errors`
    - 어플리케이션에서 발생한 Exception의 정보 및 로그
    - 오류가 고유 ID로 표시
    - 디버깅에 도움이 되도록 `Error Exception`, `Error Log`제공
    - 데이터 구성은 `Transactions`와 동일
- `Metrics`
    - 시스템 및 프로세스 레벨 `CPU`, `Memory`의 metrics 정보 제공
    - JVM metrics도 수집 가능

## 도커로 구성해보기

### Docker Compose

```bash
$ git clone https://github.com/deviantony/docker-elk.git
$ cd docker-elk/
$ docker-compose -f docker-compose.yml -f extensions/apm-server/apm-server-compose.yml up -d
```

### elastic-apm-agent.jar Download

```bash
https://github.com/elastic/apm-agent-java
```

### 자바 실행 파라미터

```bash
java -Delastic.apm.server_urls=http://elasitc-apm-server:8200 
-Delastic.apm.service_name=spring-boot-elastic-apm 
-Delastic.apm.application_packages=kr.example 
-Delastic.apm.environment=local  
-j elastic-apm-agent-{VERSION}.jar -jar ./{BUILD_JAR_FILE}.jar
```

## 실행 모습

### APM 첫 화면

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/62203bf3-8302-421d-bc32-b74020328941/2.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/62203bf3-8302-421d-bc32-b74020328941/2.png)

### 서비스 선택 후 메뉴

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/172dac37-7ded-499f-9c2c-df3e9e292ce5/3.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/172dac37-7ded-499f-9c2c-df3e9e292ce5/3.png)

### Transactions Trace

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fdf8d941-6e40-4492-a8cc-05970cbf1186/4.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fdf8d941-6e40-4492-a8cc-05970cbf1186/4.png)