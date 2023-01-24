

library IEEE;
use IEEE.std_logic_1164.all;

entity mux2t1 is

port(i_S       :in std_logic;
     i_D0       :in std_logic;
     i_D1       :in std_logic;
     o_O      : out std_logic);
end mux2t1;

architecture behavior of mux2t1 is

component andg2   
port(i_A          : in std_logic;
     i_B          : in std_logic;
     o_F          : out std_logic);

end component;

component org2
 port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);

end component;

component invg
 port(i_A          : in std_logic;
       o_F          : out std_logic);

end component;

signal x1 : std_logic;
signal x2 : std_logic;
signal x3 : std_logic;

begin

        mx_1: org2
        port MAP(i_A       => x2,
                 i_B       => x3,
                 o_F       => o_O);
       

       mx_2: andg2
       port MAP (i_A     => x1,
                 i_B     => i_D0,
                 o_F      => x2);

 
      mx_3: andg2
      port MAP(i_A      => i_S,
               i_B      => i_D1,
               o_F      => x3);

     mx_4: invg
       port MAP(i_A     => i_S,
                o_F     => x1);

end behavior;





