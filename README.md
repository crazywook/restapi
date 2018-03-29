## Context
RestApi를 객체지향 프로그램 원칙에 근거해서 코딩
등록된 url을 enum 클래스로 관리
## Class Hierarchy
<ul>
	<li>restapi.bitmex
		<ul>
			<li>BitMexApiController.java
			<li>BitMexApiServiceImpl.java
		</ul>
	<li>restapi.external
		<ul>
			<li>ExteralApiService.java
		</ul>
	<li>restapi.internal
		<ul>
			<li>CommonApiController.java
			<li>CommonDao.java
			<li>CommonDaoImpl.java
		</ul>
	<li>restapi.url
		<ul>
			<li>ApiUrl.java
			<li>BitMexUrl.java
		</ul>	
	<li>restapi.util
		<ul>
			<li>HtmlConverter.java
			<li>TransFormer.java
		</ul>	
</ul>

## REST API 구성
``` /api/{service provider}/{version}/{resources} ```

첫번째는 url이 api라는 것을 나타내고

두번째는 api 서비스 공급자를 표시

세번째는 버전을 표시

네번째 자원을 표시

서비스 공급자 관련 url을 제외한 실질 구성은

depth를 1로 제한해도 무리가 없다고 판단

resources를   와일드 카드로 처리하고

enum을 활용해 동적으로 외부 api를 호출


## 외부 API 관리
외부 API주소를 동적으로 부르기 위해 enum을 활용
서비스 공급자 변경에 대처하기 위해 개방폐쇄 원칙을 준수하고 인터페이스를 분리했습니다.
새로운 서비스 공급자가 생긴다면 ApiUrl.java를 구현한 객체를 쓰면 됩니다.

## 외부API 구현
restapi.external.ExternalApiService를 구현하면 됩니다.
get,  post, put, delete, execute 메서를 구현하면 됩니다.
get을 제외한 post, put, delete은 execute에 메서드 종류만 넘기면 됩니다.
