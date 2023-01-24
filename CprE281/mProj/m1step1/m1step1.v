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
// CREATED		"Tue Mar 01 14:05:31 2022"

module m1step1(
	w1,
	x1,
	y1,
	z1,
	p
);


input wire	w1;
input wire	x1;
input wire	y1;
input wire	z1;
output wire	p;

wire	SYNTHESIZED_WIRE_0;
wire	SYNTHESIZED_WIRE_1;
wire	SYNTHESIZED_WIRE_2;
wire	SYNTHESIZED_WIRE_3;
wire	SYNTHESIZED_WIRE_4;
wire	SYNTHESIZED_WIRE_5;





bobs_circuit	b2v_inst(
	.w(w1),
	.x(x1),
	.y(y1),
	.z(z1),
	.b(SYNTHESIZED_WIRE_4));

assign	SYNTHESIZED_WIRE_0 =  ~w1;

assign	SYNTHESIZED_WIRE_1 =  ~x1;

assign	SYNTHESIZED_WIRE_3 = SYNTHESIZED_WIRE_0 & SYNTHESIZED_WIRE_1 & y1 & z1;

assign	SYNTHESIZED_WIRE_2 = x1 & y1 & z1;

assign	SYNTHESIZED_WIRE_5 = SYNTHESIZED_WIRE_2 | SYNTHESIZED_WIRE_3;

assign	p = SYNTHESIZED_WIRE_4 ^ SYNTHESIZED_WIRE_5;


endmodule
