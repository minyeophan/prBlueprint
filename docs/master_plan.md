# prBlueprint 마스터 플랜
## 자바/스프링 초보자를 위한 완전정복 12단계 로드맵

> **목표**: 아무것도 모르는 상태에서 시작해서, 실제 서비스 가능한 프로젝트를 직접 만들며 배우기
> **철학**: "왜?"를 이해하고, 에러를 두려워하지 않고, 직접 타이핑하며 체득하기

---

## 📌 시작하기 전에 알아야 할 것들

### 버전 선택 가이드 (왜 이 버전인가?)

| 기술 | 버전 | 선택 이유 |
|------|------|----------|
| **JDK** | 17 (LTS) | 2024-2029년까지 공식 지원. Spring Boot 3.x가 요구하는 최소 버전 |
| **Spring Boot** | 3.2.x | 가장 안정적인 최신 버전. Java 17+ 필수 |
| **Gradle** | 8.5+ | Spring Boot 3.x와 호환. 빌드 속도 빠름 |
| **PostgreSQL** | 15+ | 안정성과 기능의 균형 |
| **Redis** | 7.x | 최신 안정 버전 |
| **Node.js** | 18+ LTS | React/Vite 실행용 |

### LTS란?
**Long Term Support** - 장기간(보통 5년) 버그 수정과 보안 패치를 받는 버전
→ 실무에서는 항상 LTS 버전을 선택!

---

## 🛠️ 환경 설정 체크리스트

