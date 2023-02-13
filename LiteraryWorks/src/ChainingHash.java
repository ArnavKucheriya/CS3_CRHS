import java.util.*;

public class ChainingHash implements Map<String, Integer> {
  private int size;
  private int capacity;
  private List<WordCount>[] table;

  private Iterator<String> keys;

  public ChainingHash() {
    size = 0;
    capacity = 16;
    table = new LinkedList[capacity];
  }

  public ChainingHash(int startSize) {
    size = 0;
    capacity = startSize;
    table = new LinkedList[capacity];
  }

  public ChainingHash(String[] words) {
    this();
    for (String word : words) {
      insert(word);
    }
  }

  /**
   * Adds the key to the hash table.
   * If there is a collision, it should be dealt with by chaining the keys
   * together.
   * If the key is already in the hash table, it increments that key's counter.
   *
   * @param key ToAdd : the key which will be added to the hash table
   */
  public void insert(Object key) {
    put(checkKey(key), 1);
  }

  private String checkKey(Object key) {
    if (!(key instanceof String))
      throw new IllegalStateException("Key must be a String");
    return (String) key;
  }

  /**
   * This function allows rudimentary iteration through the QPHash.
   * The ordering is not important so long as all added elements are returned only
   * once.
   * It should return null once it has gone through all elements
   *
   * @return Returns the next element of the hash table. Returns null if it is at
   *         its end.
   */
  public Object getNextKey() {
    if (keys == null)
      keys = keySet().iterator();
    else if (!keys.hasNext())
      return null;
    return keys.next();
  }

  /**
   * Returns the number of times a key has been added to the hash table.
   *
   * @param key ToFind : The key being searched for
   * @return returns the number of times that key has been added.
   */
  public int findCount(String key) {
    Integer count = get(key);
    return count == null ? 0 : count;
  }

  /**
   * Returns the number of keys in the hash table.
   *
   * @return return keys
   */
  public int getSize() {
    return size;
  }

  private int hash(Object key) {
    return Math.abs(key.hashCode()) % capacity;
  }

  private void rehash() {
    capacity = findNextPrime(capacity * 2);
    List<WordCount>[] oldTable = table;
    table = new LinkedList[capacity];
    size = 0;
    for (List<WordCount> bucket : oldTable) {
      if (bucket == null)
        continue;
      for (WordCount word : bucket)
        put(word.getWord(), word.getCount());
    }
  }

  private void checkRehash() {
    if (size > capacity / 4 * 3)
      rehash();
  }

  private int findNextPrime(int n) {
    if (n % 2 == 0)
      n++;
    while (!isPrime(n)) {
      n += 2;
    }
    return n;
  }

  private boolean isPrime(int n) {
    if (n == 2 || n == 3)
      return true;
    if (n == 1 || n % 2 == 0)
      return false;
    for (int i = 3; i * i <= n; i += 2) {
      if (n % i == 0)
        return false;
    }
    return true;
  }

  private WordCount getWordCount(Object key) {
    List<WordCount> bucket = table[hash(key)];
    if (bucket == null)
      return null;

    int indexOfKey;
    if (key instanceof String)
      indexOfKey = bucket.indexOf(new WordCount((String) key));
    else
      indexOfKey = bucket.indexOf(key);
    if (indexOfKey == -1)
      return null;

    return bucket.get(indexOfKey);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (List<WordCount> bucket : table) {
      if (bucket == null)
        continue;
      sb.append(bucket);
      sb.append("\n");
    }
    return sb.toString();
  }

  @Override
  public int size() {
    return getSize();
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public void clear() {
    size = 0;
    capacity = 16;
    table = new LinkedList[capacity];
  }

  @Override
  public boolean containsKey(Object key) {
    return getWordCount(key) != null;
  }

  @Override
  public boolean containsValue(Object value) {
    return get(value) != null;
  }

  @Override
  public Integer get(Object key) {
    WordCount word = getWordCount(key);
    return word == null ? null : word.getCount();
  }

  @Override
  public Integer put(String key, Integer value) {
    int index = hash(key);
    List<WordCount> bucket = table[index];
    size++;

    if (bucket == null) {
      bucket = new LinkedList<>();
      table[index] = bucket;
    }

    int indexOfKey = bucket.indexOf(new WordCount(key));

    // search if entry its in bucket
    if (indexOfKey == -1)
      bucket.add(new WordCount(key, value));
    else {
      // there's an entry then, no new entry is added to the buket
      size--;
      WordCount word = bucket.get(indexOfKey);
      word.incrementCount(value);
      return word.getCount() - value;
    }

    checkRehash();
    return null;
  }

  @Override
  public Integer remove(Object key) {
    int index = hash(key);
    List<WordCount> bucket = table[index];
    if (bucket == null)
      return null;
    int indexOfKey = bucket.indexOf(key);
    if (indexOfKey == -1)
      return null;
    return bucket.remove(indexOfKey).getCount();
  }

  @Override
  public void putAll(Map<? extends String, ? extends Integer> m) {
    m.keySet().forEach(k -> insert(k));
  }

  private Set<WordCount> pairs() {
    Set<WordCount> wordCounts = new HashSet<>();
    for (List<WordCount> bucket : table) {
      if (bucket == null)
        continue;
      for (WordCount word : bucket)
        wordCounts.add(word);
    }
    return wordCounts;
  }

  @Override
  public Collection<Integer> values() {
    List<Integer> values = new ArrayList<>();
    for (WordCount word : pairs())
      values.add(word.getCount());
    return values;
  }

  @Override
  public Set<String> keySet() {
    Set<String> keySet = new HashSet<>();
    for (WordCount word : pairs())
      keySet.add(word.getWord());
    return keySet;
  }

  @Override
  public Set<Entry<String, Integer>> entrySet() {
    Set<Entry<String, Integer>> entrySet = new HashSet<>();
    for (WordCount word : pairs())
      entrySet.add(new AbstractMap.SimpleEntry<String, Integer>(word.getWord(), word.getCount()));
    return entrySet;
  }

  public class WordCount {
    private String word;
    private Integer count;

    public WordCount(String word) {
      this.word = word;
      this.count = 1;
    }

    public WordCount(String word, Integer count) {
      this.word = word;
      this.count = count;
    }

    public WordCount() {
    }

    public String getWord() {
      return word;
    }

    public Integer getCount() {
      return count;
    }

    public void incrementCount() {
      count++;
    }

    public void decrementCount() {
      count--;
    }

    public void decrementCount(int count) {
      this.count -= count;
    }

    public void incrementCount(int count) {
      this.count += count;
    }

    @Override
    public int hashCode() {
      return word.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof WordCount)
        return word.equals(((WordCount) obj).getWord());
      else if (obj instanceof String)
        return word.equals((String) obj);
      return false;
    }

    @Override
    public String toString() {
      return "(" + word + ", " + count + ")";
    }
  }
}