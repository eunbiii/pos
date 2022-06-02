1. 프로젝트 구성
- SpringBoot + Mybatis(MariaDB) + JAVA8 + REST API 로 구성
- kafka 를 이용하여 주문 내역을 데이터 수집 플랫폼으로 실시간 전송
Lombok DEVELOPER TOOLS
MariaDB Driver SQL
MariaDB JDBC and R2DBC driver.
Spring Web WEB
JDBC API SQL
Spring Boot DevTools DEVELOPER TOOLS
MyBatis Framework SQL

2. 데이터 설계
- 사용자 : 사용자아이디, 이름, 전화번호, 포인트
- 사용자 포인트 적립내역 : 시퀀스, 사용자아이디, 적립포인트, 적립일자, 유효기간
- 사용자 포인트 사용내역 : 시퀀스, 사용자아이디, 차감포인트, 사용일자, 주문아이디
- 메뉴 정보	: 메뉴아이디, 이름, 가격, 메뉴카테고리, 메뉴순서, 기타
- 주문		: 주문아이디(SEQ), 유저아이디, 총금액, 주문일자, 주문상태
- 주문상세	: 주문아이디, 메뉴아이디, 개별금액, 개수

3. 로직 설계
 3-1) MENU
  - 커피 메뉴 목록 조회 API : 커피 정보(메뉴ID, 이름, 가격)을 조회하는 API를 작성
  - 인기메뉴 목록 조회 API : 최근 7일간 인기있는 메뉴 3개를 조회하는 API 작성
getAllMenuList
getMenuTopNList

 3-2) POINT
  - 사용자 식별값, 충전금액을 입력 받아 포인트를 충전. (1원=1P)
  - 포인트 사용(적립 환불 등)

 3-3) ORDER
  - 사용자 식별값, 메뉴ID를 입력 받아 주문을 하고 결제를 진행
  - PAY : 충전한 포인트에서 주문금액을 차감
  - kafka를 이용해 주문 내역을 데이터 수집 플랫폼으로 실시간 전송
// 1. 주문 확인
// 2. 주문 INSERT : 상태 = 접수
// 3. 주문상세 insert
// 4. Point 차감, 차감내용 반영
// 5. 주문상태 : 결제완료
// 6. 주문정보 Kafka 전송

4. 추가 고려사항
- 포인트 유효기간 여부 : 현금성이기 때문에 환불은 있어도 유효기간은 없어야 한다.
- 인기있는 메뉴 추천 : 주문내역이 없을 시에는 
- 주문 취소 가능여부 : refund 기능 (아이디, 주문번호 필요)


