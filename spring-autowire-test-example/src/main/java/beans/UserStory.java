package beans;

/**
 * Class UserStory
 * Description: An example bean that models a user story.
 * Uses the standard story form:
 *      "As a ______" (user role), "I want to ______" (actionDesired)
 *      "so that _____" (outcomeDesired).
 */
public class UserStory {
    private User user;
    public String name;
    
    public String arg;
    public User user2;
    
    private String actionDesired = "";
    private String outcomeDesired = "";
    public UserStory() {
    }

    public UserStory(User user2, String arg){
    	this.user2 = user2;
    	this.arg = arg;
    }
    public User getUser() {
        return user;
    }

    public void setName(String name){
    	this.name = name;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public void setUser2(User user2){
    	this.user2 = user2;
    }
    
    public String getActionDesired() {
        return actionDesired;
    }

    public void setActionDesired(String actionDesired) {
        this.actionDesired = actionDesired;
    }

    public String getOutcomeDesired() {
        return outcomeDesired;
    }

    public void setOutcomeDesired(String outcomeDesired) {
        this.outcomeDesired = outcomeDesired;
    }
}
