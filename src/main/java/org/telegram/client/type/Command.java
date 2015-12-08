package org.telegram.client.type;

public enum Command {
	WHOAMI,
	HI,
	TICTACTOE,
	X,
	O,
	O1,
	O2,
	O3,
	O4,
	O5,
	O6,
	O7,
	O8,
	O9,
	X1,
	X2,
	X3,
	X4,
	X5,
	X6,
	X7,
	X8,
	X9,
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
