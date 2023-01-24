module lab3step2(cabbage, goat, wolf, alarm);
input cabbage, goat, wolf;
output alarm;

assign alarm = ((~cabbage | goat | ~wolf) & (cabbage | ~goat | wolf));

endmodule