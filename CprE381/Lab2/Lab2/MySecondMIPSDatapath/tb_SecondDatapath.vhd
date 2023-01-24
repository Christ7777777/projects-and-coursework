

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_textio.all;  
use IEEE.numeric_std.all;	
library std;
use std.env.all;                
use std.textio.all;             


entity tb_SecondDatapath is
  generic(gCLK_HPER   : time := 50 ns);
end tb_SecondDatapath;

architecture behavior of tb_SecondDatapath is
  
 
  constant cCLK_PER  : time := gCLK_HPER * 2;


  component SecondDatapath
  port(i_RST         : in std_logic;
       i_rdDataSel   : in std_logic;
       i_we	     : in std_logic;
       i_SignSel     : in std_logic; 
       i_CLK         : in std_logic;
       i_ALUSrc	     : in std_logic;
       i_nAdd_Sub    : in std_logic;
       i_rd          : in std_logic_vector(4 downto 0);
       i_rs          : in std_logic_vector(4 downto 0); 
       i_rt          : in std_logic_vector(4 downto 0); 
       i_immediate   : in std_logic_vector(15 downto 0);
       o_rsData      : out std_logic_vector(31 downto 0);
       o_mem      : out std_logic_vector(31 downto 0);
       o_rtData      : out std_logic_vector(31 downto 0));
  end component;

  signal s_CLK : std_logic := '0';

  signal s_RST: std_logic := '0';

  signal s_mem     : std_logic_vector(31 downto 0);
  signal s_rsData  : std_logic_vector(31 downto 0);
  signal s_rtData  : std_logic_vector(31 downto 0);

  signal s_rd      : std_logic_vector(4 downto 0) := (others => '0');
  signal s_rs      : std_logic_vector(4 downto 0) := (others => '0');
  signal s_rt      : std_logic_vector(4 downto 0) := (others => '0');

  signal ALUSrc    : std_logic := '0';
  signal nAdd_Sub  : std_logic := '0';
  signal s_SignSel : std_logic := '0';
  signal s_rdDataSel:std_logic := '0';
  signal s_we      : std_logic := '0';
  signal s_immediate : std_logic_vector(15 downto 0) := (others => '0');

