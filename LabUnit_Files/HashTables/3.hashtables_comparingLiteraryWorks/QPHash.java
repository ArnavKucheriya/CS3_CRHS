public class QPHash {
  public QPHash(){
   //TODO Implement a default constructor for QPHash
  }
  
  public QPHash(int startSize){
   //TODO Implement a constructor that instantializes the hash array to startSize.
  }

  /**
   * This function allows rudimentary iteration through the QPHash.
   * The ordering is not important so long as all added elements are returned only once.
   * It should return null once it has gone through all elements
   * @return Returns the next element of the hash table. Returns null if it is at its end.
   */
  public Object getNextKey(){
   //TODO returns the next key in the hash table.
   //You will need external tracking variables to account for this.
  }
  /**
   * Adds the key to the hash table.
   * If there is a collision, a new location should be found using quadratic probing.
   * If the key is already in the hash table, it increments that key's counter.
   * @param keyToAdd : the key which will be added to the hash table
   */
  public void insert(Object keyToAdd){
   //TODO Implement insert into the hash table.
   //If keyToAdd is already in the hash table, then increment its count.
  }
  /**
   * Returns the number of times a key has been added to the hash table.
   * @param keyToFind : The key being searched for
   * @return returns the number of times that key has been added.
   */
  public int findCount(Object keyToFind){
   //TODO Implement findCount such that it returns the number of times keyToFind
   // has been added to the data structure.
  }
  /**
   * Returns the number of keys in the hash table.
   * @return return keys
   */
  public int getSize(){
    //TODO
  }
}
