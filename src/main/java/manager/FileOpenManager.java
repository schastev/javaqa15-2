package manager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FileOpenManager {
    private Map<String, String> map = new HashMap<String, String>();

    public void add(String extension, String app) {
        map.put(extension.toLowerCase(Locale.ROOT), app);
    }

    public String findApp(String extension) {
        return map.get(extension);
    }

    public void removeAssociation(String extension) {
        map.remove(extension.toLowerCase(Locale.ROOT));
    }

    public Collection<String> showExtensions() {
        return map.keySet();
    }

    public Collection<String> showApps() {
        return map.values();
    }

    public void removeAll() {
        map.clear();
    }

}
