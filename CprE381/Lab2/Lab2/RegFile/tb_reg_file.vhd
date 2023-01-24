


library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_textio.all;  
use IEEE.numeric_std.all;	
library std;
use std.env.all;                
use std.textio.all;             



entity tb_reg_file is
  generic(gCLK_HPER   : time := 50 ns);
end tb_reg_file;

architecture behavior of tb_reg_file is
  

  constant cCLK_PER  : time := gCLK_HPER * 2;


  component reg_file
  port(i_RST         : in std_logic;
       i_CLK         : in std_logic;
       i_rd          : in std_logic_vector(4 downto 0); 
       i_rs          : in std_logic_vector(4 downto 0); 
       i_rt          : in std_logic_vector(4 downto 0); 
       i_rdData      : in std_logic_vector(31 downto 0);
       o_rsData      : out std_logic_vector(31 downto 0);
       o_rtData      : out std_logic_vector(31 downto 0));
  end component;


  signal s_CLK : std_logic := '0';

  signal s_RST: std_logic := '0';

  signal s_rdData  : std_logic_vector(31 downto 0) := (others => '0');
  signal s_rsData  : std_logic_vector(31 downto 0);
  signal s_rtData  : std_logic_vector(31 downto 0);

  signal s_rd      : std_logic_vector(4 downto 0) := (others => '0');
  signal s_rs      : std_logic_vector(4 downto 0) := (others => '0');
  signal s_rt      : std_logic_vector(4 downto 0) := (others => '0');

begin

  DUT: reg_file 
  port map(i_CLK => s_CLK,
           i_RST => s_RST,
           i_rs => s_rs,
           i_rt => s_rt,
           i_rdData => s_rdData,
           o_rsData => s_rsData,
           o_rtData => s_rtData,
           i_rd   => s_rd);

 
  P_CLK: process
  begin
    s_CLK <= '0';
    wait for gCLK_HPER;
    s_CLK <= '1';
    wait for gCLK_HPER;
  end process;
  
  P_TB: process
  begin
   
    s_rdData <= X"FFFFFFFF";
    s_RST <= '1';
    wait for cCLK_PER;
    wait for cCLK_PER;
    s_RST <= '0';
   
    s_rdData <= X"11111111";
    s_rd <= "00100";
    s_rs <= "00011";
    wait for cCLK_PER;
    s_rdData <= X"FFFFFFFF";
    s_rd <= "11111";
    s_rs <= "00000";
    wait for cCLK_PER;
    s_rd <= "11111";
    s_rs <= "11111";
    wait for cCLK_PER;

    s_rs <= "00000";
    s_rt <= "00000";
    wait for cCLK_PER;
    s_rs <= "00100";
    s_rt <= "10011";
    wait for cCLK_PER;
    s_rs <= "10011";
    s_rt <= "00100";
    wait for cCLK_PER;
    s_rs <= "11111";
    s_rt <= "00000";
    wait for cCLK_PER;

 
    s_rt <= "00011";
    s_RST <= '1';
    wait for cCLK_PER;





    wait;
  end process;
  
end behavior;
