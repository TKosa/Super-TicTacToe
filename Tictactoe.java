//0.1 Made frame with keypad buttons and Menubar
//0.2 added actionListener for buttons, made new button class
//0.3 added nested class NineBoard, makes a 3x3 board of button+listener
//0.4 Frame contains nine NineBoards, making 81 squares. On click a square's text changes to X or O
//0.5 Game changes colors when a board or the game is won.
//0.6 Game almost fully functional, minor bugs exist
//0.7 Game fully functional.
//0.8 Improved code legibility. NewGame and Save buttons functional
//0.9 Game functional. Save and load methods functional.
//1.0 Arrow keys navigate the buttons, enter does a click
//1.1 Fixed bug where after using the mouse, arrow keys wouldn't work (crude fix)
//1.2 Overhauled Load/Save system, you can now save your game with a custom fileName


//todo: Open files saved on comp, make custom maps (loaded from net)


import java.io.*;
import java.nio.file.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class Tictactoe extends JFrame implements ActionListener {
	
	//start of declarations
	final String version= "1.2";
	int screenWidth=900;
	int screenHeight=650;
	int menuHeight=30;
	int turnCounter=0;
	int nextBoard=9;
	int lastBoard=9;
	
	Color red=Color.RED;Color blue=Color.BLUE;
	Color backgroundRed=new Color(255,51,51);
	Color backgroundBlue=new Color(0,153,255);
	Color foregroundRed=new Color(122,0,0);
	Color foregroundBlue=new Color(0,0,255);
	Color grey=new Color(193,193,193);
	Color blank=new JButton().getBackground();
	Border border=new LineBorder(Color.GREEN,2);
	Border defaultBorder=new Button().getBorder();
	
	boolean xWinGame=false, oWinGame=false;
	
	boolean alcol,shift=false;
	Button focusButton;
	JButton inst;
	
	JPanel whole=new JPanel();
		   
	//Menu bar and its buttons
		JMenuBar menu= new JMenuBar();
		Button 
		newGame=new Button("New Game"), 
		save=new Button("Save"),
		loadSave=new Button("Load Save"),
		help=new Button("Help");
	
	//NineBoards, one single board of nine buttons
		NineBoard
		nine1=new NineBoard(),
		nine2=new NineBoard(),
		nine3=new NineBoard(),
		nine4=new NineBoard(),
		nine5=new NineBoard(),
		nine6=new NineBoard(),
		nine7=new NineBoard(),
		nine8=new NineBoard(),
		nine9=new NineBoard();	
		
	
		NineBoard[] boardArray={nine1,nine2,nine3,nine4,nine5,nine6,nine7,nine8,nine9};
	//New button array to make focusing easier (left/right arrow key slection)
		Button[][] focusArray=
		{{newGame,save,loadSave,help},
		{nine1.b1,nine1.b2,nine1.b3,nine2.b1,nine2.b2,nine2.b3,nine3.b1,nine3.b2,nine3.b3,},
		{nine1.b4,nine1.b5,nine1.b6,nine2.b4,nine2.b5,nine2.b6,nine3.b4,nine3.b5,nine3.b6,},
		{nine1.b7,nine1.b8,nine1.b9,nine2.b7,nine2.b8,nine2.b9,nine3.b7,nine3.b8,nine3.b9,},
		
		{nine4.b1,nine4.b2,nine4.b3,nine5.b1,nine5.b2,nine5.b3,nine6.b1,nine6.b2,nine6.b3,},
		{nine4.b4,nine4.b5,nine4.b6,nine5.b4,nine5.b5,nine5.b6,nine6.b4,nine6.b5,nine6.b6,},
		{nine4.b7,nine4.b8,nine4.b9,nine5.b7,nine5.b8,nine5.b9,nine6.b7,nine6.b8,nine6.b9,},
		
		{nine7.b1,nine7.b2,nine7.b3,nine8.b1,nine8.b2,nine8.b3,nine9.b1,nine9.b2,nine9.b3,},
		{nine7.b4,nine7.b5,nine7.b6,nine8.b4,nine8.b5,nine8.b6,nine9.b4,nine9.b5,nine9.b6,},
		{nine7.b7,nine7.b8,nine7.b9,nine8.b7,nine8.b8,nine8.b9,nine9.b7,nine9.b8,nine9.b9,}};
		int focusx,focusy=0;

	JFrame frame=new JFrame("Tic-Tac-Toe Version: " + version);
	
	
	
	Dimension menuButtonSize=new Dimension(screenWidth/4,menuHeight);
	
	public class Button extends JButton
	{
		boolean X=false,O=false;
		Button()
		{
			super(); 
			this.setPreferredSize(new Dimension(50,50));
			this.setText(" ");
			this.setFocusable(false);
		}
		Button(String s)
		{
			this();
			this.setText(s);
		}
	}

	public class NineBoard extends JPanel implements ActionListener 
	{
		public boolean X=false,O=false,won=false;
		
		Button 
		b1=new Button(),
		b2=new Button(),
		b3=new Button(),
		b4=new Button(),
		b5=new Button(),
		b6=new Button(),
		b7=new Button(),
		b8=new Button(),
		b9=new Button();

		Button[] buttonArray={b1,b2,b3,b4,b5,b6,b7,b8,b9};
		
		public NineBoard ()
		{			
		//3x3, phone format
		JPanel 
		
		topPane   =new JPanel(),
		middlePane=new JPanel(),
		bottomPane=new JPanel();
		
		//add ActionListeners
		for (Button button:buttonArray)
		{
		 button.addActionListener(this);
		}
		topPane.add(b1);
		topPane.add(b2);
		topPane.add(b3);
		
		middlePane.add(b4);
		middlePane.add(b5);
		middlePane.add(b6);
		
		bottomPane.add(b7);
		bottomPane.add(b8);
		bottomPane.add(b9);
		
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(150,150));
		
		this.add(topPane,BorderLayout.NORTH);
		this.add(middlePane,BorderLayout.CENTER);
		this.add(bottomPane,BorderLayout.SOUTH);
		
		}//End of NineBoard constructor
		
		public void color(Color c)
		{
			//Colors the board a color, inputs are blue, red, blank and grey
			if (c==blue)for (Button b:buttonArray){b.setBackground(backgroundBlue);b.setForeground(foregroundBlue);}
			if (c==red)for (Button b:buttonArray){b.setBackground(backgroundRed);b.setForeground(foregroundRed);} 
			if (c==blank)for (Button b:buttonArray){b.setBackground(blank);b.setForeground(Color.BLACK);}
			if (c==grey)for (Button b:buttonArray){b.setBackground(grey);b.setForeground(Color.BLACK);}	
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
		//turn button text to X or O on click
		Button source=(Button)e.getSource();
		
			// "" means the button is unclickable
			if(source.getText()==""){return;}
			
			if(source.getText()==" ")
			//If the board is open for selection
			{if(nextBoard==9|| nextBoard==whichBoard(source))	
				{
					if (turnCounter%2==0)
					{ source.setText("X");
					  source.X=true; }
					else
					{ source.setText("O");
					  source.O=true; }
					turnCounter+=1;		
					
					nextBoard=(whichButton(source));
					lastBoard=(whichBoard(source));	
				}	
			}
		//Check if the board is won	
		if(boardWon(this)){return;}
		//If the next board to play on is won, set all boards that aren't won to be clickable
		try
		{if (boardArray[nextBoard].won==true){nextBoard=9;alcol=true;}
		}
		catch (ArrayIndexOutOfBoundsException ex){alcol=true;}
		
		//Color the boards appropriately
		if (alcol==true)allColor();	
		else recolor();
		
		}//end of actionPerformed
	//Returns which position button was clicked on a nineboard
		public int whichButton(Button source)
		{
			for (int x=0;x<9;x++)
			{if (source==this.buttonArray[x])return x;}
			return 9;
		}
		
		public int whichBoard(Button source)
		{
			for(int x=0;x<9;x++)
			{	
				if (boardArray[x]==source.getParent().getParent())return x;	
			}
			return 9;
		}
	}//end of NineBoard class
	
