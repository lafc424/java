# 포트폴리오 설명
- 회원가입, 로그인,SNS(Google) 로그인, 로그아웃, 회원수정, 회원목록 검색이 가능한 페이지 입니다.
- 초기 멤버 user, admin, owner 3개가 생성됩니다.
   - 초기 비밀번호는 123123123 입니다.
- user, admin, owner 3개의 등급으로 분류됩니다.
- owner는 회원 삭제가 가능한 최고 등급입니다.
- Java, Spring Framework, Spring Boot, JPA, JPQL, Thymeleaf, JavaScript, H2 Database를 사용했습니다.

# 부가 설명
- Google로그인에 필요한 OAuth2LoginService 구현
- @Enumerated을 사용하여 emum 타입 매핑
- 조회 메서드를 많이 사용하므로 @Transactional(readOnly = true) 설정
- 회원가입 및 수정, 삭제 시 @Transactional 설정
  
  ![image](https://github.com/user-attachments/assets/17f65420-5b34-4429-8cd3-0843d0ec4455)

- util폴더에 passwordCreate코드 및 passwordValidate코드 구현
 
  ![image](https://github.com/user-attachments/assets/cbdf3760-71f0-48e0-9687-c013da15b188)
   - 비밀번호를 임시로 발행 또는 SNS 로그인 시 password insert 하기 위해서 구현
   - 회원가입, 로그인 및 비밀번호 변경 시 패스워드가 유효한지 여러곳에서 사용하기 때문에 util폴더에 따로 구현

# 메인 화면
![image](https://github.com/user-attachments/assets/4a09f339-9847-4afd-add4-f4f577c236a4)
- 초기 메인 화면 입니다.

# 로그인 화면 
![로그인화면](https://github.com/user-attachments/assets/17524ac5-9896-453e-b8d9-c82e71d1d896)
- 로그인 화면입니다.
- 회원가입 후 로그인 / 구글로 로그인이 가능합니다.

# 회원 가입 화면
![회원가입](https://github.com/user-attachments/assets/6d8945a7-bc0e-4e5b-b3e3-ed8ee55ca5af)
- 회원 가입 화면입니다.

![팝업창](https://github.com/user-attachments/assets/e9213d46-19b1-4ef6-b106-c59e54e9d59f)
- 아이디 중복시 팝업창이 출력 됩니다.

![image](https://github.com/user-attachments/assets/fb6b9460-e85f-4648-bd19-55ff6b9cd3d1)
- 비밀번호가 일치 하지 않을 시 팝업창이 출력 됩니다.

![image](https://github.com/user-attachments/assets/2d2dc25a-cbbc-4462-8a45-cd2ddff09a22)
- 비밀번호 미입력 및 8자 이상이 아니면 출력 됩니다.

# 회원목록
![admin 회원목록](https://github.com/user-attachments/assets/7246082b-3f8c-422f-98bf-b7ac50abaeb6)
![owner 목록화면](https://github.com/user-attachments/assets/3e769e72-2387-4bc9-bc48-0442bf167237)
- admin과 owner의 회원 목록 화면입니다.
- owner는 회원을 삭제할 권한을 가지고 있습니다.

![아이디 검색](https://github.com/user-attachments/assets/3e05fe31-3376-423d-803d-1e19e3fa471c)
![이름 검색](https://github.com/user-attachments/assets/5848562d-4c65-40e9-9110-4443147dc545)
- 아이디 및 이름으로 검색이 가능합니다.

# 회원 수정
![수정화면](https://github.com/user-attachments/assets/00272eac-c77d-43e0-9285-100c66148719)
- 수정 화면입니다.
- 이름 및 비밀번호를 수정 가능합니다. (아이디는 수정 불가능)


