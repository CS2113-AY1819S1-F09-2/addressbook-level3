package seedu.addressbook.data.member;

import static seedu.addressbook.common.Messages.MESSAGE_NEGATIVE_POINTS;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents the number of membership point of a Member in the member list.
 */
public class Points {

    private int value;

    public Points() {
        this.value = 0;
    }

    public Points(int points) {
        this.value = points;
    }

    // public final String MESSAGE_NEGATIVE_POINTS = "Update points cannot result in negative points.";
    /**
     * Converts the price into points and adds in to the existing points for the member
     * @param price of the order being made
     * @return updated points
     */
    public Points updatePoints(double price, int pointsToRedeem) {
        try {
            this.value += ((int) price) / 10;
            this.value -= pointsToRedeem;
            if (this.value < 0) {
                throw new IllegalValueException(MESSAGE_NEGATIVE_POINTS);
            }
            return this;
        } catch (IllegalValueException e) {
            this.value = 0;
            return this;
        }

    }

    public int getPoints() {
        return this.value;
    }

    public void setPoints(int points) {
        this.value = points;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Points // instanceof handles nulls
                && this.toString().equals(((Points) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
    /*
    public boolean isPrivate() {
        return isPrivate;
    }
    */
}
