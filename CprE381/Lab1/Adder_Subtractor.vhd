library IEEE;
use IEEE.std_logic_1164.all;



entity Adder_Subtractor is
  Generic (N: integer := 8);
  
  port(i_A  : in std_logic_vector(N-1 downto 0);
       i_B : in std_logic_vector(N-1 downto 0);
       i_Sel : in std_logic;                       
       o_S   : out std_logic_vector(N-1 downto 0); 
       o_D   : out std_logic);   
end Adder_Subtractor;
  
architecture structural of Adder_Subtractor is
  
 component Full_Adder_N is
  generic(N: integer := 8);
  port(i_D2     : in std_logic_vector(N-1 downto 0);
     i_D3      : in std_logic_vector(N-1 downto 0);
     i_Cin1     : in std_logic;
     o_S1      : out std_logic_vector(N-1 downto 0);
     o_Cout1   : out std_logic);
 end component;
    
 component ones_comp is
    generic(N : integer := 8);
    port(i_D  : in std_logic_vector(N-1 downto 0);
       o_A   : out std_logic_vector(N-1 downto 0));

 end component;

 component mux2t1_N is
    generic(N : integer := 8);
    port(i_S          : in std_logic;
       i_D0         : in std_logic_vector(N-1 downto 0);
       i_D1         : in std_logic_vector(N-1 downto 0);
       o_O          : out std_logic_vector(N-1 downto 0));
 end component;
  
  signal o_Comp : std_logic_vector(N-1 downto 0);
  signal o_Sel  : std_logic_vector(N-1 downto 0);

 begin
    
    generic_OnesComp            : ones_comp
      port map(i_D => i_B,
               o_A => o_Comp); 

    generic_m_mux2t1_N : mux2t1_N
      port map(i_S => i_Sel,
               i_D0 => i_B,
               i_D1 => o_Comp,
               o_O => o_Sel);

    generic_fullAdder           : Full_Adder_N
      port map(i_D2 => i_A,
               i_D3 => o_Sel,
               i_Cin1 => i_Sel,
               o_S1  => o_S,
               o_Cout1 => o_D);
  
end structural;

