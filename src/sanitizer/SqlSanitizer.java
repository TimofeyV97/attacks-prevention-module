package sanitizer;

import java.util.ArrayList;
import java.util.List;
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
		final StringBuilder sb = new StringBuilder(needToEscapeSql(sql) ? sanitizeSql(sql) : sql);

		sb.insert(0, "'");
		sb.insert(sb.length(), "'");

		return sb.toString();
	}

	@Override
	public List<Integer> scanContent(final String sql, final Pattern pattern) {
		if (sql.charAt(0) == '\'' && sql.charAt(sql.length() - 1) == '\'') {
			return new ArrayList<>();
		}

		final List<Integer> indexes = new ArrayList<>();
		final char [] content = sql.toCharArray();

		for (int i = 0; i < content.length; i++) {
			if (pattern.matcher(String.valueOf(content[i])).find()) {
				indexes.add(i);
			}
		}

		return indexes;
	}

	private boolean needToEscapeSql(final String sql) {
		final Matcher matcher = SQL_PATTERN.matcher(sql);
		return matcher.find();
	}

	private String sanitizeSql(final String sql) {
		return sql.replaceAll("'", "\\\\'");
	}

}
