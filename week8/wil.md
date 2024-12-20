# 서비스 계층

---
## 서비스 계층
• 어플리케이션의 비즈니스 로직이 담기는 계층   
• 레포지토리 계층과 소통하며 엔티티, 또는 DTO로 소통한다.     
• 서비스 계층의 하나의 메서드에는 원자성을 갖는 로직을 기술한다.   
• 로직의 원자성을 보장하기 위해서 서비스 계층에 메서드 단위로 트랜잭션을 적용해준다.        
• 서비스도 객체를 중복해서 생성할 필요가 없기 때문에 @Service 어노테이션을 이용하여 빈으로 등록해서 사용한다.      
• 서비스 계층은 레포지토리 계층에 의존한다.       

---
## 비즈니스 로직
• 더 이상 쪼갤 수 없는 원자성을 가진 로직이다.        
• 레포지토리 계층을 사용하는 서비스 로직은 반드시 트랜잭션 안에서 동작하도록 작성한다.       
• 메서드에 @Transactional 을 붙여준다.       
• 클라이언트가 제공하는 정보는 컨트롤러로부터 받아서 저장하도록 한다.
---
## 단위 테스트
• 서비스 테스트는 단위 테스트로 작성한다.
• 단위 테스트는 스프링 부트와 다른 클래스에 의존하지 않고, 대상 자바 클래스 하나만 단독으로 테스트하는 것을 말한다.     
• 서비스 클래스를 단독으로 테스트하기 위해, 서비스가 의존하는 레포지토리는 가짜 객체를 사용한다.     
• 이 가짜 객체를 가리켜 mock 이라고 한다.     
• @ExtendWith 어노테이션을 사용하여 테스트 클래스에 Mockito를 적용한다.       
• @Mock을 사용하여 주입할 객체를 모킹한다.     
• @InjectMocks를 사용하여, 서비스 객체를 생성할 때 모킹한 객체를 주입한다.       