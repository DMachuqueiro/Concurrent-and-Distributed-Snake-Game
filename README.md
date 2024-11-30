# Concurrent and Distributed Snake Game

The **Concurrent and Distributed Snake Game** is a project that reimagines the classic Snake game by integrating principles of concurrent and distributed programming. This implementation uses threads for controlling the movements of the snakes and thread pools for managing dynamic obstacles, demonstrating advanced Java programming concepts. Additionally, a "Human Snake" mode allows users to play and interact with the game.

## Features

- **Concurrent Programming**:
  - Snakes are managed as independent threads, enabling smooth and parallel movement.
  - Obstacles are generated and updated using thread pools for efficient resource management.
- **Distributed Architecture**:
  - The game supports a distributed setup with remote connections.
  - Players can interact with remote boards via client-server communication.
- **Interactive Gameplay**:
  - "Human Snake" mode allows the user to control the snake manually.
  - Dynamic obstacles add complexity and challenge to the game.
- **Graphical Interface**:
  - A clean and interactive GUI built with Java Swing.

## Technologies Used

- **Java Threads**: For handling snake movements.
- **Thread Pools**: For obstacle management.
- **RMI (Remote Method Invocation)**: For distributed board control in remote gameplay.
- **Java Swing**: For building the graphical user interface.

## Requirements

- Java Development Kit (JDK) 8 or higher
- An IDE like Eclipse or IntelliJ IDEA

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/concurrent-distributed-snake-game.git
