
library IEEE;
use IEEE.std_logic_1164.all;
entity alu is
	port(i_A    : in std_logic_vector(31 downto 0);
         i_B        : in std_logic_vector(31 downto 0);
         i_aluOp    : in std_logic_vector(3 downto 0);
	 i_shamt    : in std_logic_vector(4 downto 0);
         o_F        : out std_logic_vector(31 downto 0);
         overFlow   : out std_logic;
         zero       : out std_logic);
end alu;


architecture mixed of alu is


signal adderOutput, barrelOutput, s_out : std_logic_vector(31 downto 0);
signal s_overflowControl,s_addSuboverFlow	 : std_logic;


component barrel_shifter is
	port(i_data		: in std_logic_vector(31 downto 0);
	     i_shamt  	  	: in std_logic_vector(4 downto 0);
	     i_shft_dir	  	: in std_logic; 
	     i_shft_type	: in std_logic; 
	     o_data     	: out std_logic_vector(31 downto 0));
    end component;

component beq_bne is
	port(i_F		: in std_logic_vector(31 downto 0);
	     i_equal_type  	: in std_logic; 
	     o_zero     	: out std_logic);
    end component;

component alu_addersubtractor is
    generic(N : integer := 32); 
    	port( nAdd_Sub          : in std_logic;
              i_A 	        : in std_logic_vector(N-1 downto 0);
              i_B		: in std_logic_vector(N-1 downto 0);
              o_Y		: out std_logic_vector(N-1 downto 0);
              o_Overflow	: out std_logic);
    end component;

  component andg2 is
     port(i_A          : in std_logic;
          i_B          : in std_logic;
          o_F          : out std_logic);
  end component;

begin

    with i_aluOp select s_overflowControl <=
	'1' when "1110",
	'1' when "1111",
	'0' when others;

    shifter: barrel_shifter
	port map(i_data		=> i_B,
	     i_shamt  	  	=> i_shamt,
	     i_shft_dir	  	=> i_aluOp(0),
	     i_shft_type	=> i_aluOp(1),
	     o_data     	=> barrelOutput);

    beq_bne_block: beq_bne
	port map(i_F 		=> s_out,
	     i_equal_type 	=> i_aluOp(0),
	     o_zero		=> zero);

    addsub: alu_addersubtractor
    generic map(N => 32)
    port map( nAdd_Sub     => i_aluOp(0),
            i_A 	   => i_A,
            i_B		   => i_B,
            o_Y		   => adderOutput,
            o_Overflow	   => s_addSuboverFlow);

    overflow_control: andg2
    port map(i_A => s_overflowControl,
	    i_B => s_addSuboverFlow,
	    o_F => overFlow);

    process(i_aluOp, i_A, i_B,adderOutput,barrelOutput,s_addSuboverFlow) 
    begin 
        if(i_aluOp = "0010") then
            for i in 0 to 31 loop
                s_out(i) <= i_A(i) AND i_B(i); 
            end loop;
        elsif(i_aluOp = "0011") then
            for i in 0 to 31 loop
                s_out(i) <= i_A(i) OR i_B(i); 
            end loop;
        elsif(i_aluOp = "0100" or i_aluOp = "1011" or i_aluOp = "1100") then 
            for i in 0 to 31 loop
                s_out(i) <= i_A(i) XOR i_B(i);
            end loop;
        elsif(i_aluOp = "0101") then
            for i in 0 to 31 loop
                s_out(i) <= i_A(i) NOR i_B(i); 
            end loop;
        elsif(i_aluOp = "0111") then 
            for i in 1 to 31 loop
                s_out(i) <= '0';
            end loop;
            s_out(0) <= adderOutput(31) XOR s_addSuboverFlow;
        elsif(i_aluOp = "0110") then 
            for i in 0 to 15 loop
                s_out(i) <= '0';
            end loop;
            for i in 16 to 31 loop
                s_out(i) <= i_B(i-16);
            end loop;
	elsif(i_aluOp = "1001" or i_aluOp = "1000" or i_aluOp = "1010") then 
	    s_out    <= barrelOutput;
        elsif(i_aluOp = "0000" or i_aluOp = "0001" or i_aluOp = "1110" or i_aluOp = "1111" ) then 
	    s_out <= adderOutput;
           
        else
                s_out <= x"00000000"; 
        end if;
    end process;
    o_F <= s_out;
end mixed;
