# 캐싱이란?
동일한 데이터에 반복해서 접근해야 하거나 많은 연산이 필요한 일일때, 결과를 빠르게 이용하고자 성능이 

좋거나 가까운 곳에 저장하고 접근하는 것을 말한다

![image](https://github.com/lilseongwon/BE-Roadmap/assets/102791105/43ecf743-7f52-401e-999e-ed84bb6b1951)

# Spring Cache?
spring cache는 cache 기능의 추상화를 지원하는데, EhCache, Couchbase, Redis 등의 추가적인 캐시 저장소와 빠르게 연동하여 bean으로 설정 할 수 있도록 도와준다. 만일 추가적인 캐시 저장소와 연결하지 않는다면, ConcurrentHashMap 기반의 Map 저장소가 자동으로 추가된다. 캐시를 쓰긴 써야하는데, EhCache까지는 쓸 필요는 없고, 간단하게 몇몇 토큰 정도만 캐시처리가 필요 한 경우 빠르게 사용 할 수 있다.

## 왜 쓰는데?
원래 데이터를 조회할 때는 DB에서 가져오기 위해 SQL 쿼리를 날리는데, 이것의 비용은 꽤 크다. 대신 캐싱 설정된 메소드를 호출하여 조회된 데이터를 캐싱하면 그 다음부터는 쿼리를 날릴 필요없이 저장소에서 데이터를 가져올 수 있어서 시간을 획기적으로 절약할 수 있다.

## Redis vs LocalCache
로컬 캐시 데이터는 어플리케이션에 내장되어 있어 어플리케이션이 죽으면 없어진다. 또한 분산 서버가 10개 있다고 하면 각자의 서버마다 캐시 저장소가 생긴다. Redis 등의 캐싱 서버는 반대로 서버가 다운돼도 disk에 저장되어 안전하고 분산 서버에서도 사용이 가능하며, 가용성이 높다. 하지만 싱글 스레드라는 단점이 있고, 네트워크 통신이 필요하다는 부분에서 오버스펙이 될 수 있다. 따라서 **단일 서버에서는 Local캐시를, 분산 서버에서는 캐싱 서버를** 사용하자

# 사용법
## **1. @EnableCaching 추가**
Spring에서 @Cacheable과 같은 어노테이션 기반의 캐시 기능을 사용하기 위해서는 먼저 별도의 선언이 필요하다. 그렇기 때문에 @EnableCaching 어노테이션을 설정 클래스에 추가해주어야 한다.

```java
@EnableCaching
@Configuration
public class CacheConfig {
    ...
}
```

## **2. 캐시 매니저 빈 추가**
어노테이션을 추가한 후에는 캐시를 관리해줄 CacheManager를 빈으로 등록해주어야 한다. Spring은 현재 다음과 같은 캐시 매니저들을 제공하고 있다.

- ConcurrentMapCacheManager: Java의ConcurrentHashMap을 사용해 구현한 캐시를 사용하는 캐시매니저
- SimpleCacheManager: 기본적으로 제공하는 캐시가 없어 사용할 캐시를 직접 등록하여 사용하기 위한 캐시매니저
- EhCacheCacheManager: 자바에서 유명한 캐시 프레임워크 중 하나인 EhCache를 지원하는 캐시 매니저
- CompositeCacheManager: 1개 이상의 캐시 매니저를 사용하도록 지원해주는 혼합 캐시 매니저
- CaffeineCacheManager: Java 8로 Guava 캐시를 재작성한 Caffeine 캐시를 사용하는 캐시 매니저
- JCacheCacheManager: JSR-107 기반의 캐시를 사용하는 캐시 매니저

**redis는 직렬화/역직렬화 문제 때문에 따로 config 클래스를 만들어줘야 한다.**

### **CacheConfig**
```java
@EnableCaching
@Configuration
public class CacheConfig {

    /**
     * Spring Boot 가 기본적으로 RedisCacheManager 를 자동 설정해줘서 RedisCacheConfiguration 없어도 사용 가능
     * Bean 을 새로 선언하면 직접 설정한 RedisCacheConfiguration 이 적용됨
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(60))
                .disableCachingNullValues()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
                )
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())
                );
    }

    /**
     * 여러 Redis Cache 에 관한 설정을 하고 싶다면 RedisCacheManagerBuilderCustomizer 를 사용할 수 있음
     */
    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration("cache1",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .computePrefixWith(cacheName -> "prefix::" + cacheName + "::")
                                .entryTtl(Duration.ofSeconds(120))
                                .disableCachingNullValues()
                                .serializeKeysWith(
                                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
                                )
                                .serializeValuesWith(
                                        RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())
                                ))
                .withCacheConfiguration("cache2",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofHours(2))
                                .disableCachingNullValues());
    }
}
```

## 3. 어노테이션을 메소드에 추가
```java
@Cacheable(value = "menu", key = "#menuId")
public Menu getMenu(Long menuId) {}
```

여기서 value는 캐시 데이터의 이름이 되고, key는 캐시의 키값(식별자)이 된다.

# 주요 어노테이션
### @Cacheable
- key: 해당 키의 데이터를 캐시에 저장하고 조회한다

데이터를 캐시에 저장하는 어노테이션이다.

메소드의 반환값이 캐시에 저장되어있으면 메소드 내용을 실행하지 않고 값을 반환하고, 저장되어있지 않으면 메소드 내용 실행 후 반환값을 캐시에 저장한다.

```java
@Cacheable(value = "menu", key = "#menuId")
public Menu getMenu(Long menuId) {...}
```

### @CacheEvict
캐시 데이터를 삭제하는 어노테이션이다.

- key: 해당 키의 데이터만 캐시에서 삭제한다
- allEntries: 캐시에 저장된 값을 모두 삭제할 때 사용한다
- beforeInvocation: 메서드 실행 이전에 캐시 데이터의 삭제 여부를 설정하는 속성이다. 메서드 결과에 의존하지 않을 때 유용하다

```java
@CacheaEvict(value = "menu", allEntries = true, beforeInvocation = true)
public void deleteMenu() {...}
```

### @CachePut
```java
@CachePut(value = "menu", key = "#menuId")
public Menu updateMenu(Long menuId) {...}
```

캐시 데이터를 수정하는 어노테이션이다. Cacheable과 유사하게 실행 결과를 캐시에 저장하지만, 저장된 캐시 내용을 사용하지 않고 **무조건 메서드 내용을 실행한다**는 차이가 있다



참고: https://bcp0109.tistory.com/386
