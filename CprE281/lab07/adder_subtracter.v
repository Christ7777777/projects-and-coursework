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
// CREATED		"Tue Mar 22 13:27:37 2022"

module adder_subtracter(
	pin_name6,
	X2,
	X3,
	Y3,
	Y2,
	X1,
	Y1,
	Y0,
	X0,
	overflow,
	s3,
	Cout,
	s2,
	s1,
	s0
);


input wire	pin_name6;
input wire	X2;
input wire	X3;
input wire	Y3;
input wire	Y2;
input wire	X1;
input wire	Y1;
input wire	Y0;
input wire	X0;
output wire	overflow;
output wire	s3;
output wire	Cout;
output wire	s2;
output wire	s1;
output wire	s0;

wire	SYNTHESIZED_WIRE_0;
wire	SYNTHESIZED_WIRE_1;
wire	SYNTHESIZED_WIRE_2;
wire	SYNTHESIZED_WIRE_3;





adder_4bit	b2v_inst(
	.X3(X3),
	.Y3(SYNTHESIZED_WIRE_0),
	.X2(X2),
	.Y2(SYNTHESIZED_WIRE_1),
	.X1(X1),
	.Y1(SYNTHESIZED_WIRE_2),
	.Cin(pin_name6),
	.X0(X0),
	.Y0(SYNTHESIZED_WIRE_3),
	.ov(overflow),
	.S3(s3),
	.Cout(Cout),
	.S2(s2),
	.S1(s1),
	.S0(s0));

assign	SYNTHESIZED_WIRE_0 = pin_name6 ^ Y3;

assign	SYNTHESIZED_WIRE_1 = pin_name6 ^ Y2;

assign	SYNTHESIZED_WIRE_2 = pin_name6 ^ Y1;

assign	SYNTHESIZED_WIRE_3 = pin_name6 ^ Y0;


endmodule
