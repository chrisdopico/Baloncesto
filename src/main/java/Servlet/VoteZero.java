package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ModeloDatos;

@WebServlet("/VoteZero")
public class VoteZero extends HttpServlet{

    public VoteZero() {
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try 
		{
			boolean indicador = ModeloDatos.VoteZero();
			if(indicador)
			{
				out.print("Votos a Cero");
			}else
			{
				out.print("Error");
			}
			
		} catch (NumberFormatException e) 
		{
			out.println("Number Format Exception" + e);
		} catch (IndexOutOfBoundsException e) 
		{
			out.println("Index out of bounds Exception" + e);
		} finally 
		{
			out.close();
		}
	}

}
