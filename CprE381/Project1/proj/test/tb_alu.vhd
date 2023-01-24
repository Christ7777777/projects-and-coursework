


library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_textio.all;  -- For logic types I/O
use IEEE.numeric_std.all;	-- For to_usnigned
library std;
use std.env.all;                -- For hierarchical/external signals
use std.textio.all;             -- For basic I/O



entity tb_alu is
  generic(gCLK_HPER   : time := 50 ns);
end tb_alu;

architecture behavior of tb_alu is
  
  -- Calculate the clock period as twice the half-period
  constant cCLK_PER  : time := gCLK_HPER * 2;


  component alu
	port(i_A        : in std_logic_vector(31 downto 0);
             i_B        : in std_logic_vector(31 downto 0);
             i_aluOp    : in std_logic_vector(3 downto 0);
	     i_shamt    : in std_logic_vector(4 downto 0);
             o_F        : out std_logic_vector(31 downto 0);
             overFlow   : out std_logic;
             zero       : out std_logic);
  end component;

  
  signal s_CLK : std_logic := '0';

  signal s_A,s_B		: std_logic_vector(31 downto 0) := (others => '0');
  signal s_aluOp		: std_logic_vector(3 downto 0) := (others => '0');
  signal s_shamt		: std_logic_vector(4 downto 0);

  signal s_F			: std_logic_vector(31 downto 0);
  signal s_overFlow,s_zero	: std_logic;


begin

  DUT: alu 
  port map(i_A      => s_A,
           i_B      => s_B,
           i_aluOp  => s_aluOp,
	   i_shamt  => s_shamt,
	   o_F	    => s_F,
	   overFlow => s_overFlow,
	   zero     => s_zero);

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
  
  
  P_TB: process
  begin
    --test beq 
    
    s_aluOp <= "1011"; 
    s_A     <= X"FFFFFFFF";
    s_B     <= X"FFFFFFFF";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

    
    s_aluOp <= "1011"; 
    s_A     <= X"00001000";
    s_B     <= X"00000100";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

    
    s_aluOp <= "1011"; 
    s_A     <= X"00000000";
    s_B     <= X"00000000";
    s_shamt <= "01010"; 
    wait for cCLK_PER;


    -- test bne 
 
    s_aluOp <= "1100";
    s_A     <= X"FFFFFFFF";
    s_B     <= X"FFFFFFFF";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

    
    s_aluOp <= "1100"; 
    s_A     <= X"00001000";
    s_B     <= X"00000100";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

    
    s_aluOp <= "1100"; 
    s_A     <= X"00000000";
    s_B     <= X"00000000";
    s_shamt <= "01010"; 
    wait for cCLK_PER;


    -- test addu 
   
    s_aluOp <= "0000"; 
    s_A     <= X"00000000";
    s_B     <= X"00000001";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

  
    s_aluOp <= "0000"; 
    s_A     <= X"0EF10000";
    s_B     <= X"FFF41020";
    s_shamt <= "11111"; 
    wait for cCLK_PER;


    s_aluOp <= "0000"; 
    s_A     <= X"80000000";
    s_B     <= X"80000010";
    s_shamt <= "01010"; 
    wait for cCLK_PER;


-- test subu 

    s_aluOp <= "0001";
    s_A     <= X"FFFFFFFF";
    s_B     <= X"FFFFFFFE";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

  
    s_aluOp <= "0001"; 
    s_A     <= X"0EF10000";
    s_B     <= X"0FF41020";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

   
    s_aluOp <= "0001";
    s_A     <= X"7FFFFFFF";
    s_B     <= X"82000000";
    s_shamt <= "01010"; 
    wait for cCLK_PER;


  -- test add 
  
    s_aluOp <= "1110"; 
    s_A     <= X"00000000";
    s_B     <= X"00000001";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

   
    s_aluOp <= "1110"; 
    s_A     <= X"0EF10000";
    s_B     <= X"FFF41020";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

    
    s_aluOp <= "1110"; 
    s_A     <= X"80000000";
    s_B     <= X"80000010";
    s_shamt <= "01010"; 
    wait for cCLK_PER;


