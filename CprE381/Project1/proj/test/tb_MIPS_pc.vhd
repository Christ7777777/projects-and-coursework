


library IEEE;
use IEEE.std_logic_1164.all;

entity tb_MIPS_pc is
  generic(gCLK_HPER   : time := 50 ns);
end tb_MIPS_pc;

architecture behavior of tb_MIPS_pc is
  
  -- Calculate the clock period as twice the half-period
  constant cCLK_PER  : time := gCLK_HPER * 2;


  component MIPS_pc
  port(i_CLK        : in std_logic;     
       i_RST        : in std_logic;    
       
       i_D          : in std_logic_vector(31 downto 0);    
       o_Q          : out std_logic_vector(31 downto 0));  
  end component;

 
  signal s_CLK, s_RST, s_WE  : std_logic;
  signal s_D, s_Q : std_logic_vector(31 downto 0);

begin

  DUT: MIPS_pc 
  generic map(N => 32)
  port map(i_CLK => s_CLK, 
           i_RST => s_RST,
           i_D   => s_D,
           o_Q   => s_Q);

  -- This process sets the clock value (low for gCLK_HPER, then high
  -- for gCLK_HPER). Absent a "wait" command, processes restart 
  -- at the beginning once they have reached the final statement.
  P_CLK: process
  begin
    s_CLK <= '0';
    wait for gCLK_HPER;
    s_CLK <= '1';
    wait for gCLK_HPER;
  end process;
  
  P_TB: process
  begin
 
    s_RST <= '1';
    s_D   <= X"00000000";
    wait for cCLK_PER;

 
    s_RST <= '0';
    s_D   <= X"FFFFFFFF";
    wait for cCLK_PER;  

    
    s_RST <= '0';
    s_D   <= X"00000000";
    wait for cCLK_PER;  

      
    s_RST <= '1';
    s_D   <= X"00000000";
    wait for cCLK_PER;  


    s_RST <= '0';
    s_D   <= X"FFFFFFFF";
    wait for cCLK_PER;  

    wait;
  end process;
  
end behavior;
