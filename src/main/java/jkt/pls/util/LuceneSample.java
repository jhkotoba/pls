package jkt.pls.util;
// Updated indentation to use tabs

import java.io.IOException;
import java.nio.file.Paths;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.StoredFields;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class LuceneSample {
	public static void main(String[] args) throws Exception {
		// 1) 인덱스 저장 디렉토리와 분석기 설정
		Directory dir = FSDirectory.open(Paths.get("indexDir"));
		StandardAnalyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

		// 2) 문서 인덱싱
		try (IndexWriter writer = new IndexWriter(dir, iwc)) {
			addDoc(writer, "Lucene in Action", "193398817");
			addDoc(writer, "Lucene for Dummies", "55320055Z");
			addDoc(writer, "Managing Gigabytes", "55063554A");
			addDoc(writer, "The Art of Computer Science", "9900333X");
			writer.commit();
		}

		// 3) 검색
		String queryString = "Lucene";
		QueryParser parser = new QueryParser("title", analyzer);
		Query query = parser.parse(queryString);

		try (DirectoryReader reader = DirectoryReader.open(dir)) {
			IndexSearcher searcher = new IndexSearcher(reader);
			TopDocs results = searcher.search(query, 10);
			StoredFields storedFields = searcher.storedFields();
			
			System.out.println("Total Hits: " + results.totalHits.value());
			for (ScoreDoc sd : results.scoreDocs) {
				Document doc = storedFields.document(sd.doc);
				System.out.printf("ISBN: %s, Title: %s%n",
					doc.get("isbn"), doc.get("title"));
			}
		}
	}

	private static void addDoc(IndexWriter writer, String title, String isbn) throws IOException {
		Document doc = new Document();
		doc.add(new TextField("title", title, Field.Store.YES));
		doc.add(new StringField("isbn", isbn, Field.Store.YES));
		writer.addDocument(doc);
	}
}
