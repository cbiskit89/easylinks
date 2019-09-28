package me.corymiller.easylinks;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EasyLinksListServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("applicaton/json; charset=utf-8");
        JSONArray json = new JSONArray();

        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        Session session = cluster.connect("golinks");

        for (Row row : session.execute("SELECT * FROM permlinks")) {
            HashMap<String, String> link = new HashMap();
            link.put("source", row.getString("source"));
            link.put("dest", row.getString("destination"));
            json.put(link);
        }

        response.getWriter().println(json.toString());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
