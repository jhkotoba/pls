package jkt.pls.config;
// Updated indentation to use tabs

import java.io.IOException;
import java.io.UncheckedIOException;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import jkt.pls.repository.SearchRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
	
	private final IndexWriter writer;
	private final SearchRepository searchRepository;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		searchRepository.findAll()
			.doOnNext(entity -> {
				try {
					Document doc = new Document();
					doc.add(new StringField("searchId", entity.getSearchId().toString(), Field.Store.YES));
					doc.add(new TextField("token", entity.getToken(), Field.Store.NO));
					writer.addDocument(doc);
				} catch (IOException e) {
					throw new UncheckedIOException(e);
				}
			})
			.doOnComplete(() -> {
				try { writer.commit(); }
				catch (IOException e) { throw new UncheckedIOException(e); }
			})
			.subscribe();
	}

}
