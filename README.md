# Java JSP Servlet 게시판 프로젝트

JSP, Servlet, JDBC, MySQL을 활용하여 구현한 기본 게시판 프로젝트입니다.
회원가입, 로그인, 게시글 작성, 목록 조회, 상세보기, 수정 기능을 구현했습니다.

## 개발 목적

Java 웹 개발의 기본 구조를 이해하기 위해 JSP와 Servlet 기반으로 게시판을 직접 구현했습니다.
MVC 패턴을 적용하여 Controller, Service, DAO, DTO, View 역할을 분리하는 것을 목표로 했습니다.

## 개발 환경

* Java 11
* JSP / Servlet
* Apache Tomcat 9
* MySQL 8
* Eclipse
* JDBC

## 주요 기능

### 회원 기능

* 회원가입
* 로그인
* 로그아웃
* Session을 이용한 로그인 상태 유지

### 게시판 기능

* 게시글 작성
* 게시글 목록 조회
* 게시글 상세보기
* 게시글 수정
* 로그인 사용자만 게시글 작성 가능
* 작성자 본인만 게시글 수정 가능

## 프로젝트 구조

```text
src
├── Controller
├── Service
├── DAO
├── DTO
└── Util
```

### 구조 설명

* Controller: 요청 처리 및 화면 이동 담당
* Service: 비즈니스 로직 담당
* DAO: 데이터베이스 접근 담당
* DTO: 데이터 전달 객체
* JSP: 사용자 화면 담당

## 데이터베이스 구조

### members

| 컬럼명      | 설명    |
| -------- | ----- |
| id       | 회원 번호 |
| name     | 이름    |
| age      | 나이    |
| email    | 이메일   |
| password | 비밀번호  |

### posts

| 컬럼명        | 설명        |
| ---------- | --------- |
| id         | 게시글 번호    |
| member_id  | 작성자 회원 번호 |
| title      | 제목        |
| content    | 내용        |
| created_at | 작성일       |
| view_count | 조회수       |

## 실행 화면

### 메인 화면

추가 예정

### 게시글 목록

추가 예정

### 게시글 상세보기

추가 예정

## 배운 점

* Servlet에서 request와 response의 역할
* doGet과 doPost의 차이
* Session을 이용한 로그인 처리
* JSP와 Servlet 사이의 데이터 전달
* RequestDispatcher와 forward 흐름
* JDBC를 이용한 CRUD 처리
* MVC 패턴의 기본 구조

## 앞으로 추가할 기능

* 게시글 삭제
* 조회수 증가
* 게시글 검색
* 페이징 처리
* 댓글 기능
