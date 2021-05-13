package fontys.its3.hobbybook.domain;

import lombok.Data;

import javax.persistence.*;


//@Data
@Entity
@Table(name = "match_accounts")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long matchId;

    @ManyToOne
    @JoinColumn(name = "from_User",referencedColumnName = "id")
    private Account fromUser;

    @ManyToOne
    @JoinColumn(name = "to_User",referencedColumnName = "id")
    private Account toUser;
    
    @Column
    private boolean like_pass;

    public Match(Account fromUser, Account toUser, boolean like_pass) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.like_pass = like_pass;
    }

    public Match() {

    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Account getFromUser() {
        return fromUser;
    }

    public void setFromUser(Account fromUser) {
        this.fromUser = fromUser;
    }

    public Account getToUser() {
        return toUser;
    }

    public void setToUser(Account toUser) {
        this.toUser = toUser;
    }

    public boolean isLike_pass() {
        return like_pass;
    }

    public void setLike_pass(boolean like_pass) {
        this.like_pass = like_pass;
    }
}
