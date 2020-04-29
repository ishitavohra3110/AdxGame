package adx.variants.thirtydaysgame;

import java.util.HashSet;
import java.util.Set;
import java.util.Hashtable; 
import java.util.Map; 
import java.lang.Math;

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
public class EleventhAgent extends ThirtyDaysThirtyCampaignsAgent {

  public EleventhAgent(String host, int port) {
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
      double optimalBid = 1;
      Hashtable<MarketSegment, Integer>Ms = new Hashtable<MarketSegment, Integer>();
      int cnt = 0;
      for(MarketSegment m: MarketSegment.values())
      {
          Ms.put(m,cnt);
          cnt+=1;
      }
      int users_segment = 0;
        MarketSegment segment = c.getMarketSegment();
      int Users[] = {4956,5044,4589,5411,8012,1988,2353,2603,3631,1325,2236,2808,4381,663,3816,773,4196,1215,1836,517,1795,808,1980,256,2401,407};      
      for (MarketSegment m: MarketSegment.values())//iterate over a market-segment
      {
          boolean ans = MarketSegment.marketSegmentSubset(segment,m);
          if(ans)
          {
            int idx = Ms.get(m);
            users_segment+=Users[idx];
          }
      }

      //users in order of marketsegments. 

      // Bidding only on the exact market segment of the campaign.
      Set<SimpleBidEntry> bidEntries = new HashSet<SimpleBidEntry>();
      // Logging.log("Market Segment = " + c.getMarketSegment());
      // Logging.log("Iterate over all Market Segments");
      for (MarketSegment m: MarketSegment.values())//iterate over a market-segment
      {
        boolean ans = MarketSegment.marketSegmentSubset(segment,m);
        if (ans)
        {
            double reach = c.getReach();
            double num = Users[Ms.get(m)];
            double denom = users_segment;
            // Logging.log(ratio);
            double spending_Limit = Math.ceil((reach*num)/denom);

            // Logging.log(spending_Limit);
            bidEntries.add(new SimpleBidEntry(m,optimalBid,spending_Limit));

        }
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
    EleventhAgent agent = new EleventhAgent("localhost", 9898);
    agent.connect("agent11");
  }

}
