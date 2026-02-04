# Quantum Mart (backend)
This repository holds the backend services needed to run QuantumMart, a fictional shopping website built in Spring Boot, Angular, and PostGreSQL.

## Dev Setup
Before installation, ensure you have [PostGreSQL](https://www.postgresql.org/download/) downloaded on your machine. It is recommended you install [pgadmin](https://www.pgadmin.org/download/) 
to manage the local database.

1. Clone this repository:
```
https://github.com/MattCumbo99/qmart.git
```
2. Setup your PostgreSQL database using the schemas provided in `src/main/resources/db/migration/`.
3. Create a run configuration for the application. There are three environment variables you must provide:
```
DB_URL=jdbc:postgresql://localhost:5432/qmartdb
DB_USERNAME=<db username>
DB_PASSWORD=<db password>
```
4. Run the application. If there are no exceptions, then you have successfully set up the backend services. For troubleshooting help, contact a repo admin.
