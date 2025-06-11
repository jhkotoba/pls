package jkt.pls.config;
// Updated indentation to use tabs

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuceneConfig {

	@Bean
	protected Directory luceneDirectory() throws IOException {
		Path indexPath = Paths.get("./data/lucene");
		return FSDirectory.open(indexPath);
	}
	
	@Bean
	protected Analyzer luceneAnalyzer() throws IOException {
		return CustomAnalyzer.builder()
				.withTokenizer("standard")
				.addTokenFilter("lowercase")
				.addTokenFilter("stop")
				.addTokenFilter("porterStem")
				.addTokenFilter("length", "min", "3", "max", "50")
				.addTokenFilter("koreanPartOfSpeechStop")
				.build();
	}
	
	@Bean
	protected IndexWriter indexWriter(Directory luceneDirectory, Analyzer luceneAnalyzer) throws IOException {
		IndexWriterConfig cfg = new IndexWriterConfig(luceneAnalyzer);
		IndexWriter writer = new IndexWriter(luceneDirectory, cfg);
		writer.commit();
		return writer;
	}

	@Bean
	protected IndexSearcher indexSearcher(IndexWriter indexWriter) throws IOException {
		DirectoryReader reader = DirectoryReader.open(indexWriter);
		return new IndexSearcher(reader);
	}
}
