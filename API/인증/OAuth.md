# OAuth란

OAuth는 Open Authorization(**개방된 권한**)의 줄임말이다.

쉽게 말해서 로그인을 다른 웹사이트로 위임하는 사용자 인증 방식이다.

### 왜 쓰는데?

매번 ID/Password를 입력하는 번거로움과 보안상 취약점을 해결할 수 있다

# OAuth 2.0

## 용어 정리

- **`Authentication`** : 인증이라는 뜻으로, 접근 자격이 있는지 검증하는 단계
- **`Authorization`** : 인가라는 뜻으로, 자원에 접근할 권한을 부여하는 것이다. 인가가 완료되면 리소스 접근 권한이 담긴 Access Token이 Client에게 부여된다.
- **`Resource Server`** : OAuth2 서비스를 제공하고, 자원을 관리하는 서버
- **`Resource Owner`** : Resource Server의 계정을 소유하고 있는 사용자
- **`Client`** : Resoure Server에 접속해서 정보를 가져오고자 하는 애플리케이션(=내가 만든 서버)
- **`Client App`** : Resource Owner의 요청을 도와 서버로 요청을 보내는 앱 또는 웹 애플리케이션
- **`Authorization Server`** : 인증/인가를 수행하는 서버로, Client의 접근 자격을 확인하고 Access Token을 발급하여 권한을 부여하는 역할을 함
- **`JWT`** : 이 글에서는 Client에서 발급되어 Client의 서비스를 사용할 수 있도록 해주는 Access token과 Refresh Token을 합쳐서 JWT라고 칭하겠다
- **`Access Token`** : Authorization Server에서 발급된 토큰으로, Resource Server에서 Resource Owner의 보호된 자원을 획득할 때 사용되는 만료 기간이 있는 토큰이다. (자체 로그인 기능을 개발하면 Client가 Authorization Server이자 Resource Server가 된다)
- **`Refresh Token`** : Access Token 만료시 이를 갱신하기 위한 용도로 사용하는 토큰. 일반적으로 Access Token보다 만료 기간이 길다.

## OAuth의 흐름

**Authorization Code Grant 방식**

