module lab3step3(farmer, cabbage, goat, wolf, alarm);
input farmer, cabbage, goat, wolf;
output alarm;

not(notFarmer, farmer);
not(notCabbage, cabbage);
not(notGoat, goat);
not(notWolf, wolf);

and(a,farmer, notGoat, notWolf);
and(b, farmer, notCabbage, notGoat);
and(c, notFarmer, goat, wolf);
and(d, notFarmer, cabbage, goat);
or(alarm, a, b, c, d);

endmodule