.data
vec:
.word 1 #0
.word 1 #1
.word 1 #2
.word 1 #3
.word 1 #4

.text
main:
li $sp, 0x10011000
la $t0, vec
li $t7, 0x1

start:
lw $t1, 0x0($t0)
lw $t2, 0x4($t0)
lw $t3, 0x8($t0)
lw $t4, 0x10($t0)
beq $t7, 0x1, odd

even:
add $t2, $t2, $t1
add $t3, $t3, $t2
add $t4, $t4, $t3
addi $t1, $t1, 0x1
ori $t7, $t7, 0x1
j done

odd:
add $t2, $t2, $t1
add $t3, $t3, $t2
add $t4, $t4, $t3
addi $t1, $t1, 0x1
andi $t7, $t7, 0x0
j done

done:
sw $t1, 0x0($t0)
sw $t2, 0x4($t0)
sw $t3, 0x8($t0)
sw $t4, 0x10($t0)
slti $t5, $t4, 0x1000
beq $t5, 0x1, start

end:
halt
