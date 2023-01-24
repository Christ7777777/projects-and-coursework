
library IEEE;
use IEEE.std_logic_1164.all;


library IEEE;
use IEEE.std_logic_1164.all;

entity bit_extender_16t32 is
  port(i_SignSel    : in std_logic;
       i_D          : in std_logic_vector(15 downto 0);
       o_D          : out std_logic_vector(31 downto 0));
end bit_extender_16t32;


architecture structural of bit_extender_16t32 is
  component mux32t1 is
	port(i_D    	  : in std_logic_vector(31 downto 0);
	     i_S	  : in std_logic_vector(4 downto 0);
	     o_O	  : out std_logic);
  end component;

begin
  with i_SignSel select o_D <=
    (15 downto 0 => i_D, others=>'0')     when '0',
    (15 downto 0 => i_D, others=>i_D(15)) when '1',
    X"00000000" when others;
  
end structural;
