# JWT(Json Web Token)란?
![image](https://user-images.githubusercontent.com/102791105/202878117-eb7815c8-dfce-4273-9494-ad14814c66d8.png)

JWT는 사용자 인증을 위해 사용하는 웹 표준(RFC 7519)이다.

Json포맷을 이용하여 Self-Contained(자가수용적인) 방식으로 사용자에 대한 정보를 저장하는 Claim(정보 조각들) 기반 Web 토큰이다

## 특징

- JWT는 token base stateless authentication(토큰 기반 상태 비저장 인증 = 클라 정보를 유지하지 않음) 메커니즘이다. 서버에서 해당 토큰정보를 저장할 필요가 없기 때문이다
- Refresh Token은 DB에 저장한다
- Session 토큰의 단점(분산화된 시스템에서의 세션 관리 문제 등)을 보완하기 위해 탄생했다

## JWT의 구조
![image](https://user-images.githubusercontent.com/102791105/202878129-d9f42a89-dbcd-4cda-9ead-0a525a9106a8.png)

JWT는 세 파트로 나누어지고, 각 파트는 `.` 으로 구분하여 `aaaaa.bbbbb.ccccc` 로 표현된다. 

또한 URL에서 파라미터로 사용할 수 있도록 하기 위해 url_safe한 base64인코딩(이진데이터를 ASCII코드로 바꿈. +, / 글자를 -, _으로 변경하여 전송 문제를 예방한다)을 사용한다.

### 1. Header
토큰의 타입(기본값으로는 JWT)과 JWT를 서명하는데 사용한 알고리즘을 명시한다. 대표적으로 사용되는 알고리즘에는 HMAC. SHA256, RSA, HS256 or RS256 등이 있다.
```json
{ 
  "typ": "JWT",    
  "alg": "HS256"
}
```
### 2. Payload

유저의 정보를 의미한다. 여기에 담은 정보의 한 조각을 **Claim**이라 부르고 name/value의 한 쌍으로 이뤄져 있다. 대표적인 클레임은 아래와 같다

- Issuer(iss) - 토큰 발급자
- Subject(sub) - 토큰 제목
- Audience(aud) - 토큰 대상자
- expiration(exp) - 토큰의 만료시간
- Issued at(iat) - 토큰이 발급된 시간
```json
{
"sub": "user10001",
"iat": 1569302116
}
```
### Signature

서명은 JWT의 가장 핵심적인 파트다. 서버에서 토큰의 정보가 서버로부터 생성된 것인지 증명하기 위해 사용한다. 

**서명 과정** 

base64 encoded 헤더와 payload를 .(period) seperator(.을 기준으로 분리)로 concatenate(연결) 후 헤더에 명시된 알고리즘으로 private key를 사용해 서명한다.

- IdP(Identity Provider = 서명해주는 서버)에서만 private key를 가지고 있기 때문에, 토큰 정보의 조작을 방지할 수 있다

### HS256 알고리즘

HS256 알고리즘은 하나의 secret key를 사용해 데이터를 서명하고, 검증한다. 

JWT의 서명 부분과 유저가 보낸 JWT의 header.payload 부분을 똑같은 secret key로 서명 후 같은 지 비교하는 방식으로 토큰을 비교한다.

**단점**

- 분산화된 시스템(여러 서버)에서 서명/검증 주체 모두 secret key를 공유해야 한다

### RS256 알고리즘

RS256 알고리즘은 public key(서명용)와 private key pair(검증용)를 사용한다

private key는 authentication 서버에서만 가지고 있고, 여기서만 토큰을 생성하면 돼서 private key를 공유할 필요가 없다. 그리고 검증을 위해서는 공개해도 되는 public key만 사용하면 되기 떄문에 비교적 안전하다. JWT 발급 서버는 public key를 조회하는 API를 만들어서 공유한다.

# JWT의 동작방식

JWT는 기본적으로 IdP에서 특정 유저의 identity를 증명하기 위해 생성된다.

인증 절차는 아래와 같다

![image](https://user-images.githubusercontent.com/102791105/202879346-636dcb63-38e7-490b-9c3b-fe3340591f32.png)

1. 유저가 자신의 ID/PW를 입력해 로그인한다
2. authentication 서버는 credential(유저 정보)을 DB와 비교한다

3, 4. 정보가 맞다면 서명된 JWT(액세스&리프레시 토큰)를 발급한다.(secret 이나 private key를 사용해 서명)를 발급한다

5. 유저 클라이언트는 서버에서 자신의 자원에 접근하기 위해 JWT를 HTTP Authorization Header에 담아 보낸다
6. 서버는 토큰의 authenticity를 public key나 secret을 활용해 토큰의 authenticity를 검증한다
7. 이에 맞는 데이터를 응답한다
8. Access Token의 유효기간이 만료되었을 경우
9. 다시 한 번 사용자가 Access Token을 헤더에 실어 요청을 보낸다

10, 11. 서버가 Access Token이 만료됨을 확인하고 권한없음을 신호로 보낸다

12. 사용자는 Refresh Token과 함께 Access Token을 서버로 보내며 Access Token 발급 요청을 한다.
13. 서버에서는 우선 Access Token이 조작되었는지 확인한 후, **사용자가 보낸 Refresh Token과 DB에 저장되어있는 Refresh Token을 비교한다. Token이 동일**하고 **유효기간도 지나지 않았다면** Access Token을 새로 발급한다.
14. 사용자는 새로운 Access Token을 헤더에 실어 다시 API 요청을 진행한다.

---

## Authorization 헤더 중 대표적인 종류 2가지
### Basic
사용자 ID와 PW를 Base64로 인코딩한 값을 토큰으로 사용한다. (RFC 7617)

Basic 토큰 값이 노출이 되면 ID, PW가 노출되는 것이기 때문에 보안에 취약하다.
### Bearer
일반적으로 JWT(RFC 7519) 같은 OAuth 토큰을 사용한다. (RFC 6750)

- Basic 방식과는 달리 토큰에 ID, PW 값을 넣지 않는다.
- 로그인 시 토큰을 부여받고, 이후 요청할 때 요청 헤더에 토큰을 실어서 보낸다.
- 세션 저장소가 필요가 없고, 토큰 자체에 내장이 되어있다.
- STATELESS, 무결성, 보안성이 장점

## Access Token

- 권한/ 인증에 대한 Token을 말한다.

## Refresh Token

액세스 토큰이 제 3자에게 탈취당할 경우 유효기간이 만료될 때까지 계속 정보가 털린다는 단점을 보완하기 위해 만들었다. 

- Access Token의 유효기간이 지났을 때 새롭게 발급해준다.
- 액세스 토큰의 유효기간 짧고, 리프레시 토큰의 유효기간은 길다.

## JWT의 장점

- 확장성 - 토큰 값만 알고 있으면 어떤 서버로 요청을 보내도 상관없다
- 쿠키 사용으로 인한 보안문제 개선
- 불필요한 인증 과정의 감소 - JWT 자체에 정보가 포함돼있다

## JWT의 단점

- 토큰이 노출되면 보안적으로 취약하다 - Refresh Token으로 보완
- claim이 많아질수록 토큰이 길어지고 네트워크 대역폭 낭비가 심해질 수 있다
- Payload에 대한 정보는 암호화되지 않고 Base64인코딩만 하기 때문에 중간에 토큰을 취득하면 데이터를 확인할 수 있다. 이를 해결하기 위해 JWE(Json Web Encryption)을 통해 암호화하거나 중요데이터를 payload에 넣지 않도록 해야 한다

### 참고

[https://etloveguitar.tistory.com/101](https://etloveguitar.tistory.com/101)

[https://steadily-worked.tistory.com/469](https://steadily-worked.tistory.com/469)
