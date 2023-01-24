

library IEEE;
use IEEE.std_logic_1164.all;

entity tb_Full_Adder_N is
 generic(D : integer := 8);
 port(    
      o_O1     : out std_logic_vector(D-1 downto 0);
      o_O2     : out std_logic);

end tb_Full_Adder_N;

architecture structural of tb_Full_Adder_N is

component Full_Adder_N

generic(N : integer := 8);
  port(i_D2 : in std_logic_vector(N-1 downto 0);
       i_D3 : in std_logic_vector(N-1 downto 0);
       i_Cin1 : in std_logic;
       o_S1  : out std_logic_vector(N-1 downto 0);
       o_Cout1  : out std_logic);
end component;


signal s_O0 : std_logic_vector(D-1 downto 0);
signal s_O1 : std_logic_vector(D-1 downto 0);
signal s_O2 : std_logic;


begin

  FA_1 : Full_Adder_N
  generic map(N => 8)
  port map(i_D2 => s_O0,
  	   i_D3 => s_O1,
	   i_Cin1 => s_O2,
	   o_S1 => o_O1,
	   o_Cout1 => o_O2);


process
  begin

	s_O0 <= x"0A";
	s_O1 <= x"70";
	s_O2 <=  '1';
	wait for 100 ns;

	s_O0 <= x"01";
	s_O1 <= x"15";
	s_O2 <=  '0';
	wait for 100 ns;

	s_O0 <= x"FF";
	s_O1 <= x"01";
	s_O2 <=  '0';
	wait for 100 ns;

	s_O0 <= x"11";
	s_O1 <= x"FA";
	s_O2 <=  '1';
	wait for 100 ns;
	wait;
end process;

end structural;
