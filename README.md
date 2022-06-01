# Сервис для взятия курса валюты и возвращения гифки

## Как запустить:

1) Нужно собрать проект, чтобы появилась папка `./build/libs`. Для этого можно использовать команду Gradle `bootjar`
2) Если эта папка существует, и в ней есть файл `alpha-feign-currency-0.0.1-SNAPSHOT.jar`, то надо запустить Dockerfile<br>
    _Данный Dockerfile использует Eclipse java 17 alpine_ 
3) Так же есть вариант запустить `Docker-compose`. Но для этого нужно обновить image приложения при внесении изменений.
    В Docker-compose указан маппинг портов 8080:8080, который можно поменять при необходимости
    
Как вернуть само изображение в результате запроса у меня не получилось, только ссылку.
Чтобы посмотреть на ссылку, запрос должен быть `http://localhost:8080/get/`, валюта записана в `application.yaml`
Для просмотра всех валют на сегодня `http://localhost:8080/latest/`
