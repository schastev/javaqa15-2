package manager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FileOpenManagerTest {
    FileOpenManager manager = new FileOpenManager();
    String psd = ".psd";
    String ap = "Adobe Photoshop";
    String doc = ".doc";
    String mw = "MS Word";
    String txt = ".txt";
    String np = "Notepad";


    @Nested
    public class AssociationNotFound {
        @BeforeEach
        void setUp() {
            manager.add(psd, ap);
            manager.add(doc, mw);
            manager.add(txt, np);
        }

        @AfterEach
        void cleanUp() {
            manager.removeAll();
        }

        @Test
        void findAppTest() {
            String actual = manager.findApp("foo");
            assertNull(actual);
        }

        @Test
        void removeAssociationTest() {
            manager.removeAssociation("foo");
            Set<String> expected = new HashSet<>();
            expected.add(psd);
            expected.add(txt);
            expected.add(doc);
            assertEquals(manager.showExtensions(), expected);
        }
    }

    @Nested
    public class AssociationFound {
        @BeforeEach
        void setUp() {
            manager.add(psd, ap);
            manager.add(doc, mw);
            manager.add(txt, np);
        }

        @AfterEach
        void cleanUp() {
            manager.removeAll();
        }

        @Test
        void findAppTest() {
            String expected = ap;
            String actual = manager.findApp(psd);
            assertEquals(expected, actual);
        }

        @Test
        void removeAssociationTest() {
            manager.removeAssociation(txt);
            Set<String> expected = new HashSet<>();
            expected.add(psd);
            expected.add(doc);
            assertEquals(manager.showExtensions(), expected);
        }

        @Test
        void showExtensionsTest() {
            Set<String> expected = new HashSet<>();
            expected.add(psd);
            expected.add(doc);
            expected.add(txt);
            assertEquals(manager.showExtensions(), expected);
        }

        @Test
        void showAppsTest() {
            //prepare the expected result, a sorted set to ensure order and lack of duplicates
            List<String> tmp = new ArrayList<>(List.of(ap, np, mw));
            tmp.sort(String::compareToIgnoreCase);
            Set<String> expected = new HashSet<>(tmp);
            //reuse the tmp variable store the actual result before turning it into a sorted set
            tmp = new ArrayList<>(manager.showApps());
            tmp.sort(String::compareToIgnoreCase);
            Set<String> actual = new HashSet<>(tmp);
            assertArrayEquals(actual.toArray(), expected.toArray());
        }
    }
}