//Game constructor
public Tictactoe() 
{
	frame.setVisible(true);
	frame.setSize(screenWidth,screenHeight);
	frame.setLocation(100, 100);
	frame.setResizable(true);
	frame.setLayout(new BorderLayout());
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//Menu
	newGame.setPreferredSize(menuButtonSize);
	save.setPreferredSize(menuButtonSize);
	loadSave.setPreferredSize(menuButtonSize);
	help.setPreferredSize(menuButtonSize);
	
	newGame.addActionListener(this);
	save.addActionListener(this);
	loadSave.addActionListener(this);
	help.addActionListener(this);
	
	menu.add(newGame);
	menu.add(save);
	menu.add(loadSave);
	menu.add(help);
	
	frame.add(menu,BorderLayout.NORTH);
	//End of Menu
	
	//Start of Nineboards
	JPanel
	topRow=new JPanel(),
	midRow=new JPanel(),
	botRow=new JPanel();

	topRow.add(nine1);
	topRow.add(nine2);
	topRow.add(nine3);

	midRow.add(nine4);
	midRow.add(nine5);
	midRow.add(nine6);

	botRow.add(nine7);
	botRow.add(nine8);
	botRow.add(nine9);
	
	whole.add(topRow);
	whole.add(midRow);
	whole.add(botRow);
	
	frame.add(whole);
	
	//End of Nineboards
	focusButton=focusArray[0][0];
	
	
	
	
	
	frame.addKeyListener(new KeyListener(){

		@Override
		public void keyPressed(KeyEvent e) {
			focusButton.setBorder(defaultBorder);
			//If up, down, left or right
			//Up code handles menu as well
			if (e.getKeyCode()==38){if(focusy==1)focusx=0;
				if(focusy>0)focusy-=1;if(shift && focusy>1)focusy-=2;}
			if (e.getKeyCode()==40){if(focusy<9)focusy+=1;if(shift && focusy<8)focusy+=2;}
			if (e.getKeyCode()==37){if(focusx>0)focusx-=1;if(shift && focusx>1)focusx-=2;}
			if (e.getKeyCode()==39){if(focusx<8)focusx+=1;if(shift && focusx<7)focusx+=2;}
			if (e.getKeyCode()==16){shift=true;}
			
			focusButton= focusArray[focusy][focusx];
			focusButton.setBorder(border);
			if (e.getKeyCode()==10){focusButton.doClick();}}
			
		
		public void keyReleased(KeyEvent e) {if (e.getKeyCode()==16) shift=false;}
		public void keyTyped(KeyEvent arg0) {	
		}});
}//End of TicTacToe constructor

