## 회원 가입, 비밀번호 변경, 회원 삭제 구현
### 사용된 라이브러리 및 구현에 대하여
jpa, querydsl : 참고 링크 https://techfox.tistory.com/14  
회원 삭제에 대하여 Transaction 사용: https://techfox.tistory.com/20  
Unit test는 Spring test framework 사용  
DB는 Postgresql을 사용 했습니다.(생성 스크립트는 하단에 첨부하였습니다.)
### 환경 구성
- 본 프로젝트는 docker 기반으로 배포하게끔 설계되여 있습니다.
- JAVA jdk는 11버전을 사용하여 컴파일 하면 됩니다.

### 실행 가이드
아래 과정은 Java 11(JDK) , docker 및 docker compose가 설치되었다는 가정이 있습니다.
Buid
```sh
./gradlew build
docker build -t dispatch .
```
Run
```sh
docker-compose -f .\script\docker-compose.yml up -d
```

### Create scrip of User table 
create table "user"  
(  
id       bigint not null  
constraint user_pkey  
primary key,  
name     varchar(255),  
password varchar(255)  
);
