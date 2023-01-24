

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_textio.all;  
use IEEE.numeric_std.all;	
library std;
use std.env.all;                
use std.textio.all;             

entity tb_dmem is
  generic(gCLK_HPER   : time := 50 ns);
end tb_dmem;

architecture behavior of tb_dmem is
  
  
  constant cCLK_PER  : time := gCLK_HPER * 2;


  component mem
	generic 
	(
		DATA_WIDTH : natural := 32;
		ADDR_WIDTH : natural := 10
	);

	port 
	(
		clk		: in std_logic;
		addr	        : in std_logic_vector((ADDR_WIDTH-1) downto 0);
		data	        : in std_logic_vector((DATA_WIDTH-1) downto 0);
		we		: in std_logic := '1';
		q		: out std_logic_vector((DATA_WIDTH -1) downto 0)
	);
  end component;

  
  signal s_CLK : std_logic := '0';

  signal s_we    : std_logic := '0';
  signal s_addr  : std_logic_vector(9 downto 0) := (others => '0');
  signal s_data  : std_logic_vector(31 downto 0);
  signal s_q     : std_logic_vector(31 downto 0);

begin

  dmem: mem 
  
  port map(clk  => s_CLK,
           we   => s_we,
           addr => s_addr,
	   data => s_data,
	   q    => s_q);

 
  P_CLK: process
  begin
    s_CLK <= '0';
    wait for gCLK_HPER;
    s_CLK <= '1';
    wait for gCLK_HPER;
  end process;
  
  
  P_TB: process
  begin
    for i in 0 to 9 loop
      s_addr   <= std_logic_vector(to_unsigned(i,s_addr'length));
      wait for cCLK_PER;
      s_data <= s_q;
      s_addr   <= std_logic_vector(to_unsigned(i+256,s_addr'length));
      s_we <= '1'; 
      wait for cCLK_PER; 
      s_we <= '0'; 
    end loop;


    wait;
  end process;
  
end behavior;
