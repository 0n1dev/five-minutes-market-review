# Spring Data Redis 사용하기

> Spring Data Redis는 두 가지 방식으로 접근이 가능

## Redis 통신 프레임워크

- Lettuce (별도의 설정 필요 없음)
- Jedis (의존성 필요)

## Redis 접근 방식

- RedisTemplate
- RedisRepository

## 왜 StringRedisSerializer를 썼는가?

- GenericJackson2JsonRedisSerializer
    - Object를 자동으로 Json으로 직렬화
    - Object의 Class Type을 함께 넣는 단점이 있음
- Jackson2JsonRedisSerializer
    - Class Type을 포함해서 저장하지 않음
    - 항상 Class Type을 지정해야 Object로 변환 가능
    - 여러 서비스에서 해당 Resource에 접근해서 Serializer를 바꾸면 오류 발생
- StringRedisSerializer
    - ObjectMapper 사용해서 Object <-> Json
    - Object <-> Json 변환을 직접해야하는 단점