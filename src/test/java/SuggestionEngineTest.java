//Written By Tanner Jones
//Date: Jan 26 2024
//QAP 1
//Dictionary File pulled from Jamie Cornick's GitHub


//Imports
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

    // Test for when the word is already in the dictionary
    @Test
    public void testGenerateSuggestions_WordInDictionary() throws IOException {
        Path dictionaryFile = Paths.get("src/test/resources/dictionary.txt");
        suggestionEngine.loadDictionaryData(dictionaryFile);

        String suggestions = suggestionEngine.generateSuggestions("hello");
        assertEquals("", suggestions, "Should return an empty string for correct word");
    }

    // Test for when the word is not in the dictionary
    @Test
    public void testGenerateSuggestions_WordNotInDictionary() throws IOException {
        Path dictionaryFile = Paths.get("src/test/resources/dictionary.txt");
        suggestionEngine.loadDictionaryData(dictionaryFile);

        String suggestions = suggestionEngine.generateSuggestions("helo");
        assertNotNull(suggestions, "Suggestions should not be null");
        assertFalse(suggestions.isEmpty(), "Suggestions should not be empty");
    }

    // Test for when the word is correctly spelled and in the dictionary
    @Test
    public void testGenerateSuggestions_NoSuggestions() throws IOException {
        //Path dictionaryFile = Paths.get("src/test/resources/dictionary.txt");
        suggestionEngine.loadDictionaryData(Path.of("src/test/resources/dictionary.txt"));

        String suggestions = suggestionEngine.generateSuggestions("world");
        assertEquals("", suggestions, "Should return an empty string for correct word in dictionary");
    }

    // Testing edge cases,  a single-letter words or non-alphabetic characters
    @Test
    public void testGenerateSuggestions_EdgeCases() throws IOException {
        Path dictionaryFile = Paths.get("src/test/resources/dictionary.txt");
        suggestionEngine.loadDictionaryData(dictionaryFile);

        String suggestions = suggestionEngine.generateSuggestions("a");
        assertNotNull(suggestions, "Suggestions should not be null");
        assertFalse(suggestions.isEmpty(), "Suggestions for single-letter word should not be empty");
    }
}
