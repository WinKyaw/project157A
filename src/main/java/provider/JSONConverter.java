package provider;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;

public class JSONConverter {
	public static JSONArray convertToJSON(ResultSet resultSet)
            throws Exception {
        JSONArray jsonArray = new JSONArray();
        int count = 0;
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < total_rows; i++) {
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
            }
            jsonArray.put(obj);
            
            count++;
        }
        
        System.out.println(count);
        return jsonArray;
    }
}
