





library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_textio.all;  -- For logic types I/O
library std;
use std.env.all;                -- For hierarchical/external signals
use std.textio.all;             -- For basic I/O




entity tb_Full_Adder is

end tb_Full_Adder;

architecture mixed of tb_Full_Adder is



component Full_Adder is

port(i_D0      : in std_logic;
     i_D1      : in std_logic;
     i_Cin     : in std_logic;
     o_S      : out std_logic;
     o_Cout   : out std_logic);
end component;


signal s_A : std_logic := '0';
signal s_B : std_logic := '0';
signal s_Cin : std_logic := '0';
signal s_oS : std_logic;
signal s_Cout : std_logic;


begin

  DUT0: Full_Adder
  port map(i_D0   => s_A,
           i_D1   => s_B,
           i_Cin   => s_Cin,
           o_S  => s_oS,
           o_Cout => s_Cout);

A_TEST_CASES: process
begin


s_A <= '0';
s_B <= '0';
s_Cin <= '0';
wait for 100 ns;

s_A <= '0';
s_B <= '0';
s_Cin <= '1';
wait for 100 ns;

s_A <= '0';
s_B <= '1';
s_Cin <= '0';
wait for 100 ns;

s_A <= '0';
s_B <= '1';
s_Cin <= '1';
wait for 100 ns;

s_A <= '0';
s_B <= '0';
s_Cin <= '0';
wait for 100 ns;

s_A <= '1';
s_B <= '0';
s_Cin <= '0';
wait for 100 ns;

s_A <= '1';
s_B <= '0';
s_Cin <= '1';
wait for 100 ns;

s_A <= '1';
s_B <= '1';
s_Cin <= '0';
wait for 100 ns;

s_A <= '1';
s_B <= '1';
s_Cin <= '1';
wait for 100 ns;

end process;

end mixed;









