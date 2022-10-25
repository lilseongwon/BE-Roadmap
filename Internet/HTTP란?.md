# HTTP

HTTP는 웹 상에서 하이퍼텍스트를 기반으로 데이터를 주고받기 위해 사용되는 통신 프로토콜이다

유튜브 시청, 인터넷 쇼핑이 가능한 이유는 **하이퍼텍스트** 덕분이다

### 하이퍼텍스트

하이퍼링크라고도 한다. 다른 하이퍼텍스트 문서로 이동할 수 있는 노드를 가지고, 해당 노드는 다른 문서로의 링크를 가지고 있다

![image](https://user-images.githubusercontent.com/102791105/197902884-42f31271-70bf-4648-9252-3090cd8be8b3.png)

# HTTP 동작 환경 및 구조

**클라이언트-서버 환경**에서 동작한다.

또 OSI의 7계층, TCP/IP의 4계층인 애플리케이션 영역에서 사용되는 프로토콜이며,

신뢰성 있는 전송 프로토콜인 TCP를 사용하는 환경에서 사용된다(HTTP/3 부터 UDP를 사용하기도 한다)

![image](https://user-images.githubusercontent.com/102791105/197902972-209bfd8c-f4a3-40bf-9e0d-41e70f75b7c0.png)

이렇게 서버와 클라가 분리된 네트워크 환경에서 클라이언트는 서버로 요청, 서버는 클라이언트로 응답하며 통신한다

## **클라이언트**

HTML 문서를 가져오기 위한 요청을 전송하고 응답으로 수신한 HTML문서를 사용자에게 보여줄 수 있도록 렌더링하는 웹 브라우저(chrome 등)이다

## 서버

 클라의 요청을 해석하고 응답을 전송하는 Apache, Nginx 등의 소프트웨어가 설치된 컴퓨터이다

# HTTP의 특징

## 1. 사람이 읽고 이해할 수 있다

HTTP 요청/응답은 기본적으로 사람이 이해할 수 있는 **텍스트** 형식이다
![image](https://user-images.githubusercontent.com/102791105/197903049-1a66813e-87df-47f3-9c66-83aca828f40c.png)

요청/응답에는 해당 요청과 응답을 표현하고 설명하는 Header, 실제 데이터인 Body가 포함되어 있어, 테스트와 이해가 용이하다

## 2. 확장 가능하다

HTTP 헤더로 인해 클라와 서버간의 Header 정보에 대한 약속만 정의되면 얼마든지 새로운 기능이 추가, 확장이 가능하다

실제로 HTTP는 초기버전인 HTTP/0.9에서부터 시작하여 HTTP/1.0, HTTP/1.1, HTTP/2 등 탄생부터 지금까지 계속해서 그 기능이 확장되고 있다

## 3. Connectionless&Stateless

HTTP는 기본적으로 Connectionless 방식으로 동작한다 

서버와 연결 → 응답~요청의 과정이 끝나면 연결해제를 하게된다. 즉, 각각의 요청은 독립적이며, 서로 영향을 끼치지 않는다

이로 인해 Stateless(무상태) 프로토콜이라는 특징을 가진다. 각 요청이 서로 관련이 없고, 사용자의 상태에 대한 정보를 가지지 않는다

하지만 쇼핑몰의 장바구니와 같이 해당 페이지를 벗어나도 상태 유지가 필요한 경우, 

Session 또는 HTTP Cookie등으로 세션 상태를 유지한다

# HTTP 요청 메소드

HTTP에서 요청을 전송할 때에는 HTTP Method를 포함하여 전송한다

이 HTTP Method는 요청 전송 시, 어떠한 기능을 하는지에 대해 부가적인 설명을 하는 역할을 한다

![image](https://user-images.githubusercontent.com/102791105/197903102-d904abcf-1caa-4aa0-8e6f-d51512692bf3.png)
![image](https://user-images.githubusercontent.com/102791105/197903141-4b11d936-26b3-4dd9-84a7-f75fea501efc.png)

# HTTP 상태코드

HTTP를 사용할 때, 요청에 대한 응답의 결과는 HTTP 상태코드를 통해 전달된다

성공/실패 여부를 알려주며 세자리의 숫자, 다섯 가지 클래스로 분류된다

· **1xx (정보)** : 요청을 받았으며 작업을 계속한다.

· **2xx (성공)** : 요청을 받았으며 성공적으로 이해하고 처리했다.

· **3xx (리다이렉션)** : 요청 완료를 위해 추가적인  동작을 처리해야한다.

· **4xx (클라이언트 오류)** : 요청의 문법이 잘못되었거나 요청을 처리할 수 없다.

· **5xx (서버 오류)** : 서버가 유효한 요청에 대해 처리를 실패했다.

## Next Level :

[https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP](https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP)

### 출처

[https://blog.naver.com/PostView.naver?blogId=aservmz&logNo=222301982303&from=search&redirect=Log&widgetTypeCall=true&directAccess=false](https://blog.naver.com/PostView.naver?blogId=aservmz&logNo=222301982303&from=search&redirect=Log&widgetTypeCall=true&directAccess=false)
