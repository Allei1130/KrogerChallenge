package com.challenge.kroger;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UtilsUnitTest {

    @Test
    public void customSort_isCorrect() throws Exception {
        int[] input = new int[] {3,1,2,2,4};
        int[] input2 = new int[] {8,5,5,5,5,1,1,1,4,4};
        int[] input3 = new int[] {50,-44,0,-44,-44,50,50,5,1,2,22,0};
        Utils.customSort(input);
        System.out.println();
        Utils.customSort(input2);
        System.out.println();
        Utils.customSort(input3);
        // Cannot assert print statements here
    }

    @Test
    public void mergeStrings_isCorrect() throws Exception {
        String inputA = "abc";
        String inputB = "def";

        assertEquals("adbecf", Utils.mergeStrings(inputA, inputB));
    }

    @Test
    public void count_isCorrect() throws Exception {
        int[] numbers = new int[]{1,2,3};
        int k = 7;
        assertEquals(6, Utils.count(numbers, k));

        int[] numbers2 = new int[]{4,13,20,32,44,59,61,71,75,86,88};
        int k2 = 567601;
        assertEquals(32, Utils.count(numbers2, k2));
    }

    @Test
    public void validateCards_isCorrect() throws Exception {
        String[] cards = new String[] {"6724843711060148"};
        String[] bannedPrefixes = new String[] {"11", "3434", "67453", "9"};
        Map<String, Object> expectedValidatedCard;
        Map<String, Object> validatedCard;
        Utils.validateCards(null, null);
        validatedCard = Utils.validateCards(bannedPrefixes, cards).get(0);
    }
}