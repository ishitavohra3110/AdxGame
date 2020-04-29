package adx.variants.thirtydaysgame;

import java.util.HashSet;
import java.util.Set;

import adx.exceptions.AdXException;
import adx.structures.Campaign;
import adx.structures.SimpleBidEntry;
import adx.structures.MarketSegment;
import adx.test.MarketSegmentTest;

import adx.util.Logging;
/**
 * An example of a simple agent playing the TwoDays game.
 * 
 * @author Enrique Areyan Viqueira
 */
public class OverflowAgent extends ThirtyDaysThirtyCampaignsAgent {

  public OverflowAgent(String host, int port) {
    super(host, port);
  }

  @Override
  protected ThirtyDaysBidBundle getBidBundle(int day) {
    try {
      Campaign c = null;
      if (day <= 30) {
        Logging.log("[-] Bid for campaign " + day + " which is: " + this.setCampaigns[day - 1]);
        c = this.setCampaigns[day - 1];
      } 
      else {
        throw new AdXException("[x] Bidding for invalid day " + day + ", bids in this game are only for day 1 or 2.");
      }
      //Strategy
      int optimalBid = 2147483647;
      // Bidding only on the exact market segment of the campaign.
      Set<SimpleBidEntry> bidEntries = new HashSet<SimpleBidEntry>();
      Logging.log("Market Segment = " + c.getMarketSegment());
      Logging.log("Iterate over all Market Segments");
      MarketSegment segment = c.getMarketSegment();
      for (MarketSegment m: MarketSegment.values())//iterate over a market-segment
      {
        boolean ans = MarketSegment.marketSegmentSubset(segment,m);
        if (ans)
          bidEntries.add(new SimpleBidEntry(m,optimalBid,optimalBid));
      }
      
      Logging.log("[-] bidEntries = " + bidEntries);
      //return new TwoDaysBidBundle(day, c.getId(), c.getBudget(), bidEntries);
      return new ThirtyDaysBidBundle(day, c.getId(), c.getBudget(), bidEntries);
    } catch (AdXException e) {
      Logging.log("[x] Something went wrong getting the bid bundle: " + e.getMessage());
    }
    return null;
  }

  /**
   * Agent's main method.
   * 
   * @param args
   */
  public static void main(String[] args) {
    OverflowAgent agent = new OverflowAgent("localhost", 9898);
    agent.connect("agent_overflow");
  }

}
