import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketingOperations {
    List<TicketingData> list1 = new ArrayList<TicketingData>();
     //This method reads the sample.csv file provided and returns a List
    public List<TicketingData> readTicketingData(String fileName)
    {
        list1 = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String str;
            int i=0;
            while((str=br.readLine())!=null)
            {
               /* if(i==0)
                {
                    i++;
                    continue;
                }*/

                    String[] s = str.split(",");
                    TicketingData t = new TicketingData(s[0], s[1], Integer.valueOf(s[2]), Integer.valueOf(s[3]),
                            Integer.parseInt(s[4]), Integer.parseInt(s[5]), s[6], s[7], Double.parseDouble(s[8]),
                            Float.parseFloat(s[9]));
                    list1.add(t);

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return list1;
    }
    //This method sorts the data based on the kms travelled by a bus (route)
    public List<TicketingData> sortByKmsTravelled(List<TicketingData> ticketingDataList)
    {
        List<TicketingData> td = new ArrayList<>();
        Collections.sort(ticketingDataList, (o1, o2) ->  Float.compare(o1.getTravelledKM(),o2.getTravelledKM()));

        return ticketingDataList;
    }

    //This method calculates the total revenue from ticket collection amount
    public double totalAmountCollected(List<TicketingData> ticketingDataList)
    {
        TicketCollection tc = data -> {
            double totalAmount = 0;
            for(TicketingData t : ticketingDataList)
            {
                totalAmount = totalAmount+t.getTotal_ticket_amount();
            }
            return totalAmount;
        };
       return tc.totalCollectedAmount(ticketingDataList);
    }
}
