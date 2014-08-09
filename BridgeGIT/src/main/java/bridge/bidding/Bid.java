package bridge.bidding;

import static bridge.core.deck.Trump.CLUBS;
import static bridge.core.deck.Trump.DIAMONDS;
import static bridge.core.deck.Trump.HEARTS;
import static bridge.core.deck.Trump.NOTRUMP;
import static bridge.core.deck.Trump.SPADES;
import bridge.core.deck.Clubs;
import bridge.core.deck.Diamonds;
import bridge.core.deck.Hearts;
import bridge.core.deck.NoTrump;
import bridge.core.deck.Spades;
import bridge.core.deck.Trump;

/**
 * 
 * @author pah
 * 
 * This class represents the typ eof bid that can be called
 */
public class Bid {
	public static Bid PASS = new Pass();
	public static Bid DOUBLE = new Double();
	public static Bid REDOUBLE = new Redouble();

	public static Bid ONE_NOTRUMP = new Bid(1, NOTRUMP);
	public static Bid ONE_SPADES = new Bid(1, SPADES);
	public static Bid ONE_HEARTS = new Bid(1, HEARTS);
	public static Bid ONE_DIAMONDS = new Bid(1, DIAMONDS);
	public static Bid ONE_CLUBS = new Bid(1, CLUBS);

	public static Bid TWO_NOTRUMP = new Bid(2, NOTRUMP);
	public static Bid TWO_SPADES = new Bid(2, SPADES);
	public static Bid TWO_HEARTS = new Bid(2, HEARTS);
	public static Bid TWO_DIAMONDS = new Bid(2, DIAMONDS);
	public static Bid TWO_CLUBS = new Bid(2, CLUBS);

	public static Bid THREE_NOTRUMP = new Bid(3, NOTRUMP);
	public static Bid THREE_SPADES = new Bid(3, SPADES);
	public static Bid THREE_HEARTS = new Bid(3, HEARTS);
	public static Bid THREE_DIAMONDS = new Bid(3, DIAMONDS);
	public static Bid THREE_CLUBS = new Bid(3, CLUBS);

	public static Bid FOUR_NOTRUMP = new Bid(4, NOTRUMP);
	public static Bid FOUR_SPADES = new Bid(4, SPADES);
	public static Bid FOUR_HEARTS = new Bid(4, HEARTS);
	public static Bid FOUR_DIAMONDS = new Bid(4, DIAMONDS);
	public static Bid FOUR_CLUBS = new Bid(4, CLUBS);

	public static Bid FIVE_NOTRUMP = new Bid(5, NOTRUMP);
	public static Bid FIVE_SPADES = new Bid(5, SPADES);
	public static Bid FIVE_HEARTS = new Bid(5, HEARTS);
	public static Bid FIVE_DIAMONDS = new Bid(5, DIAMONDS);
	public static Bid FIVE_CLUBS = new Bid(5, CLUBS);

	public static Bid SIX_NOTRUMP = new Bid(6, NOTRUMP);
	public static Bid SIX_SPADES = new Bid(6, SPADES);
	public static Bid SIX_HEARTS = new Bid(6, HEARTS);
	public static Bid SIX_DIAMONDS = new Bid(6, DIAMONDS);
	public static Bid SIX_CLUBS = new Bid(6, CLUBS);

	public static Bid SEVEN_NOTRUMP = new Bid(7, NOTRUMP);
	public static Bid SEVEN_SPADES = new Bid(7, SPADES);
	public static Bid SEVEN_HEARTS = new Bid(7, HEARTS);
	public static Bid SEVEN_DIAMONDS = new Bid(7, DIAMONDS);
	public static Bid SEVEN_CLUBS = new Bid(7, CLUBS);

	private final int bidLevel;
	private final Trump trump; // card suits plus noTrump
	private boolean forcing = false;
	private boolean gameForcing = false;
	private boolean doubled = false;

	public Bid(int bidLevelValue, Trump bidSuitIncludingNT) {
		this.bidLevel = bidLevelValue;
		this.trump = bidSuitIncludingNT;
	}

