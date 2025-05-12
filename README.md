**🔰 Introduction**

The Course Registration System is a web-based application developed using Java Spring Boot that streamlines the process of managing courses, users (students and admins), and course enrollments within an academic or training institution. It provides a secure and user-friendly interface for both administrators and students, ensuring seamless course registration and management workflows.

**🧰 Tech Stack Used**

**🖥 Backend:**

Java 21

Spring Boot 3.x

Spring MVC – For building RESTful web services

Spring Data JPA – For ORM and database interactions

Hibernate – For object-relational mapping

Lombok – For reducing boilerplate code

MySQL – Relational database

**🌐 Frontend:**

Thymeleaf – Server-side Java template engine

HTML5, CSS3, JavaScript – For building the user interface

Bootstrap (optional) – For responsive design

**⚙️ Build & Config:**

Maven – Build and dependency management

application.properties – Centralized application configuration

Spring DevTools – For automatic restarts and live reload

Visual studio - For IDE and Coding purpose

**🚀 Features👩‍🎓**

Student/User Module: User Registration and Login

View all available courses

Register for selected courses

View registered/enrolled courses

Update personal profile

**🧑‍💼 Admin Module:**

Admin Login

Add, update, and delete courses

View list of students and their registered courses

Manage user roles and permissions

**📚 Course Module:**

Add and manage course details (name, duration, description, etc.)

Display all courses to users

Associate courses with students via registration

**🔐 Authentication & Security:**

Role-based access control for Admin and Student

Secure login with encrypted credentials

**🏗️ Architecture**

The system is based on the classic MVC layered architecture, comprising:

Model Layer: Contains JPA entity classes such as Course, UserInfo, AdminInfo, and CourseRegistration.

Repository Layer: Interfaces extending JpaRepository to perform CRUD operations.

Service Layer: Business logic for user registration, course handling, and enrollments.

Controller Layer: Handles HTTP requests and responses.

DTO Layer: Used to transfer data between the frontend and backend securely.

Frontend (View): Static and template folders under resources contain HTML files for user interaction.

**🗃️ Database Design**

UserInfo: Stores user data including name, email, password, and role.

AdminInfo: Contains admin-specific information (can be merged with UserInfo using roles).

Course: Course ID, title, description, and duration.

CourseRegistration: Maps students to the courses they have registered for.

**⚙️ Configurations**

Application runs on port: 8081

Database connection configured via application.properties

Uses JPA and Hibernate to auto-generate and manage tables in MySQL

**📈 Benefits**

Enhances transparency in course registration.

Reduces manual errors and improves efficiency.

Supports future scalability for additional features like notifications, schedules, and certificate generation.
