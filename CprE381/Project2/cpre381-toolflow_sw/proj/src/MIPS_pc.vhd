
library IEEE;
use IEEE.std_logic_1164.all;


entity MIPS_pc is
 
  port(i_CLK        : in std_logic;     
       i_RST        : in std_logic;     
     
       i_D          : in std_logic_vector(31 downto 0);     
       o_Q          : out std_logic_vector(31 downto 0));   
end MIPS_pc;


architecture structural of MIPS_pc is

  component MIPS_pc_dffg is
  port(i_CLK        : in std_logic;     
       i_RST        : in std_logic;     
       i_RST_data   : in std_logic;    
       i_D          : in std_logic;     
       o_Q          : out std_logic);   
  end component;

  signal s_RST_data : std_logic_vector(31 downto 0) := X"00400000";

begin

 
  G_NBit_DFFG: for i in 0 to 31 generate
    ONESCOMPI: MIPS_pc_dffg port map(
              i_CLK     => i_CLK,  
	      i_RST	=> i_RST,  
	      i_RST_data=> s_RST_data(i),   
	      i_D	=> i_D(i), 
              o_Q       => o_Q(i));
  end generate G_NBit_DFFG;
  
end structural;