	/**
	 * Determines is this bid has greater bid value than  other bid
	 * 
	 * @param otherBid the other bid to be compared with
	 * @return True if this bid is greater than other bid
	 */
	public boolean greaterThan(Bid otherBid) {
		if (otherBid == null) {
			return true;
		}
		if (this.equals(new Pass())) {
			return false;
		}
		if (new Pass().equals(otherBid)) {
			return true;
		}
		if (getValue() > otherBid.getValue()) {
			return true;
		} else if (getValue() < otherBid.getValue()) {
			return false;
		} else {
			return isColorGreater(otherBid);
		}
	}

	/**
	 * In bridge trumps in increasing order are  
	 * Clubs, diamonds,hearts and spades
	 * 
	 * method returns true if thisBid  card suit (color) is
	 * greater than otherBid
	 * 
	 * @param 
	 * @return
	 */
	private boolean isColorGreater(Bid otherBid) {
		if (Clubs.i().equals(trump)) {
			return false;
		}
		if (trump.equals(Diamonds.i())) {
			if (otherBid.getTrump().equals(Clubs.i())) {
				return true;
			} else {
				return false;
			}
		}
		if (trump.equals(Hearts.i())) {
			if (otherBid.getTrump().equals(Clubs.i()) || otherBid.getTrump().equals(Diamonds.i())) {
				return true;
			} else {
				return false;
			}
		}
		if (trump.equals(Spades.i())) {
			if (otherBid.getTrump().equals(Clubs.i()) || otherBid.getTrump().equals(Diamonds.i())
					|| otherBid.getTrump().equals(Hearts.i())) {
				return true;
			} else {
				return false;
			}
		}
		if (!NoTrump.i().equals(otherBid.getTrump())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Make a bid (call) 
	 * 
	 * @param bidSize the levelof the bid (1-7)
	 * @param trump (clubs,diamonds,hearts, spades of NT, pass or double)
	 * @return
	 */
	public static Bid makeBid(int bidSize, String trump) {
		if (Pass.stringValue().equals(trump.toUpperCase())) {
			return new Pass();

		} else if (Double.stringValue().equals(trump.toUpperCase())) {
			return new Double();
		}
		return new Bid(bidSize, Trump.instance(trump));
	}

	/**
	 * Verifies this bid is a pass call
	 * 
	 * @param NONE
	 * @return True if bid is a pass call
	 */
	public boolean isPass() {
		return PASS.equals(this);
	}

	/**
	 * @param
	 * @return
	 */
	public boolean isForcing() {
		return forcing;
	}

	/**
	 * @param
	 * @return
	 */
	public boolean isGameForcing() {
		return gameForcing;
	}

	/**
	 * @param
	 * @return
	 */
	public void makeForcing() {
		forcing = true;

	}

	/**
	 * @param
	 * @return
	 */
	public void makeGameForcing() {
		forcing = true;
		gameForcing = true;

	}

	/**
	 * @param
	 * @return
	 */
	public boolean is1Suit() {
		if (getValue() == 1 && getTrump().isSuit()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param
	 * @return
	 */
	public Bid makeDoubled() {
		doubled = true;
		return this;
	}

	/**
	 * @param
	 * @return
	 */
	public boolean isDoubled() {
		return doubled;
	}

	/**
	 * Useful to distinguish Pass, Double, and Redouble
	 */
	public boolean hasTrump() {
		return getTrump() != null;
	}

	public static Bid cloneBid(Bid b) {
		if (b.hasTrump()) {
			return new Bid(b.getValue(), b.getTrump());
		} else if (b.isPass()) {
			return new Pass();
		} else if (b.isDouble()) {
			return new Double();
		}
		return null;
	}

	/**
	 * @param
	 * @return
	 */
	private boolean isDouble() {
		return DOUBLE.equals(this);
	}

	/**
	 * @param
	 * @return
	 */
	public String longDescription() {
		String result = toString();
		if (isDoubled()) {
			result += " (Doubled)";
		}
		return result;
	}
	
	/**
	 * Standard Class methods including getters and setters
	 */
	public int getValue() {
		return bidLevel;
	}
	
	public Trump getTrump() {
		return trump;
	}

	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Bid)) {
			return super.equals(other);
		} else {
			return bidLevel == ((Bid) other).getValue() && trump == ((Bid) other).getTrump();
		}
	}
	
	@Override
	public String toString() {
		return Integer.toString(getValue()) + " " + trump.toString();
	}
}
