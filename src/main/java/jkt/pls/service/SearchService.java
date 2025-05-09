package jkt.pls.service;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.stereotype.Service;

import jkt.pls.model.request.InsertRequest;
import jkt.pls.util.RandomStringGenerator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SearchService {
	
	@Value("${custom.document.path}")
	private String docPath;
	
	

	public Mono<Void> insert(InsertRequest request){
		
//		Path baseDir = Paths.get(docPath + File.separator + request.getDocumentId());
//		String fileName = System.currentTimeMillis() + RandomStringGenerator.generate(5) + ".txt";
//		
//		try {
//			Files.createDirectories(baseDir);
//			
//		}catch (IOException  e) {
//			return Mono.error(
//                new UncheckedIOException("업로드 디렉터리 생성 실패", e)
//            );
//		}
		
		
		
		
		return Mono.empty();
	}
}
