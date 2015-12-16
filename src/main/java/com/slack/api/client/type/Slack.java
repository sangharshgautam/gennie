package com.slack.api.client.type;

import org.apache.commons.lang3.StringUtils;


public class Slack {
	public enum Api implements Method{
		TEST;
		public String mName(){
			return StringUtils.join("api", ".", this.toString().toLowerCase());
		}
	}
	public enum Channel implements Method{
		ARCHIVE(com.slack.api.client.type.A.Channel.WRITE),
		CREATE(com.slack.api.client.type.A.Channel.WRITE),
		INVITE(com.slack.api.client.type.A.Channel.WRITE),
		JOIN(com.slack.api.client.type.A.Channel.WRITE),
		KICK(com.slack.api.client.type.A.Channel.WRITE),
		LEAVE(com.slack.api.client.type.A.Channel.WRITE),
		MARK(com.slack.api.client.type.A.Channel.WRITE),
		RENAME(com.slack.api.client.type.A.Channel.WRITE),
		SET_PURPOSE(com.slack.api.client.type.A.Channel.WRITE),
		SET_TOPIC(com.slack.api.client.type.A.Channel.WRITE),
		UNARCHIVE(com.slack.api.client.type.A.Channel.WRITE),
		
		HISTORY(com.slack.api.client.type.A.Channel.HISTORY),
		
		INFO(com.slack.api.client.type.A.Channel.READ),
		LIST(com.slack.api.client.type.A.Channel.READ);
		
		private com.slack.api.client.type.A.Channel scope;;
		Channel(com.slack.api.client.type.A.Channel scope){
			this.scope = scope;
		}
		public String mName(){
			return StringUtils.join("channels", ".", this.toString().toLowerCase());
		}
	}
	public enum Chat implements Method{
		DELETE(com.slack.api.client.type.A.Chat.WRITE_USER),
		UPDATE(com.slack.api.client.type.A.Chat.WRITE_USER),
		POST_MESSAGE(com.slack.api.client.type.A.Chat.WRITE_USER),
		;
		
		private com.slack.api.client.type.A.Chat scope;;
		Chat(com.slack.api.client.type.A.Chat scope){
			this.scope = scope;
		}
		public String mName(){
			return StringUtils.join("chat", ".", camelize(this.toString()));
		}
	}
	private static String camelize(String cn) {
        StringBuffer sb = new StringBuffer();
        String[] str = cn.split("_");
        boolean firstTime = true;
        for(String temp : str) {
             if(firstTime) {
                  sb.append     (temp.toLowerCase());
                  firstTime = false;
             } else {
                  sb.append(Character.toUpperCase(temp.charAt(0)));
                  sb.append(temp.substring(1).toLowerCase());
             }
        }
      return sb.toString();           
   }
}
