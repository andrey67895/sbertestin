# Автотесты UI и API - Тестовое задание
## Запуск тестов и окрытие отчета Allure

Запуск происходит через команду системы сборки Maven.

Для запуска тестов и после завершения тестов для запуска веб-сервера Аллюр с отчетов воспользуйтесь этой командой:
```sh
mvn clean test allure:serve 
```
Для запуска тестов и просто генерации отчета, используйте команду ниже. В этом случае отчет сохраниться в папку <span style="color:teal">'target/allure-reports/index.html'</span>. 

```sh
mvn clean test allure:report
```

## Технологии, которые были использованы:

### UI-автотесты:
- org.seleniumhq.selenium - спецификация интерфейса для управления браузером.
- io.github.bonigarcia - webdrivermanager библиотека для автоматическим управлением драйверами.

### API-автотесты:
- rest-assured - библиотека для тестирования REST-сервисов.

### Общие полезные фичи:
- awaitility - Библиотека позволяющая быстро и легко настроить ожидания в тестах.
- lombok - Библиотека расширяющая функциональность фреймворка и сокращающая количество кода.
- allure - фреймворк для создания простых и понятных отчётов.
- junit5 - среда тестирования.
- java-faker - библиотека для генерации различных тестовыз данных.