// Copyright (C) 2020  Intel Corporation. All rights reserved.
// Your use of Intel Corporation's design tools, logic functions 
// and other software and tools, and any partner logic 
// functions, and any output files from any of the foregoing 
// (including device programming or simulation files), and any 
// associated documentation or information are expressly subject 
// to the terms and conditions of the Intel Program License 
// Subscription Agreement, the Intel Quartus Prime License Agreement,
// the Intel FPGA IP License Agreement, or other applicable license
// agreement, including, without limitation, that your use is for
// the sole purpose of programming logic devices manufactured by
// Intel and sold by Intel or its authorized distributors.  Please
// refer to the applicable agreement for further details, at
// https://fpgasoftware.intel.com/eula.

// PROGRAM		"Quartus Prime"
// VERSION		"Version 20.1.1 Build 720 11/11/2020 SJ Standard Edition"
// CREATED		"Tue Feb 15 13:37:19 2022"

module lab4step2(
	P,
	T,
	H,
	M,
	AC
);


input wire	P;
input wire	T;
input wire	H;
input wire	M;
output wire	AC;

wire	SYNTHESIZED_WIRE_0;
wire	SYNTHESIZED_WIRE_1;





normal	b2v_inst(
	.P(P),
	.T(T),
	.H(H),
	.E(SYNTHESIZED_WIRE_0));


multiplexer	b2v_inst1(
	.M(M),
	.E(SYNTHESIZED_WIRE_0),
	.F(SYNTHESIZED_WIRE_1),
	.AC(AC));


powersaving	b2v_inst2(
	.P(P),
	.T(T),
	.H(H),
	.F(SYNTHESIZED_WIRE_1));


endmodule
