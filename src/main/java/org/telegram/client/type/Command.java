package org.telegram.client.type;

public enum Command {
	WHOAMI,
	HI,
	TICTACTOE,
	X,
	O,
	QUIT,
//	CAPTCHA,
	UNKNOWN;
	public static Command lookup(String id) {
        try {
            return Command.valueOf(id);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
