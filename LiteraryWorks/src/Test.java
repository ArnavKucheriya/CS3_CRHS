import java.util.Map;

public class Test {
	static String[] shakes;
	static String[] bacon;

	public static void main(String[] args) {
		FileInput.init();

		FileInput.init();
		shakes = FileInput.readShakespeare();
		bacon = FileInput.readBacon();

		System.out.println("Hash table");
		test(new ChainingHash(shakes), new QPHash(bacon));

		System.out.println();
	}

	public static void test(Map<String, Integer> shakesMap, Map<String, Integer> baconMap) {
		double totalSquaredError = 0;
		double mostDistant = 0;
		String mostDistantWord = "";

		for (String shakesWord : shakesMap.keySet()) {
			double shakesFrequency = ((double)shakesMap.get(shakesWord)) / shakes.length;

			if (baconMap.get(shakesWord) == null) {
				// word only appears in shakespear therefore only the frequency of shakespeare is added to the error
				totalSquaredError += Math.pow(shakesFrequency, 2);

				if (shakesFrequency > mostDistant) {
					// a new most distant word found that best represent the differnece in authors
					mostDistant = shakesFrequency;
					mostDistantWord = shakesWord;
				}
			} else {
				// word appears in both authors therefore the difference in frequency is added to the error
				double baconFrequency = ((double)baconMap.get(shakesWord)) / bacon.length;
				double difference = Math.abs(shakesFrequency - baconFrequency);
				totalSquaredError += Math.pow(difference, 2);

				if (difference > mostDistant) {
					mostDistant = difference;
					mostDistantWord = shakesWord;
				}
			}
		}

		for (String baconWord : baconMap.keySet()) {
			double baconFrequency = ((double)baconMap.get(baconWord)) / bacon.length;
			if (shakesMap.get(baconWord) == null) {
				// word only appears in bacon therefore only the frequency of bacon is added to the error
				totalSquaredError += Math.pow(baconFrequency, 2);

				if (baconFrequency > mostDistant) {
					mostDistant = baconFrequency;
					mostDistantWord = baconWord;
				}
			}
			// note that the case where the word appears in both authors is already handled in the previous loop
			// this loop only accounts for the words only present in bacon
		}

		System.out.println("Total squared error: " + totalSquaredError);
		System.out.println("Most distant word: " + mostDistantWord + " with a distance of " + mostDistant);
	}

}