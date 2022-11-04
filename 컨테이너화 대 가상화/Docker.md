# Docker란?
![image](https://user-images.githubusercontent.com/102791105/199945236-8dd281cc-d3e6-4c5b-b378-d16aadf649e9.png)

도커는 컨테이너 기술을 사용하여 애플리케이션에 필요한 환경을 신속하게 구축하고 

테스트 및 배포를 할 수 있게 해주는 플랫폼

# Container

Host OS 상에서 리소스를 논리적으로 구분하여 마치 별도의 서버인 것 처럼 사용할 수 있게 하는 기술. 논리적으로 격리된 이미지 실행 공간이며, 물리적으로 구분하지 않아서 쉽게 생성/삭제가 가능하다.

- 이식성과 확장성이 좋다
- 다양한 운영 환경을 지원할 수 있다

### VM과의 차이점
![image](https://user-images.githubusercontent.com/102791105/199945478-5b921504-5ba8-4f50-8483-003130611254.png)

**VM**

- 호스트 OS 위에 가상화 소프트웨어를 설치해서 가상환경 구축
- Guest OS가 존재한다

**Container**

- Guest OS가 없고 Host OS의 커널을 공유하여 오버헤드가 적고 가벼워 빠르다
    - OS 실행 없이 별도의 환경에서 애플리케이션 실행 가능
- 이식성과 확장성이 높다

참고로 Docker도 그 자체로는 VM이기 떄문에, 윈도우에서 도커는 Guest OS(Linux)를 사용하고, 도커와 컨테이너는 Host OS인 리눅스가 설치되어 있다(=커널 공유)

# Docker 이미지

컨테이너를 생성하는 Base가 되는 것(스마트폰 어플과 비슷)

단순히 개발한 애플리케이션만 이미지로 배포하는 게 아니라, DB, OS환경을 실행하기 위한 이미지를 Docker hub에서 Pull받아서 사용할 수 있다.

# 그래서 왜 쓰는데?

- 상황에 따라 필요한 컨테이너만 확장, 축소할 수 있게 되므로 공간 낭비를 기존의 Legacy, VM에 비해 획기적으로 줄일 수 있다
- 환경 구축할 필요 없이 이미지만 push/pull 하면 되므로 개발 배포 흐름이 매우 간편해진다

### **Legacy**

![image](https://user-images.githubusercontent.com/102791105/199945288-ed280c01-1d0b-4efd-98a4-cfa0cb56b47c.png)


Legacy(하나의 물리서버에 어플리케이션을 배포) 환경에서 개발부터 배포를 할 때,

개발자 개인 PC에서는 정상작동하는데 테스트환경에서는 오류가 발생할 수도 있다.

환경을 최대한 맞추려고 가상환경을 이용해서 환경을 구축해도 아래와 같은 문제가 생긴다

- 무겁다.
- 이식과 확장에 불리하다.
- 관리가 복잡하다. 등

### **Containerized Applications(Docker)**

![image](https://user-images.githubusercontent.com/102791105/199945327-2371ccce-05a6-4a62-ab90-6038d7f1576c.png)

Docker를 사용해서(애플리케이션 컨테이너화) 서비스 운영을 하면 개발과 배포의 흐름이 매우 간단해진다. 더불어 컨테이너에 대한 관리를 Dockerfile, docker-compose나 더 나아가 kubernetes를 사용하면 **인프라가 코드화**되어 편리하게 관리할 수 있다
