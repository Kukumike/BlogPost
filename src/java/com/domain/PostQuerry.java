/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

import com.classes.database;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mukolwe Michael
 */
public class PostQuerry extends HttpServlet {

    database dbase = new database();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("email");
        String blog = request.getParameter("myquestion");
        String date = request.getParameter("date");

        if (blog.isEmpty()) {
            String ErrorPage = "/index.jsp";
            String blog_err = "Post a question correctly";

            request.setAttribute("ErrorMessage", blog_err);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ErrorPage);
            dispatcher.forward(request, response);
        } else {
            try {
                dbase.newConn();
                int postQuerryStatus;
                postQuerryStatus = dbase.postQuestion(username, blog, date);
                if (postQuerryStatus == 1) {
                    String page_url = "/index.jsp";
                    String post_success = "Successfull Postings";

                    request.setAttribute("post_succ", post_success);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page_url);
                    dispatcher.forward(request, response);
                } else {
                    String page_url = "/index.jsp";
                    String post_success = "Unsuccessfull Postings";

                    request.setAttribute("post_error", post_success);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page_url);
                    dispatcher.forward(request, response);
                }

            } catch (SQLException ex) {
                //Tell if no database is connected                    
                String ErrorMessage = "/index.jsp";
                String user_error = "Database Connection Unsuccessfull" + ex;

                request.setAttribute("ErrorMessage", user_error);
                request.setAttribute("error_filter", "login");

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ErrorMessage);
                dispatcher.forward(request, response);
            } catch (Exception ex) {
                //Tell if no database is connected                    
                request.getSession().invalidate();

                String ErrorMessage = "/index.jsp";
                String login_error = "Class Not Found" + ex;

                request.setAttribute("ErrorMessage", login_error);
                request.setAttribute("error_filter", "login");

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ErrorMessage);
                dispatcher.forward(request, response);
            }
        }
    }

}
