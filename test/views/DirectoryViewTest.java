package views;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DirectoryViewTest {

	@Test
	public void testRendersDirectoryListing() {
		List<String> files = Arrays.asList("an item", "another item");
		String result = new DirectoryView(files).build();
		assertEquals("<!doctype html><html><head></head><body>Directory<br><a href=\"an item\">an item</a><br><a href=\"another item\">another item</a><br></body></html>", result);
	}

	@Test
	public void testRendersEmptyDirectory() {
		String result = new DirectoryView(Arrays.asList()).build();
		assertEquals("<!doctype html><html><head></head><body>Directory<br></body></html>", result);
	}

}