begin

  DUT: SecondDatapath 
  port map(i_CLK => s_CLK,
           i_RST => s_RST,
           i_rs => s_rs,
           i_rt => s_rt,
           i_ALUSrc => ALUSrc,
           o_rsData => s_rsData,
           o_rtData => s_rtData,
	   i_nAdd_Sub => nAdd_Sub,
	   i_we       => s_we,
	   i_rdDataSel=> s_rdDataSel,
	   i_SignSel  => s_SignSel,
	   o_mem  => s_mem,
	   i_immediate => s_immediate,
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
  
    s_RST <= '1';
    wait for cCLK_PER;
    s_RST <= '0';

	--addi $25, $0, 0 
	s_rd <= "11001";
	s_rs <= "00000";
	s_immediate <= X"0000";
	ALUSrc <= '1';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '0';

	s_rt <= "11001"; 
	wait for cCLK_PER;

	--addi $26, $0, 256
	s_rd <= "11010";
	s_rs <= "00000";
	s_immediate <= X"0100";
	ALUSrc <= '1';
	 nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '0';

	s_rt <= "11010"; 
	wait for cCLK_PER;

	--lw $1, 0($25) 
	s_rd <= "00001";
	s_rs <= "11001";
	s_immediate <= X"0000";
	ALUSrc <= '1';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '1';

	s_rt <= "00001"; 
	wait for cCLK_PER;

	--lw $2, 4($25) 
	s_rd <= "00010";
	s_rs <= "11001";
	s_immediate <= X"0001";
	ALUSrc <= '1';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '1';

	s_rt <= "00010"; 
	wait for cCLK_PER;

	--add $1, $1, $2 
	s_rd <= "00001";
	s_rs <= "00001";
	s_rt <= "00010";
	ALUSrc <= '0';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '0';
	wait for cCLK_PER;

	--sw $1, 0($26) 
	s_rd <= "00000";
	s_rs <= "11010";
	s_rt <= "00001";
	s_immediate <= X"0000";
	ALUSrc <= '1';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '1';
	s_we <= '1';
	wait for cCLK_PER;
	s_we <= '0';

	--lw $2, 8($25)
	s_rd <= "00010";
	s_rs <= "11001";
	s_immediate <= X"0002";
	ALUSrc <= '1';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '1';

	s_rt <= "00010"; 
	wait for cCLK_PER;

	--add $1, $1, $2 
	s_rd <= "00001";
	s_rs <= "00001";
	s_rt <= "00010";
	ALUSrc <= '0';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '0';
	wait for cCLK_PER;

	--sw $1, 4($26)
	s_rd <= "00000";
	s_rs <= "11010";
	s_rt <= "00001";
	s_immediate <= X"0001";
	ALUSrc <= '1';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '1';
	s_we <= '1';
	wait for cCLK_PER;
	s_we <= '0';

	--lw $2, 12($25) 
	s_rd <= "00010";
	s_rs <= "11001";
	s_immediate <= X"0003";
	ALUSrc <= '1';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '1';

	s_rt <= "00010"; 
	wait for cCLK_PER;

	--add $1, $1, $2 
	s_rd <= "00001";
	s_rs <= "00001";
	s_rt <= "00010";
	ALUSrc <= '0';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '0';
	wait for cCLK_PER;

	--sw $1, 8($26) 
	s_rd <= "00000";
	s_rs <= "11010";
	s_rt <= "00001";
	s_immediate <= X"0002";
	ALUSrc <= '1';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '1';
	s_we <= '1';
	wait for cCLK_PER;
	s_we <= '0';

	--lw $2, 16($25) 
	s_rd <= "00010";
	s_rs <= "11001";
	s_immediate <= X"0004";
	ALUSrc <= '1';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '1';

	s_rt <= "00010"; 
	wait for cCLK_PER;

	--add $1, $1, $2 
	s_rd <= "00001";
	s_rs <= "00001";
	s_rt <= "00010";
	ALUSrc <= '0';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '0';
	wait for cCLK_PER;

	--sw $1, 12($26) 
	s_rd <= "00000";
	s_rs <= "11010";
	s_rt <= "00001";
	s_immediate <= X"0003";
	ALUSrc <= '1';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '1';
	s_we <= '1';
	wait for cCLK_PER;
	s_we <= '0';

	--lw $2, 20($25) 
	s_rd <= "00010";
	s_rs <= "11001";
	s_immediate <= X"0005";
	ALUSrc <= '1';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '1';

	s_rt <= "00010"; 
	wait for cCLK_PER;

	--add $1, $1, $2 
	s_rd <= "00001";
	s_rs <= "00001";
	s_rt <= "00010";
	ALUSrc <= '0';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '0';
	wait for cCLK_PER;

	--sw $1, 16($26) 
	s_rd <= "00000";
	s_rs <= "11010";
	s_rt <= "00001";
	s_immediate <= X"0004";
	ALUSrc <= '1';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '1';
	s_we <= '1';
	wait for cCLK_PER;
	s_we <= '0';

	--lw $2, 24($25) 
	s_rd <= "00010";
	s_rs <= "11001";
	s_immediate <= X"0006";
	ALUSrc <= '1';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '1';

	s_rt <= "00010"; 
	wait for cCLK_PER;

	--add $1, $1, $2 
	s_rd <= "00001";
	s_rs <= "00001";
	s_rt <= "00010";
	ALUSrc <= '0';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '0';
	wait for cCLK_PER;

	--addi $27, $0, 512 
	s_rd <= "11011";
	s_rs <= "00000";
	s_immediate <= X"0200";
	ALUSrc <= '1';
	nAdd_Sub <= '0';
	s_SignSel <= '1';
	s_rdDataSel <= '0';

	s_rt <= "11011"; 
	wait for cCLK_PER;

	--sw $1, -4($27) 
	s_rd <= "00000";
	s_rs <= "11010";
	s_rt <= "00001";
	s_immediate <= X"0001";
	ALUSrc <= '1';
	nAdd_Sub <= '1';
	s_SignSel <= '1';
	s_rdDataSel <= '1';
	s_we <= '1';
	wait for cCLK_PER;
	s_we <= '0';
	wait for cCLK_PER;


    wait;
  end process;
  
end behavior;
