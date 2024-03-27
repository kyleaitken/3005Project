# 3005Project

Kyle Aitken
COMP 3005 Project (Group 39)
101280013

This application simulates a Health & Fitness Club Management System that provides various functions for members, trainers, and administrative users.

## Prerequisites

Ensure you have the following installed on your machine:

- Java Development Kit 17 (JDK)
- PostgreSQL (and PgAdmin or any other PostgreSQL GUI)
- Node.js (which includes npm)
- (Optional) React Developer Tools for Chrome/Firefox for debugging React applications

### Clone the Repository

```bash
git clone https://github.com/kyleaitken/COMP3005Project.git
```

## Install Dependencies

### Backend Dependencies

The project uses Gradle to manage its dependencies. To install them, simply run:

```bash
./gradlew build
```

This command will download and install all the necessary dependencies defined in `build.gradle`.

### Frontend Dependencies

With the terminal open, navigate from the root directory to:

```bash
cd src/main/frontend/client
```

Then, install the dependencies using npm:

```bash
npm install
```

This command reads the `package.json` file and installs all the required npm packages.

## Database Setup

1. Create a PostgreSQL database.
2. Update the url, user, and password variables in the `3005Project/src/main/resources/application.properties` file with your PostgreSQL credentials.
3. Create the relations in your database by executing the DDL queries in the `3005Project/SQL/ddl.sql` file.
4. Add the trigger functions by executing the scripts in the `3005Project/SQL/triggerFunctions.sql` file.
5. Add data to the database by executing the DML queries in the `3005Project/SQL/dml.sql` file.

## Compiling and Running with Gradle

To compile and run the backend part of the project, navigate back to the root directory of the project and run:

```bash
./gradlew bootRun
```

This command compiles the Java code and starts the Spring Boot application.

## Running the Frontend

With the backend running, open a new terminal window or tab and navigate to the frontend directory at 3005Project/src/main/frontend/client. Then, start the frontend application with:

```bash
npm run start
```

This command starts the React application, usually available at `http://localhost:3000` by default.

## Demonstration Video Link:

https://youtu.be/R3tTTdU8sXI
