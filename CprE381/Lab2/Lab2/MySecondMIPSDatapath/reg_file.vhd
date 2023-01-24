
library IEEE;
use IEEE.std_logic_1164.all;
use work.STD_LOGIC_MATRIX.all;

entity reg_file is
  port(i_RST         : in std_logic;
       i_CLK         : in std_logic;
       i_rd          : in std_logic_vector(4 downto 0); 
       i_rs          : in std_logic_vector(4 downto 0); 
       i_rt          : in std_logic_vector(4 downto 0); 
       i_rdData      : in std_logic_vector(31 downto 0);
       o_rsData      : out std_logic_vector(31 downto 0);
       o_rtData      : out std_logic_vector(31 downto 0));
end reg_file;


architecture structural of reg_file is

  component mux32t1_N is
  port(i_S          : in std_logic_vector(4 downto 0);
       i_D          : in std_logic_matrix(31 downto 0);
       o_O          : out std_logic_vector(31 downto 0));
  end component;

  component dffg_N is
  generic(N : integer := 32); 
  port(i_CLK        : in std_logic;     
       i_RST        : in std_logic;     
       i_WE         : in std_logic;    
       i_D          : in std_logic_vector(N-1 downto 0);     
       o_Q          : out std_logic_vector(N-1 downto 0));   
  end component;

  component decoder_5t32 is
  port(i_A          : in std_logic_vector(4 downto 0);	
       o_Y          : out std_logic_vector(31 downto 0));
  end component;

  signal s_Y : std_logic_vector(31 downto 0);
  signal s_dff_0 : std_logic_vector(31 downto 0) := (others => '0');
  signal s_D : std_logic_matrix(31 downto 0);

begin

  dec_c1: decoder_5t32
	port map(i_A => i_rd,
		 o_Y => s_Y);

  d_n_c2: dffg_N 
	generic map(N=>32)
        port map(i_CLK => i_CLK,
		 i_RST => i_RST,
		 i_WE  => s_Y(0),
		 i_D   => s_dff_0,
		 o_Q   => s_D(0));

  
  d_n_c3: for i in 1 to 31 generate
    x3: dffg_N
	generic map(N=>32)
        port map(i_CLK => i_CLK,
		 i_RST => i_RST,
		 i_WE  => s_Y(i),
		 i_D   => i_rdData,
		 o_Q   => s_D(i));
  end generate d_n_c3;

  d_n_c5: mux32t1_N
	port map(i_S => i_rs,
		 i_D => s_D,
		 o_O => o_rsData);
  d_n_c6: mux32t1_N
	port map(i_S => i_rt,
		 i_D => s_D,
		 o_O => o_rtData);
  
end structural;
