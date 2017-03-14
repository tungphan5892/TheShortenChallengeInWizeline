package tungpt.wizelineremotechallenge.views.models;

/**
 * Created by tungphan on 3/9/17.
 */

public class NewTweetModel {

    private int tweetCount;
    private String tweetCountText = "140";

    public void setTweetCount(int tweetCount){
        this.tweetCount = tweetCount;
    }

    public int getTweetCount(){
        return tweetCount;
    }

    public void setTweetCountText(String tweetCountText){
        this.tweetCountText = tweetCountText;
    }

    public String getTweetCountText(){
        return tweetCountText;
    }
}
