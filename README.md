# Hotel Room Booking System

A simple Java project I built to practice OOP concepts by creating a small hotel room booking system.

The program manages hotel rooms, customers, and their bookings.

## What the program can do

- Add hotel rooms
- Add customers
- Display all rooms
- Display available rooms
- Search for rooms and customers
- Book rooms
- Cancel bookings
- Search for bookings
- Display a customer's bookings
- Display all bookings
- Calculate the total booking price

## OOP concepts I practiced

This project helped me practice:

- **Encapsulation** – Room, Customer, and Booking data is kept private and accessed through methods
- **Object Relationships** – Bookings connect customers with rooms, while the Hotel manages rooms, customers, and bookings
- **Method Overriding** – `toString()` is overridden to display object details
- **Enum** – `BookingStatus` is used to represent BOOKED and CANCELLED states
- **Static Members** – A static variable is used to generate unique booking IDs

## Classes

### Room

Stores room details such as room number, room type, price, and availability. It also handles booking and cancellation.

### Customer

Stores customer details such as customer ID and customer name.

### Booking

Connects a customer with a room and stores the number of nights and booking status. It also calculates the total booking price.

### Hotel

Manages rooms, customers, bookings, and the main room booking and cancellation operations.

### BookingStatus

An enum used to represent the status of a booking:

- `BOOKED`
- `CANCELLED`

## Room Types

- **Single** – ₹1200/day
- **Double** – ₹1800/day
- **Suite** – ₹2400/day

## Example

The program creates rooms and customers, books rooms for different customers, displays the bookings, and demonstrates booking cancellation.

## Built With

Java  
NetBeans