@Override
public void actionPerformed(ActionEvent e) {
	
	String text=((JButton) e.getSource()).getText();
	
	if (  text=="New Game")
	newGame();
				
	if ( text=="Save")
		try {save();} catch (IOException ex) {
			ex.printStackTrace();
			}
	if (  text=="Load Save")
		try {loadSave();} catch (IOException e1) {e1.printStackTrace();}
		
	if (  text=="Help"){help();}
	
}
//s stands for StateArray 
public boolean checkBoard(boolean s[])
{
	if (s[0]==true && s[1]==true && s[2]==true)return true;
	if (s[3]==true && s[4]==true && s[5]==true)return true;
	if (s[6]==true && s[7]==true && s[8]==true)return true;
	if (s[0]==true && s[3]==true && s[6]==true)return true;
	if (s[1]==true && s[4]==true && s[7]==true)return true;
	if (s[2]==true && s[5]==true && s[8]==true)return true;
	if (s[0]==true && s[4]==true && s[8]==true)return true;
	if (s[2]==true && s[4]==true && s[6]==true)return true;
	
	return false;
}

public void newGame()
{
	for (NineBoard board:boardArray){
		board.won=false;
		for (Button b:board.buttonArray){
			b.setBackground(blank);
			b.setForeground(Color.black);
			b.setText(" ");
			b.X=false;
			b.O=false;	
		}
	}
	nextBoard=9;
}
public void save() throws IOException
{	
	String fileName=JOptionPane.showInputDialog("Save File Name");
	fileName+=".txt";
	Path path=createPath("./saves",fileName);
	//Write to file
	File file=path.toFile();
	try(PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(file))))
	{	
		out.println(this.turnCounter);
		out.println(this.nextBoard);
		
		String text="";
		for (NineBoard board:boardArray){
			if(board.won==true){
			if(board.buttonArray[0].getBackground()==backgroundRed)	
			text+="w";
			else if (board.buttonArray[0].getBackground()==backgroundBlue)text+="l";
			}
			else text+="n";
			
		}
			
		out.println(text);
		text="";
		for (NineBoard board:boardArray)
		{
			for (Button b:board.buttonArray)
			{if (b.getText().equals(" "))text+="~";
			else if (b.getText().equals(""))text+="`";
			else text+=b.getText();
			}	
			out.println(text);text="";
		}
		
	}
	catch (IOException e){System.out.print(e);}	
}
//Called on load Save button click
//Handles File/Directories
public void loadSave() throws IOException
{	
	//Clear the Board
	newGame();
	
	
	Path dirPath=createPath("./saves");
	File dirFile=dirPath.toFile();
	JFileChooser fc = new JFileChooser();
    fc.setCurrentDirectory(dirFile);
    
     int rVal = fc.showOpenDialog(Tictactoe.this);
     if (rVal == JFileChooser.APPROVE_OPTION) {
       String fileName=fc.getSelectedFile().getName();
       File textFile=createPath(dirPath.toString(),fileName).toFile();
       load(textFile);
     										  }
     if (rVal == JFileChooser.CANCEL_OPTION) {}
}
public void load(File file)
{
	String line = null;
	
	try(BufferedReader in=new BufferedReader(new FileReader(file)))
	{	//First line will indicate which board the players left off at
		turnCounter=Integer.parseInt(in.readLine());
		nextBoard=Integer.parseInt(in.readLine());
		char[] carray=in.readLine().toCharArray();
		for(int x=0;x<9;x++){
			
			String s=""+ carray[x];
			if(!s.equals("n"))boardArray[x].won=true;
			if(s.equals("w")){boardArray[x].X=true;boardArray[x].color(red);}
			if(s.equals("l")){boardArray[x].O=true;boardArray[x].color(blue);}
			
			boardWon(boardArray[x]);
			
					}
		
		
		for (int x=0;x<9;x++)
		{	
			line=in.readLine();
			char[] charArray=line.toCharArray();
			
			for (int y=0;y<9;y++)
			{
					String c=null;
					Button b=boardArray[x].buttonArray[y];
					
					if(charArray[y]=="~".charAt(0))
					c=" ";
					else if(charArray[y]=="`".charAt(0))
					c="";
					else c=""+ (charArray[y]);			
					
					b.setText(c);
					if(c.equals("O")){b.O=true;b.X=false;}
					else if(c.equals("X")){b.X=true;b.O=false;}
					else {b.X=false;b.O=false;b.setText(" ");}
			
			}
		}
			
	}
	catch(IOException e){System.out.print(e);}
	if (nextBoard!=9)recolor();
	else allColor();
}	

