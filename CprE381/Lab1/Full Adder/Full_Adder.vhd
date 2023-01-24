


library IEEE;
use IEEE.std_logic_1164.all;

entity Full_Adder is


port(i_D0      : in std_logic;
     i_D1      : in std_logic;
     i_Cin     : in std_logic;
     o_S      : out std_logic;
     o_Cout   : out std_logic);

end Full_Adder;

architecture behavior of Full_Adder is


component xorg2
port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);
end component;

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

signal s1: std_logic;
signal s2: std_logic;
signal s3: std_logic;



begin
     

      ad1: org2
      port MAP(i_A   => s2,
               i_B   => s3,
               o_F   => o_Cout);

      ad2: andg2
      port MAP(i_A   => i_Cin,
               i_B   => s1,
               o_F   => s2);


     ad3: andg2
      port MAP(i_A   => i_D0,
               i_B   => i_D1,
               o_F   => s3);


     ad4: xorg2
     port MAP(i_A   => s1,
              i_B   => i_Cin,
              o_F   => o_S);


    ad5: xorg2
     port MAP(i_A   => i_D0,
              i_B   => i_D1,
              o_F   => s1);

end behavior;

