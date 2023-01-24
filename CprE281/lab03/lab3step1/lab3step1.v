module lab3step1(cabbage, goat, wolf, alarm);
input cabbage, goat, wolf;
output alarm;

not(notCabbage, cabbage);
not(notGoat, goat);
not(notWolf, wolf);

or(f, notCabbage, goat, notWolf);
or(g, cabbage, notGoat, wolf);
and(alarm, f, g);

endmodule