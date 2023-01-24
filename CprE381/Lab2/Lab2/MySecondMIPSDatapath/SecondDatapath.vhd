
library IEEE;
use IEEE.std_logic_1164.all;


entity SecondDatapath is
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
end SecondDatapath;


architecture structural of SecondDatapath is

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

  component mem is
	generic (
		DATA_WIDTH : natural := 32;
		ADDR_WIDTH : natural := 10);

	port(
		clk		: in std_logic;
		addr	        : in std_logic_vector((ADDR_WIDTH-1) downto 0);
		data	        : in std_logic_vector((DATA_WIDTH-1) downto 0);
		we		: in std_logic := '1';
		q		: out std_logic_vector((DATA_WIDTH -1) downto 0));
  end component;

  component bit_extender_16t32 is
  port(i_SignSel    : in std_logic;
       i_D          : in std_logic_vector(15 downto 0);
       o_D          : out std_logic_vector(31 downto 0));
  end component;


  signal s_B : std_logic_vector(31 downto 0);
  signal o_ALU : std_logic_vector(31 downto 0);
  signal s_dff_0 : std_logic_vector(31 downto 0) := (others => '0');
  signal s_rdDataMux : std_logic_vector(31 downto 0);
  signal s_bit_encoderOut : std_logic_vector(31 downto 0);

begin

  inst_1: reg_file
  port map(
       i_RST => i_RST,
       i_CLK => i_CLK,
       i_rd  => i_rd,
       i_rs  => i_rs,
       i_rt  => i_rt,
       i_rdData => s_rdDataMux,
       o_rsData => o_rsData,
       o_rtData => o_rtData);

  inst_2: bit_extender_16t32
  port map(
       i_SignSel => i_SignSel,
       i_D	 => i_immediate,
       o_D	 => s_bit_encoderOut);

  inst_3: mux2t1_N
	generic map(N=>32)
	port map(i_S => i_ALUSrc,
		 i_D0 => o_rtData,
		 i_D1 => s_bit_encoderOut,
		 o_O => s_B);

  inst_4: AddSub_N
  generic map(N => 32)
  port map(i_A => o_rsData,
       i_B => s_B,
       i_nAdd_Sub => i_nAdd_Sub,
       o_S	  => o_ALU);

  inst_5: mux2t1_N
	generic map(N=>32)
	port map(i_S => i_rdDataSel,
		 i_D0 => o_ALU,
		 i_D1 => o_mem,
		 o_O => s_rdDataMux);
  inst_6: mem
	port map(addr => o_ALU(9 downto 0),
		 we => i_we,
		 data => o_rtData,
		 clk  => i_CLK,
		 q => o_mem);
  
end structural;
