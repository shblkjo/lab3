# Лабораторная работа №4. Построение REST API

## Цель работы
Создание Spring-приложения с несколькими таблицами с использованием REST API

## Архитектура проекта
- **Стек:** Spring Boot 3, Spring JDBC (JdbcTemplate), Maven.
- **СУБД:** H2
- **Основные слои:** 
  - Controller (endpoints)
  - Repository (методы для получения данных)
  - Controller — обработка HTTP-запросов
  - Entity — описание сущностей и связей между таблицами.
  - DTO — передача и валидация входных данных.
- **Ключевые аннотации:**
  - `@Repository`, `@Controller`, `@RestController`, `@RequestMapping` — маршрутизация и связывание данных
  - `@GetMapping`, `@PostMapping`, `@PutMapping`, `@PatchMapping`, `@DeleteMapping`, `@ModelAttribute`, `@RequestParam`, `@RequestBody`, `@PathVariable` — маршрутизация и связывание данных
  - `@NotBlank`, `@NotNull`, `@Size`, `@Min`, `@Max`, `@DecimalMin`, `@DecimalMax`, `@Past`, `@Positive` — валидация полей
  - `@Valid`, `BindingResult` — запуск валидации в контроллере и обработка ошибок
  - `@ResponseStatus` — установка HTTP-статуса ответа
- **Описание моделей данных и DTO**
  - Student — студент (ФИО, пол, национальность, рост, вес, дата рождения, телефон, адрес, курс, средний балл, специальность, ВУЗ, группа).
  - University — ВУЗ (название).
  - StudentGroup — учебная группа (название, ВУЗ).
  - StudentDTO — данные для создания и изменения студента (содержит `universityId` и `studentGroupId`).
  - StudentMapper — маппинг строки ResultSet в объект Student.

## Алгоритм работы
- Spring Boot запускает приложение и подключается к базе данных H2.
- REST-контроллер (`StudentRestController`) принимает HTTP-запросы и обрабатывает переданные данные.
- DTO (`StudentDTO`) проверяет корректность входных данных с помощью Bean Validation.
- Repository (`StudentRepository`, `UniversityRepository`, `StudentGroupRepository`) выполняет операции с базой данных через `JdbcTemplate`.
- Реализованы операции CRUD для студентов:
  - получение всех записей и поиск по ID;
  - добавление новых записей;
  - изменение существующих записей (полное — PUT, частичное — PATCH);
  - удаление записей.
- Дополнительно реализованы:
  - поиск студентов по фамилии, ВУЗу, группе, специальности и курсу;
  - частичное обновление студента через PATCH с валидацией изменяемых полей;
  - обработка ошибок через `NoSuchElementException` и `ResponseStatusException`.
