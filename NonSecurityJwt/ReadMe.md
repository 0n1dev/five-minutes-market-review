# JWT
## Session VS Token
### Session 기반 인증

> 세션 기반 인증은 Session과 Cookie를 사용

- 유저가 로그인을 하고 세션이 서버 메모리에 저장 (세션 식별 Seesion ID를 기준을 저장)
- 쿠키에 Session ID 저장
- 서버에 요청시 Session ID 쿠키를 담아 전송
- 클라이언트가 보낸 Session ID와 서버 메모리에 저장된 Session ID를 비교하여 인증을 수행

#### 장점
- 구현이 명확함
- 상대적으로 안전 (데이터 손상의 우려가 없음)

#### 단점
- 유저의 세션 정보를 서버 메모리가 가지고있는 부담
- Scale out / Scale In이 부담
- 멀티 디바이스 환경에서 로그인 시 신경쓸 부분이 생김

### Token 기반 인증

> 대부분 JWT(JSON Web Token) 사용

- 유저가 로그인을 하면 서버에서 Token을 발급
- 클라이언트는 발급된 Token을 저장 (일반적으로 local storage에 저장)
- 클라이언트는 요청 시 Token을 Header에 포함하여 요청
- 서버는 매번 클라이언트로부터 전달 받은 Token 정보를 검증 한 뒤 유저에게 권한 지급


#### 장점
- 클라이언트에 저장이 되어 메모리에 부담이 되지 않음 Scale에 있어 대비책 고려 X
- 멀티 디바이스 환경에 대한 부담 X

#### 단점
- 상대적으로 손상의 위험이 큼
- 결국 구현을 하다보면 서버츨에 Token BlackList를 관리하게 될 가능성이 있음 (서버측 메모리 소모 발생)
- Token은 일반적으로 Session ID보다 길음
- XSS 공격에 취야할 수 있어 민감 정보는 포함 X