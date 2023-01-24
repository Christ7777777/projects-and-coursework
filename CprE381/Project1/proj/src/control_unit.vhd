
library IEEE;
use IEEE.std_logic_1164.all;

entity control_unit is
	port(i_opcode  	  	: in std_logic_vector(5 downto 0);
	     i_funct	  	: in std_logic_vector(5 downto 0);
	     o_Ctrl_Unt		: out std_logic_vector(14 downto 0));
end control_unit;


architecture dataflow of control_unit is
signal s_RTYPE : std_logic_vector(14 downto 0);
begin


with i_funct select s_RTYPE <=
    "000111000110100"  when "100000", 
    "000000000110100"  when "100001",
    "000001000110100"  when "100100", 
    "000010100110100"  when "100111",
    "000010000110100"  when "100110",
    "000001100110100"  when "100101",
    "000011100110100"  when "101010", 
    "000011100110100"  when "101011", 
    "000100100110100"  when "000000", 
    "000100000110000"  when "000010",
    "000101000110100"  when "000011",
    "000111100110100"  when "100010",
    "000000100110100"  when "100011",
    "100000000000110"  when "001000", 
    "000000000000000"  when others;

with i_opcode select o_Ctrl_Unt <=
    s_RTYPE  	    when "000000", 
    "001111000100100"  when "001000", 

    "000000000000001"  when "010100", 

    "001000000100100"  when "001001",
    "001001000100000"  when "001100", 
    "001010000100000"  when "001110", 
    "001001100100000"  when "001101", 
    "001011100100100"  when "001010",
    "001011100100100"  when "001011",
    "001011000100100"  when "001111", 
    "000101100001100"  when "000100",
    "000110000001100"  when "000101",
    "001000010100100"  when "100011", 
    "001000001000100"  when "101011", 
    "000000000000110"  when "000010", 
    "010000000100110"  when "000011",
    "000000000000000"  when others;

end dataflow;
