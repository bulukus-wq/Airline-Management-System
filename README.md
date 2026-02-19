# ✈️ Airline Management System (Java OOP Project)

The main objective of this project is to implement a comprehensive **Airline Reservation and Management System** using the Java programming language. This project demonstrates the practical application of **Object-Oriented Programming (OOP)** principles such as Encapsulation, Inheritance, Polymorphism, and Abstraction. Additionally, modern software development practices, including **Unit Testing (JUnit)** and **Multithreading**, have been integrated to simulate real-world scenarios.


## 🛠 System Architecture
The application is structured into four main modules to ensure modularity and clean code separation:

* **Person Module (Inheritance):** Utilizes an abstract `Person` class to manage shared attributes for `Passenger` and `Staff`.
* **Flight & Plane Module:** Manages the core operational data, including aircraft models, 2D seat matrices, and flight schedules.
* **Reservation & Ticketing Module:** Handles booking logic, unique reservation codes, and dynamic price calculation based on baggage weigh.
* **Management & Utility Module:** Implements the **Singleton** pattern for `FlightManager`, `ReservationManager`, and `StaffManager` to ensure centralized control and thread safety.

## 🧵 Multithreading Scenarios
Two advanced scenarios were implemented to simulate real-world system behavior:

1. **Simultaneous Seat Reservation:** Simulates 90 passengers attempting to book seats concurrently on a 180-seat aircraft. It employs **synchronized** methods to prevent race conditions and ensure data consistency.
2. **Asynchronous Report Generation:** Background thread execution for resource-intensive reporting (e.g., occupancy rates) to keep the GUI responsive during processing.


## 🧪 Testing and Persistence
* **Unit Testing:** 10 JUnit 5 tests were implemented to validate price calculation logic, flight search engine filtering (excluding past flights), and seat management reliability.
* **Data Storage:** Uses structured text files (`flights.txt`, `reservations.txt`, `staff.txt`) with semicolon-separated formatting for persistent storage without requiring a database.

## 🖥️ Graphical User Interface (GUI)
The project features a user-friendly interface developed using **Java Swing**:
* **CardLayout Navigation:** Seamless transitions between login, search, and management screens.
* **Dynamic Seat Map:** Visual color-coding (Magenta: Business, Green: Economy, Red: Occupied).
* **Admin Dashboard:** Secure dual-access authentication for managing flights and staff records.

## 🔐 Access Credentials
The system features a dual-access authentication mechanism to separate administrative tasks from passenger bookings:

* **Management Panel (Admin):**
    * **Username:** `admin`
    * **Password:** `123`
* **Staff Access:** Registered personnel can also access the management panel by entering their specific **Staff Name** and **Staff ID**.
* **Booking Panel (Passenger):** Any credentials entered other than the administrative ones listed above will automatically route the user to the passenger booking interface.
