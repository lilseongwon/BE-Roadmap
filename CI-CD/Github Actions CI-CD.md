# Github Actions란?
![image](https://user-images.githubusercontent.com/102791105/199986467-a6380f66-ebec-4f23-b8fa-7f13cc6088c6.png)

Github Actions란 소프트웨어 개발 라이프사이클 안에서 PR, Push 등의 이벤트 발생에 따라 **자동화된 작업**을 진행할 수 있게 해주는 기능이다.

## 자동화된 작업이 필요한 경우

### 1. CI/CD

- 이벤트 발생에 따라 자동으로 빌드, 배포하는 스크립트를 실행

### 2. Testing

- PR을 보내면 자동으로 테스트를 진행하는 것으로 자동으로 테스트를 진행하는 것
- 테스트 성공 여부에 따라 자동으로 PR을 Open및 Close 할 수 있다

### 3. Cron Jop

- 특정 시간대에 스크립트를 반복 실행하도록 구현할 수 있다.
    
    ex)매일 특정 시간에 크롤링 작업을 진행한다
    

# Github Actions의 구성요소

### 1. Workflow

워크플로우는 레포지토리에 추가할 수 있는 일련의 자동화된 커맨드 집합이다.

하나 이상의 Job으로 구성되어 있으며, Push나 PR과 같은 이벤트에 의해 실행될 수도 있고 특정 시간대에 실행될 수도 있다

빌드, 테스트, 배포 등 각각의 역할에 맞는 Workflow를 추가할 수 있고, `.github/workflows`디렉토리에 YAML 형태로 저장한다

### 2. Event

Event는 Workflow를 실행시키는 Push, PR, Commit 등의 특정 행동을 의미한다

깃허브 외부에서 발생한 이벤트에 의해서도 Workflow를 실행시킬 수 있다

### 3. Job

Jop은 동일한 Runner에서 실행되는 여러 step의 집합을 의미한다

기본적으로 하나의 Workflow 내의 여러 Job은 독립적으로 실행되지만, 필요에 따라 의존 관계를 수정하여 순서를 지정할 수 있다

### 4. Step

Step이란 커맨드를 실행할 수 있는 각각의 Task를 의미하는데, Shell 커맨드가 될 수도 있고, 하나의 Action이 될 수도 있다. 

하나의 Job 내에서 각각의 Step은 다양한 Task로 인해 생성된 데이터를 공유할 수 있다

### 5. Action

Action은 Job을 만들기 위해 Step을 결합한 독립적인 커맨드로, 재사용이 가능한 Workflow의 가장 작은 단위의 블럭이다

### 6. Runner

Runner는 깃허브 액션 워크플로 내에 있는 Job을 실행시키기 위한 애플리케이션이다

---

### **Workflow 생성 및 Workflow 파일 알아보기**

Workflow는 `.github/workflows` 디렉토리 내에 `.yml` 파일을 생성해도 되지만, Repository의 Actions 탭에서 자동으로 템플릿을 만들어주는 기능을 이용하는 것이 좋다.

### CI.yml
<img width="702" alt="image" src="https://user-images.githubusercontent.com/102791105/202896032-229752e1-a71c-4f55-9be7-c855db265c56.png">

### CD.yml
<img width="705" alt="image" src="https://user-images.githubusercontent.com/102791105/202896082-8a32ae69-72d3-4d25-9f01-4054babdd564.png">
