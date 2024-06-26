## Описание приложения

Приложение представляет из себя веб-сервис.
![img.png](img.png)
Приложение предлагает купить тур по определённой цене с помощью двух способов:

1. Обычная оплата по дебетовой карте
2. Уникальная технология: выдача кредита по данным банковской карты

## Документы:

- План автоматизации ([Plan.md](https://github.com/Ekaterina-Isabel/Diploma/blob/master/Plan.md))
- Отчёт о проведённом тестировании ([Report.md](https://github.com/Ekaterina-Isabel/Diploma/blob/master/Report.md))
- Отчёт о проведённой автоматизации ([Summary.md](https://github.com/Ekaterina-Isabel/Diploma/blob/master/Summary.md))

## Предустановленное ПО:

- Intellij idea Ultimate Edition
- Docker с предустановленным sql (https://hub.docker.com/_/mysql, https://hub.docker.com/_/postgres)
- Node.js (https://nodejs.org/en)

## Параметры окружения
- java 17
- JAVA_HOME v17
- node.js v20.12.2
- npm v10.5.0

*примечание: если автотесты не запускаются (`./gradlew clean test allureReport`), то необходимо изменить версию библиотеки (`com.codeborne:selenide`) на более свежую

## Процедура запуска авто-тестов:

1. Запустить Docker
2. Для копирования репозитория в терминале выполнить команду `git clone <ссылка на репозиторий>`
3. Для того, что бы протестировать сервис на БД PostgreSQL или MySQL необходимо раскомментировать/закомментировать настройки для соотвтествующих БД в файлах [application.properties](https://github.com/Ekaterina-Isabel/Diploma/blob/master/application.properties), [docker-compose.yml](https://github.com/Ekaterina-Isabel/Diploma/blob/master/docker-compose.yml) и в [build.gradle](https://github.com/Ekaterina-Isabel/Diploma/blob/master/build.gradle) в блоке `test`
4. Для запуска контейнеров с БД в терминале выполнить команду `docker-compose up`
5. Для запуска приложения в терминале выполнить команду `java -jar .\artifacts\aqa-shop.jar`. Приложение открывается на
   странице http://localhost:8080/
6. Для запуска симулятора банковских сервисов перейти в каталог `gate-simulator` и выполнить в терминале
   команду `npm start`
7. Для запуска автотестов с генерацией отчета после их прохождения выполнить команду `./gradlew clean test allureReport`
8. Для открытия отчета в браузере выполнить команду `./gradlew allureServe`
