library IEEE;
use IEEE.std_logic_1164.all;

entity ones_comp is
  generic(N : integer := 8);
  port(i_D  : in std_logic_vector(N-1 downto 0);
       o_A   : out std_logic_vector(N-1 downto 0));

end ones_comp;

architecture structural of ones_comp is

component invg
  port(i_A  : in std_logic;
       o_F  : out std_logic);
end component;

begin
    O1: for i in 0 to N-1 generate
       negate: invg
       port map(i_A   => i_D(i),
                o_F   => o_A(i));
       end generate;
end structural;
