library IEEE;
use IEEE.std_logic_1164.all;


entity tb_Adder_Subtractor is
  generic(A : integer := 8);
  port(o_C : out std_logic;
       o_M : out std_logic_vector(A-1 downto 0));

end tb_Adder_Subtractor;

architecture structure of tb_Adder_Subtractor is

  component Adder_Subtractor is
    generic(N  : integer := 8);
   port(i_A  : in std_logic_vector(N-1 downto 0);
       i_B : in std_logic_vector(N-1 downto 0);
       i_Sel : in std_logic;                       
       o_S   : out std_logic_vector(N-1 downto 0); 
       o_D   : out std_logic);   
  end component;
  
  signal s_A : std_logic_vector(A-1 downto 0);
  signal s_B : std_logic_vector(A-1 downto 0);
  signal s_iS  : std_logic;
  
begin

 generic_AS: Adder_Subtractor
    generic map(N  => A)
    port map(i_A  => s_A,
             i_B  => s_B,
	     i_Sel => s_iS,
	     o_S   => o_M,
	     o_D   => o_C);
  process
    begin
  
      s_A <= x"11";
      s_B <= x"01";
      s_iS  <=  '0';
      wait for 100 ns;
      
      s_iS  <=  '1';
      wait for 100 ns;

      s_A <= x"FE";
      s_B <= x"01";      
      s_iS <=  '0';
      wait for 100 ns;

      s_iS  <=  '1';
      wait for 100 ns;

      s_A <= x"00";
      s_B <= x"AA";      
      s_iS  <=  '0';
      wait for 100 ns;
 
      s_iS  <=  '1';
      wait for 100 ns;

      s_A <= x"11";
      s_B <= x"10";      
      s_iS  <=  '0';
      wait for 100 ns;

      s_iS  <=  '1';
      wait for 100 ns;
      
  end process;
  
end structure;

