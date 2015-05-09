System zarządznia kolekcją gier ( SZKG )
==============

Workflow
--------------
1. Należy utworzyć forka repozytorium z organizacji PIKpw
2. W IDE zaimportować projekt z githuba podając link do lokalnego repozytorium
3. Po prowadzenu zmian robimy commit z poziomu IDE
4. Należy zrobić push na własne lokalne repozytorium
5. Następny etap to stworzenie pull requesta
6. Jeżeli nie można wykonać merge to robimy Rebase my github fork i rozwiązujemy konflikty
7. Jenkins automatycznie pobiera kod z githuba, buduje za pomocą mavena, wykonuje testy za pomocą Junit i wrzuca na serwer aplikacyjny.

Systemy wspomagajace tworzenie kodu
--------------
Jira - http://pik.eiv.pl/jira ( Backlog projektu i zarządzanie zadaniami )
Sonar - http://pik.eiv.pl/sonar ( Analiza jakości kodu )
Jenkins - http://pik.eiv.pl/jenkins ( Ciągła integracja i testowanie )
Elasticbeanstalk http://pik.eiv.pl ( Serwer aplikacyjny na tomcat 8 )

Srodowisko aplikacyjne
--------------
1. Cloudflare - obsługuje SSL'a, cachowanie
2. Varnish - obsługa przekierowań do systemów wsparcia tworzenia kodu
3. Aplikacja w chmurze na Amazon AWS
4. Baza danych - Mysql
5. Serwer aplikacyjny Tomcat8 w ElasticBeansTalk
6. Spring MVC
7. Hibernate do ORM
8. Junit do testów
9. Maven do budowania

User Stories
--------------
0. Wchodząc do aplikacji użytkownik widzi ekran powitalny wraz z możliwością logowania.
1. Użytkownik loguje się do aplikacji i widzi menu (Moje Gry, Lista życzeń, Kategorie).
2. Klikając na Moje gry w menu pojawia się lista gier wraz z opcją dodania nowej pozycji i filtrowania po kategoriach. Po kliknięciu na grę przechodziny do widoku szegółów danej gry.
3. Ekran edycji umożliwia edytowanie i usuwanie wybranej pozycji.
4. Lista życzen ma zachowywać się identycznie jak Moje Gry, ale parametry gier z tej kategorii mogą się różnić.
5. Po przejściu do kategorii wyświetla się lista kategorii wraz z możliwością dodawania.
6. Po wybraniu konkretnej kategorii pojawiają się szczegóły danej kategorii wraz z opcją edycji i usunięcia.
7. Każdą grę można przypisać do dowolnej ilości zdefiniowanych kategorii.

Materiały, tutoriale
-------------
http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html
http://devblog.consileon.pl/2011/01/03/mockowanie-z-uzyciem-Spring-i-Mockito/
http://docs.spring.io/spring/docs/current/spring-framework-reference/html/orm.html
https://confluence.jetbrains.com/display/IntelliJIDEA/Getting+Started+with+Spring+MVC,+Hibernate+and+JSON
https://www.jetbrains.com/idea/webhelp10.5/creating-junit-test-cases.html