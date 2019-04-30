import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class StylistTest {
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
    public void Stylist_initializesCorrectly_true(){
        Stylist newStylist = new Stylist("Stevenson","0780470479");
        assertTrue(newStylist instanceof Stylist);
    }
    @Test
    public void getId_returnsIdOfStylist_true(){
        Stylist newStylist = new Stylist("Stevenson","0780470479");
        newStylist.save();
        assertTrue(newStylist.getId()>0);
    }
    @Test
    public void getName_returnsNameOfStylist_String(){
        Stylist newStylist = new Stylist("Stevenson","0780470479");
        assertEquals(newStylist.getName(), "Stevenson");
    }
    @Test
    public void getTelNumber_returnsPhoneNumberOfStylist_int(){
        Stylist newStylist = new Stylist("Stevenson","0780470479");
        assertEquals(newStylist.getTelNumber(), "0780470479");
    }
    @Test
    public void find_returnsObjectAsStoredInDB_String(){
        Stylist newStylist = new Stylist("Stevenson","0780470479");
        newStylist.save();
        assertEquals(Stylist.find(newStylist.getId()).getName(),"Stevenson");
    }
    @Test
    public void save_savesTheStylistIntoDB_int(){
        Stylist newStylist = new Stylist("Stevenson","0780470479");
        newStylist.save();
        assertEquals((Stylist.find(newStylist.getId())).getId(),newStylist.getId());
    }
    @Test
    public void update_changesDetailsOfAStylist_String(){
        Stylist newStylist = new Stylist("Maryline","0785948305");
        newStylist.save();
        newStylist.update("Damon","0799595959");
        assertEquals(newStylist.getTelNumber(), "0799595959");
        assertEquals(newStylist.getName(),"Damon");
    }
    @Test
    public void all_returnsAllInstancesOfStylist_String(){
        Stylist newStylist = new Stylist("Nathan","0760895849");
        newStylist.save();
        assertEquals(Stylist.all().get(0).getName(),"Nathan");
    }
    @Test
    public void delete_removesTheStylistFromTheDB_false(){
        Stylist newStylist = new Stylist("Steve","0780987960");
        newStylist.save();
        newStylist.delete();
        assertFalse(Stylist.all().size()>0);
    }

}
