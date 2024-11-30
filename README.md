# Concurrent and Distributed Snake Game

The **Concurrent and Distributed Snake Game** is a project that reimagines the classic Snake game by integrating principles of concurrent and distributed programming. This implementation uses threads for controlling the movements of snakes, thread pools for managing dynamic obstacles, and synchronization mechanisms like **Locks** and **synchronized blocks** to ensure safe and efficient multi-threaded operations. Additionally, a "Human Snake" mode allows users to play and interact with the game.

## Features

- **Concurrent Programming**:
  - **Threads**: Each snake is managed as an independent thread.
  - **Thread Pools**: Efficient management of dynamic obstacles using thread pools.
  - **Synchronization**: Ensures thread safety using `Locks` and `synchronized` blocks for critical sections.
- **Distributed Architecture**:
  - Supports distributed gameplay via remote connections using RMI (Remote Method Invocation).
- **Interactive Gameplay**:
  - "Human Snake" mode for user-controlled gameplay.
  - Dynamic obstacles that increase the game's difficulty.
- **Graphical Interface**:
  - A responsive and intuitive GUI built with Java Swing.

## Technologies Used

- **Java Threads**: Handles concurrent snake movements.
- **Locks and Synchronized Blocks**: Ensures thread-safe operations on shared resources.
- **Thread Pools**: Manages obstacle generation and updates efficiently.
- **RMI (Remote Method Invocation)**: Enables distributed gameplay.
- **Java Swing**: Provides a graphical interface for an enhanced user experience.

## Requirements

- Java Development Kit (JDK) 8 or higher
- An IDE like Eclipse or IntelliJ IDEA

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/concurrent-distributed-snake-game.git
