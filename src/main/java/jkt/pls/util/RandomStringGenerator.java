package jkt.pls.util;

import java.util.random.RandomGenerator;

public class RandomStringGenerator {
    // 사용할 문자 범위 (영대소문자 + 숫자)
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    // Java 21의 기본 랜덤 생성기
    private static final RandomGenerator RNG = RandomGenerator.getDefault();

    /**
     * 지정한 길이의 랜덤 문자열을 생성합니다.
     *
     * @param length 생성할 문자열 길이
     * @return 랜덤 문자열
     */
    public static String generate(int length) {
        var sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int idx = RNG.nextInt(CHAR_POOL.length());
            sb.append(CHAR_POOL.charAt(idx));
        }
        return sb.toString();
    }    
}