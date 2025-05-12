package jkt.pls.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jkt.pls.model.entity.SearchEntity;
import jkt.pls.model.request.InsertRequest;
import jkt.pls.model.request.SearchRequest;
import jkt.pls.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SearchService {
	
	@Value("${custom.document.path}")
	private String docPath;
	
	private final SearchRepository searchRepository;
	
	
	public Flux<SearchEntity> list(SearchRequest request){
		
		long cnt = 0;
		System.out.println("SearchService.list");
		
		return searchRepository.findAll();
		
//		List<SearchEntity> list = new ArrayList<>();
//		List.of("A", "B").forEach(f -> list.add(SearchEntity.builder().searchId(cnt++).build()));
//		

//		return Flux.just(SearchEntity.builder().searchId(cnt++).build(), SearchEntity.builder().searchId(cnt++).build())
//				.map(m -> {
//					System.out.println(m);
//					return m;
//				});
		
		//return Flux.empty();
	}

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
