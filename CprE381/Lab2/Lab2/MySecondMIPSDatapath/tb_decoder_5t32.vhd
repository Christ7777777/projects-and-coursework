

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_textio.all;  
use IEEE.numeric_std.all;	
library std;
use std.env.all;               
use std.textio.all;             

entity tb_decoder_5t32 is
  generic(gCLK_HPER   : time := 50 ns);
end tb_decoder_5t32;

architecture behavior of tb_decoder_5t32 is
  

  constant cCLK_PER  : time := gCLK_HPER * 2;


  component decoder_5t32
  port(
       i_A          : in std_logic_vector(4 downto 0);	
       o_Y          : out std_logic_vector(31 downto 0));
  end component;

 
  signal s_CLK : std_logic := '0';

  signal s_Y  : std_logic_vector(31 downto 0) := (others => '0');
  signal s_A  : std_logic_vector(4 downto 0) := (others => '0');

begin

  DUT: decoder_5t32 
  port map(
           i_A => s_A,
           o_Y   => s_Y);

  P_CLK: process
  begin
    s_CLK <= '0';
    wait for gCLK_HPER;
    s_CLK <= '1';
    wait for gCLK_HPER;
  end process;
  

  P_TB: process
  begin

    
    for i in 0 to 31 loop
      s_A   <= std_logic_vector(to_unsigned(i,s_A'length));
    wait for cCLK_PER; 
    end loop;
     

    wait;
  end process;
  
end behavior;
