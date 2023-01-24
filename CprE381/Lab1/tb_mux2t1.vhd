





library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_textio.all;  -- For logic types I/O
library std;
use std.env.all;                -- For hierarchical/external signals
use std.textio.all;             -- For basic I/O




entity tb_mux2t1 is

end tb_mux2t1;

architecture mixed of tb_mux2t1 is



component mux2t1 is

port(i_S       :in std_logic;
     i_D0       :in std_logic;
     i_D1       :in std_logic;
     o_O      : out std_logic);
end component;


signal s_iS : std_logic := '0';
signal s_iD0 : std_logic := '0';
signal s_iD1 : std_logic := '0';
signal s_o : std_logic;


begin

  DUT0: mux2t1
  port map(i_S   => s_iS,
           i_D0   => s_iD0,
           i_D1   => s_iD1,
           o_O  => s_o);

P_TEST_CASES: process
begin


s_iS <= '0';
s_iD0 <= '0';
s_iD1 <= '0';
wait for 10 ns;

s_iS <= '0';
s_iD0 <= '0';
s_iD1 <= '1';
wait for 10 ns;

s_iS <= '0';
s_iD0 <= '1';
s_iD1 <= '0';
wait for 10 ns;

s_iS <= '0';
s_iD0 <= '1';
s_iD1 <= '1';
wait for 10 ns;

s_iS <= '1';
s_iD0 <= '0';
s_iD1 <= '0';
wait for 10 ns;

s_iS <= '1';
s_iD0 <= '0';
s_iD1 <= '1';
wait for 10 ns;

s_iS <= '1';
s_iD0 <= '1';
s_iD1 <= '0';
wait for 10 ns;

s_iS <= '1';
s_iD0 <= '1';
s_iD1 <= '1';
wait for 10 ns;

end process;

end mixed;









