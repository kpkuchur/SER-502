package edu.asu.msse.gkv;

import java.util.HashMap;
import java.util.Map;

public class ComparisionDictionary {
	private Map<String, String> comparisionOperatorMap;
	
	public ComparisionDictionary() {
		this.comparisionOperatorMap = new HashMap<>();
		initializeMap();
	}
	
	private void initializeMap() {
		this.comparisionOperatorMap.put("equalTo", "EQL");
		this.comparisionOperatorMap.put("notEqualTo", "NTEQL");
		this.comparisionOperatorMap.put("lessThanOrEqualTo", "LTEQL");
		this.comparisionOperatorMap.put("greaterThanOrEqualTo", "GTEQL");
		this.comparisionOperatorMap.put("lessThan", "LT");
		this.comparisionOperatorMap.put("greaterThan", "GT");
	}
	
	public Map<String,String> getComparisionOperatorMap() {
		return this.comparisionOperatorMap;
	}
}
