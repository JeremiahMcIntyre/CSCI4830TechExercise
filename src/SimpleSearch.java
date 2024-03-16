import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.SalesContact;
import util.Info;
import util.UtilDB;

@WebServlet("/SimpleSearch")
public class SimpleSearch extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleSearch() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String searchBy = request.getParameter("searchBy").trim();
	   String searchValue = request.getParameter("searchValue").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");

      List<SalesContact> listSalesContacts = null;
      if (searchValue != null && !searchValue.isEmpty()) {
         listSalesContacts = UtilDB.listSalesContact(searchBy, searchValue);
      } else {
    	  listSalesContacts = UtilDB.listSalesContact();
      }
      display(listSalesContacts, out);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   void display(List<SalesContact> listSalesContacts, PrintWriter out) {
      for (SalesContact salesContact : listSalesContacts) {
         System.out.println("[DBG] " + salesContact.getId() + ", " //
               + salesContact.getName() + ", " //
               + salesContact.getAddress() + ", " //
               + salesContact.getPhoneNumber() + ", " //
               + salesContact.getEmail());

         out.println("<li>" + salesContact.getId() + ", " //
               + salesContact.getName() + ", " //
               + salesContact.getAddress() + ", " //
               + salesContact.getPhoneNumber() + ", " //
               + salesContact.getEmail() + "</li>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
