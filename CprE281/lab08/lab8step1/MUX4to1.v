module MUX4to1 (w0, w1, w2, w3, s, f);
	input w0, w1, w2, w3;
	input [1:0] s;
	output f;
	reg f;
	always @ (s[1:0])
	begin
		case ({s[1],s[0]})
			2'b00: f = w0;
			2'b01: f = w1;
			2'b10: f = w2;
			2'b11: f = w3;
		endcase
	end
endmodule