![image](https://user-images.githubusercontent.com/102791105/203034176-6b9f63c8-fa8e-450b-9490-813fc4fc1995.png)

1. Resource Owner가 Client 서비스 이용을 위해 Client로 로그인 요청을 보낸다
2. Client가 Authorization Server로 Redirect 시켜서 Resource Owner가 Authorization Server로 로그인을 할 수 있도록 한다
3. 로그인이 완료되면 Authorization Server에서 Client로 인증 허가되었다는 의미로 Authorization Code를 발급한다
4. Client는 Authorization Code를 이용해 Authorization Server로부터 Access Token과 Refresh Token을 받는다
5. Resource Owner의 정보가 필요할 경우 해당 토큰을 통해 Resource Server로부터 정보를 가져온다
6. Access Token이 만료된다면, Refresh Token을 통해 재발급 받는다

# OAuth API 사용

## 사전 작업

Google OAuth를 사용하기 위해서는 가장 먼저 프로젝트를 생성해야 한다

**참고 사이트**: [https://www.joinc.co.kr/w/man/12/oAuth2/Google](https://www.joinc.co.kr/w/man/12/oAuth2/Google) 

## 자세한 설명

1. 처음 요청을 보내는 URL은 [https://accounts.google.com/o/oauth2/v2/auth](https://accounts.google.com/o/oauth2/v2/auth) 이다.해당 URL 뒤에 여러 파라미터를 더해 요청을 보내야 한다.

### 파라미터

- **client_id** : 처음 등록할 때 받은 Client ID
- **redirect_uri** : 사전에 등록한 리디렉션 될 주소
- **response_type** : 요청에 대한 응답 타입(Authorization Code Grant 방식을 이용하기 때문에 'code'를 입력)
- **scope** : Resource Server(구글)로부터 받아올 정보의 범위(ex. 이메일, 공개된 모든 정보)
    
    ![https://velog.velcdn.com/images/softpeanut/post/4ce94398-635d-44d8-a411-8d1dde6658ca/image.png](https://velog.velcdn.com/images/softpeanut/post/4ce94398-635d-44d8-a411-8d1dde6658ca/image.png)
    

### 순서

1. Authorization Server(구글)에서 반환한 URI로 Client App(프론트엔드)에서 요청을 보낸다.
2. Resource Owner(사용자)가 구글 아이디와 비밀번호를 입력해 Authorization Server(구글)에 로그인 요청을 보낸다.
3. Authorization Server(구글)에 인증이 완료되면 Authorization Server(구글)에서 리디렉션 URI로 [https://localhost:3000/auth?code=4/P7q7W91a-oMsCeLvIaQm6bTrgtp7](https://localhost:3000/auth?code=4/P7q7W91a-oMsCeLvIaQm6bTrgtp7) 와 같이 승인 코드를 반환해준다. `4/P7q7W91a-oMsCeLvIaQm6bTrgtp7`이 바로 인코딩된 코드값이다.
4. Client(개인 서버)에서 인코딩된 코드값을 디코딩한 후 Resource Owner(사용자)의 정보를 받아오기 위한 토큰으로 바꾸어야 한다. [https://oauth2.googleapis.com/token](https://oauth2.googleapis.com/token) 로 여러 파라미터와 함께 요청을 보낸다.

### 파라미터

- **client_id** : 처음 등록할 때 받은 Client ID
- **client_secret** : 처음 등록할 때 받은 Client Secret
- **grant_type** : 요청에 대한 응답 타입(Authorization Code Grant 방식을 이용하기 때문에 **`authorization_code`**를 입력)
- **code** : 디코딩한 코드값
- **redirect_uri** : 사전에 등록한 리디렉션 될 주소
    
    ![https://velog.velcdn.com/images/softpeanut/post/d1658622-4e0d-45d8-b324-13e429074d3c/image.png](https://velog.velcdn.com/images/softpeanut/post/d1658622-4e0d-45d8-b324-13e429074d3c/image.png)
    
5. 이제 토큰을 이용해 Resource Owner의 정보를 가져와야 한다. 아까 받은      `1/fFAGRNJru1FTz70BzhT3Zg`와 같은 토큰을 매개변수로 넣어 [https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=1/fFAGRNJru1FTz70BzhT3Zg](https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=1/fFAGRNJru1FTz70BzhT3Zg) 처럼 요청을 보낸다.
6. 아까 설정한 scope에 따라 Resource Server(구글)로부터 정보를 받아온다. 해당 토큰을 다시 사용할 일이 있다면 별도의 저장소(redis 등)에 저장을 하면 되고, 1회성으로 Resource Owner(사용자)의 정보를 받아오고 끝낸다면 별도로 저장하지 않는다. Resource Owner(사용자)가 우리 서비스를 사용할 수 있도록 JWT를 발급하여 반환해야 한다.

이렇게 하면 Resource Owner가 직접 회원가입을 하지 않고도 로그인을 할 수 있고 Resource Server(구글)에서 받아온 정보로 자동으로 회원가입을 할 수도 있다.

### 느낀점

막연하게만 알던 OAuth의 과정에 대해 제대로 공부할 수 있었다. 

또한 읽으면서 들은 생각은 ‘Authorization Server에서 액세스 토큰만 받아와도 문제가 없다’는 것이다.

왜냐하면 인증 과정에서 사용자 정보를 가져온 뒤에는 우리 서비스를 사용하기 위한 JWT만을 사용하면 되기 때문이다.(액세스 토큰이 만료되면 리프레쉬로 재발급) 그렇기에 실제로 인증 서버에서 Access Token만 받아오는 경우가 많은 것 같다.

### 참고🙏

[https://velog.io/@entry_dsm/로그인-난-부럽지가-않어](https://velog.io/@entry_dsm/%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EB%82%9C-%EB%B6%80%EB%9F%BD%EC%A7%80%EA%B0%80-%EC%95%8A%EC%96%B4)