### 필수 설치 항목
- [ ] **JDK 17** - [Adoptium](https://adoptium.net/) 권장
- [ ] **IntelliJ IDEA** - Community(무료) 또는 Ultimate
- [ ] **Docker Desktop** - 데이터베이스 컨테이너용
- [ ] **Git** - 버전 관리
- [ ] **VS Code** - 프론트엔드용 (선택)
- [ ] **Node.js 18+** - 프론트엔드용

### 설치 확인 명령어
```bash
java -version          # openjdk version "17.x.x"가 나와야 함
docker --version       # Docker version 24.x.x
git --version          # git version 2.x.x
node -v                # v18.x.x 이상
```

---

## 📚 12단계 로드맵

---

### 🚀 Phase 1: 프로젝트 초기화와 첫 실행
**목표**: "Hello World"를 보기까지

#### 학습 내용
1. **Spring Initializr로 프로젝트 생성**
   - 왜 Spring Initializr를 사용하는가?
   - Gradle vs Maven 차이점
   - 의존성 선택의 의미

2. **프로젝트 구조 이해**
   ```
   src/
   ├── main/
   │   ├── java/        ← 자바 코드
   │   └── resources/   ← 설정 파일
   └── test/            ← 테스트 코드
   ```

3. **첫 번째 실행**
   - `./gradlew bootRun` 이해하기
   - 8080 포트의 의미
   - 에러 메시지 읽는 법

#### 체크포인트 테스트 ✅
- [ ] `localhost:8080`에 접속하면 Whitelabel Error Page가 보인다
- [ ] 콘솔에 "Started Application in X seconds"가 출력된다
- [ ] `./gradlew build`가 성공한다

#### 예상 에러와 해결
| 에러 | 원인 | 해결 |
|------|------|------|
| `JAVA_HOME not set` | JDK 경로 미설정 | 환경변수 설정 |
| `Port 8080 already in use` | 포트 충돌 | 다른 프로그램 종료 |

---

### 🗂️ Phase 2: 첫 Entity와 H2 데이터베이스
**목표**: 데이터를 저장하고 조회하는 원리 이해

#### 학습 내용
1. **Entity란 무엇인가?**
   - 데이터베이스 테이블 = Java 클래스
   - `@Entity`, `@Id`, `@Column` 의미

2. **H2 인메모리 DB**
   - 왜 처음에는 H2를 쓰는가?
   - 앱 종료 시 데이터가 사라지는 이유
   - H2 Console로 데이터 직접 보기

3. **Repository 패턴**
   - `JpaRepository`가 마법처럼 동작하는 이유
   - 메서드 이름만으로 쿼리가 생성되는 원리

#### 실습: User Entity 만들기
```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
}
```

#### 체크포인트 테스트 ✅
- [ ] H2 Console(`/h2-console`)에 접속할 수 있다
- [ ] USER 테이블이 자동 생성되어 있다
- [ ] SQL로 직접 INSERT/SELECT가 가능하다

---

### 🌐 Phase 3: 첫 REST API 만들기
**목표**: 브라우저/Postman으로 데이터 CRUD

#### 학습 내용
1. **REST란?**
   - HTTP Method의 의미 (GET, POST, PUT, DELETE)
   - URL 설계 원칙
   - JSON 포맷

2. **Controller 계층**
   - `@RestController` vs `@Controller`
   - `@GetMapping`, `@PostMapping` 등
   - `@RequestBody`, `@PathVariable`

3. **계층 분리의 이유**
   - Controller → Service → Repository
   - 왜 나누는가? (테스트, 유지보수, 책임 분리)

#### 체크포인트 테스트 ✅
- [ ] `GET /api/users` → 빈 배열 `[]` 반환
- [ ] `POST /api/users` → 새 사용자 생성 성공
- [ ] `GET /api/users/1` → 생성한 사용자 조회
- [ ] `DELETE /api/users/1` → 삭제 후 조회 시 404

---

### 🐳 Phase 4: Docker로 PostgreSQL 사용하기
**목표**: 실제 데이터베이스 환경 구축

#### 학습 내용
1. **왜 Docker인가?**
   - 설치 없이 DB 사용
   - 환경 일관성 (내 PC = 동료 PC = 서버)
   - 버전 관리 용이

2. **docker-compose.yml 이해**
   ```yaml
   services:
     postgres:
       image: postgres:15
       environment:
         POSTGRES_DB: blueprint
         POSTGRES_USER: blueprint
         POSTGRES_PASSWORD: blueprint123
   ```

3. **H2 → PostgreSQL 전환**
   - application.yml 설정 변경
   - 프로필(Profile) 분리: dev, prod

#### 체크포인트 테스트 ✅
- [ ] `docker-compose up -d` 성공
- [ ] `docker ps`로 postgres 컨테이너 확인
- [ ] Spring Boot가 PostgreSQL에 연결 성공
- [ ] 앱 재시작해도 데이터가 유지된다

#### Git 브랜치 전략 🌿
```
main (안정 버전)
  └── develop (개발 중)
        └── feature/docker-setup (이 단계 작업)
```
- 이 단계 완료 후: `feature/docker-setup` → `develop` 머지

---

### 🔐 Phase 5: Spring Security와 JWT 인증
**목표**: 로그인/회원가입 구현

#### 학습 내용
1. **인증(Authentication) vs 인가(Authorization)**
   - 인증: "너 누구야?"
   - 인가: "너 이거 해도 돼?"

2. **JWT(JSON Web Token)**
   - 왜 JWT를 사용하는가?
   - Access Token vs Refresh Token
   - 토큰 저장 위치 (Header, Cookie)

3. **Spring Security 설정**
   - SecurityFilterChain 구성
   - 허용/차단 URL 설정
   - 비밀번호 암호화 (BCrypt)

#### 체크포인트 테스트 ✅
- [ ] `POST /api/auth/signup` → 회원가입 성공
- [ ] `POST /api/auth/login` → JWT 토큰 반환
- [ ] 토큰 없이 `/api/users` 접근 → 401 Unauthorized
- [ ] 토큰과 함께 접근 → 200 OK

#### 에러 노트 📝
| 상황 | 증상 | 원인 | 해결 |
|------|------|------|------|
| 로그인 실패 | 403 Forbidden | CSRF 활성화 | `csrf.disable()` 추가 |
| 토큰 무효 | 401 | 토큰 만료 | 만료 시간 확인 |

---

### ⚡ Phase 6: Redis 캐싱
**목표**: 성능 최적화의 기초

#### 학습 내용
1. **캐싱이란?**
   - DB 부하 줄이기
   - 응답 속도 개선
   - 언제 캐싱해야 하는가?

2. **Redis 기초**
   - Key-Value 저장소
   - TTL(Time To Live)
   - Docker로 Redis 실행

3. **Spring Cache 추상화**
   - `@Cacheable`, `@CacheEvict`
   - 캐시 키 전략

#### 체크포인트 테스트 ✅
- [ ] Redis 컨테이너 실행 중
- [ ] 첫 번째 조회: "Cache MISS" 로그
- [ ] 두 번째 조회: "Cache HIT" (로그 없음)
- [ ] Redis 장애 시에도 앱 정상 동작

---

### ⚛️ Phase 7: React 프론트엔드 기초
**목표**: 프론트엔드 프로젝트 생성

#### 학습 내용
1. **React + TypeScript + Vite**
   - 왜 이 조합인가?
   - CRA vs Vite

2. **프로젝트 구조**
   ```
   frontend/
   ├── src/
   │   ├── components/
   │   ├── pages/
   │   ├── hooks/
   │   └── api/
   ```

3. **첫 컴포넌트**
   - JSX 문법
   - props와 state
   - useState, useEffect

#### 체크포인트 테스트 ✅
- [ ] `npm run dev` → localhost:5173 접속
- [ ] 화면에 "Hello Blueprint" 표시
- [ ] React DevTools에서 컴포넌트 확인

---

### 🔗 Phase 8: 프론트-백엔드 연동
**목표**: 실제 데이터 흐름 완성

#### 학습 내용
1. **CORS 이해와 설정**
   - 왜 CORS 에러가 발생하는가?
   - 해결 방법들

2. **Axios로 API 호출**
   - fetch vs axios
   - 에러 처리
   - 로딩 상태 관리

3. **로그인 연동**
   - 토큰 저장 (localStorage vs 메모리)
   - 인증 상태 관리
   - Private Route 구현

#### 체크포인트 테스트 ✅
- [ ] 회원가입 폼 → 백엔드 연동 성공
- [ ] 로그인 → 토큰 저장 → 메인 페이지 이동
- [ ] 로그인 없이 보호된 페이지 접근 → 로그인 페이지로 리다이렉트

---

### 📊 Phase 9: 핵심 도메인 확장 (Project, Task)
**목표**: 실제 비즈니스 로직 구현

#### 학습 내용
1. **도메인 모델링**
   - Entity 관계 설계 (1:N, N:M)
   - JPA 연관관계 매핑

2. **복잡한 비즈니스 로직**
   - 프로젝트 생성/초대/참여
   - 권한 체크
   - 트랜잭션 관리

3. **N+1 문제와 해결**
   - 왜 발생하는가?
   - Fetch Join, @EntityGraph

#### 체크포인트 테스트 ✅
- [ ] 프로젝트 CRUD 동작
- [ ] 멤버 초대/수락 플로우
- [ ] Task 생성 및 상태 변경

---

### 📡 Phase 10: WebSocket 실시간 통신
**목표**: 실시간 협업 기능

#### 학습 내용
1. **WebSocket vs HTTP**
   - 양방향 통신
   - 연결 유지

2. **STOMP 프로토콜**
   - Subscribe/Publish 패턴
   - 메시지 브로커

3. **실시간 업데이트**
   - Task 변경 시 전체 공유
   - 온라인 사용자 표시

#### 체크포인트 테스트 ✅
- [ ] WebSocket 연결 성공
- [ ] A가 Task 수정 → B 화면에 즉시 반영
- [ ] 연결 끊김 후 자동 재연결

---

### 🎯 Phase 11: 고급 기능
**목표**: 실무 수준 기능 추가

#### 학습 내용
1. **파일 업로드**
   - 로컬 저장 vs 클라우드(S3)
   - 이미지 리사이징

2. **알림 시스템**
   - 인앱 알림
   - 이메일 알림 (선택)

3. **검색과 페이징**
   - Specification을 이용한 동적 쿼리
   - Pageable

#### 체크포인트 테스트 ✅
- [ ] 파일 업로드/다운로드
- [ ] 알림 생성 및 읽음 처리
- [ ] 검색 + 페이징 동작

---

### 🚀 Phase 12: CI/CD와 배포
**목표**: 자동화된 배포 파이프라인

#### 학습 내용
1. **테스트 작성**
   - 단위 테스트 (JUnit, Mockito)
   - 통합 테스트

2. **GitHub Actions**
   - 자동 빌드
   - 자동 테스트
   - Docker 이미지 빌드

3. **배포**
   - Docker Compose로 전체 스택 배포
   - 환경별 설정 분리

#### 체크포인트 테스트 ✅
- [ ] PR 생성 시 자동 테스트 실행
- [ ] main 머지 시 Docker 이미지 빌드
- [ ] 전체 스택 docker-compose up 성공

---

## 🌿 Git 브랜치 전략

### 브랜치 구조
```
main ─────────────────────────────────────────────→ (배포 가능한 안정 버전)
  │
  └── develop ────────────────────────────────────→ (개발 통합)
        │
        ├── feature/phase1-init ────→ (완료 후 develop 머지)
        ├── feature/phase2-entity ──→ (완료 후 develop 머지)
        ├── feature/phase3-api ─────→ (완료 후 develop 머지)
        └── ...
```

### 각 Phase 완료 시 워크플로우
```bash
# 1. feature 브랜치에서 작업 완료
git add .
git commit -m "Phase 3: REST API 구현 완료"

# 2. develop으로 머지
git checkout develop
git merge feature/phase3-api

# 3. 다음 feature 브랜치 생성
git checkout -b feature/phase4-docker
```

### 커밋 메시지 컨벤션
```
feat: 새 기능 추가
fix: 버그 수정
docs: 문서 변경
refactor: 리팩토링
test: 테스트 추가
chore: 기타 변경
```

예시:
```
feat: User 엔티티 및 Repository 구현
fix: JWT 토큰 만료 시간 오류 수정
docs: README에 실행 방법 추가
```

---

## 📝 에러 노트 템플릿

각 Phase에서 만난 에러를 기록합니다.

### 에러 노트 작성법
```markdown
## Phase X 에러 노트

### [에러 1] 포트 충돌
- **날짜**: 2024-XX-XX
- **상황**: `./gradlew bootRun` 실행 시
- **에러 메시지**: `Port 8080 already in use`
- **원인**: 이전 실행이 종료되지 않음
- **해결**:
  ```bash
  # Windows
  netstat -ano | findstr :8080
  taskkill /PID [PID번호] /F
  ```
- **배운 점**: 서버 종료 시 Ctrl+C로 확실히 종료할 것
```

### 자주 만나는 에러 모음
| Phase | 에러 | 원인 | 해결 |
|-------|------|------|------|
| 1 | JAVA_HOME not set | JDK 미설치/환경변수 | JDK 설치 후 환경변수 설정 |
| 2 | Table not found | Entity 스캔 실패 | @Entity 확인, 패키지 구조 확인 |
| 3 | 404 Not Found | URL 매핑 오류 | @RequestMapping 확인 |
| 4 | Connection refused | DB 미실행 | docker-compose up |
| 5 | 403 Forbidden | CSRF/권한 문제 | Security 설정 확인 |
| 6 | Redis connection failed | Redis 미실행 | docker-compose up |

---

## 📋 전체 진행 체크리스트

### 환경 설정
- [ ] JDK 17 설치 및 확인
- [ ] Docker Desktop 설치
- [ ] IntelliJ IDEA 설치
- [ ] Git 설치 및 설정

### Phase 진행
- [ ] **Phase 1**: 프로젝트 초기화
- [ ] **Phase 2**: Entity와 H2
- [ ] **Phase 3**: REST API
- [ ] **Phase 4**: Docker + PostgreSQL
- [ ] **Phase 5**: Security + JWT
- [ ] **Phase 6**: Redis 캐싱
- [ ] **Phase 7**: React 프론트엔드
- [ ] **Phase 8**: 프론트-백 연동
- [ ] **Phase 9**: 도메인 확장
- [ ] **Phase 10**: WebSocket
- [ ] **Phase 11**: 고급 기능
- [ ] **Phase 12**: CI/CD + 배포

---

## 💡 학습 팁

### 1. 에러를 두려워하지 말 것
> 에러 메시지는 적이 아니라 **힌트**입니다.
> 빨간 글씨가 나와도 당황하지 말고 **천천히 읽어보세요**.

### 2. 복사-붙여넣기 금지
> 코드를 직접 타이핑하면 손가락이 기억합니다.
> 처음엔 느려도, 나중엔 훨씬 빨라집니다.

### 3. "왜?"를 계속 물을 것
> 동작하는 코드보다 **이해한 코드**가 중요합니다.
> "이게 왜 필요하지?"를 항상 생각하세요.

### 4. 작은 단위로 테스트
> 한 번에 많이 만들고 테스트하면 어디가 문제인지 모릅니다.
> 조금 만들고 → 테스트 → 조금 만들고 → 테스트

### 5. 기록을 남길 것
> 오늘 해결한 에러는 내일 또 만납니다.
> 에러 노트를 꼼꼼히 작성하세요.

---

## 🎯 완성 시 결과물

이 로드맵을 완료하면 다음을 갖추게 됩니다:

1. **풀스택 웹 애플리케이션**
   - Spring Boot 백엔드
   - React 프론트엔드
   - PostgreSQL + Redis

2. **실무 경험**
   - Docker 컨테이너화
   - JWT 인증
   - 실시간 WebSocket
   - CI/CD 파이프라인

3. **포트폴리오**
   - GitHub에 전체 코드
   - 각 단계별 브랜치 히스토리
   - 문서화된 README

---

**다음 단계**: Phase 1을 시작하려면, 먼저 환경 설정 체크리스트를 완료하세요!
