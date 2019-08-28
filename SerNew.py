from socket import *
import time

def process(data):
	out = "[%s]"%time.ctime()
	out += data
	return out


tcpServer = socket(AF_INET,SOCK_STREAM)
HOST = ""
BUFSIZE = 1024

dest = input("Port?(default:12345)>")
if dest:
	PORT = (int)(dest)
else:
	PORT = 12345

ADDR = (HOST,PORT)

SUCCESS = "Connection established successfully!"


tcpServer.bind(ADDR)
tcpServer.listen(5)

while True:
    print("Waiting for connection...")
    clientSocket,clientAddress = tcpServer.accept()
    print("Connection from ",clientAddress)
    print("Connecting...")
    print(SUCCESS)
    clientSocket.send(SUCCESS.encode())
    while True:
    	COMMAND = clientSocket.recv(BUFSIZE).decode()
    	if not COMMAND:
		    continue
    	print("COMMAND RECEIVED!")
    	print("[%s]COMMAND:"%time.ctime(),COMMAND)
    	print("Processing...")
    	COMMAND = process(COMMAND)
    	clientSocket.send(COMMAND.encode())
    	print("Returned")
    	print("")
    
clientSocket.close()
tcpServer.close()
