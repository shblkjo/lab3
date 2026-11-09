## Цель работы
Создание Spring-приложения с несколькими связанными таблицами (не менее трёх) с использованием Java Persistent API (JPA). Реализация web-приложения для работы с данными: вывод всех записей, добавление новых, поиск и отбор по различным критериям (не менее трёх комбинаций), а также валидация всех передаваемых данных.

## Архитектура проекта
- **Стек:** Spring Boot 3, Maven
- **СУБД:** H2 (для разработки/тестирования), PostgreSQL (для продуктивной среды)
- **Основные слои:**
  - **Entity** — JPA-сущности (`Student`, `StudentGroup`, `University`), отображаемые на таблицы БД
  - **Repository** — интерфейсы Spring Data JPA (`StudentRepository`, `StudentGroupRepository`, `UniversityRepository`) с CRUD-операциями и derived-запросами
  - **Controller** — Spring MVC контроллеры (`StudentController`, `ReferenceController`) с обработкой GET/POST-запросов
  - **View** — Thymeleaf-шаблоны (`main.html`, `new_student.html`, `references.html`)
  - **Validation** — Bean Validation через `spring-boot-starter-validation`
- **Ключевые аннотации:**
  - `@Entity`, `@Table`, `@Id`, `@GeneratedValue` — маппинг сущностей на таблицы
  - `@ManyToOne`, `@JoinColumn` — связи между таблицами (студент → группа → ВУЗ)
  - `@NotBlank`, `@NotNull`, `@Size`, `@Min`, `@Max`, `@DecimalMin`, `@DecimalMax`, `@Past` — валидация полей
  - `@Valid`, `BindingResult` — запуск валидации в контроллере и обработка ошибок
  - `@Controller`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@ModelAttribute`, `@RequestParam` — маршрутизация и связывание данных

## Алгоритм работы
1. **Модель данных.** Три связанные сущности: `Student` → `StudentGroup` → `University` (связи `@ManyToOne` + `@JoinColumn`).
2. **Валидация.** Поля размечены аннотациями Bean Validation; в контроллерах используется `@Valid` + `BindingResult`, при ошибках — возврат на форму с сообщениями.
3. **Справочники.** `/references` — вывод и добавление ВУЗов и групп (группа привязывается к ВУЗу по `universityId`).
4. **Студенты.** `/students/main` — список всех; `/students/new` — форма создания с валидацией и проверкой существования выбранных ВУЗа и группы; `/students/find` — поиск по критериям.
5. **Поиск (≥3 комбинации).** Derived-запросы: по фамилии, названию ВУЗа, названию группы, специальности, курсу.
6. **Отображение.** Thymeleaf выводит данные связанных сущностей и ошибки валидации; таблицы создаются автоматически, начальное заполнение — через `data.sql`.
