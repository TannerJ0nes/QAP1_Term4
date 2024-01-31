//Written By Tanner Jones
//Date: Jan 26 2024
//QAP 1

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class SuggestionEngineTest {

    private final SuggestionEngine suggestionEngine = new SuggestionEngine();

    @Test
    public void testEmptyWordSuggestions() {
        suggestionEngine.setWordSuggestionDB(MockData());
        String suggestion = suggestionEngine.generateSuggestions("");
        Assertions.assertEquals("", suggestion);
    }

    @Test
    public void testCorrectWordSuggestions() {
        String suggestion = suggestionEngine.generateSuggestions("banana");
        Assertions.assertEquals("", suggestion);
    }

    @Test
    public void testMisspelledWordSuggestions() {
        suggestionEngine.setWordSuggestionDB(MockData());
        String suggestion = suggestionEngine.generateSuggestions("banna");
        Assertions.assertEquals("banana", suggestion);
    }

    @Test
    public void testPerformance() {
        suggestionEngine.setWordSuggestionDB(MockData());
        long startTime = System.currentTimeMillis();
        suggestionEngine.generateSuggestions("incorrectWord");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        Assertions.assertTrue(executeTime < 1000, "Test failed. Time exceeded.");
    }


    // Creating mock data for SuggestionDatabase
    private SuggestionsDatabase MockData() {
        SuggestionsDatabase database = new SuggestionsDatabase();
        Map<String, Integer> wordMap = new HashMap<>();
        wordMap.put("banana", 1);
        database.setWordMap(wordMap);
        return database;
    }
}
