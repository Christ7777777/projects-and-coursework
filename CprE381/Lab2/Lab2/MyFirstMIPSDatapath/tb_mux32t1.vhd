

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_textio.all; 
use IEEE.numeric_std.all;	
library std;
use std.env.all;                
use std.textio.all;             

entity tb_mux32t1 is
  generic(gCLK_HPER   : time := 50 ns);
end tb_mux32t1;

architecture behavior of tb_mux32t1 is
  
  
  constant cCLK_PER  : time := gCLK_HPER * 2;


  component mux32t1
	port(i_D    	  : in std_logic_vector(31 downto 0);
	     i_S	  : in std_logic_vector(4 downto 0);
	     o_O	  : out std_logic);
  end component;

  
  signal s_CLK : std_logic := '0';

  signal s_D  : std_logic_vector(31 downto 0) := (others => '0');
  signal s_S  : std_logic_vector(4 downto 0) := (others => '0');
  signal s_O  : std_logic;

begin

  DUT: mux32t1 
  port map(i_S => s_S,
           i_D => s_D,
           o_O   => s_O);

  
  P_CLK: process
  begin
    s_CLK <= '0';
    wait for gCLK_HPER;
    s_CLK <= '1';
    wait for gCLK_HPER;
  end process;
  
  
  P_TB: process
  begin

    
    s_D <= X"FFFFFFFF";
    for i in 0 to 31 loop
      s_S   <= std_logic_vector(to_unsigned(i,s_S'length));
    wait for cCLK_PER; 
    end loop;
     

    wait;
  end process;
  
end behavior;
