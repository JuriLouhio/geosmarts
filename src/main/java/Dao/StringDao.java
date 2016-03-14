package Dao;

import geosmarts.geosmarts.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringDao implements Dao<String, Integer> {

    private Database db;

    public StringDao(Database d) {
        this.db = d;
    }

    @Override
    public String findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> AllIn(Collection<Integer> keys) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> AlueetYhtAika() throws SQLException {

        this.db.connect();
        PreparedStatement stmt = this.db.getConnection().prepareStatement("SELECT DISTINCT alue.nimi,Count(viesti.id_viesti), MAX(viesti.aika) FROM alue\n"
                + "LEFT JOIN viestiketju ON alue.id_alue = viestiketju.alue_id\n"
                + "LEFT JOIN viesti ON viestiketju.id_viestiketju = viesti.viestiketju_id\n"
                + "group by alue.nimi");

        ResultSet rs = stmt.executeQuery();
        ArrayList<String> alueetYhteensaJaAika = new ArrayList<>();

        String alueet = "";
        String viestit = "";
        String ajat = "";

        while (rs.next()) {

            alueet = alueet + rs.getString(1) + ",";
            viestit = viestit + String.valueOf(rs.getInt(2)) + ",";

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (rs.getTimestamp(3) == null) {
                ajat = ajat + "0000-00-00 00:00:00" + ",";
            } else {

                ajat = ajat + dateFormat.format(rs.getTimestamp(3)) + ",";

            }
        }
        alueetYhteensaJaAika.add(alueet);
        alueetYhteensaJaAika.add(viestit);
        alueetYhteensaJaAika.add(ajat);

        rs.close();
        stmt.close();
        this.db.disconnect();

        return alueetYhteensaJaAika;

    }

    @Override
    public void create(String string) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<String> viestiketjut() throws SQLException {

        this.db.connect();
        PreparedStatement stmt = this.db.getConnection().prepareStatement("SELECT viestiketju.aihe AS Viestiketju, COUNT(*) AS Viestejä_yhteensä, viesti.aika AS Viimeisin_viesti\n"
                + "FROM viestiketju, viesti, alue\n"
                + "WHERE viestiketju.id_viestiketju = viesti.viestiketju_id\n"
                + "AND alue.id_alue = viestiketju.alue_id\n"
                + "AND viestiketju.alue_id = 2\n"
                + "GROUP BY viestiketju.aihe");

        ResultSet rs = stmt.executeQuery();
        ArrayList<String> viestiketjut = new ArrayList<>();

        String viestiketj = "";
        String viestit = "";
        String ajat = "";

        while (rs.next()) {

            viestiketj = viestiketj + rs.getString(1) + ",";
            viestit = viestit + String.valueOf(rs.getInt(2)) + ",";

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (rs.getTimestamp(3) == null) {
                ajat = ajat + "0000-00-00 00:00:00" + ",";
            } else {

                ajat = ajat + dateFormat.format(rs.getTimestamp(3)) + ",";

            }
        }
        viestiketjut.add(viestiketj);
        viestiketjut.add(viestit);
        viestiketjut.add(ajat);

        rs.close();
        stmt.close();
        this.db.disconnect();

        return viestiketjut;
    }

}
