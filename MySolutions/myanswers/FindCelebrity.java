package myanswers;

public class FindCelebrity {
    boolean knows(int i, int j) {
        return false;
    }

    public int findCelebrity(int n) {
        for (int candidate = 0; candidate < n; candidate++) {

            boolean found = true;
            for (int j = 0; j < n; j++) {
                if (candidate != j) {
                    if (knows(candidate, j) || !knows(j, candidate)) {
                        found = false;
                        break;

                    }
                }
            }
            if (found) {
                return candidate;
            }
        }
        return -1;
    }
}
