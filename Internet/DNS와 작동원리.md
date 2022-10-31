# 도메인이란?

웹 사이트에 접속하기 위해 외우기 어려운 IP 주소 대신 사용하는 문자열 인터넷 주소이다

# DNS와 네임 서버

DNS는 웹사이트의 IP 주소와 도메인 주소를 이어주는 시스템이고, 해당 도메인 서버의 IP 주소를 찾아주는 서버가 네임 서버(DNS 서버)이다

DNS 서버는 IP 주소를 특정 도메인 주소와 같다는 기록을 저장하고, 인터넷 사용자들이 도메인 주소를 검색했을때 IP 주소로 연결하도록 해준다
![image](https://user-images.githubusercontent.com/102791105/198924835-4e1fef03-23b3-4b1d-845f-5c3f8ef489ef.png)

예를 들어 클라이언트가 브라우저에서 [Nesite.com](http://Nesite.com)을 검색했다면, DNS 서버로 전달이 되고, 서버 내부에서 도메인 주소로 **Nesite.com = 12.123.123.123** 라는 항목을 찾아낸다. 

브라우저에게 해당 IP 주소를 가진 호스팅 서버(해당 웹사이트 데이터가 저장된 곳)으로 가라고 하면 브라우저가 다시 IP주소로 접속해서 웹사이트가 보인다

## **DNS 구성 요소**

**도메인 네임 스페이스(Domain Name Space)**

도메인 이름 저장 분산 규칙

**네임 서버(Name Server) = 권한 있는 DNS 서버** 

해당 도메인 이름의 IP 주소를 찾음

**리졸버(Resolver) = 권한 없는 DNS 서버** 

클라이언트의 요청을 네임 서버에 전달하고 찾은 정보를 클라이언트에게 응답, 찾은 정보 캐싱

# DNS 서버 종류

도메인 수는 매우 많다. 그래서 DNS 서버는 하나가 아니라 서버 종류를 계층화해서 단계적으로 처리한다
![image](https://user-images.githubusercontent.com/102791105/198924870-1a1bc844-c6b7-47bd-8955-3bb37022250d.png)

- **Root DNS Server** : ICANN(국제 인터넷 관리기구)이 직접 관리하는 절대 존엄 서버, TLD DNS 서버 IP들을 저장해두고 안내한다
- **TLD(최상위 도메인) DNS Server** : 도메인 등록 기관(Registry)이 관리하는 서버로, Authorized DNS 서버 주소를 저장해 두고 안내한다. 어떤 도메인 묶음이 어떤 Authorized DNS Server에 속하는지 아는 이유는 도메인 판매 업체(Register)의 DNS 설정이 변경되면 도메인 등록 기관으로 전달되기 때문
- **Authorizative DNS Server** : 실제 개인 도메인과 IP 주소의 관계가 기록/저장/변경되는 서버. 일반적으로 도메인/호스팅 업체의 ‘네임서버’를 말하지만, 개인 DNS 서버 구축을 한 경우에도 여기에 해당한다
- **Recursive DNS Server** : 인터넷 사용자가 가장 먼저 접근하는 DNS 서버. 위 3개의 서버를 생략하기 위해 한 번 거친 후 얻은 데이터를 일정 기간(TTL)동안 캐시로 저장해 두는 서버이다. KT/LG/SKT 등의 ISP DNS Server가 있고, 브라우저 우회 용도로 많이 쓰는 구글 DNS, 클라우드플레어와 같은 Public DNS 서버가 있다

**브라우저는 캐시가 저장된 Recursion 서버를 사용하고, 실제 네임서버를 사용하는 곳은 Authorizative 서버이다**

# DNS 동작 원리
![image](https://user-images.githubusercontent.com/102791105/198924887-033c779e-657a-45ae-ad96-64e8a843351e.png)

1. 브라우저에서 [Nesite.com](http://Nesite.com)을 검색하고, 사용하고 있는 통신사인 KT DNS 서버에게 도메인 주소에 해당하는 IP 주소를 요청함
2. ISP 서버에선 캐시 데이터가 없다는 걸 확인하고 루트 DNS 서버에게 어디로 가야 하는지 요청함(캐시가 있다면 8. 로 건너뜀)
3. 루트 서버는 TLD DNS 서버 주소만 관리하기 떄문에, ***.com 도메인을 보고 COM 최상위 도메인을 관리한는 TLD DNS 서버 주소를 안내함
4. ISP 서버는 COM 서버에게 어디로 가야 하는지 요청함
5. COM 서버는 가비아 DNS 서버에서 해당 도메인이 관리되고 있는 걸 확인하고 안내함
6. ISP 서버는 가비아 서버에게 또 요청함
7. 가비아 서버는 “Nesite.com = 12.123.123.123”이라는 정보를 확인하고 이 IP를 알려줌. 동시에 ISP 서버는 해당 정보를 캐시로 기록해 둠
8. ISP 서버는 브라우저에게 힘들게 알아 낸 12.123.123.123 주소를 안내함
9. 브라우저는 12.123.123.123 IP 주소를 갖고 이는 호스팅 서버에게 웹사이트를 출력하라고 요청함
10. 웹사이트가 보임!

**출처**: [https://hanamon.kr/dns란-도메인-네임-시스템-개념부터-작동-방식까지/](https://hanamon.kr/dns%EB%9E%80-%EB%8F%84%EB%A9%94%EC%9D%B8-%EB%84%A4%EC%9E%84-%EC%8B%9C%EC%8A%A4%ED%85%9C-%EA%B0%9C%EB%85%90%EB%B6%80%ED%84%B0-%EC%9E%91%EB%8F%99-%EB%B0%A9%EC%8B%9D%EA%B9%8C%EC%A7%80/),

[https://gentlysallim.com/dns란-뭐고-네임서버란-뭔지-개념정리/](https://gentlysallim.com/dns%EB%9E%80-%EB%AD%90%EA%B3%A0-%EB%84%A4%EC%9E%84%EC%84%9C%EB%B2%84%EB%9E%80-%EB%AD%94%EC%A7%80-%EA%B0%9C%EB%85%90%EC%A0%95%EB%A6%AC/)
