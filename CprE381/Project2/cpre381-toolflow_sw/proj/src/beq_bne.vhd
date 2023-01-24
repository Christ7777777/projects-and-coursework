
library IEEE;
use IEEE.std_logic_1164.all;

entity beq_bne is
	port(i_F		: in std_logic_vector(31 downto 0);
	     i_equal_type  	: in std_logic; -- 0 is bne, 1 is beq
	     o_zero     	: out std_logic);
end beq_bne;


architecture structural of beq_bne is
  
  component org2 is
  port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);
  end component;

  component xorg2 is
  port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);
  end component;


  signal s_oOR1 : std_logic_vector(15 downto 0);
  signal s_oOR2 : std_logic_vector(7 downto 0);
  signal s_oOR3 : std_logic_vector(3 downto 0);
  signal s_oOR4 : std_logic_vector(1 downto 0);
  signal s_or_tree_out_bne : std_logic;

begin
 
  FIRST_OR: for i in 0 to 15 generate
    OR1: org2 port map(
              i_A      => i_F(i*2),    
              i_B      => i_F(i*2+1),  
              o_F      => s_oOR1(i));  
  end generate FIRST_OR;
  

  SECOND_OR: for i in 0 to 7 generate
    OR2: org2 port map(
              i_A      => s_oOR1(i*2),   
              i_B      => s_oOR1(i*2+1),  
              o_F      => s_oOR2(i));  
  end generate SECOND_OR;


  THIRD_OR: for i in 0 to 3 generate
    OR3: org2 port map(
              i_A      => s_oOR2(i*2),    
              i_B      => s_oOR2(i*2+1),  
              o_F      => s_oOR3(i));  
  end generate THIRD_OR;


  FOURTH_OR: for i in 0 to 1 generate
    OR3: org2 port map(
              i_A      => s_oOR3(i*2),    
              i_B      => s_oOR3(i*2+1),  
              o_F      => s_oOR4(i));  
  end generate FOURTH_OR;

  ouput_or: org2 port map(
              i_A      => s_oOR4(0),    
              i_B      => s_oOR4(1),  
              o_F      => s_or_tree_out_bne);  

  output: xorg2 port map(
              i_A      => s_or_tree_out_bne, 
              i_B      => i_equal_type,      
              o_F      => o_zero);           

end structural;
