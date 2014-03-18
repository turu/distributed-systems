#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <stdio.h>

#define DEFAULT_PORT 7777

int send_file(FILE * file, int client_socket);

int main(int argc, char *argv[]) {
  if (argc != 3) {
    printf("Usage: <host addr> <file to transfer>\n");
    return -1;
  }

  FILE * file = fopen(argv[2], "rb");
  if (file < 0) {
    printf("Failure opening file %s\n", argv[2]);
    exit(1);
  }
  int client_socket;
  if ((client_socket = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
    printf("Failure opening socket\n");
    exit(2);
  }
  struct sockaddr_in server_address;
  
  memset(&server_address, 0, sizeof(server_address));
  server_address.sin_family = AF_INET;
  server_address.sin_addr.s_addr = inet_addr(argv[1]);
  server_address.sin_port = htons(DEFAULT_PORT);

  if (connect(client_socket, (struct sockaddr *)&server_address, sizeof(server_address)) < 0) {
    printf("Failure connecting to file server\n");
    close(client_socket);
    exit(3);
  }

  if (send_file(file, client_socket) == -1) {
    printf("Failure sending file to remote server\n");
    close(client_socket);
    fclose(file);
    exit(4);
  }

  close(client_socket);
  fclose(file);
  return 0;
}

int get_file_length(FILE * file) {
  int ret= 0;
  fseek(file, 0L, SEEK_END);
  ret = ftell(file);
  fseek(file, 0L, SEEK_SET);
  return ret;
}

int send_file(FILE * file, int client_socket) {
  int total_sent = 0;
  char buffer[1024];

  int length = get_file_length(file);
  int rlength = htonl(length);
  send(client_socket, &rlength, 4, 0);
  
  while (total_sent < length) {
    int read = fread(buffer, 1, 1024, file);
    
    if (send(client_socket, buffer, read, 0) != read) {
      printf("Failure sending file chunk.\n");
      return -1;
    }
    
    total_sent += read;
  }

  return total_sent;
}
