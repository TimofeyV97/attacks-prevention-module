package sanitizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlSanitizer implements ISanitizer {

	private static final String LT = "&lt;";

	private static final String GT = "&gt;";

	private static final String QUOT = "&quot;";

	private static final String AMP = "&amp;";

	private static final String XSS_REGEXP = "[&<>\"]";

	private static final Pattern HTML_PATTERN = Pattern.compile(XSS_REGEXP);

	private HtmlSanitizer() {}

	public static HtmlSanitizer getInstance() {
		return new HtmlSanitizer();
	}

	@Override
	public String escapeContent(final String html) {
		return needToEscapeHtml(html) ? sanitizeHtml(html) : html;
	}

	private boolean needToEscapeHtml(final String html) {
		final Matcher matcher = HTML_PATTERN.matcher(html);
		return matcher.find();
	}

	private String sanitizeHtml(final String html) {
		return html
				.replace("&", AMP)
				.replace("<", LT)
				.replace(">", GT)
				.replace("\"", QUOT);
	}

}
