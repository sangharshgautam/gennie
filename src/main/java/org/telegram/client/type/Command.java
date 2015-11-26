package org.telegram.client.type;

import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.SendableText;

public enum Command {
	UNKNOWN,
	WHOAMI;
	public static Command lookup(String id) {
        try {
            return Command.valueOf(id);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
