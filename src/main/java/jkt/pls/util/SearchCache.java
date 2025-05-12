package jkt.pls.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class SearchCache {
	
	private static final int MAX_ENTRIES = 100;
	
	private final Map<String, String> cache = Collections.synchronizedMap(
        new LinkedHashMap<String, String>(MAX_ENTRIES, 0.75f, true) {
        	
			private static final long serialVersionUID = 1L;

			@Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {                
                return size() > MAX_ENTRIES;
            }
        }
    );
	
	
    public String get(String key) {
        return this.cache.get(key);
    }
    
    
    public void put(String key, String value) {
    	this.cache.put(key, value);
    }

    
    public void clear() {
    	this.cache.clear();
    }
}
