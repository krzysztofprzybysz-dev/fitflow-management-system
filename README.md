# 🏋️ FitFlow

A comprehensive web-based fitness club management system built with Spring Boot and Thymeleaf.

![Java](https://img.shields.io/badge/Java-21-007396?style=flat&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.1-6DB33F?style=flat&logo=spring-boot&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.0-005F0F?style=flat&logo=thymeleaf&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.1.3-7952B3?style=flat&logo=bootstrap&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2-Database-003545?style=flat&logo=h2&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.8-C71A36?style=flat&logo=apache-maven&logoColor=white)

## 📋 Overview

FitFlow is a full-stack fitness club management system that streamlines operations for members, trainers, and staff. The application provides an intuitive interface for class scheduling, reservation management, and membership tracking, demonstrating professional software engineering practices including design patterns, clean architecture, and comprehensive testing.

Developed as a university project for Modeling and Systems Analysis (MAS) course at PJATK.

### 🎯 Target Users
- **Members**: Browse classes, make reservations, manage memberships
- **Trainers**: View assigned classes, check participant lists
- **Receptionists**: Manage memberships, handle payments

## 🌟 Key Features

### For Members
- 📅 Interactive class schedule with real-time availability
- 🎫 Online reservation system with instant confirmation
- 💳 Membership status tracking (Standard/Premium)
- 📧 Automatic email reminders for upcoming classes
- ❌ Reservation cancellation with configurable policies
- ⭐ Class rating and feedback system

### For Trainers  
- 👥 Participant list access for each class
- ✅ Attendance tracking functionality
- 🚫 Class cancellation with automatic notifications

### For Staff
- 🏷️ Membership sales and renewal processing
- 💰 Payment management system
- 📊 Real-time occupancy tracking

## 🏗️ Architecture

### System Design
```
┌─────────────────┐     ┌─────────────────┐     ┌─────────────────┐
│   Controllers   │────▶│    Services     │────▶│  Repositories   │
│   (REST/MVC)    │     │ (Business Logic)│     │  (Data Access)  │
└─────────────────┘     └─────────────────┘     └─────────────────┘
         │                                                │
         ▼                                                ▼
┌─────────────────┐                            ┌─────────────────┐
│   Thymeleaf     │                            │   H2 Database   │
│   Templates     │                            │   (In-Memory)   │
└─────────────────┘                            └─────────────────┘
```

### Design Patterns Implemented
| Pattern | Usage | Benefits |
|---------|-------|----------|
| **State Pattern** | Membership states (Standard/Premium/Expired) | Dynamic behavior based on membership status |
| **Strategy Pattern** | User authentication strategies | Flexible authentication for different user types |
| **Repository Pattern** | Data access layer | Separation of concerns, testability |
| **MVC Pattern** | Overall architecture | Clear separation of presentation, logic, and data |

### UML Diagrams

#### Use Case Diagram
![Use Case Diagram](docs/DIAGRAM%20-%20PRZYPADKOW%20UZYCIA.png)

#### Class Diagram (Analytical)
![Analytical Class Diagram](docs/DIAGRAM%20-%20ANALITYCZNY.png)

#### Activity Diagram - Reservation Process
![Activity Diagram](docs/DIAGRAM%20AKTYWNOSCI%20-%20REZERWACJA%20ZAJEC%20GRUPOWYCH.png)

#### State Diagram - Reservation Lifecycle
![State Diagram](docs/DIAGRAM%20STANU%20-%20KLASA%20REZERWACJA.png)

### Detailed Class Diagrams

<details>
<summary>Module 1: Users and Memberships</summary>

![Module 1 - Users](docs/MODUŁ%201%20-%20UŻYTKOWNICY.png)
</details>

<details>
<summary>Module 2: Classes and Facilities</summary>

![Module 2 - Classes](docs/MODUŁ%202%20-%20ZAJĘCIA.png)
</details>

<details>
<summary>Module 3: Reservations and Notifications</summary>

![Module 3 - Reservations](docs/MODUŁ%203%20-%20REZERWACJE.png)
</details>

## 🚀 Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.8+
- Web browser (Chrome, Firefox, Safari)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/krzysztofprzybysz-dev/fitflow.git
   cd fitflow
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   ```
   http://localhost:8082
   ```

### Default Test Accounts

| Role | Email | Password |
|------|-------|----------|
| Member (Standard) | jan.kowalski@example.com | password123 |
| Member (Premium) | anna.zielinska@example.com | password123 |
| Trainer | anna.nowak@fitflow.com | password123 |

## 📝 Usage

### Member Workflow

1. **Login** to the system using your credentials

2. **Browse** the class schedule
   
   ![Class Schedule](docs/Strona%20główna%20z%20grafikiem.png)
   
   The main dashboard displays available fitness classes with:
   - Real-time availability status
   - Trainer information
   - Quick reservation buttons

3. **Make a Reservation**
   
   ![Reservation Modal](docs/Modal%20rezerwacji.png)
   
   The reservation process includes:
   - Membership status verification
   - Available spots counter
   - Email reminder preferences

4. **Confirm Your Booking**
   
   ![Reservation Confirmation](docs/Potwierdzenie%20rezerwacji.png)
   
   After confirmation, you'll receive:
   - Instant booking confirmation
   - Email notification
   - Option to view all reservations

### Trainer Workflow

1. **View Your Classes** - Access your assigned classes through the trainer dashboard
2. **Check Participants** - See registered members for each class
3. **Track Attendance** - Mark present participants during class

## 🔧 Technical Details

### Technology Stack

#### Backend
- **Spring Boot 3.4.1** - Application framework
- **Spring Data JPA** - Data persistence
- **H2 Database** - In-memory database
- **Lombok** - Boilerplate code reduction
- **MapStruct** - Object mapping

#### Frontend  
- **Thymeleaf** - Server-side template engine
- **Bootstrap 5.1.3** - CSS framework
- **JavaScript** - Client-side interactions

### Project Structure
```
fitflow/
├── src/main/java/s24825/
│   ├── controller/          # MVC Controllers
│   │   ├── ClassScheduleController.java
│   │   ├── LoginController.java
│   │   ├── MembershipController.java
│   │   ├── MyReservationsController.java
│   │   └── TrainerController.java
│   ├── model/              # Entity classes
│   │   ├── classes/        # Fitness classes
│   │   ├── membership/     # Membership & states
│   │   ├── person/         # User entities
│   │   └── reservation/    # Booking system
│   ├── repository/         # Data access layer
│   ├── service/           # Business logic
│   │   ├── auth/          # Authentication strategies
│   │   └── ...
│   └── exception/         # Custom exceptions
├── src/main/resources/
│   ├── templates/         # Thymeleaf views
│   └── application.properties
├── docs/                  # Documentation & diagrams
└── pom.xml
```

### Database Schema

<details>
<summary>View Database Tables</summary>

| Table | Description |
|-------|-------------|
| `members` | Club member information |
| `trainers` | Trainer profiles and specializations |
| `fitness_classes` | Class definitions and schedules |
| `group_classes` | Group fitness class specifics |
| `reservations` | Booking records |
| `memberships` | Membership/pass information |
| `training_rooms` | Facility information |
| `equipment` | Training equipment inventory |

</details>

### Key Implementation Details

- **State Pattern**: The `Membership` class uses state pattern with `MembershipState` interface implemented by `StandardState`, `PremiumState`, and `ExpiredState`
- **Authentication Strategy**: `UserFinder` interface with `MemberFinder` and `TrainerFinder` implementations
- **Session Management**: Custom `SessionService` for handling user sessions
- **Global Attributes**: `GlobalControllerAdvice` provides membership details across all views

## 🧪 Testing

Run tests with:
```bash
mvn test
```

### Test Coverage
- Unit tests for services
- Integration tests for repositories
- Controller tests with MockMvc

## 📈 Future Enhancements

- [ ] Payment gateway integration
- [ ] Mobile application
- [ ] QR code check-in system
- [ ] Advanced analytics dashboard
- [ ] Equipment reservation module
- [ ] Personal training scheduling

## 🤝 Contributing

This is a university project and is not open for contributions. However, feel free to fork and adapt for your own use.

## 📄 Documentation

Full system documentation (in Polish) is available in the `/docs` folder:
- 📕 [Complete Project Documentation (PDF)](docs/MAS_Projekt_Przybysz_Krzysztof_S24825.pdf)
- 📊 UML Diagrams (Use Case, Class, Activity, State)
- 🖼️ GUI mockups and screenshots
- 📝 Design decisions and patterns

## 👤 Author

**Krzysztof Przybysz**  
Student ID: s24825  
Polish-Japanese Academy of Information Technology
