#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#define htonll(value) ((((uint64_t)htonl(value & 0xFFFFFFFF)) << 32LL) | htonl(value >> 32))

#define PORT 7777

int createServerSocket(void);
void closeSocket(void);
void handleChar(int client_socket);
void handleShort(int client_socket);
void handleInt(int client_socket);
void handleLong(int client_socket);
void iteration(void);

int server_socket;

int main(int argc, char *argv[]) {
  int client_socket;
  int message_size;

  if (argc != 2) {
    printf("Usage: <message size in bytes> {1, 2, 4, 8}\n", argv[0]);
    return 1;
  }

  message_size = atoi(argv[1]);
  if (message_size != 1 && message_size != 2 && message_size != 4 && message_size != 8) {
    printf("Usage: <message size in bytes> {1, 2, 4, 8}\n", argv[0]);
    return -1;
  }
  
  atexit(closeSocket);
  server_socket = createServerSocket();

  while (1) {
    iteration();
  }

  return 0;
}

void handleChar(int client_socket) {
  uint8_t buff;
  recv(client_socket, &buff, 1, 0);
  printf("Received: %d (char)\n", buff);
  buff++;
  send(client_socket, &buff, 1, 0);
}

void handleShort(int client_socket) {
  uint16_t buff;
  recv(client_socket, &buff, 2, 0);
  buff = ntohs(buff);
  printf("Received: %d (short)\n", buff);
  buff++;
  buff = htons(buff);
  send(client_socket, &buff, 2, 0);
}

void handleInt(int client_socket) {
  uint32_t buff;
  recv(client_socket, &buff, 4, 0);
  buff = ntohl(buff);
  printf("Received: %d (int)\n", buff);
  buff++;
  buff = htonl(buff);
  send(client_socket, &buff, 4, 0);
}

void handleLong(int client_socket) {
  uint64_t buff;
  recv(client_socket, &buff, 8, 0);
  buff = htonll(buff);
  printf("Received: %lld (long)\n", buff);
  buff++;
  buff = htonll(buff);
  send(client_socket, &buff, 8, 0);
}

void handleMessage(int client_socket) {
  if (message_size == 1) {
    handleChar(client_socket);
  } else if (message_size == 2) {
    handleShort(client_socket);
  } else if (message_size == 4) {
    handleInt(client_socket);
  } else if (message_size == 8) {
    handleLong(client_socket);
  }  
}

void iteration() {
  struct sockaddr_in client_address;
  socklen_t client_address_len = sizeof(client_address);

  client_socket = accept(server_socket, (struct sockaddr *)&client_address, &client_address_len);
  
  handleMessage(clientSocket);

  close(client_socket);
}

void closeSocket(void) {
  if (socket > 0) {
    close(server_socket);
  }
}

int createServerSocket(void) {
  int server_socket;
  struct sockaddr_in server_address;

  if ((server_socket = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
    printf("Failure opening server socket\n");
    exit(9);
  }
  
  memset(&server_address, 0, sizeof(server_address));
  server_address.sin_family = AF_INET;
  server_address.sin_addr.s_addr = htonl(INADDR_ANY);
  server_address.sin_port = htons(PORT);

  if (bind(server_socket, (struct sockaddr *)&server_address, sizeof(server_address)) == -1) {
    perror("Failure binding socket\n");
    exit(10);
  }

  if (listen(server_socket, 5) == -1) {
    perror("Could not start listening on socket");
    close(server_socket);
    exit(11);
  }

  return server_socket;
}
