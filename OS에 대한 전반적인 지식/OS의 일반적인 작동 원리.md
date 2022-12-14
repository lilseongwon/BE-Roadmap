# 운영체제란?

시스템 소프트웨어에 해당하는 운영체제(OS)는 컴퓨터 하드웨어를 관리하며 여러 애플리케이션(응용 소프트웨어)들이 작동할 수 있는 환경을 제공한다.

운영체제는 유저-하드웨어 간 전반적인 상호작용(인터페이스)를 하며 컴퓨터가 실행되는 동안 항상 수행되는 프로그램이다

### 운영체제 예시

**PC**

<img width="730" alt="image" src="https://user-images.githubusercontent.com/102791105/199851287-8073421d-3492-46c1-87bf-2750a91ad1a5.png">

**모바일 운영체제**

<img width="707" alt="image" src="https://user-images.githubusercontent.com/102791105/199851543-308b5cc9-1ae5-4037-9b6c-30484a8b01ae.png">


# 왜 쓰는데?

사용자가 컴퓨터를 편리하게 사용하게 하고 컴퓨터 하드웨어 각각의 자원들이 더욱 효율적으로 사용될 수 있기 때문이다

# 시스템 발전 과정

운영체제는 한 번에 한 개의 프로그램만 실행되는 단일 프로그래밍에서 현재의 빠른 운영체제로 발전했다

### 1. 일괄처리 시스템 **(Batch System)**

