package com.example.demo.test.markdown;

import com.vladsch.flexmark.ast.util.TextCollectingVisitor;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.profiles.pegdown.Extensions;
import com.vladsch.flexmark.profiles.pegdown.PegdownOptionsAdapter;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;

public class MarkdownParser {
	private static final DataHolder OPTIONS = PegdownOptionsAdapter.flexmarkOptions(Extensions.ALL);
	static final Parser PARSER = Parser.builder(OPTIONS).build();

	public static void main(String[] args) {
		String pegdown = "# Heading\n" + "> title is good\n\n`balabala……`";
		Node document = PARSER.parse(pegdown);
		TextCollectingVisitor textCollectingVisitor = new TextCollectingVisitor();
		String text = textCollectingVisitor.collectAndGetText(document);
		text = text.replaceAll("\n", " ");
		System.out.println(text);
	}
}
