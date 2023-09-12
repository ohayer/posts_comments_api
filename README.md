# Posts and Comments Management API

This project is a Spring Boot-based API for managing posts and their associated comments. It allows you to perform CRUD operations on comments and retrieve posts with their comments sorted by the date of addition in descending order.

## Table of Contents

- [Requirements](#requirements)
- [Getting Started](#getting-started)
  - [Using Docker Compose](#using-docker-compose)
  - [Building the Docker Image](#building-the-docker-image)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)

## Requirements

To run this project, you need the following software installed on your machine:

- Docker
- Docker Compose

## Getting Started

### Using Docker Compose

To quickly set up and run the API along with its required components, follow these steps:

1. Clone the repository to your local machine.

2. Navigate to the root folder of the project.

3. Run the following command to build the Docker image and start the application in detached mode:
docker-compose up -d

The `-d` flag runs the containers in the background.

4. Wait for the application to start. You can monitor the logs using the following command:
docker-compose logs -f

5. Once the application is up and running, you can access the API at `http://localhost:8080`.

### Building the Docker Image

If you make any changes to the application code, you need to rebuild the Docker image before running the application using Docker Compose. Follow these steps:

1. Make sure you have Docker installed on your machine.

2. Navigate to the root folder of the project.

3. Build the Docker image with the following command:
docker build -t posts-comments-api .

The `-t` flag allows you to specify a custom name for the Docker image (in this case, we use `posts-comments-api`).

4. After the image is successfully built, you can start the application using Docker Compose as described in the previous section.

## API Endpoints

The API exposes the following endpoints:

1. **GET /posts**: Retrieve all posts with their associated comments, sorted by the date of addition in descending order.
2. **GET /comments**: Get all the comments.

3. **POST /comments**: Create a new comment for a specific post.

4. **PUT /comments/{commentId}**: Update an existing comment.

5. **DELETE /comments/{commentId}**: Remove a comment.

Please refer to the API documentation or code comments for more details on request/response formats and required parameters for each endpoint.

## Contributing

Contributions to this project are welcome. If you encounter any issues or have suggestions for improvements, please open an issue or submit a pull request.

