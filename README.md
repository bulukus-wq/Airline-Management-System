# ✈️ Airline Management System (Java OOP Project)

The main objective of this project is to implement a comprehensive **Airline Reservation and Management System** using the Java programming language. This project demonstrates the practical application of **Object-Oriented Programming (OOP)** principles such as Encapsulation, Inheritance, Polymorphism, and Abstraction. Additionally, modern software development practices, including **Unit Testing (JUnit)** and **Multithreading**, have been integrated to simulate real-world scenarios.


## 1.System Architecture
The system is divided into four main modules. The details of the classes and their
responsibilities are explained below.

### 1.1. Person Module (Inheritance Structure)
An abstract base class is used to manage common attributes for people in the system.
• **Person (Abstract):** The parent class containing common fields: id, name, surname,
and contactInfo.
• **Passenger:** Inherits from Person. Represents the users who book flights.
• **Staff:** Inherits from Person. Represents authorized personnel who manage the
system.

### 1.2. Flight and Plane Module
This module handles the core operational data.
• **Flight:** Represents a specific scheduled flight. It contains a flightNum, date, hour,
duration, and references to a Plane and a Route.
• **Plane:** Represents the aircraft. It manages the seatMatrix (a 2D array of Seat
objects) and stores planeID, planeModel, and capacity.
• **Seat:** Represents an individual seat. It holds the seatNum, price, reserveStatus
(boolean), and SeatClass (Enum).
• **Route:** Stores the departure and arrival location strings.

### 1.3. Reservation and Ticketing Module
• **Reservation:** Associates a Passenger with a specific Flight and Seat. It contains
a unique reservationCode, records the dateOfReservation, and specifies the
passenger's baggage allowance.
• **Ticket:** The final document issued to the passenger that encapsulates a Reservation
and Baggage details. It automatically calculates the ticket price and determines the
baggage allowance based on the seat class.
• **Baggage:** Represents the baggage weight associated with a ticket.

### 1.4. Management and Utility Module (Singleton & Utility)
This module acts as the controller layer, managing lists of objects and business logic.
• **FlightManager (Singleton)**: Manages the lifecycle of Flight objects using the
Singleton pattern. It provides comprehensive CRUD capabilities, including
createFlight, updateFlight, and deleteFlight. The deleteFlight method
ensures data integrity by automatically triggering the cancellation of associated
reservations via ReservationManager. Additionally, the searchFlights method
utilizes Java Streams to filter flights by route and strictly excludes past flights based
on the current system time.
• **ReservationManager (Singleton):** The centralized controller for handling all booking
operations. It utilizes synchronized methods to ensure thread safety during
concurrent reservation attempts (makeReservation, cancelReservation). It also
manages the persistence of booking data via FileManager and includes specific
functionality to bulk-cancel reservations (cancelReservationsByFlight)
automatically if a flight is removed from the system.
• **StaffManager (Singleton):** Manages the list of authorized personnel using the
Singleton design pattern. It provides full CRUD capabilities (addStaff, updateStaff,
deleteStaff) for staff administration. It ensures data persistence by automatically
saving changes to the file system via FileManager immediately after any modification
to the staff list.
• **FileManager (Utility):** A static utility class responsible for data persistence using text
files (flights.txt, reservations.txt, staff.txt). It manages the input/output
operations for Flight, Reservation, and Staff objects using a semicolon-
separated format. Additionally, it includes specific logic (syncSeatOccupancy) to
ensure seat availability is correctly mapped to flight objects upon system initialization.
• **CalculatePrice:** A utility class containing pricing constants and baggage limits
(BUSINESS_BAGGAGE_LIMIT, EXTRA_BAGGAGE_FEE, etc.). It implements the business
logic to calculate the total ticket price, dynamically adding extra fees if the
passenger's baggage weight exceeds the allowance for the selected seat class.


## 2. Multithreading Scenarios
2.1. Scenario 1: Simultaneous Seat Reservation (Concurrency Control)
This scenario simulates 90 passengers attempting to book seats simultaneously on a plane
with 180 seats (30 rows x 6 seats).
• Implementation: Each passenger selection is handled by a separate thread.
• Synchronized Mode: By using synchronization, we ensured that exactly 90 seats are
occupied without any race conditions or data inconsistency.
• Non-Synchronized Mode: Without synchronization, the simulation results in incorrect
seat placements and inconsistent data.
4.2. Scenario 2: Asynchronous Report Generation (Asynchronous Task)
A resource-intensive reporting process (calculating flight occupancy rates) was implemented
to run in the background without freezing the user interface.
• Implementation: A dedicated Thread is created within the AdminPanel. This thread
iterates through all flights, simulating heavy processing with Thread.sleep(). To
ensure thread safety, all UI updates (modifying the progress bar and text area) are
executed using SwingUtilities.invokeLater(), preventing the main GUI thread
from blocking while the report is being generated.

## 🚀 Özellikler
* **Uçuş Yönetimi:** Uçuşların eklenmesi, listelenmesi ve takibi.
* **Rezervasyon Sistemi:** Yolcu kayıtları ve bilet işlemlerinin yönetimi.
* **Personel Takibi:** Havayolu çalışanlarının sistem üzerinden yönetimi.
* **Dosya Yönetimi:** Verilerin `.txt` dosyaları üzerinden kalıcı olarak saklanması.

## 🛠 Kullanılan Teknolojiler
* **Dil:** Java
* **Yapı:** OOP (Encapsulation, Inheritance, Polymorphism)
* **Araçlar:** Git, GitHub, Maven


* ## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).



