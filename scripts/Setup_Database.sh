#!/bin/bash

# Step 1: Create the database inside the SQL Server Docker container
echo "Creating database..."
docker exec -it sqlserver /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P verYs3cret -Q "CREATE DATABASE Order_Service;"

# Step 2: Run Liquibase to apply database changes
echo "Running Liquibase migrations..."
./gradlew update

# Step 3: Run test cases
echo "Running test cases..."
./gradlew test

echo "Setup complete."