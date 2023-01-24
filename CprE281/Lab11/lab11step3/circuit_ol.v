module circuit_ol (Q1, Q0, Z2, Z1, Z0);
	input Q1, Q0;
	output  Z2, Z1, Z0;
	
	assign Z2 = (Q1&~Q0)|(Q1&Q0);
	assign Z1 = (~Q1&Q0)|(Q1&Q0);
	assign Z0 = (Q1 | ~Q0);
	
	endmodule
	