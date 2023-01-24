
library IEEE;
use IEEE.std_logic_1164.all;


entity FirstDatapath is
  port(i_RST         : in std_logic;
       i_CLK         : in std_logic;
       i_ALUSrc	     : in std_logic;
       i_nAdd_Sub    : in std_logic;
       i_rd          : in std_logic_vector(4 downto 0);
       i_rs          : in std_logic_vector(4 downto 0); 
       i_rt          : in std_logic_vector(4 downto 0); 
       i_immediate   : in std_logic_vector(31 downto 0);
       o_ALU	     : out std_logic_vector(31 downto 0);
       o_rsData      : out std_logic_vector(31 downto 0);
       o_rtData      : out std_logic_vector(31 downto 0));
end FirstDatapath;


architecture structural of FirstDatapath is

  component mux2t1_N is
  generic(N : integer := 16); 
  port(i_S          : in std_logic;
       i_D0         : in std_logic_vector(N-1 downto 0);
       i_D1         : in std_logic_vector(N-1 downto 0);
       o_O          : out std_logic_vector(N-1 downto 0));
  end component;

  component reg_file is
  port(i_RST         : in std_logic;
       i_CLK         : in std_logic;
       i_rd          : in std_logic_vector(4 downto 0); 
       i_rs          : in std_logic_vector(4 downto 0); 
       i_rt          : in std_logic_vector(4 downto 0); 
       i_rdData      : in std_logic_vector(31 downto 0);
       o_rsData      : out std_logic_vector(31 downto 0);
       o_rtData      : out std_logic_vector(31 downto 0));
  end component;

  component AddSub_N is
  generic(N : integer := 32);  
  port(i_A          : in std_logic_vector(N-1 downto 0);
       i_B	    : in std_logic_vector(N-1 downto 0);
       i_nAdd_Sub   : in std_logic;
       o_S	    : out std_logic_vector(N-1 downto 0);
       o_C          : out std_logic);
  end component;



  signal s_B : std_logic_vector(31 downto 0);
  signal s_dff_0 : std_logic_vector(31 downto 0) := (others => '0');

begin

  inst_1: reg_file
  port map(
       i_RST => i_RST,
       i_CLK => i_CLK,
       i_rd  => i_rd,
       i_rs  => i_rs,
       i_rt  => i_rt,
       i_rdData => o_ALU,
       o_rsData => o_rsData,
       o_rtData => o_rtData);

  inst_2: mux2t1_N
	generic map(N=>32)
	port map(i_S => i_ALUSrc,
		 i_D0 => o_rtData,
		 i_D1 => i_immediate,
		 o_O => s_B);

  inst_3: AddSub_N
  generic map(N => 32)
  port map(i_A => o_rsData,
       i_B => s_B,
       i_nAdd_Sub => i_nAdd_Sub,
       o_S	  => o_ALU);
  
end structural;
