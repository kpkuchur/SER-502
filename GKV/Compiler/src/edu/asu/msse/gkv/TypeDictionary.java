/**
 * Copyright 2016 GKV
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Gowtham Ganesh Nayak mailto:gnayak2@asu.edu
 * @version April 2016
 */

package edu.asu.msse.gkv;

import java.util.HashMap;
import java.util.Map;

public class TypeDictionary {
	private final String INTEGER = "integer";
	private final String DECLINT = "DECLINT";
	
	private final String DECIMAL = "decimal";
	private final String DECLDEC = "DECLDEC";
	
	private final String STACK = "stack";
	private final String DECLSTACK = "DECLSTACK";
	
	private final String BOOLEAN = "boolean";
	private final String DECLB = "DECLB";
	
	private Map<String, String> typeMap;
	
	TypeDictionary() {
		this.typeMap = new HashMap<>();
		initializeTypeMap();	
	}
	
	private void initializeTypeMap() {
		this.typeMap.put(INTEGER, DECLINT);
		this.typeMap.put(DECIMAL, DECLDEC);
		this.typeMap.put(STACK, DECLSTACK);
		this.typeMap.put(BOOLEAN, DECLB);
	}
	
	public Map<String, String> getTypeMap() {
		return this.typeMap;
	}
}

