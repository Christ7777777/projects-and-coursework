


library IEEE;
use IEEE.std_logic_1164.all;


entity Full_Adder_N is 
  generic(N : integer := 8    );

port(i_D2     : in std_logic_vector(N-1 downto 0);
     i_D3      : in std_logic_vector(N-1 downto 0);
     i_Cin1     : in std_logic;
     o_S1      : out std_logic_vector(N-1 downto 0);
     o_Cout1   : out std_logic);

end Full_Adder_N;

architecture structural of Full_Adder_N is


component Full_Adder 
 port(i_D0      : in std_logic;
     i_D1      : in std_logic;
     i_Cin     : in std_logic;
     o_S      : out std_logic;
     o_Cout   : out std_logic);
 end component;

signal s: std_logic_vector(N downto 0);

begin
   s(0) <= i_Cin1;
  


  FA_N1: for i in 0 to N-1 generate
        
    FA1: Full_Adder
         port map(i_D0   => i_D2(i),
                  i_D1   => i_D3(i),
                  i_Cin  => s(i),
                  o_S    => o_S1(i),
                  o_Cout    => s(i+1));
         end generate;
  
  o_Cout1 <= s(N);



end structural; 
