DEPS = message.h

all:	lab1 message.o lab1.o

lab1: lab1.o message.o
	gcc lab1.o message.o -o lab1

messsage.o: message.c $(DEPS)
	gcc -c message.c

lab1.o: lab1.c $(DEPS)
	gcc -c lab1.c

clean:
	rm -f message.o lab1.o         
