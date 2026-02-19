# ✈️ Airline Management System (Java OOP Project)

The main objective of this project is to implement a comprehensive **Airline Reservation and Management System** using the Java programming language. This project demonstrates the practical application of **Object-Oriented Programming (OOP)** principles such as Encapsulation, Inheritance, Polymorphism, and Abstraction. Additionally, modern software development practices, including **Unit Testing (JUnit)** and **Multithreading**, have been integrated to simulate real-world scenarios.


## 🛠 System Architecture
[cite_start]The application is structured into four main modules to ensure modularity and clean code separation[cite: 96]:

* [cite_start]**Person Module (Inheritance):** Utilizes an abstract `Person` class to manage shared attributes for `Passenger` and `Staff`[cite: 97, 98, 99].
* [cite_start]**Flight & Plane Module:** Manages the core operational data, including aircraft models, 2D seat matrices, and flight schedules[cite: 102, 104, 105].
* [cite_start]**Reservation & Ticketing Module:** Handles booking logic, unique reservation codes, and dynamic price calculation based on baggage weight[cite: 108, 109, 110, 111].
* [cite_start]**Management & Utility Module:** Implements the **Singleton** pattern for `FlightManager`, `ReservationManager`, and `StaffManager` to ensure centralized control and thread safety[cite: 114, 116, 121, 124].

## 🧵 Multithreading Scenarios
[cite_start]Two advanced scenarios were implemented to simulate real-world system behavior[cite: 132]:

1. [cite_start]**Simultaneous Seat Reservation:** Simulates 90 passengers attempting to book seats concurrently on a 180-seat aircraft[cite: 134, 135]. [cite_start]It employs **synchronized** methods to prevent race conditions and ensure data consistency[cite: 136].
2. [cite_start]**Asynchronous Report Generation:** Background thread execution for resource-intensive reporting (e.g., occupancy rates) to keep the GUI responsive during processing[cite: 138, 139, 140, 142].


## 🧪 Testing and Persistence
* [cite_start]**Unit Testing:** 10 JUnit 5 tests were implemented to validate price calculation logic, flight search engine filtering (excluding past flights), and seat management reliability[cite: 164, 165, 168, 171].
* [cite_start]**Data Storage:** Uses structured text files (`flights.txt`, `reservations.txt`, `staff.txt`) with semicolon-separated formatting for persistent storage without requiring a database[cite: 145, 148, 149, 153].

## 🖥️ Graphical User Interface (GUI)
[cite_start]The project features a user-friendly interface developed using **Java Swing**[cite: 174]:
* [cite_start]**CardLayout Navigation:** Seamless transitions between login, search, and management screens[cite: 175].
* [cite_start]**Dynamic Seat Map:** Visual color-coding (Magenta: Business, Green: Economy, Red: Occupied)[cite: 181].
* [cite_start]**Admin Dashboard:** Secure dual-access authentication for managing flights and staff records[cite: 178, 185, 186].

