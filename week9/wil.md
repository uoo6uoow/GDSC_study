# 컨트롤러 계층
---
## 컨트롤러
• 클라이언트의 요청을 받고, 응답을 보내는 계층     
• DTO (Data Transfer Object)를 사용하여 서비스 계층과 데이터를 주고받는다.     
• 컨트롤러도 하나의 객체만 생성하면 되므로 빈으로 등록한다.      
• @Controller를 사용하면 컴포넌트 스캔의 대상이 된다.        
• 보통 어플리케이션과 관련된 데이터는 body에 담는다.        
(header에 담는 경우도 있는데, 이번 스터디에서는 다루지 않습니다.)       
• HTTP 요청으로 보내는 데이터는 Request Body       
• HTTP 응답으로 보내는 데이터는 Response Body 에 담긴다.       
• API 서버는 json 데이터를 응답하는 경우가 많다.        
• @ResponseBody 사용하면 메서드가 자바 객체를 반환했을 때, 객체를 json 데이터로 변환해서 response body에 담아 응답한다.     
• 할 일 데이터를 생성하려면 content, user 데이터를 받아야 한다.     
• Request body 데이터는 보통 json 형식으로 들어오며, 메서드 파라미터로 받을 수 있다.       
• @RequestBody를 사용하여 파라미터로 들어오는 json 데이터를
자바 객체로 변환하여 받을 수 있다.        
• 데이터를 받는 자바 객체를 DTO(Data Transfer Object) 라고 한다.       

---
## 응답생성
• 컨트롤러 계층은 클라이언트의 요청을 처리한 뒤, 처리 결과를 클라이언트에게 알려주어야 한다.       
• HTTP 프로토콜에 정의된 상태코드를 사용하여 결과를 알려주자.       
### 대표적인 상태 코드
• 200 → 처리 성공 (ok)      
• 201 → 데이터 생성 성공 (created)     
• 400 → 클라이언트 요청 오류 (bad request)       
• 404 → 요청 데이터 없음 (not found)       
• 500 → 서버 에러 (internal server error)       