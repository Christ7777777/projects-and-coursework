library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_textio.all;  -- For logic types I/O
library std;
use std.env.all;                -- For hierarchical/external signals
use std.textio.all;             -- For basic I/O


entity tb_mux2t1_N is
generic (D : integer := 8);
port(o_data    : out std_logic_vector(D-1 downto 0));
     
end tb_mux2t1_N;



architecture structural of tb_mux2t1_N is



   component mux2t1_N
       generic(N : integer := 16);
       port(i_S          : in std_logic;
       i_D0         : in std_logic_vector(N-1 downto 0);
       i_D1         : in std_logic_vector(N-1 downto 0);
       o_O          : out std_logic_vector(N-1 downto 0));
   end component;

   component mux2t1_N_dataflow
       generic(N : integer := 16);
       port(i_S          : in std_logic;
       i_D0         : in std_logic_vector(N-1 downto 0);
       i_D1         : in std_logic_vector(N-1 downto 0);
       o_O          : out std_logic_vector(N-1 downto 0));
   end component;



  signal s_iS : std_logic;
  signal s_iD0 : std_logic_vector(D-1 downto 0);
  signal s_iD1 : std_logic_vector(D-1 downto 0);
  
  

begin
    

     DUT0: mux2t1_N
       generic map(N => 8)
      port map(i_S        => s_iS,
               i_D0        => s_iD0,
                i_D1        => s_iD1,
               o_O        => o_a);

  DUT1: mux2t1_N_dataflow
       generic map(N => 8)
       port map(i_S        => s_iS,
                i_D0        => s_iD0,
                i_D1        => s_iD1,
                o_O        => o_data);


   process 
       begin
       --Test Case 1
         s_iD0 <= "11100110";
         s_iD1 <= x"00";
         s_iS <= '1';
         wait for 50 ns;
         s_iS <= '0';
         wait for 10 ns;

      --Test Case 2
        s_iD0 <= "00000000";
        s_iD1 <= x"0A";
        s_iS <= '1';
         wait for 50 ns;
        s_iS <= '0';
         wait for 10 ns;
      

  
end process;
end structural;








       


