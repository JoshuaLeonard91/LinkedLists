# Linked Lists

## Overview
The **Linked Lists** project demonstrates:
- Implementation of a stack using a double-ended singly linked list.
- Implementation of a priority queue using a sorted doubly linked list.
- Data parsing from a CSV file and categorization based on COVID Death Rates (DR).
- Dynamic deletion of priority queue elements based on user-defined intervals.

This project processes country data, implements linked list-based data structures, and provides user interaction via a menu system.

## Objectives
- Reuse the `Country` class from previous projects (with necessary modifications).
- Implement a stack using a singly linked list with efficient operations.
- Implement a priority queue using a doubly linked list, prioritizing by COVID Death Rate.
- Allow user interaction for managing and querying data using a menu-driven interface.

## Features
1. **Country Class**:
   - Fields: `name`, `capital`, `population`, `GDP`, `COVID cases`, `COVID deaths`, and `area`.
   - Methods:
     - Getters and setters for all fields.
     - Constructor for initialization.
     - Method to compute derived fields like CFR, Case Rate, Death Rate, GDP per capita, and Population Density.
     - Method to print a formatted `Country` object.

2. **Stack Class**:
   - Implements a stack using a singly linked list.
   - Operations:
     - `push`: Adds a `Country` object to the top of the stack (O(1)).
     - `pop`: Removes and returns a `Country` object from the top of the stack (O(1)).
     - `printStack`: Recursively prints the stack from top to bottom without modifying it.
     - `isEmpty`: Checks if the stack is empty.

3. **PriorityQ Class**:
   - Implements a priority queue using a sorted doubly linked list, prioritizing by COVID Death Rate (ascending order).
   - Operations:
     - `insert`: Inserts a `Country` object into the queue in the correct position based on DR (O(N)).
     - `remove`: Removes and returns the `Country` object with the lowest DR (O(1)).
     - `intervalDelete`: Deletes countries with DRs within a given interval. Returns `true` if any were deleted, `false` otherwise.
     - `printPriorityQ`: Recursively prints the priority queue from highest to lowest priority without modifying it.
     - `isEmpty`: Checks if the priority queue is empty.

4. **Country Groups**:
   - Based on COVID Death Rate:
     - **EXCELLENT**: DR < 20.
     - **VGOOD**: 20 ≤ DR < 100.
     - **GOOD**: 100 ≤ DR < 200.
     - **FAIR**: 200 ≤ DR < 350.
     - **POOR**: DR ≥ 350.

5. **Project3 Class**:
   - Main class managing the program's flow and user interaction.
   - Reads data from `Countries3.csv` and filters countries into the stack based on DR categories (**FAIR**, **GOOD**, and **VGOOD** only).
   - Transfers data from the stack to the priority queue.
   - Menu options:
     1. Enter a DR interval for deletions in the priority queue.
     2. Print the priority queue.
     3. Exit the program.

## Requirements
- **Java Development Kit (JDK) 8 or higher**
- Input File: `Countries3.csv` (containing country data)

## Implementation Details
### Stack:
1. **Constructor**:
   - Initializes an empty singly linked list.
2. **Operations**:
   - `push`: Adds a `Country` object to the top of the stack.
   - `pop`: Removes and returns the top `Country` object from the stack.
   - `printStack`: Recursively prints the stack.
   - `isEmpty`: Checks if the stack is empty.

### Priority Queue:
1. **Constructor**:
   - Initializes an empty doubly linked list.
2. **Operations**:
   - `insert`: Adds a `Country` object into the sorted queue based on DR.
   - `remove`: Removes and returns the `Country` object with the lowest DR.
   - `intervalDelete`: Deletes countries with DRs within the given range.
   - `printPriorityQ`: Recursively prints the priority queue.
   - `isEmpty`: Checks if the priority queue is empty.

### User Menu:
- Provides the following options:
  1. Enter a DR interval for deletions in the priority queue.
  2. Print the priority queue.
  3. Exit the program.

### Report Formatting:
- Example Stack or Priority Queue Report:
  ```plaintext
  Stack Contents:
  Burundi Bujumbura 0.005052 414.254 0.126 427.204

 
