### Csrf와 Cors

이번 강의에서는 나의 웹 애플리케이션을 해커의 공격으로부터 보호할 수 있는 방법들에 대해 배웠다.

cors도 보안 공격인 줄 알았는데 아니었다..!

---

### Cors Cross Origin Resource Sharing

출처가 다른 자원들을 공유한다는 뜻으로, 한 출처에 있는 자원에서 다른 출처에 있는 자원에 접근하도록 하는 개념

좀 더 쉽게 설명하자면 백엔드와 프론트엔드가 나뉘어져 있을 때,   
localhost:8080 과 localhost:4200 이 두 url 들이 서로 소통할 수 있게 허용해주는 것이다.

이걸 왜 보안 공격이라고 생각했을까..? 그건 브라우저에서 기본적으로 둘 간의 소통을 할 수 없게 설정했기 때문이다.

해커가 우리의 서버로 어떤 요청을 해서 데이터를 읽을 수도 있고 변경할 수도 있는 상황에서 해당 접근을 막아서 사전 보호하는 것!

하지만 우리는 4200에서 8080으로 api 요청을 보내고 있기 때문에 4200에서 8080으로 접근할 수 있게 허가를 해줘야 한다.

이렇게 허가된 url에서만 우리의 서버로 접근할 수 있게 하는 방법이 CORS 였던 것!   
공격이 아닌, 공격으로부터 사전 보호하는 것이고 우리가 설정한 특정 url에서만 접근할 수 있도록 열어주는 것!!

---

### Csrf Cross Site Request Forgery

사이트 간 요청 위조 라는 말.

우선 어떤 상황인지 알아보자.

1. 내가 주말에 넷플릭스를 즐겁게 보고 있다가
2. 어떤 가상의 사이트(evil.com)으로 이동을 했다.
3. 어라라 들어갔더니 내가 갖고 싶어하던 기계식 키보드를 엄청나게 싼 가격에 판다고 하네??
4. 그 링크를 클릭한 순간!! 해커가 잠복 시켜놓은 아주 질 나쁜 코드가 실행되게 된다.
5. 그 코드 나도 모르는 사이에 특정 행동을 시키고 [등록/수정/삭제 등]
6. 나는 아무것도 모르고 그대로 공격에 당해버린다.... ㅠㅠ

여기서 만약 넷플릭스가 CSRF에 대응하지 않았다면 이 요청이 진짜로 내가 보낸건지(넷플릭스 사이트에서) 위조 사이트에서 보낸건지 파악을 못하고 그냥 실행시켜 버린다고 한다... 

이 공격을 방어하기 위한 수단으로 CsrfToken을 활용해보자!

---

### CsrfToken

```java
CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();

    http.csrf(csrf -> csrf
        .csrfTokenRequestHandler(requestHandler)
        .ignoringRequestMatchers("/contact","/register")
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
        .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
```

우선 CsrfTokenRequestAttributeHandler 를 만들고  
csrf설정에서 handler 등록, ignoring path 설정, repository 설정, filter 설정을 해준다.

하나하나 알아보자!

```java
/**
 * An implementation of the {@link CsrfTokenRequestHandler} interface that is capable of
 * making the {@link CsrfToken} available as a request attribute and resolving the token
 * value as either a header or parameter value of the request.
 *
 * @author Steve Riesenberg
 * @since 5.8
 */
public class CsrfTokenRequestAttributeHandler implements CsrfTokenRequestHandler
```
CsrfToken을 요청 속성으로 사용 가능하게 만들고 토큰 값을 요청의 헤더 또는 매개변수 값으로 확인할 수 있는 CsrfTokenRequestHandler 인터페이스의 구현입니다.
(땡큐 구글 번역!)

```java
/**
 * Specify a {@link CsrfTokenRequestHandler} to use for making the {@code CsrfToken}
 * available as a request attribute.
 * @param requestHandler the {@link CsrfTokenRequestHandler} to use
 * @return the {@link CsrfConfigurer} for further customizations
 * @since 5.8
 */
public CsrfConfigurer<H> csrfTokenRequestHandler(CsrfTokenRequestHandler requestHandler)
```
CsrfToken을 요청 속성으로 사용할 수 있도록 만드는 데 사용할 CsrfTokenRequestHandler를 지정합니다.

```java
public CsrfConfigurer<H> ignoringRequestMatchers(String... patterns)
```
requireCsrfProtectionMatcher(RequestMatcher)와 일치하더라도 CSRF 보호를 사용하지 않아야 하는 HttpServletRequest를 지정할 수 있습니다.   
말이 약간 어색하게 보이는데, 여기 작성한 패턴들에 한해서 csrf를 무시하겠다는 뜻.

```java
/**
 * Specify the {@link CsrfTokenRepository} to use. The default is an
 * {@link HttpSessionCsrfTokenRepository}.
 * @param csrfTokenRepository the {@link CsrfTokenRepository} to use
 * @return the {@link CsrfConfigurer} for further customizations
 */
public CsrfConfigurer<H> csrfTokenRepository(CsrfTokenRepository csrfTokenRepository)
```
사용할 CsrfTokenRepository를 지정합니다. 기본값은 HttpSessionCsrfTokenRepository입니다.

```java
/**
 * A {@link CsrfTokenRepository} that persists the CSRF token in a cookie named
 * "XSRF-TOKEN" and reads from the header "X-XSRF-TOKEN" following the conventions of
 * AngularJS. When using with AngularJS be sure to use {@link #withHttpOnlyFalse()}.
 *
 * @author Rob Winch
 * @author Steve Riesenberg
 * @author Alex Montoya
 * @since 4.1
 */
public final class CookieCsrfTokenRepository implements CsrfTokenRepository
```
"XSRF-TOKEN"이라는 쿠키에 CSRF 토큰을 유지하고 AngularJS의 규칙에 따라 "X-XSRF-TOKEN" 헤더에서 읽는 CsrfTokenRepository입니다. AngularJS와 함께 사용할 때는 반드시 withHttpOnlyFalse()를 사용해야 합니다.



와우... 구글 번역과 함께 어떤 코드들이 사용되었고 각 코드들은 어떤 이유에서 사용되었는지 알아봤다.

---

### CsrfTokenFilter

```java
public class CsrfCookieFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrfToken.getHeaderName() != null) {
            response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
        }
        filterChain.doFilter(request, response);
    }
}
```

그 다음으로는 filter에 대해 알아보자.

로그인을 하게되면 csrfToken이 쿠키에 담겨서 생성된다. 그리고 이 필터를 사용해서 쿠키에 담긴 값을
응답 헤더에 담아서 사용하게 된다! (위에 CookieCsrfTokenRepository 에서 쿠키에 CSRF 토큰을 유지하기 때문에 쿠키에 저장된 값을 받아서 사용하는 것)

이렇게 되면 csrfToken 값이 헤더에 담겨서 모든 요청마다(OncePerRequestFilter 를 사용했기 때문) 토큰값을 가져와서 다시 응답 헤더에 담아서 넘겨주게 된다.

---

음... 나름 열심히 정리한 것 같은데 역시 이번 강의는 처음 접해보는 내용이라 제대로 이해한 것 같진 않다.

다음 프로젝트에는 해당 내용들을 적용시켜서 좀 더 보안적으로 탄탄한 프로젝트를 만들어 봐야겠다.   
만들면서 해당 내용들에 대해 구글링 해보면서 공부해야지