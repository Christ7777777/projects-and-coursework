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
// CREATED		"Tue Apr 05 13:03:45 2022"

module lab9step3b(
	CLK,
	D,
	Q,
	QN
);


input wire	CLK;
input wire	D;
output wire	Q;
output wire	QN;

wire	SYNTHESIZED_WIRE_0;
wire	SYNTHESIZED_WIRE_1;





lab9step2	b2v_inst(
	.D(D),
	.CLK(SYNTHESIZED_WIRE_0),
	.Q(SYNTHESIZED_WIRE_1)
	);


lab9step2	b2v_inst1(
	.D(SYNTHESIZED_WIRE_1),
	.CLK(CLK),
	.Q(Q),
	.QN(QN));

assign	SYNTHESIZED_WIRE_0 =  ~CLK;


endmodule
