import sanitizer.HtmlSanitizer;
import sanitizer.SqlSanitizer;

public class Main {

	public static void main(final String[] args) {
		final HtmlSanitizer htmlSanitizer = HtmlSanitizer.getInstance();
		final SqlSanitizer sqlSanitizer = SqlSanitizer.getInstance();

		System.out.println(htmlSanitizer.escapeContent("<script>alert(\"xss\");</script>"));
		System.out.println(sqlSanitizer.escapeContent("' OR 1=1; DROP DATABASE;"));
	}

}