public void help()
{
	JOptionPane.showMessageDialog(null,"The game is seperated into 9 different boards. "
	 +"A board is won when three of the same pieces form a line. The game is won in the same way, using the entire boards as pieces. \n"
	 + " The catch, however, is that the position of the board a player can play in is the same as the position of the box the last player went in."
	 + " \n To help make this easier, boards which can be played in will be highlighted in gray.","Help", JOptionPane.INFORMATION_MESSAGE);
}
public Path createPath(String folder, String file) throws IOException
{
	//Make a folder and txt file to save data in
		Path path1= Paths.get(folder);
		if (Files.notExists(path1))Files.createDirectories(path1);
		Path path2= Paths.get(folder,file);
		if(Files.notExists(path2)){Files.createFile(path2);}
		return path2;
}
public Path createPath(String folder)throws IOException
{
	Path path= Paths.get(folder);
	if (Files.notExists(path))Files.createDirectories(path);
	return path;
}
public void recolor ()
{

	for (NineBoard board:boardArray)
	{
		if (board.won==false)
		{
			for (Button button:board.buttonArray){button.setBackground(blank);}
			if(boardArray[nextBoard].won==false){
			for (Button b:boardArray[nextBoard].buttonArray){b.setBackground(grey);}
			}
	}	
}
	
}		
public void allColor()
{	
	for (NineBoard board:boardArray)
	{ 
		if (board.won==false)
		for (Button b:board.buttonArray){b.setBackground(grey);}			
	}	
	alcol=false;
	
}
//Check if board is won and set colours and text. Return true iff game is won. 
public boolean boardWon(NineBoard b)
{	
	//checkboard to see if its won
			boolean[] XArray={b.buttonArray[0].X,b.buttonArray[1].X,b.buttonArray[2].X,b.buttonArray[3].X,b.buttonArray[4].X,b.buttonArray[5].X,b.buttonArray[6].X,b.buttonArray[7].X,b.buttonArray[8].X};
			boolean[] OArray={b.buttonArray[0].O,b.buttonArray[1].O,b.buttonArray[2].O,b.buttonArray[3].O,b.buttonArray[4].O,b.buttonArray[5].O,b.buttonArray[6].O,b.buttonArray[7].O,b.buttonArray[8].O};
			
			boolean xWinBoard=checkBoard(XArray),
					oWinBoard=checkBoard(OArray);
			//If the board is won change colors and make button unclickable
			if (xWinBoard|| oWinBoard)
				{	
					b.won=true;
					if (xWinBoard)b.X=true;
					if (oWinBoard)b.O=true;
				
					for (Button button:b.buttonArray)
					{	
						if (button.getText().equals("X"))button.setForeground(foregroundRed);
						if (button.getText().equals("O")) button.setForeground(foregroundBlue);
						if(button.getText().equals(" ")){button.setText("");}
					
						if(xWinBoard)button.setBackground(backgroundRed);
						if(oWinBoard)button.setBackground(backgroundBlue);
					}
					//When a board is won check if the game is over
					boolean[] xArray={nine1.X,nine2.X,nine3.X,nine4.X,nine5.X,nine6.X,nine7.X,nine8.X,nine9.X},
							  oArray={nine1.O,nine2.O,nine3.O,nine4.O,nine5.O,nine6.O,nine7.O,nine8.O,nine9.O};
					 xWinGame=checkBoard(xArray);
					 oWinGame=checkBoard(oArray);
					//If the game is over make all buttons unclickable
					if (xWinGame||oWinGame){
					for(NineBoard board:boardArray){
						if(board.won==false){board.color(blank);}
						for(Button button:board.buttonArray){
							if (button.getText()==" "){button.setText("");}
							}
						}
					return true;
					}	
					
				}
				return false;
}
public static void main(String[] args) {new Tictactoe(); }

}



