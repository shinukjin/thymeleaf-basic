1.타임리프기본 기능 
1) 텍스트 text, utext 
 - th:text를 사용하면 된다.
 - <span th:text="${data}"}> 
 - 이스케이프 기능을 사용할 때, th:text , [[...]]
 - 이스케이프 기능을 사용하지 않을 때, th:utext, [{...}]

2) spring EL
 - Object
	${user.username} = userA
	${user['username']} = userA
	${user.getUsername()} = userA
 - List
	${users[0].username} = userA
	${users[0]['username']} = userA
	${users[0].getUsername()} = userA
 - Map
	${userMap['userA'].username} = userA
	${userMap['userA']['username']} = userA
	${userMap['userA'].getUsername()} = userA
 
 3) 기본객채 (springboot 3.xx버전 이상은 기본객체 ${#request}, ${#response}, ${#servletContext}를 제공하지 않음)
 4) 유틸리티 객체 날짜
	#message : 메시지, 국제화 처리
	#uris : URI 이스케이프 지원
	#dates : java.util.Date 서식 지원
	#calendars : java.util.Calendar 서식 지원
	#temporals : 자바8 날짜 서식 지원
	#numbers : 숫자 서식 지원
	#strings : 문자 관련 편의 기능
	#objects : 객체 관련 기능 제공
	#bools : boolean 관련 기능 제공
	#arrays : 배열 관련 기능 제공
	#lists , #sets , #maps : 컬렉션 관련 기능 제공
 - 참고만 하면됨.(필요할때 찾으면됨)
 4) url 링크
 - 단순url : @{/hello) > /hello
 - 쿼리파라미터 : @{/hello(param1=${param1}, param2=${param2})} > /hello?param1=data1&param2=data2
   > ()안에 있는 부분이 쿼리 파라미터로 처리
 - 경로변수 : @{/hello/{param1}/{param2}(param1=${param1},param2=${param2})}
   > url 경로상에 변수가 있으면 경로변수로 처리
 - 경로변수 + 쿼리파라미터 : @{/hello/{param1}(param1=${param1}, param2=${param2})}
   > 경로변수와 쿼리파라미터 함께사용 가능
 
 5) 리터럴
 - 타임리프에서 문자 리터럴은 항상 ''로 감싸줘야한다. => <span th:text="'hello'"> (원칙상)
 - 공백없이 이어진다면 하나의 의미있는 토근으로 인지해 사용가능 => <span th:text="hello"> 가능, <span th:text="hello world"> 불가능
 
 6) 속성
 - html 태그에 th:* 속성을 지정하는 방식으로 동작, 기존속성이 있으면 대체하고 없으면 새로 만듬
 - th:attrappend > 속성값 뒤에 추가
   th:attrprepend > 속성값 앞에 추가
   th:classappend > 클래스속성에 알맞게 추가
 - checked 속성은 값과 상관없이 checked만 있어도 체크되어 불편
   th:checked false인 경우 checked 속성 자체 삭제
 
 7) 반복
 - th:each를 사용가능, list뿐 아니라 java.util.Iterable, java.util.Enumeration을 구현한 모든 객체를 반복사용 가능. Map도 사용, 담기는 변수는 Map.Entry
 - 반복 상태 유지 <tr th:each="user, userStat : ${users}">
   반복의 두번째 파라미터를 사용해서 상태 확인 가능
   생략가능하며, 생략한 경우 지정한 변수명(user) + Stat으로 확인 가능. (index, count, size, even, odd, first, last, current 확인)
 
 8) 조건부평가
 - th:if, th:unless(if문의 반대), th:switch, th:case로 조건문 사용, 타임리프는 조건이 맞지 않으면 렌더링 하지 않고 사라짐. 
   <th:case="*"> : *은 만족하는 조건이 없는때 사용하는 디폴트.
 
 9) 주석
 - 표준 html 주석 : <!-- -->  , html 주석으로 렌더링시 남겨둠
 - 타임리프 파서 주석 : <!-- /* */ --> , 타임리프 주석으로 렌더링시 주석부분 제거
 - 타임리프 프로토타입 주석 : <!--/*/ /*/--> , 웹브라우저에서 그대로 열면 렌더링하지 않음. 타임리프 렌더링을 거치면 정상 렌더링이 된다.
 
 10) 블록(타임리프에서만 제공하는 유일한 자체태그)
 - 타임리프 특성상 html 태그 안에서 속성으로 기능을 정의해 사용하는데, 사용하기 애매한 경우에 사용하면 된다. <th:block> 는 렌더링시 삭제됨.
 
 11) 자바스크립트 인라인.
 - 타임리프는 자바스크립트에서 타임리프를 편리하게 사용할수이쓴 자바스크립트 인라인기능을 제공하는
 - <script th:inline = "javascript"></script>
 - 인라인 사용전 : [[${user.username}]] > userA로 반환
 - 인라인 사용 후 : [[${user.username}]] > "userA"로 반환
 - 자바스크립트 내츄럴템플릿 사용가능, var username2 = /*[[${user.username}]]*/ "test username"; 웹브라우저로 바로 실행 시, 주석처리 되어 test username으로 
   타임리프 렌더링시 "userA"로 
   
 12) 템플릿 조각
 - 공통 영역의 부분을 지원하기 위해 타임리프에서 템플릿조각, 템플릿레이아웃 기능을 지원.
 - th:fragment가 있는 태그는 다른곳에 포함되는 코드 조각
 - template/fragment/footer :: copy : template/fragment/footer.html  >> th:fragment="copy" 라는 부분을 템플릿 조각으로 가져와서 사용
 - th:insert >> 현재 태그 내부에 추가
 - th:replace >> 현재태그를 대체
 - 파라미터 사용은 아래와 같이 사용할 수 있다.
 
 (main.html영역)
 <div th:replace="~{template/fragment/footer :: copyParam ('데이터1', '데이터2')}"></div>
 
 (footer.html 영역)
 <footer th:fragment="copyParam (param1, param2)">
 <p>파라미터 자리 입니다.</p>
 <p th:text="${param1}"></p>
 <p th:text="${param2}"></p>
 </footer>
 
 13) 템플릿레이아웃
 -
 ````
 /resources/templates/template/layout/base.html
 <html xmlns:th="http://www.thymeleaf.org">
 <head th:fragment="common_header(title,links)">
 <title th:replace="${title}">레이아웃 타이틀</title>
 <!-- 공통 -->
 <link rel="stylesheet" type="text/css" media="all" th:href="@{/css/awesomeapp.css}">
 <link rel="shortcut icon" th:href="@{/images/favicon.ico}">
 <script type="text/javascript" th:src="@{/sh/scripts/codebase.js}"></script>
 <!-- 추가 -->
 <th:block th:replace="${links}" />
 </head> 

 /resources/templates/template/layout/layoutMain.html
 
 <!DOCTYPE html>
 <html xmlns:th="http://www.thymeleaf.org">
 <head th:replace="template/layout/base :: common_header(~{::title},~{::link})">
 <title>메인 타이틀</title>
 <link rel="stylesheet" th:href="@{/css/bootstrap.min.css}">
 <link rel="stylesheet" th:href="@{/themes/smoothness/jquery-ui.css}">
 </head>
 <body>
 메인 컨텐츠
 </body>
 </html>
 ````
