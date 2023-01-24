library IEEE;
use IEEE.std_logic_1164.all;

entity tb_ones_comp is
  generic(D: integer := 32);
  
  port(o_data : out std_logic_vector(D-1 downto 0));
      
end tb_ones_comp;

architecture behavioral of tb_ones_comp is
component ones_comp
  generic(N : integer := 16);
      port(i_D  : in std_logic_vector(N-1 downto 0);
           o_A  : out std_logic_vector(N-1 downto 0));
    end component;
     
  signal s : std_logic_vector(D-1 downto 0);  
     
begin
 generic_onescomp : ones_comp
      generic map(N => D)
      port map(i_D => s,
               o_A => o_data);
    
process
  begin
    s <= x"00000000";
    wait for 50 ns;
    
    s <= x"A5A5A5A5";
    wait for 50 ns;
    
    s <= x"11111111";
    wait for 50 ns;
                         
    s <= x"77777777";
    wait for 50 ns;
end process;
       
end behavioral;
