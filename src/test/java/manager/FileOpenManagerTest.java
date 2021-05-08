package manager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
        assertEquals(expected,actual);
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
        List<String> tmp = new ArrayList<>(List.of(ap, np,mw));
        tmp.sort(String::compareToIgnoreCase);
        Set<String> expected = new HashSet<>(tmp);
        tmp = new ArrayList<>(manager.showApps());
        tmp.sort(String::compareToIgnoreCase);
        Set<String> actual = new HashSet<>(tmp);
        assertArrayEquals(actual.toArray(), expected.toArray());
    }
}