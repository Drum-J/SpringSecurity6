### Spring Security 6.1 변경점

코드 작성 방식이 람다식으로 변경되었다.

### 기존 코드
```java
http.authorizeHttpRequests()
        .requestMatchers("/myUrl").authenticated()
        .anyRequest().permitall()
        .and()
        .formLogin()
        .and()
        .httpBasic();
```

---
### 변경 코드
```java
http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myUrl").authenticated()
                .anyRequest().permitAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
```

---

코드를 보면 바로 알 수 있듯이 .requestMatchers() 등으로 계속 연결 되던 것들이   
authorizeHttpRequests() 안에서 람다 식으로 작성되는 것을 볼 수 있다.

또한 .and()의 사용이 없어졌다.

이렇게 코드 작성 방식을 바꾼 이유는 아래와 같다고 한다.

- 자동 들여쓰기가 구성을 더 읽기 쉽게 만듭니다.
- 설정 옵션을 연결할 때 .and()를 사용할 필요가 없습니다.
- Spring Security DSL 은 스프링 인테그레이션과 스프링 클라우드 게이트웨이와 같은 다른 스프링 DSL들과 유사한 설정 방식을 가지고 있습니다.

또 앞으로 2-3년 이내에 출시될 예정인 Spring Security7 에서는 사용하지 않는 메소드들이 제거될 예정이라고 한다.

Deprecated 된 것들이 많았는데 7에서 완전 제거될 모양이다.