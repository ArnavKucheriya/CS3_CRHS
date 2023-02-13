import java.util.*;

public class QPHash implements Map<String, Integer> {
  private int size;
  private int capacity;
  private WordCount[] table;

  private Iterator<String> keys;

  public QPHash() {
    size = 0;
    capacity = 16;
    table = new WordCount[capacity];
  }

  public QPHash(int startSize) {
    size = 0;
    capacity = startSize;
    table = new WordCount[capacity];
  }

  public QPHash(String[] words) {
    this();
    for (String word : words) {
      insert(word);
    }
  }

  /**
   * Adds the key to the hash table.
   * If there is a collision, a new location should be found using quadratic
   * probing.
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
   * Returns the number of times a key has been added to the hash table.
   *
   * @param key ToFind : The key being searched for
   * @return returns the number of times that key has been added.
   */
  public int findCount(Object key) {
    Integer count = get(key);
    return count == null ? 0 : count;
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
   * Returns the number of keys in the hash table.
   *
   * @return return keys
   */
  public int getSize() {
    return size;
  }

  private int hash(Object key) {
    int i = 0;
    int hash = Math.abs(key.hashCode());
    int index = (hash + i * i) % capacity;
    while (table[index] != null) {
      if (key instanceof String && table[index].equals(new WordCount((String) key)))
        return index;
      else if (table[index].equals(key))
        return index;
      i++;
      index = (hash + i * i) % capacity;
    }
    return index;
  }

  private void rehash() {
    capacity = findNextPrime(capacity * 2);
    WordCount[] oldTable = table;
    table = new WordCount[capacity];
    size = 0;
    for (WordCount word : oldTable) {
      if (word != null)
        put(word.getWord(), word.getCount());
    }
  }

  private void checkRehash() {
    if (size > capacity / 2)
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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (WordCount word : table) {
      if (word != null)
        sb.append(word + " ");
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
    table = new WordCount[capacity];
  }

  @Override
  public boolean containsKey(Object key) {
    return get(key) != null;
  }

  @Override
  public boolean containsValue(Object value) {
    for (WordCount word : table) {
      if (word != null && word.getCount().equals(value))
        return true;
    }
    return false;
  }

  @Override
  public Integer get(Object key) {
    WordCount word = table[hash(key)];
    return word == null ? null : word.getCount();
  }

  @Override
  public Integer put(String key, Integer value) {
    WordCount word = table[hash(key)];
    size++;
    if (word != null) {
      // there's an entry with equal key then, no new entry must be added
      size--;
      word.incrementCount(value);
      return word.getCount() - value;
    }

    word = new WordCount(key, value);
    table[hash(key)] = word;
    checkRehash();
    return null;
  }

  @Override
  public Integer remove(Object key) {
    int index = hash(key);
    if (table[index] == null)
      return null;
    int count = table[index].getCount();
    table[index] = null;
    size--;
    return count;
  }

  @Override
  public void putAll(Map<? extends String, ? extends Integer> m) {
    m.keySet().forEach(key -> put(key, m.get(key)));
  }

  private Set<WordCount> pairs() {
    Set<WordCount> wordCounts = new HashSet<>();
    for (WordCount word : table) {
      if (word != null)
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