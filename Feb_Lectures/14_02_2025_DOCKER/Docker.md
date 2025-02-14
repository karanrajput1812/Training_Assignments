# Docker Commands Cheat Sheet

## Information and Listing
- `docker info`: Display system-wide information about Docker.
- `docker images`: List all Docker images on the system.
- `docker ps -a`: List all containers (running and stopped).

## Managing Images and Containers
- `docker pull redis`: Download the Redis image from Docker Hub.
- `docker create <img_name>`: Create a new container from the specified image (e.g., redis).
- `docker start <container_id>`: Start a stopped container using its ID.
- `docker exec -i -t <container_id> sh`: Execute a shell session inside the running container.

## Stopping and Removing Containers
- `docker stop <container_id>`: Stop a running container (sends SIGTERM and SIGKILL).
- `docker rm <container_id>`: Remove a stopped container.
- `docker rmi <img_name>`: Remove an image from the system.

## Running and Building Images
- `docker run redis`: Run a new container from the Redis image.
- `docker build . -t <docker-user-id>/mycustomjava`: Build a Docker image from the Dockerfile in the current directory and tag it.

## Pushing and Authentication
- `docker push <docker-user-id>/mycustomjava`: Push the tagged image to Docker Hub.
- `docker login`: Log in to Docker Hub to access private repositories.
