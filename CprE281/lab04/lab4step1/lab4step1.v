module lab4step1(cabbage, goat, wolf, farmer, alarm);
input farmer, cabbage, goat, wolf;
output alarm;
reg alarm;

always @(farmer or cabbage or goat or wolf)
begin

   case({farmer, cabbage, goat, wolf})


4'b0000 : alarm = 'b0;
4'b0001 : alarm = 'b0;
4'b0010 : alarm = 'b0;
4'b0011 : alarm = 'b1;
4'b0100 : alarm = 'b0;
4'b0101 : alarm = 'b0;
4'b0110 : alarm = 'b1;
4'b0111 : alarm = 'b1;
4'b1000 : alarm = 'b1;
4'b1001 : alarm = 'b1;
4'b1010 : alarm = 'b0;
4'b1011 : alarm = 'b0;
4'b1100 : alarm = 'b1;
4'b1101 : alarm = 'b0;
4'b1110 : alarm = 'b0;
4'b1110 : alarm = 'b0;
   endcase
 end
 endmodule
