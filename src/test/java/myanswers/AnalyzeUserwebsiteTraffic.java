package myanswers;


import org.junit.Test;

import java.util.*;

public class AnalyzeUserwebsiteTraffic {

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {

        Map<String, List<Pair>> userVisitedSites = new HashMap<>();

        Map<String, Integer> tripletCount = new HashMap<>();

        Pair resultingPattern = new Pair(0, "");


        for (int i = 0; i <= website.length - 1; i++) {

            if (!userVisitedSites.containsKey(username[i])) {
                List<Pair> first = new ArrayList<>();
                first.add(new Pair(timestamp[i], website[i]));
                userVisitedSites.put(username[i], first);
            } else {
                userVisitedSites.get(username[i]).add(new Pair(timestamp[i], website[i]));
            }
        }

        //sort the userVisitedSites of this user based on timestamp

        Comparator<Pair> comparator = new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.timestamp - o2.timestamp;
            }
        };

        //generate the triplet



        for (String user : userVisitedSites.keySet()) {

            Set<String> duplicates = new HashSet<>();
            Collections.sort(userVisitedSites.get(user), comparator);
            List<Pair> sites = userVisitedSites.get(user);


            for (int p = 0; p <= sites.size() - 3; p++) {
                for (int q = p + 1; q <= sites.size() - 2; q++) {
                    for (int r = q + 1; r <= sites.size() - 1; r++) {

                        String pattern = sites.get(p).site + " " + sites.get(q).site + " " + sites.get(r).site;
                        if (!duplicates.contains(pattern)) {
                            duplicates.add(pattern);
                            tripletCount.put(pattern, tripletCount.getOrDefault(pattern, 0) + 1);

                            if (resultingPattern.site.isEmpty() || tripletCount.get(pattern) > resultingPattern.timestamp
                                    || tripletCount.get(pattern) == resultingPattern.timestamp && pattern.compareTo(resultingPattern.site) < 0
                            ) {
                                resultingPattern.timestamp = tripletCount.get(pattern);
                                resultingPattern.site = pattern;
                            }
                        }

                    }
                }
            }
        }


        List<String> results = new ArrayList<>();
        for (
                String s : resultingPattern.site.split("\\s+")) {
            results.add(s);
        }
        return results;
    }


    @Test
    public void test() {
        String[] username = new String[]{"h","eiy","cq","h","cq","txldsscx","cq","txldsscx","h","cq","cq"};
        int[] timestamp = new int[]{527896567,334462937,517687281,134127993,859112386,159548699,51100299,444082139,926837079,317455832,411747930};
        String[] websites = new String[]{"hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","yljmntrclw","hibympufi","yljmntrclw"};

        System.out.println(mostVisitedPattern(username, timestamp, websites));
    }
}

class Pair {
    String site;
    int timestamp;

    public Pair(int timestamp, String site) {
        this.site = site;
        this.timestamp = timestamp;

    }
}
