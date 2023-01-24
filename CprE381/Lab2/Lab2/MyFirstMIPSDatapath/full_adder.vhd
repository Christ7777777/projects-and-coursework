
library IEEE;
use IEEE.std_logic_1164.all;

entity full_adder is
	port(i_A	: in std_logic;
	     i_B	: in std_logic;
	     i_C	: in std_logic;
	     o_S	: out std_logic;
	     o_C	: out std_logic);
end full_adder;

architecture structural of full_adder is

	
	component andg2 is
	  port( i_A,i_B : in std_logic;
		o_F	: out std_logic);
	end component;

	
	component org2 is
	  port( i_A,i_B : in std_logic;
		o_F	: out std_logic);
	end component;

	
	component xorg2 is
	  port( i_A,i_B	: in std_logic;
		o_F	: out std_logic);
	end component;

	
	signal s_XOR,s_A1,s_A2 : std_logic;

begin
	x1: xorg2
	  port map(i_A => i_A,
		   i_B => i_B,
		   o_F => s_XOR);

	x2: andg2
	  port map(i_A => s_XOR,
		   i_B => i_C,
		   o_F => s_A1);

	x3: andg2
	  port map(i_A => i_A,
		   i_B => i_B,
		   o_F => s_A2);

	x4: xorg2
	  port map(i_A => s_XOR,
		   i_B => i_C,
		   o_F => o_S);

	x5: org2
	  port map(i_A => s_A1,
		   i_B => s_A2,
		   o_F => o_C);
end structural;
