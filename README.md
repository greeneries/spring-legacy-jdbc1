# spring-legacy-jdbc1

#### Spring Legacy Project 생성하기 
1.  Spring Legacy Project > Templates를 Spring MVC Project 선택 > com.example.demo 입력 > finish 버튼 클릭 

#### Spring Legacy Project는 default가 spring 3.X임으로 spring 4.X로 변경하기 위해서는 몇가지 작업을 수동으로 해줘야 한다. 
1. 필요시에 properties > Web Project Settings > Context root를 변경한다. 
2.  properties > Java Build Path > Libraries에서 Java 1.6을 1.8로 변경해준다. 
              - remove Library > add Library에서 JRE System Library에서 1.8로 변경해준다.
3. properties > Java Complier에서 JDK Compliance 버전을 1.8로 변경해준다. 
4. properties > Project Facets에서 Java는 1.8로 변경, Dynamic Web Module은 3.1로 변경
