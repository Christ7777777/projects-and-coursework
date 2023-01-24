library IEEE;
use IEEE.std_logic_1164.all;

entity mux2t1_N_dataflow is
  generic(N : integer := 16); -- Generic of type integer for input/output data width. Default value is 32.
  port(i_S          : in std_logic;
       i_D0         : in std_logic_vector(N-1 downto 0);
       i_D1         : in std_logic_vector(N-1 downto 0);
       o_O          : out std_logic_vector(N-1 downto 0));

end mux2t1_N_dataflow;

architecture dataflow of mux2t1_N_dataflow is


signal s : std_logic_vector(N-1 downto 0);
signal x1 : std_logic_vector(N-1 downto 0);
signal x2 : std_logic_vector(N-1 downto 0);
signal x3 : std_logic_vector(N-1 downto 0);

begin

  
  MX1: for i in 0 to N-1 generate
         s(i) <= i_S;
       end generate;


      x1 <= not s;
      x2 <= i_D0 and x1;
      x3 <= i_D1 and s;
      o_O <= x2 or x3;

end dataflow;
