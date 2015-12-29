package com.slack.api.client.param;

public class Param {
	public static final String CLIENT_ID = "client_id";
	/*
	 * The scope parameter is a space-separated list of OAuth scopes, indicating
	 * which parts of the Slack user’s account you’d like your app to be able to
	 * access.
	 */
	public static final String SCOPE = "scope";
	public static final String REDIRECT_URI = "redirect_uri";
	/*
	 * The state parameter should be used to avoid forgery attacks by passing in
	 * a value that's unique to the user you're authenticating and checking it
	 * when auth completes.
	 */
	public static final String STATE = "state";

	/*
	 * If you don't pass a team param, the user will be allowed to choose which
	 * team they are authenticating against. Passing this parameter ensures the
	 * user will auth against a specific team.
	 */
	public static final String TEAM = "team";
	
	public static final String TOKEN = "token";
	public static final String TEXT = "text";
	public static final String CHANNEL = "channel";
	public static final String USERNAME = "username";
	public static final String INCLUSIVE = "inclusive";
	public static final String COUNT = "count";
	public static final String UNREADS = "unreads";
	public static final String OLDEST = "oldest";
	public static final String LATEST = "latest";
	
	public static final String AS_USER = "as_user";
	public static final String CODE = "code";
	public static final String CLIENT_SECRET = "client_secret";
	public static final String RESPONSE_TYPE = "response_type";
}
