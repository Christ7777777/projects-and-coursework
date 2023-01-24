

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_textio.all;
use IEEE.numeric_std.all;	
library std;
use std.env.all;               
use std.textio.all;            
use work.STD_LOGIC_MATRIX.all;

entity tb_mux32t1_N is
  generic(gCLK_HPER   : time := 50 ns);
end tb_mux32t1_N;

architecture behavior of tb_mux32t1_N is
  
 
  constant cCLK_PER  : time := gCLK_HPER * 2;


  component mux32t1_N
  
  port(i_S          : in std_logic_vector(4 downto 0);
       i_D          : in std_logic_matrix(31 downto 0);
       o_O          : out std_logic_vector(31 downto 0));
  end component;

  
  signal s_CLK : std_logic := '0';

  signal s_D  : std_logic_matrix(31 downto 0) := (others => X"00000000");
  signal s_S  : std_logic_vector(4 downto 0) := (others => '0');
  signal s_O  : std_logic_vector(31 downto 0);

begin

  DUT: mux32t1_N 
 
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

    
    s_D(0) <= X"00000000";
    s_D(1) <= X"00000001";
    s_D(2) <= X"00000010";
    s_D(3) <= X"00000011";
    s_D(4) <= X"00000100";
    s_D(5) <= X"00000101";
    s_D(6) <= X"00000110";
    s_D(7) <= X"00000111";
    s_D(8) <= X"00001000";
    s_D(9) <= X"00001001";
    s_D(10) <= X"00001010";
    s_D(11) <= X"00001011";
    s_D(12) <= X"00001100";
    s_D(13) <= X"00001101";
    s_D(14) <= X"00001110";
    s_D(15) <= X"00001111";
    s_D(16) <= X"00010000";
    s_D(17) <= X"00010001";
    s_D(18) <= X"00010010";
    s_D(19) <= X"00010011";
    s_D(20) <= X"00010100";
    s_D(21) <= X"00010101";
    s_D(22) <= X"00010110";
    s_D(23) <= X"00010111";
    s_D(24) <= X"00011000";
    s_D(25) <= X"00011001";
    s_D(26) <= X"00011010";
    s_D(27) <= X"00011011";
    s_D(28) <= X"00011100";
    s_D(29) <= X"00011101";
    s_D(30) <= X"00011110";
    s_D(31) <= X"00011111";

    for i in 0 to 31 loop
      s_S   <= std_logic_vector(to_unsigned(i,s_S'length));
    wait for cCLK_PER; 
    end loop;


    wait;
  end process;
  
end behavior;
