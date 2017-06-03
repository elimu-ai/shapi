package fr.tvbarthel.apps.shapi.engine;

/**
 * The score of a game.
 */
class GameScore {

    private final int numberOfCorrectAnswers;
    private final int numberOfIncorrectAnswers;

    /**
     * Create a {@link GameScore}.
     *
     * @param numberOfCorrectAnswers   the number of correct answers.
     * @param numberOfIncorrectAnswers the number of incorrect answers.
     */
    GameScore(int numberOfCorrectAnswers, int numberOfIncorrectAnswers) {
        this.numberOfCorrectAnswers = numberOfCorrectAnswers;
        this.numberOfIncorrectAnswers = numberOfIncorrectAnswers;
    }

    /**
     * Get the number of correct answers.
     *
     * @return the number of correct answers.
     */
    int getNumberOfCorrectAnswers() {
        return numberOfCorrectAnswers;
    }

    /**
     * Get the number of incorrect answers.
     *
     * @return the number of incorrect answers.
     */
    int getNumberOfIncorrectAnswers() {
        return numberOfIncorrectAnswers;
    }
}
