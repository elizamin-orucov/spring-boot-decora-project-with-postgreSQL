#!/bin/bash

# Define variables
JAR_FILE="auth-server.jar"
TARGET_DIR="target"
LOG_FILE="auth-server.log"

# Function to print messages
print_message() {
    echo "[$(date)] $1"
}

# Run Maven build
print_message "Starting Maven build..."
mvn clean install
if [ $? -ne 0 ]; then
    print_message "Maven build failed. Exiting."
    exit 1
fi
print_message "Maven build completed successfully."

# Run the JAR file in detach mode
print_message "Starting application in detach mode..."
nohup java -jar "$TARGET_DIR/$JAR_FILE" > "$LOG_FILE" 2>&1 &

if [ $? -ne 0 ]; then
    print_message "Failed to start application. Exiting."
    exit 1
fi

print_message "Application started successfully in detach mode."
print_message "Script execution finished."
