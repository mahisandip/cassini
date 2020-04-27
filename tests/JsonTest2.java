package tests;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONSerializer;

public class JsonTest2 {
	
	public static void main(String[] args) {
			
	}
	
	private static void test1() {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
	    data.put( "name", "Mars" );
	    data.put( "age", 32 );
	    data.put( "city", "NY" );

	    String jsonStr = "";
		try {
			jsonStr = new ObjectMapper().writeValueAsString(data);
			System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		String s = ", ".concat(jsonStr);
		System.out.println(s);
		s = s.replaceFirst("{1}.$", "").trim();
//		s = s.replaceFirst(".{2}$", "");
		System.out.println(s);
	}
	
	private static void test2() {
		String jsonString = "{\"serialVersionUID\":\"1\", \"transactionDate\":\"20180103\", \"preparerId\":\"TEST PREP 01\", \"verifierId\":\"TEST VERIFY 01\", \"description\":\"TEST DESC 01\", \"raCashBookValue\":\"5.00\", \"shortfall\":\"6.00\"}, {\"serialVersionUID\":\"1\", \"transactionDate\":\"20180102\", \"preparerId\":\"TEST PREP 02\", \"verifierId\":\"TEST VERIFY 02\", \"description\":\"TEST DESC 02\", \"raCashBookValue\":\"3.00\", \"shortfall\":\"4.00\"}, {\"serialVersionUID\":\"1\", \"transactionDate\":\"20180101\", \"preparerId\":\"TEST PREP 03\", \"verifierId\":\"TEST VERIFY 03\", \"description\":\"TEST DESC 03\", \"raCashBookValue\":\"1.00\", \"shortfall\":\"2.00\"}";
		JSONArray jArr = new JSONArray(jsonString);
		System.out.println(jArr);
		for (int i = 0; i < jArr.length(); i++) {
			JSONObject jObj = jArr.getJSONObject(i);
			System.out.println(jObj.toString());
		}
	}
	
	private static void test3() {
		
		String jsonString = "[{\"serialVersionUID\":\"1\", \"transactionDate\":\"20180103\", \"preparerId\":\"TEST PREP 01\", \"verifierId\":\"TEST VERIFY 01\", \"description\":\"TEST DESC 01\", \"raCashBookValue\":\"5.00\", \"shortfall\":\"6.00\"}, {\"serialVersionUID\":\"1\", \"transactionDate\":\"20180102\", \"preparerId\":\"TEST PREP 02\", \"verifierId\":\"TEST VERIFY 02\", \"description\":\"TEST DESC 02\", \"raCashBookValue\":\"3.00\", \"shortfall\":\"4.00\"}, {\"serialVersionUID\":\"1\", \"transactionDate\":\"20180101\", \"preparerId\":\"TEST PREP 03\", \"verifierId\":\"TEST VERIFY 03\", \"description\":\"TEST DESC 03\", \"raCashBookValue\":\"1.00\", \"shortfall\":\"2.00\"}]";
		
		net.sf.json.JSONArray jsonArray = (net.sf.json.JSONArray) JSONSerializer.toJSON(jsonString);
		System.out.println(jsonArray);
		for(Object object : jsonArray){
			net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) object;
            System.out.println(jsonObject.toString());
        }
	}
			
}
