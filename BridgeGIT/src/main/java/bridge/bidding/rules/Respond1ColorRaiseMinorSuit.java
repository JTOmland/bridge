package bridge.bidding.rules;

import bridge.bidding.Auctioneer;
import bridge.bidding.Bid;
import bridge.bidding.ResponseCalculator;
import bridge.core.Hand;

public class Respond1ColorRaiseMinorSuit extends Response {

	private ResponseCalculator calculator;

	public Respond1ColorRaiseMinorSuit(Auctioneer a, Hand h) {
		super(a, h);
	}

	@Override
	protected boolean applies() {
		boolean result = false;
		if (super.applies()) {
			calculator = new ResponseCalculator(hand, partnersOpeningBid);
			if (partnersOpeningBid.getTrump().isMinorSuit() && partnersOpeningBid.getValue() == 1
					&& calculator.getCombinedPoints() >= 6
					&& hand.getSuitLength(partnersOpeningBid.getTrump().asSuit()) >= 4) {
				result = true;
			}
		}
		return result;
	}

	@Override
	protected Bid prepareBid() {
		if (calculator.getCombinedPoints() >= 6 && calculator.getCombinedPoints() <= 10) {
			return new Bid(2, partnersOpeningBid.getTrump());
		} else if (calculator.getCombinedPoints() >= 13 && calculator.getCombinedPoints() <= 16) {
			return new Bid(3, partnersOpeningBid.getTrump());
		} else {
			return null;
		}
	}

}
