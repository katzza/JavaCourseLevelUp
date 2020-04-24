package streams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


class CollectionUtilsTest {
    @Test
    public void testRemoveLongStrings_whenCollectionIsEmpty_returnEmptyCollectionCopy() {
        Collection<String> emptyCol = new ArrayList<>();
        Collection<String> result = CollectionUtils.removeLongStrings(emptyCol, 10);
        Assertions.assertTrue(result.isEmpty());
        Assertions.assertNotSame(emptyCol, result);
    }

    @Test
    public void testRemoveLongStrings_whenCollectionIsNull_throwException() {
        Assertions.assertThrows(NullPointerException.class, () -> CollectionUtils.removeLongStrings(null, 10));
    }

    @Test
    public void testRemoveLongStrings_whenCollectionIsValid_returnFilteredCollection() {
        Collection<String> original = new ArrayList<>(Arrays.asList("String1","String2", "Long string"));
        Collection<String> result = CollectionUtils.removeLongStrings(original, 10);
        Assertions.assertNotEquals(original.size(),result.size());
        Assertions.assertEquals(2,result.size());
        boolean isAllStringsLenghtOk =result.stream().allMatch(string ->string.length()<=10);
        Assertions.assertTrue(isAllStringsLenghtOk);
    }


}