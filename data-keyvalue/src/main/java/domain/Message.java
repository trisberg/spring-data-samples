package domain;

import java.util.Date;

public class Message {

	private String userId;
	
	private Date timestamp;
	
	private String messageText;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	@Override
	public String toString() {
		return "Message [userId=" + userId + ", timestamp=" + timestamp
				+ ", messageText=" + messageText + "]";
	}

}
