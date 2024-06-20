library IEEE;
use IEEE.std_logic_1164.all;


entity mealy_machine_state is

	port(

			rst : in std_logic;
			clk : in std_logic;
			output : out std_logic;
			d_s : out std_logic_vector(1 downto 0)
		);

end mealy_machine_state;


architecture arch1 of mealy_machine_state is
    type STATE_TYPE is (s0, s1, s2, s3);
    signal state : STATE_TYPE;

begin

    input_P: process (clk, rst, input)
    begin
        if rst = '1' then
            state <= s0;
            output <= '0';
        elsif clk'event and clk = '1' then
            case state is
                when s0 =>
                    if input(1 downto 0) = "00" or input = "11" then
                        state <= s0;
                        output <= '0';
                    elsif input(1 downto 0) = "01" then
                        state <= s1;
                        output <= '0';
                    elsif input(1 downto 0) = "10" then
                        state <= s2;
                        output <= '0';
                    end if;
                when s1 =>
                    if input(1 downto 0) = "00" or input = "11" then
                        state <= s1;
                        output <= '0';
                    elsif input(1 downto 0) = "01" then
                        state <= s2;
                        output <= '0';
                    elsif input(1 downto 0) = "10" then
                        state <= s3;
                        output <= '0';
                    end if;
                when s2 =>
                    if input(1 downto 0) = "00" or input = "11" then
                        state <= s2;
                        output <= '0';
                    elsif input(1 downto 0) = "01" then
                        state <= s3;
                        output <= '0';
                    elsif input(1 downto 0) = "10" then
                        state <= s0;
                        output <= '1';
                    end if;
                when s3 =>
                    if input(1 downto 0) = "00" or input = "11" then
                        state <= s3;
                        output <= '0';
                    elsif input(1 downto 0) = "01" then
                        state <= s0;
                        output <= '1';
                    elsif input(1 downto 0) = "10" then
                        state <= s1;
                        output <= '1';
                    end if;
            end case;
        end if;
    end process;

    output_P: process(state)
    begin
        case state is
            when s0 =>
                d_s <= "00";
            when s1 =>
                d_s <= "01";
            when s2 =>
                d_s <= "10";
            when s3 =>
                d_s <= "11";
        end case;
    end process;

end arch1;





