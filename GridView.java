import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.*;


public class GridView extends JFrame implements Runnable
{
	Thread t;
	JFrame j1=new JFrame();
	JButton grid[][]=new JButton[10][10];
	int[] tempPos = new int[2];
	//For Setting * boarder side
	void upside()
	{	
		for(int j=0;j<10;j++)
		{
			grid[0][j].setForeground(Color.red);
			grid[0][j].setText("*");
		}
	}
	void rightside()
	{
		for(int i=0;i<10;i++)//upside
		{
			grid[i][9].setForeground(Color.red);
			grid[i][9].setText("*");
		}
	}
	void downside()
	{
		for(int i=0;i<10;i++)//upside
		{
			grid[9][i].setForeground(Color.red);
			grid[9][i].setText("*");
		}
	}
	void leftside() 
	{
		for(int i=0;i<10;i++)//upside
		{
			
			grid[i][0].setForeground(Color.red);
			grid[i][0].setText("*");
		}
	}
	void innerStar() 
	{
		
		grid[1][2].setForeground(Color.red);
		grid[1][3].setForeground(Color.red);
		grid[1][6].setForeground(Color.red);
		grid[1][2].setText("*");
		grid[1][3].setText("*");
		grid[1][6].setText("*");
		
		grid[2][3].setForeground(Color.red);
		grid[2][4].setForeground(Color.red);
		grid[2][3].setText("*");
		grid[2][4].setText("*");
		
		grid[3][1].setForeground(Color.red);
		grid[3][2].setForeground(Color.red);
		grid[3][4].setForeground(Color.red);
		grid[3][1].setText("*");
		grid[3][2].setText("*");
		grid[3][4].setText("*");
		
		grid[5][2].setForeground(Color.red);
		grid[5][3].setForeground(Color.red);
		grid[5][6].setForeground(Color.red);
		grid[5][2].setText("*");
		grid[5][3].setText("*");
		grid[5][6].setText("*");
		
		grid[7][3].setForeground(Color.red);
		grid[7][4].setForeground(Color.red);
		grid[7][6].setForeground(Color.red);
		grid[7][3].setText("*");
		grid[7][4].setText("*");
		grid[7][6].setText("*");
		
		grid[8][1].setForeground(Color.red);
		grid[8][2].setForeground(Color.red);
		grid[8][3].setForeground(Color.red);
		grid[8][1].setText("*");
		grid[8][2].setText("*");
		grid[8][3].setText("*");
	}
	void setPacmanAt()
	{
		
		grid[2][6].setText("@");
	}
	void setDollar()
	{
		
		grid[2][8].setText("$");
		grid[2][7].setText("$");
		grid[3][7].setText("$");
		grid[5][8].setText("$");
		
		
	}
	void GetPacPos() 
	{
		int i,j;
		for(i=0;i<10;i++)
		{
			for(j=0;j<10;j++)
			{
				if(grid[i][j].getText()=="@")
				{
					tempPos[0]=i;//row
					tempPos[1]=j;//column
	
				}
			}
		}
		//System.out.println(tempPos[0]+" "+tempPos[1]);
	}
	public int FindPosDollar()
	{
		int i,j;
	
		int countCheckTemp=0;
		for(i=0;i<10;i++)
		{
			for(j=0;j<10;j++)
			{
				if(grid[i][j].getText()=="$")
				{
					countCheckTemp=1;
						break;
				}
				if(countCheckTemp==1)
						break;
			}
		}
		return countCheckTemp;
	}
	void setDash()
	{
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				grid[i][j].setForeground(Color.black);
				grid[i][j].setText("-");
			}
		}
	}
	void ForwordMov() throws InterruptedException
	{
		GetPacPos();
		int i=tempPos[0];
		int j=tempPos[1];
	    int countForCheck=0;
		System.out.println("Position of i and j "+i+" "+j);
		do//Foreword Loop
		{
			Thread.sleep(1000);
			j1.revalidate();
			j1.repaint();
			//First Check With $
			if(grid[i-1][j].getText()=="$"||grid[i][j-1].getText()=="$"||grid[i+1][j].getText()=="$"||grid[i][j+1].getText()=="$")
			{
				
				if(grid[i-1][j].getText()=="$")//Move Up
				{
					grid[i-1][j].setText("#");
				}
				else if(grid[i][j+1].getText()=="$")//Move right
				{
					grid[i][j+1].setText("#");
				}
				else if(grid[i+1][j].getText()=="$")//Move Down
				{
					grid[i+1][j].setText("#");
				}
				else//Move Left
				{
					grid[i][j-1].setText("#");
					
				}
			}
			else
			{
				int k=j-1;
				if(grid[i-1][j].getText()=="-")
				{
					grid[i][j].setText("!");
					grid[i-1][j].setText("@");;
					i=i-1;
				
				}
				else if(grid[i][j+1].getText()=="-")
				{
					grid[i][j].setText("!");
					grid[i][j+1].setText("@");;
					j=j+1;
				}
				else if(grid[i+1][j].getText()=="-")
				{
					grid[i][j].setText("!");
					grid[i+1][j].setText("@");;
					i=i+1;
				}
				else if(grid[i][k].getText()=="-")
				{
					grid[i][j].setText("!");
					grid[i][j-1].setText("@");;
					j=j-1;
				}
				
			}
			countForCheck=FindPosDollar();
			System.out.println("--------------");
			System.out.println("Position of i and j "+"["+i+"]"+" "+"["+j+"]");
			System.out.println("--------------");
			//displayArray();
			System.out.println("countForCheck :"+countForCheck);
		}while(countForCheck==1);
		
	}
	GridView()
	{
		try //Thread For Step By Step Executation 
		{
			t=new Thread(this);
			t.start();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		j1.setSize(850,750);//Size of JFrame
		j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		j1.setVisible(true);//Sets if its visible.
		j1.setLayout(new GridLayout(10,10));
		//j1.setLayout(null);
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				//ImageIcon water = new ImageIcon("C:\\Users\\Yogeshwar\\Desktop\\wall.png");
				grid[i][j]=new JButton();
				//grid[i][j].setEnabled(false);
				grid[i][j].setForeground(Color.red);
				grid[i][j].setFont(new Font(Font.SANS_SERIF,Font.BOLD,25));
				//grid[i][j].setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.getHSBColor(1,1,1)));
				grid[i][j].setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.DARK_GRAY));
				
				add(grid[i][j]);
				j1.add(grid[i][j]);
			}
		}
		
		
	}
	public static void main(String[] args) throws InterruptedException 
	{
		GridView g1=new GridView();
		g1.setDash();
		g1.setDollar();
		g1.setPacmanAt();
		g1.innerStar();
		g1.leftside();
		g1.downside();
		g1.rightside();
		g1.upside();
		g1.GetPacPos();
		g1.ForwordMov();
		
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
