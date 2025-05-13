//package jkt.pls.service;
//
//package jkt.pls.service;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.StringField;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.queryparser.classic.QueryParser;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.TopDocs;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.StoredFields;
//import org.apache.lucene.store.Directory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * PLS 서비스 레이어에서 Lucene 인덱싱 및 검색을 담당하는 클래스
// */
//@Service
//public class LuceneService {
//    private final IndexWriter writer;
//    private final Analyzer analyzer;
//
//    @Autowired
//    public LuceneService(IndexWriter writer, Analyzer analyzer) {
//        this.writer = writer;
//        this.analyzer = analyzer;
//    }
//
//    /**
//     * 단일 엔티티를 Lucene 색인에 저장
//     */
//    public void indexDocument(PlsEntity entity) throws IOException {
//        Document doc = new Document();
//        // 예: ID 필드는 분석 없이 저장
//        doc.add(new StringField("id", entity.getId(), StringField.Store.YES));
//        // 예: 제목과 내용은 분석 후 저장
//        doc.add(new TextField("title", entity.getTitle(), TextField.Store.YES));
//        doc.add(new TextField("content", entity.getContent(), TextField.Store.YES));
//
//        writer.addDocument(doc);
//        writer.commit();  // NRT Reader 사용 시 바로 커밋 필요
//    }
//
//    /**
//     * 키워드로 색인에서 검색하여 엔티티 리스트 반환
//     */
//    public List<PlsEntity> search(String keyword, int maxHits) {
//        List<PlsEntity> results = new ArrayList<>();
//        try (DirectoryReader reader = DirectoryReader.open(writer)) {
//            IndexSearcher searcher = new IndexSearcher(reader);
//            QueryParser parser = new QueryParser("content", analyzer);
//            Query query = parser.parse(keyword);
//
//            TopDocs topDocs = searcher.search(query, maxHits);
//            StoredFields storedFields = searcher.storedFields();
//
//            for (ScoreDoc sd : topDocs.scoreDocs) {
//                Document d = storedFields.document(sd.doc);
//                PlsEntity e = new PlsEntity();
//                e.setId(d.get("id"));
//                e.setTitle(d.get("title"));
//                e.setContent(d.get("content"));
//                results.add(e);
//            }
//        } catch (Exception e) {
//            // 예외 처리 로직
//            throw new RuntimeException("Lucene search failed", e);
//        }
//
//        return results;
//    }
//}
