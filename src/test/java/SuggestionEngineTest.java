import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class SuggestionEngineTest {

    private SuggestionEngine suggestionEngine;

    @BeforeEach
    public void setUp() {
        suggestionEngine = new SuggestionEngine();
    }

    @Test
    public void testGenerateSuggestions_WordInDictionary() throws IOException {
        Path dictionaryFile = Paths.get("src/test/resources/dictionary.txt");
        suggestionEngine.loadDictionaryData(dictionaryFile);

        // Test when the word is already in the dictionary
        String suggestions = suggestionEngine.generateSuggestions("hello");
        assertEquals("", suggestions, "Should return an empty string for correct word");
    }

    @Test
    public void testGenerateSuggestions_WordNotInDictionary() throws IOException {
        Path dictionaryFile = Paths.get("src/test/resources/dictionary.txt");
        suggestionEngine.loadDictionaryData(dictionaryFile);

        // Test when the word is not in the dictionary
        String suggestions = suggestionEngine.generateSuggestions("helo");
        assertNotNull(suggestions, "Suggestions should not be null");
        assertFalse(suggestions.isEmpty(), "Suggestions should not be empty");
    }


    @Test
    public void testGenerateSuggestions_NoSuggestions() throws IOException {
        Path dictionaryFile = Paths.get("src/test/resources/dictionary.txt");
        suggestionEngine.loadDictionaryData(dictionaryFile);

        // Test when the word is correctly spelled and in the dictionary
        String suggestions = suggestionEngine.generateSuggestions("world");
        assertEquals("", suggestions, "Should return an empty string for correct word in dictionary");
    }

    @Test
    public void testGenerateSuggestions_EdgeCases() throws IOException {
        Path dictionaryFile = Paths.get("src/test/resources/dictionary.txt");
        suggestionEngine.loadDictionaryData(dictionaryFile);

        // Test edge cases, such as single-letter words or non-alphabetic characters
        String suggestions = suggestionEngine.generateSuggestions("a");
        assertNotNull(suggestions, "Suggestions should not be null");
        assertFalse(suggestions.isEmpty(), "Suggestions for single-letter word should not be empty");
    }
}
