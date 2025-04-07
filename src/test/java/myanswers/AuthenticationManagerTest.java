package myanswers;

import org.junit.Test;

public class AuthenticationManagerTest {
    @Test
    public void test(){
        /*
        ["AuthenticationManager","countUnexpiredTokens","countUnexpiredTokens","countUnexpiredTokens","countUnexpiredTokens","generate","generate","countUnexpiredTokens","countUnexpiredTokens","countUnexpiredTokens","renew","renew","countUnexpiredTokens","renew","countUnexpiredTokens","generate"]
        [[5],[6],[9],[10],[15],["kzuvq",16],["lg",17],[18],[19],[20],["pab",22],["panci",23],[27],["nsluj",28],[29],["uvf",30]]
         */
        AuthenticationManager authenticationManager = new AuthenticationManager(5);
        System.out.println(authenticationManager.countUnexpiredTokens(6));
        System.out.println(authenticationManager.countUnexpiredTokens(9));
        System.out.println(authenticationManager.countUnexpiredTokens(10));
        System.out.println(authenticationManager.countUnexpiredTokens(15));
        authenticationManager.generate("kzuvq", 16);
        authenticationManager.generate("lg", 17);
        System.out.println(authenticationManager.countUnexpiredTokens(18));
        System.out.println(authenticationManager.countUnexpiredTokens(19));
        System.out.println(authenticationManager.countUnexpiredTokens(20));

        authenticationManager.renew("pab", 22);
        authenticationManager.renew("panci", 23);
        System.out.println(authenticationManager.countUnexpiredTokens(27));
        authenticationManager.renew("nsluj", 28);
        System.out.println(authenticationManager.countUnexpiredTokens(29));
        authenticationManager.generate("uvf", 30);
    }
}
