# Дипломный проект по профессии «Тестировщик»
### ПО, необходимое для запуска приложения:
* IntelliJ IDEA
* Docker Desktop

### Шаги запуска автотестов:
1. Скопировать [проект](https://github.com/vetka-g/QADiplom) с помощью команды `git clone`
2. Запустить Docker Desktop 
3. Открыть проект в IntelliJ IDEA
4. В первом окне терминала запустить контейнеры, использую команду `docker-compose up -d`
5. Во втором окне терминала запустить проект:
* для MySQL командой `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`
* для PostgreSQL командой `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`
6. В третьем окне терминала запустить тесты:
* для MySQL командой `./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"`
* для PostgreSQL командой `./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`
7. Сформировать отчет Allure с помощью команды `./gradlew allureServe`

### Документы
* [План автоматизации](https://github.com/vetka-g/QADiplom/blob/main/docs/Plan.md)
* [Отчет по итогам тестирования](https://github.com/vetka-g/QADiplom/blob/main/docs/Report.md)
* [Отчет по итогам автоматизации](https://github.com/vetka-g/QADiplom/blob/main/docs/Summary.md)