# Student Management System

A full-stack Student Management System built with **Java and Spring Boot** for managing students, courses, enrollments, authentication, and enrollment analytics through a responsive web interface.

## 🚀 Features

- Student management — Create, update, view, and delete student records
- Course management — Create, update, view, and manage courses
- Course enrollment — Enroll students into available courses
- Enrollment summary — View total courses and total fees for each student
- Enrollment details — View all courses enrolled by a particular student
- Enrollment analytics — Track monthly enrollments and course statistics
- Top course identification based on enrollment count
- Pagination for students and enrollment summaries
- Server-side form validation
- Custom login and logout functionality
- Spring Security-based authentication
- BCrypt password hashing
- Default administrator initialization
- Environment-variable based configuration for sensitive credentials
- Responsive UI using Bootstrap and Thymeleaf

---

## 🛠️ Tech Stack

### Backend

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Spring Security
- Maven

### Database

- MySQL

### Frontend

- HTML5
- CSS3
- Bootstrap
- Thymeleaf
- Bootstrap Icons

### Development Tools

- IntelliJ IDEA
- MySQL
- Git
- GitHub
- Postman

---

## 🏗️ Architecture

The application follows a layered architecture to separate responsibilities and maintain clean, maintainable code.

```text
                    Browser
                       │
                       ▼
                 Thymeleaf UI
                       │
                       ▼
                  Controller
                       │
                       ▼
                    Service
                       │
                       ▼
                  Repository
                       │
                       ▼
                  Spring Data JPA
                       │
                       ▼
                    MySQL