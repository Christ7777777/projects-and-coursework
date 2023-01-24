# Test the data forwarding and hazard detection capabilities of the hardware-scheduled pipeline

# Define the data section
.data
# Declare an array with 5 words
arr:
        .word   5 # element 0
        .word   4 # element 1
        .word   3 # element 2
        .word   2 # element 3
        .word   1 # element 4

# Define the text section
.text
# Define the main entry point for the program
main:
    # Set the stack pointer
    li    $sp, 0x10011000

    # Load the address of the array into a register
    la    $t0, arr

# Test the data forwarding capabilities of the pipeline

# IF -> ID dd hazard
    # Load a value from the array into a register
    lw    $t1, 0($t0)

    # Perform an ALU operation using the loaded value
    addi  $t2, $t1, 0x1

# IF -> EX dd hazard
    # Load two values from the array into registers
    lw    $t1, 4($t0)
    lw    $t2, 8($t0)

    # Perform an ALU operation using one of the loaded values
    sll   $t1, $t1, 0x2

# IF -> MEM dd hazard
    # Perform two ALU operations using the loaded values
    add   $t3, $t2, $t2
    add   $t4, $t1, $t1

    # Store the results of the ALU operations in the array
    sw    $t1, 0($t0)
    sw    $t3, 8($t0)

# Test all three types of hazard detection
    # Load three values from the array into registers
    lw    $t1, 0($t0)
    lw    $t2, 4($t0)
    lw    $t3, 8($t0)

    # Perform multiple ALU operations using the loaded values
    add   $t2, $t2, $t1
    add   $t3, $t3, $t2
    addi  $t1, $t1, 0x1

    # Store the results of the ALU operations in the array
    sw    $t2, 4($t0)
    sw    $t3, 8($t0)
    sw    $t1, 0($t0)

# End the program
    halt

