package views;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("DirectoryView")
public class DirectoryViewTest {

	@Nested
	@DisplayName("when rendering a directory listing")
	class WhenRenderingADirectoryListing {

		@Test
		@DisplayName("renders each file as a linked list item")
		void renders_each_file_as_a_linked_list_item() {
			List<String> files = Arrays.asList("an item", "another item");
			String result = new DirectoryView(files).build();
			assertEquals("<!doctype html><html><head></head><body>Directory<br><a href=\"an item\">an item</a><br><a href=\"another item\">another item</a><br></body></html>", result, "should render files as anchor tags");
		}

		@Test
		@DisplayName("renders an empty directory with no file links")
		void renders_an_empty_directory_with_no_file_links() {
			String result = new DirectoryView(Arrays.asList()).build();
			assertEquals("<!doctype html><html><head></head><body>Directory<br></body></html>", result, "should render just the Directory heading for empty list");
		}
	}
}
