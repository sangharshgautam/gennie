package com.slack.api.client.type;

import org.apache.commons.lang3.StringUtils;


public class Slack {
	public enum Api implements Method{
		TEST;
		public String mName(){
			return StringUtils.join("api", ".", this.toString().toLowerCase());
		}
		@Override
		public Scope scope() {
			return null;
		}
	}
	public enum Channel implements Method{
		ARCHIVE(SlackApi.Channel.WRITE),
		CREATE(SlackApi.Channel.WRITE),
		INVITE(SlackApi.Channel.WRITE),
		JOIN(SlackApi.Channel.WRITE),
		KICK(SlackApi.Channel.WRITE),
		LEAVE(SlackApi.Channel.WRITE),
		MARK(SlackApi.Channel.WRITE),
		RENAME(SlackApi.Channel.WRITE),
		SET_PURPOSE(SlackApi.Channel.WRITE),
		SET_TOPIC(SlackApi.Channel.WRITE),
		UNARCHIVE(SlackApi.Channel.WRITE),
		
		HISTORY(SlackApi.Channel.HISTORY),
		
		INFO(SlackApi.Channel.READ),
		LIST(SlackApi.Channel.READ);
		
		private SlackApi.Channel scope;
		Channel(SlackApi.Channel scope){
			this.scope = scope;
		}
		public String mName(){
			return StringUtils.join("channels", ".", this.toString().toLowerCase());
		}
		@Override
		public Scope scope() {
			return this.scope;
		}
	}
	public enum Chat implements Method{
		DELETE(SlackApi.Chat.WRITE_USER),
		UPDATE(SlackApi.Chat.WRITE_USER),
		POST_MESSAGE(SlackApi.Chat.WRITE_USER),
		;
		
		private SlackApi.Chat scope;
		Chat(SlackApi.Chat scope){
			this.scope = scope;
		}
		public String mName(){
			return StringUtils.join("chat", ".", camelize(this.toString()));
		}
		@Override
		public Scope scope() {
			return this.scope;
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
