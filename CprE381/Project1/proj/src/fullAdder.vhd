

library IEEE;
use IEEE.std_logic_1164.all;

entity fullAdder is

  port(i_X0 	: in std_logic;
	   i_X1		: in std_logic;
	   i_Cin	: in std_logic;
	   o_Y		: out std_logic;
	   o_Cout	: out std_logic);

end fullAdder;

architecture structural of fullAdder is

  component xorg2 is
     port(i_A          : in std_logic;
		  i_B		   : in std_logic;
		  o_F          : out std_logic);
  end component;
  
  component andg2 is
     port(i_A          : in std_logic;
		  i_B		   : in std_logic;
		  o_F          : out std_logic);
  end component;
  
  component org2 is
     port(i_A          : in std_logic;
		  i_B		   : in std_logic;
		  o_F          : out std_logic);
  end component;

  signal s1, s2, s3	: std_logic;

begin
	xor1: xorg2
	port MAP(i_A       => i_X0,
			i_B		   => i_X1,
			o_F        => s1);
	xor2: xorg2
	port MAP(i_A       => s1,
			i_B		   => i_Cin,
			o_F        => o_Y);
	and1: andg2
	port MAP(i_A       => s1,
			i_B		   => i_Cin,
			o_F        => s2);
	and2: andg2
	port MAP(i_A       => i_X0,
			i_B		   => i_X1,
			o_F        => s3);
	or1: org2
	port MAP(i_A       => s2,
			i_B		   => s3,
			o_F        => o_Cout);
	


  
end structural;
