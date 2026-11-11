package com.kandidato.model.dto.mark;

/**
 * Created by andriy on 1/9/15.
 */
public class MatchMark implements Mark {

    private static final long serialVersionUID = 1L;

    private final Long markeObjectId;

    private final Long matchId;

    public MatchMark(Long markeObjectId, Long matchId) {
        if (markeObjectId == null || matchId == null) {
            throw new IllegalArgumentException("MatchMark should not contain null references.");
        }
        this.markeObjectId = markeObjectId;
        this.matchId = matchId;
    }

    public Long getMatchingId() {
        return matchId;
    }

    @Override
    public Long getMarkedObjectId() {
        return markeObjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchMark)) return false;

        MatchMark matchMark = (MatchMark) o;

        if (!markeObjectId.equals(matchMark.markeObjectId)) return false;
        if (!matchId.equals(matchMark.matchId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = markeObjectId.hashCode();
        result = 31 * result + matchId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MatchMark{" +
                "markeObjectId=" + markeObjectId +
                ", matchId=" + matchId +
                '}';
    }
}
