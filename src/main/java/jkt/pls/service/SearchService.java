package jkt.pls.service;

import org.springframework.stereotype.Service;

import jkt.pls.model.request.InsertRequest;
import jkt.pls.model.request.SearchRequest;
import jkt.pls.repository.ProjectRepository;
import jkt.pls.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {
	
//	@Value("${custom.document.path}")
//	private String docPath;
	
	private final SearchRepository searchRepository;
	private final ProjectRepository projectRepository;
	
	
	public Flux<?> list(SearchRequest request){
		
                long cnt = 0;
                log.info("SearchService.list");
		
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
	
	public Flux<?> findProject(){
		return projectRepository.findAll();
	}
}
