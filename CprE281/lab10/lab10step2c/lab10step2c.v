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
// CREATED		"Tue Apr 12 14:03:30 2022"

module lab10step2c(
	Enable,
	Clock,
	CLRN,
	Q
);


input wire	Enable;
input wire	Clock;
input wire	CLRN;
output wire	[4:0] Q;

reg	[4:0] Q_ALTERA_SYNTHESIZED;
wire	SYNTHESIZED_WIRE_12;
wire	SYNTHESIZED_WIRE_13;
wire	SYNTHESIZED_WIRE_14;
wire	SYNTHESIZED_WIRE_15;
wire	SYNTHESIZED_WIRE_8;

assign	SYNTHESIZED_WIRE_12 = 1;




always@(posedge Clock or negedge CLRN or negedge SYNTHESIZED_WIRE_12)
begin
if (!CLRN)
	begin
	Q_ALTERA_SYNTHESIZED[0] <= 0;
	end
else
if (!SYNTHESIZED_WIRE_12)
	begin
	Q_ALTERA_SYNTHESIZED[0] <= 1;
	end
else
	Q_ALTERA_SYNTHESIZED[0] <= Q_ALTERA_SYNTHESIZED[0] ^ Enable;
end


always@(posedge Clock or negedge CLRN or negedge SYNTHESIZED_WIRE_12)
begin
if (!CLRN)
	begin
	Q_ALTERA_SYNTHESIZED[1] <= 0;
	end
else
if (!SYNTHESIZED_WIRE_12)
	begin
	Q_ALTERA_SYNTHESIZED[1] <= 1;
	end
else
	Q_ALTERA_SYNTHESIZED[1] <= Q_ALTERA_SYNTHESIZED[1] ^ SYNTHESIZED_WIRE_13;
end



always@(posedge Clock or negedge CLRN or negedge SYNTHESIZED_WIRE_12)
begin
if (!CLRN)
	begin
	Q_ALTERA_SYNTHESIZED[2] <= 0;
	end
else
if (!SYNTHESIZED_WIRE_12)
	begin
	Q_ALTERA_SYNTHESIZED[2] <= 1;
	end
else
	Q_ALTERA_SYNTHESIZED[2] <= Q_ALTERA_SYNTHESIZED[2] ^ SYNTHESIZED_WIRE_14;
end


always@(posedge Clock or negedge CLRN or negedge SYNTHESIZED_WIRE_12)
begin
if (!CLRN)
	begin
	Q_ALTERA_SYNTHESIZED[3] <= 0;
	end
else
if (!SYNTHESIZED_WIRE_12)
	begin
	Q_ALTERA_SYNTHESIZED[3] <= 1;
	end
else
	Q_ALTERA_SYNTHESIZED[3] <= Q_ALTERA_SYNTHESIZED[3] ^ SYNTHESIZED_WIRE_15;
end


always@(posedge Clock or negedge CLRN or negedge SYNTHESIZED_WIRE_12)
begin
if (!CLRN)
	begin
	Q_ALTERA_SYNTHESIZED[4] <= 0;
	end
else
if (!SYNTHESIZED_WIRE_12)
	begin
	Q_ALTERA_SYNTHESIZED[4] <= 1;
	end
else
	Q_ALTERA_SYNTHESIZED[4] <= Q_ALTERA_SYNTHESIZED[4] ^ SYNTHESIZED_WIRE_8;
end

assign	SYNTHESIZED_WIRE_13 = Enable & Q_ALTERA_SYNTHESIZED[0];

assign	SYNTHESIZED_WIRE_14 = SYNTHESIZED_WIRE_13 & Q_ALTERA_SYNTHESIZED[1];

assign	SYNTHESIZED_WIRE_15 = SYNTHESIZED_WIRE_14 & Q_ALTERA_SYNTHESIZED[2];

assign	SYNTHESIZED_WIRE_8 = SYNTHESIZED_WIRE_15 & Q_ALTERA_SYNTHESIZED[3];

assign	Q = Q_ALTERA_SYNTHESIZED;

endmodule
