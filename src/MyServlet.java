import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.SalesContact;
import util.UtilDB;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MyServlet() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");

      // #1
      UtilDB.createSalesContacts("Jim Pasta", "2704 N. 65th Ave Omaha NE 68104", "402-830-4426", "jimpasta@gmail.com");
      UtilDB.createSalesContacts("John Doe", "101 N. 40th St Omaha NE 68131", "531-222-3333", "johndoe@gmail.com");
      
      // #2
      retrieveDisplayData(response.getWriter());
   }

   void retrieveDisplayData(PrintWriter out) {
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      List<SalesContact> listSalesContact = UtilDB.listSalesContact();
      for (SalesContact salesContact : listSalesContact) {
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
      out.println("</ul>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
