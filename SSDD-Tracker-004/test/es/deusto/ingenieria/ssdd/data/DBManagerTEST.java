package es.deusto.ingenieria.ssdd.data;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBManagerTEST {

	static DBManager dbm;
	
	@BeforeClass
	public static void setUp() throws Exception {
		dbm = new DBManager("test/db/test.db");
		dbm.initDB();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDBManager() throws SQLException {
		assertNotNull(dbm.getCon());
	}


	@Test
	public void testInsertPeer() {
		dbm.insertPeer(1234, "192.168.1.", 8080);
		assertNotEquals(-1, dbm.retrievePeers());
	}

	@Test
	public void testInsertTorrent() {
		dbm.insertTorrent("Superinfohash568945112265");
		assertNotEquals(-1, dbm.retrieveTorrents());
	}

	@Test
	public void testInsertPeer_Torrent() {
		dbm.insertPeer_Torrent(1234, "Superinfohash568945112265", 1000, 2500, 35000, 0);
		assertNotEquals(-1, dbm.retrievePeerTorrent());
	}
	
	@AfterClass
	public static void testCloseDB() throws SQLException {
		dbm.resetDB();
		dbm.getCon().close();
		assertTrue(dbm.getCon().isClosed());
	}

}
