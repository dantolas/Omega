

# Gridster -- Pathfinding algorhitm REST API and Visualization
## School project for SPŠE Ječná (Střední průmyslová škola elektrotechnická, Praha 2, Ječná 30)
## AUTHOR: Kuta Samuel 
## [Github Repo](https://github.com/dantolas/PV-PSS_Alfa4_2024)

## Table of contents (TOC)
==========================
1. [Introduction](#introduction)
2. [Requirements](#requirements)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Configurations](#configurations)
6. [Docs](#docs)
7. [Architecture and design patterns](#architecture-and-design-patterns)
8. [Application runtime behavior](#app-runtime-behaviour)
9. [Dependencies](#dependencies)
9. [TODO](#todo)

## Introduction  
# What is Gridster and why should I care?

Great question. Gridster is a tool that provides a way to help you with solving complex character mazes, educate about pathfinding algorithms and understand them better, or to just entertain yourself a bit with its visuals.

## Mazes?

The mazes we're talking about are essentially matrices containing characters, such as this one:

<div class="grid items-center justify-center">
    <div><span>#</span><span>#</span><span>#</span><span>#</span><span>.</span><span>#</span><span>#</span><span>.</span><span>.</span><span>x</span></div>
    <div><span>#</span><span>#</span><span>#</span><span>x</span><span>.</span><span>.</span><span>.</span><span>.</span><span>.</span><span>#</span></div>
    <div><span>#</span><span>.</span><span>.</span><span>.</span><span>.</span><span>#</span><span>S</span><span>.</span><span>#</span><span>#</span></div>
    <div><span>#</span><span>.</span><span>#</span><span>#</span><span>.</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span></div>
    <div><span>#</span><span>.</span><span>#</span><span>#</span><span>.</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span></div>
    <div><span>#</span><span>.</span><span>#</span><span>#</span><span>.</span><span>.</span><span>.</span><span>E</span><span>#</span><span>#</span></div>
    <div><span>#</span><span>.</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span></div>
    <div><span>#</span><span>.</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span></div>
    <div><span>x</span><span>x</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span></div>
    <div><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span></div>
</div>

# Who is Gridster for?

## Developers

If you are a developer and you need to solve mazes in your applications, Gridster provides a REST API that can not only solve a Matrix for you, but also provide you with additional information, and let you pick one of our pathfinding algorithms.

## Students and Enthusiasts

If you are an IT student or just interested in pathfinding algorithms, Gridster provides a way for you to build your own Matrix, pick a pathfinding algorithm, and see how it solves the Matrix for you. You can also see how the Matrix is searched and what the shortest path is, all with nice colorful animations that you can adjust.

# Why was Gridster created?

Gridster was created as a School graduation project for SPŠE Ječná in my 4th year of study there. It's supposed to make Pathfinding algorithms in Graph theory more approachable and easier to understand.

# How was it made?

To not go too into detail, Gridster's backend API is built in Java 21 using Spring Boot. The visual side was built using Vue 3.

# Open source?

Gridster is an open-source project, and if you are interested you can visit the [Github Repository](https://github.com/dantolas/Omega) containing all the source code.
## Requirements
`Java` - version *21*+
## Installation
- Clone this repository from the command line
`git clone address <directory>`

Or download the entire repository as a zip file
See [Requirements](#requirements)

## Usage
- Navigate to the project directory and execute this command:
`java -jar Gridster.jar`
- The application will start and u will see console output informing u about the program runtime
behavior. 


- After u start the program u can access the [Webpage](http://localhost:8000/)


## Configurations
- Database endpoint for User information can be configured in **conf/config.json**.
- Application uses a MySQL Driver, so it can be connected to a MySQL database
- Make sure this file exists and it's structure complies with the examples below.

- **config** file example:


```
{
    "database_host":"#",
    "database_name":"#",
    "database_user":"charming",
    "database_password":"kuta"
}
```

- **database_host**: Hostname to connect to
- **database_name**: Name of the end database
- **database_user**: Username of the database connection
- **database_password**: Password of the database connection

## Docs
- **Developer documentation** 
    - Entire source code is documented, and additional documentation can be found here or on the 
    web application
- **User documentation**
    - All the information a user might need can be found on the website

## Architecture and design patterns
- The entire application is developed in Java v20.
- ## Web Application
    - App uses Spring boot web framework for creating a simple tomcat web server.
    - Spring boot by default uses a MVC architecture, so that is also used in this application.
- ## TCP/UDP
    - Both TCP and UDP communication is done with builtin Java libraries, in a P2P style with all 
information being contained in RAM.
    
## App runtime behaviour
- The application first initializes and sets everything up.
- Then it will periodically broadcast UDP packets to an IPv4 broadcast address.
- If it receives a valid response or detects the same broadcast from someone else, a 2 way TCP
connection is attempted.
- Information about UDP and TCP behavior is shown in console during runtime, and the webserver
contains information about message history and established connections (refresh page to refresh info)

## Dependencies
- Google.com GSON Json parsing and serialization tool. See [GSON](https://github.com/google/gson) 
- Spring Boot web framework. See [SpringBoot](https://spring.io/projects/spring-boot)
- Vue js 3 web JS framework for frontend development. See [Vue](https://vuejs.org/)

## Todo
Future developments  and updates
- More algorhitms
    - Implementing more pathfinding algorhitms for the REST API and visualization
- Cleaning up the UI
    - Make some UI changes for better feel and look
- Provide more visually appealing information
    - Make information more concise, easy to read and easy to get through
- Mobile responsivity
    - The Web application is currently not very responsive, improve responsivity
