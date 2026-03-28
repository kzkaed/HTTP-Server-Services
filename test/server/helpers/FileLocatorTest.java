package server.helpers;

import static org.junit.Assert.*;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class FileLocatorTest {

	@Test
	public void testFileExistFalse(){
		assertFalse(FileLocator.fileExist("/jam", "public"));
	}

	@Test
	public void testDoesFileExistTrue(){
		assertTrue(FileLocator.fileExist("/file1", "public"));
	}

	@Test
	public void testDoesRootFileExistFalse(){
		assertFalse(FileLocator.fileExist("/", "public"));
	}

	@Test
	public void testfindPathURI() {
		Path path = Paths.get("");
		URI uri = path.toUri();
		assertEquals(FileLocator.findServerPathURI(), uri);
	}

	@Test
	public void testFindPathAbsolute(){
		Path path = Paths.get("");
		assertEquals(FileLocator.findServerAbsolutePath(), path.toAbsolutePath().toString());
	}

	@Test
	public void testWebrootAbsolutePath(){
		Path path = Paths.get("");
		String expected = path.toAbsolutePath() + "/public";
		assertEquals(FileLocator.webrootAbsolutePath("public"), expected);
	}
}
