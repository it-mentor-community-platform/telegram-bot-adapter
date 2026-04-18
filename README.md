# Telegram Bot Adapter

### Основная информация

Сервис-адаптер для безопасного общения микросервисов с
Telegram ботом по REST API 

## Контекст

- [Системная аналитика](https://github.com/it-mentor-community-platform/meta/blob/main/system-analytics/telegram-bot-integration.md) интеграции нашего бэкенда с Telegram ботом сообщества
- [Системная аналитика ](https://github.com/it-mentor-community-platform/meta/blob/main/system-analytics/stack.md#backend) - Общее описание стека бекенда

## Стек

- Kotlin
- Spring Boot 3
- Spring Data JDBC
- Spring Kafka
- Liquibase

## Взаимодействия

Входящие:
- REST эндпоинты
- Kafka

## Kafka

### Consumer для `projects.project.created`

Используется для уведомления других сервисов о создании нового проекта.

[Payload сообщения]( https://github.com/it-mentor-community-platform/meta/blob/main/system-analytics/services/project-service/index.md#producer-%D0%B4%D0%BB%D1%8F-%D1%82%D0%BE%D0%BF%D0%B8%D0%BA%D0%B0-projectsprojectcreated)

## Сборка и запуск Docker-образа
- Сборка образа

docker build -t telegram-bot-adapter .   

- Запуск контейнера с подключением к сети local-stack окружения

docker run --network local-stack_default -e SPRING_PROFILES_ACTIVE=local-stack -p 8087:8080 telegram-bot-adapter