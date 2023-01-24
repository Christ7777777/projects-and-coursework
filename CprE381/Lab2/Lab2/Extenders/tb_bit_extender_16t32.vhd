

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_textio.all;  
use IEEE.numeric_std.all;	
library std;
use std.env.all;                
use std.textio.all;             

entity tb_bit_extender_16t32 is
  generic(gCLK_HPER   : time := 50 ns);
end tb_bit_extender_16t32;

architecture behavior of tb_bit_extender_16t32 is
  
  
  constant cCLK_PER  : time := gCLK_HPER * 2;


  component bit_extender_16t32
  port(i_SignSel    : in std_logic;
       i_D          : in std_logic_vector(15 downto 0);
       o_D          : out std_logic_vector(31 downto 0));
  end component;

  
  signal s_CLK : std_logic := '0';

  signal s_SignSel      : std_logic := '0';
  signal s_Din 		: std_logic_vector(15 downto 0) := (others => '0');
  signal s_Dout  	: std_logic_vector(31 downto 0);

begin

  DUT: bit_extender_16t32
  port map(i_SignSel  => s_SignSel,
           i_D   => s_Din,
           o_D => s_Dout);

 
  P_CLK: process
  begin
    s_CLK <= '0';
    wait for gCLK_HPER;
    s_CLK <= '1';
    wait for gCLK_HPER;
  end process;
  
  
  P_TB: process
  begin
    s_SignSel <= '0'; 
    s_Din <= X"1a1a";
    wait for cCLK_PER; 
    s_Din <= X"FFFF";
    wait for cCLK_PER; 
    s_Din <= X"8000";
    wait for cCLK_PER; 

    s_SignSel <= '1'; 
    s_Din <= X"1a1a";
    wait for cCLK_PER; 
    s_Din <= X"FFFF";
    wait for cCLK_PER; 
    s_Din <= X"8000";
    wait for cCLK_PER; 
    



    wait;
  end process;
  
end behavior;
