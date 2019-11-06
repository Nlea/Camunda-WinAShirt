package org.camunda.devoxx.WinAShirt;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class VictoryTweetDelegate implements JavaDelegate {


	  private final Logger LOGGER = LoggerFactory.getLogger(VictoryTweetDelegate.class.getName());

	  public void execute(DelegateExecution execution) throws Exception {
		  
	    String name = (String)execution.getVariable("name");

	    String content = name + " WON THE #CAMUNDA QUIZ!!!! at #WJAX";
	    LOGGER.info("Publishing tweet: " + content);
	    AccessToken accessToken = new AccessToken("220324559-jet1dkzhSOeDWdaclI48z5txJRFLCnLOK45qStvo", "B28Ze8VDucBdiE38aVQqTxOyPc7eHunxBVv7XgGim4say");
	    Twitter twitter = new TwitterFactory().getInstance();
	    twitter.setOAuthConsumer("lRhS80iIXXQtm6LM03awjvrvk", "gabtxwW8lnSL9yQUNdzAfgBOgIMSRqh7MegQs79GlKVWF36qLS");
	    twitter.setOAuthAccessToken(accessToken);  
	    twitter.updateStatus(content);
	  }

}
