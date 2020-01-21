/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharebroker;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import brokerShares.*;
import java.util.Iterator;
/**
 *
 * @author n0590642
 */
public class Sharebroker {
    public static File sharesXML = new File("Shares.txt");
    
    public static void main(String[] args) {
    Shares shares = new Shares();
    //SharePrice prices = new Price();
    
    List<Share> sharesList = shares.getShares();
    //List<SharePrice> priceList = prices.
    
    Share share;
    SharePrice price;
    //1.
    share = new Share();
    share.setCompanyName("Google");
    share.setCompanySymbol("GOOG");
    share.setNumber(0);
    share.setSharePrice("100");
    
    //add new share price
    price = new SharePrice();
    price.setCurrency("USD");
    price.setDate(getDate(2018, 12, 1));
    price.setValuePrice(1);
    sharesList.add(share); 
    
    //2.
    share = new Share();
    share.setCompanyName("Facebook");
    share.setCompanySymbol("FB");
    share.setNumber(0);
    share.setSharePrice("800");
    
    //add new share price
    price = new SharePrice();
    price.setCurrency("USD");
    price.setDate(getDate(2018, 12, 1));
    price.setValuePrice(1);
    sharesList.add(share); 
    
    //2.
    share = new Share();
    share.setCompanyName("Amazon");
    share.setCompanySymbol("AMZN");
    share.setNumber(0);
    share.setSharePrice("600");
    
    //add new share price
    price = new SharePrice();
    price.setCurrency("USD");
    price.setDate(getDate(2018, 12, 1));
    price.setValuePrice(1);
    sharesList.add(share); 
    
    
    //marshalShares(shares);  
        try {            
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(shares.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
           
            //writting to doc
            marshaller.marshal(shares, sharesXML);
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
        search();
        //read();
    
      }
    public static void read()
    {
         Shares shares = new Shares();

        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(shares.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            shares =  (Shares) unmarshaller.unmarshal(new java.io.File("Shares.txt")); //NOI18N
            
            Share nextShare = new Share();
            
            List<Share> sharesList = shares.getShares();
           // Share share = new Share();
            
            Iterator itr = sharesList.iterator();
            while(itr.hasNext())
            {
                nextShare = (Share) itr.next();
                System.out.println(nextShare.getCompanyName() 
                        + " " + nextShare.getCompanySymbol() 
                        + " " + nextShare.getSharePrice());
            }
            
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
         
    }
    
    
    //List share details and update the number of Shares
    public static void update()
    {
       Shares shares = new Shares();
       
    }
    
     public static void search()
     {
         Shares shares = new Shares();
         Scanner reader = new Scanner(System.in);
         
         
         try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(shares.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            shares =  (Shares) unmarshaller.unmarshal(new java.io.File("Shares.txt")); //NOI18N
         
         Share nextShare = new Share();
         List<Share> sharesList = shares.getShares();
         //Share share = new Share();
          
         
         System.out.println("Search for company by name: ");
         String searchedName = reader.next();
         reader.close();
         
         Iterator itr = sharesList.iterator();
         while(itr.hasNext())
         {
             nextShare = (Share) itr.next();
             if(nextShare.getCompanyName().contentEquals(searchedName))
             {
                 System.out.println(nextShare.getCompanyName() 
                         + " " + nextShare.getCompanySymbol() 
                         + " " + nextShare.getSharePrice());
             }
             /*else if(!nextShare.getCompanyName().contentEquals(searchedName))
             {
                 System.out.println("Sorry Share does not exist try again");
                
             }*/
         }
         } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
     }
     
     
    
     public static XMLGregorianCalendar getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        XMLGregorianCalendar xmlDate;
        GregorianCalendar data = new GregorianCalendar(year, month, day,
                cal.get(Calendar.HOUR),
                cal.get(Calendar.MINUTE),
                cal.get(Calendar.SECOND));
        try {
            xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(data);
            return xmlDate;
        } catch (DatatypeConfigurationException e) {
            // TODO: replace with logger
            System.out.println(e);
        }
        return null;
    }
}

    
    /**
     * @param args the command line arguments
     
     
     public void marshalShares(Shares shares) {
    //marshalling
         try {             
             javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(Shares.class);
             javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
             marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
             marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
             marshaller.marshal(shares, sharesXML);
         } catch (javax.xml.bind.JAXBException ex) {
             // XXXTODO Handle exception
             java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
         }
                
    }
     
     private Shares unmarshalShares() {
         try {
             javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(Shares.class);
             javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
             Shares shares = (Shares) unmarshaller.unmarshal(sharesXML); //NOI18N
             return shares;
         } catch (javax.xml.bind.JAXBException ex) {
             // XXXTODO Handle exception
             java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
         }
         
        return null;
    }
     
   
    //public Shares setup(){  
   
   // }
   
   
     
     
     
    public List<Share> getAllShares() {
        Shares shares = unmarshalShares();
        List<Share> sharesList = shares.getShares();
        
        return sharesList;
               
    }
        

// TODO code application logic here
         //List<String> Shares;
        
        //shareOrder.Shares quickXML = new shareOrder.Shares();
        //quickXML.setCompanyName("Google");
        //quickXML.setCompanySymbol("G");
        //quickXML.setNumber(0);
        //quickXML.setSharePrice("4");
        
    
      
        //shareOrder.SharePrice quickXML = new shareOrder.SharePrice();
       // quickXML.setCurrency("£");
        //quickXML.setDate();
       // quickXML.setValuePrice(100);
        
        
        //shareOrder.SellingShares quickXML = new shareOrder.SellingShares();
       // quickXML.getShareCollection();
        
       //shareOrder.ObjectFactory quickXML = new shareOrder.ObjectFactory();
       
      // quickXML.createSellingShares();
      // quickXML.createSharePrice();
       // quickXML.createShares();
       */
       
       
       
       
                
       
       
   
    
