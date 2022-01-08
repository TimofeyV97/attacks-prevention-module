package sanitizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlSanitizer implements ISanitizer {

	private static final String SQL_REGEXP = "['\"]";

	private static final Pattern SQL_PATTERN = Pattern.compile(SQL_REGEXP);

	private SqlSanitizer() {}

	public static SqlSanitizer getInstance() {
		return new SqlSanitizer();
	}

	@Override
	public String escapeContent(final String sql) {
		return needToEscapeSql(sql) ? sanitizeSql(sql) : sql;
	}

	private boolean needToEscapeSql(final String sql) {
		final Matcher matcher = SQL_PATTERN.matcher(sql);
		return matcher.find();
	}

	private String sanitizeSql(final String sql) {
		final StringBuilder sb = new StringBuilder(sql.replaceAll("'", "\\\\'"));

		sb.insert(0, "'");
		sb.insert(sb.length(), "'");

		return sb.toString();
	}

}