![os1](https://user-images.githubusercontent.com/102791105/199851586-eff608cf-fb9c-4c74-b87f-60ea5a3c9f35.png)


일괄처리시스템은 유사한 요구를 가지는 작업을 모아 하나의 그룹으로 수행하는 시스템이다. 초기 컴퓨터의 동작 방식으로, 작업 실행시 끝날때까지 다른 작업을 할 수 없다.

- CPU가 쉬는 상태가 많다.
- 상호작용이 필요없는 큰 단위의 작업들을 수행할 때 사용된다.

**장점**

- 많은 사용자가 시스템 자원을 공유할 수 있다
- 처리의 효율이 향상된다

**단점**

- 생산성 저하
- 응답시간이 길다(약 6시간)

### 2. 다중 프로그램 시스템**(Multi-Programmed System)**
![os2](https://user-images.githubusercontent.com/102791105/199851663-608c4c5e-f807-47f4-a9e8-b6f14feb9a45.png)


다중 프로그램 시스템은 CPU가 수행할 작업을 항상 가지도록 하는 시스템이다.

프로그램이 존재하면 CPU는 멈추지 않고 계속 동작한다

### 3. 시분할 시스템**(Time-Sharing System)**
![os3](https://user-images.githubusercontent.com/102791105/199851679-5cebb1ce-5983-49e7-8854-e2dd40f9415b.png)


시분할 시스템은 아주 짧은 주기로 CPU를 각각의 프로그램에 할당하여 각 사용자가 모든 프로그램이 **동시에 작동**하고 있다고 느끼게 하는 시스템이다.

**장점**

- 응답시간 단축 → 생산성 향상

**단점**

- 통신 비용 증가
- 동접자 증가시 시스템이 부하되어 개인 사용자의 체감 속도기 느려진다

### 4. 실시간 처리 시스템**(Real-Time System)**

실시간 처리 시스템은 작업처리에 대한 제한시간을 갖는 시스템이며 CPU의 동작이나 작업이 즉시적인 처리를 요할 때 사용되는 시스템이다. 실시간 처리 시스템은 **Hard Real-Time System(**시간 제약을 못지키면 시스템에 치명적인 발전소/무기 제어 프로그램 등), **Soft Real-Time System**(비교적 엄격하지 않은 동영상 재생 프로그램 등)으로 구분된다.

### 5. 분산 처리 시스템**(Distributed Processing System)**
![os4](https://user-images.githubusercontent.com/102791105/199851921-4f873003-01a1-442c-87b3-f7ed7fa3f762.png)


독립적인 처리 능력을 가진 컴퓨터 시스템을 네트워크를 이용해 연결해 작업을 처리하는 시스템이다. CPU들은 메모리를 공유하지 않는다.

분산처리 시스템의 예로는 P2P사이트(각 컴퓨터가 서버이자 클라이언트), 클라이언트&서버 시스템이 있다. 

**장점**

- 자원 공유를 통한 높은 성능을 가지며 신뢰성이 높다

**단점**

- 구축/관리가 어렵다

### 6. 병렬 처리 시스템**(Parallel Processing System)**

병렬 처리 시스템은 메모리 등의 자원을 공유하는 시스템이다. 사용 목적은 시스템의 성능 향상과 부품 중 하나가 고장이 나도 정상 동작이 가능하게 해서 신뢰성을 향상시키는 것

# 운영체제의 역할

화면 상의 요소를 통해 컴퓨터와 상호 작용하는 사용자 인터페이스(UI)를 제공함으로써 편리성을 향상시킨다(CUI, GUI, EUCI)

# 운영체제의 구조

![os_info](https://user-images.githubusercontent.com/102791105/199851892-7c346c51-a73b-4892-9eab-508447fd2fe0.png)


### 1. 커널**(kernel)**

커널은 알맹이를 뜻하며, 운영체제의 가장 빈번하게 사용되는 기능들을 담당하는 운영체제의 핵심 부분이다. 커널은 항상 메모리에 올라가있는 메모리 상주 프로그램이다. 

### 2. 유틸리티**(utility)**

운영체제의 커널을 제외한 나머지 부분. 항상 메모리에 올라가 있지 않은 비상주 프로그램이며 UI등 서비스 프로그램을 의미한다.

# 운영체제 작동방식

1. 컴퓨터의 전원을 누르면 컴퓨터의 전원을 공급하는 파워 서플라이는 메인보드에 부착된 장치들(CPU, 디스크, 메모리[RAM, ROM]등)에게 전력을 공급한다
2. CPU가 ROM에 저장된 펌웨어인 BIOS(Basic input/Output System, 주변장치와 OS사이의 데이터 흐름을 관리, OS가 하드웨어의 입출력을 컨트롤할 때 BIOS를 통해 컨트롤함)를 실행한다
3. 실행된 BIOS는 컴퓨터를 켤 때 문제가 있나 확인하는 프로그램인 POST를 실행하여 하드웨어를 체크한다
4. POST 과정이 끝난 BIOS는 부팅매체를 선택하고, 부팅매체의 MBR에 저장된 부팅정보를 읽어오는 **부트스트랩**을 실행한다
    
    ## **MBR, 부팅**, 부트로더, **부트스트랩**
    
    ![mbr](https://user-images.githubusercontent.com/102791105/199851802-34a44e51-a37b-4861-a03e-e50bdd7a65ce.png)

    
    > 모든 기억장치(USB, 디스크 등)은 MBR(Master Boot Record)란 영역을 가지고 있다. MBR이라는 기억 공간에는 디스크에 있는 운영체제 코드가 복사되어 있는 부트로더라는 프로그램이 저장되어 있다. 디스크는 MBR 정보를 읽고 BIOS는 부팅 과정을 도와주는 부트 스트랩을 이용해 부트로더를 RAM으로 옮긴다. 부트로더의 OS 코드는 RAM에서 실행되어 제어권이 OS에 넘어간다. 이것을 부팅(Booting)이라고 한다.
    > 
    
5. 부트스트랩 과정으로 RAM에 부트로더가 올라가고, 부트로더는 디스크에 있는 OS코드를 복사해 RAM에 붙여서 OS를 실행한다
6. 제어권이 OS에 넘어오며 성공적으로 OS가 부팅된다
7. OS는 대기하고 있던 첫번째 프로세스를 실행한다
8. 인터럽트가 발생하면서 CPU는 각종 작업을 처리한다

## 인터럽트(Interrupt)

![interrupt](https://user-images.githubusercontent.com/102791105/199851945-96a77699-7a2e-4a18-9d24-c15029b435d2.png)


인터럽트는 어떠한 방해물이나 개입적인 요소를 의미한다. 

CPU가 프로세스를 실행할 떄 예외상황이 일어나면 현재 하던 일을 중단하고 그 인터럽트에 대한 처리를 먼저 하게 된다. 이를 서비스 루틴이라고 하고, 서비스 루틴이 종료되면 다시 CPU는 인터럽트 되었던 연산을 재개한다.
