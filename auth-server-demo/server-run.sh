#!/bin/bash

# Define variables
JAR_FILE="auth-server.jar"
TARGET_DIR="target"
#REMOTE_USER="my-course"
#REMOTE_DIR="~/workspace/backend-workspace/elizamin"

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

# Rsync the JAR file to the remote server
print_message "Starting file transfer..."
# linux ve unix ucun bu isledilmelidi
#rsync -aP "$TARGET_DIR/$JAR_FILE" "$REMOTE_USER:$REMOTE_DIR"
# windows ucun bu - scp
scp "$TARGET_DIR/$JAR_FILE" "$REMOTE_USER:$REMOTE_DIR"
if [ $? -ne 0 ]; then
    print_message "File transfer failed. Exiting."
    exit 1
fi
print_message "File transfer completed successfully."

print_message "Script exaecution finished."