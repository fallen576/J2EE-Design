package app.html;

import app.utils.FileUtils;

public class Element {

	public static String create(String el, String value, String ...classNames) {
		return new StringBuilder()
				.append("<")
				.append(el)
				.append(" class=\"")
				.append(String.join(" ", classNames))
				.append("\">")
				.append(value)
				.append("</" + el + ">")
				.toString();
	}

	public static String createHead() {
		String css = FileUtils.getFileContents("email_style.css");
		return new StringBuilder("<head>")
				.append("<style type=\"text/css\">")
				.append(css)
				.append("</style>")
				.append("</head>")
				.toString();
	}
	
	public static String createLink(String href, String value, String ...classNames) {
		return new StringBuilder("<a ")
				.append("href=\"")
				.append(href)
				.append(String.join(" ", classNames))
				.append("\">")
				.append(value)
				.append("</a>")
				.toString();
	}
	
}
