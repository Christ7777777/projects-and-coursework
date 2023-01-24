
library IEEE;
use IEEE.std_logic_1164.all;


entity ones_comp_N is
  generic(N : integer := 32); 
  port(i_A          : in std_logic_vector(N-1 downto 0);
       o_F          : out std_logic_vector(N-1 downto 0));
end ones_comp_N;


architecture structural of ones_comp_N is

  component invg is
    port(i_A                  : in std_logic;
         o_F                  : out std_logic);
  end component;

begin

  
  ONESCOMP_NBIT: for i in 0 to N-1 generate
    inst_1: invg port map(
              i_A     => i_A(i),  
              o_F      => o_F(i)); 
  end generate ONESCOMP_NBIT;
  
end structural;
