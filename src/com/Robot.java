package com;

import java.util.Scanner;

public class Robot {
	public static String rotateRobot(String move,String currentpos)
	{
		switch (currentpos) {
		case "N":
			currentpos=(move.equals("R")?"E":"W");
			break;
		case "E":
			currentpos=(move.equals("R")?"S":"N");
			break;
		case "S":
			currentpos=(move.equals("R")?"W":"E");
			break;
		case "W":
			currentpos=(move.equals("R")?"N":"S");
			break;
		}
		return currentpos;
	}

	public static void getPosition(int maxX, int maxY,int x, int y, String dir, String expression, boolean[][] particles)
	{
		boolean[][] visited=new boolean[maxX+1][maxY+1];
		visited[x][y]=true;
		int expLength=expression.length();
		for(int i=0;i<expLength;i++)
		{
			String tmp= ""+expression.charAt(i);
			
			if(tmp.equals("M"))
			{
				int xtemp=x;
				int ytemp=y;
				if(dir.equals("N"))
					ytemp++;
				else if(dir.equals("E"))
					xtemp++;
				else if(dir.equals("S"))
					ytemp--;
				else
					xtemp--;
				
				if((xtemp>=0 && xtemp<=maxX)&&(ytemp>=0 && ytemp<=maxY) && (!visited[xtemp][ytemp]) && (!particles[xtemp][ytemp])) // checks if move is leading towards any failure condition
				{
					x=xtemp;
					y=ytemp;
					visited[x][y]=true;
				}
				else
					break; // Robot will stop moving if it fails to fulfil above condition
			}
			else
			{
				dir = rotateRobot(tmp, dir);
			}
		}
		System.out.println(x + " " + y + " "+ dir);
	}

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the top-right coordinates of the rectangular plan");
		String s=sc.nextLine();
		String arr[]=s.split("\\s");
		int maxX=Integer.parseInt(arr[0]),maxY=Integer.parseInt(arr[1]);
		System.out.println("enter current position of robot in x-y plane");
		String s1=sc.nextLine();
		String arr1[]=s1.split("\\s");
		int x= Integer.parseInt(arr1[0]),y=Integer.parseInt(arr1[1]);
		String dir=arr1[2];
		System.out.println("Enter series of commands for the robot");
		String expression=sc.nextLine();
		//System.out.println(maxX+" "+maxY+" "+x+" "+y+" "+dir+" "+expression);
		boolean particles[][] = new boolean[maxX+1][maxY+1];
		System.out.println("enter no of particles present in x-y plane");
		int noOfParticles=Integer.parseInt(sc.nextLine());
		System.out.println("enter the particles postion");
		for(int n=0;n<noOfParticles;n++)
		{
			String s2=sc.nextLine();
			String position[]=s2.split("\\s");
			particles[Integer.parseInt(position[0])][Integer.parseInt(position[1])]=true;
		}
			
		getPosition(maxX, maxY, x, y, dir, expression, particles);
		
	}

}
