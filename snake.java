public class snake
{
	public static short[][] board; //where the snake lives
	public static char[][] screen;
	public static byte keys;
	public static byte keyUp;
	public static byte keyDown;
	public static byte keyLeft;
	public static byte keyRight;

	public static char snakeHead = '+';
	public static char snakeBody = '\u2588';
	public static byte sx = 5; //snake x position
	public static byte sy = 5; //snake y position
	public static byte dx;
	public static byte dy;
	public static short snakeLengh = 1;

	public static char apple = '@';
	public static byte ax;
	public static byte ay;
	public static byte appleExist = 0;
	
	public static void main(String[] args)
	{
		//load the c extension library(the ext.dll file)
		System.loadLibrary("ext");

		java.util.Random rand = new java.util.Random();

		board = new short[22][22];
		screen = new char[22][22];
	
		//set borders for the playfield
		board[0][0]   = -2;
		board[0][21]  = -2;
		board[21][0]  = -2;
		board[21][21] = -2;
		for(byte i = 1; i < 21; i++){board[0][i]  = -2;}
		for(byte i = 1; i < 21; i++){board[21][i] = -2;}
		for(byte i = 1; i < 21; i++){board[i][0]  = -2;}
		for(byte i = 1; i < 21; i++){board[i][21] = -2;}

		screen[0][0]   = '\u2554';
		screen[0][21]  = '\u2557';
		screen[21][0]  = '\u255A';
		screen[21][21] = '\u255D';	
		for(byte i = 1; i < 21; i++){screen[0][i]  = '\u2550';}
		for(byte i = 1; i < 21; i++){screen[21][i] = '\u2550';}
		for(byte i = 1; i < 21; i++){screen[i][0]  = '\u2551';}
		for(byte i = 1; i < 21; i++){screen[i][21] = '\u2551';}	

		for(byte i = 1; i < 21; i++)
		{
			for(byte j = 1; j < 21; j++)
			{
				board[i][j] = 0;
				screen[i][j] = ' ';
			}
		}

		while(true)
		{
			long startTime = System.nanoTime();
			long futureTime = startTime + milliToNano(200);

			for(int i = 1; i < 21; i++)
			{
				for(int j = 1; j < 21; j++)
				{
					if(board[i][j] > 0)
					{
						board[i][j] -= 1;
					}
				}
			}

			while(true)
			{
				ax = (byte)(rand.nextInt(21) + 1);
				ay = (byte)(rand.nextInt(21) + 1);

				if(appleExist == 1)
				{
					break;
				}

				if(board[ay][ax] == 0)
				{
					board[ay][ax] = -1;
					appleExist = 1;
					break;
				}
			}

			keyDown  = getInputDown();
			keyRight = getInputRight();
			keyUp    = getInputUp();
			keyLeft  = getInputLeft();

			if(keyDown>0)
			{
				dy = 1;
				dx = 0;
			}
			else if(keyUp>0)
			{
				dy = -1;
				dx = 0;
			}
			else if(keyLeft>0)
			{
				dx = -1;
				dy = 0;
			}
			else if(keyRight>0)
			{
				dx = 1;
				dy = 0;
			}
			sx += dx;
			sy += dy;

			if(board[sy][sx] == -1)
			{
				appleExist = 0;
				snakeLengh += 1;
			}
			if(board[sy][sx] > 0)
			{
				System.out.print("you lost");
				break;
			}
			if(board[sy][sx] == -2)
			{
				if(dx == 1)  sx = 1;
				if(dx == -1) sx = 21;
				if(dy == 1)  sy = 1;
				if(dy == -1) sy = 21;
			}

			board[sy][sx] = snakeLengh;

			for(int i = 1; i < 21; i++)
			{
				for(int j = 1; j < 21; j++)
				{
					if(board[i][j] == 0)
					{
						screen[i][j] = ' ';
					}

					if(board[i][j] == snakeLengh)
					{
						screen[i][j] = snakeHead;
					}

					if(board[i][j] > 0 & board[i][j] <snakeLengh)
					{
						screen[i][j] = snakeBody;
					}

					if(board[i][j] == -1)
					{
						screen[i][j] =  apple;
					}
				}
			}

			printscreen();

			long elapsedTime = System.nanoTime() - startTime;
			while(System.nanoTime()<futureTime){continue;}
			//System.out.print("startTime:"+startTime+"|elapsedTime:"+elapsedTime+"|futureTime:"+futureTime+"\n");
			clearScreen();
		}
	}
	public static long milliToNano(long n)
	{
			return n*1000000;
	}
	public static void printscreen()
	{
		for(byte i = 0; i < 22; i++)
		{
			for(byte j = 0; j < 22; j++)
			{
				System.out.print(screen[i][j]);
			}
			System.out.print("\n");
		}
	}
	public static native byte getInput();
	public static native byte getInputUp();
	public static native byte getInputDown();
	public static native byte getInputLeft();
	public static native byte getInputRight();
	
	public static native void clearScreen();
}
