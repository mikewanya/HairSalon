import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class ClientTest {

    @Before
    public void setUp(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon", DbDetails.getUsername(), DbDetails.getPassword());
    }

    @After
    public void tearDown(){
        try(Connection conn = DB.sql2o.open()){
            String stylistsql = "DELETE FROM stylists *";
            String clientsql = "DELETE FROM clients *";
            conn.createQuery(stylistsql).executeUpdate();
            conn.createQuery(clientsql).executeUpdate();
        }
    }

    @Test
    public void Client_instantiatesCorrectly_true(){
        Stylist newStylist = new Stylist("Kennedy","020234987");
        newStylist.save();
        Client newClient = new Client("Mary","020576493",newStylist.getId());
        newClient.save();
        assertTrue(newClient instanceof Client);
    }
    @Test
    public void all_returnsALlInstancesOfClients_true(){
        Stylist newStylist = new Stylist("Kennedy","020234987");
        newStylist.save();

        Client firstClient = new Client("Mary","020576493",newStylist.getId());
        firstClient.save();

        Client secondClient = new Client("Shark", "0785647382", newStylist.getId());
        secondClient.save();

        Client thirdClient = new Client("Damon", "0789474635", newStylist.getId());
        thirdClient.save();

        assertTrue(Client.all().size()==3);

        assertTrue(Client.all().get(0).equals(firstClient));
        assertTrue(Client.all().get(1).equals(secondClient));
        assertTrue(Client.all().get(2).equals(thirdClient));
    }
    @Test
    public void find_returnsAParticularInstanceOfClient_Client(){
        Stylist newStylist = new Stylist("Mary","02029784673");
        newStylist.save();

        Client firstClient = new Client("John","0204856756",newStylist.getId());
        firstClient.save();

        assertTrue(Client.find(firstClient.getId()).equals(firstClient));
    }
    @Test
    public void save_createsARecordOfClientInDb_true(){
        Stylist newStylist = new Stylist("Rose","0726772775");
        newStylist.save();

        Client firstClient = new Client("John","0204856756",newStylist.getId());
        firstClient.save();

        Client secondClient = new Client("Damon", "0721787878", newStylist.getId());
        secondClient.save();

        Client thirdClient = new Client("Gus", "0789578485", newStylist.getId());
        thirdClient.save();

        assertTrue(Client.all().size()==3);
    }
    @Test
    public void update_changesNameOfClient_String(){
        Stylist newStylist = new Stylist("Rose","0726772775");
        newStylist.save();

        Client firstClient = new Client("John","0204856756",newStylist.getId());
        firstClient.save();

        firstClient.update("Damon","0721787878",newStylist.getId());
        assertEquals(firstClient.getName(), "Damon");
    }
    @Test
    public void delete_removesTheInstanceOfClientFromDb_true(){
        Stylist newStylist = new Stylist("Selena","0789475869");
        newStylist.save();

        Client firstClient = new Client("John","0204856756",newStylist.getId());
        firstClient.save();

        firstClient.delete();

        assertTrue(Client.all().size()==0);
    }
    @Test
    public void getClientStylist_returnsTheStylistAssignedToAParticularClient_String(){
        Stylist newStylist = new Stylist("Rose","0726772775");
        newStylist.save();

        Client firstClient = new Client("Nathan","0721717141",newStylist.getId());
        firstClient.save();

        assertEquals(firstClient.getClientStylist().getName(), "Rose");
    }
}
