.data

.text

main:

li $s0, 5 # Set the call depth to 5
jal func1 # Jump to func1

func1:

li $s1, 1 # Set the return value to 1
beq $s0, $zero, end # If the call depth is 0, jump to end
addi $s0, $s0, -1 # Decrement the call depth
jal func2 # Jump to func2
j end # Jump to end

func2:

li $s2, 2 # Set the return value to 2
beq $s0, $zero, end # If the call depth is 0, jump to end
addi $s0, $s0, -1 # Decrement the call depth
jal func3 # Jump to func3
j end # Jump to end

func3:

li $s3, 3 # Set the return value to 3
beq $s0, $zero, end # If the call depth is 0, jump to end
addi $s0, $s0, -1 # Decrement the call depth
jal func4 # Jump to func4
j end # Jump to end

func4:

li $s4, 4 # Set the return value to 4
beq $s0, $zero, end # If the call depth is 0, jump to end
addi $s0, $s0, -1 # Decrement the call depth
jal func5 # Jump to func5
j end # Jump to end

func5:

li $s5, 5 # Set the return value to 5
beq $s0, $zero, end # If the call depth is 0, jump to end
addi $s0, $s0, -1 # Decrement the call depth
j end # Jump to end

end:

li $v0, 10 # Exit the program
syscall
halt
