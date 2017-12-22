package group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle;

/**
 * Created by RyoRyeong Kim on 2017-12-21.
 */

public class BoardData {

    String numberOfContent;         // 0 인경우는 베스트 청원임.
    String category;
    String title;
    String author;
    String term;
    String numberOfJoinPeople;
    String link;

    public String getNumberOfContent() {
        return numberOfContent;
    }

    public void setNumberOfContent(String numberOfContent) {
        this.numberOfContent = numberOfContent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getNumberOfJoinPeople() {
        return numberOfJoinPeople;
    }

    public void setNumberOfJoinPeople(String numberOfJoinPeople) {
        this.numberOfJoinPeople = numberOfJoinPeople;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
