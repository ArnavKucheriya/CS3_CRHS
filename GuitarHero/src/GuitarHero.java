
public class GuitarHero {
	public static void main(String[] args) {

		//set keyboard
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

		//set number of strings we want to create
		int numberOfStrings = 37;

		//create an array of guitar strings
		GuitarString[] arrayOfStrings = new GuitarString[numberOfStrings];

		//for loop: initialize array of guitar strings
		for (int i = 0; i < numberOfStrings; i++) {

			//calculate frequency for each string in array
			double frequency = 440 * Math.pow(2, ((i - 24) / 12.0));

			//fill that array spot with a guitar string
			arrayOfStrings[i] = new GuitarString(frequency);

		}


		// the main input loop
		while (true) {

			// check if the user has typed a key, and, if so, process it
			if (StdDraw.hasNextKeyTyped()) {

				// the user types this character
				char key = StdDraw.nextKeyTyped();

				// pluck the corresponding string
				int indexOfKeyboard = keyboard.indexOf(key);

				if (indexOfKeyboard == -1) {
					continue;
				}

				arrayOfStrings[indexOfKeyboard].pluck();
			}

			//define sample
			double sample = 0;

			//calculate superposition of samples
			for (int j = 0; j < arrayOfStrings.length; j++) {
				sample += arrayOfStrings[j].sample();

			}

			// send the result to standard audio
			StdAudio.play(sample);

			// advance the simulation of each guitar string by one step
			for (int j = 0; j < arrayOfStrings.length; j++) {
				arrayOfStrings[j].tic();
			}

		}
	}

}

//make sure program doesn't crash if key is played that isn't one of my notes!