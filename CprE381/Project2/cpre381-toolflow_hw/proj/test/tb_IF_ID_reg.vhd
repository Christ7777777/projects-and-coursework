


library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_textio.all;  -- For logic types I/O
use IEEE.numeric_std.all;	-- For to_usnigned
library std;
use std.env.all;                -- For hierarchical/external signals
use std.textio.all;             -- For basic I/O

entity tb_IF_ID_reg is
  generic(gCLK_HPER   : time := 50 ns);
end tb_IF_ID_reg;

architecture behavior of tb_IF_ID_reg is
  
  -- Calculate the clock period as twice the half-period
  constant cCLK_PER  : time := gCLK_HPER * 2;


  component IF_ID_reg
	port(i_CLK		: in std_logic;
	     i_RST		: in std_logic;
	     i_PC_4		: in std_logic_vector(31 downto 0);
	     i_instruction  	: in std_logic_vector(31 downto 0);
	     o_PC_4	  	: out std_logic_vector(31 downto 0);
	     o_instruction	: out std_logic_vector(31 downto 0));
  end component;

  -- Temporary signals to connect to the dff component.
  signal s_CLK : std_logic := '0';

  signal s_i_PC_4     : std_logic_vector(31 downto 0) := (others=> '0');
  signal s_i_instruction     : std_logic_vector(31 downto 0) := (others=> '0');
  signal s_o_PC_4     : std_logic_vector(31 downto 0);
  signal s_o_instruction     : std_logic_vector(31 downto 0);

begin

  DUT: IF_ID_reg 
  port map(i_CLK => s_CLK,
           i_RST => '0',
           i_PC_4   => s_i_PC_4,
           i_instruction   => s_i_instruction,
           o_PC_4   => s_o_PC_4,
	   o_instruction => s_o_instruction);

  -- This process sets the clock value (low for gCLK_HPER, then high
  -- for gCLK_HPER). Absent a "wait" command, processes restart 
  -- at the beginning once they have reached the final statement.
  P_CLK: process
  begin
    s_CLK <= '0';
    wait for gCLK_HPER;
    s_CLK <= '1';
    wait for gCLK_HPER;
  end process;
  
  -- Testbench process  
  P_TB: process
  begin

     -- Test 1: Check that the initial values are available in the output registers
    s_i_PC_4 <= X"EEEEEEEE";
    s_i_instruction <= X"EE0EE0EE"; -- shift by 10
    wait for cCLK_PER; 
    assert s_o_PC_4 = X"EEEEEEEE" and s_o_instruction = X"EE0EE0EE" report "Test 1 failed" severity error;

    -- Test 2: Check that new values can be inserted into the pipeline every cycle
    s_i_PC_4 <= X"01001091";
    s_i_instruction <= X"1022FF00"; -- shift by 10
    wait for cCLK_PER; 
    assert s_o_PC_4 = X"EEEEEEEE" and s_o_instruction = X"EE0EE0EE" report "Test 2 failed" severity error;

    -- Test 3: Check that the values from Test 1 are available in the output registers after 4 cycles
    wait for cCLK_PER*4;
    assert s_o_PC_4 = X"01001091" and s_o_instruction = X"1022FF00" report "Test 3 failed" severity error;

    -- Test 4: Check that the values from Test 2 are available in the output registers after 8 cycles
    wait for cCLK_PER*4;
    assert s_o_PC_4 = X"EEEEEEEE" and s_o_instruction = X"EE0EE0EE" report "Test 4 failed" severity error;


     assert false report "Testbench completed successfully" severity error;
  end process;
  
end behavior;
