package bridge.bidding.rules;

import bridge.bidding.Auctioneer;
import bridge.bidding.Bid;
import bridge.bidding.Pass;
import bridge.bidding.PointCalculator;
import bridge.core.Hand;
import bridge.core.deck.Hearts;
import bridge.core.deck.NoTrump;
import bridge.core.deck.Spades;
import bridge.core.deck.Suit;

public class Respond1NT extends Response {

	private final PointCalculator pc;

	public Respond1NT(Auctioneer a, Hand h) {
		super(a, h);
		pc = new PointCalculator(hand);
	}

	@Override
	protected Bid prepareBid() {
		Bid result = null;
		Suit longer = Spades.i();
		if (hand.getSuitLength(Spades.i()) < hand.getSuitLength(Hearts.i())) {
			longer = Hearts.i();
		}
		if (hand.getSuitLength(longer) < 5) {
			if (pc.getHighCardPoints() <= 7) {
				result = new Pass();
			} else if (pc.getHighCardPoints() <= 9) {
				result = new Bid(2, NoTrump.i());
			} else if (pc.getHighCardPoints() <= 14) {
				result = new Bid(3, NoTrump.i());
			}
		} else if (pc.getCombinedPoints() <= 7) {
			if (hand.getSuitLength(longer) >= 5) {
				result = new Bid(2, longer);
			}
		} else if (pc.getCombinedPoints() >= 10) {
			if (hand.getSuitLength(longer) == 5) {
				result = new Bid(3, longer);
			} else if (hand.getSuitLength(longer) >= 6) {
				result = new Bid(4, longer);
			}
		}
		return result;
	}

	@Override
	protected boolean applies() {
		return super.applies() && new Bid(1, NoTrump.i()).equals(partnersOpeningBid);
	}
}
