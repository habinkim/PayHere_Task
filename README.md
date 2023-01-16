# 페이히어 과제 전형

[TOC]

------



## 1. 주요 사용 기술, 의존성

- JDK : OpenJDK 17
- Spring Boot : 3.0.1
- DataBase : MySQL (local)
- 데이터 접근 기술 : JPA, Spring Data JPA, QueryDSL
- OAS Generator : springdoc-openapi-starter-webmvc-ui
- Entity, Dto Mapper : MapStruct



## 2. JDBC Connection 설정

- 로컬로 실행하시려면 `src/main/resources/application.yml` 파일의 일부 property 수정이 필요합니다
  - `spring.datasource.url`
  - `spring.datasource.username`
  - `spring.datasource.password`
  - `spring.data.redis.*`



## 3. OAS - Swagger UI URL

- http://localhost:8300/swagger-ui.html



## 4. 인증 API

### 4.1. POST `/signUp` 회원가입

#### 4.1.1. RequestBody

```json
{
  "email": "danny9643@naver.com",
  "password": "tyuiop90",
  "nickname": "habin"
}
```



#### 4.1.2. ResponseBody

```json
{
  "message": "SUCCESS",
  "body": null
}
```



### 4.2. POST `/login` 로그인

#### 4.2.1. RequestBody

```json
{
  "email": "danny9643@naver.com",
  "password": "tyuiop90"
}
```



#### 4.2.2. ResponseBody

```json
{
  "message": "SUCCESS",
  "body": {
    "accessToken": {
      "email": "danny9643@naver.com",
      "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiVVNFUiIsInN1YiI6ImRhbm55OTY0M0BuYXZlci5jb20iLCJpYXQiOjE2NzM4Nzc1NTMsImV4cCI6MTY3Mzg3OTM1M30.PBG8Vd9ogteMMRS2VNJD-CIHLSBYAgwMRwWq_umgg4QLGoB0vaHY2JazrB2P-9yUJruV2H8Vh2jj3I3puF-24w"
    },
    "refreshToken": {
      "email": "danny9643@naver.com",
      "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiVVNFUiIsInN1YiI6ImRhbm55OTY0M0BuYXZlci5jb20iLCJpYXQiOjE2NzM4Nzc1NTMsImV4cCI6MTY3Mzk2Mzk1M30.vPxYmBUUGeCQLXBkW9_MP2aF6dxODo5KzUjWgXZnblu4PLlcXft1RyLbgreO10mqE79ljaI2RVZF3brFiWV8ow"
    }
  }
}
```



### 4.3. GET `/logoutUser` 로그아웃 (인증 필요)

#### 4.3.1. RequestParam (없음)

#### 4.3.2. ResponseBody

```json
{
  "message": "SUCCESS",
  "body": null
}
```



### 4.4. POST `/refresh` Access Token 재발급

#### 4.4.1. RequestBody

```json
{
  "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiVVNFUiIsInN1YiI6ImRhbm55OTY0M0BuYXZlci5jb20iLCJpYXQiOjE2NzM4Nzc1NTMsImV4cCI6MTY3Mzk2Mzk1M30.vPxYmBUUGeCQLXBkW9_MP2aF6dxODo5KzUjWgXZnblu4PLlcXft1RyLbgreO10mqE79ljaI2RVZF3brFiWV8ow"
}
```



#### 4.4.2. ResponseBody

```json
{
  "message": "SUCCESS",
  "body": {
    "email": "danny9643@naver.com",
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiVVNFUiIsInN1YiI6ImRhbm55OTY0M0BuYXZlci5jb20iLCJpYXQiOjE2NzM4NzkxODIsImV4cCI6MTY3Mzg4MDk4Mn0.NmVqqXGa0ffXVk3YaQRMyRA8x9qdXImBDksUwuQvI4wt15wiZwIZMpe7Ryhswmn9NgPIIiDSkdpbwv9MqL3e9g"
  }
}
```



## 5. 가계부 관리 API (인증 필요)

### 5.1. PUT `/houseHold` 가계부 내역 생성

#### 5.1.1. RequestBody

```json
{
  "category": "소비",
  "payment": "신용카드",
  "amount": "30000",
  "memo": "지갑 구매"
}
```



#### 5.1.2. RepsonseBody

```json
{
  "message": "SUCCESS",
  "body": null
}
```



### 5.2. PATCH `/houseHold` 가계부 내역 수정

#### 5.2.1. RequestBody

```json
{
  "houseHoldId": 1, // 고정 파라미터
  "category": "수입", // 동적 파라미터
  "payment": "통장", // 동적 파라미터
  "amount": "4400000", // 동적 파라미터
  "memo": "급여" // 동적 파라미터
}
```



#### 5.2.2. ResponseBody

```json
{
  "message": "SUCCESS",
  "body": null
}
```



### 5.3. POST /houseHold/list 가계부 내역 리스트 조회

#### 5.3.1. RequestBody

```json
{
  "pageNo": 1, // 고정 파라미터 [1 이상]
  "pageSize": 10, // 고정 파라미터 [10 이상]
  "category": "string", // 동적 파라미터 (LIKE)
  "payment": "string", // 동적 파라미터 (LIKE)
  "minAmount": 0, // 동적 파라미터 (GOE)
  "maxAmount": 0, // 동적 파라미터 (LOE)
  "isDeleted": false // 고정 파라미터
}
```



#### 5.3.2. ResponseBody

```json
{
  "message": "SUCCESS",
  "body": {
    "content": [
      {
        "houseHoldId": 1,
        "category": "수입",
        "amount": 4400000
      },
      {
        "houseHoldId": 2,
        "category": "소비",
        "amount": 30000
      }
    ],
    "pageable": {
      "sort": {
        "empty": true,
        "unsorted": true,
        "sorted": false
      },
      "offset": 0,
      "pageNumber": 0,
      "pageSize": 10,
      "paged": true,
      "unpaged": false
    },
    "last": true,
    "totalElements": 2,
    "totalPages": 1,
    "first": true,
    "size": 10,
    "number": 0,
    "sort": {
      "empty": true,
      "unsorted": true,
      "sorted": false
    },
    "numberOfElements": 2,
    "empty": false
  }
}
```



### 5.4. GET /houseHold/{id} 가계부 내역 상세 조회

#### 5.4.1. RequestParam (id = houseHoldId)



#### 5.4.2. ResponseBody

```json
{
  "message": "SUCCESS",
  "body": {
    "houseHoldId": 1,
    "category": "수입",
    "payment": "통장",
    "amount": 4400000,
    "memo": "급여",
    "userEmail": "danny9643@naver.com",
    "nickname": "habin",
    "insDtm": "2023-01-16 22:59:49"
  }
}
```



### 5.5. DELETE /houseHold 가계부 내역 삭제

#### 5.5.1. RequestBody

```json
{
  "id": [1]
}
```



#### 5.5.2. ResponseBody

```json
{
  "message": "SUCCESS",
  "body": null
}
```



### 5.6. PATCH /houseHold/delete/cancel 가계부 내역 삭제 취소

#### 5.5.1. RequestBody

```json
{
  "id": [1]
}
```



#### 5.5.2. ResponseBody

```json
{
  "message": "SUCCESS",
  "body": null
}
```

