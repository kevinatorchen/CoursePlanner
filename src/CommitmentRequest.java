/**
 * Created by Kevin on 7/20/2017.
 */
public class CommitmentRequest implements Comparable<CommitmentRequest> {
    private Commitment commitment;
    private String prof[];

    public CommitmentRequest(Commitment commitment) {
        this(commitment, null);
    }

    public CommitmentRequest(Commitment commitment, String[] prof) {
        this.commitment = commitment;
        this.prof = prof;
    }

    public boolean containsProf(String professor) {
        for (String currentProf: prof) {
            if (currentProf.equals(professor)) {
                return true;
            }
        }
        return false;
    }

    public Commitment getCommitment() {
        return commitment;
    }

    public void setCommitment(Commitment commitment) {
        this.commitment = commitment;
    }

    public String[] getProf() {
        return prof;
    }

    public void setProf(String[] prof) {
        this.prof = prof;
    }

    @Override
    public int compareTo(CommitmentRequest o) {
        return this.getCommitment().compareTo(o.getCommitment());
    }
}