-- test sub 
  
    s_aluOp <= "1111"; 
    s_A     <= X"FFFFFFFF";
    s_B     <= X"FFFFFFFE";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

 
    s_aluOp <= "1111"; 
    s_A     <= X"0EF10000";
    s_B     <= X"0FF41020";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

   
    s_aluOp <= "1111"; 
    s_A     <= X"7FFFFFFF";
    s_B     <= X"82000000";
    s_shamt <= "01010"; 
    wait for cCLK_PER;


   -- test and
 
    s_aluOp <= "0010"; 
    s_A     <= X"FFFFFFFF";
    s_B     <= X"FFFEFFFF";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

    
    s_aluOp <= "0010"; 
    s_A     <= X"00001001";
    s_B     <= X"11111112";
    s_shamt <= "11100"; 
    wait for cCLK_PER;


 -- test or 
    
    s_aluOp <= "0011"; 
    s_A     <= X"FFFFFFFF";
    s_B     <= X"FFFEFFFF";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

 
    s_aluOp <= "0011"; 
    s_A     <= X"00001001";
    s_B     <= X"11111112";
    s_shamt <= "11100";
    wait for cCLK_PER;


 -- test xor
  
    s_aluOp <= "0100"; 
    s_A     <= X"FFFFFFFF";
    s_B     <= X"FFFEFFFF";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

    
    s_aluOp <= "0100"; 
    s_A     <= X"00001001";
    s_B     <= X"11111112";
    s_shamt <= "11100"; 
    wait for cCLK_PER;


    -- test nor 
    
    s_aluOp <= "0101"; 
    s_A     <= X"FFFFFFFF";
    s_B     <= X"FFFEFFFF";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

   
    s_aluOp <= "0101";
    s_A     <= X"00001001";
    s_B     <= X"11111112";
    s_shamt <= "11100";
    wait for cCLK_PER;


 -- test lui 
  
    s_aluOp <= "0110"; 
    s_A     <= X"EEEE0000"; 
    s_B     <= X"0000FFFF";
    s_shamt <= "11111";
    wait for cCLK_PER;

   
    s_aluOp <= "0110"; 
    s_A     <= X"FFFFFFFF"; 
    s_B     <= X"00008001";
    s_shamt <= "11100"; 
    wait for cCLK_PER;

-- test slt 
    
    s_aluOp <= "0111"; 
    s_A     <= X"FFFFFFFF";
    s_B     <= X"00000001";
    s_shamt <= "11111"; 
    wait for cCLK_PER;

  
    s_aluOp <= "0111"; 
    s_A     <= X"00FFFFFF";
    s_B     <= X"00FFFFFF";
    s_shamt <= "11100"; 
    wait for cCLK_PER;

   
    s_aluOp <= "0111"; 
    s_A     <= X"7FFFFFFF";
    s_B     <= X"80000000";
    s_shamt <= "11100"; 
    wait for cCLK_PER;


-- test sll 
    
    s_aluOp <= "1001"; 
    s_A     <= X"EEEE0000"; 
    s_B     <= X"0021FFFF";
    s_shamt <= "01100";
    wait for cCLK_PER;

   
    s_aluOp <= "1001"; 
    s_A     <= X"FFFFFFFF"; 
    s_B     <= X"FFFF0101";
    s_shamt <= "11111";
    wait for cCLK_PER;


-- test srl 
  
    s_aluOp <= "1000"; 
    s_A     <= X"EEEE0000";
    s_B     <= X"8021FFFF";
    s_shamt <= "01000";
    wait for cCLK_PER;

    
    s_aluOp <= "1000"; 
    s_A     <= X"FFFFFFFF";
    s_B     <= X"FFFF0101";
    s_shamt <= "11111";
    wait for cCLK_PER;


  -- test sra 

    s_aluOp <= "1010";
    s_A     <= X"EEEE0000"; 
    s_B     <= X"8021FFFF";
    s_shamt <= "01000";
    wait for cCLK_PER;

    
    s_aluOp <= "1010"; 
    s_A     <= X"FFFFFFFF";
    s_B     <= X"80000000";
    s_shamt <= "11111";
    wait for cCLK_PER;


    wait;
  end process;
  
end behavior;
