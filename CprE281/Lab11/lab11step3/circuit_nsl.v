module circuit_nsl (w, Q1, Q0, Y1, Y0);
	input w, Q1, Q0;
	output Y1, Y0;
	
	assign Y1 = (Q1&~w)|(Q1&~Q0)|(~Q1&Q0&w);
	assign Y0= (Q0 ^ w);
	
endmodule