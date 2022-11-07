import java.util.HashMap;

public class SeedList {
    private final HashMap<String, QuizDataInterface> seedList = new HashMap<>();

    public SeedList addSeedItem(final String key, final QuizDataInterface val) {
        seedList.putIfAbsent(key, val);
        return this;
    }

    public QuizDataInterface getSeedItem(final String key) {
        return seedList.getOrDefault(key, null);
    }

    public boolean containsSeedKey(final String key) {
        return seedList.containsKey(key);
    }
}
