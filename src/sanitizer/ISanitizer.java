package sanitizer;

import java.util.List;
import java.util.regex.Pattern;

public interface ISanitizer {

	String escapeContent(String content);

	List<Integer> scanContent(String content, Pattern pattern);

	default String replaceContent(String content, String [] data, String [] replacement) {
		StringBuilder sb = new StringBuilder(content);

		for (int i = 0; i < data.length; i++) {
			sb = new StringBuilder(sb.toString().replaceAll(data[i], replacement[i]));
		}

		return sb.toString();
	}

	default String removeContent(String content, String [] data) {
		StringBuilder sb = new StringBuilder(content);

		for (final String s : data) {
			sb = new StringBuilder(sb.toString().replaceAll(s, ""));
		}

		return sb.toString();
	}

}
